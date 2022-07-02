package com.awakeee.hodgepodge.testexpand;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @auther dailb
 * @date 2021/6/4 下午2:08
 */
public class SystemA implements InterfaceA , ApplicationContextAware, InitializingBean {
    @Override
    public void say() {
        System.out.println("system a!");
    }

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }


    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
