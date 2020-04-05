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
public class Paiement {
  private int id;
  private String nom;
  private String titre;
  private int tel;
  private String email;
  private int montant;

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

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public Paiement(int id, String nom, String titre, int tel, String email, int montant) {
        this.id = id;
        this.nom = nom;
        this.titre = titre;
        this.tel = tel;
        this.email = email;
        this.montant = montant;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + this.id;
        hash = 41 * hash + Objects.hashCode(this.nom);
        hash = 41 * hash + Objects.hashCode(this.titre);
        hash = 41 * hash + this.tel;
        hash = 41 * hash + Objects.hashCode(this.email);
        hash = 41 * hash + this.montant;
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
        final Paiement other = (Paiement) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.tel != other.tel) {
            return false;
        }
        if (this.montant != other.montant) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.titre, other.titre)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Paiement{" + "id=" + id + ", nom=" + nom + ", titre=" + titre + ", tel=" + tel + ", email=" + email + ", montant=" + montant + '}';
    }
  
  
    
}
