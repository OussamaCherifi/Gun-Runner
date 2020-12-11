/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scenes;

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
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

/**
 *
 * @author oussa
 */
public class BarracksPane extends Pane {

    Label title;
    private Button back;
    private ArrayList<Item> List;
    private Barracks barrack;
    private previewPane prev;
    private Rectangle BackGroundBarrack;

    public BarracksPane() throws FileNotFoundException {

        this.List = new ArrayList<Item>();

        this.title = new Label("Barracks");
        this.title.setLayoutX(1920 / 2);
        this.title.setLayoutY(20);
        this.title.setScaleX(3);
        this.title.setScaleY(3);
        this.title.setTextFill(Color.web("#7FFF00", 0.8));
        this.title.setFont(new Font("Broadway", 12));

        back = new Button("Back");

        this.back = new Button("Back");
        this.back.setLayoutY(900);
        this.back.setLayoutX(30);
        this.back.setScaleX(4);
        this.back.setScaleY(3);

        this.back.setScaleX(1.25);
        this.back.setScaleY(1.25);
        this.back.setTextFill(Color.web("#ff0000", 0.8));
        
        this.BackGroundBarrack = new Rectangle(1100, 525);
        this.BackGroundBarrack.setLayoutX(220);
        this.BackGroundBarrack.setLayoutY(290);
        this.BackGroundBarrack.setStroke(Color.AZURE);
        this.BackGroundBarrack.setStrokeWidth(2);
        
        Barracks barracks = new Barracks();
        this.barrack = barracks;
        barracks.setLayoutX(1920/8);
        barracks.setLayoutY(325);

        previewPane barrackspreview = new previewPane();
        this.prev = barrackspreview;
        barrackspreview.setLayoutX(1400);
        barrackspreview.setLayoutY(350);

        BackgroundImage myBI = new BackgroundImage(new Image("preview/bg.png", 1920, 1080, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        this.setBackground(new Background(myBI));

        this.getChildren().addAll(BackGroundBarrack, title, back, barracks, barrackspreview);
    }

    public ArrayList<Item> getList() {
        return List;
    }

    public void setList(ArrayList<Item> List) {

        this.List = List;
    }

    public Barracks getBarrack() {
        return barrack;
    }

    public void setBarrack(Barracks barrack) {
        this.barrack = barrack;
    }

    public previewPane getPrev() {
        return prev;
    }

    public void setPrev(previewPane prev) {
        this.prev = prev;
    }

    public Button getBack() {
        return back;
    }
    
    

}
