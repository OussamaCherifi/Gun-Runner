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
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Paint;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.shape.StrokeType;
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
    private previewPane prev;
    private Rectangle BackGroundBarrack;

    public BarracksPane() throws FileNotFoundException {

        Font font2 = new Font("Impact", 20);

        this.List = new ArrayList<ItemNotInGame>();

        this.title = new Label("Barracks");
        this.title.setLayoutX(1920 / 2);
        this.title.setLayoutY(20);
        this.title.setScaleX(3);
        this.title.setScaleY(3);
        this.title.setTextFill(Color.web("#7FFF00", 0.8));
        this.title.setFont(new Font("Broadway", 12));

        this.back.setPrefSize(152, 64);
        this.back.setLayoutY(900);
        this.back.setLayoutX(30);
        this.back.getStylesheets().add("styles/button-small.css");
        this.back.setFont(font2);  

        Stop[] stops = new Stop[] { new Stop(0, Color.DARKGRAY), new Stop(1, Color.BLACK)};
        //LinearGradient lg1 = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops);


        InnerShadow e = new InnerShadow();
        e.setColor(Color.BLACK);
        e.setWidth(90);
        e.setHeight(90);
        e.setRadius(300);
        e.setChoke(0.5);
        this.BackGroundBarrack = new Rectangle(1000, 525);
        this.BackGroundBarrack.setLayoutX(150);
        this.BackGroundBarrack.setLayoutY(290);
        this.BackGroundBarrack.setEffect(e);
        this.BackGroundBarrack.setStroke(Paint.valueOf("gray"));
        this.BackGroundBarrack.setStrokeLineJoin(StrokeLineJoin.BEVEL);
        this.BackGroundBarrack.setStrokeType(StrokeType.OUTSIDE);
        this.BackGroundBarrack.setStrokeWidth(28);
        LinearGradient lg2 = new LinearGradient(BackGroundBarrack.getWidth(), -200, BackGroundBarrack.getWidth(), BackGroundBarrack.getHeight(), false, CycleMethod.REFLECT, stops);
        this.BackGroundBarrack.setFill(lg2);



        Barracks barracks = new Barracks();
        this.barrack = barracks;
        barracks.setLayoutX(200);
        barracks.setLayoutY(325);

        previewPane barrackspreview = new previewPane();
        this.prev = barrackspreview;
        barrackspreview.setLayoutX(1400);
        barrackspreview.setLayoutY(350);

        BackgroundImage myBI = new BackgroundImage(new Image("preview/bg.png", 1920/2, 1080, false, true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        this.setBackground(new Background(myBI));

        this.getChildren().addAll(BackGroundBarrack, title, back, barracks, barrackspreview);
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
