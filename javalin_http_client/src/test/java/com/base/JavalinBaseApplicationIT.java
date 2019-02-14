package com.base;

import com.httpclient.HttpClientApplication;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.core.IsEqual.equalTo;

class JavalinBaseApplicationIT {
    private HttpClientApplication application;

    @BeforeEach
    void before() {
        application = new HttpClientApplication();
        application.start();
    }

    @AfterEach
    void teardown() {
        application.stop();
    }


    @Test
    @DisplayName("Test circuitbreaker, retry and fallback ")
    void testCircuitBreakerRetryAndFallback() throws InterruptedException {

        // warm up the system by calling a dummy page
        when().get("/");

        // initial, the call is succesfull
        when().get("/external").then().statusCode(200).body(equalTo("from external server"));

        // enable a delay
        when().get("/enabledelay");

        IntStream.range(0,10).forEach(i->{
            when().get("/external").then().statusCode(200).body(equalTo("from fallback"));
        });

        // disable a delay
        when().get("/disabledelay");

        // call /external is still failing, because circuit is open
        when().get("/external").then().statusCode(200).body(equalTo("from fallback"));

        // after one second, the circuit should be half open, so the next call should work
        Thread.sleep(1500);
        when().get("/external").then().statusCode(200).body(equalTo("from external server"));

        // when calling more the circuit should be closed again
        IntStream.range(0,14).forEach(i->{
            when().get("/external").then().statusCode(200).body(equalTo("from external server"));
        });


    }

    private void sleep(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}