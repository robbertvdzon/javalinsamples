package com.demobasicauth.authentication;

import com.demobasicauth.domain.User;
import com.demobasicauth.domain.UserRole;
import com.demobasicauth.usermanagement.UserManagement;
import io.javalin.BasicAuthCredentials;
import io.javalin.Context;
import io.javalin.Javalin;

public class BasicAuthManager {
    private UserManagement userManagement;

    public BasicAuthManager(UserManagement userManagement){
        this.userManagement = userManagement;
    }

    public void init(Javalin app){
        // Set the access-manager that Javalin should use
        app.accessManager((handler, ctx, permittedRoles) -> {
            UserRole userRole = getUserRole(ctx);
            if (permittedRoles.isEmpty() || permittedRoles.contains(userRole)) {
                handler.handle(ctx);
            } else {
                ctx.header("WWW-Authenticate","Basic realm=\"User Visible Realm\"");
                ctx.status(401).result("Unauthorized");
            }
        });
    }

    UserRole getUserRole(Context ctx) {
        BasicAuthCredentials basicAuthCredentials = ctx.basicAuthCredentials();
        return userManagement
                .getUsers()
                .stream()
                .filter(u->authenticateUser(u, basicAuthCredentials))
                .map(u->u.getRole())
                .findFirst()
                .orElseGet(()-> UserRole.NONE);
    }

    private boolean authenticateUser(User u, BasicAuthCredentials basicAuthCredentials) {
        if (basicAuthCredentials==null) return false;
        return (u.getUsername().equals(basicAuthCredentials.getUsername()) && u.getPassword().equals(basicAuthCredentials.getPassword()));

    }

}
