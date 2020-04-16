/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.controller;


import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import pidev.entites.Abonnement;
import pidev.entites.Contine;
import pidev.entites.Enfant;
import pidev.services.ContineService;
import pidev.utils.ConnectionBD;

/**
 * FXML Controller class
 *
 * @author Moetaz Jebri
 */
public  class AfficherContineController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TextField nomptxt;

    @FXML
    private TextField menutxt;

    @FXML
    private TextField plattxt;

    @FXML
    private TextField nbrtxt;
    
    @FXML
    private ComboBox<Contine> colabn;

    @FXML
    private Button save;

    @FXML
    private Button saveedit;

    @FXML
    private TextField filterField;

    @FXML
    private Button logout;

    @FXML
    private TableView<Contine> table;

    @FXML
    private TableColumn<Contine, String> colnom;

    @FXML
    private TableColumn<Contine, String> colmenu;

    @FXML
    private TableColumn<Contine, Date> coldate;

    @FXML
    private TableColumn<Contine, String> colplat;

    @FXML
    private TableColumn<Contine, Integer> colenf;

    @FXML
    private MenuItem supprimer;

    @FXML
    private MenuItem modifier;
    @FXML
    private ComboBox<Enfant> cbenf;
    @FXML
    private DatePicker dpdate;

    @FXML
    private ComboBox<Abonnement> cbab;

    public ObservableList<Contine> data=FXCollections.observableArrayList();
    public ObservableList<Abonnement> data2=FXCollections.observableArrayList();
    public ObservableList<Enfant> data3=FXCollections.observableArrayList();
        @Override
    public void initialize(URL location, ResourceBundle resources) {
        viewContine();
        viewEnfant();
        viewAbonnement();
       
    }
    
       public void viewContine(){
    ContineService se = new ContineService();
    table.setItems((ObservableList<Contine>) se.afficher());
    colnom.setCellValueFactory(new PropertyValueFactory<Contine,String>("Nom"));
    colmenu.setCellValueFactory(new PropertyValueFactory<Contine,String>("Menu"));
    coldate.setCellValueFactory(new PropertyValueFactory<Contine,Date>("date"));
    colplat.setCellValueFactory(new PropertyValueFactory<Contine,String>("PlatDuJours"));
    colenf.setCellValueFactory(new PropertyValueFactory<Contine,Integer>("Nbenf"));

    }
    
    public void viewEnfant(){
    try{
      Connection cnx = ConnectionBD.getInstance().getCnx();
      String sql="SELECT * from enfant";
      PreparedStatement stat = cnx.prepareStatement(sql);
      ResultSet rs = stat.executeQuery();
      while (rs.next()){
      data3.add(new Enfant(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4)));

      } 
          }
    catch(Exception e){
        e.printStackTrace();
    }
    cbenf.setItems(data3);

    }
    
    public void viewAbonnement(){
    try{
      Connection cnx = ConnectionBD.getInstance().getCnx();
      String sql="SELECT * from abonne";
      PreparedStatement stat = cnx.prepareStatement(sql);
      ResultSet rs = stat.executeQuery();
      while (rs.next()){
      data2.add(new Abonnement(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getDate(4),rs.getString(5)));

      } 
          }
    catch(Exception e){
        e.printStackTrace();
    }
    cbab.setItems(data2);

    }
    

    
    
     @FXML
    public void insertContine(ActionEvent event){
        if(!nomptxt.getText().equals("")&&!menutxt.getText().equals("")){
            ContineService se = new ContineService();
            Enfant dm =cbenf.getSelectionModel().getSelectedItem();
            Abonnement ab =cbab.getSelectionModel().getSelectedItem();
              
            Date datecons = new Date(dpdate.getValue().getYear()-1900, dpdate.getValue().getMonthValue()-1, dpdate.getValue().getDayOfMonth());

            se.ajouter(new Contine(nomptxt.getText(),menutxt.getText(),datecons,plattxt.getText(),Integer.parseInt(nbrtxt.getText()),dm.getIdEnfant(),ab.getIdAbn()));
           
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
           // alert.setHeaderText("Look, an Information Dialog");
            alert.setContentText("Contine ajouté !");
            alert.showAndWait();
               /*nomtxt.setText("");
               agetxt.setText("");*/
               viewContine();
              // clearFields();

        }else{
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        //alert.setHeaderText("Look, a Warning Dialog");
        alert.setContentText("verifier vos parametres ");
        alert.showAndWait();
        }

    }
    @FXML
    void supprimer(ActionEvent event) {
    Contine m = table.getSelectionModel().getSelectedItem();

    if (m == null) {
    Alert alert = new Alert(Alert.AlertType.WARNING);

    alert.setAlertType(Alert.AlertType.WARNING);

     // set content text
     alert.setContentText(" Choix invalide ");

     // show the dialog
     alert.show();
  }
     else{
       ContineService se =new ContineService();
       Contine e =new Contine(m.getIdC());
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("Confirmation ");
      alert.setHeaderText(null);
      alert.setContentText("Vous voulez vraiment supprimer cette abonnement ");
      Optional<ButtonType> action = alert.showAndWait();
      if (action.get() == ButtonType.OK) {
         se.supprimer(e);
         viewContine();

      }
    }
} 
         @FXML
         private void modifier(ActionEvent event){
           selectionner();
   
    }
         
 String selectionner()
    {
          data=table.getSelectionModel().getSelectedItems();
            String id;
           id=data.get(0).getNom();
            System.out.println(id);
            nomptxt.setText(data.get(0).getNom());
            menutxt.setText(data.get(0).getMenu());
            plattxt.setText(data.get(0).getPlatDuJours());
            return id;
    }
  int getid()
    {
        data=table.getSelectionModel().getSelectedItems();
            int id;
          return  id=data.get(0).getIdC();
    }

  @FXML
      void saveedit(ActionEvent event) {
             Connection cnx = ConnectionBD.getInstance().getCnx();
             
         try {
            String requete = "UPDATE cont SET Nom=?,Menu=?,Date=?,PlatDuJour=?,NbEnf=?,IdEnf=?,IdAb=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
                        Enfant dm =cbenf.getSelectionModel().getSelectedItem();
                        Abonnement ab =cbab.getSelectionModel().getSelectedItem();

           
            pst.setInt(6, dm.getIdEnfant());
            pst.setString(1, nomptxt.getText());
            Date datecons = new Date(dpdate.getValue().getYear()-1900, dpdate.getValue().getMonthValue()-1, dpdate.getValue().getDayOfMonth());

            pst.setDate(3,datecons);
            pst.setString(4,plattxt.getText());
            pst.setString(2,menutxt.getText());
            pst.setString(5,nbrtxt.getText());
            pst.setInt(7, ab.getIdAbn());
            pst.setInt(8, getid());
            
            pst.executeUpdate();
           // System.out.println("Enfant modifiée !");
            viewContine();
            //clearFields();
                         Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
           // alert.setHeaderText("Look, an Information Dialog");
            alert.setContentText("Abonnement Modifieé !");
            alert.showAndWait();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } 
    }

}

    
    
   
    
