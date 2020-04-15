/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.utils;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import static java.util.Collections.list;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pidev.entites.DossierMedical;
import pidev.entites.Enfant;
import pidev.services.ServiceEnfant;

/**
 *
 * @author yanisinfo
 */
public class pdf {
    
    public static void pdf(Enfant e) throws FileNotFoundException, DocumentException{
        
        System.out.println("begin pdf generate");
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("C:/Users/yanisinfo/Desktop/junior/"+e.getNom()+" "+e.getIdEnfant()+".pdf"));
        document.open();
        document.add(new Paragraph(e.toString()));
        document.close();
        System.out.println("end pdf generate");
    }
        public static void pdf2(DossierMedical d) throws FileNotFoundException, DocumentException{
        
        System.out.println("begin pdf generate");
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("C:/Users/yanisinfo/Desktop/junior/"+d.getTitre()+".pdf"));
        document.open();     
        document.add(new Paragraph(d.toString2()));
        document.close();
        System.out.println("end pdf generate");
    }
        
        Enfant e=new Enfant();

    public Enfant getE() {
        return e;
    }

    public void setE(Enfant e) {
        this.e = e;
    }
        /*public static void pdf3(ServiceEnfant se) throws FileNotFoundException, DocumentException{
       Enfant e = new Enfant();
        System.out.println("begin pdf generate");
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("C:/Users/yanisinfo/Desktop/junior/Enfants.pdf"));
        document.open();
       // document.add(new Paragraph("hello"));
       list.add(new Enfant(e.getIdEnfant(),e.getNom(), e.getAge(), e.getTitre()));
        document.add(new Paragraph(list));
        document.close();
        System.out.println("end pdf generate");*/
    }
    
