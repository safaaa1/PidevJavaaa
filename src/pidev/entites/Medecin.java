/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.entites;

import java.util.Objects;

/**
 *
 * @author yanisinfo
 */
public class Medecin extends Utilisateur{
    private int idMedecin;
    private int tel;
    private String nom;
    private String prenom;
    private String email;
    
    public Medecin(int idMedecin,String nom,String prenom,String email,int tel )
    {
        this.idMedecin=idMedecin;
        this.nom=nom;
        
        this.prenom=prenom;
        this.email=email;
        this.tel=tel;
    
    }
        public Medecin(String nom,int tel,String prenom,String email )
    {
       
        this.nom=nom;
        this.tel=tel;
        this.prenom=prenom;
        this.email=email;
    }
    public Medecin(int idMedecin){
    this.idMedecin=idMedecin;
    }
    public Medecin(){}

    public int getIdMedecin() {
        return idMedecin;
    }

    public void setIdMedecin(int idMedecin) {
        this.idMedecin = idMedecin;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + this.idMedecin;
        hash = 53 * hash + this.tel;
        hash = 53 * hash + Objects.hashCode(this.nom);
        hash = 53 * hash + Objects.hashCode(this.prenom);
        hash = 53 * hash + Objects.hashCode(this.email);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Medecin other = (Medecin) obj;
        if (this.idMedecin != other.idMedecin) {
            return false;
        }
        if (this.tel != other.tel) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.prenom, other.prenom)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nom;
    }
    
}
