/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameGUI;

//import PlayerPreview.Bodypart;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

/**
 *
 * @author oussa
 */
public class previewPane extends Pane {

    private Label title;
    private Rectangle head;
    private Rectangle leftHand;
    private Rectangle rightHand;
    private Rectangle Torso;
    private Rectangle leftFoot;
    private Rectangle rightFoot;
    private Image helmet;

    public previewPane() {
        this.title = new Label("Preview");
        this.title.setLayoutX(350 / 2);
        this.title.setLayoutY(10);
        this.title.setScaleX(3);
        this.title.setScaleY(3);
        this.title.setTextFill(Color.web("#7FFF00", 0.8));
        this.title.setFont(new Font("Broadway", 12));

        this.setMinSize(350, 370);

        BackgroundImage myBI = new BackgroundImage(new Image("preview/background.png", 350, 370, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);

        this.setBackground(new Background(myBI));
//        createRectangles();
//        InsertHelmet(13);
//        InsertTorso(15);
//        insertDualPistoles(18, 18);
//        // inserLeftHand(14);
//
//        insertLeftFoot(17);
//        insertRightFoot(19);

        this.getChildren().addAll(title);
    }

//    private void createRectangles() {
//        this.head = new Rectangle(25, 25);
//        this.head.setFill(Color.AQUA);
//        this.head.setLayoutX(190);
//        this.head.setLayoutY(198);
//
//    }
//
//    public void InsertHelmet(int choice) {
//        Bodypart one = new Bodypart(choice);
//        one.setLayoutX(150);
//        one.setLayoutY(70);
//        one.setFitHeight(60);
//        one.setFitWidth(60);
//        this.getChildren().add(one);
//    }
//
//    public void InsertTorso(int choice) {
//        Bodypart one = new Bodypart(choice);
//        one.setLayoutX(155);
//        one.setLayoutY(125);
//        one.setFitHeight(70);
//        one.setFitWidth(55);
//        this.getChildren().add(one);
//
//    }
//
//    public void insertRightHand(int choice) {
//        Bodypart one = new Bodypart(choice);
//        one.setLayoutX(210);
//        one.setLayoutY(132);
//        one.setFitHeight(25);
//        one.setFitWidth(25);
//        this.getChildren().add(one);
//    }
//
//    public void inserLeftHand(int choice) {
//        Bodypart one = new Bodypart(choice);
//        one.setLayoutX(135);
//        one.setLayoutY(132);
//        one.setFitHeight(25);
//        one.setFitWidth(25);
//        this.getChildren().add(one);
//    }
//
//    public void insertLeftFoot(int choice) {
//        Bodypart one = new Bodypart(choice);
//        one.setLayoutX(147);
//        one.setLayoutY(190);
//        one.setFitHeight(50);
//        one.setFitWidth(50);
//        this.getChildren().add(one);
//    }
//
//    public void insertRightFoot(int choice) {
//        Bodypart one = new Bodypart(choice);
//        one.setLayoutX(190);
//        one.setLayoutY(190);
//        one.setFitHeight(50);
//        one.setFitWidth(50);
//        this.getChildren().add(one);
//    }
//
//    public void insertDualPistoles(int left, int right) {
//        Bodypart leftGun = new Bodypart(left);
//        leftGun.setLayoutX(140);
//        leftGun.setLayoutY(132);
//        leftGun.setFitHeight(25);
//        leftGun.setFitWidth(25);
//        this.getChildren().add(leftGun);
//
//        Bodypart rightGun = new Bodypart(right);
//        rightGun.setLayoutX(210);
//        rightGun.setLayoutY(132);
//        rightGun.setFitHeight(25);
//        rightGun.setFitWidth(25);
//        this.getChildren().add(rightGun);
//
//    }

}
