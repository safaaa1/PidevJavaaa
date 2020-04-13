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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pidev.entites.Consultation;
import pidev.entites.ConsultationMedecin;
import pidev.utils.ConnectionBD;

/**
 *
 * @author yanisinfo
 */
public class Consultatiooooooon  {
    Connection cnx = ConnectionBD.getInstance().getCnx();
    public ObservableList<ConsultationMedecin> afficher()

   

     {

       
        ObservableList<ConsultationMedecin> listt = FXCollections.observableArrayList();
        String requete = "select enfant.nom ,medcin.nom,consultation.date_const from enfant,medcin ,consultation where id_medecin=medcin.id and enfant.id=consultation.id_enfant";
        
        try {
            PreparedStatement st = cnx.prepareStatement(requete);
            ResultSet rs = st.executeQuery();
            
            while(rs.next()){
                ConsultationMedecin res = new ConsultationMedecin(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getString(4), rs.getString(5),rs.getDate(6));
                
                
                
                listt.add(res);
                
            }
            
        } catch (SQLException ex) {
           System.err.println(ex.getMessage());
          
        }
        return listt;
    }

    
}
