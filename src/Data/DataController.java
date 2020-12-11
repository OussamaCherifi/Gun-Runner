/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author 15148
 */
public class DataController {

    private static Connection conn = null;
    private static String fileName = "saveFile.db";
    
    public static void createConnectionWithDataBase(){
         //Creating the connection between database and code
        try {
            String connectionUrl = "jdbc:sqlite:src\\Data\\" + fileName;
            conn = DriverManager.getConnection(connectionUrl);
            System.out.println("Connection to the database -> " + fileName + " has been established.");
            initializeAllItemsById();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }       
    }
    
    private static void initializeAllItemsById(){
        String query;
        try{
            Statement stmt = conn.createStatement(); 
            for(int id = 1; id <= 12; id++){
                query = "Insert into items(id , isBought , isEquiped)\n"
                    + "values("+ id +" , 0 , 0)";
                stmt.execute(query);
            }            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    public static void buyItem(int id){
        String query = "Update items\n"
                + "set isBought = 1\n"
                + "where id = " + id;
        try{
            Statement stmt = conn.createStatement();
            stmt.execute(query);
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
    
    public static void equipItem(int id){
        String query = "Update items\n"
                + "set isEquiped = 1\n"
                + "where id = " + id;
        try{
            Statement stmt = conn.createStatement();
            stmt.execute(query);
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
    
    public static void unEquipItem(int id){
        String query = "Update items\n"
                + "set isEquiped = 0\n"
                + "where id = " + id;
        try{
            Statement stmt = conn.createStatement();
            stmt.execute(query);
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
    
    public static boolean isItemBought(int id){
        String query = "Select isbought from items where id = " + id;
        try{
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(query);
            int isBought = result.getInt("isBought");
            if(isBought == 1) return true;
            else return false;
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return false;
    }
    
    public static boolean isItemEquiped(int id){
        String query = "Select isEquiped from items where id = " + id;
        try{
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(query);
            int isEquiped = result.getInt("isEquiped");
            if(isEquiped == 1) return true;
            else return false;
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return false;
    }
    
    
    public static void chooseHelmet(){
        
    }
    
    public static void chooseTorsot(){
        
    }
    
    public static void chooseHands(){
        
    }
    
    public static void chooseBoots(){
        
    }
    
}
