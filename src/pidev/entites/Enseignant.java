/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.entites;

import java.util.Objects;

/**
 *
 * @author islem
 */
public class Enseignant {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private int tel;
    private int salaire_id;
    private int salaire_montant;

    public int getSalaire_montant() {
        return salaire_montant;
    }

    public void setSalaire_montant(int salaire_montant) {
        this.salaire_montant = salaire_montant;
    }
    public Enseignant(){}
    
        public Enseignant(int id){
            this.id=id;
        }


    public int getSalaire_id() {
        return salaire_id;
    }

    public void setSalaire_id(int salaire_id) {
        this.salaire_id = salaire_id;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }
    public Enseignant(String nom, String prenom, String email, int tel, int salaire_id,int salaire_montant) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.tel = tel;
        this.salaire_id = salaire_id;
        this.salaire_montant=salaire_montant;
    }
    
    public Enseignant(String nom, String prenom, String email, int tel,int salaire_montant) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.tel = tel;
        this.salaire_montant=salaire_montant;

       
    }
    public Enseignant(int id, String nom, String prenom, String email, int tel, int salaire_id,int salaire_montant) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.tel = tel;
        this.salaire_id = salaire_id;
        this.salaire_montant=salaire_montant;

    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + this.id;
        hash = 29 * hash + Objects.hashCode(this.nom);
        hash = 29 * hash + Objects.hashCode(this.prenom);
        hash = 29 * hash + Objects.hashCode(this.email);
        hash = 29 * hash + this.tel;
        hash = 29 * hash + this.salaire_id;
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
        final Enseignant other = (Enseignant) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.tel != other.tel) {
            return false;
        }
        if (this.salaire_id != other.salaire_id) {
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
        if(salaire_id ==0)
        {
             return "Enseignant{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", tel=" + tel + ", salaire_id= PAS DE SALAIRE}";
        }
        return "Enseignant{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", tel=" + tel + ", salaire_id=" + salaire_id +", montant="+salaire_montant + '}';
    }

    
    
    
}
