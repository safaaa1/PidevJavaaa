/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;

import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
import pidev.utils.ConnectionBD;

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
    private TableView<Salaire> table;
    @FXML
    private MenuItem supprimer;
    @FXML
    private MenuItem modifier;
    @FXML
    private TableColumn<Salaire, Integer> colid;
    @FXML
    private TableColumn<Salaire, Integer> colchiffre;
    @FXML
    private TableColumn<Salaire, Integer> colprime;
    @FXML
    private TableColumn<Salaire, Date> coldate;

     SalaireService se ;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
         se = new SalaireService();
         viewSalaire();
    }

    public void viewSalaire(){
        ObservableList<Salaire> observableArrayList = FXCollections.observableArrayList(se.read());
        colid.setCellValueFactory(new PropertyValueFactory<Salaire,Integer>("id"));
        colchiffre.setCellValueFactory(new PropertyValueFactory<Salaire,Integer>("chiffre"));
        colprime.setCellValueFactory(new PropertyValueFactory<Salaire,Integer>("prime"));
        coldate.setCellValueFactory(new PropertyValueFactory<Salaire,Date>("date"));
  
  table.setItems(observableArrayList);
  
    }
    @FXML
    private void insertSalaire(ActionEvent event) {
        if (!chiffretxt.getText().equals("") && !primetxt.getText().equals("")) {
           // SalaireService se = new SalaireService();
            se.add(new Salaire(Integer.parseInt(chiffretxt.getText()), Integer.parseInt(primetxt.getText()), new Date()), 26);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setContentText("Salaire ajouté !");
            alert.showAndWait();
            chiffretxt.setText("");
            primetxt.setText("");
              viewSalaire();
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Warning");
            //alert.setHeaderText("Look, a Warning Dialog");
            alert.setContentText("verifier vos parametres ");
            alert.showAndWait();
        }
    }
    @FXML
    private void supprimer(ActionEvent event) {
          Salaire m = table.getSelectionModel().getSelectedItem();

    if (m == null) {
    Alert alert = new Alert(AlertType.WARNING);

    alert.setAlertType(Alert.AlertType.WARNING);

     // set content text
     alert.setContentText(" Choix invalide ");

     // show the dialog
     alert.show();
  }
     else{
          SalaireService se =new SalaireService();
       Salaire e =new Salaire(m.getId());
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("Confirmation ");
      alert.setHeaderText(null);
      alert.setContentText("Vous voulez vraiment supprimer ce Salaire ");
      Optional<ButtonType> action = alert.showAndWait();
      if (action.get() == ButtonType.OK) {
         se.delete(e);
         viewSalaire();

      }
    }
    }
    
 
   
      
    
}
