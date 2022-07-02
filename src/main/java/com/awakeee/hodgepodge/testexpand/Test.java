package com.awakeee.hodgepodge.testexpand;

import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.support.XmlWebApplicationContext;
import sun.jvm.hotspot.debugger.ThreadContext;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @auther dailb
 * @date 2021/6/4 下午2:08
 */
public class Test {

    public static void main(String[] args) {

        Test t = new Test();
        System.out.println(t.getClass().getClassLoader());

        ClassLoader classLoader  = Thread.currentThread().getContextClassLoader();
        try {
            Class<?> cls = classLoader.loadClass("com.awakeee.hodgepodge.testexpand.InterfaceA");
            System.out.println(cls.getClass());
            boolean flag = cls.isAssignableFrom(SystemA.class);
            System.out.println(flag);


//            Object o = cls.newInstance();
//            System.out.println(o);

            ServiceLoader<?> load = ServiceLoader.load(cls);
            Iterator<?> iterator = load.iterator();
            while (iterator.hasNext()){
                Object next = iterator.next();
                System.out.println(next);
            }




        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
