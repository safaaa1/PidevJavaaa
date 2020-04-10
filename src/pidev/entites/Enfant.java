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
public class Enfant{
    private int idEnfant;
    private String nom;
    private int age;
    private int id_dossier;

    public Enfant(int idEnfant,String nom, int age,int id_dossier)
    {
        this.idEnfant=idEnfant;
        this.nom=nom;
        this.age=age;
        this.id_dossier=id_dossier;
    }
    public Enfant(String nom, int age,int id_dossier)
    {
     
        this.nom=nom;
        this.age=age;
        this.id_dossier=id_dossier;
    }
    public Enfant(int idEnfant)
    {
        this.idEnfant=idEnfant;
    }
    public Enfant()
    {
    }

    public int getIdEnfant() {
        return idEnfant;
    }

    public void setIdEnfant(int idEnfant) {
        this.idEnfant = idEnfant;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId_dossier() {
        return id_dossier;
    }

    public void setId_dossier(int id_dossier) {
        this.id_dossier = id_dossier;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.idEnfant;
        hash = 71 * hash + Objects.hashCode(this.nom);
        hash = 71 * hash + this.age;
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
        final Enfant other = (Enfant) obj;
        if (this.idEnfant != other.idEnfant) {
            return false;
        }
        if (this.age != other.age) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nom;
    }
    
}
