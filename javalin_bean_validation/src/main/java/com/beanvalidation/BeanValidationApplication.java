package com.beanvalidation;

import com.beanvalidation.validation.BeanValidator;
import io.javalin.Javalin;

public class BeanValidationApplication {
    Javalin app;

    public static void main(String[] args) {
        new BeanValidationApplication().start();
    }

    public void start(){
        app = Javalin.create();
        BeanValidator beanValidator = new BeanValidator(app);
        RestEndpoints restEndpoints = new RestEndpoints(beanValidator);

        beanValidator.initValidation();
        restEndpoints.initRestEndpoints(app);
        app.start(8080);
    }

    public void stop(){
        app.stop();
    }
}
