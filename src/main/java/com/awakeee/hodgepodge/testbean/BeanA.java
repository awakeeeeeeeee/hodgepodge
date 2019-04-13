package com.awakeee.hodgepodge.testbean;

public class BeanA {

    private BeanB beanB;

    public void println(){
        beanB.println();
    }


    public void setBeanB(BeanB beanB) {
        this.beanB = beanB;
    }
}
