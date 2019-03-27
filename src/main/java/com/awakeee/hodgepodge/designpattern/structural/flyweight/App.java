package com.awakeee.hodgepodge.designpattern.structural.flyweight;

public class App {

    public static void main(String[] args) {

        Potion potion = PotionFactory.creatPotion(PotionType.HealingPotion);
        Potion potion1 = PotionFactory.creatPotion(PotionType.HealingPotion);

        Potion potion2 = PotionFactory.creatPotion(PotionType.HolyWaterPotion);
        Potion potion3 = PotionFactory.creatPotion(PotionType.HolyWaterPotion);

        potion.drink();
        potion1.drink();
        potion2.drink();
        potion3.drink();


        System.out.println(PotionType.HealingPotion.getCode());
        System.out.println(PotionType.HolyWaterPotion.getCode());
    }
}
