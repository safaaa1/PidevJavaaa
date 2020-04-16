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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javax.mail.MessagingException;
import pidev.entites.Utilisateur;
import pidev.services.UtilisateurService;
import pidev.utils.Mail;

/**
 *
 * @author safa
 */
public class SeConnecterGuestController implements Initializable {

    @FXML
    private TextField nomUtilisateur;
    
    
    @FXML
    private Button seConnecter;
    
     @FXML
    private BorderPane border;

    public static int idUtilisateur;
    
    @FXML
    private Button mod;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println("se connecter controller");
    }

    @FXML
    public void connecter(ActionEvent actionEvent) throws SQLException, IOException {
        System.out.println("se connecter action");

        UtilisateurService utilisateurService = new UtilisateurService();
        Utilisateur utilisateur = new Utilisateur();

        utilisateur = utilisateurService.seConnecter1(nomUtilisateur.getText());
        /*String responsable= "a:1:{i:0;s:16:\\\"ROLE_RESPONSABLE\\\";}";
        String testRole = utilisateur.getRoles();
        Password md = new Password();
        Boolean mdpCrypte = md.checkPassword(mdp.getText(), utilisateur.getPassword());

        if (mdpCrypte == true) {
            System.out.println("authentification reussite");
            
            idUtilisateur = utilisateur.getId();
            if (utilisateur.getRoles().equals("a:1:{i:0;s:11:\"ROLE_CLIENT\";}")) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/gui/panelParent.fxml"));
                Parent root = loader.load();
                PanelParentController panelClientController = loader.getController();
                mdp.getScene().setRoot(root);
            }else if(testRole.equals(responsable)){*/
               FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/gui/accueilContine.fxml"));
                Parent root = loader.load();
            AccueilContineController AccueilContinetController = loader.getController();
            nomUtilisateur.getScene().setRoot(root);
                /*secondStage.setScene(new Scene(root));
               Stage stage = (Stage) mdp.getScene().getWindow();
                stage.hide();
                  secondStage.show();}  
            
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("veuillez verifier votre login ou mot de passe ..");
            alert.show();
            System.out.println("veuillez verifier votre login ou mot de passe ..");
        }*/

    }
    //ddddaaa

    @FXML
    public void sinscrire(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/gui/sinscrireGuest.fxml"));
        Parent root = loader.load();
        SinscrireGuestController sinscrireController = loader.getController();
        nomUtilisateur.getScene().setRoot(root);
    }
    
    
    
    @FXML
    public void SendMailCC(ActionEvent event) throws  IOException, MessagingException{
        if(!nomUtilisateur.getText().equals("")){
            
               Mail.sendMailC("moetaz23@gmail.com");
               /*FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/gui/ValiderCode.fxml"));
               
               //ResourceBundle resources = ResourceBundle.getBundle("Language/lang_pt");
               Parent root = loader.load();
               //Parent root = FXMLLoader.load(getClass().getResource("ValiderCode.fxml"),resources);
               ValiderCodeController ValiderCodeController = loader.getController();
               */
                     FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/gui/ValiderCode.fxml"));
                     Parent root = loader.load();
                     ValiderCodeController ValiderCodeController = loader.getController();
                     border.getScene().setRoot(root);
        }else{
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        //alert.setHeaderText("Look, a Warning Dialog");
        alert.setContentText("verifier vos parametres ");
        alert.showAndWait();
        }

    }
    

}
