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
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Moetaz Jebri
 */
public class MdpController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TextField mdptxt;

    @FXML
    private TextField cmdptxt;
    
    @FXML
    private BorderPane border;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    public void valider(ActionEvent event) throws IOException{

        if(mdptxt.getText().equals(cmdptxt.getText())&&!cmdptxt.getText().equals(""))
        {
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            // alert.setHeaderText("Look, an Information Dialog");
            alert.setContentText("Mot de passe modifier!");
            alert.showAndWait();
            
            
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/gui/seConnecter.fxml"));
            Parent root = loader.load();
            SeConnecterGuestController SeConnecterController = loader.getController();
            border.getScene().setRoot(root);
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
           // alert.setHeaderText("Look, an Information Dialog");
            alert.setContentText("Mot de passe Invalide!");
            alert.showAndWait();
        }
    }
    
}
