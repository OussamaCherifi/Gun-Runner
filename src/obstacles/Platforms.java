/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obstacles;

import GameGUI.Map;

/**
 *
 * @author 15148
 */
public class Platforms extends Obstacles {

    public Platforms(String path, double x, double y, String type) {
        super(path, x, y, type);
    }

    @Override
    protected void enemySpawn(Map map) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
