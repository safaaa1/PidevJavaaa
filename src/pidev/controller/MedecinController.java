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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import pidev.entites.Medecin;
import pidev.services.ServiceMedecin;
import pidev.utils.ConnectionBD;

/**
 *
 * @author yanisinfo
 */
public class MedecinController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        viewMedecin();
        numTelTest.setVisible(false);

    }
        @FXML
    private TextField nomtxt;

    @FXML
    private TextField prenomtxt;

    @FXML
    private TextField teltxt;

    @FXML
    private TextField emailtxt;

    @FXML
    private Button save;

    @FXML
    private Button saveedit;

    @FXML
    private Button reset;

    @FXML
    private TextField recherchetxt;
      @FXML
    private TableView<Medecin> table;

    @FXML
    private TableColumn<Medecin, Integer> colid;

    @FXML
    private TableColumn<Medecin,String> colnom;

    @FXML
    private TableColumn<Medecin, String> colprenom;

    @FXML
    private TableColumn<Medecin, Integer> coltel;

    @FXML
    private TableColumn<Medecin, String> colemail;
    @FXML
    private Label numTelTest;
    boolean verificationNumTel=false;
    public ObservableList<Medecin> data=FXCollections.observableArrayList();

    
        public void viewMedecin(){
    ServiceMedecin sm = new ServiceMedecin();
    table.setItems((ObservableList<Medecin>) sm.afficher());
    colid.setCellValueFactory(new PropertyValueFactory<Medecin,Integer>("idMedecin"));
    colnom.setCellValueFactory(new PropertyValueFactory<Medecin,String>("nom"));
    colprenom.setCellValueFactory(new PropertyValueFactory<Medecin,String>("prenom"));
    coltel.setCellValueFactory(new PropertyValueFactory<Medecin,Integer>("tel"));
    colemail.setCellValueFactory(new PropertyValueFactory<Medecin,String>("email"));


    }
    public void insertMedecin(ActionEvent event){
    if(!nomtxt.getText().equals("")&&!prenomtxt.getText().equals("")&&!nomtxt.getText().equals("")&&!prenomtxt.getText().equals("")){
        ServiceMedecin se = new ServiceMedecin();
      //  DossierMedical dm =cbdm.getSelectionModel().getSelectedItem();
    se.ajouter(new Medecin(nomtxt.getText(),Integer.parseInt(teltxt.getText()),prenomtxt.getText(),emailtxt.getText()));

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information");
       // alert.setHeaderText("Look, an Information Dialog");
        alert.setContentText("Medecin ajouté !");
        alert.showAndWait();
           nomtxt.setText("");
           prenomtxt.setText("");
           emailtxt.setText("");
           teltxt.setText("");
           viewMedecin();

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
    Medecin m = table.getSelectionModel().getSelectedItem();

    if (m == null) {
    Alert alert = new Alert(AlertType.WARNING);

    alert.setAlertType(Alert.AlertType.WARNING);

     // set content text
     alert.setContentText(" Choix invalide ");

     // show the dialog
     alert.show();
  }
     else{
           ServiceMedecin se =new ServiceMedecin();
       Medecin e =new Medecin(m.getIdMedecin());
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("Confirmation ");
      alert.setHeaderText(null);
      alert.setContentText("Vous voulez vraiment supprimer ce Medecin ");
      Optional<ButtonType> action = alert.showAndWait();
      if (action.get() == ButtonType.OK) {
         se.supprimer(e);
         viewMedecin();

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
           id=data.get(0).getNom();
            System.out.println(id);
            teltxt.setText(String.valueOf(data.get(0).getTel()));
            nomtxt.setText(data.get(0).getNom());
            prenomtxt.setText(data.get(0).getPrenom());
            emailtxt.setText(data.get(0).getEmail());

            return id;
    }
               @FXML
    void saveedit(ActionEvent event) {
        Connection cnx = ConnectionBD.getInstance().getCnx();

        try {
            String requete = "UPDATE medcin SET nom=?,tel=?,prenom=?,email=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(5, getid());
            pst.setString(1, nomtxt.getText());
            pst.setInt(2, Integer.parseInt(teltxt.getText()));
            pst.setString(3, prenomtxt.getText());
            pst.setString(4, emailtxt.getText());
            pst.executeUpdate();
            System.out.println("Medecin modifiée !");
            viewMedecin();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }
     int getid()
    {
        data=table.getSelectionModel().getSelectedItems();
            int id;
          return  id=data.get(0).getIdMedecin();
    }
     
     
     
     
     
     
    @FXML
    private void controlNumero(KeyEvent event) {
        verificationNumTel = false;
        if (teltxt.getText().trim().length() == 8) {
            boolean test = true;
            for (int i = 1; i < teltxt.getText().trim().length() && test; i++) {
                char ch = teltxt.getText().charAt(i);
                if (Character.isLetter(ch)) {
                    test = false;
                }
            }
            if (test) {
                System.out.println("taille num est valide");
                numTelTest.setVisible(false);
                verificationNumTel = true;
            }
        } else {
            System.out.println("taille num non valide");
            numTelTest.setVisible(true);
            numTelTest.setText("Il faut 8 chiffres");
            verificationNumTel = false;
        }
    }
}
