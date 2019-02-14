package com.base;

import com.cdc.JavalinBaseApplication;
import org.junit.After;
import org.junit.Before;

public class BaseTestClass {
    private JavalinBaseApplication application;

    @Before
    public void before(){
        application = new JavalinBaseApplication();
        application.start();
    }

    @After
    public void after(){
        application.stop();
    }
}
