package com.group4.lib.enums;

public enum Components {
    Menu("menu");

    private String component;

    Components(String component) {
        this.component = component;
    }

    public String getPage() {
        return component;
    }
}
