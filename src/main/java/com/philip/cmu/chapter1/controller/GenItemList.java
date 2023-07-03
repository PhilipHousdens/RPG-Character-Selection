package com.philip.cmu.chapter1.controller;

import com.philip.cmu.chapter1.model.DamageType;
import com.philip.cmu.chapter1.model.item.Armor;
import com.philip.cmu.chapter1.model.item.BasedEqiupment;
import com.philip.cmu.chapter1.model.item.Weapon;

import java.util.ArrayList;

public class GenItemList {

    public static ArrayList<BasedEqiupment> setUpItemList() {
        ArrayList<BasedEqiupment> itemLists = new ArrayList<BasedEqiupment>(6);
        itemLists.add(new Weapon("Sword", 10, DamageType.physical, "assets/sword.png"));
        itemLists.add(new  Weapon("Gun", 20, DamageType.physical, "assets/gun.png"));
        itemLists.add(new Weapon("Staff", 30, DamageType.magical, "assets/staff.png"));
        itemLists.add(new Armor("Shirt", 0, 50, "assets/shirt.png"));
        itemLists.add(new Armor("Armor", 50, 0, "assets/armor.png"));
        itemLists.add(new Weapon("Wand", 15,DamageType.magical, "assets/wand.png"));
        return itemLists;
    }
}
