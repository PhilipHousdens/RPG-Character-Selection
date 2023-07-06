package com.philip.cmu.chapter1.controller;

import com.philip.cmu.chapter1.Launcher;
import com.philip.cmu.chapter1.model.DamageType;
import com.philip.cmu.chapter1.model.character.BasedCharacter;
import com.philip.cmu.chapter1.model.item.Armor;
import com.philip.cmu.chapter1.model.item.BasedEqiupment;
import com.philip.cmu.chapter1.model.item.Weapon;
import com.philip.cmu.chapter1.view.InventoryPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;


public class AllCustomHandler {
    static InventoryPane InP = new InventoryPane();
    public static class GenCharacterHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            BasedCharacter oldCharacter = GenCharacter.setUpCharacter();


            if (Launcher.getEquippedWeapon() != null) {
                oldCharacter.unequipWeapon();
                Launcher.setEquippedWeapon(null);
            }
            if (Launcher.getEquippedArmor() != null) {
                oldCharacter.unequipArmor();
                Launcher.setEquippedArmor(null);
            }

            Launcher.setMainCharacter(oldCharacter);
            Launcher.refreshPane();
        }
    }
    public static void onDragDetected(MouseEvent event, BasedEqiupment eqiupment, ImageView imageView) {
        Dragboard db = imageView.startDragAndDrop(TransferMode.ANY);
        db.setDragView(imageView.getImage());
        ClipboardContent content = new ClipboardContent();
        content.put(eqiupment.DATA_FORMAT, eqiupment);
        db.setContent(content);
        event.consume();
    }

    public static void onDragOver(DragEvent event, String type) {
        Dragboard dragboard = event.getDragboard();
        BasedEqiupment retrievedEquipment = (BasedEqiupment) dragboard.getContent(BasedEqiupment.DATA_FORMAT);
        BasedCharacter character = Launcher.getMainCharacter();
        if (dragboard.hasContent(BasedEqiupment.DATA_FORMAT) && retrievedEquipment.getClass().getSimpleName().equals(type) && checkClassCompatibility(character, retrievedEquipment)) {
            event.acceptTransferModes(TransferMode.MOVE);
        }
    }

    public static void onDragDropped(DragEvent event, Label lbl, StackPane imgGroup) {
        boolean dragCompleted = false;
        Dragboard dragboard = event.getDragboard();
        ArrayList<BasedEqiupment> allEquipments = Launcher.getAllEquipments();



        if (dragboard.hasContent(BasedEqiupment.DATA_FORMAT)) {
            BasedEqiupment retrievedEquipment = (BasedEqiupment) dragboard.getContent(BasedEqiupment.DATA_FORMAT);
            BasedCharacter character = Launcher.getMainCharacter();



            if (retrievedEquipment.getClass().getSimpleName().equals("Weapon")) {
                if (Launcher.getEquippedWeapon() != null)
                    allEquipments.add(Launcher.getEquippedWeapon());
                Launcher.setEquippedWeapon((Weapon) retrievedEquipment);
                character.equipWeapon((Weapon) retrievedEquipment);
            } else {
                if (Launcher.getEquippedArmor() != null)
                    allEquipments.add(Launcher.getEquippedArmor());
                Launcher.setEquippedArmor((Armor) retrievedEquipment);
                character.equipArmor((Armor) retrievedEquipment);
            }
            Launcher.setMainCharacter(character);
            Launcher.setAllEquipments(allEquipments);
            Launcher.refreshPane();



            ImageView imgView = new ImageView();
            if (imgGroup.getChildren().size() != 1) {
                imgGroup.getChildren().remove(1);
                Launcher.refreshPane();
            }



            lbl.setText(retrievedEquipment.getClass().getSimpleName() + ":\n" + retrievedEquipment.getName());
            imgView.setImage(new Image(Launcher.class.getResource(retrievedEquipment.getImgpath()).toString()));
            imgGroup.getChildren().add(imgView);
            dragCompleted = true;
        } else {
            BasedEqiupment retrievedEquipment = (BasedEqiupment) dragboard.getContent(BasedEqiupment.DATA_FORMAT);
            if (retrievedEquipment != null) {
                allEquipments.add(retrievedEquipment);
                Launcher.setAllEquipments(allEquipments);
                Launcher.refreshPane();
                dragCompleted = true;
            }
        }



        event.setDropCompleted(dragCompleted);
    }

    public static void onEquipDone(DragEvent event, InventoryPane inventoryPane) {
        Dragboard dragboard = event.getDragboard();
        ArrayList<BasedEqiupment> allEquipments = Launcher.getAllEquipments();
        BasedEqiupment retrievedEquipment = (BasedEqiupment) dragboard.getContent(BasedEqiupment.DATA_FORMAT);

        int pos = -1;
        for (int i = 0; i < allEquipments.size(); i++) {
            if (allEquipments.get(i).getName().equals(retrievedEquipment.getName())) {
                pos = i;
                break;
            }
        }

        Launcher.setAllEquipments(allEquipments);
        Launcher.refreshPane();

        // Adding the item back to the inventory
        inventoryPane.addItem(retrievedEquipment);

    }

    public static class UnEquipAll implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            Launcher.setEquippedWeapon(null);
            Launcher.setEquippedArmor(null);
            Launcher.setAllEquipments(GenItemList.setUpItemList());
            Launcher.refreshPane();
        }
    }

    public static boolean checkClassCompatibility(BasedCharacter chr, BasedEqiupment item) {
        boolean check = false;
        if (chr.getName().equals("MagicalChar1")) {
            if (item.getClass().getSimpleName().equals("Weapon")) {
                check = ((Weapon) item).getDamageType().equals(DamageType.magical);
            } else {
                check = true;
            }
        }
        if (chr.getName().equals("PhysicalChar1")) {
            if (item.getClass().getSimpleName().equals("Weapon")) {
                check = ((Weapon) item).getDamageType().equals(DamageType.physical);
            } else {
                check = true;
            }
        }
        if (chr.getName().equals("BattleMage1")) {
            if (item.getClass().getSimpleName().equals("Weapon")) {
                check = true;
            } else {
                check = false;
            }
        }
        return check;
    }



}
