package com.prometheus2;

import io.javalin.Javalin;
import io.prometheus.client.exporter.HTTPServer;
import io.prometheus.client.hotspot.DefaultExports;

public class RunnableJarApplication {

  Javalin app;

  public static void main(String[] args) {
    new RunnableJarApplication().start();
  }

  public void start() {

    app = Javalin.create();
    new RestEndpoints().initRestEndpoints(app);
    app.start(8080);

    try {
      new HTTPServer(1234);
      DefaultExports.initialize();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void stop() {
    app.stop();
  }
}
