package com.awakeee.hodgepodge.designpattern.bridge;

public class Sword implements Weapon {

    private Enchantment enchantment;

    public Sword(Enchantment enchantment) {
        this.enchantment = enchantment;
    }

    @Override
    public void wield() {
        System.out.println("wield sword");
        enchantment.onActivate();
    }

    @Override
    public void swing() {
        System.out.println("swing sword");
        enchantment.apply();
    }

    @Override
    public void unwield() {
        System.out.println("unwield sword");
        enchantment.onDeactivate();
    }
}
