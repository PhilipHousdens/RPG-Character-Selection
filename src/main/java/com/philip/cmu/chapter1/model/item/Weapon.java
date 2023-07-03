package com.philip.cmu.chapter1.model.item;

import com.philip.cmu.chapter1.model.DamageType;

public class Weapon extends BasedEqiupment{
    private int power;
    private DamageType damageType;
    public Weapon(String name, int power, DamageType damageType, String imgpath){
        this.name = name;
        this.power = power;
        this.imgpath = imgpath;
        this.damageType = damageType;
    }

    public Weapon() {

    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public void setDamageType(DamageType damageType) {
        this.damageType = damageType;
    }

    public DamageType getDamageType() {
        return damageType;
    }

    @Override
    public String toString() {
        return name;
    }
}
