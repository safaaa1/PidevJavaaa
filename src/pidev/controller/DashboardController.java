/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.controller;

import java.io.IOException;
import static java.lang.Thread.sleep;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pidev.entites.Utilisateur;
import pidev.utils.Session;

/**
 * FXML Controller class
 *
 * @author safa
 */
public class DashboardController implements Initializable {

    @FXML
    private Button GestReser;
    @FXML
    private Button GestPar;
    @FXML
    private Button avis;
    
    @FXML
    private AnchorPane container_client;
    @FXML
    private Button btn_goBack;
        Utilisateur u= Session.getLoggedInUser();
    private Button btnprofil;
    @FXML
    private Button GestReser1;
    @FXML
    private Button GestPar1;
    @FXML
    private Button GestPar11;
    @FXML
    private Button GestReser2;
    @FXML
    private Button GestReser21;
    @FXML
    private Button GestReser211;
    @FXML
    private Button GestDispo;
    @FXML
    private Button GestReser11;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
@FXML
    private void GestDispo(ActionEvent event) throws IOException {
         System.out.println(" Ajouter un evenement ");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/gui/AfficheEvenement.fxml"));
        Parent root = loader.load();
        container_client.getChildren().setAll(root);
    }
   

    @FXML
    private void GestReser(ActionEvent event) throws IOException {
        System.out.println(" Lister les evenements ");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/gui/AfficherCommande.fxml"));
        Parent root = loader.load();
        container_client.getChildren().setAll(root); 
    }


    
    @FXML
    private void Deconnexion(MouseEvent event) {
    }

    @FXML
    private void GestPar(ActionEvent event) throws IOException {
      System.out.println(" Ajouter un parent ");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/gui/GestionParent.fxml"));
        Parent root = loader.load();
        container_client.getChildren().setAll(root);   
    }

    @FXML
    private void LogOut(ActionEvent event) throws IOException {
        System.out.println(" Retour ");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/gui/seConnecter.fxml"));
        Parent root = loader.load();
        container_client.getChildren().setAll(root);
    }


    @FXML
    private void GoToMed(ActionEvent event) throws IOException {
        System.out.println(" Retour ");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/gui/Anis.fxml"));
        Parent root = loader.load();
        container_client.getChildren().setAll(root);
        
    }

    @FXML
    private void GoToEnf(ActionEvent event) throws IOException {
        System.out.println(" Retour ");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/gui/Enfant.fxml"));
        Parent root = loader.load();
        container_client.getChildren().setAll(root);
    }
//badlouha
    @FXML
    private void GoToClasses(ActionEvent event) throws IOException {
        System.out.println(" Retour ");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/gui/Ahmed.fxml"));
        Parent root = loader.load();
        container_client.getChildren().setAll(root);
    }

    @FXML
    private void GoToEns(ActionEvent event) throws IOException {
         System.out.println(" Retour ");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/gui/Enseignant.fxml"));
        Parent root = loader.load();
        container_client.getChildren().setAll(root);
   
        
    }

    @FXML
    private void GoToSal(ActionEvent event) throws IOException {
         System.out.println(" Retour ");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/gui/Salaire.fxml"));
        Parent root = loader.load();
        container_client.getChildren().setAll(root);
   
    }

    @FXML
    private void GoToResponse(ActionEvent event) throws IOException {
         System.out.println(" Retour ");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/gui/Reponse.fxml"));
        Parent root = loader.load();
        container_client.getChildren().setAll(root);
   
    }

    @FXML
    private void GoToCantine(ActionEvent event) throws IOException {
         System.out.println(" Retour ");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/gui/AfficherContine.fxml"));
        Parent root = loader.load();
        container_client.getChildren().setAll(root);
   
    }

    @FXML
    private void GoTorec(ActionEvent event) throws IOException {
        System.out.println(" Retour ");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/gui/reclamationadmin.fxml"));
        Parent root = loader.load();
        container_client.getChildren().setAll(root);
    }

  

}
