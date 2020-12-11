/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameGUI;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author oussa
 */
public class KeyBindClass {
    
        
        String actionName;
        Label previousKey;
        TextField newKey;

        public KeyBindClass(String actionName, Label previousKey, TextField newKey) {
            this.actionName = actionName;
            this.previousKey = previousKey;
            this.newKey = newKey;
        }

        public String getActionName() {
            return actionName;
        }

        public void setActionName(String actionName) {
            this.actionName = actionName;
        }

        public Label getPreviousKey() {
            return previousKey;
        }

        public void setPreviousKey(Label previousKey) {
            this.previousKey = previousKey;
        }

        public TextField getNewKey() {
            return newKey;
        }

        public void setNewKey(TextField newKey) {
            this.newKey = newKey;
        }

    
        
        
        
        
        
        
    }
    
