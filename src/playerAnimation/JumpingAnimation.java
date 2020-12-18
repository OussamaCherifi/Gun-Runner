/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playerAnimation;

import items.*;

import javafx.animation.PathTransition;
import javafx.animation.SequentialTransition;

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
    


    
    public static SequentialTransition createSequentialTransition(InGameItems item, PathTransition p1, PathTransition p2, PathTransition p3){
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