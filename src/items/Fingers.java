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
public class Fingers extends InGameItems implements ISearchablePath{
    String kind;

    public Fingers(String kind, double xpos, double ypos, double scale) {
        super(xpos+58, ypos+66, scale);
        super.type = ItemType.fingers;
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
    
    public void setKind(String kind){
        this.kind = kind;
        String path = findPath();
        super.initializeImage(path);
    }
    
    
    
}
