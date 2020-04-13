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
import pidev.entites.Enfant;
import pidev.utils.ConnectionBD;

/**
 *
 * @author yanisinfo
 */
public class ServiceEnfant implements IService<Enfant>{
      Connection cnx = ConnectionBD.getInstance().getCnx();


    @Override
    public void ajouter(Enfant t) {
   
        try {
     
            String requete = "INSERT INTO enfant (nom,age,id_dossier) VALUES (?,?,?)"; 
            PreparedStatement pst = cnx.prepareStatement(requete);
           pst.setString(1, t.getNom());
            pst.setInt(2, t.getAge());
            pst.setInt(3,t.getId_dossier());
            pst.executeUpdate();
            System.out.println("Enfant ajouté !");
            } 
        catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(Enfant t) {
        try {
            String requete = "DELETE FROM enfant WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, t.getIdEnfant());
            pst.executeUpdate();
            System.out.println("Enfant supprimé !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }    }

    @Override
    public void modifier(Enfant t) {
        try {
            String requete = "UPDATE enfant SET nom=?,age=?,id_dossier=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(4, t.getIdEnfant());
            pst.setString(1, t.getNom());
            pst.setInt(2, t.getAge());
            pst.setInt(3,t.getId_dossier());
            pst.executeUpdate();
            System.out.println("Enfant modifié !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }    }

    @Override
    public List<Enfant> afficher() {
ObservableList <Enfant> list =FXCollections.observableArrayList();

        try {
            String requete = "SELECT enfant.id, enfant.nom,enfant.age ,dossier_medical.titre FROM `enfant`,dossier_medical WHERE dossier_medical.id=enfant.id_dossier;";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Enfant(rs.getInt(1),rs.getString(2), rs.getInt(3), rs.getString(4)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }    
}
