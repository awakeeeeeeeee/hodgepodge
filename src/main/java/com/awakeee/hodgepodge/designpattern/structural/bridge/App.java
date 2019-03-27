package com.awakeee.hodgepodge.designpattern.structural.bridge;

public class App {

    public static void main(String[] args) {

        Weapon hammer = new Hammer(new FlyEnchantment());
        hammer.wield();
        hammer.swing();
        hammer.unwield();

        Weapon sword = new Sword(new SoulEatingEnchantment());
        sword.wield();
        sword.swing();
        sword.unwield();

    }
}
