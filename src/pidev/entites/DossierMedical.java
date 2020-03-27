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
public class DossierMedical {
    protected int idDM;
    private String contenu;
    private String titre;
    
    public DossierMedical(int idDM, String titre,String contenu)
    {
        this.idDM=idDM;
        this.titre=titre;
        this.contenu=contenu;
    }
    public DossierMedical(){}

    public int getIdDM() {
        return idDM;
    }

    public void setIdDM(int idDM) {
        this.idDM = idDM;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.idDM;
        hash = 53 * hash + Objects.hashCode(this.contenu);
        hash = 53 * hash + Objects.hashCode(this.titre);
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
        final DossierMedical other = (DossierMedical) obj;
        if (this.idDM != other.idDM) {
            return false;
        }
        if (!Objects.equals(this.contenu, other.contenu)) {
            return false;
        }
        if (!Objects.equals(this.titre, other.titre)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DossierMedical{" + "idDM=" + idDM + ", contenu=" + contenu + ", titre=" + titre + '}';
    }

}
