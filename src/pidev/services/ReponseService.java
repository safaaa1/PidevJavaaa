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
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import static jdk.nashorn.internal.runtime.Debug.id;

import pidev.entites.Reponse;
import pidev.utils.ConnectionBD;

/**
 *
 * @author islem
 */

    public class ReponseService {
    Connection cnx = ConnectionBD.getInstance().getCnx();
    
     public void add(Reponse rp , int id){
                               
    //    String req = "insert into reponse (id, nom, titre, email, tel, description) values(?, ?, ?, ?, ?, ?);";
        String req = "insert into reponse ( nom, titre, email, tel, description) values( ?, ?, ?, ?, ?);";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);               
           // pst.setInt(1, rp.getId());
            pst.setString(1, rp.getNom());
            pst.setString(2, rp.getTitre());
            pst.setString(3, rp.getEmail());
            pst.setInt(4, rp.getTel());
            pst.setString(5, rp.getDescription());
            pst.executeUpdate();
    
                System.out.println("reponse crée!");
            //    System.out.println("recl"+id+"idreclam"+;
                /*
                 String req2 = "UPDATE reclamations SET reponse_id=? WHERE id=?";
                 PreparedStatement pst2 = cnx.prepareStatement(req2);  
                 pst2.setInt(1, read().get(read().size()-1).getId());
                 pst2.setInt(2, id);
                 pst2.executeUpdate();*/
        } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        /*
        
        try {
            String req2 = "UPDATE reclamations SET reponse_id=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(2, id);       
            pst.setInt(1, read().get(read().size()-1).getId()); 
            pst.executeUpdate();
            System.out.println("Reclamations modifiÃ©e !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
   */
    }
     
    /**
     *
     * @param rp
     */
    public void Update(Reponse rp) {
        try {
            String req = "UPDATE reponse SET nom=?,titre=?,email=?,tel=?,description=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(6, rp.getId());
            pst.setString(1, rp.getNom());
            pst.setString(2, rp.getTitre());
            pst.setString(3, rp.getEmail());
            pst.setInt(4, rp.getTel());
            pst.setString(5, rp.getDescription());
            pst.executeUpdate();
            System.out.println("Reponse modifiÃ©e !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
        public void Delete(Reponse rp) {
        try {
            String req = "DELETE FROM Reponse WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, rp.getId());
            pst.executeUpdate();
            System.out.println("Reponse supprimÃ©e !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public List<Reponse> read() {
          ObservableList <Reponse> list =FXCollections.observableArrayList();

        try {
            String req = "SELECT rp.id,rp.nom,rp.titre,rp.email,rp.tel,rp.description FROM reponse rp ";
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
             while (rs.next()) {
             
               
                 int id=rs.getInt("rp.id");
                 String nom=rs.getString("rp.nom");
                 String titre=rs.getString("rp.titre");
                 String email=rs.getString("rp.email");
                 int tel=rs.getInt("rp.tel");
                 String description=rs.getString("rp.description");
        
                 Reponse rp=new Reponse(id,nom,titre,email,tel,description);
                 System.out.println(rp.toString());
                list.add(rp);
                        
            
       } 
        }
             catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }

    public void Delete() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getrep(int id) {
          ObservableList <Reponse> list =FXCollections.observableArrayList();

        try {
            String req = "SELECT description FROM reponse where id="+id;
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
             while (rs.next())   return rs.getString("description");
      
        }
        catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return "Pas de reponse";
    }



    
}
