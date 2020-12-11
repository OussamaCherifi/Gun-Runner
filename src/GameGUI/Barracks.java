/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameGUI;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

/**
 *
 * @author oussa
 */
public class Barracks extends GridPane {

    private Label title;
    ArrayList<ItemNotInGame> ListOfItemsBarracks = new ArrayList(12);
    ItemNotInGame it;

    public Barracks() throws FileNotFoundException {

        this.title = new Label("Barracks");

        InsertImagesInGrid(ListOfItemsBarracks);
        insertLabels();
        this.setHgap(20);
        this.setVgap(24);
        this.setAlignment(Pos.CENTER);

    }

    public void InsertImagesInGrid(ArrayList<ItemNotInGame> items) throws FileNotFoundException {

        for (int i = 1; i < 13; i++) {
            ListOfItemsBarracks.add(new ItemNotInGame(i));
        }

        for (int i = 0; i < items.size(); i++) {
            if (i < 6) {
                ListOfItemsBarracks.get(i).setFitHeight(128);
                ListOfItemsBarracks.get(i).setFitWidth(128);
                this.add(ListOfItemsBarracks.get(i), i + 1, 1);

            }
            if (i < 12 && i > 5) {
                ListOfItemsBarracks.get(i).setFitHeight(128);
                ListOfItemsBarracks.get(i).setFitWidth(128);
                this.add(ListOfItemsBarracks.get(i), i - 5, 4);
            }
        }

    }

    public void insertLabels() {
        for (int i = 0; i < ListOfItemsBarracks.size(); i++) {
            if(!ListOfItemsBarracks.get(i).isBought()){
                Label notPossessed = ListOfItemsBarracks.get(i).getLabelNotPossessed();
                notPossessed.setAlignment(Pos.CENTER);
                notPossessed.setTextFill(Color.web("#FFA500", 0.8));   
                if (i < 6) {
                    this.setHalignment(notPossessed, HPos.CENTER);
                    this.add(notPossessed, i + 1, 2);
                }
                if (i < 12 && i > 5) {
                    this.setHalignment(notPossessed, HPos.CENTER);
                    this.add(notPossessed, i - 5, 5);
                }                
            }else{
                if (i < 6) {
                    this.setHalignment(ListOfItemsBarracks.get(i).getEquipButton(), HPos.CENTER);
                    this.add(ListOfItemsBarracks.get(i).getEquipButton(), i + 1, 2);
                }
                if (i < 12 && i > 5) {
                    this.setHalignment(ListOfItemsBarracks.get(i).getEquipButton(), HPos.CENTER);
                    this.add(ListOfItemsBarracks.get(i).getEquipButton(), i - 5, 5);
                }    
            }
            
        }

    }
    

    public Label getTitle() {
        return title;
    }

    public void setTitle(Label title) {
        this.title = title;
    }

    public ItemNotInGame getIt() {
        return it;
    }

    public void setIt(ItemNotInGame it) {
        this.it = it;
    }

    public ArrayList<ItemNotInGame> getListOfItemsBarracks() {
        return ListOfItemsBarracks;
    }

    public void setListOfItemsBarracks(ArrayList<ItemNotInGame> ListOfItemsBarracks) {
        this.ListOfItemsBarracks = ListOfItemsBarracks;
    }

}
