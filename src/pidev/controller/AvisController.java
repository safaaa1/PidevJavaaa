/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import org.controlsfx.control.Rating;
import pidev.entites.Avis;
import pidev.entites.Utilisateur;
import pidev.services.AvisService;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author safa
 */
public class AvisController implements Initializable {

    @FXML
    private AnchorPane anchorP;
    @FXML
    private Button evenement;
    @FXML
    private Pane pane;
    @FXML
    private Button btn11;
    @FXML
    private Button btn1;
    @FXML
    private AnchorPane anchorEvent;
    @FXML
    private TextArea description;
    @FXML
    private Button ajouter;
    @FXML
    private Button btn_goBack;
 @FXML
    private Rating rate;
    @FXML
    private Label msg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          System.out.println(rate.getRating());
rate.setRating(3);
        rate.ratingProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            msg.setText("Rating :- "+newValue);
        });
             System.out.println(rate.getRating());
       
    }    

    @FXML
    private void dashboard(ActionEvent event) throws IOException {
         System.out.println("afficher mes events");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/gui/Dashboard1.fxml"));
        Parent root = loader.load();
        anchorP.getChildren().setAll(root);
    }

    @FXML
    private void ListerEvent(ActionEvent event) throws IOException {
         System.out.println("afficher mes events");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/gui/EventFront.fxml"));
        Parent root = loader.load();
        anchorP.getChildren().setAll(root);
    }


    

    @FXML
    private void LogOut(ActionEvent event) throws IOException {
         System.out.println("afficher mes events");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/gui/seConnecter.fxml"));
        Parent root = loader.load();
        anchorP.getChildren().setAll(root);
        
    }

    @FXML
    private void hidePane(MouseEvent event) {
  
    }
    
    
    
    
    
    
    Utilisateur utilisateur=new Utilisateur();

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }
Integer idEvent;

    public Integer getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(Integer idEvent) {
        this.idEvent = idEvent;
    }
    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
    @FXML
    public void ajouter(ActionEvent event) throws SQLException{
        Avis p=new Avis();
        
        if(!description.getText().equals("")){
            AvisService se = new AvisService();
                System.out.println(description.getText()+ idEvent+"+"+ utilisateur.getId()+rate.getRating());

           // DossierMedical dm =cbdm.getSelectionModel().getSelectedItem();
      // se.ajouterAvis(new Avis(description.getText(),));
             se.ajouterAvis(description.getText(),idEvent,utilisateur,rate.getRating());

       
           // alert.setHeaderText("Look, an Information Dialog");
           TrayNotification tray = new TrayNotification("Successfully",
                                            "Merci ,votre avis est pris en consideration ", NotificationType.SUCCESS);
                                    tray.setAnimationType(AnimationType.SLIDE);
                                    tray.showAndDismiss(Duration.seconds(10));
                                   
               description.setText("");
               

        }else{
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Warning");
        //alert.setHeaderText("Look, a Warning Dialog");
        alert.setContentText("verifier vos parametres ");
        alert.showAndWait();
        }

    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
