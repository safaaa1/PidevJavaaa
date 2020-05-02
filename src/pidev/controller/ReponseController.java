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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import pidev.entites.Enseignant;
import pidev.entites.Reclamations;
import pidev.entites.Reponse;
import pidev.entites.Salaire;
import pidev.services.EnseignantService;
import pidev.services.ReclamationsService;
import pidev.services.ReponseService;
import pidev.utils.ConnectionBD;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;


/**
 * FXML Controller class
 *
 * @author islem
 */
public class ReponseController implements Initializable {
    
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
    private TextField recherchetxt;
    
    @FXML
    private TableView<Reponse> table;
    
    @FXML
    private MenuItem supprimer;
    
    @FXML
    private MenuItem modifier;
    

    
    @FXML
    private TableColumn<Reponse, String> colnom;
    
    @FXML
    private TableColumn<Reponse, String> coltitre;
    
    @FXML
    private TableColumn<Reponse, String> colemail;
    
    @FXML
    private TableColumn<Reponse, Integer> coltel;
    
    @FXML
    private TableColumn<Reponse, String> coldesc;

   // @FXML
    //private ComboBox<Reponse> colrep;
       public ObservableList<Reponse> data=FXCollections.observableArrayList();
     //  public ObservableList<Reponse> data2=FXCollections.observableArrayList();
    @FXML
    private Button save;
    @FXML
    private Button saveedit;
    @FXML
    private Button viewEnfant;
    @FXML
    private BorderPane sakhta;
    @FXML
    private Button viewEnfant1;

  
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       viewReponse();
        
       //  viewReponse();
    } 
    public void viewReponse(){
    ReponseService se = new ReponseService();
    //table.setItems((ObservableList<Reponse>) se.read());
    ObservableList<Reponse> observableArrayList = 
           FXCollections.observableArrayList(se.read());
  
    colnom.setCellValueFactory(new PropertyValueFactory<Reponse,String>("nom"));
    coltitre.setCellValueFactory(new PropertyValueFactory<Reponse,String>("titre"));
    colemail.setCellValueFactory(new PropertyValueFactory<Reponse,String>("email"));
    coltel.setCellValueFactory(new PropertyValueFactory<Reponse,Integer>("tel"));
  
    coldesc.setCellValueFactory(new PropertyValueFactory<Reponse,String>("description"));

    
    
  table.setItems((ObservableList<Reponse>) se.read());
  

    }
    
     @FXML
    private void insertReponse(ActionEvent event) {
       
        if(!nomtxt.getText().equals("")&&!titretxt.getText().equals("")&&!emailtxt.getText().equals("")&&!teltxt.getText().equals("")&&!desctxt.getText().equals("")){
            ReponseService se = new ReponseService();
           // Reponse dm =colrep.getSelectionModel().getSelectedItem();
        se.add(new Reponse(nomtxt.getText(),titretxt.getText(),emailtxt.getText(),Integer.parseInt(teltxt.getText()),desctxt.getText()),52);
      
           
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information");
           // alert.setHeaderText("Look, an Information Dialog");
            alert.setContentText("Reponse ajouté !");
            alert.showAndWait();
               nomtxt.setText("");
               titretxt.setText("");
               emailtxt.setText("");
               teltxt.setText("");
              
               desctxt.setText("");
               viewReponse();

        }else{
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Warning");
        //alert.setHeaderText("Look, a Warning Dialog");
        alert.setContentText("verifier vos parametres ");
        alert.showAndWait();
        }

        
      

    }
    
    @FXML
    private void modifier(ActionEvent event) {
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
            titretxt.setText(data.get(0).getTitre());
            emailtxt.setText(data.get(0).getEmail());
            desctxt.setText(data.get(0).getDescription());
            return id;
    }

       @FXML
    void saveedit(ActionEvent event) {
             Connection cnx = ConnectionBD.getInstance().getCnx();
             
         try{
            String requete = "UPDATE Reponse SET nom=?,titre=?,email=?,tel=?,description=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
                       

            pst.setInt(6, getid());
            pst.setString(1, nomtxt.getText());
            pst.setString(2, titretxt.getText());
            pst.setString(3, emailtxt.getText());
            pst.setInt(4, Integer.parseInt(teltxt.getText()));
            pst.setString(5, desctxt.getText());

           //salaire
            
            pst.executeUpdate();
            System.out.println("reponse modifiée !");
            viewReponse();

        }catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
 int getid()
    {
        data=table.getSelectionModel().getSelectedItems();
            int id;
          return  id=data.get(0).getId();
    
    }
@FXML
    private void supprimer(ActionEvent event) {
          Reponse m = table.getSelectionModel().getSelectedItem();

    if (m == null) {
    Alert alert = new Alert(AlertType.WARNING);

    alert.setAlertType(Alert.AlertType.WARNING);

     // set content text
     alert.setContentText(" Choix invalide ");

     // show the dialog
     alert.show();
  }
     else{
           ReponseService se =new ReponseService();
       Reponse rp =new Reponse(m.getId());
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("Confirmation ");
      alert.setHeaderText(null);
      alert.setContentText("Vous voulez vraiment supprimer ce Reponse ");
      Optional<ButtonType> action = alert.showAndWait();
      if (action.get() == ButtonType.OK) {
         se.Delete(rp);
         viewReponse();

      }
    }
    }   

     @FXML
    private void Retour(ActionEvent event) throws IOException {
        System.out.println(" Retour ");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/gui/Dashboard.fxml"));
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

