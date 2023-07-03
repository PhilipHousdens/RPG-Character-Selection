package com.philip.cmu.chapter1.view;
import com.philip.cmu.chapter1.Launcher;
import com.philip.cmu.chapter1.controller.AllCustomHandler;
import com.philip.cmu.chapter1.model.character.BasedCharacter;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.scene.image.Image;

public class CharacterPane extends ScrollPane {
    private BasedCharacter character;

    public CharacterPane() { }

    private Pane getDetailsPane() {
        Pane characterInfoPane = new VBox(10);
        characterInfoPane.setBorder(null);
        characterInfoPane.setPadding(new Insets(25, 25, 25, 25));

        Label name, type, hp, atk, def, res;
        ImageView mainImage = new ImageView();

        if (this.character != null) {
            name = new Label("Name: " + character.getName());
            mainImage.setImage(new Image(Launcher.class.getResource(character.getImgpath()).toString()));
            hp = new Label("HP: " + character.getHp() + "/" + character.getFullHp());
            type = new Label("Type: " + character.getType());
            atk = new Label("ATK: " + character.getPower());
            def = new Label("DEF: " + character.getDefense());
            res = new Label("RES: " + character.getResistance());
        } else {
            name = new Label("Name:");
            mainImage.setImage(new Image(Launcher.class.getResource("assets/unknown.png").toString()));
            hp = new Label("HP:");
            type = new Label("Type:");
            atk = new Label("ATK:");
            def = new Label("DEF:");
            res = new Label("RES:");
        }

        Button genCharacter = new Button();
        genCharacter.setText("Generate Character");
        genCharacter.setOnAction(new AllCustomHandler.GenCharacterHandler());

        characterInfoPane.getChildren().addAll(name, mainImage, type, hp, atk, def, res, genCharacter);
        return characterInfoPane;
    }

    public void drawPane(BasedCharacter character) {
        this.character = character;
        Pane characterInfo = getDetailsPane();
        this.setStyle("-fx-background-color: Red;");
        this.setContent(characterInfo);
    }
}
