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
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import pidev.entites.DossierMedical;
import pidev.services.ServiceDossierMedical;
import pidev.services.ServiceEnfant;
import pidev.utils.ConnectionBD;

/**
 *
 * @author yanisinfo
 */
public class DossierMedicalController implements Initializable {
    @FXML
    private TextField titretxt;

    @FXML
    private TextArea contenutxt;
    @FXML
    private TextField recherchetxt;
    @FXML
    private TableView<DossierMedical> table;

    @FXML
    private TableColumn<DossierMedical, Integer> colid;

    @FXML
    private TableColumn<DossierMedical, String> coltitre;

    @FXML
    private TableColumn<DossierMedical, String> colcontenu;

    public ObservableList<DossierMedical> data=FXCollections.observableArrayList();

    public void initialize(URL location, ResourceBundle resources) {
        viewDossierMedical();
    }
    public void viewDossierMedical(){
    ServiceDossierMedical se = new ServiceDossierMedical();
    table.setItems((ObservableList<DossierMedical>) se.afficher());
    colid.setCellValueFactory(new PropertyValueFactory<DossierMedical,Integer>("idDM"));
    coltitre.setCellValueFactory(new PropertyValueFactory<DossierMedical,String>("titre"));
    colcontenu.setCellValueFactory(new PropertyValueFactory<DossierMedical,String>("contenu"));

    }
    public void insertDossierMedical(ActionEvent event){
        if(!titretxt.getText().equals("")&&!contenutxt.getText().equals("")){
            ServiceDossierMedical se = new ServiceDossierMedical();
        se.ajouter(new DossierMedical(titretxt.getText(),contenutxt.getText()));
           
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information");
           // alert.setHeaderText("Look, an Information Dialog");
            alert.setContentText("Dossier Medical ajouté !");
            alert.showAndWait();
               titretxt.setText("");
               contenutxt.setText("");
               viewDossierMedical();

        }else{
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Warning");
        //alert.setHeaderText("Look, a Warning Dialog");
        alert.setContentText("verifier vos parametres ");
        alert.showAndWait();
        }

    }  
        @FXML
    void supprimer(ActionEvent event) {
    DossierMedical m = table.getSelectionModel().getSelectedItem();

    if (m == null) {
    Alert alert = new Alert(AlertType.WARNING);

    alert.setAlertType(Alert.AlertType.WARNING);

     // set content text
     alert.setContentText(" Choix invalide ");

     // show the dialog
     alert.show();
  }
     else{
           ServiceDossierMedical se =new ServiceDossierMedical();
       DossierMedical e =new DossierMedical(m.getIdDM());
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("Confirmation ");
      alert.setHeaderText(null);
      alert.setContentText("Vous voulez vraiment supprimer ce Dossier ? ");
      Optional<ButtonType> action = alert.showAndWait();
      if (action.get() == ButtonType.OK) {
         se.supprimer(e);
         viewDossierMedical();

      }
    }
}
         @FXML
         private void modifier(ActionEvent event){
           selectionner();
   
    }

 String selectionner()
    {
          data=table.getSelectionModel().getSelectedItems();
            String id;
           id=data.get(0).getTitre();
            System.out.println(id);
            titretxt.setText(data.get(0).getTitre());
            contenutxt.setText(data.get(0).getContenu());
            return id;
    }
        @FXML
    void saveedit(ActionEvent event) {
             Connection cnx = ConnectionBD.getInstance().getCnx();
             
         try {
            String requete = "UPDATE dossier_medical SET titre=?,contenu=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(3, getid());
            pst.setString(1, titretxt.getText());
            pst.setString(2, contenutxt.getText());           
            pst.executeUpdate();
            System.out.println("Dossier modifiée !");
            viewDossierMedical();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } 
    }

 int getid()
    {
        data=table.getSelectionModel().getSelectedItems();
            int id;
          return  id=data.get(0).getIdDM();
    }
}
