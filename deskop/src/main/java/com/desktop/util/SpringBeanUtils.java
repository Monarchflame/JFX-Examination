package com.desktop.util;

import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author qxt
 * @date 2020/12/5 22:15
 */
public class SpringBeanUtils {
    public static ConfigurableApplicationContext applicationContext;

    public static void setApplicationContext(ConfigurableApplicationContext applicationContext) {
        SpringBeanUtils.applicationContext = applicationContext;
    }
}
