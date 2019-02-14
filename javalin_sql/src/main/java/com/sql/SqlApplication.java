package com.sql;

import io.javalin.Javalin;

public class SqlApplication {
    Javalin app;

    public static void main(String[] args) {
        new SqlApplication().start();
    }

    public void start() {
        Database database = new Database();
        database.storePersons();

        app = Javalin.create();
        new RestEndpoints(database).initRestEndpoints(app);
        app.start(8080);
    }

    public void stop() {
        app.stop();
    }
}
