/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.gui;

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
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import pidev.services.EvenementService;

/**
 * FXML Controller class
 *
 * @author safa
 */
public class StatController implements Initializable {

    @FXML
    private Button parc;
    @FXML
    private Button button;
    @FXML
    private Button vet;
    @FXML
    private Button userName;
    @FXML
    private Button logOut;
    @FXML
    private Pane pane;
    @FXML
    private Button btn11;
    @FXML
    private Button btn1;
    @FXML
    private AnchorPane anchorEvent;
    @FXML
    private Text nombret;
    @FXML
    private Text nombre;
    @FXML
    private PieChart chart;
    @FXML
    private Button editp;
    @FXML
    private Button listp;
    @FXML
    private AnchorPane container_client;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
                try {

                    
                    
                    //User u= Session.getLoggedInUser();
                    //userName.setText(u.getUsername());
                    EvenementService m=null;
                    m = new EvenementService();
                    
                    
                    ObservableList<PieChart.Data> pieChartData =
                            FXCollections.observableArrayList(
                                   // new PieChart.Data("Total", m.CountParc()),
                                    new PieChart.Data("sport", m.CountParcByCateg("sport")),
                                    new PieChart.Data("bricolage", m.CountParcByCateg("bricolage")),
                    new PieChart.Data("dance", m.CountParcByCateg("dance")),
                    new PieChart.Data("fun", m.CountParcByCateg("fun")),

                             new PieChart.Data("peinture", m.CountParcByCateg("peinture")));

                    
                    chart.getData().addAll(pieChartData);
                    
                    
                    final Label caption = new Label("");
                    caption.setTextFill(Color.DARKORANGE);
                    caption.setStyle("-fx-font: 24 arial;");
                    Tooltip container = new Tooltip();
                    container.setGraphic(caption);
                    
                    chart.getData().forEach((data) ->
                    {
                        data.getNode().
                                
                                addEventHandler(MouseEvent.MOUSE_ENTERED, e ->
                                {  Stage stage=(Stage) button.getScene().getWindow();
                                if (container.isShowing())
                                {
                                    container.hide();
                                }
                                
                                caption.setText(String.valueOf((int)data.getPieValue()));
                                container.show(stage, e.getScreenX(), e.getScreenY());
                                });
                    });
                    chart.getData().forEach((data) ->
                    {
                        data.getNode().
                                
                                addEventHandler(MouseEvent.MOUSE_EXITED, e ->
                                {  Stage stage=(Stage) button.getScene().getWindow();
                                if (container.isHideOnEscape())
                                {
                                    container.hide();
                                }
                                
                           
                                });
                    });
                } catch (SQLException ex) {
                    Logger.getLogger(StatController.class.getName()).log(Level.SEVERE, null, ex);
                }
        
        
        
        
        
        
    }    

    @FXML
    private void parcAction(ActionEvent event) throws IOException {
          System.out.println(" Ajouter un evenement ");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/gui/accueil.fxml"));
        Parent root = loader.load();
        container_client.getChildren().setAll(root);
   
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
    }

    @FXML
    private void vetAffiche(ActionEvent event) {
    }

    @FXML
    private void RedirectPromAction(ActionEvent event) {
    }


    @FXML
    private void showPane(MouseEvent event) {
    }

    @FXML
    private void Deconnexion(ActionEvent event) {
    }

    @FXML
    private void connexionAction(ActionEvent event) {
    }

    @FXML
    private void mouseClick(MouseEvent event) {
    }

    private void listEvent(ActionEvent event) throws IOException {
        System.out.println(" Lister les evenements ");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/gui/AfficheEvenement.fxml"));
        Parent root = loader.load();
        container_client.getChildren().setAll(root); 
    
    }

    @FXML
    private void listeEvent(ActionEvent event) {
    }
    
}
