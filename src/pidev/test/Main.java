/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.test;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pidev.entites.Classe;
import pidev.entites.Cours;
import pidev.services.ClasseServices;
import pidev.services.CoursServices;

/**
 *
 * @author Mohamed
 */
public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("/pidev/gui/Classe.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
        
          // TODO code application logic here
     // Classe c = new Classe (2,"Maah",2);
       // Grade g = new Grade (3,2,2,"Maasssah");
       // ClasseServices cp = new ClasseServices();
         
       
   //classe      
       // cp.ajouter(c);
       // cp.afficher().forEach(System.out::println);
       // cp.supprimer(c);
       // cp.modifier(c);
        //GradeServices gr = new GradeServices();
    //classe Grade
       // gr.afficher().forEach(System.out::println);
        // gr.ajouter2(g);
        // gr.supprimer(g);
        // gr.modifier(g);
         
    // classe cours
   //Cours c = new Cours(2,"phy","3h","oueesslati",1,2);
   /*CoursServices cr = new CoursServices();
   // cr.ajouter(c);
      // cr.supprimer(c);
      cr.afficher().forEach(System.out::println);
      //cr.modifier(c);*/
       
    }
    
}
