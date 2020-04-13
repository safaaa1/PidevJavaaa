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
public class Consultation {
    private int idCons;
    private int idEnf;
    private int idMed;
    private Date dateCons;
    private String nomEnf;
    private String nomMed;
    
    public Consultation(int idCons,int idMed, int idEnf,Date dateCons,String nomEnf,String nomMed){
    this.idCons=idCons;  
    this.idMed=idMed;
    this.idEnf=idEnf;
    this.dateCons=dateCons;
    this.nomEnf=nomEnf;
    this.nomMed=nomMed;
    }
    public Consultation(int idCons,int idMed, int idEnf,Date dateCons){
    this.idCons=idCons;  
    this.idMed=idMed;
    this.idEnf=idEnf;
    this.dateCons=dateCons;
   
    }
    public Consultation(int idMed,int idEnf,Date dateCons,String nomEnf){    
    this.idMed=idMed;
    this.idEnf=idEnf;
    this.dateCons=dateCons;
    this.nomEnf=nomEnf;
    }
    public Consultation(int idMed,int idEnf,Date dateCons){    
    this.idMed=idMed;
    this.idEnf=idEnf;
    this.dateCons=dateCons;
    
    }
    public Consultation(String nomMed,String nomEnf,Date dateCons){
    this.nomMed=nomMed;
    this.nomEnf=nomEnf;
    this.dateCons=dateCons;
    }
    public Consultation(int idCons,String nomMed,String nomEnf,Date dateCons){
        this.idCons=idCons;
    this.nomMed=nomMed;
    this.nomEnf=nomEnf;
    this.dateCons=dateCons;
    }
    public Consultation(int idCons){
    this.idCons=idCons;
    }
    public Consultation(){}

    
    
    public int getIdCons() {
        return idCons;
    }

    public void setIdCons(int idCons) {
        this.idCons = idCons;
    }

    public int getIdEnf() {
        return idEnf;
    }

    public void setIdEnf(int idEnf) {
        this.idEnf = idEnf;
    }

    public int getIdMed() {
        return idMed;
    }

    public void setIdMed(int idMed) {
        this.idMed = idMed;
    }

    public Date getDateCons() {
        return dateCons;
    }

    public void setDateCons(Date dateCons) {
        this.dateCons = dateCons;
    }

    public String getNomEnf() {
        return nomEnf;
    }

    public void setNomEnf(String nomEnf) {
        this.nomEnf = nomEnf;
    }

    public String getNomMed() {
        return nomMed;
    }

    public void setNomMed(String nomMed) {
        this.nomMed = nomMed;
    }

    @Override
    public String toString() {
        return "Consultation{" + "idCons=" + idCons + ", idEnf=" + idEnf + ", idMed=" + idMed + ", dateCons=" + dateCons + '}';
    }
    
}
