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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import javafx.util.Duration;
import javax.mail.MessagingException;
import pidev.entites.Evenement;
import pidev.entites.Reservation;
import pidev.entites.ReservationUtilisateur;
import pidev.entites.Utilisateur;
import pidev.services.EvenementService;
import pidev.services.ReservationService;
import pidev.utils.Mail;
import pidev.utils.Session;
import pidev.utils.alertHelper;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author safa
 */
public class ReservationController implements Initializable {
    private ObservableList<ReservationUtilisateur> list;

    @FXML
    private AnchorPane bigAnchor;
    @FXML
    private AnchorPane sidePane;
    @FXML
    private Button button;
    @FXML
    private Button Petsitter;
    @FXML
    private Button buttonRefuge;
    @FXML
    private Button evenement;
    @FXML
    private Button annonceBtn;
    @FXML
    private Button parc;
    @FXML
    private Button userName;
    @FXML
    private Pane paneProfil;
    @FXML
    private Button btn11;
    @FXML
    private Button btn1;
    @FXML
    private AnchorPane anchorEvent;
    @FXML
    private TableView<ReservationUtilisateur> TableViewCommande;
    @FXML
    private TableColumn<ReservationUtilisateur, String> etat;
    @FXML
    private TableColumn valider;
    @FXML
    private TableColumn<ReservationUtilisateur, Integer> idevent;
    @FXML
    private TableColumn<ReservationUtilisateur, String> nomUser;
    @FXML
    private TableColumn annuler;
    
    @FXML
    private TableColumn<ReservationUtilisateur, String> prenomUser;
    public static  String DEST = "file:///C:/simple_table6.pdf";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try { 
            afficherReservations();
// TODO
        } catch (IOException ex) {
            Logger.getLogger(ReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void afficherAccueil(ActionEvent event) throws IOException {
        System.out.println(" Accueil ");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/gui/Dashboard.fxml"));
        Parent root = loader.load();
        anchorEvent.getChildren().setAll(root);
    
    }

    @FXML
    private void MagasinButtonAction(ActionEvent event) throws IOException {
        System.out.println(" Reservations ");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/gui/AfficherCommande.fxml"));
        Parent root = loader.load();
        anchorEvent.getChildren().setAll(root);
    
    }

    @FXML
    private void goToVet(ActionEvent event) throws IOException {
        System.out.println(" liste parents ");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/gui/GestionParent.fxml"));
        Parent root = loader.load();
        anchorEvent.getChildren().setAll(root);
    
    }
    @FXML
    void LogOut(ActionEvent event) throws IOException {
        System.out.println(" LogOut ");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/gui/seConnecter.fxml"));
        Parent root = loader.load();
        anchorEvent.getChildren().setAll(root);
    
    }
    
Utilisateur utilisateur;

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
   

    
        public ObservableList<ReservationUtilisateur> data=FXCollections.observableArrayList();

    @FXML
    private void onClickEvenementAction(ActionEvent event) {
    }

   

    @FXML
    private void Afficher(ActionEvent event) throws IOException {
        System.out.println(" liste events ");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/gui/AfficheEvenement.fxml"));
        Parent root = loader.load();
        anchorEvent.getChildren().setAll(root);
    
    }

    @FXML
    private void showPaneProfil(MouseEvent event) {
    }

    @FXML
    private void connexionAction(ActionEvent event) {
    }

    @FXML
    private void hidePaneProfil(MouseEvent event) {
    }
   
    
    
    
    
    
    
    
            public void afficherReservations()throws IOException{

    
    ReservationService se = new ReservationService();
               list = se.getAll();
           // tableSafa.setItems((ObservableList<Evenement>) se.getAllEvent());
         
        idevent.setCellValueFactory(new PropertyValueFactory<>("event"));
                 nomUser.setCellValueFactory(new PropertyValueFactory<>("nomUser"));
                prenomUser.setCellValueFactory(new PropertyValueFactory<>("prenomUser"));

         etat.setCellValueFactory(new PropertyValueFactory<>("etat"));

       Callback<TableColumn<ReservationUtilisateur, String>, TableCell<ReservationUtilisateur, String>> cellFactoryRemove = (param) -> {

            final TableCell<ReservationUtilisateur, String> cell = new TableCell<ReservationUtilisateur, String>() {
               @Override
                public void updateItem(String item, boolean empty) {

                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {//if(4>9){
                        final Button test = new Button("valider");
                        test.setOnAction(event -> {
                    System.out.println(getTableView().getItems().get(getIndex()).getNomUser());
                              
                            ReservationUtilisateur p = getTableView().getItems().get(getIndex());
                          ReservationService evenementService1 = new ReservationService();
                              System.out.println(p.getIdReser());

                            se.valider(p.getIdReser());
                            
                            try {
                                refresh();
                     System.out.println("envoi mail vers "+p.getEmail());

             Mail.sendMail(p.getEmail());

                            } catch (IOException ex) {
                                Logger.getLogger(ReservationController.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (MessagingException ex) {
                                Logger.getLogger(ReservationController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            System.out.println(p);
                        });

                        setGraphic(test);
                        setText(null);
                        //}

                    }
                }
            };
            return cell;
        };
         Callback<TableColumn<ReservationUtilisateur, String>, TableCell<ReservationUtilisateur, String>> cellFactoryDec = (param) -> {

            final TableCell<ReservationUtilisateur, String> cell = new TableCell<ReservationUtilisateur, String>() {
               @Override
                public void updateItem(String item, boolean empty) {

                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {//if(4>9){
                        final Button test = new Button("Annuler");
                        test.setOnAction((ActionEvent event) -> {
                            ReservationUtilisateur p = getTableView().getItems().get(getIndex());
                            EvenementService evenementService1 = new EvenementService();
               
                            try {
                                se.annuler(p.getIdReser(),utilisateur);
                                TrayNotification tray = new TrayNotification("Done",
                                            "la reservation est annulee ", NotificationType.WARNING);
                                    tray.setAnimationType(AnimationType.SLIDE);
                                    tray.showAndDismiss(Duration.seconds(10));
                    // alertHelper.afficher("Erreur", "Choisir une demande");
                           //SendMail.send(to,subject, message,DEST, user, pass);

                                try {
                                    refresh();
                                } catch (IOException ex) {
                                    Logger.getLogger(ReservationController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                           
                                try {
                                    Mail.sendMail(utilisateur.getEmail());
                                } catch (MessagingException ex) {
                                    Logger.getLogger(ReservationController.class.getName()).log(Level.SEVERE, null, ex);
                                }
           
     
                            } catch (SQLException ex) {
                                Logger.getLogger(EventFrontController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                           
                            System.out.println(p);
                        });

                        setGraphic(test);
                        setText(null);

                    }
                }
            };
            return cell;
        };
                     
       
       
       
       
       
       valider.setCellFactory(cellFactoryRemove);
                  TableViewCommande.setItems(list);

           annuler.setCellFactory(cellFactoryDec);

            }
    
    
    
    public void refresh() throws IOException {
        System.out.println("afficher mes events");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/gui/AfficherCommande.fxml"));
        Parent root = loader.load();
        anchorEvent.getChildren().setAll(root);
    }
   
    
    
    
    
}
