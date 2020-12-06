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
public class Gun extends Item implements ISearchablePath{
    private boolean isDualWield;
    private String kind;

    public Gun(String kind, double x, double y, int price, int scale, Custom custom) {
        super(x, y, price, scale, custom);
        super.type = ItemType.gun;
        this.kind = kind;
        if(kind.equalsIgnoreCase("ak")){
            this.isDualWield = false;
        }else{
            this.isDualWield = true;      
        }
        String path = findPath();
        super.initializeImage(path);
    }

    @Override
    public String findPath() {
        String path = "";
        switch(custom){
            case normal:
                path = "sprites/guns/"+kind+".png";
                break;
            case c1:
                path = "sprites/guns/"+kind+"_c1.png";
                break;
        }
        return path;
    }

    
    public boolean getIsDualWield() {
        return isDualWield;
    }

    public String getKind() {
        return kind;
    }
    
    
    
}