package com.thymeleaf;

import io.javalin.Javalin;

public class ThymeleafApplication {
    Javalin app;

    public static void main(String[] args) {
        new ThymeleafApplication().start();
    }

    public void start(){
        app = Javalin.create();
        new RestEndpoints().initRestEndpoints(app);
        app.start(8080);
    }

    public void stop(){
        app.stop();
    }
}
