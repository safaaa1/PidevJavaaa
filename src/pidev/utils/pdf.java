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
import pidev.entites.DossierMedical;
import pidev.entites.Enfant;

/**
 *
 * @author Mohamed
 */
public class pdf {
    
    public static void pdf(Enfant e) throws FileNotFoundException, DocumentException{
        
        System.out.println("begin pdf generate");
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("C:/Users/yanisinfo/Desktop/"+e.getNom()+".pdf"));
        document.open();
       // document.add(new Paragraph("hello"));
        document.add(new Paragraph(e.toString()));
        document.close();
        System.out.println("end pdf generate");
    }
        public static void pdf2(DossierMedical d) throws FileNotFoundException, DocumentException{
        
        System.out.println("begin pdf generate");
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("C/Users/yanisinfo/Desktop/"+d.getTitre()+".pdf"));
        document.open();
        //document.add(new Paragraph("hello"));
        document.add(new Paragraph(d.toString()));
        document.close();
        System.out.println("end pdf generate");
    }
    
}