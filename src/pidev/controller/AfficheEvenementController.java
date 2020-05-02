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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import pidev.entites.Evenement;
import pidev.services.EvenementService;
import pidev.utils.ConnectionBD;

/**
 * FXML Controller class
 *
 * @author safa
 */
public class AfficheEvenementController implements Initializable {

        public ObservableList<Evenement> data=FXCollections.observableArrayList();

    @FXML
    private TableColumn<Evenement, String> nom;
    @FXML
    private TableColumn<Evenement, String> type;
    @FXML
    private TableColumn<Evenement, String> dressCode;
    @FXML
    private TableColumn<Evenement, String> image;
     @FXML
    private TableColumn<Evenement, Integer> id;
   
    
    @FXML
    private TableView<Evenement> tableSafa;
    @FXML
    private TableColumn<Evenement, Date> date;
    @FXML
    private TableColumn<Evenement, Integer> nbrPlace;
    @FXML
    private AnchorPane containerEvent;
    @FXML
    private TableColumn supprimerEvent;
    private ObservableList<Evenement> list;
    @FXML
    private TextField mot;
    @FXML
    private TableColumn modifierEvent;

    @FXML
    private Button parc;
    @FXML
    private Button parc1;
    @FXML
    private Button button;
    @FXML
    private Button userName;
    @FXML
    private Button annonceBtn;
    @FXML
    private Button btn_goBack;
    @FXML
    private AnchorPane conatiner_client;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            afficherEvenements();
            // TODO
        } catch (IOException ex) {
            Logger.getLogger(AfficheEvenementController.class.getName()).log(Level.SEVERE, null, ex);
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
     
    private void afficherAccueil(ActionEvent event) throws IOException{
        System.out.println(" Accueil ");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/gui/Dashboard.fxml"));
        Parent root = loader.load();
        containerEvent.getChildren().setAll(root);
    }
  
  
    public void getAllEvent(ActionEvent actionEvent) throws IOException {
        System.out.println("afficher liste des evenements");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/gui/afficheEvenement.fxml"));
        Parent root = loader.load();
    }
    
    
        public void afficherEvenements()throws IOException{
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
         
        id.setCellValueFactory(new PropertyValueFactory<>("idEvent"));
         nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
         type.setCellValueFactory(new PropertyValueFactory<>("type"));
         date.setCellValueFactory(new PropertyValueFactory<>("date")); 
         nbrPlace.setCellValueFactory(new PropertyValueFactory<>("nbrPlace")); 
         dressCode.setCellValueFactory(new PropertyValueFactory<>("dressCode"));
                  image.setCellValueFactory(new PropertyValueFactory<>("image"));
   Callback<TableColumn<Evenement, String>, TableCell<Evenement, String>> cellFactoryRemove = (param) -> {

            final TableCell<Evenement, String> cell = new TableCell<Evenement, String>() {

                @Override
                public void updateItem(String item, boolean empty) {

                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                        
                    } else {
                        final Button test = new Button("Supprimer");
                        test.setOnAction(event -> {
                            Evenement p = getTableView().getItems().get(getIndex());

                            se.remove(p.getIdEvent());
                          try {
                                refresh();
                            } catch (IOException ex) {
                                Logger.getLogger(AfficheEvenementController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            System.out.println(p);
                        System.out.println("supprime");

                                  });
                        setGraphic(test);
                        setText(null);} }};
            return cell;
        };
        
      
        
               
        Callback<TableColumn<Evenement, String>, TableCell<Evenement, String>> cellFactoryModifier = (param) -> {

            final TableCell<Evenement, String> cell = new TableCell<Evenement, String>() {

                @Override
                public void updateItem(String item, boolean empty) {

                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {//if(4>9){
                        final Button test = new Button("Modifier");
                        test.setOnAction(event -> {
                            try {
                                Evenement p = getTableView().getItems().get(getIndex());
                                // selectionner();
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/gui/modifierEvent.fxml"));
                                Parent root = loader.load();
                                ModifierEventController controller =(ModifierEventController) loader.getController();
                                    controller.remplirchamp(p);
                                containerEvent.getChildren().setAll(root);
                               // ModifierEventController modifierEventController=new ModifierEventController(p);
                                // se.modifier(p);
                                // try {
                                //   refresh();
                                // } catch (IOException ex) {
                                //Logger.getLogger(ModifierEventController.class.getName()).log(Level.SEVERE, null, ex);
                                //  }
                            } catch (IOException ex) {
                                Logger.getLogger(AfficheEvenementController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });

                        setGraphic(test);
                        setText(null);
                        //}

                    }}

            };
            return cell;
        };
        
               supprimerEvent.setCellFactory(cellFactoryRemove);
        modifierEvent.setCellFactory(cellFactoryModifier);
         tableSafa.setItems(list);
        
    }


       

    public void refresh() throws IOException {
        System.out.println("new aafichage");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/gui/AfficheEvenement.fxml"));
        Parent root = loader.load();
        containerEvent.getChildren().setAll(root);
    }
    
    
    
        @FXML
        public void filter(){
     EvenementService se = new EvenementService();
         data = FXCollections.observableArrayList(se.getAllEvent());
            FilteredList<Evenement> filterdata = new FilteredList<>(data, t -> true);
            mot.setOnKeyReleased(t -> {
                mot.textProperty().addListener((observableValue, oldValue, newValue) -> {
                    filterdata.setPredicate((Predicate<? super Evenement>) type -> {
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }
                        if((type.getNom().contains(newValue)) || (type.getNom().toLowerCase().contains(newValue) )||
                                ((type.getType()+"").contains(newValue)))
                                 {
                            return true;
                        }
                        return false;
                    });
                });
                SortedList<Evenement> sorteddata = new SortedList<>(filterdata);
                data = sorteddata;  
                tableSafa.setItems(data);
       
  
            });
    }
    
    
     

    private void chercher(KeyEvent event) {
        System.out.println(mot.getText());
        EvenementService evenementService = new  EvenementService();
        list = evenementService.chercher(mot.getText());
        for ( Evenement evenement : list) {
            System.out.println(evenement);
        }
        
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
       date.setCellValueFactory(new PropertyValueFactory<>("date"));
        nbrPlace.setCellValueFactory(new PropertyValueFactory<>("nbrPlace"));
        dressCode.setCellValueFactory(new PropertyValueFactory<>("dressCode"));
        image.setCellValueFactory(new PropertyValueFactory<>("image"));
        tableSafa.setItems(list);
    }   
   
    void supprimer(ActionEvent event) throws IOException {
    Evenement m = tableSafa.getSelectionModel().getSelectedItem();

    if (m == null) {
    Alert alert = new Alert(AlertType.WARNING);

    alert.setAlertType(Alert.AlertType.WARNING);

     // set content text
     alert.setContentText(" Choix invalide ");

     // show the dialog
     alert.show();
  }
     else{
           EvenementService se =new EvenementService();
       Evenement e;
        e = new Evenement(m.getIdEvent());
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("Confirmation ");
      alert.setHeaderText(null);
      alert.setContentText("Vous voulez vraiment supprimer cet event ");
      Optional<ButtonType> action = alert.showAndWait();
      if (action.get() == ButtonType.OK) {
         se.supprimer(e);
         afficherEvenements();

      }
    }
}       
        

    int selectionner()
    {   

          data=tableSafa.getSelectionModel().getSelectedItems();
            int id;
           id=data.get(0).getIdEvent();
            System.out.println(id);
            nom.setText(data.get(0).getNom());
            type.setText(data.get(0).getType());
            dressCode.setText(data.get(0).getDressCode());
            image.setText(data.get(0).getDressCode());
        nbrPlace.setText(String.valueOf(data.get(0).getNbrPlace()));
            return id;
    }

 int getid()
    {
        data=tableSafa.getSelectionModel().getSelectedItems();
            int id;
          return  id=data.get(0).getIdEvent();
    }

    private void GoBack(ActionEvent event) throws IOException {
        System.out.println(" Retour ");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/gui/accueil.fxml"));
        Parent root = loader.load();
        containerEvent.getChildren().setAll(root);
    }

    @FXML
      void Dashboard(ActionEvent event) throws IOException {
       System.out.println(" accueil ");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/gui/Dashboard.fxml"));
        Parent root = loader.load();
        containerEvent.getChildren().setAll(root);
    
    } 
    @FXML
     void ListerEvent(ActionEvent event) throws IOException {
      System.out.println(" liste des evenements ");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/gui/accueil.fxml"));
        Parent root = loader.load();
        containerEvent.getChildren().setAll(root);
    }
   
    @FXML
    void GoToReserv(ActionEvent event) throws IOException {
        System.out.println(" liste des reservations ");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/gui/AfficherCommande.fxml"));
        Parent root = loader.load();
        containerEvent.getChildren().setAll(root);
    }    
    @FXML
     void LogOut(ActionEvent event) throws IOException {
      System.out.println(" se deconnecter ");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/gui/seConnecter.fxml"));
        Parent root = loader.load();
        containerEvent.getChildren().setAll(root);
    }  
    
    @FXML
    void goToParents(ActionEvent event) throws IOException {
      System.out.println(" gestion parents ");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/gui/GestionParent.fxml"));
        Parent root = loader.load();
        containerEvent.getChildren().setAll(root);
    }   

    @FXML
    private void showPaneProfil(MouseEvent event) {
    }

    @FXML
    private void stat(ActionEvent event) throws IOException {
        
        System.out.println(" Lister les evenements ");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/gui/Stat.fxml"));
        Parent root = loader.load();
        containerEvent.getChildren().setAll(root); 
    
        
    }

    @FXML
    private void hidePane(MouseEvent event) {
    }
}    

    

