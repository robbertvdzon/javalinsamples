package com.staticfiles;

import io.javalin.Javalin;

public class StaticFilesApplication {
    Javalin app;

    public static void main(String[] args) {
        new StaticFilesApplication().start();
    }

    public void start(){
        app = Javalin.create();
        app.enableStaticFiles("/html");
        new RestEndpoints().initRestEndpoints(app);
        app.start(8080);
    }

    public void stop(){
        app.stop();
    }
}
