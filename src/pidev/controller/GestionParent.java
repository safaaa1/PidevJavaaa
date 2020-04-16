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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import javax.mail.MessagingException;
import pidev.entites.Parentt;
import pidev.services.GestionUtilisateur;
import pidev.services.ParenttService;
import pidev.utils.ConnectionBD;
import pidev.utils.Mail;
import pidev.utils.Password;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
/**
 * FXML Controller class
 *
 * @author safa
 */
public class GestionParent implements Initializable {
   @FXML
    private AnchorPane container;
    @FXML
    private TextField input_nom;
    @FXML
    private TextField input_prenom;
    @FXML
    private TextField input_email;
    @FXML
    private TextField input_mdpp;
    
    @FXML
    private PasswordField input_mdp;

    @FXML
    private TextField input_tel;
    @FXML
    private Button save;
    @FXML
    private TableView<Parentt> table;
    @FXML
    private MenuItem modifier;
    @FXML
    private TableColumn<Parentt, Integer> column_id;
    @FXML
    private TableColumn<Parentt, String> column_nom;
    @FXML
    private TableColumn<Parentt, String> column_prenom;
    @FXML
    private TableColumn<Parentt, String> column_email;
    @FXML
    private TableColumn<Parentt, String> column_mdp;
 @FXML
    private MenuItem supprimer;
 @FXML
    private Button saveedit; 
 
    public ObservableList<Parentt> data=FXCollections.observableArrayList();
    @FXML
    private TableColumn<Parentt, Integer> column_tel;
   Boolean verificationNom = false;
    Boolean verificationPrenom = false;
    Boolean verificationEmail = false;
    Boolean verificationNumTel = false;
    Boolean verificationMdp = false;
   @FXML
    private Label numTelTest;
    @FXML
    private Button btn_goBack;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
                   numTelTest.setVisible(false);
  
        viewParent();
    }    
 public void viewParent(){
    ParenttService se = new ParenttService();
    table.setItems((ObservableList<Parentt>) se.afficher());
    column_id.setCellValueFactory(new PropertyValueFactory<Parentt,Integer>("id"));
    column_nom.setCellValueFactory(new PropertyValueFactory<Parentt,String>("nom"));
  column_prenom.setCellValueFactory(new PropertyValueFactory<Parentt,String>("prenom"));

    column_email.setCellValueFactory(new PropertyValueFactory<Parentt,String>("email"));
    column_mdp.setCellValueFactory(new PropertyValueFactory<Parentt,String>("mdp"));
    column_tel.setCellValueFactory(new PropertyValueFactory<Parentt,Integer>("tel"));

    }
    @FXML
    public void insertParent(ActionEvent event) throws IOException, MessagingException{
        

    if (verificationNom == false) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("Veuillez remplir le nom");
            alert.show();

        } else if (verificationPrenom == false) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("Veillez remplir le prenom");
            alert.show();

            } else if (verificationEmail == false) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("Veuillez remplir l'email");
            alert.show();
        } else if (verificationNumTel == false) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("Veuillez remplir le telephone");
            alert.show();

        } else if ((verificationMdp == false)) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("Veuillez resaissir votre Mot de Passe Correctement");
            alert.show();

        }else{
            Password md = new Password();
            String mdpCrypte1 = md.hashPassword(input_mdp.getText());

            Parentt parentt = new Parentt();
            parentt.setNom(input_nom.getText());
            parentt.setPrenom(input_prenom.getText());
            parentt.setEmail(input_email.getText());
            parentt.setMdp(mdpCrypte1);
             parentt.setTel(Integer.parseInt(input_tel.getText()));

            ParenttService gestionParent = new ParenttService();
            gestionParent.ajouter(parentt);

            TrayNotification tray = new TrayNotification("Successfully",
                    "ajout Effectué avec Succés", NotificationType.SUCCESS);
            tray.setAnimationType(AnimationType.SLIDE);
            tray.showAndDismiss(Duration.seconds(10));
             viewParent();
                          Mail.sendMail("safa.doghmani@esprit.tn");
      
    }}

    
    @FXML
    private void controlNom(KeyEvent event) {

        if (input_nom.getText().trim().equals("")) {
            verificationNom = false;
        } else {
            verificationNom = true;
        }

    }

    @FXML
    private void controlPrenom(KeyEvent event) {
        if (input_prenom.getText().trim().equals("")) {
            verificationPrenom = false;
        } else {
            verificationPrenom = true;
        }
    }

    @FXML
    private void controlNumero(KeyEvent event) {
        verificationNumTel = false;
        if (input_tel.getText().trim().length() == 8) {
            boolean test = true;
            for (int i = 1; i < input_tel.getText().trim().length() && test; i++) {
                char ch = input_tel.getText().charAt(i);
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
    
    @FXML
    private void controlEmail(KeyEvent event) throws SQLException {
        verificationEmail = false;
        GestionUtilisateur gestionUtilisateur = new GestionUtilisateur();
        if(gestionUtilisateur.mailExiste(input_email.getText())){
            System.out.println("mail est unique");
            String email_pattern = "^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+" + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$";
            Pattern pattern = Pattern.compile(email_pattern);
            Matcher matcher = pattern.matcher(input_email.getText());

            if (matcher.matches())
                verificationEmail = true;
        }
        else
            System.out.println("mail non valide");
    }
    
    @FXML
    private void controlMDP(KeyEvent event) {

        if (input_mdp.getText().trim().equals("")) {

            verificationMdp = false;

        } else {

            verificationMdp = true;

        }
    }

    
    
    
    
      @FXML
    void supprimer(ActionEvent event) {
    Parentt m = table.getSelectionModel().getSelectedItem();

    if (m == null) {
    Alert alert = new Alert(AlertType.WARNING);

    alert.setAlertType(Alert.AlertType.WARNING);

     // set content text
     alert.setContentText(" Choix invalide ");

     // show the dialog
     alert.show();
    }
     else{
           ParenttService se =new ParenttService();
       Parentt e =new Parentt(m.getId());
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("Confirmation ");
      alert.setHeaderText(null);
      alert.setContentText("Vous voulez vraiment supprimer cet Event ");
      Optional<ButtonType> action = alert.showAndWait();
      if (action.get() == ButtonType.OK) {
se.supprimer(String.valueOf(m.getId()));
         viewParent();
         System.out.println(m.getId());


      }
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
            input_tel.setText(String.valueOf(data.get(0).getTel()));
            input_nom.setText(data.get(0).getNom());
                        input_prenom.setText(data.get(0).getPrenom());
            input_email.setText(data.get(0).getEmail());
            input_mdp.setText(data.get(0).getMdp());

            return id;
    }
 
 @FXML
    void saveedit(ActionEvent event) {
             Connection cnx = ConnectionBD.getInstance().getCnx();
             
         try {
            String requete = "UPDATE user SET nom=?,prenom=?,email=?,mdp=?,tel=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);

            pst.setInt(6, getid());
            pst.setString(1, input_nom.getText());
            pst.setString(2, input_prenom.getText());
            pst.setString(3, input_email.getText());
            pst.setString(4, input_mdp.getText());
            pst.setInt(5, Integer.parseInt(input_tel.getText()));
            pst.executeUpdate();
            System.out.println("Parent modified !");
            viewParent();

        } catch (SQLException ex) {
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
    void LogOut(ActionEvent event) throws IOException {
      System.out.println(" accueil ");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/gui/Dashboard.fxml"));
        Parent root = loader.load();
        container.getChildren().setAll(root);
    }    

    @FXML
    private void controlMDPP(KeyEvent event) {
    }

    
    }


    
