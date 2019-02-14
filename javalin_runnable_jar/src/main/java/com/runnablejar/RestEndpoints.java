package com.runnablejar;

import io.javalin.Javalin;

public class RestEndpoints {

    public void initRestEndpoints(Javalin app) {
        app.get("/", ctx -> ctx.result("Hello World from javalin"));
        app.get("/json", ctx -> ctx.json(new MyJson("Hello World")));
        app.post("/json", ctx -> ctx.result("Got post of " + ctx.bodyAsClass(MyJson.class).getText()));
    }
}
