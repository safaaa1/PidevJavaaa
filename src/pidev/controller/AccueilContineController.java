
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.controller;


import java.io.IOException;
import java.net.URL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;


/**
 *
 * @author safa
 */
public class AccueilContineController {

    @FXML
    private Button bic;

    @FXML
    private Button bac;

    @FXML
    private Button bc;

    /**
     * Initializes the controller class.
     */
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println("Acceuil Contine");
        
        
    }

       
    @FXML
    public void sinscrire(ActionEvent actionEvent) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/gui/InscriptionContine.fxml"));
        Parent root = loader.load();
        InscriptionContineController InscriptionContineController = loader.getController();
        bic.getScene().setRoot(root);
    }
    
    @FXML
    public void sinscrire1(ActionEvent actionEvent) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/gui/AbonnementContine.fxml"));
        Parent root = loader.load();
        AbonnementContineController AbonnementContineController = loader.getController();
        bac.getScene().setRoot(root);
    }
    
    @FXML
    public void sinscrire2(ActionEvent actionEvent) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/gui/AfficherContine.fxml"));
        Parent root = loader.load();
        AfficherContineController AfficherContineController = loader.getController();
        bc.getScene().setRoot(root);
    }
      
     
     
    
    
    }
    
