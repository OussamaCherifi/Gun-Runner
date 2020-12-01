/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obstacles;

import GameGUI.Map;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.awt.Dimension;
import java.awt.Toolkit;

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
    protected Image image, imageAfter;
    protected String path = "";
    
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
   
    //private final double screenH = screenSize.getHeight();
    private final double screenW = screenSize.getWidth();

    
    public BackgroundsParent(double x , double y, String type){
        
        //scaling the images depending on their types. 
        double a = findScaling(); //sets the scaling of the images
        if(type.equalsIgnoreCase("bg")){
           path = "sprites/Map/bg.png";
           image = new Image(path , 256 , 276 , false , true); //Original image without scaling
           imageAfter = new Image(path , 256*a+1 , (920)-(42*a)+2 , false , false); 
        }else if(type.equalsIgnoreCase("ceiling")){
            path = "sprites/Map/ceiling.png";
            image = new Image(path , 256, 42  , false , true ); //Original image without scaling
            imageAfter = new Image(path , 256*a+1, 42*a  , false , false);
        }
        
        setImage(imageAfter);
        xpos = x;
        ypos = y;
        width = imageAfter.getWidth(); height = imageAfter.getHeight();
        setTranslateX(xpos);
        setTranslateY(ypos);        
    }   

    public Image getImageAfter() {
        return imageAfter;
    }
    
    public double findScaling(){
        double s = screenW/(3*256);
        return s;
    }
    
    private int findNumberOfBackgrounds(){
        int num;
        if(screenW > 1920){
            num = 6;
        }else if(screenW <= 1920 && screenW > 720){
            num = 5;
        }
        return num =2;
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
