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
public class Helmet extends Item implements ISearchablePath{
    
    public Helmet(double x, double y, int price, int scale, Custom custom) {
        super(x, y, price, scale, custom);
        super.type = ItemType.helmet;
        String path = findPath();
        super.initializeImage(path);
    }

    @Override
    public String findPath() {
        String path = "";
        switch(custom){
            case normal:
                path = "sprites/player/pl_helm.png";
                break;
            case c1:
                path = "sprites/player/c1/pl_helm_c1.png";
                break;
            case c2:
                path = "sprites/player/c2/pl_helm_c2.png";
                break;
        }
        return path;
    }
    
}
