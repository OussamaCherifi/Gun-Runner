/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamefinalversionhopefully;

import Data.DataController;
import GUIController.GUIController;
import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author 15148
 */
public class GameFinalVersionHopefully extends Application {
    

    @Override
    public void start(Stage stage) throws FileNotFoundException {
        
        GUIController c = new GUIController();
        c.setMenuButtonHandlers(stage);
        c.start(stage);
        
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DataController.createConnectionWithDataBase();
        launch(args);
    }
}
    
   
