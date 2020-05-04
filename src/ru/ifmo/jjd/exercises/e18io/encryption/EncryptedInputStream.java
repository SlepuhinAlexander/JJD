package ru.ifmo.jjd.exercises.e18io.encryption;

import ru.ifmo.jjd.utils.Primes;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class EncryptedInputStream extends FilterInputStream {

    private final byte[] key = new byte[256];
    private int pos = -1;

    public EncryptedInputStream(InputStream in) {
        super(in);
    }

    /**
     * @param data is byte value from 0 to 255 or -1.
     */
    private int decrypt(int data) {
        if (data < -1 || data > 255) throw new IllegalArgumentException("data value '" + data +
                                                                        "' must be in [-1,255]");
        if (data == -1) return data;
        int decrypted = data ^ key[pos];
        pos = (pos + 1) % key.length;
        return decrypted;
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
        System.arraycopy(builder.toString().getBytes(), 0, key, 0, 256);
        pos = 0;
    }

    @Override
    public int read() throws IOException {
        int data = super.read();
        if (pos < 0) {
            keygen(data);
            pos++;
            return read();
        }
        return decrypt(data);
    }

    @Override
    public int read(byte[] b) throws IOException {
        return read(b, 0, b.length);
    }

    // copied from InputStream
    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        Objects.checkFromIndexSize(off, len, b.length);
        if (len == 0) {
            return 0;
        }

        int c = read();
        if (c == -1) {
            return -1;
        }
        b[off] = (byte)c;

        int i = 1;
        try {
            for (; i < len ; i++) {
                c = read();
                if (c == -1) {
                    break;
                }
                b[off + i] = (byte)c;
            }
        } catch (IOException ee) {
            ee.printStackTrace();
        }
        return i;
    }
}
