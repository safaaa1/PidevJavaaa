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
import pidev.entites.Contine;
import pidev.utils.ConnectionBD;

/**
 *
 * @author Moetaz Jebri
 */
public class ContineService {
    Connection cnx = ConnectionBD.getInstance().getCnx();
    
    public void ajouter (Contine c){
        String req = "INSERT INTO cont ( `Nom`, `Menu`, `Date`, `PlatDuJour`, `NbEnf`, `IdEnf`, `IdAb`) VALUES ('"+c.getNom()+"','"+c.getMenu()+"','"+c.getDate()+"','"+c.getPlatDuJours()+"','"+c.getNbenf()+"','"+c.getIdenf()+"','"+c.getIdAb()+"');";
        try{
          PreparedStatement st = cnx.prepareStatement(req);
          /*st.setString(1, c.getNom());
          st.setString(2, c.getMenu());
          st.setDate(3, c.getDate());
          st.setString(4, c.getPlatDuJours());
          st.setInt(5, c.getNbenf());
          st.setInt(6, c.getIdenf());
          st.setInt(7, c.getIdAb());*/
            st.executeUpdate(req);
            System.out.println("Ajout Done !");
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
            
    }
    
    public void modifier (Contine c){
        String req = "update into cont (`Nom`, `Menu`, `Date`, `PlatDuJour`, `NbEnf`, `IdEnf`, `IdAb`) VALUES ('"+c.getNom()+"','"+c.getMenu()+"','"+c.getDate()+"','"+c.getPlatDuJours()+"','"+c.getNbenf()+"','"+c.getIdenf()+"','"+c.getIdAb()+"');";
        try{
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Modification Effectuer !");
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
            
    }
    
    public void supprimer(Contine c){
        String req = "delete from cont where id=?;";
        try{
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1,c.getIdC());
            pst.executeUpdate();
            System.out.println("Contine Supprimer !");
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        
    }
    
    public List<Contine> afficher(){
      ObservableList <Contine> list =FXCollections.observableArrayList();
        
        String req = "select * from cont;";
        try{
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                Contine c = new Contine(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDate(4),rs.getString(5),rs.getInt(6),rs.getInt(7),rs.getInt(8));
                list.add(c);
            
            }
            System.out.println("Contine Afficher !");
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        
        return list;
    }
    
}
