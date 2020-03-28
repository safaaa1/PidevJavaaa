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
public class Grade {
    
     private int idgr;
    private int nbrclasse ;
    private int nbrenfgr;
    private String nomgr;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + this.idgr;
        hash = 31 * hash + this.nbrclasse;
        hash = 31 * hash + this.nbrenfgr;
        hash = 31 * hash + Objects.hashCode(this.nomgr);
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
        final Grade other = (Grade) obj;
        if (this.idgr != other.idgr) {
            return false;
        }
        if (this.nbrclasse != other.nbrclasse) {
            return false;
        }
        if (this.nbrenfgr != other.nbrenfgr) {
            return false;
        }
        if (!Objects.equals(this.nomgr, other.nomgr)) {
            return false;
        }
        return true;
    }

    public Grade(int idgr, int nbrclasse, int nbrenfgr, String nomgr) {
        this.idgr = idgr;
        this.nbrclasse = nbrclasse;
        this.nbrenfgr = nbrenfgr;
        this.nomgr = nomgr;
    }



    public int getIdgr() {
        return idgr;
    }

    public void setIdgr(int idgr) {
        this.idgr = idgr;
    }

    public int getNbrclasse() {
        return nbrclasse;
    }

    public void setNbrclasse(int nbrclasse) {
        this.nbrclasse = nbrclasse;
    }

    public int getNbrenfgr() {
        return nbrenfgr;
    }

    public void setNbrenfgr(int nbrenfgr) {
        this.nbrenfgr = nbrenfgr;
    }

    public String getNomgr() {
        return nomgr;
    }

    public void setNomgr(String nomgr) {
        this.nomgr = nomgr;
    }
}
