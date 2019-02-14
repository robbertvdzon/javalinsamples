package com.demojsonlog;

import io.javalin.Javalin;

public class JavalinJsonLoggingApplication {
    public static void main(String[] args) {
        Javalin app = Javalin.create();
        new RestEndpoints().initRestEndpoints(app);
        app.start(8080);
    }
}
