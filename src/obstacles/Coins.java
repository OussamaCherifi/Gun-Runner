/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obstacles;

import Data.DataController;
import GameController.Difficulty;
import GameGUI.Map;
import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author 15148
 */
public class Coins extends ImageView{
    
    private String path = "coin/coin_1.png";
    private double xpos, ypos;
    private double width, height;
    private double heightAboveGround;
    
    ArrayList<Image> animationImages = new ArrayList<>();
    
    private final Image image, imageAfter;
    private double velocity;
    
    private boolean isDead = false;
    
    private int animateSwitchTimer = 0;
    private int imageCounter = 1;
    
    public Coins(Obstacles ground, double InitialXpos, double initialYpos) {
        velocity = ground.getVelocity();
        image = new Image(path);
        imageAfter = new Image(path, image.getWidth()*0.75, image.getHeight()*0.75, false, true);
        setImage(imageAfter);
        setImages();
        
        width = imageAfter.getWidth();
        height = imageAfter.getHeight();
        
        if(ground instanceof Platforms) heightAboveGround = 250 + height;
        else heightAboveGround = height + 60;
        
        this.xpos = InitialXpos + (ground.getWidth() / 2);
        this.ypos = initialYpos - heightAboveGround;
        setTranslateX(xpos);
        setTranslateY(ypos);
    }
    
    private void setImages(){
        for (int i = 1; i < 16; i++) {
            if (i < 10) {
                animationImages.add(new Image("coin/coin_" + i + ".png", width , height , false , true));
            } else {
                animationImages.add(new Image("coin/coin_" + i + ".png" , width , height , false , true));
            }
        }
    }
    
    public void update(Map map){
        if (!isDead) {
            xpos += velocity;
            if (xpos + width <= 0) {
                isDead = true;
            }
            setTranslateX(xpos);
            updateImages();
        } else {
            map.getChildren().remove(this);
        }
    }
    
    private void updateImages(){
        animateSwitchTimer++;
        if(animateSwitchTimer > 2){
            if(imageCounter >= 15) imageCounter = 0;
            animateSwitchTimer = 0;
            setImage(animationImages.get(imageCounter));
            imageCounter++;
        }
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
