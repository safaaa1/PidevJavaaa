/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.entites;

import java.util.Objects;

/**
 *
 * @author Lenovo
 */
public class Cours {
    
    private int id ;
    private int idcl;
    private String nomcours;
    private String duree;
    private String listeens;
    private int idenfant;
    private int idenseignant;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.id;
        hash = 67 * hash + this.idcl;
        hash = 67 * hash + Objects.hashCode(this.nomcours);
        hash = 67 * hash + Objects.hashCode(this.duree);
        hash = 67 * hash + Objects.hashCode(this.listeens);
        hash = 67 * hash + this.idenfant;
        hash = 67 * hash + this.idenseignant;
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
        final Cours other = (Cours) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.idcl != other.idcl) {
            return false;
        }
        if (this.idenfant != other.idenfant) {
            return false;
        }
        if (this.idenseignant != other.idenseignant) {
            return false;
        }
        if (!Objects.equals(this.nomcours, other.nomcours)) {
            return false;
        }
        if (!Objects.equals(this.duree, other.duree)) {
            return false;
        }
        if (!Objects.equals(this.listeens, other.listeens)) {
            return false;
        }
        return true;
    }

    public Cours(int id, int idcl, String nomcours, String duree, String listeens, int idenfant, int idenseignant) {
        this.id = id;
        this.idcl = idcl;
        this.nomcours = nomcours;
        this.duree = duree;
        this.listeens = listeens;
        this.idenfant = idenfant;
        this.idenseignant = idenseignant;
    }

public Cours(int idcl, String nomcours, String duree, String listeens, int idenfant, int idenseignant) {
        
        this.idcl = idcl;
        this.nomcours = nomcours;
        this.duree = duree;
        this.listeens = listeens;
        this.idenfant = idenfant;
        this.idenseignant = idenseignant;
    }
     public Cours(int id) {
        
       this.id = id;
       
        
  }
     public Cours() {
        
      
       
        
  }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdcl() {
        return idcl;
    }

    public void setIdcl(int idcl) {
        this.idcl = idcl;
    }

    public String getNomcours() {
        return nomcours;
    }

    public void setNomcours(String nomcours) {
        this.nomcours = nomcours;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public String getListeens() {
        return listeens;
    }

    public void setListeens(String listeens) {
        this.listeens = listeens;
    }

    public int getIdenfant() {
        return idenfant;
    }

    public void setIdenfant(int idenfant) {
        this.idenfant = idenfant;
    }

    public int getIdenseignant() {
        return idenseignant;
    }

    public void setIdenseignant(int idenseignant) {
        this.idenseignant = idenseignant;
    }
    
}
