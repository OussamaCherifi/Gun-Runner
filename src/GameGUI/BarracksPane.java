/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameGUI;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 *
 * @author oussa
 */
public class BarracksPane extends Pane {

    Label title;
    private Button back = new Button("Back");
    private ArrayList<ItemNotInGame> List;
    private Barracks barrack;
    private previewPane preview;
    //private Rectangle BackGroundBarrack;

    public BarracksPane() throws FileNotFoundException {

        Font font2 = new Font("Impact", 20);

        this.List = new ArrayList<>();

        this.title = new Label("Barracks");
        this.title.setLayoutX(1920 / 2);
        this.title.setLayoutY(20);
        this.title.setScaleX(3);
        this.title.setScaleY(3);
        this.title.setTextFill(Color.web("#ededed", 0.8));
        this.title.setFont(font2);
        

        this.back.setPrefSize(152, 64);
        this.back.setLayoutY(900);
        this.back.setLayoutX(30);
        this.back.getStylesheets().add("styles/button-small.css");
        this.back.setFont(font2);  


        gridBackground gBg = new gridBackground(110, 275);

        Barracks barracks = new Barracks();
        this.barrack = barracks;
        barracks.setLayoutX(160);
        barracks.setLayoutY(325);

        previewPane preview = new previewPane();
        this.preview = preview;
        preview.setLayoutX(1400);
        preview.setLayoutY(325);

        BackgroundImage myBI = new BackgroundImage(new Image("preview/bg.png", 1920/2, 1080, false, true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        this.setBackground(new Background(myBI));

        this.getChildren().addAll(preview.getpBg(), preview,  gBg, title, back, barracks, preview.getPlayer());
    }

    public ArrayList<ItemNotInGame> getList() {
        return List;
    }

    public void setList(ArrayList<ItemNotInGame> List) {

        this.List = List;
    }

    public Barracks getBarrack() {
        return barrack;
    }

    public void setBarrack(Barracks barrack) {
        this.barrack = barrack;
    }

    public previewPane getPreview() {
        return preview;
    }

    public void setPreview(previewPane preview) {
        this.preview = preview;
    }

    public Button getBack() {
        return back;
    }
    
    
}