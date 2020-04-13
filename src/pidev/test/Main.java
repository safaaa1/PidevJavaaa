/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.test;

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
        
        Parent root = FXMLLoader.load(getClass().getResource("/pidev/gui/Consultation.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
       
        //Test Medecin
       /* ServiceMedecin se = new ServiceMedecin();
        Medecin m = new Medecin(18,"islem",12121212,"aaaaaa","anis2@gmail.com");
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

}
}