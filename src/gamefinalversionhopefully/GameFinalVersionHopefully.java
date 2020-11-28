/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamefinalversionhopefully;

import GameController.GameController;
import GameGUI.Map;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author 15148
 */
public class GameFinalVersionHopefully extends Application {
    
    Map map;
    GameController gc;

    //This is just a second hand method to create the content seperately from the scene. 
    private Parent createContent(){
        map = new Map();
        gc = new GameController(map);
        return map;
    }
    
    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(createContent());
        scene.setOnKeyPressed(gc.getKeyPressedController());
        scene.setOnKeyReleased(gc.getKeyReleasedController());
        primaryStage.setScene(scene);
     //   primaryStage.setFullScreen(true);
        primaryStage.show();
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
