package com.prometheus2;

public class MyJson {
    private String text = "";

    public MyJson() {
    }

    public MyJson(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
