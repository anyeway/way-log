package com.way.log;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by way on 2016/9/20.
 */
public class Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);


    public static void main(String[] args) {
        LOGGER.error(" 中文顶顶顶 ");
    }
}
