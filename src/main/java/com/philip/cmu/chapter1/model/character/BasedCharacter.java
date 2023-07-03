package com.philip.cmu.chapter1.model.character;

import com.philip.cmu.chapter1.model.DamageType;
import com.philip.cmu.chapter1.model.item.Armor;
import com.philip.cmu.chapter1.model.item.Weapon;

public class BasedCharacter {
    protected String name, imgpath;
    protected DamageType type;
    protected Integer fullHp, basedPow, basedDef, basedRes;
    protected Integer hp,power,defense,resistance;
    protected Weapon weapon;
    protected Armor armor;

    public void equipWeapon (Weapon weapon) {
        this.weapon = weapon;
        this.power = this.basedPow + weapon.getPower();
    }

    public  void  equipArmor(Armor armor) {
        this.armor = armor;
        this.defense = this.basedDef + armor.getDefense();
        this.resistance = this.basedRes + armor.getResistance();
    }
    public String getName() {return name;}

    public String getImgpath() {
        return imgpath;
    }

    public Integer getHp() {
        return hp;
    }

    public Integer getFullHp() {
        return fullHp;
    }

    public Integer getPower() {
        return power;
    }

    public Integer getDefense() {
        return defense;
    }

    public Integer getResistance() {
        return resistance;
    }

    @Override
    public String toString() {
        return name;
    }
    public DamageType getType() {
        return type;
    }
}
