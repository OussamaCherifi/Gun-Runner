/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamefinalversionhopefully;

import GUIController.GUIController;
import GameController.GameController;
import GameGUI.Map;
import scenes.Settings;
import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import scenes.BarracksPane;
import scenes.MainMenu;
import scenes.UnlockablesPane;

/**
 *
 * @author 15148
 */
public class GameFinalVersionHopefully extends Application {
    
    Map map;
    GameController gc;

    //This is just a second hand method to create the content seperately from the scene. 
    private Parent createGame(){
        map = new Map();
        gc = new GameController(map);
        return map;
    }
    
    
    @Override
    public void start(Stage stage) throws FileNotFoundException {
        Scene mainMenu, unlockables, barracks, settings;
        
        UnlockablesPane store = new UnlockablesPane();
        MainMenu main = new MainMenu();
        BarracksPane inventory = new BarracksPane();
        Settings set = new Settings();
        
        
        unlockables = new Scene(store, 1920, 1080);
        mainMenu = new Scene(main, 1920, 1080);
        barracks = new Scene(inventory, 1920, 1090);
        settings = new Scene(set, 1920,1080);
        
        GUIController c = new GUIController(store.getUnlock(), store, inventory, inventory.getBarrack(), inventory.getPrev());
        
        main.getUnlockables().setOnAction(e -> stage.setScene(unlockables));
        main.getExit().setOnAction(e -> stage.close());
        main.getBarracks().setOnAction(e -> stage.setScene(barracks));
        main.getSettings().setOnAction(e->stage.setScene(settings));
        main.getPlay().setOnAction(e ->{
            Scene game = new Scene(createGame());
            stage.setScene(game);
            game.setOnKeyPressed(gc.getKeyPressedController());
            game.setOnKeyReleased(gc.getKeyReleasedController());
            gc.getMap().getBack().setOnAction(b -> stage.setScene(mainMenu));
        });
    
                
        
        store.getBack().setOnAction(e -> stage.setScene(mainMenu));
        inventory.getBack().setOnAction(e -> stage.setScene(mainMenu));
        set.getBack().setOnAction(e->stage.setScene(mainMenu));
        
        
        
        stage.setScene(mainMenu);
        //primaryStage.setFullScreen(true);
        stage.show();
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
    
   
