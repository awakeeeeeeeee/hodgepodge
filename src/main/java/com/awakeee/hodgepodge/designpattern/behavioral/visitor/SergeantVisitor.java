package com.awakeee.hodgepodge.designpattern.behavioral.visitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SergeantVisitor implements UnitVisitor {

    private static final Logger LOGGER = LoggerFactory.getLogger(SergeantVisitor.class);


    @Override
    public void visitSoldier(Soldier soldier) {

    }

    @Override
    public void visitCommander(Commander commander) {

    }

    @Override
    public void visitSergeant(Sergeant sergeant) {
        LOGGER.info("Hello {}", sergeant);
    }
}
