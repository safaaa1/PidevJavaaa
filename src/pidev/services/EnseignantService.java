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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pidev.entites.Enseignant;
import pidev.utils.ConnectionBD;

/**
 *
 * @author islem
 */
public class EnseignantService {
    Connection cnx = ConnectionBD.getInstance().getCnx();
    
     public void add(Enseignant e){
        
        String req = "insert into enseignant (nom, prenom, email, tel) values(?, ?, ?, ? )";
        try {
                PreparedStatement pst = cnx.prepareStatement(req); 
                pst.setString(1, e.getNom());
                pst.setString(2, e.getPrenom());
                pst.setString(3, e.getEmail());
                pst.setInt(4, e.getTel());
          //      pst.setInt(5, e.getSalaire_id());
                pst.executeUpdate();
                System.out.println("enseignant crée!");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
    }
         public void Update(Enseignant e) {
        try {
            String req = "UPDATE Enseignant SET nom=?,prenom=?,email=?,tel=?,salaire_id=?,salaire_montant=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(7, e.getId());
            pst.setString(1, e.getNom());
            pst.setString(2, e.getPrenom());
            pst.setString(3, e.getEmail());
            pst.setInt(4, e.getTel());
            pst.setInt(5, e.getSalaire_id());
            pst.setInt(6, e.getSalaire_montant());
           
            pst.executeUpdate();
            System.out.println("Enseignant modifiÃ©e !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
        public void delete(Enseignant e) {
        try {
            String req = "DELETE FROM Enseignant WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, e.getId());
            pst.executeUpdate();
            System.out.println("Enseignant supprimÃ©e !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
     
    /**
     *
     * @return 
         */
    
    public List<Enseignant> read() {
      //  List<Enseignant> list = new ArrayList<>();
        ObservableList <Enseignant> list =FXCollections.observableArrayList();


        try {
            String req = "SELECT e.id,e.nom,e.prenom,e.email,e.tel,e.salaire_id FROM enseignant e ";
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
             while (rs.next()) {
                int montant=0;
               
                 int id=rs.getInt("e.id");
                 String nom=rs.getString("e.nom");
                 String prenom=rs.getString("e.prenom");
                 String email=rs.getString("e.email");
                 int tel=rs.getInt("e.tel");
                 int salaire_id=rs.getInt("e.salaire_id");
                 if(salaire_id!=0)
                 {
                      String req2="Select chiffre,prime from salaire where id="+salaire_id;
                   PreparedStatement pst2 = cnx.prepareStatement(req2);
                    ResultSet rs2 = pst2.executeQuery();
                    while(rs2.next())
                    {
                     montant=rs2.getInt("chiffre")+rs2.getInt("prime");   
                    }
                 }
                 Enseignant e=new Enseignant(id,nom,prenom,email,tel,salaire_id,montant);
                 System.out.println(e.toString());
                list.add(e);
            }
           /* String req = "SELECT * FROM enseignant";
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
             while (rs.next()) {
                // int montant=rs.getInt("s.chiffre");
                 int id=rs.getInt("id");
                 String nom=rs.getString("nom");
                 String prenom=rs.getString("prenom");
                 String email=rs.getString("email");
                 int tel=rs.getInt("tel");
                 int salaire_id=rs.getInt("salaire_id");
                 Enseignant e=new Enseignant(id,nom,prenom,email,tel,salaire_id,0);
                 System.out.println(e.toString());
                list.add(e);
            }
*/
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
    
    public List<Enseignant> getAllEnseignant(){
        List<Enseignant> ListP = new ArrayList<>();
        try {
            String requete = "SELECT id,nom,prenom,email,tel,salaire_id FROM enseignant";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                Enseignant p = new Enseignant();
                p.setId(rs.getInt(1));
                p.setNom(rs.getString(2));
                p.setPrenom(rs.getString(3));
                p.setEmail(rs.getString(4));
                p.setTel(rs.getInt(5));
                p.setSalaire_id(rs.getInt(6));
                
                   String requete2 = "SELECT chiffre,prime FROM salaire where id="+p.getSalaire_id();
                    PreparedStatement pst2 = cnx.prepareStatement(requete2);
                    ResultSet rs2 = pst2.executeQuery();
                    while(rs2.next()){
                        p.setSalaire_montant(rs2.getInt(1)+rs2.getInt(2));      
                        break; 
                    }
                //p.setSalaire_montant(rs.getInt(7));

                
                ListP.add(p);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
return ListP;
    }
    
    public float sumsalaire(){
        try {
            String requete = "SELECT sum(chiffre),sum(prime) FROM salaire";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
             while(rs.next()){
                 return(rs.getInt(1)+rs.getInt(2));
             }   
        } 
        catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return 0;
    }
}
