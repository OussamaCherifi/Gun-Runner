/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIController;

import Data.DataController;
import static Data.DataController.unEquipItem;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import GameGUI.Barracks;
import GameGUI.BarracksPane;
import GameGUI.ItemNotInGame;
import GameGUI.Unlockables;
import GameGUI.UnlockablesPane;
import GameGUI.previewPane;

/**
 *
 * @author oussa
 */
public class GUIController {

    private ArrayList<ItemNotInGame> aquiredItemsList;
    private ArrayList<ItemNotInGame> ItemsToBuy;
    private Unlockables un;
    private UnlockablesPane pane;
    private Barracks bar;
    private BarracksPane barPane;
    private previewPane preview;
    
    //private ItemNotInGame it;
    private ArrayList<Integer> orderOfPreview;

    public GUIController(Unlockables unlock, UnlockablesPane pane1, BarracksPane mainBrracks, Barracks barrack, previewPane characterView) throws FileNotFoundException {

        this.un = unlock;
        this.pane = pane1;

        this.ItemsToBuy = unlock.getListOfItems();
        this.aquiredItemsList = new ArrayList<>(13);
        this.barPane = new BarracksPane();
        this.bar = barrack;
        this.aquiredItemsList = bar.getListOfItemsBarracks();
        this.preview = characterView;
        addItem(ItemsToBuy);
        setEquipButtonOnAction(aquiredItemsList);
    }

    private void addItem(ArrayList<ItemNotInGame> list) {
        for (ItemNotInGame it : list) {
            it.setBuyHandler(event -> buyButtonHandler(it));
        }
    }

    public void buyButtonHandler(ItemNotInGame acquiredItem) {
        if (acquiredItem.getPrice() <= pane.getBalanceAmount()) {
            if(pane.getChildren().contains(pane.getNotEnoughMoney())){
                pane.getChildren().remove(pane.getNotEnoughMoney());
            }
            
            un.getChildren().remove(acquiredItem.getBuyButton());
            int newBalance = pane.getBalanceAmount() - acquiredItem.getPrice();
            DataController.updateBalance(newBalance);
            
            pane.setBalanceAmount(newBalance);
            pane.customBalanceLabel(newBalance);

            int idnumber = acquiredItem.getIdNumber();
            for (int i = 0; i < ItemsToBuy.size(); i++) {
                if (idnumber == ItemsToBuy.get(i).getIdNumber()) {
                    if (i < 6) {
                        bar.getChildren().remove(aquiredItemsList.get(i).getLabelNotPossessed());
                        bar.setHalignment(ItemsToBuy.get(i).getEquipButton(), HPos.CENTER);
                        bar.add(ItemsToBuy.get(i).getEquipButton(), i + 1, 2);
                        un.add(acquiredItem.getPossessedItem(), i + 1, 2); 
                        bar.setHalignment(acquiredItem.getPossessedItem(), HPos.CENTER);
                    }
                    
                    if (i < 12 && i > 5) {
                        bar.getChildren().remove(aquiredItemsList.get(i).getLabelNotPossessed());
                        bar.setHalignment(ItemsToBuy.get(i).getEquipButton(), HPos.CENTER);
                        bar.add(ItemsToBuy.get(i).getEquipButton(), i - 5, 5);
                        un.add(acquiredItem.getPossessedItem(), i - 5, 5);
                        bar.setHalignment(acquiredItem.getPossessedItem(), HPos.CENTER);
                    }
                }
            }
            
            
            aquiredItemsList.add(acquiredItem);
            setEquipButtonOnAction(aquiredItemsList);
            DataController.buyItem(idnumber);
        } else if (acquiredItem.getPrice() > pane.getBalanceAmount()) {
            pane.InsertNotEnoughMoney();
        }
    }
    
    private void insertEquipItem(){
        
    }
    
    private void setEquipButtonOnAction(ArrayList<ItemNotInGame> list) {
        for (ItemNotInGame it : list) {
            it.setEquipButtonHandler(event -> {
                equipButtonHandler(it); 
                
                //SQL
                int id = it.getIdNumber(); 
                DataController.equipItem(id);
            });
        }
    }
    
    private void equipUnequipeEquipButton(int equiping , int unequiping){
      //  if(!aquiredItemsList.get(unequiping).isBought()){
      
    }
    
    
    public void equipButtonHandler(ItemNotInGame acquiredItem) {
        

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