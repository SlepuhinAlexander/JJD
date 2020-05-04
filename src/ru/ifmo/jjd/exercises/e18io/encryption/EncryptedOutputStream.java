package ru.ifmo.jjd.exercises.e18io.encryption;

import ru.ifmo.jjd.utils.Primes;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class EncryptedOutputStream extends FilterOutputStream {

    private final byte[] key = new byte[256];
    private int pos = -1;

    public EncryptedOutputStream(OutputStream out) {
        super(out);
    }

    /**
     * @param data is byte value from 0 to 255
     */
    private int encrypt(int data) {
        if (data < 0 || data > 255) throw new IllegalArgumentException("data value '" + data + "' must be in [0,255]");
        int encrypted = data ^ key[pos];
        pos = (pos + 1) % key.length;
        return encrypted;
    }

    /**
     * Generates the key for further encoding byte stream.
     * Uses the first incoming byte to write as the seed for keygen
     *
     * @param seed is byte value from 0 to 255.
     */
    public void keygen(int seed) {
        if (seed < 0 || seed > 255) throw new IllegalArgumentException("seed value '" + seed + "' must be in [0,255]");
        int notSeed = ~seed << 24 >>> 24;
        seed = seed * notSeed + seed * seed;
        if (seed <= 2) seed = 3;
        seed = (seed) % Primes.getBiggest();
        int lower = Primes.lowerPrime(seed);
        int notLower = ~lower << 8 >>> 8;
        int upper = Primes.upperPrime(seed);
        int notUpper = ~upper << 8 >>> 8;
        StringBuilder builder = new StringBuilder();
        long keygen = ((long) lower * notLower * upper * notUpper) << 1 >>> 1;
        do {
            builder.append(Long.toString(keygen, 32));
            keygen >>= 1;
        } while (keygen > 0);
        byte[] generated = builder.toString().getBytes();
        System.arraycopy(generated, 0, key, 0, 256);
        pos = 0;
    }

    @Override
    public void write(int b) throws IOException {
        if (pos < 0) {
            keygen(b);
            super.write(b);
            pos++;
        }
        super.write(encrypt(b));
    }
}
