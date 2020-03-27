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
import pidev.entites.DossierMedical;
import pidev.utils.ConnectionBD;

/**
 *
 * @author yanisinfo
 */
public class ServiceDossierMedical implements IService<DossierMedical>{
            Connection cnx = ConnectionBD.getInstance().getCnx();

 @Override
    public void ajouter(DossierMedical t) {
        try {
            String requete = "INSERT INTO dossier_medical (titre,contenu) VALUES (?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, t.getTitre());
            pst.setString(2, t.getContenu());
            pst.executeUpdate();
            System.out.println("Dossier Medical ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }    }

    @Override
    public void supprimer(DossierMedical t) {
        try {
            String requete = "DELETE FROM dossier_medical WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, t.getIdDM());
            pst.executeUpdate();
            System.out.println("Dossier Medical supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }    }

    @Override
    public void modifier(DossierMedical t) {
        try {
            String requete = "UPDATE dossier_medical SET titre=?,contenu=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(3, t.getIdDM());
            pst.setString(1, t.getTitre());
            pst.setString(2, t.getContenu());
            pst.executeUpdate();
            System.out.println("Dossier Medical modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }    }

    @Override
    public List<DossierMedical> afficher() {
        List<DossierMedical> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM dossier_medical";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new DossierMedical(rs.getInt(1), rs.getString(2), rs.getString("contenu")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;    }
    
}
