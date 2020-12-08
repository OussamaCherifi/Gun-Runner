/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obstacles;

import GameGUI.Map;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Objects;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;


/**
 *
 * @author 15148
 */
public abstract class Obstacles extends ImageView {
    
    //this class represents a parent for the floor, the ceilings, and the platforms. 
    //they will all follow this template and have the methods of this class right here.  
    protected double xpos ,ypos;
    protected final double velocity = -5;
    protected String path;
    protected Image image;
    protected double width , height;
    protected String type;
    
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
   
    //private final double screenH = screenSize.getHeight();
    private final double screenH = screenSize.getHeight();
            
    public Obstacles(String path, double x , double y, String type){
        this.path = path;
        this.type = type;
      //  image = new Image(path);
        if(type.equalsIgnoreCase("floor")){
           image = new Image(path , (128*3) , 42*3 , false , false); 
        }
        else if(type.equalsIgnoreCase("platform")){
            image = new Image(path , 548 , 60 , false , true);
        }
        
        setImage(image);
        width = image.getWidth();
        height = image.getHeight();
        xpos = x; 
        ypos = y;
        setTranslateX(xpos);
        setTranslateY(ypos);
    }
    
    public void update(Map map){
        xpos += velocity;
        ypos = getTranslateY();
        
        if(xpos + width <= 0){
            if(type.equalsIgnoreCase("platform")){
            generateY();
            //ypos = Math.round(Math.random() * (-200) + 680);
            }
            xpos = map.getMapWidth();
        }
                
        setTranslateX(xpos);
        setTranslateY(ypos);
    }
    
    public double generateY(){
        ypos = Math.round(Math.random()/0.2) * -40 + 680;
        return ypos;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + (int) (Double.doubleToLongBits(this.xpos) ^ (Double.doubleToLongBits(this.xpos) >>> 32));
        hash = 41 * hash + (int) (Double.doubleToLongBits(this.ypos) ^ (Double.doubleToLongBits(this.ypos) >>> 32));
        hash = 41 * hash + (int) (Double.doubleToLongBits(this.width) ^ (Double.doubleToLongBits(this.width) >>> 32));
        hash = 41 * hash + (int) (Double.doubleToLongBits(this.height) ^ (Double.doubleToLongBits(this.height) >>> 32));
        hash = 41 * hash + Objects.hashCode(this.type);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Obstacles other = (Obstacles) obj;
        if (Double.doubleToLongBits(this.ypos) != Double.doubleToLongBits(other.ypos)) {
            return false;
        }
        if (Double.doubleToLongBits(this.width) != Double.doubleToLongBits(other.width)) {
            return false;
        }
        if (Double.doubleToLongBits(this.height) != Double.doubleToLongBits(other.height)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        return true;
    }
    
    
    
    protected abstract void enemySpawn(Map map);
    
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

    
}
