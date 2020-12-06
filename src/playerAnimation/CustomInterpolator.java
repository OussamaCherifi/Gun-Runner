/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playerAnimation;

import javafx.animation.Interpolator;

/**
 *
 * @author ismai
 */
public class CustomInterpolator {
    
    public static Interpolator jumpInterpolator(){
        Interpolator t = new Interpolator() {
            @Override
            protected double curve(double t) {
                return(t == 1.0) ? 1.0 : 1 - Math.pow(2.0, -10 * t); 
                
            }
        };
          
        return t;
    }
    
    public static Interpolator fallInterpolator(){
        Interpolator t = new Interpolator() {
            @Override
            protected double curve(double t) {
                return(t == 1.0) ? 0.0 : 1 - Math.pow(2.0, 10 * (t - 1));
            }
        };
          
        return t;
    }
}
