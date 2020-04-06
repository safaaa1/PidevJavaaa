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
import pidev.entites.Medecin;
import pidev.utils.ConnectionBD;

/**
 *
 * @author yanisinfo
 */
public class ServiceMedecin implements IService<Medecin>{

           Connection cnx = ConnectionBD.getInstance().getCnx();

    @Override
    public void ajouter(Medecin t) {
        try {
            String requete = "INSERT INTO medcin (nom,tel,prenom,email) VALUES (?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, t.getNom());
            pst.setInt(2,t.getTel());
            pst.setString(3, t.getPrenom());
            pst.setString(4, t.getEmail());
            pst.executeUpdate();
            System.out.println("Medecin ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }    }

    @Override
    public void supprimer(Medecin t) {
        try {
            String requete = "DELETE FROM medcin WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, t.getIdMedecin());
            pst.executeUpdate();
            System.out.println("Medecin supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }    }

    @Override
    public void modifier(Medecin t) {
        try {
            String requete = "UPDATE medcin SET nom=?,tel=?,prenom=?,email=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(5, t.getIdMedecin());
            pst.setString(1, t.getNom());
            pst.setInt(2, t.getTel());
            pst.setString(3, t.getPrenom());
            pst.setString(4, t.getEmail());
            pst.executeUpdate();
            System.out.println("Medecin modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }    }

    @Override
    public List<Medecin> afficher() {
       // List<Medecin> list = new ArrayList<>();
       ObservableList <Medecin> list =FXCollections.observableArrayList();


        try {
            String requete = "SELECT * FROM medcin";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Medecin(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;    
    }
    
}
