/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.test;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pidev.entites.Consultation;
import pidev.entites.DossierMedical;
import pidev.entites.Enfant;
import pidev.entites.Medecin;
import pidev.services.ServiceConsultation;
import pidev.services.ServiceDossierMedical;
import pidev.services.ServiceEnfant;
import pidev.services.ServiceMedecin;

/**
 *
 * @author Mohamed
 */
public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("/pidev/gui/DossierMedical.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, DocumentException {
       launch(args);
       
        //Test Medecin
      /* ServiceMedecin se = new ServiceMedecin(); 
       se.
        /*Medecin m = new Medecin(18,"islem",12121212,"aaaaaa","anis2@gmail.com");
        se.modifier(m);
        //se.ajouter(m);
       // se.supprimer(m);
        se.afficher().forEach(System.out::println); */
       
       //Test Enfant
     /* ServiceEnfant sf = new ServiceEnfant();
       Enfant f = new Enfant("test",2,3);
       //sf.ajouter(f);
      // sf.modifier(f);
      //sf.supprimer(f);
       sf.afficher().forEach(System.out::println); 
      
       //test dossier medical
     /*ServiceDossierMedical dm = new ServiceDossierMedical();
     DossierMedical d = new DossierMedical(3,"aaaaa","bbbbbb");
     dm.modifier(d);
     //dm.supprimer(d);
     //dm.ajouter(d);
     dm.afficher().forEach(System.out::println); */
     
     //test consultation
     /*ServiceConsultation sc = new ServiceConsultation();
     Date dateS=Date.valueOf("2000-04-09");

     Consultation c = new Consultation(15,25,39,dateS);
     sc.modifier(c);
     sc.afficher().forEach(System.out::println); */
     
        //test PDF
        /*
        System.out.println("begin pdf generate");
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("C:/Users/yanisinfo/Desktop/test.pdf"));
        document.open();
        document.add(new Paragraph("hello"));
       // document.add(new Paragraph(e.toString()));
        document.close();
        System.out.println("end pdf generate");*/
        
        
      /*  int min = 1000;
      int max = 9999;
          System.out.println("Random value in int from "+min+" to "+max+ ":");
      int random_int = (int)(Math.random() * (max - min + 1) + min);
      System.out.println(random_int);*/
}
}