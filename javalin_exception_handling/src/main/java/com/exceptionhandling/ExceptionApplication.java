package com.exceptionhandling;

import io.javalin.Javalin;

public class ExceptionApplication {
    Javalin app;

    public static void main(String[] args) {
        new ExceptionApplication().start();
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
