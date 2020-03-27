/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pidev.entites.Evenement;
import pidev.utils.ConnectionBD;

/**
 *
 * @author safa
 */
public class EvenementService {
    
    Connection cnx = ConnectionBD.getInstance().getCnx();
    
    
    public void add(Evenement evenement){
        
        String req = "insert into evenement (nom, type, date, nbrPlace, dressCode,image) values(?, ?, ?, ?, ?, ?);";
        try {
            java.util.Date dateeve = new Date(evenement.getDate().getTime());
                PreparedStatement pst = cnx.prepareStatement(req);
                pst.setString(1, evenement.getNom());
                pst.setString(2, evenement.getType());
                pst.setDate(3, (Date)dateeve);
                pst.setInt(4, evenement.getNbrPlace());
                pst.setString(5, evenement.getDressCode());
                pst.setString(6, evenement.getImage());
                pst.executeUpdate();
                System.out.println("evenement crée!");
            } catch (SQLException ex) {
            }
    }
    
    public List<Evenement> getAllEvent()
   {
        List<Evenement> listEvenement = new ArrayList<>();
        try {
            String requete = "select * from evenement ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            
            while(rs.next()){
                Evenement evt = new Evenement();
                
               
                evt.setNom(rs.getString("Nom"));
               evt.setType(rs.getString("type"));
               evt.setDate (rs.getDate("date"));
               evt.setNbrPlace(rs.getInt("NbrPlace"));
               evt.setDressCode(rs.getString("DressCode"));
               evt.setImage(rs.getString("image"));
               
                listEvenement.add(evt);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return listEvenement;
    }
    
}
