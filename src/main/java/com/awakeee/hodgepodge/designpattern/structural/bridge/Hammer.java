package com.awakeee.hodgepodge.designpattern.structural.bridge;

public class Hammer implements Weapon {

    private Enchantment enchantment;

    public Hammer(Enchantment enchantment) {
        this.enchantment = enchantment;
    }

    @Override
    public void wield() {
        System.out.println("wield hammer");
        enchantment.onActivate();
    }

    @Override
    public void swing() {
        System.out.println("swing hammer");
        enchantment.apply();
    }

    @Override
    public void unwield() {
        System.out.println("unwield hammer");
        enchantment.onDeactivate();
    }
}
