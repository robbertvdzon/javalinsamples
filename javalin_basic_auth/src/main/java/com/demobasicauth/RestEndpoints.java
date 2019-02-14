package com.demobasicauth;

import com.demobasicauth.domain.UserRole;
import io.javalin.Javalin;

import static io.javalin.security.SecurityUtil.roles;

public class RestEndpoints {

    public void initRestEndpoints(Javalin app) {
        app.get("/", ctx -> ctx.result("Hello World from javalin"));
        app.get("/secure", ctx -> ctx.result("Secured page"), roles(UserRole.ROLE_ONE));
    }
}
