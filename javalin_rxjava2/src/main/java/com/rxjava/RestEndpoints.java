package com.rxjava;

import io.javalin.Javalin;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import java.time.LocalDateTime;
//import java.util.Observable;

public class RestEndpoints {

  ObservableEmitter<String> emitter;
  SimpleEventQueue simpleEventQueue = new SimpleEventQueue();

  private Observable<String> bus = Observable.create(s -> {
    s.onNext("b");
    emitter = s;
  });

  public void initRestEndpoints(Javalin app) {
//        Observable<Object> objectObservable = Observable.create(null);
//        objectObservable.star
    bus.subscribe(s -> processEvent(s));
    bus.startWith("a");
//    emitter.onNext("c");

    simpleEventQueue.subscribe(this::eventListener);

    app.get("/", ctx -> {
      ctx.result("Hello World from javalin");
      String str = "d-" + LocalDateTime.now();
      System.out.println("\n\nemit "+str);
//      emitter.onNext(str);
      simpleEventQueue.addEvent(str);
      System.out.println("emitted "+str);
    });
    app.get("/json", ctx -> ctx.json(new MyJson("Hello World")));
    app.post("/json", ctx -> ctx.result("Got post of " + ctx.bodyAsClass(MyJson.class).getText()));
  }

  private void eventListener(String s) {
    System.out.println("START event listener: "+s);
    try {
      Thread.sleep(2500);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("END event listener : "+s);
  }

  private void processEvent(String s) {
    System.out.println("start process " + s);
    try {
      Thread.sleep(1100);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("return process " + s);
  }
}
