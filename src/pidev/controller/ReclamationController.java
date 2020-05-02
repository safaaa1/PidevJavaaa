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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
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
    private Label numTelTest;
    @FXML
    private Button save;
    @FXML
    private Button reset;
     boolean verificationNumTel=false;
    @FXML
    private Button viewEnfant;
    private ComboBox<Reponse> colrep;
           public ObservableList<Reclamations> data=FXCollections.observableArrayList();
       public ObservableList<Reponse> data2=FXCollections.observableArrayList();
    @FXML
    private Button viewEnfant1;
    @FXML
    private AnchorPane sakhta;

   
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
    private void insertReclamation(ActionEvent event) throws SQLException {
       //  if(!nomtxt.getText().isEmpty()){
        if(!nomtxt.getText().equals("")&&!titretxt.getText().equals("")&&!emailtxt.getText().equals("")&&!teltxt.getText().equals("")&&!desctxt.getText().equals("")){
            
            ReclamationsService se = new ReclamationsService();
                if((isValid(emailtxt.getText()))&&(!checkEmail(emailtxt.getText()))){
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
        alert.setContentText("verifier votre email ");
        alert.showAndWait();
            
            }

        }else{
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        //alert.setHeaderText("Look, a Warning Dialog");
        alert.setContentText("verifier vos parametres ");
        alert.showAndWait();
        }

        
      

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
  public static boolean isValid(String email) 
    { 
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                            "[a-zA-Z0-9_+&*-]+)*@" + 
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                            "A-Z]{2,7}$"; 
        Pattern pat = Pattern.compile(emailRegex); 
        if (email == null) 
            return false; 
        return pat.matcher(email).matches(); 
    }
      public static boolean checkEmail(String email) throws SQLException{
        boolean memberIdExists = false;

         Connection cnx = ConnectionBD.getInstance().getCnx();
        Statement stm = cnx.createStatement();
        ResultSet rst = stm.executeQuery("SELECT * FROM enseignant WHERE email='"+email+"'");

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
    private void logout(ActionEvent event) throws IOException {
            System.out.println("afficher mes events");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/gui/seConnecter.fxml"));
        Parent root = loader.load();
        sakhta.getChildren().setAll(root);
    }

    @FXML
    private void Retour(ActionEvent event) throws IOException {
          System.out.println("afficher mes events");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/gui/Dashboard1.fxml"));
        Parent root = loader.load();
        sakhta.getChildren().setAll(root);
        
    }

    
}

