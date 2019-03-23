package com.awakeee.hodgepodge.designpattern.flyweight;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HealingPotion implements Potion {

    private static final Logger LOGGER = LoggerFactory.getLogger(HealingPotion.class);


    @Override
    public void drink() {
        System.out.println("You feel healed. (Potion="+System.identityHashCode(this)+")");
    }
}
