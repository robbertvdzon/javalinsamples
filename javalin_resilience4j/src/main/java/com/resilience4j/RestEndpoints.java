package com.resilience4j;

import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.retry.RetryRegistry;
import io.javalin.Javalin;
import io.vavr.control.Try;
import java.io.IOException;
import java.time.Duration;
import java.util.Random;
import java.util.function.Supplier;
import org.apache.http.client.fluent.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RestEndpoints {

  Logger log = LoggerFactory.getLogger(RestEndpoints.class);
  Random random = new Random(System.currentTimeMillis());

  RetryConfig config = RetryConfig
      .custom()
      .waitDuration(Duration.ofMillis(10l))
      .maxAttempts(10)
      .build();
  RetryRegistry registry = RetryRegistry.of(config);
  Retry retry = registry.retry("my");


  public void initRestEndpoints(Javalin app) {

    app.get("/", ctx -> ctx.result(callUnstableWithRetry()));
    app.get("/unstable", ctx -> ctx.result(getUnstable()));
  }

  private String callUnstableWithRetry() {
    Supplier<String> decoratedSupplier = Retry
        .decorateSupplier(retry, this::callUnstable);

    return Try.ofSupplier(decoratedSupplier)
        .recover(throwable -> "Hello from Recovery").get();
  }

  private String callUnstable() {
    try {
      return Request
          .Get("http://localhost:8080/unstable")
          .connectTimeout(10)
          .socketTimeout(10)
          .execute()
          .returnContent()
          .asString();
    } catch (IOException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }

  }

  private String getUnstable() {
    int random10 = random.nextInt(10);
    boolean failing = random10 < 8;
    if (failing) {
      throw new RuntimeException("Failing");
    }
    return "Succeeded";


  }


}
