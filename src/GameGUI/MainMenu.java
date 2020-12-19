/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameGUI;

import Data.DataController;
import java.io.FileNotFoundException;
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
import javafx.scene.text.Font;

/**
 *
 * @author oussa
 */
public class MainMenu extends Pane {
    private Label top = new Label("Gun");
    private Label bottom = new Label("Runner");
    private Label highscore = new Label();
    private Button play = new Button("Play");
    private Button unlockables = new Button("Unlockables");
    private Button barracks  = new Button("Barracks");
    private Button settings  = new Button("Settings");
    private Button exit = new Button("Exit");
    private Button help = new Button("Help");

    public MainMenu() throws FileNotFoundException {
        setupButtons();
        setupLabels();
        //String path = "C:\\Users\\15148\\OneDrive\\Documents\\GameWithCleanMenu\\AlmostFinishedGame\\src\\sprites\\resources\\bg_menu.png";
        //BackgroundImage myBI = new BackgroundImage(new Image(path, 1920, 1080, false, true),
        BackgroundImage myBI = new BackgroundImage(new Image("sprites/resources/bg_menu.png", 1920, 1080, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);

        this.setBackground(new Background(myBI));

        this.getChildren().addAll(highscore,top, bottom, play, unlockables, barracks,settings ,exit, help);

    }
    
    private void setupLabels(){
        Font font2 = new Font("Impact", 100);
        Font font = new Font("Impact", 30);
        top.setFont(font2);
        top.setTextFill(Color.valueOf("#ededed"));
        top.setLayoutX(1920/2 - top.getWidth()/2 - 20);
        top.setLayoutY(50);
        
        bottom.setFont(font2);
        bottom.setTextFill(Color.valueOf("#ededed"));
        bottom.setLayoutX(1920/2 - 70);
        bottom.setLayoutY(170);
        
        highscore.setFont(font);
        highscore.setLayoutX(1200);
        highscore.setLayoutY(50);
        highscore.setTextFill(Color.valueOf("#99c2c2"));
        highscore.setText("Highscore: " + DataController.getHighScore());
        
    }

    private void setupButtons() {
        double x = 1920/2 + 54; 
        double c = 20;
        Font font = new Font("Impact", 32);
        Font font2 = new Font("Impact", 20);
//        this.play.setScaleX(5);
//        this.play.setScaleY(3);
//        this.play.setLayoutX(1920 / 2);
//        this.play.setLayoutY(100);
        
        this.play.setPrefSize(192, 128);
        this.play.setLayoutX(x - 192/2+c);
        this.play.setLayoutY(300);
        this.play.getStylesheets().add("styles/button-large.css");
        this.play.setFont(font);

        this.unlockables.setPrefSize(192, 64);
        this.unlockables.setLayoutX(x - 192/2+c);
        this.unlockables.setLayoutY(play.getLayoutY()+150);
        this.unlockables.getStylesheets().add("styles/button-medium.css");
        this.unlockables.setFont(font2);    
        
        this.barracks.setPrefSize(192, 64);
        this.barracks.setLayoutX(x - 192/2+c);
        this.barracks.setLayoutY(unlockables.getLayoutY()+80);
        this.barracks.getStylesheets().add("styles/button-medium.css");
        this.barracks.setFont(font2);  
        
        this.settings.setPrefSize(192, 64);
        this.settings.setLayoutX(x - 192/2+c);
        this.settings.setLayoutY(barracks.getLayoutY()+80);
        this.settings.getStylesheets().add("styles/button-medium.css");
        this.settings.setFont(font2);  
        
        this.exit.setPrefSize(152, 64);
        this.exit.setLayoutX(x - 152/2+c);
        this.exit.setLayoutY(settings.getLayoutY()+100);
        this.exit.getStylesheets().add("styles/button-small.css");
        this.exit.setFont(font2);  
        
        this.help.setPrefSize(152, 64);
        this.help.setLayoutX(x + 300);
        this.help.setLayoutY(exit.getLayoutY()+150);
        this.help.getStylesheets().add("styles/button-small.css");
        this.help.setFont(font2);

    }

    public Button getHelp() {
        return help;
    }

    public void setHelp(Button help) {
        this.help = help;
    }

    public Button getPlay() {
        return play;
    }

    public void setPlay(Button play) {
        this.play = play;
    }

    public Button getUnlockables() {
        return unlockables;
    }

    public void setUnlockables(Button unlockables) {
        this.unlockables = unlockables;
    }

    public Button getBarracks() {
        return barracks;
    }

    public void setBarracks(Button barracks) {
        this.barracks = barracks;
    }

    public Button getExit() {
        return exit;
    }

    public void setExit(Button exit) {
        this.exit = exit;
    }

    private void setImage(Image image) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Button getSettings() {
        return settings;
    }

    public void setSettings(Button settings) {
        this.settings = settings;
    }
    
    

}