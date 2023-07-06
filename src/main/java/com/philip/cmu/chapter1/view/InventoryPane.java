package com.philip.cmu.chapter1.view;

import com.philip.cmu.chapter1.Launcher;
import com.philip.cmu.chapter1.controller.AllCustomHandler;
import com.philip.cmu.chapter1.model.item.BasedEqiupment;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

import static com.philip.cmu.chapter1.controller.AllCustomHandler.onDragDetected;
import static com.philip.cmu.chapter1.controller.AllCustomHandler.onEquipDone;

public class InventoryPane extends ScrollPane{
    private ArrayList<BasedEqiupment> eqiupmentArray;
    public InventoryPane() {};
    private Pane getDetailsPane() {
        Pane inventoryInfoPane = new HBox(10);
        inventoryInfoPane.setBorder(null);
        inventoryInfoPane.setPadding(new Insets(25, 25, 25, 25));
        if (eqiupmentArray != null) {
            ImageView[] imageViewsList = new ImageView[eqiupmentArray.size()];
            for (int i = 0; i < eqiupmentArray.size(); i++) {
                imageViewsList[i] = new ImageView();
                imageViewsList[i].setImage(new Image(Launcher.class.getResource(eqiupmentArray.get(i).getImgpath()).toString()));
                int finalI = i;
                imageViewsList[i].setOnDragDetected(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        onDragDetected(event, eqiupmentArray.get(finalI), imageViewsList[finalI]);
                    }
                });
                imageViewsList[i].setOnDragDone(new EventHandler<DragEvent>() {
                    @Override
                    public void handle(DragEvent event) {
                        onEquipDone(event, InventoryPane.this);
                    }
                });
            }
            inventoryInfoPane.getChildren().addAll(imageViewsList);
        }
        return inventoryInfoPane;
    }
    private void onDragDropped(DragEvent event, BasedEqiupment eqiupment) {
        AllCustomHandler.onDragDropped(event,null,null);
    }
    public void addItem(BasedEqiupment eqiupment) {
        if (!eqiupmentArray.contains(eqiupment)) {
            eqiupmentArray.add(eqiupment);
            drawPane(eqiupmentArray);
        }
    }
    public void drawPane(ArrayList<BasedEqiupment> eqiupmentArray) {
        this.eqiupmentArray = eqiupmentArray;
        Pane invetoryInfo = getDetailsPane();
        this.setStyle("-fx-background-color: red");
        this.setContent(invetoryInfo);
    }
}
