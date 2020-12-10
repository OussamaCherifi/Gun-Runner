/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playerAnimation;

import items.*;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;
import characterElements.Player;

/**
 *
 * @author ismai
 */
public class JumpingAnimation {
    
    Player player;

    public JumpingAnimation(Player player) {
        this.player = player;
    }
    
    public static SequentialTransition helmetPath(Helmet helmet){
        double x = 4;
        double y = 20;
        
        //SequentialTransition t1 = createPaths(helmet, x, y, 10, 100, 20);
        
        SequentialTransition jump = verticalJump(helmet, helmet.getXpos() + x, helmet.getYpos() + y, 515);
        
//        ParallelTransition pt = new ParallelTransition(t1, jump);
//        pt.setNode(helmet);
//        pt.setCycleCount(1);
        
        return jump;
    }
    
    
    
    public static SequentialTransition handPath(Hand hand){
        double x;
        double y;
        if(hand.getKind().equalsIgnoreCase("r")){
            x = -6;
            y = 32;
        }else{
            x = 20;
            y = -26;
        }
        
        //SequentialTransition t1 = createPaths(hand, x, y, 10, 0, -20);
        
        SequentialTransition jump = verticalJump(hand, hand.getXpos() + x, hand.getYpos() + y, 515);
        
//        ParallelTransition pt = new ParallelTransition(t1, jump);
//        pt.setNode(hand);
//        pt.setCycleCount(1);
        
        return jump;
    }
    
    public static SequentialTransition torsoPath(Torso torso){
        double x = 4;
        double y = 20;
        
        //SequentialTransition t1 = createPaths(helmet, x, y, 10, 100, 20);
        
        SequentialTransition jump = verticalJump(torso, torso.getXpos() + x, torso.getYpos() + y, 515);
        
//        ParallelTransition pt = new ParallelTransition(t1, jump);
//        pt.setNode(helmet);
//        pt.setCycleCount(1);
        
        return jump;
    }
    
    public static SequentialTransition verticalJump(Item item, double x, double y, double height){
        TranslateTransition tt = new TranslateTransition();
        tt.setByX(x);
        tt.setByY(y);
        tt.setToX(x);
        tt.setToY(y-height);
        tt.setCycleCount(1);
        tt.setNode(item);
        tt.setDuration(Duration.seconds(0.43/2));
        tt.setInterpolator(CustomInterpolator.jumpInterpolator());
        
        TranslateTransition tt2 = new TranslateTransition();
        tt2.setByX(x);
        tt2.setByY(y-height);
        tt2.setToX(x);
        tt2.setToY(y);
        tt2.setCycleCount(1);
        tt2.setNode(item);
        tt2.setDuration(Duration.seconds(0.43/2));
        tt.setInterpolator(CustomInterpolator.jumpInterpolator());
        
        SequentialTransition st = new SequentialTransition(tt, tt2);
        
        return st;
    }
    
    public static PathTransition createPathTransition(Path path, double s){
        PathTransition t = new PathTransition();
        //t.setNode(item);
        t.setDuration(Duration.seconds(s));
        t.setInterpolator(Interpolator.EASE_OUT);
        t.setPath(path);
        t.setCycleCount(1);
        return t;
    }
    
    public static SequentialTransition createPaths(Item item, double x, double y, double a, double b, double x1){
        Path path1 = new Path();
        MoveTo move1 = new MoveTo();
            move1.setX(item.getXpos() + x);
            move1.setY(item.getYpos() + y);
        LineTo line1 = new LineTo();
            line1.setX(item.getXpos() + x);
            line1.setY(item.getYpos() + y+a);
        path1.getElements().add(move1);
        path1.getElements().add(line1);
        
        PathTransition pathA = createPathTransition(path1, 0.1);
        
        Path path2 = new Path();
        
        MoveTo move2 = new MoveTo();
            move2.setX(item.getXpos() + x);
            move2.setY(item.getYpos() + y+a);
        LineTo line2 = new LineTo();
            line2.setX(item.getXpos() + x+x1);
            line2.setY(item.getYpos() + y-b);
            path2.getElements().add(move2);
            path2.getElements().add(line2);
            
        PathTransition pathB = createPathTransition(path2, 0.115);
        
        Path path3 = new Path();
        
        MoveTo move3 = new MoveTo();
            move3.setX(item.getXpos() + x+x1);
            move3.setY(item.getYpos() + y-b);
        LineTo line3 = new LineTo();
            line3.setX(item.getXpos() + x);
            line3.setY(item.getYpos() + y);
            
            path3.getElements().add(move3);
            path3.getElements().add(line3);
        
        
        PathTransition pathC = createPathTransition(path3, 0.215);
        
        return createSequentialTransition(item, pathA, pathB, pathC);
    }
    
    public static SequentialTransition bootPath(Boot hand){
        
        double x;
        double y;
        if(hand.getKind().equalsIgnoreCase("r")){
            x = 6;
            y = 22;
        }else{
            x = 16;
            y = -12;
        }
        
        //SequentialTransition t1 = createPaths(hand, x, y, 10, 0, -20);
        
        SequentialTransition jump = verticalJump(hand, hand.getXpos() + x, hand.getYpos() + y, 515);
        
//        ParallelTransition pt = new ParallelTransition(t1, jump);
//        pt.setNode(hand);
//        pt.setCycleCount(1);
        
        return jump;
    }
    
    public static SequentialTransition createSequentialTransition(Item item, PathTransition p1, PathTransition p2, PathTransition p3){
        SequentialTransition st = new SequentialTransition(p1, p2, p3);
        st.setNode(item);
        st.setCycleCount(1);
        
        return st;
    }


    public void handJump(Hand hand){
        double x;
        double y;
        if(hand.getKind().equalsIgnoreCase("r")){
            x = 14;
            y = -90;
        }else{
            x = 74;
            y = -180;
        }
        hand.setPos(x+player.getXpos(), y+player.getLowerY());
    }
    
    public void helmetJump(Helmet helmet){
        helmet.setPos(helmet.getOriginalX(), player.getLowerY()-helmet.getOffsetY());
    }
    public void torsoJump(Torso torso){
        torso.setPos(torso.getOriginalX(), player.getLowerY()-2*torso.getOffsetY()+10);
    }
    
    public void bootJump(Boot boot){
        double x;
        double y;
        if(boot.getKind().equalsIgnoreCase("r")){
            x = 0;
            y = -26;
        }else{
            x = 66;
            y = -68;
        }
        boot.setPos(x+player.getXpos(), y+player.getLowerY());
    }
    
    public void gunJump(Gun gun){
        gun.setPos(gun.getOriginalX(), player.getLowerY()+gun.getOffsetY()-106);
    }
    
}