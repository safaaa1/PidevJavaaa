
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.mail.MessagingException;
import pidev.entites.Evenement;
import pidev.services.EvenementService;
import pidev.utils.Mail;
import javax.mail.MessagingException;


/**
 *
 * @author safa
 */
public class AccueilController {
ObservableList<String>  listt=FXCollections.observableArrayList("fun","sport","bricolage","peinture","dance");
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
    private TextField nomEvent;
    @FXML
    private DatePicker dateEvent;
    @FXML
    private ChoiceBox TypeEvent;
    @FXML
    private Button imageEvent;
    @FXML
    private TextField nbrPlace;
    @FXML
    private TextField dressCode;
    @FXML
    private Button ajouterEvent;
    @FXML
    private ImageView iv;
    @FXML
    private Button button;
    @FXML
    private Button annonceBtn;
    @FXML
    private Button btn_goBack;
    @FXML
    private TextField TypeEventt;

    @FXML
    private void initialize(){
            TypeEvent.setValue("loisir");

       TypeEvent.setItems(listt); 
    
    }
    
     public String handle(){
        FileChooser fileChooser = new FileChooser();//Set extension filter
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

        //Show open file dialog
        File file = fileChooser.showOpenDialog(null);
        String filePath = file.getName();
        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            iv.setImage(image);

        } catch (IOException ex) {
            System.err.println(ex);
        }

        return filePath;
    }  
     
     

    @FXML
    private void hidePane(MouseEvent event) {
    }

    @FXML
    private void chooseAction(MouseEvent event) {
        imageEvent.setText(handle());
    }

    @FXML
    private void CreateEvent(ActionEvent event) throws IOException, SQLException {
       
       /* listt.removeAll(listt);
        String a="loisir";
         String b="sport";
          String c="theatre";
           String cc="bricolage";
           String ccc="peinture";
           String cccc="dance";
           listt.addAll(a,b,c,cc,ccc,cccc);
         TypeEvent.getItems().addAll(listt);
        
        */
        
        
        
        
        
        
        EvenementService se= new EvenementService();
           LocalDate d = dateEvent.getValue();
         Date dated = Date.from(d.atStartOfDay(ZoneId.systemDefault()).toInstant());

        String nbP =  nbrPlace.getText().toString();
        int i=0;
        
      
         Date date = new Date();
         if(dated.compareTo(date) <= 0 ||(dated.equals("")))  
       {
           i++;
       Alert alert = new Alert(AlertType.WARNING, "Date invalide: Un évenement doit être créer avant au moins d'un jour", ButtonType.OK);
        alert.showAndWait();}
        
      
       
        if (TypeEvent.getValue().toString().isEmpty()) {
            Alert alert = new Alert(AlertType.WARNING, "Veuillez Saisir un type", ButtonType.OK);
        alert.showAndWait();
             i++;
        }
        if (dressCode.getText().isEmpty()) {
            Alert alert = new Alert(AlertType.WARNING, "Veuillez Saisir un code vestimentaire", ButtonType.OK);
        alert.showAndWait();
             i++;
        }
        if (nomEvent.getText().isEmpty()) {
             Alert alert = new Alert(AlertType.WARNING, "Veuillez Saisir un nom", ButtonType.OK);
        alert.showAndWait();
             i++;
        }

      if(i==0)
      { 
       Evenement e=new Evenement(nomEvent.getText(),(String)TypeEvent.getValue(),dated,Integer.parseInt(nbrPlace.getText()),dressCode.getText(),imageEvent.getText());
           
           se.add(e);
            

         System.out.println("evenement ajouté");
         
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/pidev/gui/AfficheEvenement.fxml"));
                    Parent root = (Parent) fxmlLoader.load();
                Stage secondStage = new Stage();
                secondStage.setScene(new Scene(root));
                  secondStage.show();}  
       }

    @FXML
    private void dashboard(ActionEvent event) throws IOException {
    
      System.out.println(" Accueil ");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/gui/Dashboard.fxml"));
        Parent root = loader.load();
        anchorP.getChildren().setAll(root);
    }
    
     @FXML
    void LogOut(ActionEvent event) throws IOException {
        System.out.println(" LogOut ");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/gui/seConnecter.fxml"));
        Parent root = loader.load();
        anchorP.getChildren().setAll(root);
    
    }

    
    
    @FXML
    void GoToReserv(ActionEvent event) throws IOException {
       System.out.println(" Afficher les reservations ");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/gui/AfficherCommande.fxml"));
        Parent root = loader.load();
        anchorP.getChildren().setAll(root);
    }
     @FXML
    void goToParents(ActionEvent event) throws IOException {
        System.out.println(" Afficher les parents ");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/gui/GestionParent.fxml"));
        Parent root = loader.load();
        anchorP.getChildren().setAll(root);
    }
    
    @FXML
    void ListerEvent(ActionEvent event) throws IOException {
     System.out.println(" Afficher les evenements ");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/gui/AfficheEvenement.fxml"));
        Parent root = loader.load();
        anchorP.getChildren().setAll(root);
    }

    }
    
