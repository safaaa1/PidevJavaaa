/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package pidev.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.imageio.ImageIO;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import javafx.util.Duration;
import pidev.entites.Evenement;
import pidev.entites.Parentt;
import pidev.entites.Reservation;
import pidev.entites.Utilisateur;
import pidev.services.EvenementService;
import pidev.utils.ConnectionBD;
import pidev.services.ReservationService;
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
public class EventFrontController implements Initializable {
    
    @FXML
    private Button parc;
    @FXML
    private Button btn_goBack;
    @FXML
    private Button userName;
    @FXML
    private AnchorPane containerEvent;
    @FXML
    private AnchorPane containerE;
    @FXML
    private TableView<Evenement> tableSafa;
    @FXML
    private TableColumn<Evenement, Integer> Id;
    @FXML
    private TableColumn<Evenement, String> nom;
    @FXML
    private TableColumn<Evenement, String> type;
    @FXML
    private TableColumn<Evenement, Date> date;
    @FXML
    private TableColumn<Evenement, Integer> nbrPlace;
    @FXML
    private TableColumn<Evenement, String> dressCode;
    @FXML
    private TableColumn<Evenement, String> image;
    @FXML
    private TextField mot;
    int a;
    @FXML
    private TableColumn reserverEvent;
    public ObservableList<Evenement> data=FXCollections.observableArrayList();
    private ObservableList<Evenement> list;
    @FXML
    private TableColumn avis;
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            afficherEvenements();
            
            // TODO
        } catch (IOException ex) {
            Logger.getLogger(EventFrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            
        } catch (IOException ex) {
            System.err.println(ex);
        }
        
        return filePath;
    }
    @FXML
    private void afficherAccueil(ActionEvent event) throws IOException{
        System.out.println(" Accueil ");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/gui/Dashboard.fxml"));
        Parent root = loader.load();
        containerEvent.getChildren().setAll(root);
    }
    
    @FXML
    private void AfficherParc(ActionEvent event) {
    }
    
    @FXML
    private void GoBack(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/gui/seConnecter.fxml"));
        Parent root = loader.load();
        containerEvent.getChildren().setAll(root);
    }
    
    @FXML
    private void showPaneProfil(MouseEvent event) {
    }
    
    @FXML
    private void chercher(KeyEvent event) {
    }
    
    @FXML
    private void hidePane(MouseEvent event) {
    }
    Utilisateur utilisateur;
    
    public Utilisateur getUtilisateur() {
        return utilisateur;
    }
    
    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
    
    
    public void afficherEvenements() throws IOException{
        /* EvenementService se = new EvenementService();
        tableSafa.setItems((ObservableList<Evenement>) se.getAllEvent());
        Id.setCellValueFactory(new PropertyValueFactory<Evenement,Integer>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<Evenement,String>("nom"));
        type.setCellValueFactory(new PropertyValueFactory<Evenement,String>("type"));
        
        date.setCellValueFactory(new PropertyValueFactory<Evenement,Date>("date"));
        nbrPlace.setCellValueFactory(new PropertyValueFactory<Evenement,Integer>("nbrPlace"));
        dressCode.setCellValueFactory(new PropertyValueFactory<Evenement,String>("dressCode"));
        image.setCellValueFactory(new PropertyValueFactory<Evenement,String>("image"));
        
        }*/
        EvenementService se = new EvenementService();
        list = se.getAllEvent();
        // tableSafa.setItems((ObservableList<Evenement>) se.getAllEvent());
        
        Id.setCellValueFactory(new PropertyValueFactory<>("idEvent"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        nbrPlace.setCellValueFactory(new PropertyValueFactory<>("nbrPlace"));
        dressCode.setCellValueFactory(new PropertyValueFactory<>("dressCode"));
        image.setCellValueFactory(new PropertyValueFactory<>("image"));
        
        Callback<TableColumn<Evenement, String>, TableCell<Evenement, String>> cellFactoryReserver = (param) -> {
            
            final TableCell<Evenement, String> cell = new TableCell<Evenement, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {//if(4>9){
                        final Button test = new Button("reserver");
                        test.setOnAction(event -> {
                            Evenement p = getTableView().getItems().get(getIndex());
                            EvenementService evenementService1 = new EvenementService();
                            if(p.getNbrPlace()>0){
                                try {
                                    se.reserver(p.getIdEvent(),utilisateur);
                                    TrayNotification tray = new TrayNotification("Successfully",
                                            "Votre reservation est en cours, attendez la validation admin", NotificationType.SUCCESS);
                                    tray.setAnimationType(AnimationType.SLIDE);
                                    tray.showAndDismiss(Duration.seconds(10));
                                    try {
                                        test.setDisable(true);
                                        refresh();
                                    } catch (IOException ex) {
                                        Logger.getLogger(EventFrontController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    test.setDisable(true);
                                } catch (SQLException ex) {
                                    Logger.getLogger(EventFrontController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                System.out.println(p);
                            }else {

                                
                       alertHelper.afficher("Erreur","pas de place disponible");

                            
                            
                            
                            }
                        });
                        setGraphic(test);
                        setText(null);
                    }
                }
            };
            return cell;
        };
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        Callback<TableColumn<Evenement, String>, TableCell<Evenement, String>> cellFactoryAvis = (param) -> {
            
            final TableCell<Evenement, String> cell = new TableCell<Evenement, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {//if(4>9){
                        final Button test = new Button("avis");
                        test.setOnAction(event -> {
                           try {
                                Evenement p = getTableView().getItems().get(getIndex());
                                // selectionner();
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/gui/Avis.fxml"));
                                Parent root = loader.load();
                                AvisController controller =(AvisController) loader.getController();
                                    controller.setUtilisateur(utilisateur);
                                    controller.setIdEvent(p.getIdEvent());
                                containerE.getChildren().setAll(root);
                               // ModifierEventController modifierEventController=new ModifierEventController(p);
                                // se.modifier(p);
                                // try {
                                //   refresh();
                                // } catch (IOException ex) {
                                //Logger.getLogger(ModifierEventController.class.getName()).log(Level.SEVERE, null, ex);
                                //  }
                            } catch (IOException ex) {
                                Logger.getLogger(EventFrontController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                                //AvisController controller =(AvisController) loader.getController();
                                   // controller.remplirchamp(p);
                              //  containerE.getChildren().setAll(root);
                              
                        });
                        setGraphic(test);
                        setText(null);
                    }
                }
            };
            return cell;
        };
         
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        avis.setCellFactory(cellFactoryAvis);
        reserverEvent.setCellFactory(cellFactoryReserver);
        tableSafa.setItems(list);
        
        
        
        
        
        
    }
    
    public void refresh() throws IOException {
        System.out.println("afficher mes events");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/gui/EventFront.fxml"));
        Parent root = loader.load();
        containerE.getChildren().setAll(root);
    }
    
    
    
    
    
    
    
}
