/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obstacles;

import GameGUI.Map;
import characterElements.Enemies;
import java.awt.Dimension;
import java.awt.Toolkit;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

/**
 *
 * @author 15148
 */
public abstract class Obstacles extends ImageView {

    //this class represents a parent for the floor, the ceilings, and the platforms. 
    //they will all follow this template and have the methods of this class right here.  
    protected double xpos, ypos;
    protected final double velocity = -5;
    protected String path;
    protected Image image;
    protected double width, height;
    protected String type;
    
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    //private final double screenH = screenSize.getHeight();
    private final double screenH = screenSize.getHeight();

    //Enemies and crates
    private Enemies e;
    private Crates crate;
    private Coins coin;
    
    public Obstacles(String path, double x, double y, String type) {
        this.path = path;
        this.type = type;
        //  image = new Image(path);
        if (type.equalsIgnoreCase("floor")) {
            image = new Image(path, (128 * 3), 42 * 3, false, false);
        } else if (type.equalsIgnoreCase("platform")) {
            image = new Image(path, 548, 60, false, true);
        }
        
        setImage(image);
        width = image.getWidth();
        height = image.getHeight();
        xpos = x;
        ypos = y;
        setTranslateX(xpos);
        setTranslateY(ypos);
    }
    
    public void update(Map map) {
        xpos += velocity;
        ypos = getTranslateY();
        
        if (xpos + width <= 0) {
            if (type.equalsIgnoreCase("platform")) {
                generateY();
            }
            xpos = map.getMapWidth();
            enemySpawn(map);
            crateSpawn(map);
            if (crate == null) {
                coinSpawn(map);
            }
        }
        
        removeEnemyIfDead(map);
        removeCrate(map);
        removeCoin(map);
        setTranslateX(xpos);
        setTranslateY(ypos);
    }
    
    public double generateY() {
        ypos = Math.round(Math.random() / 0.2) * -40 + 680;
        return ypos;
    }
    
    private void enemySpawn(Map map) {
        double chances = 0;
        chances = Math.random() * (100 - 1) + 1;
        if (isFloor()) {
            if (chances <= 10) {
                e = new Enemies(this, xpos, ypos);                
                map.insertElement(e);
            }            
        } else {
            if (chances <= 40) {
                e = new Enemies(this, xpos, ypos);                
                map.insertElement(e);
            }
        }
    }
    
    private void removeEnemyIfDead(Map map) {
        if (e != null) {
            if (e.IsDead() == true) {
                map.removeElement(e);
                e = null;
            } else {
                e.update(map);                
            }            
        }
    }
    
    private void crateSpawn(Map map) {
        double chances = 0;
        chances = Math.random() * (1000 - 1) + 1;
        if (chances < 30) {
            crate = new Crates(this, xpos, ypos);
            map.insertElement(crate);
        }
    }
    
    private void removeCrate(Map map) {
        if (crate != null) {
            if (crate.isDead() == true) {
                map.removeElement(crate);
                crate = null;
            } else {
                crate.update(map);                
            }            
        }
    }
    
    private void coinSpawn(Map map) {
        double chances = 0;
        chances = Math.random() * (1000 - 1) + 1;
        if (chances < 100) {
            coin = new Coins(this, xpos, ypos);
            map.insertElement(coin);
        }
    }
    
    private void removeCoin(Map map) {
        if (coin != null) {
            if (coin.isDead() == true) {
                map.removeElement(coin);
                coin = null;
            } else {
                coin.update(map);                
            }            
        }
    }

    //getters and setters
    public double getXpos() {
        return getTranslateX();
    }
    
    public double getYpos() {
        return getTranslateY();
    }
    
    public double getVelocity() {
        return velocity;
    }
    
    public String getPath() {
        return path;
    }
    
    public double getWidth() {
        return image.getWidth();
    }
    
    public double getHeight() {
        return image.getHeight();
    }
    
    @Override
    public String toString() {
        return "Obstacles{" + "ypos= " + ypos + " type=" + type + '}';
    }
    
    public boolean isFloor() {
        if (type.equalsIgnoreCase("floor")) {
            return true;
        } else {
            return false;
        }
    }
}
