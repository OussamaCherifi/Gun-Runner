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
import items.Bullet;
import items.Custom;
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
    //bullets
    InGameItems pBullet, uziBullet, akBullet;
    //Player
    Player player = new Player(400);
    //Last equiped guns
    InGameItems rGun, lGun;
    
    

    public previewPane() {
        this.title = new Label("Preview");
        this.title.setLayoutX(90);
        this.title.setLayoutY(40);
        this.title.setScaleX(3);
        this.title.setScaleY(3);
        this.title.setTextFill(Color.web("#ededed", 0.8));
        this.title.setFont(new Font("Impact", 12));
        
        this.pBg = new previewBackground(1300, 275);
        this.pBg.setScaleX(0.56);
        this.pBg.setScaleY(0.56);
        
        initializeItems();
        
        rGun = pistol;
        lGun = pistol2;
        
        
        
        insertAllItems();
        setItems();
        player.addEquipedItems();
        
        insertItem(pistol);
        
        this.getChildren().addAll(title, pBg);
    }
    
    private void initializeItems(){
        double x = player.getTranslateX();
        double y = player.getTranslateY();
        double s = 56;
        
        helmet = new Helmet(x, y, 2, DataController.chooseHelmet());
        fingers = new Fingers("dual", x, y, 2);
        torso = new Torso(x, y, 2, DataController.chooseTorsot());
        lhand = new Hand("l", x, y, 2, DataController.chooseHands());
        rhand = new Hand("r", x, y, 2, DataController.chooseHands());
        lboot = new Boot("l", x, y, 2, DataController.chooseBoots());
        rboot = new Boot("r", x, y, 2, DataController.chooseBoots());
        pistol = new Gun("pistol", x, y, 2, DataController.choosePistol());
        pistol2 = new Gun("pistol", x + s, y, 2, DataController.choosePistol());
        uzi = new Gun("uzi", x, y, 3, DataController.chooseUzi());
        uzi2 = new Gun("uzi", x + s, y, 2, DataController.chooseUzi());
        ak = new Gun("ak", x, y, 2, DataController.chooseAk());
        pBullet = new Bullet("pistol", x, y, 2, Custom.c1, player);
        uziBullet = new Bullet("uzi", x, y, 2, Custom.c1, player);
        akBullet = new Bullet("ak", x, y, 2, Custom.c1, player);
        
        pBullet.setLayoutY(50);
        pBullet.setYpos(50);
        pBullet.setTranslateY(50);
        
        uziBullet.setLayoutY(75);
        uziBullet.setTranslateY(75);
        uziBullet.setYpos(75);
        
        akBullet.setLayoutY(100);
        akBullet.setLayoutY(100);
        akBullet.setYpos(100);
        
        pBullet.setLayoutX(0);
        uziBullet.setLayoutX(0);
        akBullet.setLayoutX(0);
        
    }
    
    public void insertItem(InGameItems item){
        player.setTranslateY(142);
        
        double x = player.getTranslateX();
        double y = player.getTranslateY();
        double s = 56;
        if(item.getType() == ItemType.gun){
            Gun gun = (Gun)item;
            switch(item.getKind()){
                case "ak":
                    removeBullets();
                    removeAllItems();
                    initializeItems();
                    ak = new Gun("ak", x, y, 2, DataController.chooseAk());
                    rGun = ak;
                    setItems();
                    insertAllItems();
                    player.walkAnimate(0, 0);
                    break;
                case "uzi":
                    removeBullets();
                    removeAllItems();
                    initializeItems();
                    uzi = new Gun("uzi", x, y, 2, DataController.chooseUzi());
                    uzi2 = new Gun("uzi", x + s, y, 2, DataController.chooseUzi());
                    rGun = uzi;
                    lGun = uzi2;
                    setItems();
                    insertAllItems();
                    player.walkAnimate(0, 0);
                    break;
                case "pistol":
                    removeBullets();
                    removeAllItems();
                    initializeItems();
                    pistol = new Gun("pistol", x, y, 2, DataController.choosePistol());
                    pistol2 = new Gun("pistol", x + s, y, 2, DataController.choosePistol());
                    rGun = pistol;
                    lGun = pistol2;
                    setItems();
                    insertAllItems();
                    player.walkAnimate(0, 0);
                    break;
            }
        }else{
           if(item.getType() == ItemType.bullet){
            removeAllItems();
            insertBullets();
            }else{
            removeBullets();
            removeAllItems();
            initializeItems();
            setItems();
            insertAllItems();
            player.walkAnimate(0, 0); 
            }
        }
    }
    private void insertBullets(){
        insertElement(pBullet);
        insertElement(uziBullet);
        insertElement(akBullet);
    }
    
    private void removeBullets(){
        if(getChildren().contains(pBullet)){
            removeElement(pBullet);
            removeElement(uziBullet);
            removeElement(akBullet);
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

    public Player getPlayer() {
        return player;
    }

    public previewBackground getpBg() {
        return pBg;
    }
    
    

}