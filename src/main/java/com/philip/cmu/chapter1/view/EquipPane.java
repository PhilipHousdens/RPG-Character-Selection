package com.philip.cmu.chapter1.view;

import com.philip.cmu.chapter1.Launcher;
import com.philip.cmu.chapter1.controller.AllCustomHandler;
import com.philip.cmu.chapter1.controller.GenCharacter;
import com.philip.cmu.chapter1.model.character.BasedCharacter;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;


import static com.philip.cmu.chapter1.controller.AllCustomHandler.*;


public class EquipPane extends ScrollPane {
    InventoryPane inventoryPane = new InventoryPane();
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
            public void handle(DragEvent e) {onDragOver(e, "Weapon");}
        });
        armorImgGroup.setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent e) {onDragOver(e,"Armor");}
        });
        weaponImgGroup.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent e) {
                AllCustomHandler.onDragDropped(e, weaponLbl, weaponImgGroup);
            }
        });

        armorImgGroup.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent e) {
                AllCustomHandler.onDragDropped(e, armorLbl, armorImgGroup);
            }
        });
        weaponImgGroup.setOnDragDone(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent e) {
                AllCustomHandler.onEquipDone(e, inventoryPane);
            }
        });

        armorImgGroup.setOnDragDone(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent e) {
                AllCustomHandler.onEquipDone(e, inventoryPane);
            }
        });


        Button unequipAllButton = new Button("Unequipped All");
        unequipAllButton.setOnAction((new AllCustomHandler.UnEquipAll()));
        equipmentInfoPane.getChildren().addAll(weaponLbl, weaponImgGroup, armorLbl, armorImgGroup, unequipAllButton);
        return equipmentInfoPane;
    }



    public void drawPane(Weapon equippedWeapon, Armor equippedArmor) {
        this.equippedWeapon = equippedWeapon;
        this.equippedArmor = equippedArmor;
        VBox equipmentInfo = getDetailsPane();
        this.setStyle("-fx-background-color: Red;");
        this.setContent(equipmentInfo);
    }
}
