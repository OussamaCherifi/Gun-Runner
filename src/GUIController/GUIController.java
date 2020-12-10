/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIController;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import scenes.Barracks;
import scenes.BarracksPane;
import scenes.Item;
import scenes.Unlockables;
import scenes.UnlockablesPane;
import scenes.previewPane;

/**
 *
 * @author oussa
 */
public class GUIController {

    private ArrayList<Item> aquiredItems;
    private ArrayList<Item> ItemsToBuy;
    private Unlockables un;
    private UnlockablesPane pane;
    private Barracks bar;
    private BarracksPane barPane;
    private previewPane preview;
    private Item it;
    private ArrayList<Integer> orderOfPreview;

    public GUIController(Unlockables unlock, UnlockablesPane pane1, BarracksPane mainBrracks, Barracks barrack, previewPane characterView) throws FileNotFoundException {

        this.un = unlock;
        this.pane = pane1;

        this.ItemsToBuy = unlock.getListOfItems();
        this.aquiredItems = new ArrayList<Item>();
        this.barPane = new BarracksPane();
        this.bar = barrack;
        this.aquiredItems = bar.getListOfItemsBarracks();
        this.preview = characterView;
        addItem(ItemsToBuy);
        equipItem(aquiredItems);
    }

    private void addItem(ArrayList<Item> list) {
        for (Item it : list) {
            it.setBuyHandler(event -> buyButtonHandler(it));
        }

    }

    public void buyButtonHandler(Item acquiredItem) {
        if (acquiredItem.getPrice() <= pane.getBalanceAmount()) {
            if(pane.getChildren().contains(pane.getNotEnoughMoney())){
                pane.getChildren().remove(pane.getNotEnoughMoney());
                
            }
            un.getChildren().remove(acquiredItem.getBuyButton());
            int newBalance = pane.getBalanceAmount() - acquiredItem.getPrice();
            pane.setBalanceAmount(newBalance);
            pane.customBalanceLabel(newBalance);


            System.out.println(newBalance);

            int idnumber = acquiredItem.getIdNumber();

            for (int i = 0; i < ItemsToBuy.size(); i++) {
                if (idnumber == ItemsToBuy.get(i).getIdNumber()) {

                    Button equip = aquiredItems.get(i).getBuyButton();
                    equip.setText("Equip Item");

                    if (i < 6) {
                        bar.getChildren().remove(aquiredItems.get(i).getLabelNotPossessed());
                        bar.setHalignment(equip, HPos.CENTER);
                        bar.add(equip, i + 1, 2);
                        un.add(acquiredItem.getPossessedItem(), i + 1, 2); 
                        bar.setHalignment(acquiredItem.getPossessedItem(), HPos.CENTER);
                        
                    }
                    if (i < 12 && i > 5) {
                        bar.getChildren().remove(aquiredItems.get(i).getLabelNotPossessed());
                        bar.setHalignment(equip, HPos.CENTER);
                        bar.add(equip, i - 5, 5);
                        un.add(acquiredItem.getPossessedItem(), i - 5, 5);
                        bar.setHalignment(acquiredItem.getPossessedItem(), HPos.CENTER);

                    }
                }
            }
        } else if (acquiredItem.getPrice() > pane.getBalanceAmount()) {
            
            pane.InsertNotEnoughMoney();
        }
    }

    private void equipItem(ArrayList<Item> list) {
        for (Item it : list) {
            it.setBuyHandler(event -> equipButtonHandler(it));
        }

    }

    public void equipButtonHandler(Item acquiredItem) {
//
//        if (acquiredItem.getIdNumber() == 1) {
//            this.preview.InsertHelmet(1);
//        }
//        if (acquiredItem.getIdNumber() == 2) {
//            this.preview.InsertTorso(4);
//        }
//        if (acquiredItem.getIdNumber() == 3) {
//            this.preview.insertRightHand(5);
//            this.preview.inserLeftHand(6);
//        }
//        if (acquiredItem.getIdNumber() == 4) {
//            this.preview.insertRightFoot(2);
//            this.preview.insertLeftFoot(3);
//        }
//
//        if (acquiredItem.getIdNumber() == 7) {
//            this.preview.InsertHelmet(7);
//        }
//        if (acquiredItem.getIdNumber() == 7) {
//            this.preview.InsertHelmet(7);
//        }
//        if (acquiredItem.getIdNumber() == 8) {
//            this.preview.InsertTorso(8);
//        }
//        if (acquiredItem.getIdNumber() == 9) {
//            this.preview.insertRightHand(12);
//            this.preview.inserLeftHand(9);
//        }
//        if (acquiredItem.getIdNumber() == 10) {
//            this.preview.insertRightFoot(10);
//            this.preview.insertLeftFoot(11);
//        }
    }
}