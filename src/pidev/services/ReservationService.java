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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pidev.entites.Evenement;
import pidev.entites.Parentt;
import pidev.entites.Reservation;
import pidev.entites.ReservationUtilisateur;
import pidev.entites.Utilisateur;
import pidev.utils.ConnectionBD;
import pidev.utils.Session;

/**
 *
 * @author safa
 */
public class ReservationService {
    Connection cnx = ConnectionBD.getInstance().getCnx();
    public ObservableList<ReservationUtilisateur> getAll()
    {
        ObservableList<ReservationUtilisateur> listEvenement = FXCollections.observableArrayList();
        String requete = "select evenement.nom ,utilisateurs.nom,utilisateurs.prenom  ,etat,id_Reser,idUtilisateur ,email from evenement,reservation ,utilisateurs where idUtilisateur=utilisateurs.id and evenement.id_event=reservation.id_event";
        
        try {
            PreparedStatement st = cnx.prepareStatement(requete);
            ResultSet rs = st.executeQuery();
            
            while(rs.next()){
                ReservationUtilisateur res = new ReservationUtilisateur(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6),rs.getString(7));
                
                
                
                listEvenement.add(res);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return listEvenement;
    }
    
    
    public void valider(int idReser){
        
        String req = "update reservation set etat = ? where id_Reser = ?";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, "traite");
            pst.setInt(2, idReser);
            pst.executeUpdate();
            System.out.println(" traite!");
        } catch (SQLException ex) {
        }
    }
    
    
    
    
    
    public void annuler(int ReserId,Utilisateur loggedInUser) throws SQLException{
        int eventId=0;
        ReservationService res = new ReservationService();
        String re= "select id_event from reservation where id_Reser=?";
        try {
            PreparedStatement pst = cnx.prepareStatement(re);
            pst.setInt(1, ReserId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                eventId=rs.getInt(1);}
            
        } catch (SQLException ex) {
        }
        Evenement e=new Evenement();
        String req = "update evenement set nbrPlace = ? where id_event = ?";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, getQtOfProduct2(eventId) + 1);
            pst.setInt(2, eventId);
            pst.executeUpdate();
            
        } catch (SQLException ex) {
        }
        
        long millis=System.currentTimeMillis();
        java.sql.Date date=new java.sql.Date(millis);
        String req3 = "update reservation set etat = ? where id_Reser = ?";
        try {
            PreparedStatement pst = cnx.prepareStatement(req3);
            pst.setString(1, "annule");
            pst.setInt(2, ReserId);
            pst.executeUpdate();
            System.out.println(" reservation annulee!");
        } catch (SQLException ex) {
        }
        
    }
    public int getQtOfProduct2(int eventId) throws SQLException {
        int qt = 0 ;
        
        String req = "select nbrPlace from evenement where id_event = ?";
        
        PreparedStatement pst = cnx.prepareStatement(req);
        
        pst.setInt(1, eventId);
        System.out.println("33:" + eventId);
        ResultSet rs = pst.executeQuery();
        
        while (rs.next()) {
            
            qt = rs.getInt(1);
        }
        
        return qt;
    }
    
    
    
    
    
    
    
}
