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
public class Dashboard1Controller implements Initializable {

    @FXML
    private AnchorPane container_client;
    @FXML
    private Button GestPar1;
    @FXML
    private Button GestPar11;
    @FXML
    private Button GestReser21;
    @FXML
    private Button btn_goBack;
    @FXML
    private Button GestPar12;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void GoToClasses(ActionEvent event) throws IOException {
         System.out.println(" Retour ");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/gui/Payment.fxml"));
        Parent root = loader.load();
        container_client.getChildren().setAll(root);
   
        
    }

    @FXML
    private void GoToEns(ActionEvent event) throws IOException {
          System.out.println(" Retour ");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/gui/EventFront.fxml"));
        Parent root = loader.load();
        container_client.getChildren().setAll(root);
   
   
    }

    @FXML
    private void GoToResponse(ActionEvent event) throws IOException {
         System.out.println(" Retour ");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/gui/Reclamation.fxml"));
        Parent root = loader.load();
        container_client.getChildren().setAll(root);
    }

    @FXML
    private void Deconnexion(MouseEvent event) {
    }

    @FXML
    private void LogOut(ActionEvent event) throws IOException {
         System.out.println(" Retour ");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/gui/seConnecter.fxml"));
        Parent root = loader.load();
        container_client.getChildren().setAll(root);
    }

    @FXML
    private void GoToCon(ActionEvent event) throws IOException {
          System.out.println(" Retour ");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/gui/AbonnementContine.fxml"));
        Parent root = loader.load();
        container_client.getChildren().setAll(root);
        
        
    }
    
}
