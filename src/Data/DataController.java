/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import items.Custom;
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
            initializePlayer();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }       
    }
    
    private static void initializePlayer(){
        String query = "insert into player(id , balance ,highscore)"
                + "values(1 , 1500 , 0)";
        try{
            Statement stmt = conn.createStatement(); 
            stmt.execute(query);           
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
    
    public static void updateBalance(int newBalance){
        String query = "Update player\n"
                + "set balance = " + newBalance + "\n"
                + "where id = 1;";
        try{
            Statement stmt = conn.createStatement();
            stmt.execute(query);
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }   
    }
    
    public static int getBalance(){
        String query = "select balance from player where id = 1;";
        int balance = 0;
        try{
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(query);
            balance = result.getInt("balance");
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        
        return balance;
    }
        
    public static int getHighScore(){
        String query = "select highscore from player where id = 1;";
        int hs = 0;
        try{
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(query);
            hs = result.getInt("highscore");
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        
        return hs;
    }
    
    public static void setHighScore(int highscore){
        String query = "Update player\n"
                + "set highscore = " + highscore + "\n"
                + "where id = 1;";
        try{
            Statement stmt = conn.createStatement();
            stmt.execute(query);
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
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
        
        for(int i = 1; i < 5; i++){
            if(id == i) unEquipItem(i + 6);
        }
        for(int j = 7; j < 11; j++){
            if(id == j) unEquipItem(j - 6);
        }

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
    
    
    public static Custom chooseHelmet(){
        String query = "Select isEquiped from items where id = 1";
        String query2 = "Select isEquiped from items where id = 7";

        try{
            Statement stmt = conn.createStatement();
            Statement stmt2 = conn.createStatement();
            ResultSet result =  stmt.executeQuery(query);
            ResultSet result2 = stmt2.executeQuery(query2);
            System.out.println(result.getInt("isEquiped"));
            if(result.getInt("isEquiped") == 1){
                return Custom.c1;
            }else if(result2.getInt("isEquiped") == 1){
                return Custom.c2;
            }else{
                return Custom.normal;
            }
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return Custom.normal;
    }
    
    public static Custom chooseTorsot(){
       String query = "Select isEquiped from items where id = 2";
        String query2 = "Select isEquiped from items where id = 8";

        try{
            Statement stmt = conn.createStatement();
            Statement stmt2 = conn.createStatement();

            ResultSet result =  stmt.executeQuery(query);
            ResultSet result2 = stmt2.executeQuery(query2);
            
            if(result.getInt("isEquiped") == 1){
                return Custom.c1;
            }else if(result2.getInt("isEquiped") == 1){
                return Custom.c2;
            }else{
                return Custom.normal;
            }
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return Custom.normal; 
    }
    
    public static Custom chooseHands(){
       String query = "Select isEquiped from items where id = 3";
        String query2 = "Select isEquiped from items where id = 9";

        try{
            Statement stmt = conn.createStatement();
            Statement stmt2 = conn.createStatement();
            ResultSet result =  stmt.executeQuery(query);
            ResultSet result2 = stmt2.executeQuery(query2);
            
            if(result.getInt("isEquiped") == 1){
                return Custom.c1;
            }else if(result2.getInt("isEquiped") == 1){
                return Custom.c2;
            }else{
                return Custom.normal;
            }
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return Custom.normal;
    }
    
    public static Custom chooseBoots(){
       String query = "Select isEquiped from items where id = 4";
        String query2 = "Select isEquiped from items where id = 10";

        try{
            Statement stmt = conn.createStatement();
            Statement stmt2 = conn.createStatement();
            ResultSet result =  stmt.executeQuery(query);
            ResultSet result2 = stmt2.executeQuery(query2);
            
            if(result.getInt("isEquiped") == 1){
                return Custom.c1;
            }else if(result2.getInt("isEquiped") == 1){
                return Custom.c2;
            }else{
                return Custom.normal;
            }
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return Custom.normal;
    }
    
    
    public static Custom chooseUzi(){
        String query = "Select isEquiped from items where id = 5";
        try{
            Statement stmt = conn.createStatement();
            ResultSet result =  stmt.executeQuery(query);
            
            if(result.getInt("isEquiped") == 1){
                return Custom.c1;
            }
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return Custom.normal;
    }
    
    public static Custom choosePistol(){
        String query = "Select isEquiped from items where id = 6";

        try{
            Statement stmt = conn.createStatement();
            ResultSet result =  stmt.executeQuery(query);
            
            if(result.getInt("isEquiped") == 1){
                return Custom.c1;
            }
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return Custom.normal;
    }
    
    public static Custom chooseAk(){
        String query = "Select isEquiped from items where id = 11";

        try{
            Statement stmt = conn.createStatement();
            ResultSet result =  stmt.executeQuery(query);
            
            if(result.getInt("isEquiped") == 1){
                return Custom.c1;
            }
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return Custom.normal;
    }
    
    public static Custom chooseBullets(){
        String query = "Select isEquiped from items where id = 5";

        try{
            Statement stmt = conn.createStatement();
            ResultSet result =  stmt.executeQuery(query);
            
            if(result.getInt("isEquiped") == 1){
                return Custom.c1;
            }
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
               
        return Custom.normal;
    }
    
    public static void resetData(){
        String query = "Drop table player";
    }
    
    public static void setJumpKey(String jumpKey){
        String query = "Update controls\n"
                + "set jump = '" + jumpKey + "'\n"
                + "where id = 1";
        try{
            Statement stmt = conn.createStatement();
            stmt.execute(query);
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }        
    }
    
    public static void setDescendKey(String DescendKey){
        String query = "Update controls\n"
                + "set descend = '" + DescendKey +"' \n"
                + "where id = 1";
        try{
            Statement stmt = conn.createStatement();
            stmt.execute(query);
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }    
    }
    
    public static void setShootKey(String shootKey){
        String query = "Update controls\n"
                + "set shoot = '" + shootKey + "'\n"
                + "where id = 1";
        try{
            Statement stmt = conn.createStatement();
            stmt.execute(query);
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
    
    public static String getJumpKey(){
        String query = "Select jump from controls where id = 1";
        String key = "";
        try{
            Statement stmt = conn.createStatement();
            ResultSet jumpKey = stmt.executeQuery(query);
            key = jumpKey.getString("jump");
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        
        return key;
    }
    
    public static String getShootKey(){
        String query = "Select shoot from controls where id = 1";
        String key = "";
        try{
            Statement stmt = conn.createStatement();
            ResultSet jumpKey = stmt.executeQuery(query);
            key = jumpKey.getString("shoot");
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        
        return key;
    }
    
    public static String getDescendKey(){
        String query = "Select descend from controls where id = 1";
        String key = "";
        try{
            Statement stmt = conn.createStatement();
            ResultSet jumpKey = stmt.executeQuery(query);
            key = jumpKey.getString("descend");
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        
        return key;
    }
    
}
