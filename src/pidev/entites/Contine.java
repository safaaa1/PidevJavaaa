/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.entites;

import java.sql.Date;

/**
 *
 * @author Moetaz Jebri
 */
public class Contine {
    private int IdC;
    private String Nom;
    private String Menu;
    private Date date;
    private String PlatDuJours;
    private int Nbenf;
    private int Idenf;
    private int IdAb;

    public Contine(int IdC, String Nom, String Menu, Date date, String PlatDuJours, int Nbenf, int Idenf, int IdAb) {
        this.IdC = IdC;
        this.Nom = Nom;
        this.Menu = Menu;
        this.date = date;
        this.PlatDuJours = PlatDuJours;
        this.Nbenf = Nbenf;
        this.Idenf = Idenf;
        this.IdAb = IdAb;
    }
        public Contine(String Nom, String Menu, Date date, String PlatDuJours, int Nbenf, int Idenf, int IdAb) {
        
        this.Nom = Nom;
        this.Menu = Menu;
        this.date = date;
        this.PlatDuJours = PlatDuJours;
        this.Nbenf = Nbenf;
        this.Idenf = Idenf;
        this.IdAb = IdAb;
    }
    public Contine(int IdC)
    {
        this.IdC = IdC;
    }
    
    public int getIdC() {
        return IdC;
    }

    public void setIdC(int IdC) {
        this.IdC = IdC;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public String getMenu() {
        return Menu;
    }

    public void setMenu(String Menu) {
        this.Menu = Menu;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPlatDuJours() {
        return PlatDuJours;
    }

    public void setPlatDuJours(String PlatDuJours) {
        this.PlatDuJours = PlatDuJours;
    }

    public int getNbenf() {
        return Nbenf;
    }

    public void setNbenf(int Nbenf) {
        this.Nbenf = Nbenf;
    }

    public int getIdenf() {
        return Idenf;
    }

    public void setIdenf(int Idenf) {
        this.Idenf = Idenf;
    }

    public int getIdAb() {
        return IdAb;
    }

    public void setIdAb(int IdAb) {
        this.IdAb = IdAb;
    }

 

    @Override
    public String toString() {
        return "Contine{" + "IdC=" + IdC + ", Nom=" + Nom + ", Menu=" + Menu + ", date=" + date + ", PlatDuJours=" + PlatDuJours + ", Nbenf=" + Nbenf + ", Idenf=" + Idenf + ", IdAb=" + IdAb + '}';
    }

    
    
    
}
