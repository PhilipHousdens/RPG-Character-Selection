package com.philip.cmu.chapter1;

import com.philip.cmu.chapter1.controller.GenCharacter;
import com.philip.cmu.chapter1.controller.GenItemList;
import com.philip.cmu.chapter1.model.character.BasedCharacter;
import com.philip.cmu.chapter1.model.item.Armor;
import com.philip.cmu.chapter1.model.item.BasedEqiupment;
import com.philip.cmu.chapter1.model.item.Weapon;
import com.philip.cmu.chapter1.view.CharacterPane;
import com.philip.cmu.chapter1.view.EquipPane;
import com.philip.cmu.chapter1.view.InventoryPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Launcher extends Application {

    private static Scene mainScene;
    private static BasedCharacter mainCharacter = null;
    private static ArrayList<BasedEqiupment> allEquipments = null;
    private static Weapon equippedWeapon = null;
    private static Armor equippedArmor = null;
    private static Weapon unequippedWeapon = null;
    private static Armor unequippedArmor = null;
    private static CharacterPane characterPane = null;


    private static EquipPane equipPane = null;


    private static InventoryPane inventoryPane = null;

    public static Scene getMainScene() {
        return mainScene;
    }

    public static ArrayList<BasedEqiupment> getAllEquipments() {
        return allEquipments;
    }

    public static Weapon getEquippedWeapon() {
        return equippedWeapon;
    }

    public static Armor getEquippedArmor() {
        return equippedArmor;
    }

    public static CharacterPane getCharacterPane() {
        return characterPane;
    }

    public static void setMainScene(Scene mainScene) {
        Launcher.mainScene = mainScene;
    }

    public static EquipPane getEquipPane() {
        return equipPane;
    }

    public static InventoryPane getInventoryPane() {
        return inventoryPane;
    }
    public static void setInventoryPane(InventoryPane inventoryPane) {
        Launcher.inventoryPane = inventoryPane;
    }


    public static void setEquipPane(EquipPane equipPane) {
        Launcher.equipPane = equipPane;
    }

    public static void setCharacterPane(CharacterPane characterPane) {
        Launcher.characterPane = characterPane;
    }

    public static void setAllEquipments(ArrayList<BasedEqiupment> allEquipments) {
        Launcher.allEquipments = allEquipments;
    }

    public static void setEquippedWeapon(Weapon equippedWeapon) {
        Launcher.equippedWeapon = equippedWeapon;
    }

    public static void setEquippedArmor(Armor equippedArmor) {
        Launcher.equippedArmor = equippedArmor;
    }

    public static void setUnequippedWeapon(Weapon unequippedWeapon) {Launcher.unequippedWeapon = unequippedWeapon;}
    public static void setUnequippedArmor(Armor unequippedArmor) {Launcher.unequippedArmor = unequippedArmor;}

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Intro to RPG");
        primaryStage.setResizable(false);
        primaryStage.show();
        mainCharacter = GenCharacter.setUpCharacter();
        allEquipments = GenItemList.setUpItemList();
        Pane mainPane;
        mainPane = getMainPane();
        mainScene = new Scene(mainPane);
        primaryStage.setScene(mainScene);
    }

    public Pane getMainPane() {
        BorderPane mainPane = new BorderPane();
        characterPane = new CharacterPane();
        equipPane = new EquipPane();
        inventoryPane = new InventoryPane();
        refreshPane();
        mainPane.setCenter(characterPane);
        mainPane.setLeft(equipPane);
        mainPane.setBottom(inventoryPane);
        return mainPane;
    }

    public static void refreshPane() {
        characterPane.drawPane(mainCharacter);
        equipPane.drawPane(equippedWeapon, equippedArmor);
        inventoryPane.drawPane(allEquipments);
    }

    public static BasedCharacter getMainCharacter() {
        return mainCharacter;
    }

    public static void setMainCharacter(BasedCharacter mainCharacter) {
        Launcher.mainCharacter = mainCharacter;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
