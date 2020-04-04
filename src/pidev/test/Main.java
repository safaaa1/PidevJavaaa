/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.test;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pidev.entites.Enseignant;
import pidev.entites.Reclamations;
import pidev.entites.Reponse;
import pidev.entites.Salaire;
import pidev.services.EnseignantService;
import pidev.services.ReclamationsService;
import pidev.services.ReponseService;
import pidev.services.SalaireService;

/**
 *
 * @author Mohamed
 */
public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("/pidev/gui/Enseignant.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        EnseignantService se=new EnseignantService();
        se.read();
        
    
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      launch(args);
     //
     
      /*
        ReclamationsService sp1 = new ReclamationsService();
        Reclamations r = new Reclamations( 45,"is","lo","eem@gmail.com",56745678,"jihdfk");
       // sp1.Update(r); 
        //sp1.add(r);
        sp1.Delete(r);
        sp1.read().forEach(System.out::println);
      
      
       ReponseService sp2 = new ReponseService();
        Reponse rp = new Reponse( 54,"islem","salouma","iiiisleeeem@gmail.com",26745678,"jihdfk");
       //sp2.add(rp,46);
        sp2.Delete(rp);
       sp2.read().forEach(System.out::println);
       
       //test enseignant
       EnseignantService sp1 = new EnseignantService();
       Enseignant e = new Enseignant(18,"islem","salouma","iiiisleeeem@gmail.com",26745678,12);
       sp1.delete(e);
       sp1.read().forEach(System.out::println);
       
       
       //test salaire
       SalaireService sp = new SalaireService();
       Salaire s = new Salaire(12,5500,100,new Date());
        sp.add(s,12);
        //sp1.Update(s);
       //sp1.delete(s);
       sp.read().forEach(System.out::println);*/
       
       
    
}}
