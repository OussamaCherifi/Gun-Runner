/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameGUI;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author ismai
 */
public class gridBackground extends ImageView{
    protected Image image;

    public gridBackground(double x, double y) {
        String path = "sprites/resources/gridBackground.png";
        this.image = new Image(path);
        this.setImage(image);
        this.setTranslateX(x);
        this.setTranslateY(y);
    }
    
    
    
    
}
