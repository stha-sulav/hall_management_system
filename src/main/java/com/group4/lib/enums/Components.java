package com.group4.lib.enums;

public enum Components {
    Menu("menu"),
    Dashboard("dashboard"),
    Student("student"),
    Hall("hall"),
    Room("room"),
    Setting("setting");

    private String component;

    Components(String component) {
        this.component = component;
    }

    public String getPage() {
        return component;
    }
}
