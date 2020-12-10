/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playerAnimation;

import items.Boot;
import items.Gun;
import items.Hand;
import items.Helmet;
import items.Torso;
import characterElements.Player;

/**
 *
 * @author ismai
 */
public class FallAnimation {
    private Player player;

    public FallAnimation(Player player) {
        this.player = player;
    }
    
    public void handFall(Hand hand){
        double x;
        double y;
        if(hand.getKind().equalsIgnoreCase("r")){
            x = 14;
            y = -90;
        }
        else{
            x = 74;
            y = -140;
        }
        
        hand.setPos(x+player.getXpos(), y+player.getLowerY());
        hand.setPos(x+player.getXpos(), y+player.getLowerY());
    }
    
    public void helmetFall(Helmet helmet){
        helmet.setPos(helmet.getOriginalX(), player.getLowerY()-helmet.getOffsetY()-6);
    }
    public void torsoFall(Torso torso){
        torso.setPos(torso.getOriginalX(), player.getLowerY()-2*torso.getOffsetY());
    }
    
    public void bootFall(Boot boot){
        double x;
        double y;
        if(boot.getKind().equalsIgnoreCase("r")){
            x = 10;
            y = -44;
        }else{
            x = 55;
            y = -50;
        }
        boot.setPos(x+player.getXpos(), y+player.getLowerY());
    }
    
    public void gunFall(Gun gun){
        gun.setPos(gun.getOriginalX()-2, player.getLowerY()+gun.getOffsetY()-106);
    
    }
}
