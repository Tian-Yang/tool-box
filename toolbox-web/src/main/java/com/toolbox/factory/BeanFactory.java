package com.toolbox.factory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BeanFactory {

    private static Properties env = new Properties();

    static {
        //获取输入流
        InputStream in = BeanFactory.class.getResourceAsStream("/ApplicationContext.properties");
        //读取文件内容封装在Properties中
        try {
            env.load(in);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取Bean
     *
     * @param beanName
     * @return
     */
    public static Object getBean(String beanName) {
        Class cls = null;
        Object obj = null;
        try {
            cls = Class.forName(env.getProperty(beanName));
            obj = cls.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
