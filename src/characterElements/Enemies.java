/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package characterElements;

import GameGUI.Map;
import items.Bullet;
import items.Custom;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import obstacles.Obstacles;

/**
 *
 * @author 15148
 */

public class Enemies extends ImageView{
    //Note that the enemy is first a rectangle representing its hitbox. 
    //We then add images corresponding to the different parts of the player inside
    //of that player. 
    
    //images 
    String path = "sprites/monster/animations/monster_animation/monster_animation0.png";
    private Image image, imageAfter;
    private final ArrayList<Image> animationWalking = new ArrayList<>();
    private ArrayList<Image> animationDying = new ArrayList<>();
    
    private int animateSwitchTimer = 0;
    private int imageCounter = 1;
    
    private int animationDyingTimer = 0;
    private int imageDyingCounter = 0;
    
    //Shape and size of the enemies. 
    private final double width;
    private final double height;
    
    //positioning 
    private double xpos , ypos;
    private double velocity;
    
    //conditions 
    private boolean isDead = false;
    private boolean isDyingDone = false;
    
    //attributes controller when and how he is shooting.
    private int timer = 0;
    private double chancesToShoot = 0;
    List<Bullet> ammo = new LinkedList<>();
    
    
    public Enemies(Obstacles ground , double initialXpos , double initialYpos){
        image = new Image(path);
        imageAfter = new Image(path, image.getWidth()*2, image.getHeight()*2, false, true);
        setImage(imageAfter);
        
        setupImages();
        
        width = image.getWidth();
        height = image.getHeight();        
        velocity = ground.getVelocity();
        
        this.xpos = initialXpos + ground.getWidth()/2.5;
        if(ground.isFloor()) this.ypos = initialYpos - height;
        else this.ypos = initialYpos - height;
 
        
        velocity = ground.getVelocity();       
        setTranslateX(xpos);
        setTranslateY(ypos);
    }
    
    public void update(Map map){
        if (!isDead){
            xpos += velocity;
            if (xpos + width <= 0) {
                isDead = true;
            }
            shoot(map);
            setTranslateX(xpos);
            for(int i = 0; i < ammo.size(); i++){
                if(ammo.get(i).getIsDead()){
                    ammo.remove(i);
                }
            }            
            updateImagesWalking();
        }
    }
    
    private void setupImages(){
        for (int i = 0; i < 60 ; i++) {
            if (i < 10) {
                animationWalking.add(new Image("sprites/monster/animations/monster_animation/monster_animation" + i + ".png", width , height , false , true));
            } else {
                animationWalking.add(new Image("sprites/monster/animations/monster_animation/monster_animation" + i + ".png" , width , height , false , true));
            }
        }
        for (int i = 0; i < 36 ; i++) {
            animationDying.add(new Image("sprites/monster/animations/monster_deathv2/monster_death"+ i +".png", width , height , false , true));
        }
    }
    
    private void updateImagesWalking(){
        animateSwitchTimer++;
        if(animateSwitchTimer > 2){
            if(imageCounter >= 59) imageCounter = 0;
            animateSwitchTimer = 0;
            setImage(animationWalking.get(imageCounter));
            imageCounter++;
        }
    }
    
    public void updateImagesDying(Map map, Obstacles obstacle){
        animationDyingTimer++;
        if(animationDyingTimer > 1){
            animationDyingTimer = 0;
            if(imageDyingCounter >= 34){
                map.removeElement(this);
                obstacle.setE(null);
                isDyingDone = false;
                imageDyingCounter = 34;
                animationDyingTimer = 0;  
            }else{
                setImage(animationDying.get(imageDyingCounter));
                imageDyingCounter++;
            }            
            
        } 
    }
    
    public void die(){      
        isDead = true;
        isDyingDone = true;
    }
    
    public void shoot(Map map){
        timer++;
        if(timer >= 40){
            timer = 0;
            chancesToShoot = Math.random()*100 + 1;
            if(chancesToShoot <= 20 ){
                Bullet b = new Bullet("pistol", getTranslateX(), getTranslateY() + width/2, 0, 2, Custom.c1, this);
                ammo.add(b); 
                map.insertElement(b);
            }
        }
    }
    
    public double getXpos() {
        return xpos;
    }

    public void setXpos(double xpos) {
        this.xpos = xpos;
    }

    public double getYpos() {
        return ypos;
    }

    public void setYpos(double ypos) {
        this.ypos = ypos;
    }

    public double getVelocity() {
        return velocity;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    public boolean IsDead() {
        return isDead;
    }

    public void setIsDead(boolean isDead) {
        this.isDead = isDead;
    }

    public boolean isIsDyingDone() {
        return isDyingDone;
    }

    public void setIsDyingDone(boolean isDyingDone) {
        this.isDyingDone = isDyingDone;
    }
    
}