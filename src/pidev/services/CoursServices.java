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
import pidev.entites.Cours;
import pidev.utils.ConnectionBD;

/**
 *
 * @author Lenovo
 */
public class CoursServices implements IService<Cours> {
    Connection cnx = ConnectionBD.getInstance().getCnx();
    
    public void ajouter(Cours t) {
       String req="insert into cours (idcl,nomcours,duree,listeens,idenfant,idenseignant) values(?,?,?,?,?,?);";
        
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
          
            pst.setInt(2, t.getIdcl());
            pst.setString(3, t.getNomcours());
            pst.setString(4, t.getDuree());
            pst.setString(5, t.getListeens());
            pst.setInt(6, t.getIdenfant());
            pst.setInt(7, t.getIdenseignant());
            pst.executeUpdate();
            System.err.println("cours Ajoutee ...");
        } catch (SQLException ex) {
            Logger.getLogger(CoursServices.class.getName()).log(Level.SEVERE, null, ex);
        }  }

    
    public void supprimer(Cours t) {
       String req ="delete from cours where id=?";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, t.getId());
            System.out.println("cours supprimee...");
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CoursServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public void modifier(Cours t) {
        try {
            String requete = "UPDATE cours SET idcl=?,nomcours=?,duree=?,listeens=?,idenfant=?,idenseignant=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(7, t.getId());
            pst.setInt(1, t.getIdcl());
            pst.setString(2, t.getNomcours());
            pst.setString(3, t.getDuree());
            pst.setString(4, t.getListeens());
            pst.setInt(5, t.getIdenfant());
            pst.setInt(6, t.getIdenseignant());
            pst.executeUpdate();
            System.out.println("cours modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } }

   
    public List<Cours> afficher() {
        List<Cours> list = new ArrayList<>();
       String req ="select * from cours";
           
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
            Cours c = new Cours(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getInt(7));
             list.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       return list;
    }
    
}
