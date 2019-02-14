package com.beanvalidation;

import com.beanvalidation.validation.BeanValidator;
import io.javalin.Context;
import io.javalin.Javalin;

public class RestEndpoints {

    BeanValidator beanValidator;

    public RestEndpoints(BeanValidator beanValidator) {
        this.beanValidator = beanValidator;
    }

    public void initRestEndpoints(Javalin app) {
        app.get("/", ctx -> ctx.result("Hello World from javalin"));
        app.get("/json", ctx -> ctx.json(new MyJson("Hello World")));
        app.post("/json", this::handlePost);
    }

    private void handlePost(Context ctx) {
        MyJson myJson = ctx.bodyAsClass(MyJson.class);
        beanValidator.validateBean(myJson);
        ctx.result("Got post of " + myJson.getText());
    }

}
