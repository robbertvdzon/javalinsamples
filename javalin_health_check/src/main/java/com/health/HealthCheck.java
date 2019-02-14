package com.health;

import io.javalin.Handler;
import io.javalin.Javalin;
import org.jetbrains.annotations.NotNull;

public class HealthCheck {

    public void initRestEndpoints(Javalin app) {
        app.get("/health",
                myHealthCheck());
    }

    @NotNull
    private Handler myHealthCheck() {
        // check system here
        return ctx -> ctx.result("OK");
    }
}
