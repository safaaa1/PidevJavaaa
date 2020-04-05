/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author islem
 */
public class ReclamationController implements Initializable {

    @FXML
    private TextField nomtxt;
    @FXML
    private TextField titretxt;
    @FXML
    private TextField emailtxt;
    @FXML
    private TextField teltxt;
    @FXML
    private TextField desctxt;
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
    private TableColumn<?, ?> colnom;
    @FXML
    private TableColumn<?, ?> coltitre;
    @FXML
    private TableColumn<?, ?> colemail;
    @FXML
    private TableColumn<?, ?> coltel;
    @FXML
    private TableColumn<?, ?> coldesc;
    @FXML
    private TableColumn<?, ?> colreponse;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
