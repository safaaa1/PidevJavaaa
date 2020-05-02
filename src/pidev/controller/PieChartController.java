/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.controller;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import pidev.entites.Grade;
import pidev.services.GradeServices;


/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class PieChartController implements Initializable {

    @FXML
    private Button button1;
    @FXML
    private PieChart piechart;
    @FXML
    private AnchorPane sakhta;

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleButton1Action(ActionEvent event) {
        GradeServices ps=new GradeServices();
           String GradeA="A";
           String GradeB="B";
           String GradeL="L";
           
         String ch = "A ("+Integer.toString(ps.getnbrdegrade(GradeA)*100/ps.getnbrdegarde())+" % )";
         String ch1 = "B ("+Integer.toString(ps.getnbrdegrade(GradeB)*100/ps.getnbrdegarde())+" % )";
         String ch2 = "L ("+Integer.toString(ps.getnbrdegrade(GradeL)*100/ps.getnbrdegarde())+" % )";
        
       
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
              
             new PieChart.Data(ch, ps.getnbrdegrade(GradeA)*100/ps.getnbrdegarde()),
             new PieChart.Data(ch1,ps.getnbrdegrade(GradeB)*100/ps.getnbrdegarde() ),
             new PieChart.Data(ch2,ps.getnbrdegrade(GradeL)*100/ps.getnbrdegarde() )
             
            
         );
       
         
        piechart.setTitle("Statistiques des cours");
        piechart.setData(pieChartData);
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
        System.out.println(" Retour ");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/gui/seConnecter.fxml"));
        Parent root = loader.load();
        sakhta.getChildren().setAll(root);
   
    }

    @FXML
    private void Retour(ActionEvent event) throws IOException {
        System.out.println(" Retour ");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/gui/Dashboard.fxml"));
        Parent root = loader.load();
        sakhta.getChildren().setAll(root);
   
    } 
     
         
         
    }    