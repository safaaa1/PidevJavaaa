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
import pidev.entites.Abonnement;
import pidev.entites.Inscription;
import pidev.services.AbonnementService;
import pidev.services.InscriptionService;

/**
 *
 * @author Mohamed
 */
public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("/pidev/gui/seConnecterGuest.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
       /* Inscription i =new Inscription (1,"moetaz","Jebri","hh","123","123");
        InscriptionService is = new InscriptionService();*/
        //is.ajouter(i);
        //is.afficher().forEach(System.out::println);
         /*Date dateS=Date.valueOf("1999-04-09");
        Abonnement a = new Abonnement(30,"aaaa",dateS,"aaa");
        
        AbonnementService se = new AbonnementService();
        se.ajouter(a);
        se.afficher().forEach(System.out::println);*/
        
      /*int min = 100000;
      int max = 999999;
          System.out.println("Random value in int from "+min+" to "+max+ ":");
      int random_int = (int)(Math.random() * (max - min + 1) + min);
      System.out.println(random_int);*/
    }
    
}
