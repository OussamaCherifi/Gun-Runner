/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package player;

import playerAnimation.JumpingAnimation;
import playerAnimation.WalkingAnimation;
import items.*;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.PathTransition;
import javafx.animation.SequentialTransition;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import obstacles.Obstacles;

/**
 *
 * @author 15148
 */
public class Player extends Rectangle{
    
    // Separation of the attributes 
    private Item helmet,torso, rHand, lHand, rBoot, lBoot, bullet, lGun, rGun, fingers;
    private PathTransition  torsoTransition, helmetTransition, rbootTransition, lbootTransition,
                            rhandTransition, lhandTransition, fingersTransition, pistol1Transition, pistol2Transition;
    private ArrayList<Item> equipedItems = new ArrayList<>();
    //shape and size;
    private final double width = 110 ;
    private final double height = 168;

    // positioning and movement
    private double xpos , ypos , rightX , lowerY;
    private double jumpingForce, fallingForce;
    private double originalX, originalY;
    
    //constraint attributes to control movements 
    private boolean isInTheAir = false;
    private boolean isJumping = false;
    private boolean isFalling = false;
    private boolean isDead = false;
    private boolean isShooting = false;
    private boolean isGoingBottom = false;  
    private boolean isAlreadyRunning = false;
    private boolean ifDone = false;
    
    JumpingAnimation ja = new JumpingAnimation(this);
    

    
    //These two are two control the y position of the player. 
    //They represent the sprites on which my player can stand on.
    private Obstacles ground;
    //the main ground is referred to as the base line of our player. He cannot go under 
    // the y position of this Obstacle
    private final Obstacles mainGround;
    
    public Player(Obstacles ground){
        setWidth(width); setHeight(height); setFill(Color.BLUE);
        this.ground = ground;
        mainGround = ground;
        
        System.out.println("Ground: " + ground.getYpos());
        //getting the four corners of the rectangles as coordinates. 
        xpos = 180;
        ypos = (ground.getYpos() - height);
        rightX = xpos + width;
        lowerY = ypos + height;
        
        setTranslateX(xpos);
        setTranslateY(ypos);
        
        this.setVisible(true);
        this.setOpacity(0.5);
        
        
    }



    public void addEquipedItems(){
        equipedItems.add(helmet);
        equipedItems.add(torso);
        equipedItems.add(rHand);
        equipedItems.add(lHand);
        equipedItems.add(rBoot);
        equipedItems.add(lBoot);
        equipedItems.add(lGun);//
        equipedItems.add(rGun);//
        equipedItems.add(fingers);//
    }
    
    public void update(List<Obstacles> obstacles){
        rightX = xpos + width;
        lowerY = ypos + height;
        ypos = getTranslateY();
        
        //Movements Handling
        if (isJumping == true) {
            jump();
        }
        if (isFalling == true) {
            fall(obstacles);
        }
        if(isInTheAir){
            //updateItems();
            //jumpAnimate();
            
        }
        isFalling = true;
        
    }
    
    private void updateItems(){
        //System.out.println("updating...");
        double a = lowerY - mainGround.getYpos();
        double b = ground.getYpos();
        //System.out.println(a+" = "+lowerY+" - "+mainGround.getYpos());
        
            for(Item it : equipedItems){
                //System.out.println("lowerY: "+ lowerY);
                //System.out.println("current ground: "+ b);
                //System.out.println("Item's Y: "+ it.getYpos());

                    it.setYpos(it.getOriginalY()+a);
                    it.setTranslateY(it.getOriginalY()+a);
                
//              System.out.println(it.getOriginalX());
//              System.out.println(it.getXpos());
                
            }  
    }
    
    //The next methods will be related to the player movement : 
    public void jump(){
        //isAlreadyRunning = false;
        isInTheAir = true;
        isFalling = false;
        setTranslateY(getTranslateY() - jumpingForce);
        jumpingForce -= 0.90;
        if (jumpingForce <= 0) {
            fallingForce = 0;
            isFalling = true;
        }
    }
    
    public void fall(List<Obstacles> obstacles){
        setTranslateY(getTranslateY() - fallingForce);
        fallingForce -= 1;
        
        
        if(isGoingBottom == false){
            for (Obstacles o : obstacles) {
                if (xpos <= o.getTranslateX() + o.getWidth() && rightX >= o.getTranslateX()) {
                    if (ypos >= o.getYpos() - height && lowerY <= o.getYpos()){
                        
                        fallingForce = 0;
                        isJumping = false;
                        isFalling = false;
                        isInTheAir = false;
                        if(ground.equals(o)){
                            //System.out.println("allo");
                            updateItems();
                        }
                        if(!ground.equals(o)){
                            walkAnimate(0, 0);
                        }
                        ground = o;
                        setTranslateY(o.getTranslateY() - height);
                        break;
                    }
                }
            }            
        }else{
            if(ground.equals(mainGround)){
               walkAnimate(0, 0);
               updateItems();
            }
            ground = mainGround;
                if (ypos >= ground.getYpos() - height && lowerY <= ground.getYpos()) {
                    fallingForce = 0;
                    isJumping = false;
                    isFalling = false;
                    isGoingBottom = false;
                    setTranslateY(ground.getTranslateY() - height);
                    isInTheAir = false;
                }
            }
    }
    
    public void jump2(){
        ja.handJump((Hand)lHand);
    }
    
    public void stopAnimate(){
        Gun gun = (Gun)rGun;
        if(gun.getIsDualWield()){
            fingersTransition.stop();
            rhandTransition.stop();
            lhandTransition.stop();
            pistol2Transition.stop();
            pistol1Transition.stop();
        }if(!gun.getIsDualWield()){
            pistol1Transition.stop();
            fingersTransition.stop();
            rhandTransition.stop();
        }
        torsoTransition.stop();
        helmetTransition.stop();
        rbootTransition.stop();
        lbootTransition.stop();
       
    }
    
    public void walkAnimate(double x, double y){
        System.out.println("walking...");
        
        updateItems();
        
        setupWalkItems();
        
            this.isAlreadyRunning = true;
            torsoTransition = WalkingAnimation.torsoPath(torso, x, y);
            helmetTransition = WalkingAnimation.helmetPath((Helmet)helmet, x, y);
            rbootTransition = WalkingAnimation.bootPath((Boot)rBoot, x, y, 46);
            lbootTransition = WalkingAnimation.bootPath((Boot)lBoot, x, y, 46);
            
            Gun gun = (Gun)rGun;
            if(gun.getIsDualWield()){
                lGun.setVisible(true);
                lHand.setVisible(true);
                rhandTransition = WalkingAnimation.handPath((Hand)rHand, x, y);
                fingersTransition = WalkingAnimation.fingersPath((Fingers) fingers, x, y);
                lhandTransition = WalkingAnimation.handPath((Hand)lHand, x, y);
                pistol1Transition = WalkingAnimation.gunPath((Gun) rGun, x, y);
                pistol2Transition = WalkingAnimation.gunPath((Gun) lGun, x, y);
                fingersTransition.play();
                rhandTransition.play();
                lhandTransition.play();
                pistol2Transition.play();
                pistol1Transition.play();
            }if(!gun.getIsDualWield()){
                lGun.setVisible(false);
                lHand.setVisible(false);
                Fingers f = (Fingers)fingers;
                f.setKind("single");
                fingers = (Item)f;
                rhandTransition = WalkingAnimation.handPath((Hand)rHand, x+8, y+16);
                pistol1Transition = WalkingAnimation.gunPath((Gun)rGun, x+8, y+20);
                fingersTransition = WalkingAnimation.fingersPath((Fingers) fingers, x+2, y);
                pistol1Transition.play();
                fingersTransition.play();
                rhandTransition.play();
                        
            }
            
            helmetTransition.play();
            
            torsoTransition.play();
            rbootTransition.play();
            lbootTransition.play();
            
            
    }
    
    public void jumpAnimate(){
        setupJumpItems();
        SequentialTransition helmetTransition = JumpingAnimation.helmetPath((Helmet)helmet);
        SequentialTransition rhandTransition = JumpingAnimation.handPath((Hand)rHand);
        SequentialTransition lhandTransition = JumpingAnimation.handPath((Hand)lHand);
        SequentialTransition torsoTransition = JumpingAnimation.torsoPath((Torso)torso);
        SequentialTransition lbootTransition = JumpingAnimation.bootPath((Boot)lBoot);
        SequentialTransition rbootTransition = JumpingAnimation.bootPath((Boot)rBoot);
        
        torsoTransition.play();
        lhandTransition.play();
        rhandTransition.play();
        helmetTransition.play();
        lbootTransition.play();
        rbootTransition.play();
        
        //System.out.println("jumping...");
    }
    
    private void setupJumpItems(){
        this.rHand.setRotate(90);
        Hand newHand = (Hand)lHand;
        newHand.setKind("whole");
        this.lHand = newHand;
        this.fingers.setVisible(false);
        this.lHand.setVisible(true);
        this.lHand.setRotate(-90);
        this.lGun.setVisible(false);
        this.rGun.setVisible(false);
        this.lBoot.setRotate(-30);
        this.rBoot.setRotate(30);
    }
    
    private void setupWalkItems(){
        this.rHand.setRotate(0);
        Hand newHand = (Hand)lHand;
        newHand.setKind("whole");
        this.lHand = newHand;
        this.fingers.setVisible(true);
        this.lHand.setVisible(false);
        this.lHand.setRotate(0);
        this.lGun.setVisible(false);
        this.rGun.setVisible(true);
        this.lBoot.setRotate(0);
        this.rBoot.setRotate(0);
    }
    
    public void goToBottom(){
        isGoingBottom = true;
    }

    public double getJumpingForce() {
        return jumpingForce;
    }

    public void setJumpingForce(double jumpingForce) {
        this.jumpingForce = jumpingForce;
    }

    public double getFallingForce() {
        return fallingForce;
    }

    public void setFallingForce(double fallingForce) {
        this.fallingForce = fallingForce;
    }

    public boolean getIsJumping() {
        return isJumping;
    }

    public void setIsJumping(boolean isJumping) {
        this.isJumping = isJumping;
    }

    public boolean getIsFalling() {
        return isFalling;
    }

    public void setIsFalling(boolean isFalling) {
        this.isFalling = isFalling;
    }

    public boolean getIsDead() {
        return isDead;
    }

    public void setIsDead(boolean isDead) {
        this.isDead = isDead;
    }

    public boolean getIsShooting() {
        return isShooting;
    }

    public void setIsShooting(boolean isShooting) {
        this.isShooting = isShooting;
    }

    public boolean getIsGoingBottom() {
        return isGoingBottom;
    }

    public void setIsGoingBottom(boolean isGoingBottom) {
        this.isGoingBottom = isGoingBottom;
    }

    public Item getHelmet() {
        return helmet;
    }

    public Item getTorso() {
        return torso;
    }

    public Item getBullet() {
        return bullet;
    }

    public Item getrHand() {
        return rHand;
    }

    public Item getlHand() {
        return lHand;
    }

    public Item getrBoot() {
        return rBoot;
    }

    public Item getlBoot() {
        return lBoot;
    }

    public void setHelmet(Item Helmet) {
        this.helmet = Helmet;
    }

    public void setTorso(Item Torso) {
        this.torso = Torso;
    }

    public void setrHand(Item rHand) {
        this.rHand = rHand;
    }

    public void setlHand(Item lHand) {
        this.lHand = lHand;
    }

    public void setrBoot(Item rBoot) {
        this.rBoot = rBoot;
    }

    public void setlBoot(Item lBoot) {
        this.lBoot = lBoot;
    }

    public boolean getIsAlreadyRunning() {
        return isAlreadyRunning;
    }

    public void setIsAlreadyRunning(boolean isAlreadyRunning) {
        this.isAlreadyRunning = isAlreadyRunning;
    }

    public double getXpos() {
        return xpos;
    }

    public double getYpos() {
        return ypos;
    }

    public Item getFingers() {
        return fingers;
    }

    public void setFingers(Item fingers) {
        this.fingers = fingers;
    }

    public Item getlGun() {
        return lGun;
    }

    public void setlGun(Item lGun) {
        this.lGun = lGun;
    }

    public Item getrGun() {
        return rGun;
    }

    public void setrGun(Item rGun) {
        this.rGun = rGun;
    }
    
    public Obstacles getGround() {
        return ground;
    }

    public double getLowerY() {
        return lowerY;
    }

    public boolean getIsInTheAir() {
        return isInTheAir;
    }
    
    
    
    
    
    
    


    

}
