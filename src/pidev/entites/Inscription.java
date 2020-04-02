/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.entites;

/**
 *
 * @author Moetaz Jebri
 */
public class Inscription {
    
    private int Idins;
    private String Nom;
    private String Prenom;
    private String Mail;
    private String Mdp;
    private String CMdp;

    public Inscription(int Idins, String Nom, String Prenom, String Mail, String Mdp, String CMdp) {
        this.Idins = Idins;
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Mail = Mail;
        this.Mdp = Mdp;
        this.CMdp = CMdp;
    }

    public int getIdins() {
        return Idins;
    }

    public void setIdins(int Idins) {
        this.Idins = Idins;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String Prenom) {
        this.Prenom = Prenom;
    }

    public String getMail() {
        return Mail;
    }

    public void setMail(String Mail) {
        this.Mail = Mail;
    }

    public String getMdp() {
        return Mdp;
    }

    public void setMdp(String Mdp) {
        this.Mdp = Mdp;
    }

    public String getCMdp() {
        return CMdp;
    }

    public void setCMdp(String CMdp) {
        this.CMdp = CMdp;
    }

    @Override
    public String toString() {
        return "Inscription{" + "Idins=" + Idins + ", Nom=" + Nom + ", Prenom=" + Prenom + ", Mail=" + Mail + ", Mdp=" + Mdp + ", CMdp=" + CMdp + '}';
    }
    
}
