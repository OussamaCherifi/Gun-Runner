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
public class Bullet extends Item implements ISearchablePath{
    String kind;
    
    public Bullet(String kind, double x, double y, int price, double scale, Custom custom) {
        super(x, y, price, scale, custom);
        super.type = ItemType.bullet;
        this.kind = kind;
        String path = findPath();
        super.initializeImage(path);
        
    }
    
    @Override
    public String findPath(){
        String path = "";
        switch(custom){
            case normal:
                path = "sprites/guns/bullets/bullet_"+kind+".png";
                break;
            case c1:
                path = "sprites/guns/bullets/bullet_"+kind+"_c1.png";
                break;
        }
        return path;
    }
    
    public String getKind() {
        return kind;
    }
    
    
    
    
    
    
    
    
    
}
