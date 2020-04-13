/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.entites;

import java.sql.Date;

/**
 *
 * @author yanisinfo
 */
public class ConsultationMedecin {
  private int idCons;
   private int idMed;
   private int idEnf;
   private String nomMed;
   private String nomEnf;
   private Date date;
   
   public ConsultationMedecin(int idCons,int idMed,int idEnf,String nomMed,String nomEnf,Date date){
   this.idCons=idCons;
   this.idMed=idMed;
   this.nomMed=nomMed;
   this.nomEnf=nomEnf;
   this.date=date;
   }
    public ConsultationMedecin(String nomMed,String nomEnf,Date date){
        this.nomMed=nomMed;
   this.nomEnf=nomEnf;
   this.date=date;
   }
   
      public ConsultationMedecin(){
 
   }

    public ConsultationMedecin(int aInt, String string, int aInt0, Date date) {
    }

    public int getIdCons() {
        return idCons;
    }

    public void setIdCons(int idCons) {
        this.idCons = idCons;
    }

    public int getIdMed() {
        return idMed;
    }

    public void setIdMed(int idMed) {
        this.idMed = idMed;
    }

    public String getNomMed() {
        return nomMed;
    }

    public void setNomMed(String nomMed) {
        this.nomMed = nomMed;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getIdEnf() {
        return idEnf;
    }

    public void setIdEnf(int idEnf) {
        this.idEnf = idEnf;
    }

    public String getNomEnf() {
        return nomEnf;
    }

    public void setNomEnf(String nomEnf) {
        this.nomEnf = nomEnf;
    }

    @Override
    public String toString() {
        return "ConsultationMedecin{" + "idCons=" + idCons + ", idMed=" + idMed + ", idEnf=" + idEnf + ", nomMed=" + nomMed + ", date=" + date + '}';
    }




}
