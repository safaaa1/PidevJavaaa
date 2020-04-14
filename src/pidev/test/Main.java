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
        
        Parent root = FXMLLoader.load(getClass().getResource("/pidev/gui/reclamationadmin.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    
        
    
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       launch(args);
        /*
        EnseignantService rr = new EnseignantService();
       rr.sumsalaire();*/
}
}
