/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameController;

import obstacles.*;
import GameGUI.Map;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;

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
    List<Obstacles> floors = new ArrayList<>();
    List<Obstacles> platforms = new ArrayList<>();
    
    //the two classes sbackgrounds and ceilings have their own 
    List<BackgroundsParent> backgrounds = new ArrayList<>();
    List<BackgroundsParent> ceilings = new ArrayList<>();

    
    
    public GameController(Map map){
        this.map = map;
        createBackground();
        createFloors();
        createPlatforms();
        createCeilings();
       
        
        map.addAllBackgrounds(backgrounds);
        map.addAllBackgrounds(ceilings);
        map.addAllElements(floors);
        map.addAllElements(platforms);
        
        
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
            }
        };
        timer.start();
    }

    private void update(){
        updateMapSprites();
        
    }
    
    private void updateMapSprites(){
        for(Obstacles o : getAllObstaclesInMap()){
            o.update(map);
        }
        for(BackgroundsParent b : getAllBackgroundsInMap()){
            b.update(map);
        }
  
    }
    
    //The next two methods are for extrating the obstacles and backgrounds separately from the children
    //of our map
    private ArrayList<Obstacles> getAllObstaclesInMap(){
        ArrayList<Obstacles> list = new ArrayList<>();
        
        for(Node n : map.getChildren()){
            if(n instanceof Obstacles){
                list.add((Obstacles)n);
            }
        }
        
        return list;
    }
    
    private ArrayList<BackgroundsParent> getAllBackgroundsInMap(){
        ArrayList<BackgroundsParent> list = new ArrayList<>();
        
        for(Node n : map.getChildren()){
            if(n instanceof BackgroundsParent){
                list.add((BackgroundsParent)n);
            }
        }
        
        return list;
    }    
    
    //the next four methods are only here to create the map's grphical elements. 
    private void createBackground(){
        int xpos = 0;
        while(xpos <= map.getMapWidth()){
            Background b = new Background(xpos, 0 , "bg");
            backgrounds.add(b);
            xpos += b.getWidth();
        }
        //adding one more background to fill up the empty space created by the while loop
        Background b = new Background(xpos, 0, "bg");
        backgrounds.add(b);
    }
    
    private void createFloors(){
        int xpos = 0;
        int counter = 1;
        String path = "";
        
        while(xpos <= map.getMapWidth()){
           // System.out.println("x pos = :" + xpos);
            path = "sprites/Map/floor_" + counter + ".png";
            Floor f = new Floor(path, xpos, 400 , "floor");
            f.setTranslateY(920);
            floors.add(f);
            if(counter == 3) counter = 1; 
            else counter++;
            xpos += f.getWidth();
        }
    }
    
    private void createPlatforms(){
        String path = "sprites/Map/platform.png";
        double distanceBetweenPlatforms = 0; 
        
        int xpos = 0;
        int counter = 0;
        while(xpos <= map.getMapWidth()*2){
            Platforms p = new Platforms(path, 500 + distanceBetweenPlatforms, Math.random() * (-200) + 600, "platform");
            distanceBetweenPlatforms += p.getWidth() + (Math.random() * (40));
            platforms.add(p);
            xpos += distanceBetweenPlatforms;
        }
    }
    
    private void createCeilings(){
        double xpos = 0;

        while(xpos <= map.getMapWidth()){
            Ceiling ceiling = new Ceiling(xpos, 0 , "ceiling");
            ceilings.add(ceiling);
            xpos += ceiling.getWidth();
        }
        
        //creating one more ceiling to fill up the empty space created by the while loop. 
        Ceiling ceiling = new Ceiling(xpos, 0 , "ceiling");
        ceilings.add(ceiling);       
    }
 

    //getters for the key controllers : 
    public KeyPressedController getKeyPressedController(){
        return new KeyPressedController();
    }
    
    public KeyReleasedController getKeyReleasedController(){
        return new KeyReleasedController();
    }
    
    
    //These classes are event handlers for whenever we press specific buttons 
    // This is where we will create every key binds that our player will need in order to play the game. 
    private class KeyPressedController implements EventHandler<KeyEvent>{
        @Override
        public void handle(KeyEvent e) {
            
        }
    }  
    
    private class KeyReleasedController implements EventHandler<KeyEvent>{
        @Override
        public void handle(KeyEvent e){
            
        }
    }
}

