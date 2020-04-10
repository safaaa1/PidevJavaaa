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
public class Abonnement {
    private int IdAbn;
    private int Idenf;
    private String NomParent;
    private Date date;
    private String Typepay;

    public Abonnement(int IdAbn, int Idenf, String NomParent,  Date date, String Typepay) {
        this.IdAbn = IdAbn;
        this.Idenf = Idenf;
        this.NomParent = NomParent;
        
        this.date = date;
        this.Typepay = Typepay;
    }
        public Abonnement(int Idenf, String NomParent, Date date, String Typepay) {
        
      this.Idenf = Idenf;
        this.NomParent = NomParent;
     
        this.date = date;
        this.Typepay = Typepay;
          
    }
        public Abonnement() {
        
       
    }
    public Abonnement(int IdAbn) {
        
       this.IdAbn=IdAbn;
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





    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTypepay() {
        return Typepay;
    }

    public void setTypepay(String Typepay) {
        this.Typepay = Typepay;
    }

    @Override
    public String toString() {
        return "Abonnement{" + "IdAbn=" + IdAbn + ", Idenf=" + Idenf + ", NomParent=" + NomParent  + ", Date=" + date + ", Typepay=" + Typepay + '}';
    }

    
    
}
