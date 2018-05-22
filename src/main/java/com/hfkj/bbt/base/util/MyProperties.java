package com.hfkj.bbt.base.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Administrator on 2017-09-05.
 */
public class MyProperties {

    public Properties getProperties()throws IOException {
        Properties properties = new Properties();
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("/config.properties");
        properties.load(inputStream);
        inputStream.close();
        return properties;
    }


}
