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
public class Abonnement {
    private int IdAbn;
    private int Idenf;
    private String NomParent;
    private String NomEnfant;
    private String Date;
    private String Typepay;

    public Abonnement(int IdAbn, int Idenf, String NomParent, String NomEnfant, String Date, String Typepay) {
        this.IdAbn = IdAbn;
        this.Idenf = Idenf;
        this.NomParent = NomParent;
        this.NomEnfant = NomEnfant;
        this.Date = Date;
        this.Typepay = Typepay;
    }
    
    public int getIdAbn() {
        return IdAbn;
    }

    public void setIdAbn(int IdAbn) {
        this.IdAbn = IdAbn;
    }

    public int getIdenf() {
        return Idenf;
    }

    public void setIdenf(int Idenf) {
        this.Idenf = Idenf;
    }

    public String getNomParent() {
        return NomParent;
    }

    public void setNomParent(String NomParent) {
        this.NomParent = NomParent;
    }

    public String getNomEnfant() {
        return NomEnfant;
    }

    public void setNomEnfant(String NomEnfant) {
        this.NomEnfant = NomEnfant;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public String getTypepay() {
        return Typepay;
    }

    public void setTypepay(String Typepay) {
        this.Typepay = Typepay;
    }

    @Override
    public String toString() {
        return "Abonnement{" + "IdAbn=" + IdAbn + ", Idenf=" + Idenf + ", NomParent=" + NomParent + ", NomEnfant=" + NomEnfant + ", Date=" + Date + ", Typepay=" + Typepay + '}';
    }

    
    
}
