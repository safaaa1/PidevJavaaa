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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;
import static pidev.controller.MedecinController.isValid;
import pidev.entites.Medecin;
import pidev.services.ServiceMedecin;
import pidev.utils.ConnectionBD;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 *
 * @author yanisinfo
 */
public class MedecinController implements Initializable {

    @FXML
    private Button logout;
    @FXML
    private MenuItem supprimer;
    @FXML
    private MenuItem modifier;
    @FXML
    private BorderPane sakhta;
    @FXML
    private Button logout1;

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
    private TextField recherchetxt;
      @FXML
    private TableView<Medecin> table;


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
//    colid.setCellValueFactory(new PropertyValueFactory<Medecin,Integer>("idMedecin"));
    colnom.setCellValueFactory(new PropertyValueFactory<Medecin,String>("nom"));
    colprenom.setCellValueFactory(new PropertyValueFactory<Medecin,String>("prenom"));
    coltel.setCellValueFactory(new PropertyValueFactory<Medecin,Integer>("tel"));
    colemail.setCellValueFactory(new PropertyValueFactory<Medecin,String>("email"));
    }
        
    @FXML
    private void logout(ActionEvent event) throws IOException {
        System.out.println(" Retour ");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/gui/seConnecter.fxml"));
        Parent root = loader.load();
        sakhta.getChildren().setAll(root);
    }  
        
         public static boolean isValid(String email)
{
  
	if( email!=null && email.trim().length()>0 )
	return email.matches("^[a-zA-Z0-9\\.\\-\\_]+@([a-zA-Z0-9\\-\\_\\.]+\\.)+([a-zA-Z]{2,4})$");
	return false;
}  
         
    public static boolean isNumber(String num)
{
  
	if( num!=null && num.trim().length()>0 )
	return num.matches("^[0-9]{8}$");
	return false;
}
   /* public static boolean isValid(String email) 
    { 
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                            "[a-zA-Z0-9_+&*-]+)*@" + 
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                            "A-Z]{2,7}$"; 
        Pattern pat = Pattern.compile(emailRegex); 
        if (email == null) 
            return false; 
        return pat.matcher(email).matches(); 
    }*/
     
         
         public static boolean checkEmail(String email) throws SQLException{
        boolean memberIdExists = false;

         Connection cnx = ConnectionBD.getInstance().getCnx();
        Statement stm = cnx.createStatement();
        ResultSet rst = stm.executeQuery("SELECT * FROM medcin WHERE email='"+email+"'");

        String id;
        if (rst.next()){
            id = rst.getString("email");
            if(id.equals(email)){
                memberIdExists = true;
            }
        }
        return memberIdExists;
    }
         
         
         
    @FXML
    public void insertMedecin(ActionEvent event) throws SQLException{
       
    if(!nomtxt.getText().equals("")&&!prenomtxt.getText().equals("")&&!nomtxt.getText().equals("")&&!prenomtxt.getText().equals("")){
      // Medecin m = new Medecin();
        if (isValid(emailtxt.getText())&&(!checkEmail(emailtxt.getText()))){
         if(isNumber(teltxt.getText())){
        ServiceMedecin se = new ServiceMedecin();
      //  DossierMedical dm =cbdm.getSelectionModel().getSelectedItem();
    se.ajouter(new Medecin(nomtxt.getText(),Integer.parseInt(teltxt.getText()),prenomtxt.getText(),emailtxt.getText()));

   TrayNotification tray = new TrayNotification("Done","le Medecin est ajouté ", NotificationType.INFORMATION);
          tray.setAnimationType(AnimationType.SLIDE);
          tray.showAndDismiss(Duration.seconds(5));
           nomtxt.setText("");
           prenomtxt.setText("");
           emailtxt.setText("");
           teltxt.setText("");
           viewMedecin();
           clearFields();
         
           }else{
            Alert alert = new Alert(AlertType.WARNING);
    alert.setTitle("Warning");
    //alert.setHeaderText("Look, a Warning Dialog");
    alert.setContentText("verifier votre numero de telephone ");
    alert.showAndWait();
        }
        }else{
            Alert alert = new Alert(AlertType.WARNING);
    alert.setTitle("Warning");
    //alert.setHeaderText("Look, a Warning Dialog");
    alert.setContentText("verifier votre email ");
    alert.showAndWait();
        }

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
            clearFields();

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
       /* verificationNumTel = false;
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
    */}
    private void clearFields(){
        nomtxt.clear();
        prenomtxt.clear();
        teltxt.clear();
        emailtxt.clear();
    }
    void reset(ActionEvent event) {
    	clearFields();
    }

    @FXML
    private void Retour(ActionEvent event) throws IOException {
        System.out.println(" Retour ");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/gui/Anis.fxml"));
        Parent root = loader.load();
        sakhta.getChildren().setAll(root);
   
    }
}
