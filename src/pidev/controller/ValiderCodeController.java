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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Moetaz Jebri
 */
public class ValiderCodeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField codetxt;

    @FXML
    private Button valider;
    
    @FXML
    private BorderPane border;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    
    
    @FXML
    public void valider(ActionEvent event) throws IOException{

        if(codetxt.getText().equals("619588"))
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/gui/mdp.fxml"));
            Parent root = loader.load();
            MdpController MdpController = loader.getController();
            border.getScene().setRoot(root);
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
           // alert.setHeaderText("Look, an Information Dialog");
            alert.setContentText("Code Invalide!");
            alert.showAndWait();
        }
    }
    
}