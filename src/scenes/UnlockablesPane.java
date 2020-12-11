/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scenes;

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
    private ArrayList<Item> copyItems;
    private Label NotEnoughMoney;
    private Rectangle gridBackground;
    private Font textFont;

    public UnlockablesPane() throws FileNotFoundException {
        customTitle();

        this.back = new Button("Back");
        this.back.setLayoutY(900);
        this.back.setLayoutX(30);

        this.back.setScaleX(1.25);
        this.back.setScaleY(1.25);
        this.back.setTextFill(Color.web("#ff0000", 0.8));

        this.balanceAmount = 2500;

        this.balance = new Label("Balance: " + balanceAmount + "$");
        this.balance.setTextFill(Color.web("#7FFF00", 0.8));
        this.balance.setLayoutY(250);
        this.balance.setLayoutX(550);
        this.balance.setScaleX(2.5);
        this.balance.setScaleY(2.5);
        this.balance.setFont(new Font("Broadway", 12));

        this.gridBackground = new Rectangle(950, 525);
        this.gridBackground.setLayoutX(470);
        this.gridBackground.setLayoutY(290);
        this.gridBackground.setStroke(Color.AZURE);
        this.gridBackground.setStrokeWidth(2);

        this.Unlock = new Unlockables();
        this.Unlock.setLayoutX(1920 / 4);
        this.Unlock.setLayoutY(325);

        BackgroundImage myBI = new BackgroundImage(new Image("preview/bg.png", 1920, 1080, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        this.setBackground(new Background(myBI));
        customBalanceLabel(balanceAmount);

        this.getChildren().addAll(title, back, gridBackground, Unlock, balance);

    }

    private void customTitle() {
        this.title = new Label("Unlockables");
        this.title.setLayoutX(1920 / 2);
        this.title.setLayoutY(20);
        this.title.setScaleX(3);
        this.title.setScaleY(3);
        this.title.setTextFill(Color.web("#7FFF00", 0.8));
        this.title.setFont(new Font("Broadway", 12));
    }

    public void customBalanceLabel(int money) {
        this.balance.setText("Balance: " + money + "$");
    }

    public void InsertNotEnoughMoney() {

        this.NotEnoughMoney = new Label("Not enough credits");
        NotEnoughMoney.setLayoutX(1920 / 2);
        NotEnoughMoney.setLayoutY(200);
        NotEnoughMoney.setScaleX(3);
        NotEnoughMoney.setScaleY(3);
        NotEnoughMoney.setTextFill(Color.web("#DC143C", 0.8));
        NotEnoughMoney.setFont(new Font("Broadway", 12));

        this.getChildren().add(NotEnoughMoney);
    }

    public ArrayList<Item> getCopyItems() {
        return copyItems;
    }

    public void setCopyItems(ArrayList<Item> copyItems) {
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
