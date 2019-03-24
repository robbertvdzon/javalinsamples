package com.prometheus2;

import io.javalin.Context;
import io.javalin.Javalin;
import io.prometheus.client.Counter;
import io.prometheus.client.Summary;
import io.prometheus.client.Summary.Timer;
import java.util.Random;
import org.jetbrains.annotations.NotNull;

public class RestEndpoints {

    static final Counter totalRequests = Counter.build()
        .name("requests_total").help("Total requests.").register();
    static final Summary callSummary = Summary.build()
        .name("call_summary").help("Duration of my rest call.").register();
    Random random = new Random(System.currentTimeMillis());


    public void initRestEndpoints(Javalin app) {
        app.get("/", ctx -> getHello(ctx));
        }

    @NotNull
    private Context getHello(Context ctx) {
        totalRequests.inc();
        Timer timer = callSummary.startTimer();
        int slept = sleepRandomTime();
        String hello = "Waited "+slept+" msec";
        timer.observeDuration();
        return ctx.result(hello);
    }

    private int sleepRandomTime() {
        int sleepTime = random.nextInt(30);
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return sleepTime;
    }
}
