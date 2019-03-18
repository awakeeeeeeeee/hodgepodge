package com.awakeee.hodgepodge.designpattern.visitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SoldierVisitor implements UnitVisitor{

    private static final Logger LOGGER = LoggerFactory.getLogger(SergeantVisitor.class);


    @Override
    public void visitSoldier(Soldier soldier) {
        LOGGER.info("Hello {}", soldier);
    }

    @Override
    public void visitCommander(Commander commander) {

    }

    @Override
    public void visitSergeant(Sergeant sergeant) {

    }
}
