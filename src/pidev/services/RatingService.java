/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import pidev.entites.Rating;
import pidev.utils.ConnectionBD;

/**
 *
 * @author safa
 */
public class RatingService {
  private static RatingService instance;
    public static RatingService getInstance() throws SQLException {
       
         if (instance == null) {
            instance = new RatingService();
        }
        return instance;//To change body of generated methods, choose Tools | Templates.
    }
     public Connection con = ConnectionBD.getInstance().getCnx();
     public Statement ste;
     public RatingService() throws SQLException 
    {
        ste=con.createStatement();
      
}
     
     
     public boolean ajouterAvis(Rating g) throws SQLException {
       String req="INSERT INTO avis (note) VALUES (?)";
        Rating a =new Rating();
       // User user=Session.getLoggedInUser();
        //Userservice us= new Userservice();
       try {
       
       PreparedStatement pre= con.prepareStatement(req);
        
        
        pre.setDouble(1, g.getNote());
        pre.executeUpdate();   
     System.out.println("ajout reussit");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
            return false;
    }
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
}