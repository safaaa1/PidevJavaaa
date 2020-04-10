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
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pidev.entites.Abonnement;
import pidev.entites.Inscription;
import pidev.utils.ConnectionBD;

/**
 *
 * @author Moetaz Jebri
 */
public class AbonnementService {
    
    Connection cnx = ConnectionBD.getInstance().getCnx();
    
    public void ajouter (Abonnement a){
        String req = "INSERT INTO abonne (`IdEnf`, `NomParent`, `Date`, `TypePay`)VALUES('"+a.getIdenf()+"','"+a.getNomParent()+"','"+a.getDate()+"','"+a.getTypepay()+"')";
        try{
           PreparedStatement st =cnx.prepareStatement(req);
           /* st.setInt(1,a.getIdenf());
            st.setString(2, a.getNomParent());
            st.setDate(3,a.getDate());
            st.setString(4, a.getTypepay());*/
            
            
            st.executeUpdate(req);
            System.out.println("Ajout Done !");
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
            
    }
    
    public void modifier (Abonnement a){
        String req = "update into abonne (Idenf,NomParent, Date, TypePay)values (?,?,?,?)";
        try{
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Modification Effectuer !");
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
            
    }
    
    public void supprimer(Abonnement a){
        String req = "delete from abonne where id=?;";
        try{
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1,a.getIdAbn());
            pst.executeUpdate();
            System.out.println("Abonnement Supprimer !");
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        
    }
    
     public List<Abonnement> afficher(){
        //List<Abonnement> list = new ArrayList<>();
        ObservableList <Abonnement> list =FXCollections.observableArrayList();

        
        String req = "select * from abonne;";
        try{
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                Abonnement a = new Abonnement(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getDate(4),rs.getString(5));
                list.add(a);
            
            }
            System.out.println("Abonnement Afficher !");
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        
        return list;
    }
}
