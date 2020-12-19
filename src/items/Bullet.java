/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package items;

import Data.DataController;
import GameController.Difficulty;
import javafx.scene.Node;
import characterElements.Player;

/**
 *
 * @author ismai
 */
public class Bullet extends InGameItems implements ISearchablePath{
    //private final double width, height;
    private int velocity;
    private boolean isDead;
    private double width;
    private String kind;
   
    private Node fromWho;
    public Bullet(String kind, double x, double y, double scale, Custom custom , Node fromWho) {
        super(x, y, scale, custom);
        super.type = ItemType.bullet;
        this.kind = kind;
        String path = findPath();
        super.initializeImage(path);
        
        width = imageAfter.getWidth();
        this.fromWho = fromWho;
        
        if(this.fromWho instanceof Player) velocity = 7;
        else velocity = 11;
        
        if(DataController.getDifficulty() == Difficulty.NORMAL) velocity *= 1.5;
        if(DataController.getDifficulty() == Difficulty.HARD) velocity *= 2;
    }
    
    public void update(double mapWidth, String direction){
        if(!isDead){
            if(direction.equalsIgnoreCase("left")) xpos -= velocity;
            if(direction.equalsIgnoreCase("right")) xpos += velocity; 
            if(xpos >= mapWidth) isDead = true;
            if(xpos + width <= 0) isDead = true;
            setTranslateX(xpos);
            setTranslateY(ypos);     
        }
    }
    
    @Override
    public String findPath(){
        String path = "";
        switch(custom){
            case normal:
                path = "sprites/guns/bullets/bullet_"+kind+".png";
                break;
            case c1:
                path = "sprites/guns/bullets/bullet_"+kind+"_c1.png";
                break;
        }
        return path;
    }
    
    public void setKind(String kind){
        this.kind = kind;
        String path = findPath();
        super.initializeImage(path);
    }
        
    public void setIsDead(boolean isDead){
        this.isDead = isDead;
    }
    
    public boolean getIsDead(){
        return isDead;
    }
    
    public Node getFromWho(){
        return fromWho;
    }
    
    
    
    
    
    
    
}
