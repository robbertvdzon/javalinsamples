package com.base;

import io.javalin.Javalin;

public class JavalinBaseApplication {
    Javalin app;

    public static void main(String[] args) {
        new JavalinBaseApplication().start();
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
