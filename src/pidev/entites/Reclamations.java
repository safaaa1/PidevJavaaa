/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.entites;

import static java.sql.JDBCType.NULL;
import java.util.Objects;


/**
 *
 * @author islem
 */
public class Reclamations{
  private int id;
  private String nom;
  private String titre;
  private int tel;
  private String email;
  private String description;
private Integer reponseid;

    public Reclamations(String nom, String titre, int tel, String email, String description, int reponseid) {
        this.nom = nom;
        this.titre = titre;
        this.tel = tel;
        this.email = email;
        this.description = description;
        this.reponseid = reponseid;
    }

    public int getReponseid() {
        return reponseid;
    }

    public void setReponseid(int reponseid) {
        this.reponseid = reponseid;
    }

    public Reclamations(int id, String nom, String titre, int tel, String email, String description, int reponseid) {
        this.id = id;
        this.nom = nom;
        this.titre = titre;
        this.tel = tel;
        this.email = email;
        this.description = description;
/*       if(reponseid==NULL)
        {
            this.reponseid=-1;
        }*/
            
        this.reponseid = reponseid;
        
    }
    
        public Reclamations(int id, String nom, String titre, int tel, String email, String description) {
        this.id = id;
        this.nom = nom;
        this.titre = titre;
        this.tel = tel;
        this.email = email;
        this.description = description;
        
    }

    public Reclamations(String nom, String titre, int tel, String email, String description) {
        this.nom = nom;
        this.titre = titre;
        this.tel = tel;
        this.email = email;
        this.description = description;
    }
 
  

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

  
    public String getDescription() {
        return description;
    }

  
    public void setDescription(String description) {
        this.description = description;
    }

    public Reclamations(){}

    public Reclamations(int id, String nom, String titre, String email, int tel, String description) {
        //super();
        this.id = id;
        this.nom = nom;
        this.titre = titre;
        this.email = email;
        this.tel = tel;
        this.description = description;
        
    }

    @Override
    public String toString() {
        if(reponseid==0)
        {
          return "Reclamations{" + "id=" + id + ", nom=" + nom + ", titre=" + titre + ", tel=" + tel + ", email=" + email + ", Reponse= PAS DE REPONSE, description=" + description + '}';   
        }
        return "Reclamations{" + "id=" + id + ", nom=" + nom + ", titre=" + titre + ", tel=" + tel + ", email=" + email + ", Reponse=" + reponseid + ", description=" + description + '}';
    }


    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.id;
        hash = 97 * hash + Objects.hashCode(this.nom);
        hash = 97 * hash + Objects.hashCode(this.titre);
        hash = 97 * hash + this.tel;
        hash = 97 * hash + Objects.hashCode(this.email);
        hash = 97 * hash + Objects.hashCode(this.description);

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
        final Reclamations other = (Reclamations) obj;
        if (this.id != other.id) {
            return false;
        }
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
        return Objects.equals(this.description, other.description);
    }

  
  
  
    
}
