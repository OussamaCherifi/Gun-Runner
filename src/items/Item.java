/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package items;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author ismai
 */
public abstract class Item extends ImageView{
    
    protected String kind;
    protected double offsetX, offsetY;
    protected double originalX, originalY;
    protected boolean isEquiped = false;
    protected static int counterId = 0;
    protected int idNum = 0;
    protected Custom custom;
    protected double xpos, ypos;
    protected int price;
    protected double scale;
    protected Image image, imageAfter;
    protected Button BuyButton = new Button("Buy");
    protected ItemType type;
    
    
    public Item(String path, double x, double y, int price, double scale) {
        this.custom = custom;
        this.price = price;
        this.scale = scale;
        this.idNum = counterId;
        counterId++;
        
        this.originalX = x;
        this.originalY = y;
        
        initializeImage(path);
        initializePos(x, y);
    }
    
        public Item(double xpos, double ypos, int price, double scale){
        this.scale = scale;
        this.price = price;
        this.originalX = xpos;
        this.originalY = ypos;
        
        this.idNum = counterId;
        counterId++;
        initializePos(xpos, ypos);
    }

    public Item(double xpos, double ypos, int price, double scale, Custom custom){
        this.scale = scale;
        this.price = price;
        this.custom = custom;
        this.originalX = xpos;
        this.originalY = ypos;
        this.idNum = counterId;
        counterId++;
        initializePos(xpos, ypos);
    }
    
    
    protected void initializePos(double x, double y){
        this.xpos = x; 
        this.ypos = y; 
        setTranslateX(xpos);
        setTranslateY(ypos);
    }
    
    protected void initializeImage(String path){
        this.image = new Image(path);
        imageAfter = new Image(path, image.getWidth()*scale, image.getHeight()*scale, true, true);
        this.setImage(imageAfter);
    }
    
    public void setBuyHandler(EventHandler handler){
        this.BuyButton.setOnAction(handler);   
    }

    public int getIdNum() {
        return idNum;
    }

    public double getXpos() {
        return xpos;
    }

    public double getYpos() {
        return ypos;
    }

    public int getPrice() {
        return price;
    }

    public Image getImageAfter() {
        return imageAfter;
    }

    public Button getBuyButton() {
        return BuyButton;
    }

    public ItemType getType() {
        return type;
    }
    
    public void setPos(double x, double y){
        setTranslateX(x);
        setTranslateY(y);
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setType(ItemType type) {
        this.type = type;
    }

    public Custom getCustom() {
        return custom;
    }

    public boolean isIsEquiped() {
        return isEquiped;
    }

    public void setIsEquiped(boolean isEquiped) {
        this.isEquiped = isEquiped;
    }

    public void setXpos(double xpos) {
        this.xpos = xpos;
    }

    public void setYpos(double ypos) {
        this.ypos = ypos;
    }

    public double getOriginalX() {
        return originalX;
    }

    public void setOriginalX(double originalX) {
        this.originalX = originalX;
    }

    public double getOriginalY() {
        return originalY;
    }

    public void setOriginalY(double originalY) {
        this.originalY = originalY;
    }

    public double getOffsetX() {
        return offsetX;
    }

    public double getOffsetY() {
        return offsetY;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.idNum;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Item other = (Item) obj;
        if (this.idNum != other.idNum) {
            return false;
        }
        return true;
    }
    
     
}
