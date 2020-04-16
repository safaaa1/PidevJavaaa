/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import pidev.entites.Evenement;
import pidev.entites.Utilisateur;
import pidev.services.EvenementService;
import pidev.services.UtilisateurService;
import pidev.utils.ConnectionBD;
import pidev.utils.Session;

/**
 * FXML Controller class
 *
 * @author safa
 */
public class ModifierEventController implements Initializable {

    @FXML
    private AnchorPane anchorP;
    @FXML
    private Button evenement;
    @FXML
    private Button userName;
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
    private TextField TypeEvent;
    @FXML
    private Button imageEvent;
    @FXML
    private TextField nbrPlace;
    @FXML
    private TextField dressCode;
    @FXML
    private ImageView iv;
    @FXML
    private Button saveedit;

    int eventid=0;
  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
           
    }
        // TODO
    
    

 public String handle() {
        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

        //Show open file dialog
        File file = fileChooser.showOpenDialog(null);
        String filePath = file.getAbsolutePath();
        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            iv.setImage(image);

        } catch (IOException ex) {
            System.err.println(ex);
        }

        return filePath;
    }
 
               

    public void remplirchamp(Evenement e){
        nomEvent.setText(e.getNom());
        TypeEvent.setText(e.getType());
        dateEvent.setValue(LOCAL_DATE(e.getDate().toString()));
        nbrPlace.setText(Integer.toString(e.getNbrPlace()));
        dressCode.setText(e.getDressCode());
        imageEvent.setText(e.getImage());
        eventid = e.getIdEvent();
        
    }
 
    private void uploadImage(ActionEvent event) {
        imageEvent.setText(handle());

    }

    @FXML
    private void chooseAction(MouseEvent event) {
    }

    
   public static final LocalDate LOCAL_DATE (String dateString){
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate localDate = LocalDate.parse(dateString, formatter);
    return localDate;
}
    
    
    
    /*@FXML
    private void modEvent(ActionEvent event) {
          Connection cnx = ConnectionBD.getInstance().getCnx();
             
         try {
  java.util.Date dateeve = new Date(evenement.getDate().getTime());

            String requete = "UPDATE user SET nom=?,prenom=?,email=?,mdp=?,tel=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);

           // pst.setInt(7, getid());
            pst.setString(1, nomEvent.getText());
            pst.setString(2, TypeEvent.getText());
           // pst.setDate(3, DatePicker(dateEvent.getText()));

            pst.setInt(4, Integer.parseInt(nbrPlace.getText()));
            pst.setString(5, dressCode.getText());
            pst.setString(6, imageEvent.getText());
            pst.executeUpdate();
            System.out.println("Enfant modifiée !");
            //GetAllEvent();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } 
    }*/

    @FXML
    private void hidePane(MouseEvent event) {
    }
   
    
    
     
    
@FXML
    void saveedit(ActionEvent event) throws IOException {
          Connection cnx = ConnectionBD.getInstance().getCnx();
             
         try {
            String requete = "UPDATE evenement SET nom=?,type=?,date=?,nbrPlace=?,dressCode=?,image=? WHERE id_event=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            LocalDate d = dateEvent.getValue();
            java.util.Date dated = java.util.Date.from(d.atStartOfDay(ZoneId.systemDefault()).toInstant());

                    Date da = Date.valueOf(dateEvent.getValue().toString());

            
            
            pst.setString(1, nomEvent.getText());
            pst.setString(2, TypeEvent.getText());
            pst.setDate(3, da);
            pst.setInt(4, Integer.parseInt(nbrPlace.getText()));
            pst.setString(5, dressCode.getText());
            pst.setString(6, imageEvent.getText());
            pst.setInt(7,eventid);

            pst.executeUpdate();
            System.out.println("Event modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } 
          FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/pidev/gui/AfficheEvenement.fxml"));
                    Parent root = (Parent) fxmlLoader.load();
                Stage secondStage = new Stage();
                secondStage.setScene(new Scene(root));
                  secondStage.show();
         
    }


       
    
    
}
