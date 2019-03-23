package com.awakeee.hodgepodge.designpattern.flyweight;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HolyWaterPotion implements Potion {

    private static final Logger LOGGER = LoggerFactory.getLogger(HolyWaterPotion.class);

    @Override
    public void drink() {
        System.out.println("You feel blessed. (Potion="+System.identityHashCode(this)+")");
    }
}
