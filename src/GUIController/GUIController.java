/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIController;

import Data.DataController;
import GameController.GameController;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javafx.geometry.HPos;
import GameGUI.Barracks;
import GameGUI.BarracksPane;
import GameGUI.ItemNotInGame;
import GameGUI.MainMenu;
import GameGUI.Map;
import GameGUI.Settings;
import GameGUI.Unlockables;
import GameGUI.UnlockablesPane;
import GameGUI.previewPane;
import items.InGameItems;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 *
 * @author oussa
 */
public class GUIController {

    private ArrayList<ItemNotInGame> aquiredItemsList;
    private ArrayList<ItemNotInGame> ItemsToBuy;
    private Unlockables unlock;
    private UnlockablesPane store;
    private Barracks barracks;
    private BarracksPane inventory;
    private MainMenu menu = new MainMenu();
    private Map map;
    private GameController gc;
    private Settings settings;
    private Scene unlockScene, menuScene, barrackScene, settingScene;
    private Popup popup = new Popup();
    
    //private ItemNotInGame it;
    private ArrayList<Integer> orderOfPreview;

    public GUIController() throws FileNotFoundException{
        
        aquiredItemsList = new ArrayList<>(13);
        settings = new Settings();
        
        setUnlock();
        setBarrack();
        
        //preview = characterView;
        
        settingScene();
    }
    
    private void setUnlock() throws FileNotFoundException{
        store = new UnlockablesPane();
        unlock = store.getUnlock();
        ItemsToBuy = unlock.getListOfItems();
        addItem(ItemsToBuy);
    }
    
    private void setBarrack() throws FileNotFoundException{
        inventory = new BarracksPane();
        barracks = inventory.getBarrack();
        aquiredItemsList = barracks.getListOfItemsBarracks();
        setEquipButtonOnAction(aquiredItemsList);
    }
    
    private void settingScene(){
        double x = 1920;
        double y = 1080;
        unlockScene = new Scene(store, x, y);
        menuScene = new Scene(menu, x, y);
        barrackScene = new Scene(inventory, x, y);
        settingScene = new Scene(settings, x, y);
    }

    private void addItem(ArrayList<ItemNotInGame> list) {
        for (ItemNotInGame it : list) {
            it.setBuyHandler(event -> buyButtonHandler(it));
        }
    }

    public void buyButtonHandler(ItemNotInGame acquiredItem) {
        if (acquiredItem.getPrice() <= store.getBalanceAmount()) {
            if(store.getChildren().contains(store.getNotEnoughMoney())){
                store.getChildren().remove(store.getNotEnoughMoney());
            }
            
            unlock.getChildren().remove(acquiredItem.getBuyButton());
            int newBalance = store.getBalanceAmount() - acquiredItem.getPrice();
            DataController.updateBalance(newBalance);
            
            store.setBalanceAmount(newBalance);
            store.customBalanceLabel(newBalance);

            int idnumber = acquiredItem.getIdNumber();
            for (int i = 0; i < ItemsToBuy.size(); i++) {
                if (idnumber == ItemsToBuy.get(i).getIdNumber()) {
                    if (i < 6) {
                        barracks.getChildren().remove(aquiredItemsList.get(i).getLabelNotPossessed());
                        barracks.setHalignment(ItemsToBuy.get(i).getEquipButton(), HPos.CENTER);
                        barracks.add(ItemsToBuy.get(i).getEquipButton(), i + 1, 2);
                        unlock.add(acquiredItem.getPossessedItem(), i + 1, 2); 
                        barracks.setHalignment(acquiredItem.getPossessedItem(), HPos.CENTER);
                    }
                    
                    if (i < 12 && i > 5) {
                        barracks.getChildren().remove(aquiredItemsList.get(i).getLabelNotPossessed());
                        barracks.setHalignment(ItemsToBuy.get(i).getEquipButton(), HPos.CENTER);
                        barracks.add(ItemsToBuy.get(i).getEquipButton(), i - 5, 5);
                        unlock.add(acquiredItem.getPossessedItem(), i - 5, 5);
                        barracks.setHalignment(acquiredItem.getPossessedItem(), HPos.CENTER);
                    }
                }
            }
            
            
            aquiredItemsList.add(acquiredItem);
            setEquipButtonOnAction(aquiredItemsList);
            DataController.buyItem(idnumber);
        } else if (acquiredItem.getPrice() > store.getBalanceAmount()) {
            store.InsertNotEnoughMoney();
        }
    }
    
    private void insertEquipItem(){
        
    }
    
    private void setEquipButtonOnAction(ArrayList<ItemNotInGame> list) {
        for (ItemNotInGame it : list) {
            it.setEquipButtonHandler(event -> {
                int id = it.getIdNumber(); 
                InGameItems i = it.toInGameItems(id, 56, 232);
                
                //SQL
                DataController.equipItem(id);
                //Insert in preview
                inventory.getPreview().insertItem(i);
            });
        }
    }
    
    
    
    public void setMenuButtonHandlers (Stage stage){
        menu.getUnlockables().setOnAction(e -> {
                try {
                setUnlock();
                } catch (FileNotFoundException ex) {
                Logger.getLogger(GUIController.class.getName()).log(Level.SEVERE, null, ex);
                }
                unlockScene = new Scene(store, 1920, 1080);
                store.getBack().setOnAction(b -> stage.setScene(menuScene));
                stage.setScene(unlockScene);
        });
        
        menu.getBarracks().setOnAction(e ->{
                try {
                setBarrack();
                } catch (FileNotFoundException ex) {
                Logger.getLogger(GUIController.class.getName()).log(Level.SEVERE, null, ex);
                }
                barrackScene = new Scene(inventory, 1920, 1080);
                inventory.getBack().setOnAction(c -> stage.setScene(menuScene));
                stage.setScene(barrackScene);
                
        });
        
        menu.getSettings().setOnAction(e->
                stage.setScene(settingScene));
        
        menu.getPlay().setOnAction(e ->{
            try {
                Scene game = new Scene(createGame());
                stage.setScene(game);
                game.setOnKeyPressed(gc.getKeyPressedController());
                game.setOnKeyReleased(gc.getKeyReleasedController());
                map.getBack().setOnAction(b -> {
                    gc.close();
                    stage.setScene(menuScene);   
                });
            } catch (IOException ex) {
                Logger.getLogger(GUIController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        //store.getBack().setOnAction(e -> stage.setScene(menuScene));
        //inventory.getBack().setOnAction(e -> stage.setScene(menuScene));
        settings.getBack().setOnAction(e->stage.setScene(menuScene));
        
        menu.getExit().setOnAction(e -> stage.close());
        menu.getHelp().setOnAction(e -> {
            Font font2 = new Font("Impact", 20);
            Label label = new Label("\t\t\t\t HELP : \n"
                    + "The game is inspired by Jetpack Joyride. The goal is simple, survive as long as possible to the \n"
                    + "horde of zombies that are trying to kill you. \n"
                    + "-To purchase items, visit unlockables and select the wanted item.\n" +
                    "-To equip a certain item, visit the Barracks menu. See for yourself the look of your player in the preview.\n" +
                    "-You can find your highest score in the ....\n" +
                    "-To modify the level of difficulty, visit the settings menu and choose between the options. Also use settings to modify your controls.\n" +
                    "-Your game will come to an end once the player takes too many zombie bullets.\n" +
                    "Good luck!");
            label.setStyle(" -fx-background-color: black;"); 
            label.setFont(font2);
            label.setTextFill(Color.WHITE);
            popup.getContent().add(label);
            if (!popup.isShowing()) {
                    popup.show(stage); 
                    popup.setX(450);
                    popup.setAnchorX(450);
            }
            else {
                popup.hide();
            } 
        });
    }
    
    private Parent createGame() throws IOException{
        map = new Map();
        gc = new GameController(map);
        return map;
    }
    
    public void start(Stage stage){
        stage.setScene(menuScene);
        //primaryStage.setFullScreen(true);
        stage.show();
    }

    public void updatePreview(InGameItems acquiredItem) {
        inventory.getPreview().insertItem(acquiredItem);
   }
}