package com.group4.lib.enums;

public enum Pages {
    Login("login"),
    Signup("signup");

    private String view;

    Pages(String view) {
        this.view = view;
    }

    public String getPage() {
        return view;
    }
}
