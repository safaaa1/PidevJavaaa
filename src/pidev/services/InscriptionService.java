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
            Statement st = cnx.createStatement();
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
        String req = "delete from inscri where Id=?;";
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
        List<Inscription> list = new ArrayList<>();
        
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
}
