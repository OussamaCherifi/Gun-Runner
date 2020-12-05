/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package items;

/**
 *
 * @author ismai
 */
public class Fingers extends Item implements ISearchablePath{
    String kind;

    public Fingers(String kind, double xpos, double ypos, int price, double scale) {
        super(xpos, ypos+8, price, scale);
        this.kind = kind;
        String path = findPath();
        super.initializeImage(path);
    }
    

    @Override
    public String findPath() {
        String path = "";
        switch(kind){
            case "dual":
                path = "sprites/player/pl_l_fingers_dual.png";
                break;
            case "single":
                path = "sprites/player/pl_l_fingers.png";
                break;
        }
        return path;
    }
    
    
    
}
