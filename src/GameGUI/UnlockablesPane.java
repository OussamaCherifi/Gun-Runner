/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameGUI;

import Data.DataController;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

/**
 *
 * @author oussa
 */
public class UnlockablesPane extends Pane {

    private Label title;
    private Button back;
    private Unlockables Unlock;
    private Label balance;
    private int balanceAmount;
    private ArrayList<ItemNotInGame> copyItems;
    private Label NotEnoughMoney;
    private gridBackground gBg;
    private Font textFont;

    public UnlockablesPane() throws FileNotFoundException {
        customTitle();
        Font font2 = new Font("Impact", 20);

        back = new Button("Back");
        back.setPrefSize(152, 64);
        back.setLayoutY(900);
        back.setLayoutX(30);
        back.getStylesheets().add("styles/button-small.css");
        back.setFont(font2);  

        balanceAmount = DataController.getBalance();

        balance = new Label("Balance: " + balanceAmount + "$");
        balance.setTextFill(Color.web("#ededed", 0.8));
        balance.setLayoutY(210);
        balance.setLayoutX(474);
        balance.setScaleX(2.5);
        balance.setScaleY(2.5);
        balance.setFont(new Font("Impact", 12));
        
        

        Unlock = new Unlockables();
        Unlock.setLayoutX(1920 / 4);
        Unlock.setLayoutY(325);

        BackgroundImage myBI = new BackgroundImage(new Image("preview/bg.png", 1920/2, 1080, false, true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        setBackground(new Background(myBI));
        customBalanceLabel(balanceAmount);
        
        gBg = new gridBackground(400, 275);
        
        getChildren().addAll(title, back, gBg, Unlock, balance);
    }

    private void customTitle() {
        title = new Label("Unlockables");
        title.setLayoutX(1920 / 2);
        title.setLayoutY(20);
        title.setScaleX(3);
        title.setScaleY(3);
        title.setTextFill(Color.web("#ededed", 0.8));
        title.setFont(new Font("Impact", 12));
    }

    public void customBalanceLabel(int money) {
        balance.setText("Balance: " + money + "$");
    }

    public void InsertNotEnoughMoney() {

        NotEnoughMoney = new Label("Not enough credits");
        NotEnoughMoney.setLayoutX(1920 / 2);
        NotEnoughMoney.setLayoutY(200);
        NotEnoughMoney.setScaleX(3);
        NotEnoughMoney.setScaleY(3);
        NotEnoughMoney.setTextFill(Color.web("#DC143C", 0.8));
        NotEnoughMoney.setFont(new Font("Impact", 12));

        getChildren().add(NotEnoughMoney);
    }

    public ArrayList<ItemNotInGame> getCopyItems() {
        return copyItems;
    }

    public void setCopyItems(ArrayList<ItemNotInGame> copyItems) {
        this.copyItems = copyItems;
    }

    public Unlockables getUnlock() {
        return Unlock;
    }

    public void setUnlock(Unlockables Unlock) {
        this.Unlock = Unlock;
    }

    public Button getBack() {
        return back;
    }

    public void setBack(Button back) {
        this.back = back;
    }

    public int getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(int balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public Label getBalance() {
        return balance;
    }

    public void setBalance(Label balance) {
        this.balance = balance;
    }

    public Label getNotEnoughMoney() {
        return NotEnoughMoney;
    }

    public void setNotEnoughMoney(Label NotEnoughMoney) {
        this.NotEnoughMoney = NotEnoughMoney;
    }

}
