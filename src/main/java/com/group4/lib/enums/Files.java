package com.group4.lib.enums;

public enum Files {
    User("user"),
    Hall("hall");

    private String fileName;

    Files(String fileName) {
        this.fileName = fileName;
    }

    public String getPage() {
        return fileName;
    }
}
