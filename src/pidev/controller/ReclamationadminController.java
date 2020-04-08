/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pidev.controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pidev.entites.Reclamations;
import pidev.services.ReclamationsService;

/**
 * FXML Controller class
 *
 * @author islem
 */
public class ReclamationadminController extends ReclamationController {
       @FXML
    private TableView<Reclamations> table;
         @FXML
    private MenuItem supprimer;
      @FXML
    private TableColumn<Reclamations, Integer> colid;
    @FXML
    private TableColumn<Reclamations, String> colnom;
    @FXML
    private TableColumn<Reclamations, String> coltitre;
    @FXML
    private TableColumn<Reclamations, String> colemail; 
    @FXML
    private TableColumn<Reclamations, Integer> coltel;
    @FXML
    private TableColumn<Reclamations, String> coldesc;
    @FXML
    private TableColumn<Reclamations, Integer> colreponse;
   // @FXML
    //private ComboBox<Reponse> colrep;
       public ObservableList<Reclamations> data=FXCollections.observableArrayList();
      // public ObservableList<Reponse> data2=FXCollections.observableArrayList();
    @FXML
    private Button viewReclamation;

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        viewReclamations();
    }    
     public void viewReclamations(){
    ReclamationsService se = new ReclamationsService();
    //table.setItems((ObservableList<Reclamations>) se.read());
       ObservableList<Reclamations> observableArrayList = 
           FXCollections.observableArrayList(se.read());
    colid.setCellValueFactory(new PropertyValueFactory<Reclamations,Integer>("id"));
    colnom.setCellValueFactory(new PropertyValueFactory<Reclamations,String>("nom"));
    coltitre.setCellValueFactory(new PropertyValueFactory<Reclamations,String>("titre"));
    coltel.setCellValueFactory(new PropertyValueFactory<Reclamations,Integer>("tel"));
    colemail.setCellValueFactory(new PropertyValueFactory<Reclamations,String>("email"));
    coldesc.setCellValueFactory(new PropertyValueFactory<Reclamations,String>("description"));
    colreponse.setCellValueFactory(new PropertyValueFactory<Reclamations,Integer>("reponseid"));
    
    
  table.setItems((ObservableList<Reclamations>) se.read());
  

    }
     
 @FXML
    private void supprimer(ActionEvent event) {
          Reclamations m = table.getSelectionModel().getSelectedItem();

    if (m == null) {
    Alert alert = new Alert(AlertType.WARNING);

    alert.setAlertType(Alert.AlertType.WARNING);

     // set content text
     alert.setContentText(" Choix invalide ");

     // show the dialog
     alert.show();
  }
     else{
           ReclamationsService se =new ReclamationsService();
       Reclamations r =new Reclamations(m.getId());
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("Confirmation ");
      alert.setHeaderText(null);
      alert.setContentText("Vous voulez vraiment supprimer ce Reclamations ");
      Optional<ButtonType> action = alert.showAndWait();
      if (action.get() == ButtonType.OK) {
         se.Delete(r);
         viewReclamations();

      }
    }
    }
    
}
