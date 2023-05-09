package com.bluewhaleyt.materialfileicon;

public class FileItem {
    private String name;
    private String path;
    private int icon;
    private boolean isDirectory;

    public FileItem(String name, String path, int icon, boolean isDirectory) {
        this.name = name;
        this.path = path;
        this.icon = icon;
        this.isDirectory = isDirectory;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public int getIcon() {
        return icon;
    }

    public boolean isDirectory() {
        return isDirectory;
    }
}