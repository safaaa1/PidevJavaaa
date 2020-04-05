/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.controller;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import pidev.entites.Enseignant;
import pidev.entites.Salaire;
import pidev.services.EnseignantService;
import pidev.services.SalaireService;

/**
 * FXML Controller class
 *
 * @author islem
 */
public class SalaireController implements Initializable {

    @FXML
    private TextField chiffretxt;
    @FXML
    private TextField primetxt;
    @FXML
    private DatePicker dpdate;
    @FXML
    private Button save;
    @FXML
    private Button saveedit;
    @FXML
    private Button reset;
    @FXML
    private TextField recherchetxt;
    @FXML
    private Button viewEnfant;
    @FXML
    private TableView<?> table;
    @FXML
    private MenuItem supprimer;
    @FXML
    private MenuItem modifier;
    @FXML
    private TableColumn<?, ?> colid;
    @FXML
    private TableColumn<?, ?> colchiffre;
    @FXML
    private TableColumn<?, ?> colprime;
    @FXML
    private TableColumn<?, ?> coldate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
  
    }    

      
    
}
