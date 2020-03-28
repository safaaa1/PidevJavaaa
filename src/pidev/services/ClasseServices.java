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
import java.util.logging.Level;
import java.util.logging.Logger;
import pidev.entites.Classe;
import java.util.ArrayList;
import java.util.List;
import pidev.utils.ConnectionBD;

/**
 *
 * @author Lenovo
 */
public class ClasseServices implements IService<Classe> {

    Connection cnx = ConnectionBD.getInstance().getCnx();
    @Override
    public void ajouter(Classe t) {
        
    String req="insert into classe (idcl,nbrenfcl,idgr,nomclasse) values('"+t.getIdcl()+"','"+t.getNbrenfcl()+"','"+t.getIdgr()+"','"+t.getNomclasse()+"');";
    
    try { 
        Statement st = cnx.createStatement() ;
        st.executeUpdate(req);
        System.err.println("Classe Ajoutee ...");
    
    }   catch (SQLException ex) {
            Logger.getLogger(ClasseServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void supprimer(Classe t) {
        
    String req="delete from classe where idcl=?";
        PreparedStatement pst;
         
        try {
            pst = cnx.prepareStatement(req);
             pst.setInt(1,t.getIdcl());
             System.out.println("personne supprimer");
             pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClasseServices.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @Override
    public void modifier(Classe t) {
        
    try {
            String requete = "UPDATE Classe SET Nbrenfcl=?,idgr=?,Nomclasse=? WHERE Idcl=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(4, t.getIdcl());
            pst.setInt(1, t.getNbrenfcl());
            pst.setInt(2, t.getIdgr());
            pst.setString(3, t.getNomclasse());
            pst.executeUpdate();
            System.out.println("Classe modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    

    @Override
    public List<Classe> afficher() 
    {
       List<Classe> list = new ArrayList<>();
       String req ="select * from classe";
           
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
            Classe c = new Classe(rs.getInt(1),rs.getInt(2), rs.getString(3),rs.getInt(4));
             list.add(c);
            }
        } catch (SQLException ex) {
         Logger.getLogger(ClasseServices.class.getName()).log(Level.SEVERE, null, ex);
        }
       return list;
       
       }
    
    
    
    
}
