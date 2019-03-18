package com.awakeee.hodgepodge.designpattern.visitor;

public class Soldier extends Unit {

    public Soldier(Unit... children){
        super(children);
    }

    @Override
    void accept(UnitVisitor unitVisitor) {
        unitVisitor.visitSoldier(this);
        super.accept(unitVisitor);
    }


    public static void main(String[] args) {
        Commander commander =
                new Commander(new Sergeant(new Soldier(), new Soldier(), new Soldier()), new Sergeant(
                        new Soldier(), new Soldier(), new Soldier()));
        commander.accept(new SoldierVisitor());
        commander.accept(new SergeantVisitor());
        commander.accept(new CommanderVisitor());

    }
}
