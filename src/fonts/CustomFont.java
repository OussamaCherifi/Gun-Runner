/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fonts;

import java.io.IOException;
import java.io.InputStream;
import javafx.scene.text.Font;

/**
 *
 * @author ismai
 */
public class CustomFont {
    private static Font font;
    
    public CustomFont() throws IOException{
    }

    public static void setFont(Font font) {
        CustomFont.font = font;
    }
    

    public Font getFont() {
        return font;
    }
    
    
    
}
