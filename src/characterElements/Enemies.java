/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package characterElements;

import GameGUI.Map;
import items.Bullet;
import items.Custom;
import java.util.LinkedList;
import java.util.List;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import obstacles.Obstacles;

/**
 *
 * @author 15148
 */
public class Enemies extends Rectangle{
    //Note that the enemy is first a rectangle representing its hitbox. 
    //We then add images corresponding to the different parts of the player inside
    //of that player. 
    
    //Shape and size of the enemies. 
    private final double width = 100;
    private final double height = 200;
    
    //positioning 
    private double xpos , ypos;
    private double velocity;
    
    //conditions 
    private boolean isDead = false;
    
    //attributes controller when and how he is shooting.
    private int timer = 0;
    private double chancesToShoot = 0;
    List<Bullet> ammo = new LinkedList<>();
    
    
    public Enemies(Obstacles ground , double initialXpos , double initialYpos){
        setWidth(width); setHeight(height); setFill(Color.RED);
        velocity = ground.getVelocity();
        this.xpos = initialXpos + (ground.getWidth() / 2);
        this.ypos = initialYpos - height;
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
        } else {
            map.getChildren().remove(this);
        }
    }
    
    public void die(Map map){      
        isDead = true;
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
}