package com.awakeee.hodgepodge.designpattern.flyweight;

public enum PotionType {

    HealingPotion("HealingPotion"),HolyWaterPotion("HolyWaterPotion");

    private String code;

    PotionType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
