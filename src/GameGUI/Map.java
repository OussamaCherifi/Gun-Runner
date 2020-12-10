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
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import obstacles.*;

/**
 *
 * @author 15148
 */
public class Map extends Pane{
    private Button back = new Button("Back");
    
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    
    private final double height = screenSize.getHeight();
    private final double width = screenSize.getWidth();
    
    public Map(){
        
        this.back.setLayoutY(40);
        this.back.setLayoutX(30);
        this.back.setScaleX(1.25);
        this.back.setScaleY(1.25);
        this.back.setTextFill(Color.web("#ff0000", 0.8));
        this.back.setFocusTraversable(false);
        
        
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

    public Button getBack() {
        return back;
    }
    
    
}
