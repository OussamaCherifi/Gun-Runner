/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameController;

import items.*;
import javafx.animation.PathTransition;
import javafx.scene.Node;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.ClosePath;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

/**
 *
 * @author ismai
 */
public class PlayerAnimation {
    
    public PlayerAnimation(){
        
    }
    
    public static PathTransition handPath(Hand hand, double x, double y, double spacing){
        Path path = new Path();
        double s = spacing; //Redefining spacing as s (for simplicity in the code)
        if(hand.getKind().equalsIgnoreCase("right")){
            s = 0;
        }
        
        MoveTo move = new MoveTo();
            move.setX(hand.getXpos()+6);
            move.setY(hand.getYpos());
            
        ArcTo arcF = new ArcTo();
            arcF.setX(hand.getXpos()+56);
            arcF.setY(hand.getYpos());
            arcF.setRadiusX(25);
            arcF.setRadiusY(25);
            
        MoveTo move2 = new MoveTo();
            move.setX(hand.getXpos()+56);
            move.setY(hand.getYpos());
            
        ArcTo arcB = new ArcTo();
            arcB.setX(hand.getXpos());
            arcB.setY(hand.getXpos());
            arcB.setRadiusX(20);
            arcB.setRadiusY(20);
            
            path.getElements().add(move);
            path.getElements().add(arcF);
            //path.getElements().add(arcForward);
            //path.getElements().add(arcBackward);
            
        return createPathTransition(hand, path);
    }
    
    public static PathTransition torsoPath(Item torso, double x, double y){
        Path path = new Path();
        MoveTo move = new MoveTo();
        move.setX(x+5);
        move.setY(y+8);
        
        MoveTo moveBack = new MoveTo();
        moveBack.setX(x);
        moveBack.setY(y);
        
        path.getElements().add(move);
        path.getElements().add(moveBack);
        
        return createPathTransition(torso, path);
    }
    

    
    public static PathTransition bootPath(Item hand, String kind, double x, double y, double spacing){
        Path path = new Path();
        ArcTo arc = new ArcTo();
        double s = spacing; //Redefining spacing as s (for simplicity in the code)
        if(kind.equalsIgnoreCase("right")){
            
            arc.setX(x+s);
            arc.setX(y+s);
            arc.setRadiusX(90);
            arc.setRadiusY(80);
            
            path.getElements().add(arc);
            path.getElements().add(new ClosePath());
            
        }if(kind.equalsIgnoreCase("left")){
            MoveTo move = new MoveTo();
            move.setX(x-s);
            move.setY(y);
            
            arc.setX(x+s);
            arc.setY(y);
            arc.setRadiusX(90);
            arc.setRadiusY(80);
            
            path.getElements().add(move);
            path.getElements().add(arc);
        }
        return createPathTransition(hand, path);
    }
    
    
    private static PathTransition createPathTransition(Node node, Path path){
        PathTransition transition = new PathTransition();
        transition.setNode(node);
        transition.setDuration(Duration.seconds(1));
        transition.setPath(path);
        transition.setCycleCount(PathTransition.INDEFINITE);
        return transition;
    }
    
}
