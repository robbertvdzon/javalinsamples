package com.httpclient.resilience;

import net.jodah.failsafe.CircuitBreaker;
import net.jodah.failsafe.Fallback;
import net.jodah.failsafe.RetryPolicy;
import net.jodah.failsafe.function.CheckedRunnable;
import net.jodah.failsafe.function.CheckedSupplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.Duration;

public class FailSafeBuilder {
    private static Logger log = LoggerFactory.getLogger(FailSafeBuilder.class);

    public static CircuitBreaker<Object> getCircuitBreaker() {
        return new CircuitBreaker<>()
                .handle(IOException.class)
                .withFailureThreshold(10, 12)
                .withSuccessThreshold(3, 5)
                .withDelay(Duration.ofSeconds(1)) // only this small for demo purposes!
                .onClose(() -> log.info("=======> Circuit Closed"))
                .onOpen(() -> log.warn("=======> Circuit Opened, use fallback for all the call's in the next second"))
                .onHalfOpen(() -> log.info("\"=======> Circuit Half-Open, try the endpoint again"));
    }

    public static  RetryPolicy<Object> getRetryPolicy() {
        return new RetryPolicy<>()
                .handle(IOException.class)
                .withDelay(Duration.ofMillis(1)) // only this small for demo purposes!
                .withMaxRetries(2)
                .onRetry((ctx)->log.info("  ----->Retry"))
                .onRetriesExceeded((ctx)->log.info("  ----->Retries exceeded, use fallback"));



    }

    public static  Fallback<Object> getFallback(CheckedSupplier fallback) {
        return Fallback.of(fallback);
    }


}
