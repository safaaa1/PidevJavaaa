/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import pidev.entites.Reclamations;
import pidev.entites.Reponse;
import pidev.services.ReclamationsService;
import pidev.utils.ConnectionBD;

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
    private TableColumn<Reclamations,Integer> colid;
    @FXML
    private TableColumn<Reclamations,String>colnom;
    @FXML
    private TableColumn<Reclamations,String>coltitre;
    @FXML
    private TableColumn<Reclamations,String> colemail;
    @FXML
    private TableColumn<Reclamations,Integer> coltel;
    @FXML
    private TableColumn<Reclamations,String> coldesc;
    @FXML
    private TableColumn<Reclamations,Integer> colreponse;
    private ComboBox<Reponse> colrep;
           public ObservableList<Reclamations> data=FXCollections.observableArrayList();
       public ObservableList<Reponse> data2=FXCollections.observableArrayList();
    @FXML
    private Button viewReclamation;

   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      // viewReclamation();
        
       //  viewReponse();
    } 
    /*
    public void viewReclamation(){
    ReclamationsService se = new ReclamationsService();
    ObservableList<Reclamations> observableArrayList = 
           FXCollections.observableArrayList(se.read());
    colid.setCellValueFactory(new PropertyValueFactory<Reclamations,Integer>("id"));
    colnom.setCellValueFactory(new PropertyValueFactory<Reclamations,String>("nom"));
    coltitre.setCellValueFactory(new PropertyValueFactory<Reclamations,String>("titre"));
    coltel.setCellValueFactory(new PropertyValueFactory<Reclamations,Integer>("tel"));
    colemail.setCellValueFactory(new PropertyValueFactory<Reclamations,String>("email"));
    coldesc.setCellValueFactory(new PropertyValueFactory<Reclamations,String>("description"));
    colreponse.setCellValueFactory(new PropertyValueFactory<Reclamations,Integer>("reponseid"));
    
    
    //table.setItems((ObservableList<Reclamations>) se.read());
  

    }
    
    
       public void viewReponse(){
    try{
      Connection cnx = ConnectionBD.getInstance().getCnx();
      String sql="SELECT * from reponse";
      PreparedStatement stat = cnx.prepareStatement(sql);
      ResultSet rs = stat.executeQuery();
      while (rs.next()){
      data2.add(new Reponse(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getInt(5), rs.getString(6)));

      }
      

    }
    catch(Exception r){
        r.printStackTrace();
    }
//      colrep.setItems(data2);

    }
  */
   @FXML
    private void insertReclamation(ActionEvent event) {
       //  if(!nomtxt.getText().isEmpty()){
        if(!nomtxt.getText().equals("")&&!titretxt.getText().equals("")&&!emailtxt.getText().equals("")&&!teltxt.getText().equals("")&&!desctxt.getText().equals("")){
            ReclamationsService se = new ReclamationsService();
           // Reponse dm =colrep.getSelectionModel().getSelectedItem();
        se.add(new Reclamations(nomtxt.getText(),titretxt.getText(),Integer.parseInt(teltxt.getText()),emailtxt.getText(),desctxt.getText()));
           
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
           // alert.setHeaderText("Look, an Information Dialog");
            alert.setContentText("Reclamation ajouté !");
            alert.showAndWait();
               nomtxt.setText("");
               titretxt.setText("");
               teltxt.setText("");
               emailtxt.setText("");
               desctxt.setText("");
               //viewReclamation();

        }else{
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        //alert.setHeaderText("Look, a Warning Dialog");
        alert.setContentText("verifier vos parametres ");
        alert.showAndWait();
        }

        
      

    }

    
}

