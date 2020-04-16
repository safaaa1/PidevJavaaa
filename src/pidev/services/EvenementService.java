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
import pidev.entites.Reservation;
import pidev.entites.Utilisateur;
import pidev.utils.ConnectionBD;
import pidev.utils.Session;

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
    
    
    
    public ObservableList<Evenement> chercher(String mot){
        ObservableList<Evenement> list = FXCollections.observableArrayList();
        String req = "select * from evenement where nom like '"+mot+"%' or type like '"+mot+"%';";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Evenement p = new Evenement(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4),rs.getInt(5),rs.getString(6),rs.getString(7));
                list.add(p);
            }
        } catch (SQLException ex) {
        }
        System.out.println("evenement trouve ");
        return list;
    }
    
    
    
    
    
    
    
    
    public ObservableList<Evenement> getAllEvent()
   {
        
        ObservableList<Evenement> listEvenement = FXCollections.observableArrayList();
                    String requete = "select * from evenement ";

        try {
            PreparedStatement st = cnx.prepareStatement(requete);
            ResultSet rs = st.executeQuery();
            
            while(rs.next()){
                Evenement evt = new Evenement();
                
               evt.setIdEvent(rs.getInt("id_event"));
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


   
   
  
    public void remove(int idevent){
        
        String req = "delete from evenement where id_event = ?;";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, idevent);
            pst.executeUpdate();
            System.out.println("event removed!");
        } catch (SQLException ex) {
        }
    }
    public void supprimer(Evenement t) {
        try {
            String requete = "DELETE FROM evenement WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, t.getIdEvent());
            pst.executeUpdate();
            System.out.println("Enfant supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }    }
   
   public boolean modifierEvenement(String  nom,Evenement e)
    { 
    String requete="UPDATE evenement SET nom=?, type=?, date=?, nbrPlace=? , dressCode=? ,image=? where nom='"+nom+"'";
        try {
           java.util.Date date = new Date(e.getDate().getTime());
            PreparedStatement pst =cnx.prepareStatement(requete);
            pst.setString(1,e.getNom());
            pst.setString(1,e.getType());

            pst.setDate(3, (Date) date);
            pst.setInt(4,e.getNbrPlace());

            pst.setString(5,e.getDressCode());
            pst.setString(6,e.getImage());
             pst.executeUpdate();
            System.out.println("modifié avec succés");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
            return false;
    }
    public Evenement getByUser(String nom)
   {
       Utilisateur usr = Session.getLoggedInUser();
       
        Evenement ev = new Evenement();
        try {
            String requete = "select * from evenement where nom='"+nom+"'";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            
            while(rs.next()){
                
               
               usr.setNomUser(nom);
               ev.setNom(rs.getString("Nom"));
               ev.setType(rs.getString("type"));
               ev.setDate (rs.getDate("date"));
               ev.setNbrPlace(rs.getInt("nbrPlace"));
               ev.setDressCode(rs.getString("dressCode"));
               ev.setImage(rs.getString("image"));
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return ev;
    }



 public void modifier(Evenement t) {
        try {
            String requete = "UPDATE evenement SET nom=?,type=?,date=?,nbrPlace=?,dressCode=?,image=? WHERE id_event=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(7, t.getIdEvent());
            pst.setString(1, t.getNom());
            pst.setString(2, t.getType());
            pst.setDate(3, (Date) t.getDate());
            pst.setInt(4, t.getNbrPlace());

            pst.setString(5,t.getDressCode());
           pst.setString(6,t.getImage());

            pst.executeUpdate();
            System.out.println("Enfant modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }    }


 





 
   public void reserver(int eventId,Utilisateur loggedInUser) throws SQLException{
       ReservationService rs = new ReservationService();
       
        Evenement e=new Evenement();
            String req = "update evenement set nbrPlace = ? where id_event = ?";
               try {
               
                PreparedStatement pst = cnx.prepareStatement(req);
             
                pst.setInt(1, getNbr(eventId) - 1);
                pst.setInt(2, eventId);
 
                pst.executeUpdate();

            } catch (SQLException ex) {
            }
               
                           long millis=System.currentTimeMillis();
            java.sql.Date date=new java.sql.Date(millis);
               
                    //  Utilisateur user = Session.getLoggedInUser();
                      //System.out.println(user.getId());
                   System.out.println("1");
           Reservation r = new Reservation(eventId,4 ,"En cours", date,loggedInUser.getId());
           
                       String req2 = "INSERT INTO reservation(id_event,id, idUtilisateur,etat,dateReservation)VALUES(?,?,?,?,?)";
            System.out.println("INSERT INTO reservation(id_event,id, idUtilisateur,etat,dateReservation)VALUES(r.getIdEvent(),4,loggedInUser.getId(),r.getEtat(),(Date) r.getDateReservation())");

                PreparedStatement pst2 = cnx.prepareStatement(req2);
             
                pst2.setInt(1, r.getIdEvent());
               pst2.setInt(2,4);

                pst2.setInt(3, loggedInUser.getId());
                System.out.println("3 " + loggedInUser.getId());

                pst2.setString(4, r.getEtat());
                pst2.setDate(5, (Date) r.getDateReservation());
                System.out.println("4");

                pst2.executeUpdate();
                    System.out.println("creation");
      
           
               
        }
    public int getNbr(int eventId) throws SQLException {
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
     

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
   public int CountParcByCateg(String c) throws SQLException {  
     
     int count=0;
    String requete="SELECT count(*) from evenement where type='"+c+"'";
    Statement st;
    st = cnx.createStatement();
   try {
              st = cnx.createStatement(); 
              ResultSet rs = st.executeQuery(requete);
              while (rs.next()){
            count = rs.getInt("count(*)");
              }
            } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            
        }
        return count;
        

      
 } 
    
    
    
    
    public int CountParc() throws SQLException {  
     
     int count=0;
    String requete="SELECT count(*) from evenement ";
    Statement st;
    st = cnx.createStatement();
      try {
              st = cnx.createStatement(); 
              ResultSet rs = st.executeQuery(requete);
              while (rs.next()){
            count = rs.getInt("count(*)");
              }
            } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            
        }
        return count;
        

      
 }
    
    
    
    
    
    
    
    
    
    

}
