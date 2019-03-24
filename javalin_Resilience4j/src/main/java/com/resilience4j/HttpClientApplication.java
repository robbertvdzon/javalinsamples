package com.resilience4j;

import io.javalin.Javalin;

public class HttpClientApplication {
    Javalin app;

    public static void main(String[] args) {
        new HttpClientApplication().start();
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
