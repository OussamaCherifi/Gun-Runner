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
    
    public static Interpolator acceleratingInterpolator(){
        Interpolator t = new Interpolator() {
            @Override
            protected double curve(double t) {
                return(t == 1.0) ? 1.0 : Math.sqrt(1-Math.pow(t-1, 2));
                        //1 - Math.pow(20, -1.6 * t); 
                
            }
        };
          
        return t;
    }
}
