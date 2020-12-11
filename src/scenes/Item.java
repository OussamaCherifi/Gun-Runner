/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scenes;

import Data.DataController;
import java.io.FileNotFoundException;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 *
 * @author oussa
 */
public class Item extends ImageView {

    private Label labelPrice;
    private Label LabelNotPossessed;
    private Label PossessedItem;
    private int price;
    private Image ItemImage;
    private Button BuyButton;
    private int idNumber;
    
    
    //These values are for the database
    private boolean isBought = false;
    private boolean isEquiped = false;

    public Item(int b) throws FileNotFoundException {
        this.idNumber = b;
        this.price = price;
        String a = Integer.toString(b);
        givePrice(b);
        this.LabelNotPossessed = new Label("Purchase in store");
        this.PossessedItem = new Label("Acquired");
        this.labelPrice = new Label("Price: " + price);
        this.setImage(new Image("preview/preview_" + a + ".png"));
        this.BuyButton = new Button("Buy Item");
        customizeLabel();
        
        isBought = DataController.isItemBought(idNumber);
        isEquiped = DataController.isItemEquiped(idNumber);
    }
    
    private void customizeLabel(){
        this.LabelNotPossessed.setFont(new Font("Arial", 20));
        this.PossessedItem.setFont(new Font("Arial", 20));
        this.PossessedItem.setTextFill(Color.web("#FFFFFF", 0.8));
        this.LabelNotPossessed.setTextFill(Color.web("#FFFFFF", 0.8));
        
    }

    public Button getBuyButton() {
        return BuyButton;
    }

    public void setBuyButton(Button BuyButton) {
        this.BuyButton = BuyButton;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void givePrice(int priceDecide) {
        if (priceDecide <= 4) {
            this.price = 500;
        }
        if (priceDecide <= 6 && priceDecide > 4) {
            this.price = 1000;
        }
        if (priceDecide <= 10 && priceDecide > 6) {
            this.price = 1500;
        }
        if (priceDecide == 11) {
            this.price = 2000;
        }
        if (priceDecide == 12) {
            this.price = 2500;
        }
    }

    public void setBuyHandler(EventHandler handler) {
        this.BuyButton.setOnAction(handler);
    }

    public int getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }

    public Image getItemImage() {
        return ItemImage;
    }

    public void setItemImage(Image ItemImage) {
        this.ItemImage = ItemImage;
    }

    public Label getLabelPrice() {
        return labelPrice;
    }

    public void setLabelPrice(Label labelPrice) {
        this.labelPrice = labelPrice;
    }

    public Label getLabelNotPossessed() {
        return LabelNotPossessed;
    }

    public void setLabelNotPossessed(Label LabelNotPossessed) {
        this.LabelNotPossessed = LabelNotPossessed;
    }

    public Label getPossessedItem() {
        return PossessedItem;
    }

    public void setPossessedItem(Label PossessedItem) {
        this.PossessedItem = PossessedItem;
    }

    public boolean isBought() {
        return isBought;
    }

    public void setIsBought(boolean isBought) {
        this.isBought = isBought;
    }

    public boolean isEquiped() {
        return isEquiped;
    }

    public void setIsEquiped(boolean isEquiped) {
        this.isEquiped = isEquiped;
    }

}
