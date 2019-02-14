package com.sql;

import io.javalin.Javalin;

public class RestEndpoints {
    Database database;

    public RestEndpoints(Database database) {
        this.database = database;
    }

    public void initRestEndpoints(Javalin app) {
        app.get("/", ctx -> ctx.result("Hello World from javalin"));
        app.get("/json", ctx -> ctx.json(new MyJson("Hello World")));
        app.post("/json", ctx -> ctx.result("Got post of " + ctx.bodyAsClass(MyJson.class).getText()));
        app.get("/persons", ctx -> ctx.json(database.loadPersons()));
    }
}
