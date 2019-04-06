package com.rxjava;

import io.javalin.Javalin;

public class JavalinRxjavaApplication {
    Javalin app;

    public static void main(String[] args) {
        new JavalinRxjavaApplication().start();
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
