/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.services;

import java.sql.Connection;
import static java.sql.JDBCType.NULL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pidev.entites.Reclamations;
import pidev.utils.ConnectionBD;

/**
 *
 * @author islem
 */
public class ReclamationsService {
    Connection cnx = ConnectionBD.getInstance().getCnx();
    
     public void add(Reclamations reclamations){
      
                
            
        String req = "insert into reclamations (nom, titre, email, tel, description,reponse_id) values(?, ?, ?, ?, ?, ?);";
        
        try {
                PreparedStatement pst = cnx.prepareStatement(req);
               
                pst.setObject(6, null);
                pst.setString(1, reclamations.getNom());
                pst.setString(2, reclamations.getTitre());
                pst.setString(3, reclamations.getEmail());
                pst.setInt(4, reclamations.getTel());
                pst.setString(5, reclamations.getDescription());
                pst.executeUpdate();
                System.out.println("reclamations crée!");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
    }
     
    /**
     *
     * @param r
     */
    public void Update(Reclamations r) {
        try {
            String req = "UPDATE reclamations SET nom=?,titre=?,email=?,tel=?,description=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(6, r.getId());
            pst.setString(1, r.getNom());
            pst.setString(2, r.getTitre());
            pst.setString(3, r.getEmail());
            pst.setInt(4, r.getTel());
            pst.setString(5, r.getDescription());
            pst.executeUpdate();
            System.out.println("Reclamations modifiÃ©e !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
        public void Delete(Reclamations r) {
        try {
            String req = "DELETE FROM Reclamations WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, r.getId());
            pst.executeUpdate();
            System.out.println("Reclamations supprimÃ©e !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public List<Reclamations> read() {
        List<Reclamations> list = new ArrayList<>();

        try {
            String req = "SELECT id,nom,titre,email,tel,description,reponse_id FROM reclamations";
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
             while (rs.next()) {
                 
                     
                 
                list.add(new Reclamations(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getInt(5), rs.getString(4),  rs.getString(6), rs.getInt(7)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
}
