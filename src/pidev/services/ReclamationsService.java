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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pidev.entites.Enseignant;
import pidev.entites.Reclamations;
import pidev.utils.ConnectionBD;

/**
 *
 * @author islem
 */
public class ReclamationsService {

    Connection cnx = ConnectionBD.getInstance().getCnx();

    public void add(Reclamations reclamations) {

        String req = "insert into reclamations (nom, titre, email, tel, description) values(?, ?, ?, ?, ?);";

        try {
            PreparedStatement pst = cnx.prepareStatement(req);

            // pst.setObject(6, null);
            pst.setString(1, reclamations.getNom());
            pst.setString(2, reclamations.getTitre());
            pst.setString(3, reclamations.getEmail());
            pst.setInt(4, reclamations.getTel());
            pst.setString(5, reclamations.getDescription());
            //   pst.setInt(6, reclamations.getReponseid());
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
        // List<Reclamations> list = new ArrayList<>();
        ObservableList<Reclamations> list = FXCollections.observableArrayList();
//ObservableList <Reclamations> list2 =FXCollections.observableArrayList();
        ReponseService rps = new ReponseService();
        try {
            String req = "SELECT id,nom,titre,email,tel,description,reponse_id FROM reclamations";
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                Reclamations r = new Reclamations(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(5), rs.getString(4), rs.getString(6), rs.getInt(7));
                r.setReponse(rps.getrep(r.getReponseid()));
                list.add(r);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }/*
            for(Reclamations r:list){
                if(r.getReponseid()==0){
                    r.setReponse("Pas du Reponse");
                    System.out.println(r);
                }
                list2.add(r);
            }*/
        return list;
    }
    /*
              ObservableList <Reclamations> list =FXCollections.observableArrayList();


        try {
            String req = "SELECT r.id,r.nom,r.titre.email,r.tel,r.reponseid FROM Reclamations r ";
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
             while (rs.next()) {
                String descrition=null;
               
                 int id=rs.getInt("r.id");
                 String nom=rs.getString("r.nom");
                 String titre=rs.getString("r.titre");
                 int tel=rs.getInt("r.tel");
                 String email=rs.getString("r.email");
                 int reponseid=rs.getInt("r.reponseid");
                 if(reponseid !=0)
                 {
                      String req2="Select description from reponse where id="+reponseid;
                   PreparedStatement pst2 = cnx.prepareStatement(req2);
                    ResultSet rs2 = pst2.executeQuery();
                    while(rs2.next())
                    {
                     descrition=rs2.getString("description");   
                    }
                 }
                 Reclamations r=new Reclamations(id,nom,titre,tel,email,descrition,reponseid);
                 System.out.println(r.toString());
                list.add(r);
            }
                     } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }*/
}
