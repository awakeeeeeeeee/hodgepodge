package com.awakeee.hodgepodge.designpattern.behavioral.visitor;

public class Commander extends Unit {

    public Commander(Unit... children){
        super(children);
    }

    @Override
    void accept(UnitVisitor unitVisitor) {
        unitVisitor.visitCommander(this);
        super.accept(unitVisitor);
    }
}
