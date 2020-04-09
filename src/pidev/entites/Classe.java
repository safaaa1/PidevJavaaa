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
public class Classe {
    
     private int idcl;
     private int nbrenfcl; 
     private int idgr;

    @Override
    public String toString() {
        return nomclasse;
    }
     private String nomclasse;
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.idcl;
        hash = 67 * hash + this.nbrenfcl;
        hash = 67 * hash + Objects.hashCode(this.nomclasse);
        hash = 67 * hash + this.idgr;
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
        final Classe other = (Classe) obj;
        if (this.idcl != other.idcl) {
            return false;
        }
        if (this.nbrenfcl != other.nbrenfcl) {
            return false;
        }
        if (this.idgr != other.idgr) {
            return false;
        }
        if (!Objects.equals(this.nomclasse, other.nomclasse)) {
            return false;
        }
        return true;
    }

    public Classe(int idcl, int nbrenfcl, String nomclasse, int idgr) {
        this.idcl = idcl;
        this.nbrenfcl = nbrenfcl;
        this.nomclasse = nomclasse;
        this.idgr = idgr;
    }
    public Classe(int nbrenfcl, String nomclasse, int idgr) {
        
        this.nbrenfcl = nbrenfcl;
        this.nomclasse = nomclasse;
        this.idgr = idgr;
    }
    
   
    public Classe() {
        
     
    }
    public Classe(int idcl) {
        this.idcl = idcl;
        
    }

    public int getIdcl() {
        return idcl;
    }

    public void setIdcl(int idcl) {
        this.idcl = idcl;
    }

    public int getNbrenfcl() {
        return nbrenfcl;
    }

    public void setNbrenfcl(int nbrenfcl) {
        this.nbrenfcl = nbrenfcl;
    }

    public String getNomclasse() {
        return nomclasse;
    }

    public void setNomclasse(String nomclasse) {
        this.nomclasse = nomclasse;
    }

    public int getIdgr() {
        return idgr;
    }

    public void setIdgr(int idgr) {
        this.idgr = idgr;
    }
    
}
