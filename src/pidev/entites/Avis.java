/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.entites;

import java.util.Objects;

/**
 *
 * @author safa
 */
public class Avis {
   private int idAvis;
  private String description;
      private int idEvent;
    private int idParent;
        private int idUtilisateur;
        private double rating;

    public Avis(int idAvis, String description, int idEvent, int idParent, int idUtilisateur,double rating) {
        this.idAvis = idAvis;
        this.description = description;
        this.idEvent = idEvent;
        this.idParent = idParent;
        this.idUtilisateur = idUtilisateur;
        this.rating=rating;
    }

    public Avis() {
    }

    public Avis(String description, int idEvent, int idParent, int idUtilisateur,double rating) {
        this.description = description;
        this.idEvent = idEvent;
        this.idParent = idParent;
        this.idUtilisateur = idUtilisateur;
                this.rating=rating;

    }

    public Avis(int idEvent, int idParent, int idUtilisateur) {
        this.idEvent = idEvent;
        this.idParent = idParent;
        this.idUtilisateur = idUtilisateur;
    }

    public Avis(String description, int idEvent, int idUtilisateur) {
        this.description = description;
        this.idEvent = idEvent;
        this.idUtilisateur = idUtilisateur;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

   

   

   
    public int getIdAvis() {
        return idAvis;
    }

    public void setIdAvis(int idAvis) {
        this.idAvis = idAvis;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    public int getIdParent() {
        return idParent;
    }

    public void setIdParent(int idParent) {
        this.idParent = idParent;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.idAvis;
        hash = 53 * hash + Objects.hashCode(this.description);
        hash = 53 * hash + this.idEvent;
        hash = 53 * hash + this.idParent;
        hash = 53 * hash + this.idUtilisateur;
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
        final Avis other = (Avis) obj;
        if (this.idAvis != other.idAvis) {
            return false;
        }
        if (this.idEvent != other.idEvent) {
            return false;
        }
        if (this.idParent != other.idParent) {
            return false;
        }
        if (this.idUtilisateur != other.idUtilisateur) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Avis{" + "idAvis=" + idAvis + ", description=" + description + ", idEvent=" + idEvent + ", idParent=" + idParent + ", idUtilisateur=" + idUtilisateur + ", rating=" + rating + '}';
    }

   
  
}
