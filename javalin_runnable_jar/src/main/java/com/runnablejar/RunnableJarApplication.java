package com.runnablejar;

import io.javalin.Javalin;

public class RunnableJarApplication {
    Javalin app;

    public static void main(String[] args) {
        new RunnableJarApplication().start();
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
