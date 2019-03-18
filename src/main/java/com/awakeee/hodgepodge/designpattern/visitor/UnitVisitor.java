package com.awakeee.hodgepodge.designpattern.visitor;

public interface UnitVisitor {

    void visitSoldier(Soldier soldier);

    void visitCommander(Commander commander);//指揮官

    void visitSergeant(Sergeant sergeant);//軍士
}
