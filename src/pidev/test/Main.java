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
import pidev.entites.Enfant;
import pidev.entites.Medecin;
import pidev.services.ServiceEnfant;
import pidev.services.ServiceMedecin;

/**
 *
 * @author Mohamed
 */
public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("/pidev/gui/seConnecter.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //launch(args);
        
        //Test Medecin
        ServiceMedecin se = new ServiceMedecin();
        Medecin m = new Medecin(0,"anis","aniiis","anis@gmail.com", 1);
        //se.ajouter(new Medecin(0,"anis","aniiis","anis@gmail.com", 1));
        se.supprimer(m);
        se.afficher().forEach(System.out::println); 
       
       //Test Enfant
      /* ServiceEnfant sf = new ServiceEnfant();
       Enfant f = new Enfant(7,"flenmodifier",5);
       //sf.ajouter(f);
       sf.modifier(f);
       sf.afficher().forEach(System.out::println); */
      
      

}
}