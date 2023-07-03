package com.philip.cmu.chapter1.view;

import com.philip.cmu.chapter1.Launcher;
import com.philip.cmu.chapter1.model.item.Armor;
import com.philip.cmu.chapter1.model.item.BasedEqiupment;
import com.philip.cmu.chapter1.model.item.Weapon;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;


import static com.philip.cmu.chapter1.controller.AllCustomHandler.*;


public class EquipPane extends ScrollPane {
    public static Weapon getEquippedWeapon() {
        return equippedWeapon;
    }

    public static void setEquippedWeapon(Weapon equippedWeapon) {
        EquipPane.equippedWeapon = equippedWeapon;
    }

    public static Armor getEquippedArmor() {
        return equippedArmor;
    }

    public static void setEquippedArmor(Armor equippedArmor) {
        EquipPane.equippedArmor = equippedArmor;
    }

    private static Weapon equippedWeapon;
    private static Armor equippedArmor;

    private ArrayList<BasedEqiupment> eqiupmentArray;


    public EquipPane() {
    }

    private VBox getDetailsPane() {
        VBox equipmentInfoPane = new VBox(10);
        equipmentInfoPane.setBorder(null);
        equipmentInfoPane.setAlignment(Pos.CENTER);
        equipmentInfoPane.setPadding(new Insets(25, 25, 25, 25));

        Label weaponLbl, armorLbl;
        StackPane weaponImgGroup = new StackPane();
        StackPane armorImgGroup = new StackPane();
        ImageView bg1 = new ImageView();
        ImageView bg2 = new ImageView();
        ImageView weaponImg = new ImageView();
        ImageView armorImg = new ImageView();

        bg1.setImage(new Image(Launcher.class.getResource("assets/blank.png").toString()));
        bg2.setImage(new Image(Launcher.class.getResource("assets/blank.png").toString()));
        weaponImgGroup.getChildren().add(bg1);
        armorImgGroup.getChildren().add(bg2);
        ArrayList<BasedEqiupment> allEquipments = Launcher.getAllEquipments();

        if (equippedWeapon != null) {
            weaponLbl = new Label("Weapon:\n" + equippedWeapon.getName());
            weaponImg.setImage(new Image(Launcher.class.getResource(equippedWeapon.getImgpath()).toString()));
            weaponImgGroup.getChildren().add(weaponImg);

        } else {
            weaponLbl = new Label("Weapon:");
            weaponImg.setImage(new Image(Launcher.class.getResource("assets/blank.png").toString()));
        }

        if (equippedArmor != null) {
            armorLbl = new Label("Armor:\n" + equippedArmor.getName());
            armorImg.setImage(new Image(Launcher.class.getResource(equippedArmor.getImgpath()).toString()));
            armorImgGroup.getChildren().add(armorImg);

        } else {
            armorLbl = new Label("Armor:");
            armorImg.setImage(new Image(Launcher.class.getResource("assets/blank.png").toString()));
        }

        weaponImgGroup.setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                onDragOver(event, "Weapon");
            }
        });
        armorImgGroup.setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                onDragOver(event,"Armor");
            }
        });
        weaponImgGroup.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                onDragDropped(event,weaponLbl,weaponImgGroup);
            }
        });
        armorImgGroup.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                onDragDropped(event,armorLbl,armorImgGroup);
            }
        });
        //TODO: Allow Only Weapons with the same Damatype as Character to be equipped.

        Button unequipAllButton = new Button("Unequip All");
        unequipAllButton.setOnAction(event -> onUnequipAll());
        equipmentInfoPane.getChildren().addAll(weaponLbl, weaponImgGroup, armorLbl, armorImgGroup, unequipAllButton);

        return equipmentInfoPane;
    }


    //TODO: Add a button to unequipped all the equipments.
    //TODO: Its can unequipped the euipment now. However its still in the slot when regenerate character.
    //TODO: Make its so that Item goes back in the inventory.
    public void onUnequipAll() {

        equippedWeapon = null;
        equippedArmor = null;

        drawPane(null, null);
    }


    public void drawPane(Weapon equippedWeapon, Armor equippedArmor) {
        this.equippedWeapon = equippedWeapon;
        this.equippedArmor = equippedArmor;
        VBox equipmentInfo = getDetailsPane();
        this.setStyle("-fx-background-color: Red;");
        this.setContent(equipmentInfo);
    }
}
