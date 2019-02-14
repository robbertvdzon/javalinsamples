package com.demobasicauth;

import com.demobasicauth.authentication.BasicAuthManager;
import com.demobasicauth.usermanagement.UserManagement;
import io.javalin.BasicAuthCredentials;
import io.javalin.Javalin;

public class JavalinBasicAuthApplication {
    public static void main(String[] args) {
        UserManagement userManagement = new UserManagement();
        BasicAuthManager basicAuthManager = new BasicAuthManager(userManagement);

        Javalin app = Javalin.create();
        new RestEndpoints().initRestEndpoints(app);
        basicAuthManager.init(app);
        app.start(8080);
    }
}
