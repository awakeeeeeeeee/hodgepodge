package com.awakeee.hodgepodge.designpattern.ceational.abstractfactory;

public class WriteImpl implements Operate {
    @Override
    public void operate() {
        System.out.println("写");
    }
}
