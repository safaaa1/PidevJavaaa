/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.controller;

import java.io.IOException;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import pidev.services.PaymentService;

/**
 *
 * @author islem
 */

public class PaymentController {
    
 
    
@FXML
    private TextField numcardtxt;

    @FXML
    private TextField exp_month;

    @FXML
    private TextField exp_year;

    @FXML
    private TextField cvctxt;
        @FXML
    private TextField mnt;

    @FXML
    private Button save;

        @FXML
    private Label exp_yearTest;

    @FXML
    private Button viewEnfant;
    boolean verificationexp_year=false;
    @FXML
    private Button viewEnfant1;
    @FXML
    private AnchorPane sakhta;
   
        @FXML
    void ajouterpayment(ActionEvent event)   throws SQLException  {
           

            PaymentService p = new PaymentService();
            if(!numcardtxt.getText().equals("")&&!exp_month.getText().equals("")&&!exp_year.getText().equals("")&&!cvctxt.getText().equals("")&&!mnt.getText().equals("")){
            p.payer(numcardtxt.getText(), exp_month.getText(), exp_year.getText(), cvctxt.getText(), mnt.getText());
           
             if(!numcardtxt.getText().equalsIgnoreCase("4242 4242 4242 4242")||(isMonth(exp_month.getText()))){
            Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Your card number is incorrect OU UR MONTH");
        //alert.setHeaderText("Look, a Warning Dialog")
        alert.showAndWait();}
            else{ 
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            
             alert.setContentText("payment transfrée !");
             alert.showAndWait();
               numcardtxt.setText("");
               exp_month.setText("");
               exp_year.setText("");
               cvctxt.setText("");
               mnt.setText("");
            }}
             else{
            
             Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        //alert.setHeaderText("Look, a Warning Dialog")
        alert.showAndWait();}
    }
    @FXML
    private void controlNumero(KeyEvent event) {
        verificationexp_year = false;
        if (exp_year.getText().trim().length() == 4) {
            boolean test = true;
            for (int i = 1; i < exp_year.getText().trim().length() && test; i++) {
                char ch = exp_year.getText().charAt(i);
                if (Character.isLetter(ch)) {
                    test = false;
                }
            }
            if (test) {
                System.out.println("wrong year");
                exp_yearTest.setVisible(false);
                verificationexp_year = true;
            }
        } else {
            System.out.println(" year non valide");
            exp_yearTest.setVisible(true);
            exp_yearTest.setText("Il faut 4 chiffres");
            verificationexp_year = false;
        }
    }
           public static boolean isMonth(String month)
{
  
	if( month!=null && month.trim().length()>0 )
	return month.matches("^[1-12]{1,2}$");
	return false;
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

