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
public abstract class BackgroundsParent extends ImageView{
    
    //The classes which will inherit from this class do not need any addition as they are
    //final. The backgrounds are only there for asthethics and do not need any addition/modification. 
    
    protected double xpos, ypos;
    protected final double velocity = -2;
    protected double width , height;
    protected Image image;
    protected String path = "";
    
    public BackgroundsParent(double x , double y, String type){
        
        //scaling the images depending on their types. 
        if(type.equalsIgnoreCase("bg")){
           path = "sprites/Map/bg.png";
           image = new Image(path , 512 , 552 * 2 , false , true ); 
        }else if(type.equalsIgnoreCase("ceiling")){
            path = "sprites/Map/ceiling.png";
            image = new Image(path , 512 , 84  , false , true );
        }
        
        setImage(image);
        xpos = x;
        ypos = y;
        width = image.getWidth(); height = image.getHeight();
        setTranslateX(xpos);
        setTranslateY(ypos);        
    }
    
    public void update(Map map){
        xpos += velocity;
        
        if(xpos + width <= 0) xpos = map.getMapWidth();
        
        setTranslateX(xpos);
        setTranslateY(ypos);   
    }

    public double getXpos() {
        return xpos;
    }

    public double getYpos() {
        return ypos;
    }

    public double getVelocity() {
        return velocity;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
}
