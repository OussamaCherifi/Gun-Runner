/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obstacles;

import GameGUI.Map;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author 15148
 */
import GameGUI.Map;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author 15148
 */
public class Crates extends ImageView{

    private final String path = "sprites/Map/crate.png";
    private double xpos, ypos;
    private double width, height;
    private double heightAboveGround;
    
    private final Image image, imageAfter;
    private double velocity;
    
    //conditions 
    private boolean isDead = false;
    private int gunLuck = 0;
    
    public Crates(Obstacles ground, double InitialXpos , double initialYpos){
        velocity = ground.getVelocity();
        image = new Image(path);
        imageAfter = new Image(path, image.getWidth() * 2, image.getHeight() * 2, false, true);
        setImage(imageAfter);
        
        width = imageAfter.getWidth();
        height = imageAfter.getHeight();
        
        if(ground instanceof Platforms) heightAboveGround = 250 + height;
        else heightAboveGround = height;
        
        this.xpos = InitialXpos + (ground.getWidth() / 2);
        this.ypos = initialYpos - heightAboveGround;
        setTranslateX(xpos);
        setTranslateY(ypos);
    }
    
    public void update(Map map){
        if (!isDead) {
            xpos += velocity;
            if (xpos + width <= 0) {
                isDead = true;
            }
            setTranslateX(xpos);
        } else {
            map.getChildren().remove(this);
        }
    }
    

    public int getGun(){
        gunLuck = (int)(Math.random() * 2 + 1);
        return gunLuck;
    }
    
    public void die(){
        isDead = true;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getHeightAboveGround() {
        return heightAboveGround;
    }

    public void setHeightAboveGround(double heightAboveGround) {
        this.heightAboveGround = heightAboveGround;
    }

    public double getVelocity() {
        return velocity;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setIsDead(boolean isDead) {
        this.isDead = isDead;
    }
        
}
