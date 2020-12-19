/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameGUI;

import Data.DataController;
import java.io.File;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.text.Font;


/**
 *
 * @author oussa
 */
public class Settings extends Pane {

    Slider volumeSlider;
    MediaPlayer musicPlayer;
    TextField tf;
    ObservableList<KeyBindClass> keyList;
    Button applySettings;
    KeyBindClass defaultJump;
    KeyBindClass defaultDescend;
    KeyBindClass defaultShoot;
    Label keyBindError;
    KeyCode finalJumpKey;
    KeyCode finalDescendKey;
    KeyCode finalShootKey;
    ToggleGroup difficultyGroup;
    private Button back;

    public Settings() {

        Label settingsName = settingsName = new Label("Settings");
        settingsName.setLayoutX(1920 / 2);
        settingsName.setLayoutY(20);
        settingsName.setScaleX(3);
        settingsName.setScaleY(3);
        settingsName.setTextFill(Color.web("#7FFF00", 0.8));
        settingsName.setFont(new Font("Broadway", 12));

        Font font2 = new Font("Impact", 20);
        this.back = new Button("Back");
        this.back.setPrefSize(152, 64);
        this.back.setLayoutY(900);
        this.back.setLayoutX(30);
        this.back.getStylesheets().add("styles/button-small.css");
        this.back.setFont(font2);

        this.back.setTextFill(Color.web("#ff0000", 0.8));

        double setNameWidth = settingsName.getWidth();
        settingsName.setLayoutX((1920 / 2) - (setNameWidth / 2));
        settingsName.setLayoutY(25);
        settingsName.setScaleX(4);
        settingsName.setScaleY(4);

        // Creating and setting up the volume slider
        createVolumeSlider();
        // ToggleButton group for game difficulty
        createDifficultyGroup();
        createKeyBindings();
        applyAllSettings();

        tf = new TextField();

        tf.setOnKeyPressed(new keyChangedController());

        BackgroundImage myBI = new BackgroundImage(new Image("preview/bg.png", 1920 / 2, 1080, false, true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        this.setBackground(new Background(myBI));

        this.getChildren().addAll(settingsName, volumeSlider, back);
    }

    // Volume Slider Function
    public void createVolumeSlider() {

        // Label on top of slider
        Label volumeName = new Label("Volume:");
        double volNameWidth = volumeName.getWidth();
        volumeName.setLayoutX(1920 / 2 - volNameWidth / 2);
        volumeName.setLayoutY(120);
        volumeName.setScaleX(2);
        volumeName.setScaleY(2);
        volumeName.setTextFill(Color.web("#7FFF00", 0.8));
        volumeName.setFont(new Font("Broadway", 9));

        this.getChildren().add(volumeName);

        // Volume Slider properties and placement
        volumeSlider = new Slider(0, 10, 5);
        volumeSlider.setShowTickMarks(false);
        volumeSlider.setShowTickLabels(false);
        volumeSlider.setMajorTickUnit(1);
        volumeSlider.setBlockIncrement(1);
        volumeSlider.setMinorTickCount(0);
        volumeSlider.setShowTickMarks(false);
        volumeSlider.setSnapToTicks(true);

        double volSliderWidth = volumeSlider.getWidth();
        volumeSlider.setLayoutX(910);
        volumeSlider.setLayoutY(200);
        volumeSlider.setScaleX(2.5);
        volumeSlider.setScaleY(2);

        // Function that changes volume according to slider value
        volumeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                musicPlayer.setVolume((double) newValue / 10);

            }
        });

    }

    // Function that retrieves difficulty level
    public void createDifficultyGroup() {

        difficultyGroup = new ToggleGroup();

        // Label 
        Label diffName = new Label("Game Difficulty:");
        double diffNameWidth = diffName.getWidth();
        diffName.setLayoutX(1920 / 2 - diffNameWidth / 2 - 20);
        diffName.setLayoutY(350);
        diffName.setScaleX(2);
        diffName.setScaleY(2);
        diffName.setTextFill(Color.web("#7FFF00", 0.8));
        diffName.setFont(new Font("Broadway", 9));

        // Toggle buttons for 3 game difficulties
        ToggleButton easyMode = new ToggleButton("Easy");
        easyMode.setToggleGroup(difficultyGroup);
        easyMode.setSelected(true);
        easyMode.setOnAction(e -> {
            DataController.setDifficultyToEasy();
        });

        ToggleButton normalMode = new ToggleButton("Normal");
        normalMode.setToggleGroup(difficultyGroup);
        normalMode.setOnAction(e -> {
            DataController.setDifficultyToNormal();
        });

        ToggleButton hardMode = new ToggleButton("Hard");
        hardMode.setToggleGroup(difficultyGroup);
        hardMode.setOnAction(e -> {
            DataController.setDifficultyToHard();
        });

        // Toggle Buttons properites and placement
        double eModeWidth = easyMode.getWidth();
        double nModeWidth = normalMode.getWidth();

        easyMode.setLayoutX(1920 / 2 - 160);
        easyMode.setLayoutY(425);
        easyMode.setScaleX(2.5);
        easyMode.setScaleY(2.5);

        normalMode.setLayoutX(1920 / 2);
        normalMode.setLayoutY(425);
        normalMode.setScaleX(2.5);
        normalMode.setScaleY(2.5);

        hardMode.setLayoutX(1140);
        hardMode.setLayoutY(425);
        hardMode.setScaleX(2.5);
        hardMode.setScaleY(2.5);

        this.getChildren().addAll(diffName, easyMode, normalMode, hardMode);
    }

    public String getDifficulty() {

        ToggleButton diffSelection = (ToggleButton) difficultyGroup.getSelectedToggle();
        String diffSelectionString = diffSelection.getText();

        return diffSelectionString;

    }

    // Key Binding Function
    public void createKeyBindings() {
        // Label

        Label keybindLabel = new Label("Key Bindings:");
        double keyLabelWidth = keybindLabel.getWidth();
        keybindLabel.setLayoutX(1920 / 2 - keyLabelWidth / 2 - 20);
        keybindLabel.setLayoutY(600);
        keybindLabel.setScaleX(2);
        keybindLabel.setScaleY(2);
        keybindLabel.setTextFill(Color.web("#7FFF00", 0.8));
        keybindLabel.setFont(new Font("Broadway", 9));

        //Table View for keybinding
        TableView keyBindTable = new TableView<>();

        // Three Columns for Action - Current Key - New Key     
        TableColumn<String, String> actionColumn = new TableColumn<>("Action");
        TableColumn<String, Label> currentKeyColumn = new TableColumn<>("Current Key");
        TableColumn<String, TextField> newKeyColumn = new TableColumn<>("New Key Binding");
        newKeyColumn.setMinWidth(150);
        keyBindTable.setMaxHeight(120);

        defaultJump = new KeyBindClass("Jump", new Label(DataController.getJumpKey()), new TextField(""));
        defaultDescend = new KeyBindClass("Descend", new Label(DataController.getDescendKey()), new TextField(""));
        defaultShoot = new KeyBindClass("Shoot", new Label(DataController.getShootKey()), new TextField(""));

        // Applying Function that limits text field of new key entered to 1 character only
        addTextLimiter(defaultJump.getNewKey());
        addTextLimiter(defaultDescend.getNewKey());
        addTextLimiter(defaultShoot.getNewKey());

        // Applying function that changes value of previous key if key isn't already assigned
        defaultJump.getNewKey().setOnKeyPressed(new JumpKeyChangedController());
        defaultDescend.getNewKey().setOnKeyPressed(new DescendKeyChangedController());
        defaultShoot.getNewKey().setOnKeyPressed(new ShootKeyChangedController());

        keyList = FXCollections.observableArrayList(
                defaultJump, defaultDescend, defaultShoot
        );

        // Adding values of KeyBindClass to Table
        actionColumn.setCellValueFactory(new PropertyValueFactory<String, String>("actionName"));
        currentKeyColumn.setCellValueFactory(new PropertyValueFactory<String, Label>("previousKey"));
        newKeyColumn.setCellValueFactory(new PropertyValueFactory<String, TextField>("newKey"));

        //Assinging table properties and placement
        keyBindTable.setItems(keyList);

        keyBindTable.getColumns().setAll(actionColumn, currentKeyColumn, newKeyColumn);

        keyBindTable.setScaleX(2);
        keyBindTable.setScaleY(2);

        keyBindTable.setLayoutX(840);
        keyBindTable.setLayoutY(700);

        keyBindError = new Label();
        keyBindError.setLayoutX(1500);
        keyBindError.setLayoutY(750);
        keyBindError.setScaleX(2);
        keyBindError.setScaleY(2);
        keyBindError.setTextFill(Color.RED);

        this.getChildren().addAll(keybindLabel, keyBindTable, keyBindError);

    }

    //Function Limiting New Key Entered to 1 Character only
    public static void addTextLimiter(final TextField tf) {
        tf.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {

                if (tf.getText().length() > 1) {
                    tf.deleteNextChar();
                    String textLimit = tf.getText().substring(0, 1);
                    tf.setText(textLimit);
                }
            }
        });
    }

    // Function that creates media
    public void createMusic() {

        String path2 = "/music/CountryRoads.mp3";
        String path = "C:\\Users\\bryan\\Desktop\\GUI Final Project\\CountryRoads.mp3";

        Media media = new Media(new File(path).toURI().toString());

        musicPlayer = new MediaPlayer(media);

        musicPlayer.setAutoPlay(true);

    }

    // Function handling action for Apply Settings Button : Game Difficulty Change and Key Binding
    public void applyAllSettings() {

        Button applySettings = new Button("Apply Settings");
        double applySettingsWidth = applySettings.getWidth();
        applySettings.setLayoutX(1920 / 2 - applySettingsWidth / 2 - 20);
        applySettings.setLayoutY(950);
        applySettings.setScaleX(2);
        applySettings.setScaleY(2);

        applySettings.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // To write: retrieve keybindings with the get functions for each key and save in save file
                
            }
        });

        this.getChildren().add(applySettings);

    }

    public keyChangedController getKeyChangedController() {
        return new keyChangedController();
    }

    private class keyChangedController implements EventHandler<KeyEvent> {
        @Override
        public void handle(KeyEvent event) {

        }

    }

    // Handler for New Key TextField in Key Binding (Only changes prev key if key is not already assigned to another action)
    private class JumpKeyChangedController implements EventHandler<KeyEvent> {
        @Override
        public void handle(KeyEvent event) {
            KeyCode keyC = event.getCode();
            TextField newField = new TextField(keyC.toString());
            defaultJump.setNewKey(newField);

            if (defaultJump.getNewKey().getText().equalsIgnoreCase(defaultShoot.getPreviousKey().getText())
                    || defaultJump.getNewKey().getText().equalsIgnoreCase(defaultDescend.getPreviousKey().getText())) {

                keyBindError.setText("Key was Assigned to another Action, Retry");

            } else {

                defaultJump.getPreviousKey().setText(keyC.toString());
                finalJumpKey = keyC;
                keyBindError.setText("");
                addTextLimiter(defaultJump.getNewKey());
                
            }
            
            DataController.setJumpKey(keyC.getName());
        }
    }

    // Handler for New Key TextField in Key Binding (Only changes prev key if key is not already assigned to another action)
    private class DescendKeyChangedController implements EventHandler<KeyEvent> {
        @Override
        public void handle(KeyEvent event) {

            KeyCode keyC = event.getCode();
            TextField newField = new TextField(keyC.toString());
            defaultDescend.setNewKey(newField);

            if (defaultDescend.getNewKey().getText().equalsIgnoreCase(defaultJump.getPreviousKey().getText())
                    || defaultDescend.getNewKey().getText().equalsIgnoreCase(defaultShoot.getPreviousKey().getText())) {

                keyBindError.setText("Key was Assigned to another Action, Retry");

            } else {

                defaultDescend.getPreviousKey().setText(keyC.toString());
                finalDescendKey = keyC;
                keyBindError.setText("");
                addTextLimiter(defaultDescend.getNewKey());

            }
            
            DataController.setDescendKey(keyC.getName());
        }
    }

    // Handler for New Key TextField in Key Binding (Only changes prev key if key is not already assigned to another action)
    private class ShootKeyChangedController implements EventHandler<KeyEvent> {
        @Override
        public void handle(KeyEvent event) {
            KeyCode keyC = event.getCode();
            TextField newField = new TextField(keyC.toString());
            defaultShoot.setNewKey(newField);

            if (defaultShoot.getNewKey().getText().equalsIgnoreCase(defaultDescend.getPreviousKey().getText())
                    || defaultShoot.getNewKey().getText().equalsIgnoreCase(defaultJump.getPreviousKey().getText())) {

                keyBindError.setText("Key was Assigned to another Action, Retry");

            } else {
                defaultShoot.getPreviousKey().setText(keyC.toString());
                finalShootKey = keyC;
                keyBindError.setText("");
                addTextLimiter(defaultShoot.getNewKey());
            }
            DataController.setShootKey(keyC.getName());
        }

    }

    // Functions retrieving Key Codes of 3 actions : is used when Apply Settings Button is pressed (Saved to save file)
    public KeyCode getJumpKey() {
        return finalJumpKey;
    }

    public KeyCode getDescendKey() {
        return finalDescendKey;
    }

    public KeyCode getShootKey() {
        return finalShootKey;
    }

    public Button getBack() {
        return back;
    }

    public void setBack(Button back) {
        this.back = back;
    }

}
