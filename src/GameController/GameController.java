/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameController;

import obstacles.*;
import GameGUI.Map;
import characterElements.Enemies;
import items.*;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import characterElements.Player;

/**
 *
 * @author 15148
 */
     public class GameController {

    Map map;

    //Graphical elements.  Creating them seperately in different list
    //as the player will interact differently with all of these. 
    //In fact, it will only interact with the floors and platforms. 
    //floors and plaforms are simply children of the abstract class obstacles
    private Node toAnimate;

    List<Obstacles> floors = new ArrayList<>();
    List<Obstacles> platforms = new ArrayList<>();
    private boolean firstTime = false;

    long startTime = System.currentTimeMillis();
    Label timerLabel = new Label();
    Label lbl = new Label();
    Label lbl2 = new Label();
    Rectangle rect = new Rectangle(100, 2, Paint.valueOf("#ff14ff"));

    //the two classes sbackgrounds and ceilings have their own 
    List<BackgroundsParent> backgrounds = new ArrayList<>();
    List<BackgroundsParent> ceilings = new ArrayList<>();

    //Actual player
    Player player;

    //List of the items of the game : 
    Item helmet, fingers, torso, lhand, rhand, lboot, rboot;
    //guns
    Item pistol, pistol2, uzi, uzi2, ak;

    public GameController(Map map) {
        this.map = map;
        createBackground();
        createFloors();
        createPlatforms();
        createCeilings();

        player = new Player(floors.get(0));

        map.addAllBackgrounds(backgrounds);
        map.addAllBackgrounds(ceilings);
        map.addAllElements(floors);
        map.addAllElements(platforms);
        map.insertElement(player);

        double s = 56;
        double x = player.getTranslateX();
        double y = player.getTranslateY();

        helmet = new Helmet(x, y, 0, 2, Custom.normal);
        fingers = new Fingers("dual", x, y, 0, 2);
        torso = new Torso(x, y, 0, 2, Custom.normal);
        lhand = new Hand("l", x, y, 0, 2, Custom.normal);
        rhand = new Hand("r", x, y, 0, 2, Custom.normal);
        lboot = new Boot("l", x, y, 0, 2, Custom.normal);
        rboot = new Boot("r", x, y, 0, 2, Custom.normal);
        pistol = new Gun("pistol", x, y, 0, 2, Custom.normal);
        pistol2 = new Gun("pistol", x + s, y, 0, 2, Custom.normal);
        uzi = new Gun("uzi", x, y, 0, 2, Custom.normal);
        uzi2 = new Gun("uzi", x + s, y, 0, 2, Custom.normal);
        ak = new Gun("ak", x, y, 0, 2, Custom.normal);
            
        addPlayerSprite(pistol , pistol2);
        player.setFingers(fingers);
        player.setHelmet(helmet);
        player.setTorso(torso);
        player.setlHand(lhand);
        player.setrHand(rhand);
        player.setlBoot(lboot);
        player.setrBoot(rboot);

        player.addEquipedItems();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {

                update();
            }
        };

        timer.start();

    }
    
    private void addPlayerSprite(Item gun1){
        player.setrGun(gun1);
        player.setlGun(pistol);
        
        map.insertElement(lhand);
        map.insertElement(pistol);
        map.insertElement(fingers);
        map.insertElement(lboot);
        map.insertElement(torso);
        map.insertElement(helmet);
        map.insertElement(gun1);
        map.insertElement(rboot);
        map.insertElement(rhand);    
    }
    
    private void addPlayerSprite(Item gun1 , Item gun2){
        player.setrGun(gun1);
        player.setlGun(gun2);
        
        map.insertElement(lhand);
        map.insertElement(gun2);
        map.insertElement(fingers);
        map.insertElement(lboot);
        map.insertElement(torso);
        map.insertElement(helmet);
        map.insertElement(gun1);
        map.insertElement(rboot);
        map.insertElement(rhand);    
    }
    
    private void removeAllItems(){
        map.removeElement(player.getlHand());
        map.removeElement(player.getlGun());
        map.removeElement(player.getFingers());
        map.removeElement(player.getlBoot());
        map.removeElement(player.getTorso());
        map.removeElement(player.getHelmet());
        map.removeElement(player.getrGun());
        map.removeElement(player.getrBoot());
        map.removeElement(player.getrHand());     
    }

    
    private void update() {
        if (!firstTime) {
            this.firstTime = true;
            player.walkAnimate(0, 0);
        }
        updateMapSprites();
        
        //Player updates
        player.update(getAllObstaclesInMap() , map.getMapWidth());
        player.BulletImpact(getAllEnemies(), getAllObstaclesInMap(), map);

        //non-player updates
        updateEnemyBullets();
        
        crateCollision();
    }

    
    private void updateMapSprites() {
        for (Obstacles o : getAllObstaclesInMap()) {
            o.update(map);
        }
        for (BackgroundsParent b : getAllBackgroundsInMap()) {
            b.update(map);
        }
    }
    
    private void updateEnemyBullets() {
        for (Node n : map.getChildren()) {
            if (n instanceof Bullet) {
                if (((Bullet) n).getFromWho() instanceof Enemies) {
                    ((Bullet) n).update(map.getMapWidth(), "left");
                    enemyBulletContact((Bullet) n);
                }
            }
        }
    }
    
    private void enemyBulletContact(Bullet b) {
        for (Obstacles o : getAllObstaclesInMap()) {
            if (b.getBoundsInParent().intersects(o.getBoundsInParent())) {
                b.setTranslateY(-2000);
                b.setIsDead(true);
            }
        }

        if (b.getBoundsInParent().intersects(player.getBoundsInParent())) {
            System.out.println("Player health before : " + player.getHealth());
            b.setTranslateY(-2000);
            b.setIsDead(true);
            //damage of the bullet = 10;
            player.setHealth(player.getHealth() - 10);
            System.out.println("PLayer health after : " + player.getHealth());
        }
    }

    private void crateCollision(){
        ArrayList<Crates> crates = getAllCrates();
        for(int i = 0; i < crates.size(); i++){
            if(crates.get(i).getBoundsInParent().intersects(player.getBoundsInParent())){
                int gun = crates.get(i).getGun();
                (crates.get(i)).die();
                if(gun == 1){
                    removeAllItems();
                    addPlayerSprite(ak);
                    player.setHasSpecialGun(true);
                }else{
                    removeAllItems();
                    addPlayerSprite(uzi, uzi2);
                    player.setHasSpecialGun(true);
                }
            }
            
        }
    }
    
    
    //The next two methods are for extrating the obstacles and backgrounds separately from the children
    //of our map
    private ArrayList<Obstacles> getAllObstaclesInMap() {
        ArrayList<Obstacles> list = new ArrayList<>();

        for (Node n : map.getChildren()) {
            if (n instanceof Obstacles) {
                list.add((Obstacles) n);
            }
        }

        return list;
    }

    private ArrayList<BackgroundsParent> getAllBackgroundsInMap() {
        ArrayList<BackgroundsParent> list = new ArrayList<>();

        for (Node n : map.getChildren()) {
            if (n instanceof BackgroundsParent) {
                list.add((BackgroundsParent) n);
            }
        }

        return list;
    }

    private List<Enemies> getAllEnemies() {
        List<Enemies> enemies = new ArrayList<>();
        for (Node n : map.getChildren()) {
            if (n instanceof Enemies) {
                enemies.add((Enemies) n);
            }
        }
        return enemies;
    }
    
    private ArrayList<Crates> getAllCrates(){
        ArrayList<Crates> crates = new ArrayList<>();
        for (Node n : map.getChildren()) {
            if (n instanceof Crates) {
                crates.add((Crates) n);
            }
        }
        return crates;
    }
    
    //the next four methods are only here to create the map's graphical elements. 
    private void createBackground() {
        int xpos = 0;

        Background b = new Background(0, 0, "bg"); //this background serves as a reference for the rest of the backgrounds

        for (int i = 0; i < 4; i++) {
            backgrounds.add(new Background(b.getWidth() * i, 42 * (b.findScaling() + 0.5), "bg"));
        }

    }

    private void createFloors() {
        int xpos = 0;
        int counter = 1;
        String path = "";
        while (xpos <= map.getMapWidth()) {
            // System.out.println("x pos = :" + xpos);
            path = "sprites/Map/floor_" + counter + ".png";
            Floor f = new Floor(path, xpos, 954, "floor");

            floors.add(f);

            if (counter == 3) {
                counter = 1;
            } else {
                counter++;
            }
            xpos += f.getWidth();
        }
    }

    private void createPlatforms() {
        String path = "sprites/Map/platform.png";
        double distanceBetweenPlatforms = 20;

        int xpos = 0;
        int counter = 0;
        double s = map.getMapHeight() * 0.62;
        while (xpos <= map.getMapWidth() * 2) {
            Platforms p = new Platforms(path, 500 + distanceBetweenPlatforms, Math.round(Math.random() * (-400) + s), "platform");
            distanceBetweenPlatforms += p.getWidth() + (Math.random() * (40));
            platforms.add(p);
            xpos += distanceBetweenPlatforms;
        }
    }

    private void createCeilings() {
        double xpos = 0;

        while (xpos <= map.getMapWidth()) {
            Ceiling ceiling = new Ceiling(xpos, 0, "ceiling");
            ceilings.add(ceiling);
            xpos += ceiling.getWidth();
        }

    }

    //getters for the key controllers : 
    public KeyPressedController getKeyPressedController() {
        return new KeyPressedController();
    }

    public KeyReleasedController getKeyReleasedController() {
        return new KeyReleasedController();
    }

    //These classes are event handlers for whenever we press specific buttons 
    // This is where we will create every key binds that our player will need in order to play the game. 
    private class KeyPressedController implements EventHandler<KeyEvent> {
        @Override
        public void handle(KeyEvent e) {

            if (e.getCode() == KeyCode.SPACE && player.getIsJumping() == false) {
                player.setJumpingForce(30);
                player.setIsJumping(true);
            }
            if (e.getCode() == KeyCode.S) {
                System.out.println("this hapenned");
                player.goToBottom();
            }
//            if(e.getCode() == KeyCode.A){
//                removeAllItems();
//            }
//            if(e.getCode() == KeyCode.F){
//                addPlayerSprite(uzi, uzi2);
//            }
        }
    }

    private class KeyReleasedController implements EventHandler<KeyEvent> {
        @Override
        public void handle(KeyEvent e) {
            if (e.getCode() == KeyCode.Q) {
                if (!player.isReloading()) {
                    boolean specialGunBefore = player.isHasSpecialGun();
                    player.shoot(map);
                    if(player.isHasSpecialGun() != specialGunBefore){
                        removeAllItems();
                        addPlayerSprite(pistol, pistol2);
                    }
                }
            }        
        }
    }
}
