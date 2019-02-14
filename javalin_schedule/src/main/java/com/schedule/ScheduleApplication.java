package com.schedule;

import io.javalin.Javalin;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class ScheduleApplication {
    Javalin app;

    public static void main(String[] args) throws SchedulerException {
        new ScheduleApplication().start();
    }

    public void start() throws SchedulerException {
        app = Javalin.create();
        new RestEndpoints().initRestEndpoints(app);
        app.start(8080);

        startSchedular();
    }

    private void startSchedular() throws SchedulerException {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.start();

        JobDetail job = JobBuilder.newJob(MyScheduledJob.class)
                .withIdentity("myJob", "group1")
                .build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("myTrigger", "group1")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(4)
                        .repeatForever())
                .build();

        JobDetail cronJob = JobBuilder.newJob(MyScheduledJob.class)
                .withIdentity("myCronJob", "group1")
                .build();


        CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger3", "group1")
                .withSchedule(CronScheduleBuilder.cronSchedule("0 0/2 8-17 * * ?"))
                .forJob("myCronJob", "group1")
                .build();


        scheduler.scheduleJob(job, trigger);
        scheduler.scheduleJob(cronJob, cronTrigger);
    }


    public void stop(){
        app.stop();
    }
}
