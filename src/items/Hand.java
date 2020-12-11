/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package items;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author ismai
 */
public class Hand extends InGameItems implements ISearchablePath{
    private String kind;

    public Hand(String kind, double x, double y, int price, int scale, Custom custom) {
        super(x+2, y+66, price, scale, custom);
        super.offsetX = 2;
        super.offsetY = 66;
        super.type = ItemType.hands;
        this.kind = kind;
        if(kind.equalsIgnoreCase("l")){
            this.xpos = xpos + 56;
            System.out.println(getTranslateX());
        }
        String path = findPath();
        super.initializeImage(path);
    }

    @Override
    public String findPath() {
        String path = "";
        if(kind.equalsIgnoreCase("whole")){
            switch(custom){
            case normal:
                path = "sprites/player/player_l_hand.png";
                break;
            case c1:
                path = "sprites/player/c1/player_l_hand_c1.png";
                break;
            case c2:
                path = "sprites/player/c2/player_l_hand_c2.png";
                break;
        }
        }else{
        switch(custom){
            case normal:
                path = "sprites/player/pl_"+kind+"_hand.png";
                break;
            case c1:
                path = "sprites/player/c1/pl_"+kind+"_hand_c1.png";
                break;
            case c2:
                path = "sprites/player/c2/pl_"+kind+"_hand_c2.png";
                break;
            }
        }
        return path;
    }
    
    public void setKind(String kind){
        this.kind = kind;
        String path = findPath();
        super.initializeImage(path);
    }
    
    public String getKind() {
        return kind;
    }
    
    
    
    
}
