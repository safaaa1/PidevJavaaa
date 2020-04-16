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
public class Reponse {
  protected int id;
  private String nom;
  private String titre;
  private String email;
  private int tel;
  private String description;



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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Reponse(int id, String nom, String titre, String email, int tel, String description) {
        this.id = id;
        this.nom = nom;
        this.titre = titre;
        this.email = email;
        this.tel = tel;
        this.description = description;
    }

    public Reponse(String nom, String titre, String email, int tel, String description) {
        this.nom = nom;
        this.titre = titre;
        this.email = email;
        this.tel = tel;
        this.description = description;
    }

    public Reponse(String description) {
        this.description = description;
    }


    

    public Reponse(int id) {
        this.id = id;
    }

    public Reponse() {
    }

    
    @Override
    public String toString() {
        return "Reponse{" + "id=" + id + ", nom=" + nom + ", titre=" + titre + ", email=" + email + ", tel=" + tel + ", description=" + description + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.nom);
        hash = 23 * hash + Objects.hashCode(this.titre);
        hash = 23 * hash + Objects.hashCode(this.email);
        hash = 23 * hash + this.tel;
        hash = 23 * hash + Objects.hashCode(this.description);
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
        final Reponse other = (Reponse) obj;
        if (this.tel != other.tel) {
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
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        return true;
    }


    

 

 


    
}
