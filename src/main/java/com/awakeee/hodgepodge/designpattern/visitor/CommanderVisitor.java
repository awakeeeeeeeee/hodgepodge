package com.awakeee.hodgepodge.designpattern.visitor;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommanderVisitor implements UnitVisitor{

    private static final Logger LOGGER = LoggerFactory.getLogger(CommanderVisitor.class);


    @Override
    public void visitSoldier(Soldier soldier) {

    }

    @Override
    public void visitCommander(Commander commander) {
        LOGGER.info("Hello {}", commander);
    }

    @Override
    public void visitSergeant(Sergeant sergeant) {

    }
}
