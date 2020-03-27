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
            String requete = "INSERT INTO enfant (nom,age) VALUES (?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, t.getNom());
            pst.setInt(2, t.getAge());
            pst.executeUpdate();
            System.out.println("Enfant ajoutée !");

        } catch (SQLException ex) {
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
            System.out.println("Enfant supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }    }

    @Override
    public void modifier(Enfant t) {
        try {
            String requete = "UPDATE enfant SET nom=?,age=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(3, t.getIdEnfant());
            pst.setString(1, t.getNom());
            pst.setInt(2, t.getAge());
            pst.executeUpdate();
            System.out.println("Enfant modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }    }

    @Override
    public List<Enfant> afficher() {
        List<Enfant> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM enfant";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Enfant(rs.getInt(1), rs.getString(2), rs.getInt("age")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }    
}
