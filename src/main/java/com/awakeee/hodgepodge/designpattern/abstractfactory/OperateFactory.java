package com.awakeee.hodgepodge.designpattern.abstractfactory;

public class OperateFactory extends AbsractFactory {

    @Override
    Operate careateOperate(OperateType operateType) {

        switch (operateType){
            case read:
                return new ReadImpl();
            case write:
                return new WriteImpl();
            case listen:
                return new ListenImpl();
                default:throw new IllegalArgumentException("operatetype not supported.");
        }


    }

    public static void main(String[] args) {

        AbsractFactory absractFactory = new OperateFactory();
        Operate operate = absractFactory.careateOperate(OperateType.listen);
        operate.operate();

    }
}
