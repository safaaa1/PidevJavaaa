/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.entites;

/**
 *
 * @author safa
 */
public class ReservationUtilisateur {
  private String event;
private String nomUser;
private String prenomUser;
private String etat;
private int idReser;
private int idU;
private String email;
    public ReservationUtilisateur( String event, String nomUser, String prenomUser, String etat,int idReser,int idU,String email) {
        this.event = event;
        this.nomUser = nomUser;
        this.prenomUser = prenomUser;
        this.etat = etat;
        this.idReser=idReser;
        this.idU=idU;
        this.email=email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
    public String getEvent() {
        return event;
    }

    public int getIdReser() {
        return idReser;
    }

    public void setIdReser(int idReser) {
        this.idReser = idReser;
    }

    public int getIdU() {
        return idU;
    }

    public void setIdU(int idU) {
        this.idU = idU;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getNomUser() {
        return nomUser;
    }

    public void setNomUser(String nomUser) {
        this.nomUser = nomUser;
    }

    public String getPrenomUser() {
        return prenomUser;
    }

    public void setPrenomUser(String prenomUser) {
        this.prenomUser = prenomUser;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }


}
