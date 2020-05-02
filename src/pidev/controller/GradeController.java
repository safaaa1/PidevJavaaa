/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import pidev.entites.Grade;
import pidev.services.GradeServices;
import pidev.utils.ConnectionBD;

/**
 *
 * @author Lenovo
 */
public class GradeController implements Initializable{

      @FXML
    private TextField nbctxt;

    @FXML
    private TextField nbtxt;

    @FXML
    private TextField nomgtxt;

    @FXML
    private Button save;

    @FXML
    private Button saveedit;


    @FXML
    private Button viewEnfant;

    @FXML
    private TableView<Grade> table;

   

    @FXML
    private TableColumn<Grade, String> colnom;

    @FXML
    private TableColumn<Grade, Integer> colnb;

    @FXML
    private TableColumn<Grade, Integer> colgr;

    @FXML
    private MenuItem supprimer;

    @FXML
    private MenuItem modifier;

    public ObservableList<Grade> data =FXCollections.observableArrayList();
    @FXML
    private BorderPane sakhta;
    @FXML
    private Button viewEnfant1;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        viewGrade();
        
        
    }
    public void viewGrade(){
    GradeServices se = new GradeServices();
    table.setItems((ObservableList<Grade>) se.afficher());
   // colid.setCellValueFactory(new PropertyValueFactory<Grade,Integer>("idgr"));
    colnb.setCellValueFactory(new PropertyValueFactory<Grade,Integer>("nbrclasse"));
    colgr.setCellValueFactory(new PropertyValueFactory<Grade,Integer>("nbrenfgr"));
    colnom.setCellValueFactory(new PropertyValueFactory<Grade,String>("nomgr"));

    }
    
    @FXML
     public void insertGrade(ActionEvent event){
        if(!nbctxt.getText().equals("")&&!nbtxt.getText().equals("")&&!nomgtxt.getText().equals("")){
            GradeServices se = new GradeServices();
            //Grade dm =cbcl.getSelectionModel().getSelectedItem();
        se.ajouter(new Grade(Integer.parseInt(nbctxt.getText()),Integer.parseInt(nbtxt.getText()),nomgtxt.getText()));
           
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information");
           // alert.setHeaderText("Look, an Information Dialog");
            alert.setContentText("Grade ajouté !");
            alert.showAndWait();
               nbctxt.setText("");
               nomgtxt.setText("");
               nbtxt.setText("");
               viewGrade();

        }else{
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Warning");
        //alert.setHeaderText("Look, a Warning Dialog");
        alert.setContentText("verifier vos parametres ");
        alert.showAndWait();
        }

    }
     int getid()
    {
        data=table.getSelectionModel().getSelectedItems();
            int id;
          return  id=data.get(0).getIdgr();
    }
     
     @FXML
         private void modifier(ActionEvent event){
           selectionner();
   
    }
         
         String selectionner()
    {
          data=table.getSelectionModel().getSelectedItems();
            String id;
           id=data.get(0).getNomgr();
            System.out.println(id);
            nbctxt.setText(String.valueOf(data.get(0).getNbrclasse()));
            nbtxt.setText(String.valueOf(data.get(0).getNbrenfgr()));
            nomgtxt.setText(data.get(0).getNomgr());
            return id;
    }
    
         
@FXML
    void saveedit(ActionEvent event) {
       Connection cnx = ConnectionBD.getInstance().getCnx();
             
         try {
            String requete = "UPDATE Grade SET nbrclasse=?,nbrenfgr=?,nomgr=? WHERE idgr=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
                       // Grade dm =cbcl.getSelectionModel().getSelectedItem();

            pst.setInt(4, getid()); 
            
            pst.setInt(1, Integer.parseInt(nbctxt.getText()));
            pst.setInt(2,Integer.parseInt(nbtxt.getText()));
            pst.setString(3, nomgtxt.getText());
            pst.executeUpdate();
            System.out.println("Grade modifiée !");
            viewGrade();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } 
    }

    @FXML
    void supprimer(ActionEvent event) {
    Grade m = table.getSelectionModel().getSelectedItem();

    if (m == null) {
    Alert alert = new Alert(AlertType.WARNING);

    alert.setAlertType(Alert.AlertType.WARNING);

     // set content text
     alert.setContentText(" Choix invalide ");

     // show the dialog
     alert.show();
  }
     else{
           GradeServices se =new GradeServices();
       Grade e =new Grade(m.getIdgr());
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("Confirmation ");
      alert.setHeaderText(null);
      alert.setContentText("Vous voulez vraiment supprimer ce Classe ");
      Optional<ButtonType> action = alert.showAndWait();
      if (action.get() == ButtonType.OK) {
         se.supprimer(e);
         viewGrade();

      }
    }
    }    

    @FXML
    private void Retour(ActionEvent event) throws IOException {
        System.out.println(" Retour ");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/gui/Ahmed.fxml"));
        Parent root = loader.load();
        sakhta.getChildren().setAll(root);
   
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
        System.out.println(" Retour ");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/gui/seConnecter.fxml"));
        Parent root = loader.load();
        sakhta.getChildren().setAll(root);
      
    }
    
}
