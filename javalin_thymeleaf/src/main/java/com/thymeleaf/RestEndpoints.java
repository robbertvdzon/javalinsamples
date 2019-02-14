package com.thymeleaf;

import io.javalin.Javalin;

import static io.javalin.rendering.template.TemplateUtil.model;

public class RestEndpoints {

    public void initRestEndpoints(Javalin app) {
        app.get("/", ctx -> ctx.result("Hello World from javalin"));
        app.get("/json", ctx -> ctx.json(new MyJson("Hello World")));
        app.post("/json", ctx -> ctx.result("Got post of " + ctx.bodyAsClass(MyJson.class).getText()));
        app.get("/page", ctx -> ctx.render("/templateFile.html", model("firstName", "John", "lastName", "Doe")));
    }
}
