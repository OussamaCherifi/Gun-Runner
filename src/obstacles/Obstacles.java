/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obstacles;

import GameGUI.Map;
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
            
    public Obstacles(String path, double x , double y, String type){
        this.path = path;
        this.type = type;
      //  image = new Image(path);
        if(type.equalsIgnoreCase("floor")){
           image = new Image(path , 240 , 84 , false , true); 
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
                ypos = (Math.random() * (-200) + 600);
            }
            xpos = map.getMapWidth();
        }
                
        setTranslateX(xpos);
        setTranslateY(ypos);
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
