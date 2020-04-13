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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pidev.entites.Consultation;
import pidev.utils.ConnectionBD;

/**
 *
 * @author yanisinfo
 */
public class ServiceConsultation implements IService<Consultation> {
    Connection cnx = ConnectionBD.getInstance().getCnx();

    @Override
    public void ajouter(Consultation t) {
    try {
    String requete = "INSERT INTO `consultation`(`id_const`, `id_medecin`, `id_enfant`, `date_const`) VALUES (?,?,?,?)"; 
    PreparedStatement pst = cnx.prepareStatement(requete);
    pst.setInt(1, t.getIdCons());
    pst.setInt(2, t.getIdMed());
    pst.setInt(3,t.getIdEnf());
    pst.setDate(4, (Date) t.getDateCons());
    pst.executeUpdate();
    System.out.println("Consultation ajoutée !");
    } 
    catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
}

    @Override
    public void supprimer(Consultation t) {
        try {
        String requete = "DELETE FROM consultation WHERE id_const=?";
        PreparedStatement pst = cnx.prepareStatement(requete);
        pst.setInt(1, t.getIdCons());
        pst.executeUpdate();
        System.out.println("Consultation supprimé !");

    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
    }

    @Override
    public void modifier(Consultation t) {
        try {
        String requete = "UPDATE consultation SET id_medecin=?,id_enfant=?,date_const=? WHERE id_const=?";
        PreparedStatement pst = cnx.prepareStatement(requete);
        pst.setInt(4, t.getIdCons());
        pst.setInt(1, t.getIdMed());
        pst.setInt(2, t.getIdEnf());
        pst.setDate(3, t.getDateCons());
        pst.executeUpdate();
        System.out.println("Consultation modifiée !");

    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
    }

    @Override
    public List<Consultation> afficher() {
ObservableList <Consultation> list =FXCollections.observableArrayList();

        try {
           // String requete = "select * from consultation";
            
            String requete = "select consultation.id_const, medcin.nom,enfant.nom ,consultation.date_const from enfant,medcin ,consultation where consultation.id_medecin=medcin.id and enfant.id=consultation.id_enfant";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Consultation(rs.getInt(1),rs.getString(2), rs.getString(3),rs.getDate(4)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }

    
}
