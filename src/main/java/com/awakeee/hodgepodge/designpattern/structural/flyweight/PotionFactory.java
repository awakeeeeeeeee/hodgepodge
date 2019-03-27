package com.awakeee.hodgepodge.designpattern.structural.flyweight;

import java.util.HashMap;
import java.util.Map;

public class PotionFactory {

    private static Map<PotionType,Potion> cache = new HashMap<PotionType,Potion>();

    public static Potion creatPotion(PotionType potionType){

        switch (potionType){
            case HealingPotion:
                if(cache.get(potionType) == null){
                    HealingPotion healingPotion = new HealingPotion();
                    cache.put(potionType,healingPotion);
                    return healingPotion;
                }
                return cache.get(potionType);
            case HolyWaterPotion:
                if(cache.get(potionType) == null){
                    HolyWaterPotion holyWaterPotion = new HolyWaterPotion();
                    cache.put(potionType,holyWaterPotion);
                    return holyWaterPotion;
                }
                return cache.get(potionType);
                default:throw new IllegalArgumentException("no mached potion");
        }
    }
}
