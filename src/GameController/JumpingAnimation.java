/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameController;

import items.*;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

/**
 *
 * @author ismai
 */
public class JumpingAnimation {
    
    public static SequentialTransition helmetPath(Helmet helmet, double x, double y){
        
        Path path1 = new Path();
        x = 32 + x;
        y = 29 + y;
        MoveTo move1 = new MoveTo();
            move1.setX(helmet.getXpos() + x);
            move1.setY(helmet.getYpos() + y);
        LineTo line1 = new LineTo();
            line1.setX(helmet.getXpos() + x);
            line1.setY(helmet.getYpos() + y+50);
        path1.getElements().add(move1);
        path1.getElements().add(line1);
        
        PathTransition pathA = createPathTransition(helmet, path1, 0.1);
        
        Path path2 = new Path();
        
        MoveTo move2 = new MoveTo();
            move2.setX(helmet.getXpos() + x);
            move2.setY(helmet.getYpos() + y+10);
        LineTo line2 = new LineTo();
            line2.setX(helmet.getXpos() + x+4);
            line2.setY(helmet.getYpos() + y-30);
        
            path2.getElements().add(move2);
            path2.getElements().add(line2);
            
        PathTransition pathB = createPathTransition(helmet, path2, 0.2);
        
        Path path3 = new Path();
        
        MoveTo move3 = new MoveTo();
            move3.setX(helmet.getXpos() + x+10);
            move3.setY(helmet.getYpos() + y-30);
        LineTo line3 = new LineTo();
            line3.setX(helmet.getXpos() + x);
            line3.setY(helmet.getYpos() + y);
        
            path3.getElements().add(move3);
            path3.getElements().add(line3);
        
        
        PathTransition pathC = createPathTransition(helmet, path3, 0.3);
        
        SequentialTransition t1 = createSequentialTransition(helmet, pathA, pathB, pathC);
        
        SequentialTransition jump = verticalJump(helmet, helmet.getXpos() + x, helmet.getYpos() + y);
        
        ParallelTransition pl = new ParallelTransition(t1, jump);
        pl.setNode(helmet);
        pl.setCycleCount(1);
        
        
        return t1;
    }
    
    /*public static List<PathTransition> handPath(Hand hand, double x, double y, double spacing){
        List<PathTransition> list = new ArrayList<>();
        Path path = new Path();
        x = 16 + x;
        y = 10 + y;
        MoveTo move = new MoveTo();
            move.setX(hand.getXpos() + x);
            move.setY(hand.getYpos() + y);
        LineTo line = new LineTo();
            line.setX(hand.getXpos() + x-5);
            line.setY(hand.getYpos() + y+10);
        path.getElements().add(move);
        path.getElements().add(line);
        
        pathA = createPathTransition(Item item, Path path);
        
        
        MoveTo move1 = new MoveTo();
            move1.setX(hand.getXpos() + x-5);
            move1.setY(hand.getYpos() + y+10);
        LineTo line1 = new LineTo();
            line1.setX(hand.getXpos() + x+10);
            line1.setY(hand.getYpos() + y-20);
        
        Path path2 = new Path();
        path2.getElements().add(move);
        path2.getElements().add(line);
        
        
        
        
        list.add(pathA);
        list.add(pathB);
        return list;
    }*/
    
//    public static List<PathTransition> fingersPath(Fingers fingers, double x, double y, double spacing){
//        
//    }
//    
//    public static List<PathTransition> gunPath(Gun gun, double x, double y){
//        
//    }
//    
//    public static List<PathTransition> torsoPath(Item torso, double x, double y){
//        
//    }
//    
//    public static List<PathTransition> bootPath(Boot boot, double x, double y, double spacing){
//        
//    }
    
    private static SequentialTransition verticalJump(Item item, double x, double y){
        TranslateTransition tt = new TranslateTransition();
        tt.setByX(x);
        tt.setByY(y);
        tt.setToX(x);
        tt.setToY(y-400);
        tt.setCycleCount(1);
        tt.setNode(item);
        tt.setDuration(Duration.seconds(0.3));
        tt.setInterpolator(Interpolator.LINEAR);
        
        TranslateTransition tt2 = new TranslateTransition();
        tt2.setByX(x);
        tt2.setByY(y-400);
        tt2.setToX(x);
        tt2.setToY(y);
        tt2.setCycleCount(1);
        tt.setNode(item);
        tt2.setDuration(Duration.seconds(0.3));
        tt.setInterpolator(Interpolator.LINEAR);
        
        SequentialTransition st = new SequentialTransition(tt, tt2);
        
        return st;
    }
    
    private static PathTransition createPathTransition(Item item, Path path, double s){
        PathTransition t = new PathTransition();
        t.setNode(item);
        t.setDuration(Duration.seconds(s));
        t.setPath(path);
        t.setCycleCount(1);
        return t;
    }
    
    private static SequentialTransition createSequentialTransition(Item item, PathTransition p1, PathTransition p2, PathTransition p3){
        SequentialTransition st = new SequentialTransition(p1, p2, p3);
        st.setNode(item);
        st.setCycleCount(1);
        return st;
    }
}
