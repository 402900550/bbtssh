package com.hfkj.bbt.base.util;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

/**
 * Created by Administrator on 2017-07-05.
 */
public class SpringUtil {
    private static ApplicationContext applicationContext;

    public static <T> T getBean(Class<T> clazz, String beanName) {
        ApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
        if (context == null) {
            context = applicationContext;
        }
        return (T) context.getBean(beanName);
    }

    public static void setApplicationContext(ApplicationContext applicationContext) {
        SpringUtil.applicationContext = applicationContext;
    }
}
