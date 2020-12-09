/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playerAnimation;

import items.*;
import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.scene.Node;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.ClosePath;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

/**
 *
 * @author ismai
 */
public class WalkingAnimation {
    
    public WalkingAnimation(){
        
    }
    public static PathTransition helmetPath(Helmet helmet, double x, double y){
        Path path = new Path();
        x = 32+x;
        y = 29+y;
        MoveTo move = new MoveTo();
            move.setX(helmet.getXpos() + x);
            move.setY(helmet.getYpos() + y);
        
        LineTo line = new LineTo();
            line.setX(helmet.getXpos() + x + 2);
            line.setY(helmet.getYpos() + y + 10);
        
        LineTo line2 = new LineTo();
            line2.setX(helmet.getXpos() + x);
            line2.setY(helmet.getYpos() + y);
           
        
        path.getElements().add(move);
        path.getElements().add(line);
        path.getElements().add(line2);
        
        return createPathTransition(helmet, path);
    }
    
    public static PathTransition handPath(Hand hand, double x, double y){
        Path path = new Path();
        x = 16 + x;
        y = 10 + y;
        
        
        MoveTo move = new MoveTo();
            move.setX(hand.getXpos()+x);
            move.setY(hand.getYpos()+y);
            
        ArcTo arcF = new ArcTo();
            arcF.setX(hand.getXpos()+x+20);
            arcF.setY(hand.getYpos()+y);
            arcF.setRadiusX(10);
            arcF.setRadiusY(10);
            
            
            path.getElements().add(move);
            path.getElements().add(arcF);
            //path.getElements().add(arcForward);
            //path.getElements().add(arcBackward);
            
        return createPathTransition(hand, path);
    }
    
    public static PathTransition fingersPath(Fingers fingers, double x, double y){
        Path path = new Path();
        x = 16 + x;
        y = 10 + y;
        
        MoveTo move = new MoveTo();
            move.setX(fingers.getXpos()+x);
            move.setY(fingers.getYpos()+y);
            
        ArcTo arcF = new ArcTo();
            arcF.setX(fingers.getXpos()+x+20);
            arcF.setY(fingers.getYpos()+y);
            arcF.setRadiusX(10);
            arcF.setRadiusY(10);
            
            
            path.getElements().add(move);
            path.getElements().add(arcF);
            //path.getElements().add(arcForward);
            //path.getElements().add(arcBackward);
            
        return createPathTransition(fingers, path);
    }
    
    public static PathTransition gunPath(Gun gun, double x, double y){
        x = 16+x;
        y = 10+y;
        if(gun.getKind().equalsIgnoreCase("uzi")){
            x = 14;
            y = 12;
        }
        Path path = new Path();
        
        MoveTo move = new MoveTo();
            move.setX(gun.getXpos()+x);
            move.setY(gun.getYpos()+y);
            
        ArcTo arcF = new ArcTo();
            arcF.setX(gun.getXpos()+x+20);
            arcF.setY(gun.getYpos()+y);
            arcF.setRadiusX(10);
            arcF.setRadiusY(10);
            
            
            path.getElements().add(move);
            path.getElements().add(arcF);
            //path.getElements().add(arcForward);
            //path.getElements().add(arcBackward);
            
        return createPathTransition(gun, path);
    }
    
    public static PathTransition torsoPath(Item torso, double x, double y){
        Path path = new Path();
        x = 24;
        y = 29;
        MoveTo move = new MoveTo();
            move.setX(torso.getXpos() + x);
            move.setY(torso.getYpos() + y);
        
        LineTo line = new LineTo();
            line.setX(torso.getXpos() + x - 2);
            line.setY(torso.getYpos() + y + 9);
        
        LineTo line2 = new LineTo();
            line2.setX(torso.getXpos() + x);
            line2.setY(torso.getYpos() + y);
           
        
        path.getElements().add(move);
        path.getElements().add(line);
        path.getElements().add(line2);
        
        return createPathTransition(torso, path);
    }
    
    public static PathTransition bootPath(Boot boot, double x, double y, double spacing){
        Path path = new Path();
        x = 28;
        y = 24;
        double s = spacing;
        if(boot.getKind().equalsIgnoreCase("l")){
            MoveTo move = new MoveTo();
                move.setX(boot.getXpos()+x);
                move.setY(boot.getYpos()+y);
                
            LineTo line = new LineTo();
                line.setX(boot.getXpos()-s+x);
                line.setY(boot.getYpos()+y);
                
            ArcTo arc = new ArcTo();
                arc.setX(boot.getXpos()+x);
                arc.setY(boot.getYpos()+y);
                arc.setRadiusX(s/2);
                arc.setRadiusY(s/2);
                arc.setLargeArcFlag(false);
                arc.setSweepFlag(true);
              
            path.getElements().add(move);
            path.getElements().add(line);
            path.getElements().add(arc);
            
        }if(boot.getKind().equalsIgnoreCase("r")){
            MoveTo move = new MoveTo();
                move.setX(boot.getXpos()+x);
                move.setY(boot.getYpos()+y);
            ArcTo arc = new ArcTo();
                arc.setX(boot.getXpos()+x+s);
                arc.setY(boot.getYpos()+y);
                arc.setRadiusX(s/2);
                arc.setRadiusY(s/2);
                arc.setLargeArcFlag(false);
                arc.setSweepFlag(true);   
                
            LineTo line = new LineTo();
                line.setX(boot.getXpos()+x);
                line.setY(boot.getYpos()+y);
                
            
              
            path.getElements().add(move);
            path.getElements().add(arc);
            path.getElements().add(line);
        }
        
        return createPathTransition(boot, path);
    }
    
    private static PathTransition createPathTransition(Item item, Path path){
        PathTransition transition = new PathTransition();
        transition.setNode(item);
        if(item.getType() == ItemType.hands || item.getType() == ItemType.fingers ||  item.getType() == ItemType.gun){
            transition.setDuration(Duration.seconds(0.5));
            transition.setRate(2);
            transition.setAutoReverse(true);
        }
        if(item.getType() == ItemType.boots){
            transition.setInterpolator(Interpolator.LINEAR);
        }
        transition.setDuration(Duration.seconds(1));
        transition.setPath(path);
        transition.setCycleCount(PathTransition.INDEFINITE);
        return transition;
    }
    
}
