/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scenes;

import java.io.FileNotFoundException;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;

/**
 *
 * @author oussa
 */
public class MainMenu extends Pane {

    private Button play;
    private Button unlockables;
    private Button barracks;
    private Button settings;
    private Button exit;

    public MainMenu() throws FileNotFoundException {
        this.play = new Button("Play");
        this.unlockables = new Button("Unlockables");
        this.barracks = new Button("Barracks");
        this.settings = new Button("Settings");
        this.exit = new Button("Exit");
        
        ButtonSize();

        BackgroundImage myBI = new BackgroundImage(new Image("preview/background.png", 1920, 1080, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);

        this.setBackground(new Background(myBI));

        this.getChildren().addAll(play, unlockables, barracks,settings ,exit);

    }

    private void ButtonSize() {

        this.play.setScaleX(5);
        this.play.setScaleY(3);
        this.play.setLayoutX(1920 / 2);
        this.play.setLayoutY(100);

        this.unlockables.setScaleX(4);
        this.unlockables.setScaleY(2);
        this.unlockables.setLayoutX(1920 / 2);
        this.unlockables.setLayoutY(200);

        this.barracks.setScaleX(4);
        this.barracks.setScaleY(2);
        this.barracks.setLayoutX(1920 / 2);
        this.barracks.setLayoutY(300);
        
        this.settings.setScaleX(3.5);
        this.settings.setScaleY(2);
        this.settings.setLayoutX(1920 / 2);
        this.settings.setLayoutY(400);
        
        this.exit.setScaleX(3);
        this.exit.setScaleY(2);
        this.exit.setLayoutX(1920 / 2);
        this.exit.setLayoutY(500);

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
