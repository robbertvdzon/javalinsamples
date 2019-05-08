package com.base;

import com.rxjava.JavalinRxjavaApplication;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.core.IsEqual.equalTo;

class JavalinRxJavaApplicationIT {
    private JavalinRxjavaApplication application;

    @BeforeEach
    void before() {
        application = new JavalinRxjavaApplication();
        application.start();
    }

    @AfterEach
    void teardown() {
        application.stop();
    }

    @Test
    @DisplayName("Test basic get")
    void testHelloWorld() throws InterruptedException {
        when()
                .get("/")
                .then()
                .statusCode(200)
                .body(equalTo("Hello World from javalin"));
        Thread.sleep(1000);
        when()
            .get("/")
            .then()
            .statusCode(200)
            .body(equalTo("Hello World from javalin"));
        Thread.sleep(1000);
        when()
            .get("/")
            .then()
            .statusCode(200)
            .body(equalTo("Hello World from javalin"));
        Thread.sleep(20000);

    }

}
