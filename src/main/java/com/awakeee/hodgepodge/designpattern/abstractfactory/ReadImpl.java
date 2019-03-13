package com.awakeee.hodgepodge.designpattern.abstractfactory;

public class ReadImpl implements Operate {
    @Override
    public void operate() {
        System.out.println("读");
    }

}
