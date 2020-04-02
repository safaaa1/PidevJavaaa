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
        String req = "insert into abonnement (IdAbn,Idenf,NomParent,NomEnfant, Date, Typepay)values ('"+a.getIdAbn()+"','"+a.getNomParent()+"','"+a.getNomEnfant()+"','"+a.getDate()+"','"+a.getTypepay()+"');";
        try{
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Ajout Done !");
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
            
    }
    
    public void modifier (Abonnement a){
        String req = "update into abonnement (IdAbn,Idenf,NomParent,NomEnfant, Date, Typepay)values ('"+a.getIdAbn()+"','"+a.getNomParent()+"','"+a.getNomEnfant()+"','"+a.getDate()+"','"+a.getTypepay()+"');";
        try{
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Modification Effectuer !");
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
            
    }
    
    public void supprimer(Abonnement a){
        String req = "delete from abonnement where IdAbn=?;";
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
        List<Abonnement> list = new ArrayList<>();
        
        String req = "select * from abonnement;";
        try{
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                Abonnement a = new Abonnement(rs.getInt("IdAbn"),rs.getInt("Idenf"),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
                list.add(a);
            
            }
            System.out.println("Abonnement Afficher !");
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        
        return list;
    }
}
