/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author yanisinfo
 */
public class AhmedController implements Initializable {

    @FXML
    private AnchorPane container_client;
    @FXML
    private Button GestReser;
    @FXML
    private Button GestPar;
    @FXML
    private Button avis;
    @FXML
    private Button btn_goBack;
    @FXML
    private Button btn_goBack1;
    @FXML
    private Button avis1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    private void GestMed(ActionEvent event) throws IOException {
        System.out.println(" Retour ");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/gui/Cours.fxml"));
        Parent root = loader.load();
        container_client.getChildren().setAll(root);
   
    }

    @FXML
    private void GestDos(ActionEvent event) throws IOException {
        System.out.println(" Retour ");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/gui/Grade.fxml"));
        Parent root = loader.load();
        container_client.getChildren().setAll(root);
   
    }

    @FXML
    private void GoToConsu(ActionEvent event) throws IOException {
        System.out.println(" Retour ");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/gui/Classe.fxml"));
        Parent root = loader.load();
        container_client.getChildren().setAll(root);
   
        
    }

   
    @FXML
    private void Affect(ActionEvent event) throws IOException {
        System.out.println(" Retour ");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/gui/PieChart.fxml"));
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
    private void Retour(ActionEvent event) throws IOException {
        System.out.println(" Retour ");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/gui/Dashboard.fxml"));
        Parent root = loader.load();
        container_client.getChildren().setAll(root);
   
    }


    @FXML
    private void Deconnexion(MouseEvent event) {
    }
    
}
