/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameGUI;

//import PlayerPreview.Bodypart;
import Data.DataController;
import characterElements.Player;
import items.Boot;
import items.Fingers;
import items.Gun;
import items.Hand;
import items.Helmet;
import items.InGameItems;
import items.ItemType;
import items.Torso;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 *
 * @author oussa
 */
public class previewPane extends Pane {

    private Label title;
    
    private previewBackground pBg;
    
    //List of the items for the preview:
    InGameItems helmet, fingers, torso, lhand, rhand, lboot, rboot;
    //guns
    InGameItems pistol, pistol2, uzi, uzi2, ak;
    //Player
    Player player = new Player(300);
    //Last equiped guns
    InGameItems rGun, lGun;
    
    

    public previewPane() {
        this.title = new Label("Preview");
        this.title.setLayoutX(90);
        this.title.setLayoutY(0);
        this.title.setScaleX(3);
        this.title.setScaleY(3);
        this.title.setTextFill(Color.web("#7FFF00", 0.8));
        this.title.setFont(new Font("Broadway", 12));
        
        this.pBg = new previewBackground(-98, -50);
        
        double x = player.getTranslateX();
        double y = player.getTranslateY();
        double s = 56;
        helmet = new Helmet(x, y, 0, 2, DataController.chooseHelmet());
        fingers = new Fingers("dual", x, y, 0, 2);
        torso = new Torso(x, y, 0, 2, DataController.chooseTorsot());
        lhand = new Hand("l", x, y, 0, 2, DataController.chooseHands());
        rhand = new Hand("r", x, y, 0, 2, DataController.chooseHands());
        lboot = new Boot("l", x, y, 0, 2, DataController.chooseBoots());
        rboot = new Boot("r", x, y, 0, 2, DataController.chooseBoots());
        pistol = new Gun("pistol", x, y, 0, 2, DataController.choosePistol());
        pistol2 = new Gun("pistol", x + s, y, 0, 2, DataController.choosePistol());
        uzi = new Gun("uzi", x, y, 0, 2, DataController.chooseUzi());
        uzi2 = new Gun("uzi", x + s, y, 0, 2, DataController.chooseUzi());
        ak = new Gun("ak", x, y, 0, 2, DataController.chooseAk());
        
        rGun = ak;
        lGun = pistol;
        
        
        setItems();
        player.addEquipedItems();
        insertAllItems();
        
//        createRectangles();
//        InsertHelmet(13);
//        InsertTorso(15);
//        insertDualPistoles(18, 18);
//        // inserLeftHand(14);
//
//        insertLeftFoot(17);
//        insertRightFoot(19);

        this.getChildren().addAll(title, pBg);
    }
    
    private void insertItem(InGameItems item){
        removeAllItems();
        double x = player.getTranslateX();
        double y = player.getTranslateY();
        double s = 56;
        if(item.getType() == ItemType.gun){
            Gun gun = (Gun)item;
            switch(item.getKind()){
                case "ak":
                    ak = new Gun("ak", x, y, 0, 2, DataController.chooseAk());
                    rGun = ak;
                    removeAllItems();
                    setItems();
                    player.walkAnimate(0, 0);
                    break;
                case "uzi":
                    uzi = new Gun("uzi", x, y, 0, 2, DataController.chooseUzi());
                    uzi2 = new Gun("uzi", x + s, y, 0, 2, DataController.chooseUzi());
                    rGun = uzi;
                    lGun = uzi2;
                    player.walkAnimate(0, 0);
                    break;
                case "pistol":
                    pistol = new Gun("pistol", x, y, 0, 2, DataController.choosePistol());
                    pistol2 = new Gun("pistol", x + s, y, 0, 2, DataController.choosePistol());
                    rGun = pistol;
                    lGun = pistol2;
                    player.walkAnimate(0, 0);
                    break;
            }
        }
        
        if(item.getType() == ItemType.bullet){
            removeAllItems();
        }else{
            helmet = new Helmet(x, y, 0, 2, DataController.chooseHelmet());
            fingers = new Fingers("dual", x, y, 0, 2);
            torso = new Torso(x, y, 0, 2, DataController.chooseTorsot());
            lhand = new Hand("l", x, y, 0, 2, DataController.chooseHands());
            rhand = new Hand("r", x, y, 0, 2, DataController.chooseHands());
            lboot = new Boot("l", x, y, 0, 2, DataController.chooseBoots());
            rboot = new Boot("r", x, y, 0, 2, DataController.chooseBoots());
        }
    }
    
    private void removeAllItems(){
        removeElement(player.getlHand());
        removeElement(player.getlGun());
        removeElement(player.getFingers());
        removeElement(player.getlBoot());
        removeElement(player.getTorso());
        removeElement(player.getHelmet());
        removeElement(player.getrGun());
        removeElement(player.getrBoot());
        removeElement(player.getrHand());
    }
    
    private void insertAllItems(){
        insertElement(lhand);
        insertElement(lGun);
        insertElement(fingers);
        insertElement(lboot);
        insertElement(torso);
        insertElement(helmet);
        insertElement(rGun);
        insertElement(rboot);
        insertElement(rhand);
    }
    
    public void setItems(){
        player.setlGun(lGun);
        player.setrGun(rGun);
        player.setFingers(fingers);
        player.setHelmet(helmet);
        player.setTorso(torso);
        player.setlHand(lhand);
        player.setrHand(rhand);
        player.setlBoot(lboot);
        player.setrBoot(rboot);
    }
    
    private void removeElement(Node node){
        getChildren().remove(node);
    }
    
    private void insertElement(Node node){
        getChildren().add(node);
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
