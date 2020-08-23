package ru.ifmo.jjd.exercises.e18io.splitter;

import java.io.*;
import java.lang.invoke.MethodHandles;
import java.util.*;

import static ru.ifmo.jjd.utils.ConsoleHelper.println;

public class FileSplitter {

    public static void main(String[] args) {
        if (args.length < 1) {
            usage();
            return;
        }
        File theFile = findFile(args[0]);
        if (theFile == null) {
            println("File '" + args[0] + "' not found");
            return;
        }
        if (args.length == 1) {
            File[] chunks;
            try {
                chunks = collectChunks(theFile);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
            try {
                merge(chunks);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (args.length > 1) {
            long size = parseSize(args[1]);
            if (size == 0) {
                println("Malformed argument '" + args[1] + "'");
                usage();
                return;
            }
            if (size < 0) size = theFile.length() / -size;
            try {
                split(theFile, size);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void usage() {
        println("Usage:\n" +
                "[fileName / filePath]\n" +
                "(for any file chunk) to merge all corresponding file chunks into one file\n" +
                "\n" +
                "[fileName / filePath] [amount]\n" +
                "to split the given file into given amount of equally-sized chunks\n" +
                "\n" +
                "[fileName / FilePath] [1K / 1.44m / 2.4G / etc]\n" +
                "to split the given file into chunks occupying at most the given size\n");
    }

    private static File findFile(String path) {
        if (path == null) throw new NullPointerException("File path cannot be null");
        if (path.isEmpty()) throw new IllegalArgumentException("File path cannot be empty");
        File result;

        // check the case if given path string is a full path to a file
        if (path.contains(File.separator)) {
            result = new File(path);
            if (result.exists()) return result;
        }

        // otherwise build the relative path starting from current folder containing THIS class file
        Class<?> thisClass = MethodHandles.lookup().lookupClass();
        //noinspection ConstantConditions
        result = new File(thisClass.getClassLoader().
                getResource(thisClass.getName().replace(".", "/") + ".class").
                getFile()).getParentFile();

        String separator = File.separator;
        if (separator.equals("\\")) separator = separator.repeat(2); // to fix malformed regex
        String[] dirs = path.split(separator);

        for (String dir : dirs) {
            if (dir.equals("..")) {
                result = result.getParentFile();
            } else {
                result = new File(result + File.separator + dir);
            }
            if (!result.exists()) return null;
        }
        return result.exists() ? result : null;
    }

    // collect all files in the same folder having the same name and extension .part[number]
    private static File[] collectChunks(File file) throws IOException {
        if (file == null) throw new NullPointerException("File cannot be null");
        if (!file.exists()) throw new IOException("File '" + file + "' does not exist");
        if (!file.isFile()) throw new IOException("Provided file '" + file.getName() + "' is not a file");
        int chunkNumber = getChunkNumber(file);
        if (chunkNumber < 0) throw new IOException("Provided file '" + file.getName() + "' is not a file chunk");
        String name = getFileName(file);
        File folder = file.getParentFile();
        File[] folderFiles = folder.listFiles();
        if (folderFiles == null) throw new IOException("Could not collect files in folder '" + folder + "'");
        ArrayList<File> fileList = new ArrayList<>(Arrays.asList(folderFiles));
        Iterator<File> iterator = fileList.iterator();
        //noinspection Java8CollectionRemoveIf
        while (iterator.hasNext()) {
            File next = iterator.next();
            if (getChunkNumber(next) < 0) iterator.remove();
        }
        if (fileList.size() < 2) throw new IOException("There cannot be less than two chunks");
        //noinspection Convert2Lambda
        fileList.sort(new Comparator<>() {
            @Override
            public int compare(File o1, File o2) {
                return getChunkNumber(o1) - getChunkNumber(o2);
            }
        });
        File[] result = new File[fileList.size()];
        fileList.toArray(result);
        for (int i = 0; i < result.length; i++) {
            if (getChunkNumber(result[i]) != i + 1) {
                throw new IOException("Missing file '" + name + ".part" + (i + 1) + "'");
            }
        }
        return result;
    }

    private static String getFileName(File file) {
        if (file == null || !file.exists() || !file.isFile()) return "";
        String name = file.getName();
        int idx = name.lastIndexOf('.');
        return (idx >= 0) ? name.substring(0, idx) : name;
    }

    private static int getChunkNumber(File file) {
        if (file == null || !file.exists() || !file.isFile()) return -1;
        int ind = file.getName().lastIndexOf('.');
        if (ind < 0) return -1;
        String extension = file.getName().substring(ind + 1);
        if (!extension.startsWith("part")) return -1;
        int result;
        try {
            result = Integer.parseInt(extension.substring("part".length()));
        } catch (NumberFormatException e) {
            return -1;
        }
        return result;
    }

    private static long parseSize(String sizeString) {
        if (sizeString == null) throw new NullPointerException();
        if (sizeString.isEmpty()) throw new IllegalArgumentException("size cannot be null");
        sizeString = sizeString.toLowerCase().trim();
        char lastChar = sizeString.charAt(sizeString.length() - 1);
        long result = 0;
        if (Character.isDigit(lastChar)) {
            try {
                result = Long.parseLong(sizeString);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                return 0;
            }
            return result > 1 ? -result : 0;
        }
        sizeString = sizeString.substring(0, sizeString.length() - 1);
        double size;
        try {
            size = Double.parseDouble(sizeString);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
        switch (lastChar) {
            case 'b':
                result = (long) size;
                break;
            case 'k':
                result = (long) (size * 1024);
                break;
            case 'm':
                result = (long) (size * 1024) * 1024;         // round to kb
                break;
            case 'g':
                result = (long) (size * 1024) * 1024 * 1024; // round to mb
                break;
        }
        return result;
    }

    private static void split(File file, long maxSize) throws IOException {
        if (file == null) throw new NullPointerException("File cannot be null");
        if (!file.exists()) throw new IOException("File '" + file + "' does not exist");
        if (!file.isFile()) throw new IOException("Provided file '" + file + "' is not a file");
        if (!file.canRead()) throw new IOException("Cannot read the file '" + file + "'");
        if (maxSize <= 1024) throw new IllegalArgumentException("File chunk size cannot be less than 1 Kb");
        long fileSize = file.length();
        if (fileSize <= maxSize) {
            System.out.println("Operation aborted: File '" + file + "' is too small to split.");
            return;
        }
        File folder = new File(file.getParent());
        int partNumber = 1;
        String fileName = file.getName();
        File chunkFile = new File(folder + File.separator + fileName + ".part" + partNumber);
        byte[] chunkBuf = new byte[(int) Math.min(8196, maxSize)];
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(file))) {
            int bytesRead;
            long remainingToWrite = maxSize;
            while ((bytesRead = in.read(chunkBuf, 0, (int) Math.min(chunkBuf.length, remainingToWrite))) > 0) {
                try (BufferedOutputStream out = new BufferedOutputStream(
                        new FileOutputStream(chunkFile, true))) {
                    out.write(chunkBuf, 0, bytesRead);
                    remainingToWrite -= bytesRead;
                } catch (IOException e) {
                    println("Failed to write to file '" + chunkFile.getName() + "'");
                    e.printStackTrace();
                    abortSplit(file);
                }
                if (remainingToWrite <= 0) {
                    partNumber++;
                    chunkFile = new File(folder + File.separator + fileName + ".part" + partNumber);
                    remainingToWrite = maxSize;
                }
            }
        } catch (IOException e) {
            println("Failed to read from file '" + fileName + "'");
            e.printStackTrace();
            abortSplit(file);
        }
    }

    private static void abortSplit(File file) throws IOException {
        println("Split operation failed. Aborting:");
        File[] chunks = collectChunks(new File(file.getAbsolutePath() + ".part1"));
        for (File chunk : chunks) {
            println("deleting '" + chunk.getName() + "'");
            if (!chunk.delete()) throw new IOException("failed to delete '" + chunk.getName() + "'");
        }
    }

    private static void merge(File[] chunks) throws IOException {
        if (chunks == null) throw new NullPointerException();
        if (chunks.length < 2) throw new IllegalArgumentException("There cannot be less than two file chunks");
        if (!chunks[0].getName().endsWith(".part1")) {
            throw new IllegalArgumentException("'" + chunks[0].getName() + "' is not the first file chunk");
        }

        File result = new File(chunks[0].getAbsolutePath().
                substring(0, chunks[0].getAbsolutePath().length() - ".part1".length()));
        Vector<InputStream> sequence = new Vector<>();
        for (File chunk : chunks) {
            sequence.add(new FileInputStream(chunk));
        }
        try (BufferedInputStream in = new BufferedInputStream(new SequenceInputStream(sequence.elements()));
             BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(result))) {
            int data;
            while ((data = in.read()) >= 0) out.write(data);
        }
        for (File chunk : chunks) {
            if (!chunk.delete()) throw new IOException("failed to delete '" + chunk.getName() + "'");
        }
    }

    private static void abortMerge(File file) throws IOException {
        println("Merge operation failed. Aborting: ");
        println("deleting " + file.getName());
        if (!file.delete()) throw new IOException("failed to delete '" + file.getName() + "'");
    }
}
