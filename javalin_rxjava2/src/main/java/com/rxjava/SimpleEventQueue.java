package com.rxjava;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedTransferQueue;
import java.util.function.Consumer;

public class SimpleEventQueue {

  private Object lock = new Object();
  private Queue<String> events = new LinkedTransferQueue<String>();
  private List<Consumer<String>> consumers = new ArrayList<>();

  public SimpleEventQueue() {
    startExecuter();
  }

  public void addEvent(String event) {
    events.offer(event);
    System.out.println("Notify");
    synchronized (lock) {
      lock.notifyAll();
    }
  }

  public void subscribe(Consumer<String> consumer) {
    consumers.add(consumer);
  }

  private void startExecuter() {
    Executors.newSingleThreadExecutor().submit(this::executerLoop);
  }

  private void executerLoop() {
    while (true) {
      if (events.isEmpty()) {
        waitAWhile();
      }
//      System.out.println("Loop");
      String event = events.poll();
      if (event!=null){
        consumers.forEach(c->c.accept(event));
      }
    }
  }

  private void waitAWhile() {
    try {
      synchronized (lock) {
//      System.out.println("START WAIT");
//      Thread.sleep(40000);
        lock.wait();
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
//    System.out.println("WOKEUP");
  }

}
