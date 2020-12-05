/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameGUI;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.List;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import obstacles.*;

/**
 *
 * @author 15148
 */
public class Map extends Pane{
    
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    
    private final double height = screenSize.getHeight();
    private final double width = screenSize.getWidth();
    
    public Map(){
        setPrefSize(width, height);
        
        this.getStylesheets().add("styles/Map.css");
    }
    
    public double getMapWidth(){
        return width;
    }
    
    public double getMapHeight(){
        return height;
    }
    
    public void insertElement(Node n){
        getChildren().add(n);
    }
    
    public void addAllBackgrounds(List<BackgroundsParent> list){
        for(BackgroundsParent b : list){
            insertElement(b);
        }
    }
    
    public void addAllElements(List<Obstacles> list){
        for(Obstacles o: list){
            insertElement(o);
        }
    }
    
    public void removeElement(Node n){
        getChildren().remove(n);
    }
}
