package com.awakeee.hodgepodge.testbean;

import java.lang.reflect.Field;

public class BeanCreate {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        ClassLoader classLoader  = Thread.currentThread().getContextClassLoader();
        Class<?> cls = classLoader.loadClass("com.awakeee.hodgepodge.testbean.BeanB");
        BeanB beanB = (BeanB) cls.newInstance();
        System.out.println(beanB);

        Class clazz = new BeanA().getClass();
        Field[] fileds = clazz.getDeclaredFields();
        for(Field field : fileds){
            if(field.getName().equalsIgnoreCase("beanb")){

            }
        }
    }
}
