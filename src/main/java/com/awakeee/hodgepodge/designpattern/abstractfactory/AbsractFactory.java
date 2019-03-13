package com.awakeee.hodgepodge.designpattern.abstractfactory;

public abstract class AbsractFactory {

    abstract Operate careateOperate(OperateType operateType);


    public enum OperateType{
        write,listen,read
    }
}
