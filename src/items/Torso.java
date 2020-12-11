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
public class Torso extends InGameItems implements ISearchablePath{
    
    public Torso(double x, double y, int price, int scale, Custom custom) {
        super(x+26, y+58, price, scale, custom);
        super.type = ItemType.torso;
        super.offsetX = 26;
        super.offsetY = 58;
        String path = findPath();
        super.initializeImage(path);
    }

    @Override
    public String findPath() {
        String path = "";
        switch(custom){
            case normal:
                path = "sprites/player/pl_torso.png";
                break;
            case c1:
                path = "sprites/player/c1/pl_torso_c1.png";
                break;
            case c2:
                path = "sprites/player/c2/pl_torso_c2.png";
                break;
        }
        return path;
    }
    
}
