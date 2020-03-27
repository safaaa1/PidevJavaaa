/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.controller;

import java.net.URL;
import static java.nio.file.Files.list;
import static java.rmi.Naming.list;
import java.sql.SQLException;
import static java.util.Collections.list;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import pidev.entites.Evenement;
import pidev.services.EvenementService;

/**
 * FXML Controller class
 *
 * @author safa
 */
public class AfficheEvenementController implements Initializable {

    @FXML
    private Button parc;
    @FXML
    private Button userName;
    @FXML
    private Pane paneProfil;
    @FXML
    private Button btn11;
    @FXML
    private Button btn1;
    @FXML
    private AnchorPane anchorEvent;
    @FXML
    private VBox vbPanier;
    @FXML
    private VBox nom;
    @FXML
    private VBox type;
    @FXML
    private VBox place;
    @FXML
    private VBox dressCode;
    @FXML
    private VBox image;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void afficherAccueil(ActionEvent event) {
    }

    @FXML
    private void AfficherParc(ActionEvent event) {
    }

    @FXML
    private void showPaneProfil(MouseEvent event) {
    }

    @FXML
    private void connexionAction(ActionEvent event) {
    }

    @FXML
    private void hidePane(MouseEvent event) {
    }
  
    
    
    
}
