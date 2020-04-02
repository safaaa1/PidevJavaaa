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
import pidev.entites.Contine;
import pidev.utils.ConnectionBD;

/**
 *
 * @author Moetaz Jebri
 */
public class ContineService {
    Connection cnx = ConnectionBD.getInstance().getCnx();
    
    public void ajouter (Contine c){
        String req = "insert into contine (IdC, Nom,  Menu,  date, PlatDuJours,  Nbenf,  Idenf, IdAb, Nombrenf)values ('"+c.getIdC()+"','"+c.getNom()+"','"+c.getMenu()+"','"+c.getDate()+"','"+c.getPlatDuJours()+"','"+c.getNbenf()+"','"+c.getIdAb()+"','"+c.getNombrenf()+"');";
        try{
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Ajout Done !");
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
            
    }
    
    public void modifier (Contine c){
        String req = "update into contine (IdC, Nom,  Menu,  date, PlatDuJours,  Nbenf,  Idenf, IdAb, Nombrenf)values ('"+c.getIdC()+"','"+c.getNom()+"','"+c.getMenu()+"','"+c.getDate()+"','"+c.getPlatDuJours()+"','"+c.getNbenf()+"','"+c.getIdAb()+"','"+c.getNombrenf()+"');";
        try{
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Modification Effectuer !");
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
            
    }
    
    public void supprimer(Contine c){
        String req = "delete from contine where IdC=?;";
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
        List<Contine> list = new ArrayList<>();
        
        String req = "select * from contine;";
        try{
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                Contine c = new Contine(rs.getInt("IdC"),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt("Nbrenf"),rs.getInt("IdEnf"),rs.getInt("IdAb"),rs.getInt("Nombenf"));
                list.add(c);
            
            }
            System.out.println("Contine Afficher !");
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        
        return list;
    }
    
}
