package com.dependencyinjection;

import io.javalin.Javalin;

import javax.inject.Inject;

public class RestEndpoints {

    private Javalin app;
    private HelloWorldService helloWorldService;

    @Inject
    public RestEndpoints(Javalin app, HelloWorldService helloWorldService) {
        this.app = app;
        this.helloWorldService = helloWorldService;
    }

    public void init() {
        app.get("/", ctx -> ctx.result(helloWorldService.getHelloWorldText()));
        app.get("/json", ctx -> ctx.json(new MyJson("Hello World")));
        app.post("/json", ctx -> ctx.result("Got post of " + ctx.bodyAsClass(MyJson.class).getText()));
    }


}
