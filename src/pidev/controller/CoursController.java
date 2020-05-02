/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import pidev.entites.Classe;
import pidev.entites.Cours;
import pidev.entites.Enfant;
import pidev.entites.Enseignant;
import pidev.services.CoursServices;
import pidev.utils.ConnectionBD;

/**
 *
 * @author Lenovo
 */
public class CoursController  implements Initializable {

    @FXML
    private TextField nomtcxt;

    @FXML
    private TextField dutxt;

    @FXML
    private ComboBox<Classe> cbclasse;

    @FXML
    private ComboBox<Enseignant> listeenseignant;

    @FXML
    private ComboBox<Enfant> cbenfant;

    @FXML
    private Button save;

    @FXML
    private Button saveedit;



    @FXML
    private TableView<Cours> table;



    @FXML
    private TableColumn<Cours,String> colnom;

    @FXML
    private TableColumn<Cours, String> coldu;

    @FXML
    private TableColumn<Cours, String> colcl;

    @FXML
    private TableColumn<Cours, String> colens;

    @FXML
    private TableColumn<Cours, String> colenf;
           
  public ObservableList<Cours> data=FXCollections.observableArrayList();
    public ObservableList<Enseignant> data2=FXCollections.observableArrayList();
    public ObservableList<Enfant> data3=FXCollections.observableArrayList();
    public ObservableList<Classe> data4=FXCollections.observableArrayList();
    @FXML
    private BorderPane sakhta;
    @FXML
    private Button viewEnfant1;
    @FXML
    private Button viewEnfant;
    @FXML
    private MenuItem supprimer;
    @FXML
    private MenuItem modifier;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    viewCours();
    viewClasse();
    viewEnseignant();
    viewEnfant();
    }
    
    public void viewCours(){
    CoursServices se = new CoursServices(); 
    table.setItems((ObservableList<Cours>) se.afficher());
    colcl.setCellValueFactory(new PropertyValueFactory<Cours,String>("nomclasse"));
    colnom.setCellValueFactory(new PropertyValueFactory<Cours,String>("nomcours"));
    coldu.setCellValueFactory(new PropertyValueFactory<Cours,String>("duree"));
    
    colenf.setCellValueFactory(new PropertyValueFactory<Cours,String>("nomenf"));
    colens.setCellValueFactory(new PropertyValueFactory<Cours,String>("nomeng"));
    
    }
    
     public void viewEnseignant(){
         
    try{
        
        Connection cnx = ConnectionBD.getInstance().getCnx();
        String sql="SELECT * from enseignant";
        PreparedStatement stat = cnx.prepareStatement(sql);
        ResultSet rs = stat.executeQuery();
        while(rs.next()){
        data2.add(new Enseignant(rs.getInt(1),rs.getString(2), rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6) ));
        
        }
    }
     catch(Exception e){
        e.printStackTrace();
    }
    listeenseignant.setItems(data2);
    
    }
     
       public void viewEnfant(){
         
    try{
        
        Connection cnx = ConnectionBD.getInstance().getCnx();
        String sql="SELECT * from Enfant";
        PreparedStatement stat = cnx.prepareStatement(sql);
        ResultSet rs = stat.executeQuery();
        while(rs.next()){
        data3.add(new Enfant(rs.getInt(1), rs.getString(2),rs.getInt(3),rs.getInt(4) ));
        
        }
    }
     catch(Exception e){
        e.printStackTrace();
    }
    cbenfant.setItems(data3);
    
    }
       public void viewClasse() {
         
    try{
        
        Connection cnx = ConnectionBD.getInstance().getCnx();
        String sql="SELECT * from classe";
        PreparedStatement stat = cnx.prepareStatement(sql);
        ResultSet rs = stat.executeQuery();
        while(rs.next()){
        data4.add(new Classe(rs.getInt(1),rs.getInt(2), rs.getString(3),rs.getInt(4) ));
        
        }
    }
     catch(Exception e){
        e.printStackTrace();
    }
    cbclasse.setItems(data4);
    
    }
    @FXML
      public void insertCours(ActionEvent event){
        if(!nomtcxt.getText().equals("")&&!dutxt.getText().equals("")){
            CoursServices se = new CoursServices();
            Enseignant dm =listeenseignant.getSelectionModel().getSelectedItem();
            Classe cbC =cbclasse.getSelectionModel().getSelectedItem();
            Enfant cben =cbenfant.getSelectionModel().getSelectedItem();
        se.ajouter(new Cours(cbC.getIdcl(),nomtcxt.getText(),dutxt.getText(),cben.getIdEnfant(),dm.getId()));
           
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information");
           // alert.setHeaderText("Look, an Information Dialog");
            alert.setContentText("Classe ajouté !");
            alert.showAndWait();
               /*nomtxt.setText("");
               nbtxt.setText("");*/
               viewCours();

        }else{
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Warning");
        //alert.setHeaderText("Look, a Warning Dialog");
        alert.setContentText("verifier vos parametres ");
        alert.showAndWait();
        }

    }
    
    @FXML
    void modifier(ActionEvent event) {
selectionner();
    }
     
     String selectionner()
    {
          data=table.getSelectionModel().getSelectedItems();
            String id;
           id=data.get(0).getNomcours();
            System.out.println(id);
            dutxt.setText(data.get(0).getDuree());
            nomtcxt.setText(String.valueOf(data.get(0).getIdenfant()));
            return id;
    }
     
     @FXML
     void saveedit(ActionEvent event) {
             Connection cnx = ConnectionBD.getInstance().getCnx();
             
         try {
            String requete = "UPDATE cours SET idcl=?,nomcours=?,duree=?,idenfant=?,idenseignant=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            Enseignant dm =listeenseignant.getSelectionModel().getSelectedItem();
            Classe cbC =cbclasse.getSelectionModel().getSelectedItem();
            Enfant cben =cbenfant.getSelectionModel().getSelectedItem();
        
            pst.setInt(6, getid()); 
            
            pst.setInt(1, cbC.getIdcl());
            pst.setString(2,nomtcxt.getText());
            pst.setString(3, dutxt.getText());
            pst.setInt(4, cben.getIdEnfant());
            pst.setInt(5, dm.getId());
            pst.executeUpdate();
            System.out.println("Cours modifiée !");
            viewCours();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } 
    }

      int getid()
    {
        data=table.getSelectionModel().getSelectedItems();
            int id;
          return  id=data.get(0).getId();
    }
     
    @FXML
    void supprimer(ActionEvent event) {
            Cours m = table.getSelectionModel().getSelectedItem();

    if (m == null) {
    Alert alert = new Alert(AlertType.WARNING);

    alert.setAlertType(Alert.AlertType.WARNING);

     // set content text
     alert.setContentText(" Choix invalide ");

     // show the dialog
     alert.show();
  }
     else{
           CoursServices se =new CoursServices();
       Cours e =new Cours(m.getId());
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("Confirmation ");
      alert.setHeaderText(null);
      alert.setContentText("Vous voulez vraiment supprimer ce Classe ");
      Optional<ButtonType> action = alert.showAndWait();
      if (action.get() == ButtonType.OK) {
         se.supprimer(e);
         viewCours();

      }
    }

    }

    @FXML
    private void Retour(ActionEvent event) throws IOException {
        System.out.println(" Retour ");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/gui/Ahmed.fxml"));
        Parent root = loader.load();
        sakhta.getChildren().setAll(root);
   
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
        System.out.println(" Retour ");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/gui/seConnecter.fxml"));
        Parent root = loader.load();
        sakhta.getChildren().setAll(root);
      
    }
    
    
}
