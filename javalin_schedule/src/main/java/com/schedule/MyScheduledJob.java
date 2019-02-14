package com.schedule;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class MyScheduledJob  implements Job {
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        System.out.println("This is a quartz job!");
    }
}