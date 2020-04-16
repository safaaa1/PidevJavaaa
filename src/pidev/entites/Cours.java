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
    
    private int idcl;
    private int id ;
    private String nomcours;
    private String duree;
    private String nomenf;
    private String nomeng;
    private String nomclasse;
    private int idenfant;
    private int idenseignant;

    public Cours(int id, String nomclasse,  String nomeng, String nomenf,String nomcours, String duree) {
        this.id = id;
        this.nomcours = nomcours;
        this.duree = duree;
        this.nomenf = nomenf;
        this.nomeng = nomeng;
        this.nomclasse = nomclasse;
    }

    public String getNomenf() {
        return nomenf;
    }

    public void setNomenf(String nomenf) {
        this.nomenf = nomenf;
    }

    public String getNomeng() {
        return nomeng;
    }

    public void setNomeng(String nomeng) {
        this.nomeng = nomeng;
    }

    public String getNomclasse() {
        return nomclasse;
    }

    public void setNomclasse(String nomclasse) {
        this.nomclasse = nomclasse;
    }
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.id;
        hash = 67 * hash + this.idcl;
        hash = 67 * hash + Objects.hashCode(this.nomcours);
        hash = 67 * hash + Objects.hashCode(this.duree);
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
        
        return true;
    }

    public Cours(int id, int idcl, String nomcours, String duree, int idenfant, int idenseignant) {
        this.id = id;
        this.idcl = idcl;
        this.nomcours = nomcours;
        this.duree = duree;
        
        this.idenfant = idenfant;
        this.idenseignant = idenseignant;
    }

public Cours(int idcl, String nomcours, String duree, int idenfant, int idenseignant) {
        
        this.idcl = idcl;
        this.nomcours = nomcours;
        this.duree = duree;
        
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
