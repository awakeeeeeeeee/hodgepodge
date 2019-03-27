package com.awakeee.hodgepodge.designpattern.structural.bridge;

public class SoulEatingEnchantment implements Enchantment {
    @Override
    public void onActivate() {
        System.out.println("The item spreads bloodust");
    }

    @Override
    public void apply() {
        System.out.println("The item eats the soul of enemies");
    }

    @Override
    public void onDeactivate() {
        System.out.println("Bloodust slowly disappears");
    }
}
