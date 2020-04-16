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
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pidev.entites.Inscription;
import pidev.utils.ConnectionBD;

/**
 *
 * @author Moetaz Jebri
 */
public class InscriptionService {
    
    Connection cnx = ConnectionBD.getInstance().getCnx();
    
    public void ajouter (Inscription I){
        String req = "insert into inscri ( Nom,Prenom,Mail,Motedepasse, ConMdp)values ('"+I.getNom()+"','"+I.getPrenom()+"','"+I.getMail()+"','"+I.getMdp()+"','"+I.getCMdp()+"');";
        try{
          PreparedStatement st = cnx.prepareStatement(req);
          /*st.setString(1, I.getNom());
          st.setString(2,I.getPrenom());
          st.setString(3, I.getMail());
          st.setString(4, I.getMdp());
          st.setString(5, I.getCMdp());*/
            st.executeUpdate(req);
            System.out.println("Ajout Done !");
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
            
    }
    
    public void modifier (Inscription I){
        String req = "update into inscri (Id,Nom,Prenom,Mail,Motedepasse, ConMdp)values ('"+I.getIdins()+"','"+I.getNom()+"','"+I.getPrenom()+"','"+I.getMail()+"','"+I.getMdp()+"','"+I.getCMdp()+"');";
        try{
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Modification Effectuer !");
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
            
    }
    
    public void supprimer(Inscription I){
        String req = "delete from inscri where id=?;";
        try{
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1,I.getIdins());
            pst.executeUpdate();
            System.out.println("Inscription Supprimer !");
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        
    }
    
    public List<Inscription> afficher(){
        //List<Inscription> list = new ArrayList<>();
        ObservableList <Inscription> list =FXCollections.observableArrayList();
        
        String req = "select * from inscri;";
        try{
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                Inscription I = new Inscription(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
                list.add(I);
            
            }
            System.out.println("Inscription Afficher !");
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        
        return list;
    }
    
     public ObservableList<Inscription> chercher(String mot){
        ObservableList<Inscription> list = FXCollections.observableArrayList();
        String req = "select * from inscri where nom like '"+mot+"%';";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Inscription p = new Inscription(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
                
                list.add(p);
            }
        } catch (SQLException ex) {
        }
        System.out.println("Inscription trouve ");
        return list;
    }
}
