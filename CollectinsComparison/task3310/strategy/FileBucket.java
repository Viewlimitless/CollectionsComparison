package com.javarush.task.task33.task3310.strategy;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileBucket {
    private Path path;

    public FileBucket() {
        Path path = null;
        try {
            path = Files.createTempFile(null, null);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Files.createFile(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.path = path;

        this.path.toFile().deleteOnExit();
    }

    public long getFileSize() {

        try {
            return Files.size(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void putEntry(Entry entry) {
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(path))) {
            oos.writeObject(entry);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Entry getEntry() {
        Entry entry = null;
        if (getFileSize() == 0) {
            return null;
        }
        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(path))) {
            entry = (Entry) ois.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return entry;
    }
    public void remove(){
        try {
            Files.delete(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
