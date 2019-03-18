package com.awakeee.hodgepodge.designpattern.visitor;

public class Sergeant extends Unit{

    public Sergeant(Unit... children){
        super(children);
    }

    @Override
    void accept(UnitVisitor unitVisitor) {
        unitVisitor.visitSergeant(this);
        super.accept(unitVisitor);
    }
}
