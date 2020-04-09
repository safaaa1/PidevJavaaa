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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pidev.entites.Grade;
import pidev.utils.ConnectionBD;

/**
 *
 * @author Lenovo
 */
public class GradeServices implements IService<Grade> {
Connection cnx = ConnectionBD.getInstance().getCnx();


    public void ajouter(Grade g) {
         String req="insert into grade (nbrclasse,nbrenfgr,nomgr) values(?,?,?);";
        
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            
            pst.setInt(1, g.getNbrclasse());
            pst.setInt(2, g.getNbrenfgr());
            pst.setString(3, g.getNomgr());
            pst.executeUpdate();
            System.err.println("Grade Ajoutee ...");
        } catch (SQLException ex) {
            Logger.getLogger(GradeServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
     }


    public void supprimer(Grade g) {
       String req ="delete from grade where idgr=?";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, g.getIdgr());
            System.out.println("Grade supprimee...");
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(GradeServices.class.getName()).log(Level.SEVERE, null, ex);
        }  }

    
    public void modifier(Grade g) {
       try {
            String requete = "UPDATE Grade SET nbrclasse=?,nbrenfgr=?,nomgr=? WHERE idgr=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(4, g.getIdgr());
            pst.setInt(1, g.getNbrclasse());
            pst.setInt(2, g.getNbrenfgr());
            pst.setString(3, g.getNomgr());
            pst.executeUpdate();
            System.out.println("Grade modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }  }

    public List<Grade> afficher() {
       ObservableList <Grade> list =FXCollections.observableArrayList();
       String req ="select * from grade";
           
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
            Grade g = new Grade(rs.getInt(1),rs.getInt(2), rs.getInt(3),rs.getString(4));
             list.add(g);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       return list;
        }
    
}
