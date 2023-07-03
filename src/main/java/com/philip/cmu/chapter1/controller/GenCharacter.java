package com.philip.cmu.chapter1.controller;

import com.philip.cmu.chapter1.model.character.BasedCharacter;
import com.philip.cmu.chapter1.model.character.BattleMageCharacter;
import com.philip.cmu.chapter1.model.character.MagicalCharacter;
import com.philip.cmu.chapter1.model.character.PhysicalCharacter;

import java.util.Random;

public class GenCharacter {
    public static BasedCharacter setUpCharacter() {
        BasedCharacter character;
        Random rand = new Random();
        int type = rand.nextInt(3)+1;
        int baseDef = rand.nextInt(50)+1;
        int baseRes = rand.nextInt(50)+1;
        if (type == 1) {
            character = new MagicalCharacter("MagicalChar1", "assets/wizard.png", baseDef, baseRes);
        } else {
            character = new PhysicalCharacter("PhysicalChar1", "assets/knight.png", baseRes, baseRes);
        }
        if  (type == 3) {
            character = new BattleMageCharacter("BattleMage1", "assets/BattleMage.png",baseRes, baseRes);
        }
        return character;
    }
}
