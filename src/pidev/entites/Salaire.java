/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.entites;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author islem
 */
public class Salaire {
    private int id;
    private int chiffre;
    private int prime;
    private Date date;
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getChiffre() {
        return chiffre;
    }

    public void setChiffre(int chiffre) {
        this.chiffre = chiffre;
    }

    public int getPrime() {
        return prime;
    }

    public void setPrime(int prime) {
        this.prime = prime;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Salaire(int id, int chiffre, int prime, Date date) {
        this.id = id;
        this.chiffre = chiffre;
        this.prime = prime;
        this.date = date;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + this.id;
        hash = 37 * hash + this.chiffre;
        hash = 37 * hash + this.prime;
        hash = 37 * hash + Objects.hashCode(this.date);
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
        final Salaire other = (Salaire) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.chiffre != other.chiffre) {
            return false;
        }
        if (this.prime != other.prime) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Salaire{" + "id=" + id + ", chiffre=" + chiffre + ", prime=" + prime + ", date=" + date + '}';
    }
    
    
    
    
    
}
