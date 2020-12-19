/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package characterElements;

import Data.DataController;
import GameGUI.Map;
import playerAnimation.JumpingAnimation;
import playerAnimation.WalkingAnimation;
import items.*;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.util.Duration;
import obstacles.Obstacles;
import playerAnimation.CustomInterpolator;
import playerAnimation.FallAnimation;

/**
 *
 * @author 15148
 */
public class Player extends Rectangle {

    // Separation of the attributes 
    private InGameItems helmet, torso, rHand, lHand, rBoot, lBoot, bullet, lGun, rGun, fingers;
    private ArrayList<InGameItems> equipedItems = new ArrayList<>();
    
    //Check if it's a player preview
    private boolean ifPreview;

    //health
    private double health = 5;

    //shape and size;
    private final double width = 110;
    private final double height = 168;

    // positioning and movement
    private double xpos, ypos, rightX, lowerY;
    private double jumpingForce, fallingForce;

    //constraint attributes to control movements 
    private boolean isInTheAir = false;
    private boolean isJumping = false;
    private boolean isFalling = false;
    private boolean isShooting = false;
    private boolean isGoingBottom = false;
    private boolean isAlreadyRunning = false;
    private boolean isReloading = false;
    private boolean hasSpecialGun = false;

    //Reload
    private boolean timerStarted = false;
    private double timeAtReload = 0;
    private double tDifference = 0;

    JumpingAnimation ja = new JumpingAnimation(this);
    FallAnimation fa = new FallAnimation(this);
    private int specialCounter = 0;
    double reloadTime = 0;

    private Arc reloadArc;

    //ammo
    List<Bullet> ammo = new ArrayList<>();
    private int numberOfTimesHeShot = 0;
    private int kills = 0;
    //These two are two control the y position of the player. 
    //They represent the sprites on which my player can stand on.
    private Obstacles currentGround, previousGround;
    //the main ground is referred to as the base line of our player. He cannot go under 
    // the y position of this Obstacle
    private Obstacles mainGround;

    public Player(Obstacles ground) {
        setWidth(width);
        setHeight(height);
        setFill(Color.BLUE);
        
        this.ifPreview = false;
        this.previousGround = ground;
        mainGround = ground;
        this.currentGround = mainGround;
        //getting the four corners of the rectangles as coordinates. 
        xpos = 180;
        ypos = (ground.getYpos() - height);
        rightX = xpos + width;
        lowerY = ypos + height;

        setTranslateX(xpos);
        setTranslateY(ypos);

        initializeArc();
        this.setVisible(false);
    }

    public Player(double y) {
        setWidth(width);
        setHeight(height);
        setFill(Color.RED);

        this.ifPreview = true;
        xpos = 54;
        ypos = (y - height);
        rightX = xpos + width;
        lowerY = ypos + height;

        setTranslateX(xpos);
        setTranslateY(ypos);

        this.setVisible(false);
    }

    public void addEquipedItems() {
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

    public void update(List<Obstacles> obstacles, double mapWidth, double timeElapsed) {
        //movement 
        movementHanadling(obstacles);
        //Fix bugs
        fixBug();
        //ammo handling
        ammoHandling(mapWidth, timeElapsed);
    }

    private void movementHanadling(List<Obstacles> obstacles) {
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
        if (isInTheAir) {
            jumpAnimate();
        }
        if (xpos >= currentGround.getXpos() + currentGround.getWidth() && !isInTheAir) {
            fallAnimate();
        }
        if (currentGround.equals(mainGround) && lowerY < mainGround.getYpos() && !isInTheAir) {
            fallAnimate();
        }
        isFalling = true;
    }

    private void ammoHandling(double mapWidth, double timeElapsed) {
        //bullet handling
        for (Bullet b : ammo) {
            b.update(mapWidth, "right");
        }

        //reload
        reload(timeElapsed);
    }

    private void reload(double timeElapsed) {
        if (isReloading == true) {
            numberOfTimesHeShot = 0;
            if (!timerStarted) {
                reloadAnimate();
                timerStarted = true;
                timeAtReload = timeElapsed;
            }
            tDifference = timeElapsed;

            for (int i = 0; i < ammo.size(); i++) {
                if (ammo.get(i).getIsDead()) {
                    ammo.remove(i);
                }
            }
            tDifference = timeElapsed - timeAtReload;
            if (tDifference >= 1.5) {
                tDifference = 0;
                timeAtReload = 0;
                timerStarted = false;
                isReloading = false;
            }
        }
    }

    private void initializeArc() {
        reloadArc = new Arc(1754, 208, 100, 100, 90, 0);
        reloadArc.setFill(Color.TRANSPARENT);
        reloadArc.setStroke(Color.valueOf("#75c7c7"));
        reloadArc.setStrokeType(StrokeType.OUTSIDE);
        reloadArc.setStrokeWidth(12);
        reloadArc.setLength(0);
    }

    private void fixBug() {
        if (lowerY == currentGround.getYpos() && !rGun.isVisible() && rGun.getKind().equalsIgnoreCase("pistol")) {
            rGun.setVisible(true);
        }
        if (lowerY == mainGround.getYpos() || lowerY == currentGround.getYpos()) {
            if (rGun.getKind().equalsIgnoreCase("ak") && lHand.isVisible()) {
                lHand.setVisible(false);
            }
            if (!lGun.isVisible() && !rGun.getKind().equalsIgnoreCase("ak")) {
                walkAnimate(0, 0);
            }
            if (lBoot.getRotate() == -40 || lHand.getRotate() == -95) {
                walkAnimate(0, 0);
            }
        }
    }

    private void updateItems() {
        double a;
        if (ypos == 232) { //Only useful when animating from the preview
            a = ypos - 140;
        } else {
            a = mainGround.getYpos() - currentGround.getYpos();
        }
        for (InGameItems it : equipedItems) {
            it.setYpos(it.getOriginalY() - a);
            it.setTranslateY(it.getOriginalY() + a);
        }
    }

    //The next methods will be related to the player movement : 
    public void jump() {
        isInTheAir = true;
        isFalling = false;
        setTranslateY(getTranslateY() - jumpingForce);
        jumpingForce -= 0.90;
        if (jumpingForce <= 0) {
            fallingForce = 0;
            isFalling = true;
        }
    }

    public void fall(List<Obstacles> obstacles) {
        setTranslateY(getTranslateY() - fallingForce);
        fallingForce -= 0.8;

        if (isGoingBottom == false) {
            for (Obstacles o : obstacles) {
                if (xpos <= o.getTranslateX() + o.getWidth() && rightX >= o.getTranslateX()) {
                    if (ypos >= o.getYpos() - height && lowerY <= o.getYpos()) {
                        fallingForce = 0;
                        isJumping = false;
                        isFalling = false;
                        isInTheAir = false;

                        if (!currentGround.equals(o)) {
                            previousGround = currentGround;
                            if (previousGround.getYpos() == mainGround.getYpos() && o.getYpos() == mainGround.getYpos()) {
                                setupWalkItems();
                                currentGround = o;
                            } else if (previousGround.getYpos() == o.getYpos()) {
                                setupWalkItems();
                                currentGround = o;
                            } else {
                                currentGround = o;
                                walkAnimate(0, 0);
                            }
                        }
                        setTranslateY(o.getTranslateY() - height);
                        break;
                    }
                }
            }
        } else {
            if (currentGround.equals(mainGround)) {
                walkAnimate(0, 0);
            }
            currentGround = mainGround;
            if (ypos >= currentGround.getYpos() - height && lowerY <= currentGround.getYpos()) {
                fallingForce = 0;
                isJumping = false;
                isFalling = false;
                isGoingBottom = false;
                setTranslateY(currentGround.getTranslateY() - height);
                isInTheAir = false;
            }
        }
    }

    public boolean shoot(Map map) {
        if (!isReloading) {
            if (!hasSpecialGun) {
                numberOfTimesHeShot++;
                if (numberOfTimesHeShot <= 5) {
                    chooseWeaponToShoot(map);
                } else {
                    isReloading = true;
                }
            } else {
                int specialGunBullets;
                if (rGun.getKind().equalsIgnoreCase("ak")) {
                    specialGunBullets = 40;
                    specialCounter++;
                } else {
                    specialGunBullets = 20;
                    specialCounter += 2;
                }

                if (specialCounter <= specialGunBullets) {
                    chooseWeaponToShoot(map);
                    return false;
                } else {
                    specialCounter = 0;
                    isReloading = true;
                    hasSpecialGun = false;
                    return true;
                }
            }
        }
        return false;
    }

    private void chooseWeaponToShoot(Map map) {
        double r = Math.round(Math.random() * -20 + 10);
        if (rGun.getKind().equalsIgnoreCase("pistol") || rGun.getKind().equalsIgnoreCase("uzi")) {
            //right bullet

            Bullet rb = new Bullet(rGun.getKind(), rGun.getXpos() + 28, getTranslateY() + height / 2.8 + r + 8, 2, DataController.chooseBullets(), this);
            //left bullet
            Bullet lb = new Bullet(lGun.getKind(), lGun.getXpos() + 28, getTranslateY() + height / 2.8 + r - 8, 2, DataController.chooseBullets(), this);

            map.insertElement(rb);
            map.insertElement(lb);
            ammo.add(rb);
            ammo.add(lb);
        } else {
            Bullet b = new Bullet(rGun.getKind(), getTranslateX() + width, getTranslateY() + height / 2.8 + 16 + r, 1.5, DataController.chooseBullets(), this);
            map.insertElement(b);
            ammo.add(b);
        }
    }

    public void BulletImpact(List<Enemies> enemies, List<Obstacles> obstacles) {
        for (Bullet b : ammo) {
            for (Obstacles o : obstacles) {
                if (b.getBoundsInParent().intersects(o.getBoundsInParent())) {
                    b.setTranslateY(-100);
                    b.setIsDead(true);
                }
            }
            for (Enemies e : enemies) {
                if (b.getBoundsInParent().intersects(e.getBoundsInParent())) {
                    b.setTranslateY(-100);
                    b.setIsDead(true);
                    e.die();
                    kills++;
                }
            }
        }
    }

    public void jumpAnimate() {
        setupJumpItems();
        ja.handJump((Hand) lHand);
        ja.handJump((Hand) rHand);
        ja.bootJump((Boot) lBoot);
        ja.bootJump((Boot) rBoot);
        ja.helmetJump((Helmet) helmet);
        ja.torsoJump((Torso) torso);
        ja.gunJump((Gun) rGun);

    }

    public void fallAnimate() {
        setupFallItems();
        fa.handFall((Hand) lHand);
        fa.handFall((Hand) rHand);
        fa.bootFall((Boot) lBoot);
        fa.bootFall((Boot) rBoot);
        fa.helmetFall((Helmet) helmet);
        fa.torsoFall((Torso) torso);
        fa.gunFall((Gun) rGun);
    }

    public void reloadAnimate() {

        //Initial KeyFrame
        KeyValue initKeyValue = new KeyValue(reloadArc.lengthProperty(), 0, CustomInterpolator.acceleratingInterpolator());

        KeyFrame initFrame = new KeyFrame(Duration.ZERO, initKeyValue);

        //End KeyFrame
        KeyValue endKeyValue = new KeyValue(reloadArc.lengthProperty(), 360, CustomInterpolator.acceleratingInterpolator());

        KeyFrame endFrame = new KeyFrame(Duration.seconds(1.5), endKeyValue);
        
        //Creating the timeline
        Timeline t = new Timeline(initFrame, endFrame);
        t.setAutoReverse(false);
        t.setCycleCount(1);
        t.setOnFinished(e -> {
            reloadArc.setVisible(false);
            reloadArc.setLength(0);
        });
        reloadArc.setVisible(true);
        t.play();

    }

    public void walkAnimate(double x, double y) {
        updateItems();
        setupWalkItems();
        
        //Variables used for minitius adjustments in both the preview and in the game
        double a = 0;
        double b = 0;
        double c = 0;
        double d = 0;
        double w = 6;
        
        if(ifPreview){
            setTranslateY(142);
            c = 86;
            a = -6;
            b = 6;
            d = 4;
            w = 0;
        }
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);
        
        this.isAlreadyRunning = true;
        PathTransition torsoTransition = WalkingAnimation.torsoPath(torso, x, y);
        PathTransition helmetTransition = WalkingAnimation.helmetPath((Helmet) helmet, x, y);
        PathTransition rbootTransition = WalkingAnimation.bootPath((Boot) rBoot, x, y, 46);
        PathTransition lbootTransition = WalkingAnimation.bootPath((Boot) lBoot, x, y, 46);

        Gun gun = (Gun) rGun;
        if (gun.getIsDualWield()) {
            Fingers f = (Fingers) fingers;
            f.setKind("dual");
            fingers = (InGameItems) f;
            Hand newHand = (Hand) lHand;
            newHand.setKind("l");
            lHand = (InGameItems) newHand;
            lGun.setVisible(true);
            lHand.setVisible(true);
            
            PathTransition pistol1Transition;
            PathTransition pistol2Transition;
            PathTransition rhandTransition = WalkingAnimation.handPath((Hand) rHand, x, y);
            PathTransition fingersTransition = WalkingAnimation.fingersPath((Fingers) fingers, x, y);
            PathTransition lhandTransition = WalkingAnimation.handPath((Hand) lHand, x, y);
            if(gun.getKind().equalsIgnoreCase("uzi")){
                pistol1Transition = WalkingAnimation.gunPath((Gun) rGun, x+d-20, y+c-b-w);
                pistol2Transition = WalkingAnimation.gunPath((Gun) lGun, x+d-20, y+c-b-w);
            }
            else{
                pistol1Transition = WalkingAnimation.gunPath((Gun) rGun, x+a, y+c+b);
                pistol2Transition = WalkingAnimation.gunPath((Gun) lGun, x+a, y+c+b);
            }
            
            
            
            fingersTransition.play();
            rhandTransition.play();
            lhandTransition.play();
            pistol2Transition.play();
            pistol1Transition.play();
        }
        if (!gun.getIsDualWield()) {
            lGun.setVisible(false);
            lHand.setVisible(false);
            Fingers f = (Fingers) fingers;
            f.setKind("single");
            fingers = (InGameItems) f;
            PathTransition rhandTransition = WalkingAnimation.handPath((Hand) rHand, x + 8, y + 16);
            PathTransition pistol1Transition = WalkingAnimation.gunPath((Gun) rGun, x + 8, y + 20+c);
            PathTransition fingersTransition = WalkingAnimation.fingersPath((Fingers) fingers, x + 2, y);
            pistol1Transition.play();
            fingersTransition.play();
            rhandTransition.play();

        }

        helmetTransition.play();
        torsoTransition.play();
        rbootTransition.play();
        lbootTransition.play();
    }

    public void setupFallItems() {
        Hand newHand = (Hand) lHand;
        newHand.setKind("whole");
        this.lHand = newHand;

        this.lHand.setRotate(-95);
        this.rHand.setRotate(0);
        this.lBoot.setRotate(0);
        this.rBoot.setRotate(0);

        this.lGun.setVisible(false);
        this.fingers.setVisible(false);
        this.lHand.setVisible(true);
    }

    private void setupJumpItems() {
        Hand newHand = (Hand) lHand;
        newHand.setKind("whole");
        this.lHand = newHand;

        this.lHand.setRotate(-90);
        this.lBoot.setRotate(-40);
        this.rBoot.setRotate(25);

        this.lGun.setVisible(false);
        this.fingers.setVisible(false);
        this.lHand.setVisible(true);
    }

    private void setupWalkItems() {
        Hand newHand = (Hand) lHand;
        newHand.setKind("whole");
        this.lHand = newHand;

        this.lHand.setRotate(0);
        this.rHand.setRotate(0);
        this.lBoot.setRotate(0);
        this.rBoot.setRotate(0);

        this.fingers.setVisible(true);
    }

    public void goToBottom() {
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
        return (health <= 0);
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

    public InGameItems getHelmet() {
        return helmet;
    }

    public InGameItems getTorso() {
        return torso;
    }

    public InGameItems getBullet() {
        return bullet;
    }

    public InGameItems getrHand() {
        return rHand;
    }

    public InGameItems getlHand() {
        return lHand;
    }

    public InGameItems getrBoot() {
        return rBoot;
    }

    public InGameItems getlBoot() {
        return lBoot;
    }

    public void setHelmet(InGameItems Helmet) {
        this.helmet = Helmet;
    }

    public void setTorso(InGameItems Torso) {
        this.torso = Torso;
    }

    public void setrHand(InGameItems rHand) {
        this.rHand = rHand;
    }

    public void setlHand(InGameItems lHand) {
        this.lHand = lHand;
    }

    public void setrBoot(InGameItems rBoot) {
        this.rBoot = rBoot;
    }

    public void setlBoot(InGameItems lBoot) {
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

    public InGameItems getFingers() {
        return fingers;
    }

    public void setFingers(InGameItems fingers) {
        this.fingers = fingers;
    }

    public InGameItems getlGun() {
        return lGun;
    }

    public void setlGun(InGameItems lGun) {
        if (equipedItems.isEmpty() || equipedItems.size() == 1) {
            this.lGun = lGun;
        } else {
            int index = equipedItems.indexOf(this.lGun);
            this.lGun = lGun;
            equipedItems.set(index, lGun);
        }
    }

    public InGameItems getrGun() {
        return rGun;
    }

    public void setrGun(InGameItems rGun) {
        if (equipedItems.isEmpty() || equipedItems.size() == 1) {
            this.rGun = rGun;
        } else {
            int index = equipedItems.indexOf(this.rGun);
            this.rGun = rGun;
            equipedItems.set(index, rGun);
        }
    }

    public Obstacles getGround() {
        return currentGround;
    }

    public double getLowerY() {
        return lowerY;
    }

    public boolean getIsInTheAir() {
        return isInTheAir;
    }

    public Obstacles getPreviousGround() {
        return previousGround;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public boolean isReloading() {
        return isReloading;
    }

    public void setIsReloading(boolean isReloading) {
        this.isReloading = isReloading;
    }

    public boolean isHasSpecialGun() {
        return hasSpecialGun;
    }

    public void setHasSpecialGun(boolean hasSpecialGun) {
        this.hasSpecialGun = hasSpecialGun;
    }

    public int getSpecialCounter() {
        return specialCounter;
    }

    public void setSpecialCounter(int specialCounter) {
        this.specialCounter = specialCounter;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }
    
    public Arc getReloadArc() {
        return reloadArc;
    }
}
