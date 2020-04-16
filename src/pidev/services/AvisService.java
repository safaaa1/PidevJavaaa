/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pidev.entites.Avis;
import pidev.entites.Evenement;
import pidev.entites.ReservationUtilisateur;
import pidev.entites.Utilisateur;
import pidev.utils.ConnectionBD;
import pidev.utils.Session;

/**
 *
 * @author safa
 */
public class AvisService {
 
 private static AvisService instance;
    public static AvisService getInstance() throws SQLException {
       
         if (instance == null) {
            instance = new AvisService();
        }
        return instance;//To change body of generated methods, choose Tools | Templates.
    }
     public Connection cnx = ConnectionBD.getInstance().getCnx();
     public Statement ste;
     public AvisService() throws SQLException 
    {
        ste=cnx.createStatement();
    }
        
    
    public void ajouterAvis(String description ,int eventId,Utilisateur loggedInUser,double rating) throws SQLException {
        Avis g=new Avis();
        String req="INSERT INTO avis (id_event,id,idUtilisateur,description, rating) VALUES (?,?,?,?,?)";
       // Utilisateur user=Session.getLoggedInUser();
      //  UtilisateurService us= new UtilisateurService();
       try {
       
       PreparedStatement pre= cnx.prepareStatement(req);
        
    pre.setInt(1, eventId);
    pre.setInt(2,4);
    pre.setInt(3, loggedInUser.getId());
    System.out.println("3 " + loggedInUser.getId());

    pre.setString(4, description);    
    pre.setDouble(5, rating);

    pre.executeUpdate();   
     System.out.println("ajout reussit");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
            
            
           
            
            
            
            
    }
    
    
    
    
    
    
    
   
    
    
    
    
    
    
    
    
    
    
}
