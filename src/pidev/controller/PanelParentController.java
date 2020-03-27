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
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Mohamed
 */
public class PanelParentController implements Initializable {

    @FXML
    private AnchorPane container_client;
    @FXML
    private Label idUtilisateur;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void afficherEvenement(ActionEvent actionEvent) throws IOException {
        System.out.println("afficher liste des evenements");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/gui/listeDesevenements.fxml"));
        Parent root = loader.load();
        container_client.getChildren().setAll(root);
    }
    
}