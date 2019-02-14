package com.demohttps;

import com.demohttps.ssl.SslManager;
import io.javalin.Javalin;

public class JavalinHttpsApplication {
    public static void main(String[] args) {
        Javalin app = Javalin.create();
        new RestEndpoints().initRestEndpoints(app);
        new SslManager().init(app);
        app.start(8080);
    }
}
