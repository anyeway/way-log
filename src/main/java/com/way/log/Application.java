package com.way.log;


import org.apache.logging.log4j.ThreadContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by way on 2016/9/20.
 */
public class Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);


    public static void main(String[] args) {
        ThreadContext.put("userId","userId=111");
        LOGGER.error(" 中文顶顶顶 ");
    }
}
