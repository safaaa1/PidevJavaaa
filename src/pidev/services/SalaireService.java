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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pidev.entites.Reclamations;
import pidev.entites.Salaire;
import pidev.utils.ConnectionBD;

/**
 *
 * @author islem
 */
public class SalaireService {
    Connection cnx = ConnectionBD.getInstance().getCnx();
    java.util.Date dt = new java.util.Date();
    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
      
    
     public void add(Salaire s , int idens){
        /*
        String req = "insert into salaire (id, chiffre, prime, date) values(?, ?, ?, ?);";
        try {
                PreparedStatement pst = cnx.prepareStatement(req);
                pst.setInt(1, s.getId());
                pst.setInt(2, s.getChiffre());
                pst.setInt(3, s.getPrime());
                pst.setDate(4, (Date) s.getDate());
                pst.executeUpdate();
                System.out.println("salaire crée!");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }*/
        //
        Statement ps;
        try {
            ps = cnx.createStatement();
            ps.executeUpdate("insert into salaire values (" + null + "," + s.getChiffre()+ ","+ s.getPrime()+",'"
                        + sdf.format(s.getDate())+ "')");
            
            
            String req = "UPDATE Enseignant SET  salaire_id=?  WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, read().get(read().size()-1).getId());
            pst.setInt(2, idens);
         
            
            System.out.println("//"+read().get(read().size()-1).getId()+"//"+idens);
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        

    }
         public void delete(Salaire s) {
        try {
            String req = "DELETE FROM salaire WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, s.getId());
            pst.executeUpdate();
            System.out.println("Salaire supprimÃ©e !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
     public void Update(Salaire s) {
        
        try {
            Statement ps = cnx.createStatement();
            ps.executeUpdate(" , date = "
                    + "UPDATE salaire SET chiffre = "+ s.getChiffre()+ " , prime = " + s.getPrime()+ " , date" + s.getDate() +  " WHERE id = " + s.getId());
          
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
     
         
    public List<Salaire> read() {
        List<Salaire> list = new ArrayList<>();
Salaire s;
        try {
            String req = "SELECT * FROM salaire";
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
             while (rs.next()) {
                  s= new Salaire(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getDate(4));
                list.add(s);
                       
                 System.out.println(s+"aaaaaa");
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
    
}
