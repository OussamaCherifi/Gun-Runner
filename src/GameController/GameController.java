/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameController;

import obstacles.*;
import GameGUI.Map;
import items.*;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import player.Player;

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
    
    public GameController(Map map) {
        this.map = map;
        createBackground();
        createFloors();
        createPlatforms();
        createCeilings();
        
        timerLabel.setTextFill(Paint.valueOf("white"));
        timerLabel.setLayoutX(100);
        timerLabel.setLayoutY(600);
        
        rect.setTranslateX(180);
        rect.setTranslateY(950);
        
        player = new Player(floors.get(0));
        
        map.addAllBackgrounds(backgrounds);
        map.addAllBackgrounds(ceilings);
        map.addAllElements(floors);
        map.addAllElements(platforms);
        map.insertElement(player);
        
        double s = 56;
        double s1 = 46;
        
        double x = player.getTranslateX();
        double y = player.getTranslateY();
        
        System.out.println("TRANSLATE X: " + x);
        System.out.println("TRANSLATE Y: " + y);
        
        System.out.println("POS X: " + player.getXpos());
        System.out.println("POS Y: " + player.getYpos());
        
        Item helmet = new Helmet(x, y, 0, 2, Custom.normal);
        Item fingers = new Fingers("dual", x, y, 0, 2);
        Item torso = new Torso(x, y, 0, 2, Custom.normal);
        Item lhand = new Hand("l", x, y, 0, 2, Custom.normal);
        Item rhand = new Hand("r", x, y, 0, 2, Custom.normal);
        Item lboot = new Boot("l", x, y, 0, 2, Custom.normal);
        Item rboot = new Boot("r", x, y, 0, 2, Custom.normal);
        Item pistol = new Gun("pistol", x, y, 0, 2, Custom.normal);
        Item pistol2 = new Gun("pistol", x+s, y, 0, 2, Custom.normal);
        Item uzi = new Gun("uzi", x, y, 0, 2, Custom.normal);
        Item uzi2 = new Gun("uzi", x+s, y, 0, 2, Custom.normal);
        Item ak = new Gun("ak", x, y, 0, 2, Custom.normal);
        
        
        toAnimate = rect;
        
        map.insertElement(timerLabel);
        map.insertElement(lhand);
        map.insertElement(uzi2);
        map.insertElement(fingers);
        map.insertElement(lboot);
        
        map.insertElement(torso);
        map.insertElement(helmet);
        map.insertElement(uzi);
        map.insertElement(rboot);
        map.insertElement(rhand);
        
        
        player.setlGun(uzi2);//
        player.setrGun(uzi);//
        player.setFingers(fingers);//
        player.setHelmet(helmet);
        player.setTorso(torso);
        player.setlHand(lhand);
        player.setrHand(rhand);
        player.setlBoot(lboot);
        player.setrBoot(rboot);
        
        
        player.addEquipedItems();
        
        lbl.setTranslateX(1700);
        lbl.setTranslateY(300);
        lbl.setTextFill(Paint.valueOf("white"));
        
        lbl2.setTranslateX(1500);
        lbl2.setTranslateY(500);
        lbl2.setTextFill(Paint.valueOf("white"));
        
            
        map.insertElement(rect);
        map.insertElement(lbl);
        map.insertElement(lbl2);
        
        
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                
                update();
            }
        };

        timer.start();
        
    }
    

    
    private void update() {
        if(!firstTime){
            this.firstTime = true;
            player.walkAnimate(0, 0);
        }
        updateMapSprites();
        player.update(getAllObstaclesInMap());
        lbl2.setText("isInTheAir: "+player.getIsInTheAir()+"   isFalling:"+player.getIsFalling()+"\n"
                        +"helmet translate y: " + player.getHelmet().getTranslateY() + " helmet ypos: "+ player.getHelmet().getYpos());
        
        long elapsedMillis = System.currentTimeMillis() - startTime ;
                //timerLabel.setText(Long.toString(elapsedMillis));
                timerLabel.setText("lowerY: "+Math.round(player.getLowerY())+" mainGround: "+Math.round(player.getGround().getYpos())+"\n"
                        +"previous Ground: "+player.getPreviousGround()+" current ground: "+ player.getGround()
                        +"  difference: "+Math.round(player.getLowerY()-player.getTorso().getYpos()));
        
         
        
    }
    
    private void updateMapSprites() {
        for (Obstacles o : getAllObstaclesInMap()) {
            o.update(map);
        }
        for (BackgroundsParent b : getAllBackgroundsInMap()) {
            b.update(map);
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
    
    private void initializeItems(){
        
        
        
    }

    //the next four methods are only here to create the map's graphical elements. 
    private void createBackground() {
        int xpos = 0;
        
        Background b = new Background(0, 0, "bg"); //this background serves as a reference for the rest of the backgrounds
        
        for(int i=0;i<4;i++){
            backgrounds.add(new Background(b.getWidth()*i, 42*(b.findScaling()+0.5), "bg"));
        }
        
        /*
        while (xpos <= map.getMapWidth()) {
            Background b = new Background(xpos, 0, "bg");
            backgrounds.add(b);
            xpos += b.getWidth();
        }
        //adding one more background to fill up the empty space created by the while loop
        Background b = new Background(xpos, 0, "bg");
        backgrounds.add(b);
        */
    }
    
    
    
    private void createFloors() {
        int xpos = 0;
        int counter = 1;
        String path = "";
        while (xpos <= map.getMapWidth()) {
            // System.out.println("x pos = :" + xpos);
            path = "sprites/map/floor_" + counter + ".png";
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
        String path = "sprites/map/platform.png";
        double distanceBetweenPlatforms = 20;        
        
        int xpos = 0;
        int counter = 0;
        double s = map.getMapHeight()*0.62;
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
                    //player.stopWalkAnimation();
                    //player.jumpAnimate();
                    //player.stopAnimate();
                    player.setJumpingForce(30);
                    player.setIsJumping(true);
            }
            if(e.getCode() == KeyCode.S){
                    System.out.println("this hapenned");
                    player.goToBottom();
            }
            if(e.getCode() == KeyCode.UP){
                double a = toAnimate.getTranslateY();
                a--;
                a--;
                toAnimate.setTranslateY(a);
                String txt = "X:"+toAnimate.getTranslateX()+"   Y:"+toAnimate.getTranslateY();
                lbl.setText(txt);
            }
            if(e.getCode() == KeyCode.DOWN){
                double a = toAnimate.getTranslateY();
                a++;
                a++;
                toAnimate.setTranslateY(a);
                String txt = "X:"+toAnimate.getTranslateX()+"   Y:"+toAnimate.getTranslateY();
                lbl.setText(txt);
            }
            if(e.getCode() == KeyCode.LEFT){
                double a = toAnimate.getTranslateX();
                a--;
                a--;
                toAnimate.setTranslateX(a);
                String txt = "X:"+toAnimate.getTranslateX()+"   Y:"+toAnimate.getTranslateY();
                lbl.setText(txt);
            }
            if(e.getCode() == KeyCode.RIGHT){
                double a = toAnimate.getTranslateX();
                a++;
                a++;
                toAnimate.setTranslateX(a);
                String txt = "X:"+toAnimate.getTranslateX()+"   Y:"+toAnimate.getTranslateY();
                lbl.setText(txt);
            }
            
            

        }
    }    
    
    private class KeyReleasedController implements EventHandler<KeyEvent> {

        @Override
        public void handle(KeyEvent e) {
            
        }
    }
}
