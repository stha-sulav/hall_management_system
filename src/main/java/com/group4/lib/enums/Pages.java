package com.group4.lib.enums;

public enum Pages {
    Login("login"),
    Signup("signup"),
    ForgotPassword("forgotPassword"),
    Dashboard("dashboard"),
    Student("student"),
    Hall("hall"),
    Room("room"),
    Setting("setting"),
    Menu("menu");

    private String view;

    Pages(String view) {
        this.view = view;
    }

    public String getPage() {
        return view;
    }
}
