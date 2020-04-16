/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javax.mail.MessagingException;
import pidev.entites.Inscription;
import pidev.services.InscriptionService;
import pidev.utils.ConnectionBD;
import pidev.utils.Mail;

/**
 * FXML Controller class
 *
 * @author Moetaz Jebri
 */
public class InscriptionContineController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField mot;
    
    private ObservableList<Inscription> list;
    
    @FXML
    private TextField nomptxt;

    @FXML
    private TextField pretxt;

    @FXML
    private TextField emailtxt;

    @FXML
    private PasswordField mdptxt;

    @FXML
    private PasswordField cmdptxt;

    @FXML
    private Button save;

    @FXML
    private Button saveedit;


    @FXML
    private Button logout;

    @FXML
    private TableView<Inscription> table;

    @FXML
    private TableColumn<Inscription, String> colenf;

    @FXML
    private TableColumn<Inscription, String> colpar;

    @FXML
    private TableColumn<Inscription, String> coltype;

    @FXML
    private MenuItem supprimer;

    @FXML
    private MenuItem modifier;

    public ObservableList<Inscription> data=FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        viewInscription();
    }   
    
    public void viewInscription(){
    InscriptionService se = new InscriptionService();
    table.setItems((ObservableList<Inscription>) se.afficher());
    colenf.setCellValueFactory(new PropertyValueFactory<Inscription,String>("Nom"));
    colpar.setCellValueFactory(new PropertyValueFactory<Inscription,String>("Prenom"));
    coltype.setCellValueFactory(new PropertyValueFactory<Inscription,String>("Mail"));

    }
     @FXML
    public void insertInscription(ActionEvent event) throws MessagingException{
        if(!nomptxt.getText().equals("")&&!pretxt.getText().equals("")&&(mdptxt.getText().equals(cmdptxt.getText()))){
            InscriptionService se = new InscriptionService();
         se.ajouter(new Inscription(nomptxt.getText(),pretxt.getText(),emailtxt.getText(),mdptxt.getText(),cmdptxt.getText()));
           
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
           // alert.setHeaderText("Look, an Information Dialog");
            alert.setContentText("Inscription ajouté !");
            alert.showAndWait();
               /*nomtxt.setText("");
               agetxt.setText("");*/
               viewInscription();
               Mail.sendMail("moetaz23@gmail.com");
              // clearFields();

        }else{
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        //alert.setHeaderText("Look, a Warning Dialog");
        alert.setContentText("verifier vos parametres ");
        alert.showAndWait();
        }

    }
        @FXML
    void supprimer(ActionEvent event) {
    Inscription m = table.getSelectionModel().getSelectedItem();

    if (m == null) {
    Alert alert = new Alert(Alert.AlertType.WARNING);

    alert.setAlertType(Alert.AlertType.WARNING);

     // set content text
     alert.setContentText(" Choix invalide ");

     // show the dialog
     alert.show();
  }
     else{
           InscriptionService se =new InscriptionService();
       Inscription e =new Inscription(m.getIdins());
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("Confirmation ");
      alert.setHeaderText(null);
      alert.setContentText("Vous voulez vraiment supprimer cette Inscription ");
      Optional<ButtonType> action = alert.showAndWait();
      if (action.get() == ButtonType.OK) {
         se.supprimer(e);
         viewInscription();

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
            nomptxt.setText(data.get(0).getNom());
            pretxt.setText(data.get(0).getPrenom());
            emailtxt.setText(data.get(0).getMail());
            return id;
    }
  int getid()
    {
        data=table.getSelectionModel().getSelectedItems();
            int id;
          return  id=data.get(0).getIdins();
    }
    
  
  @FXML
      void saveedit(ActionEvent event) {
             Connection cnx = ConnectionBD.getInstance().getCnx();
             
         try {
            String requete = "UPDATE inscri SET Nom=?,Prenom=?,Mail=?,Motedepasse=?,ConMdp=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
                        

            pst.setString(1, nomptxt.getText());
            pst.setString(2, pretxt.getText());
            pst.setString(3, emailtxt.getText());
            pst.setString(4, mdptxt.getText());
            pst.setString(5, cmdptxt.getText());
            pst.setInt(6, getid());
            
            pst.executeUpdate();
           // System.out.println("Enfant modifiée !");
            viewInscription();
            //clearFields();
                         Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
           // alert.setHeaderText("Look, an Information Dialog");
            alert.setContentText("Inscription Modifieé !");
            alert.showAndWait();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } 
    }
      
      
      @FXML
    private void chercher(KeyEvent event) {
        
        
        System.out.println(mot.getText());
        InscriptionService evenementService = new  InscriptionService();
        list = evenementService.chercher(mot.getText());
        for ( Inscription evenement : list) {
            System.out.println(evenement);
        }
        
        colenf.setCellValueFactory(new PropertyValueFactory<Inscription,String>("Nom"));
        colpar.setCellValueFactory(new PropertyValueFactory<Inscription,String>("Prenom"));
        coltype.setCellValueFactory(new PropertyValueFactory<Inscription,String>("Mail"));
        table.setItems(list);
    }
    
}
