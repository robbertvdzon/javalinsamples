package com.exceptionhandling;

import io.javalin.Javalin;

public class RestEndpoints {

    public void initRestEndpoints(Javalin app) {

        app.exception(MyException.class, (e, ctx) -> {
            ctx.status(500);
            ctx.result("Something went wrong! Error=" + e.getMessage());
        });


        app.get("/", ctx -> ctx.result("Hello World from javalin"));
        app.get("/json", ctx -> ctx.json(new MyJson("Hello World")));
        app.post("/json", ctx -> ctx.result("Got post of " + ctx.bodyAsClass(MyJson.class).getText()));
        app.get("/error", ctx -> {throw new MyException("oops");});
    }
}
