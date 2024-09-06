package it.its.springdemo.models;

public class FileModel {
    private String name;
    private String path;

    public FileModel(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }
}
