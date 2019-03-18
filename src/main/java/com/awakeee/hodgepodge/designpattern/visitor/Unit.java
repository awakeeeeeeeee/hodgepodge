package com.awakeee.hodgepodge.designpattern.visitor;

public abstract class Unit {

    private Unit[] children;

    public Unit(Unit... children){
        this.children = children;
    }

    void accept(UnitVisitor unitVisitor){
        for(Unit unit : children){
            unit.accept(unitVisitor);
        }
    }
}

