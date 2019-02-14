package com.httpclient;

import com.httpclient.resilience.FailSafeBuilder;
import io.javalin.Javalin;
import net.jodah.failsafe.CircuitBreaker;
import net.jodah.failsafe.Failsafe;
import net.jodah.failsafe.Fallback;
import net.jodah.failsafe.RetryPolicy;
import org.apache.http.client.fluent.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class RestEndpoints {
    Logger log = LoggerFactory.getLogger(RestEndpoints.class);

    private RetryPolicy<Object> retryPolicy;
    private CircuitBreaker<Object> circuitBreakerPolicy;
    private Fallback<Object> fallbackPolicy;

    // for demo and debugging purposes:
    private int delay = 0;


    public void initRestEndpoints(Javalin app) {
        retryPolicy = FailSafeBuilder.getRetryPolicy();
        circuitBreakerPolicy = FailSafeBuilder.getCircuitBreaker();
        fallbackPolicy = FailSafeBuilder.getFallback(this::getFallbackResult);

        app.get("/", ctx -> ctx.result("Hello World from javalin"));
        app.get("/json", ctx -> ctx.json(new MyJson("Hello World")));
        app.post("/json", ctx -> ctx.result("Got post of " + ctx.bodyAsClass(MyJson.class).getText()));
        app.get("/external", ctx -> ctx.result(getFromExternalWithRetryAndCircuitBreaker()));
        app.get("/delayedresponse", ctx -> ctx.result(getDelayedResponse()));
        app.get("/disabledelay", ctx -> ctx.result(setDelay(0)));
        app.get("/enabledelay", ctx -> ctx.result(setDelay(1000)));
    }

    private String getFromExternalWithRetryAndCircuitBreaker() {
        return Failsafe.with(fallbackPolicy, retryPolicy, circuitBreakerPolicy).get(this::callExternalSystem);
    }

    private String callExternalSystem() throws IOException {
        log.info("----> try to call external system");
        String result = Request
                .Get("http://localhost:8080/delayedresponse")
                .connectTimeout(10)
                .socketTimeout(10)
                .execute()
                .returnContent()
                .asString();
        log.info("----> call to external succeeded");
        return result;
    }

    private String setDelay(int i) {
        delay = i;
        log.info("----> set delay to "+i);
        return "ok";
    }

    private String getDelayedResponse() {
        sleep(delay);
        return "from external server";
    }

    private String getFallbackResult() {
        log.info("----> return from fallback");
        return "from fallback";
    }

    private void sleep(long delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
