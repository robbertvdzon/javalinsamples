package com.beanvalidation;

import javax.validation.constraints.Size;

public class MyJson {

    @Size(min = 1, max = 200, message = "Text must be between 1 and 200 characters")
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
