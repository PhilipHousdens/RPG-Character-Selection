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
            Launcher.setMainCharacter(GenCharacter.setUpCharacter());

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
        if (dragboard.hasContent(BasedEqiupment.DATA_FORMAT) && retrievedEquipment.getClass().getSimpleName().equals(type)) {
            event.acceptTransferModes(TransferMode.MOVE);
        }
    }

    public static void onDragDropped(DragEvent event, Label lbl, StackPane imgGroup) {
        boolean dragCompleted = false;
        Dragboard dragboard = event.getDragboard();
        ArrayList<BasedEqiupment> allEquipments= Launcher.getAllEquipments();
        BasedEqiupment retrivedEquipment = (BasedEqiupment) dragboard.getContent(BasedEqiupment.DATA_FORMAT); // to retrieve content from DragBoard.
        BasedCharacter character = Launcher.getMainCharacter(); // Assigned the main character obtained from Launcher.
        if (dragboard.hasContent(BasedEqiupment.DATA_FORMAT)) {
            if (retrivedEquipment.getClass().getSimpleName().equals("Weapon")) {
                if (Launcher.getEquippedWeapon() != null) {
                    allEquipments.add(Launcher.getEquippedWeapon());
                }
                Launcher.setEquippedWeapon((Weapon) retrivedEquipment);
                character.equipWeapon((Weapon) retrivedEquipment);
            } else {
                if (Launcher.getEquippedArmor() != null) {
                    allEquipments.add(Launcher.getEquippedArmor());
                }
                Launcher.setEquippedArmor((Armor) retrivedEquipment);
                character.equipArmor((Armor) retrivedEquipment);
            }
            Launcher.setMainCharacter(character);
            Launcher.setAllEquipments(allEquipments);
            Launcher.refreshPane();
            ImageView imageView = new ImageView();
            if (imgGroup.getChildren().size() != 1) {
                imgGroup.getChildren().remove(1);
                Launcher.refreshPane();
            }
            lbl.setText(retrivedEquipment.getClass().getSimpleName() + ":\n" + retrivedEquipment.getName());
            imageView.setImage(new Image(Launcher.class.getResource(retrivedEquipment.getImgpath()).toString()));
            imgGroup.getChildren().add(imageView);
            dragCompleted = true;
        }
        event.setDropCompleted(dragCompleted);

    }


    public AllCustomHandler() {

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

        if (pos != -1) {
            allEquipments.remove(pos);
        }
        Launcher.setAllEquipments(allEquipments);
        Launcher.refreshPane();

        //Adding the Item back to the inventory
        inventoryPane.addItem(retrievedEquipment);
    }
    public static boolean checkClassCompatibility(BasedCharacter chr, BasedEqiupment item) {
        boolean check = false;
        if (chr.getName().equals("MagicChar1")) {
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
        if (chr.getName().equals("BattleMageChar1")) {
            if (item.getClass().getSimpleName().equals("Weapon")) {
                check = true;
            } else {
                check = false;
            }
        }
        return check;
    }



}
