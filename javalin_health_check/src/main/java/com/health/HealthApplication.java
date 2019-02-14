package com.health;

import io.javalin.Javalin;

public class HealthApplication {
    Javalin app;
    Javalin managementApp;

    public static void main(String[] args) {
        new HealthApplication().start();
    }

    public void start(){
        app = Javalin.create();
        new RestEndpoints().initRestEndpoints(app);
        app.start(8080);

        managementApp = Javalin.create();
        new HealthCheck().initRestEndpoints(managementApp);
        managementApp.start(8090);
    }

    public void stop(){
        app.stop();
        managementApp.stop();
    }
}
