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
import javafx.scene.control.Alert.AlertType;
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
import pidev.entites.Enfant;
import pidev.services.AbonnementService;
import pidev.utils.ConnectionBD;

/**
 * FXML Controller class
 *
 * @author Moetaz Jebri
 */
public class AbonnementContineController implements Initializable {

    /**
     * Initializes the controller class.
     */
   @FXML
    private TextField nomptxt;

    @FXML
    private TextField typetxt;

    @FXML
    private DatePicker dpdate;

    @FXML
    private ComboBox<Enfant> cbenf;

    @FXML
    private Button save;

    @FXML
    private Button saveedit;

    @FXML
    private Button reset;

    @FXML
    private TextField filterField;

    @FXML
    private Button logout;

    @FXML
    private TableView<Abonnement> table;

    @FXML
    private TableColumn<Abonnement, Integer> colenf;

    @FXML
    private TableColumn<Abonnement, String> colpar;

    @FXML
    private TableColumn<Abonnement, String> coltype;

    @FXML
    private TableColumn<Abonnement, Date> coldate;

    @FXML
    private MenuItem supprimer;

    @FXML
    private MenuItem modifier;

    public ObservableList<Abonnement> data=FXCollections.observableArrayList();
        public ObservableList<Enfant> data2=FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        viewAbonnnement();
        viewEnfant();
    }    
       public void viewAbonnnement(){
    AbonnementService se = new AbonnementService();
    table.setItems((ObservableList<Abonnement>) se.afficher());
    colenf.setCellValueFactory(new PropertyValueFactory<Abonnement,Integer>("Idenf"));
    colpar.setCellValueFactory(new PropertyValueFactory<Abonnement,String>("NomParent"));
    coltype.setCellValueFactory(new PropertyValueFactory<Abonnement,String>("Typepay"));
    coldate.setCellValueFactory(new PropertyValueFactory<Abonnement,Date>("date"));

    }
     public void viewEnfant(){
    try{
      Connection cnx = ConnectionBD.getInstance().getCnx();
      String sql="SELECT * from enfant";
      PreparedStatement stat = cnx.prepareStatement(sql);
      ResultSet rs = stat.executeQuery();
      while (rs.next()){
      data2.add(new Enfant(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4)));

      } 
          }
    catch(Exception e){
        e.printStackTrace();
    }
    cbenf.setItems(data2);

    }
     @FXML
    public void insertAbonnement(ActionEvent event){
        if(!nomptxt.getText().equals("")&&!typetxt.getText().equals("")){
            AbonnementService se = new AbonnementService();
            Enfant dm =cbenf.getSelectionModel().getSelectedItem();
    Date datecons = new Date(dpdate.getValue().getYear()-1900, dpdate.getValue().getMonthValue()-1, dpdate.getValue().getDayOfMonth());

        se.ajouter(new Abonnement(dm.getIdEnfant(),nomptxt.getText(),datecons,typetxt.getText()));
           
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information");
           // alert.setHeaderText("Look, an Information Dialog");
            alert.setContentText("Abonnement ajouté !");
            alert.showAndWait();
               /*nomtxt.setText("");
               agetxt.setText("");*/
               viewAbonnnement();
              // clearFields();

        }else{
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Warning");
        //alert.setHeaderText("Look, a Warning Dialog");
        alert.setContentText("verifier vos parametres ");
        alert.showAndWait();
        }

    }
        @FXML
    void supprimer(ActionEvent event) {
    Abonnement m = table.getSelectionModel().getSelectedItem();

    if (m == null) {
    Alert alert = new Alert(AlertType.WARNING);

    alert.setAlertType(Alert.AlertType.WARNING);

     // set content text
     alert.setContentText(" Choix invalide ");

     // show the dialog
     alert.show();
  }
     else{
           AbonnementService se =new AbonnementService();
       Abonnement e =new Abonnement(m.getIdAbn());
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("Confirmation ");
      alert.setHeaderText(null);
      alert.setContentText("Vous voulez vraiment supprimer cette abonnement ");
      Optional<ButtonType> action = alert.showAndWait();
      if (action.get() == ButtonType.OK) {
         se.supprimer(e);
         viewAbonnnement();

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
           id=data.get(0).getNomParent();
            System.out.println(id);
            nomptxt.setText(data.get(0).getNomParent());
            typetxt.setText(data.get(0).getTypepay());
            return id;
    }
  int getid()
    {
        data=table.getSelectionModel().getSelectedItems();
            int id;
          return  id=data.get(0).getIdAbn();
    }
  @FXML
      void saveedit(ActionEvent event) {
             Connection cnx = ConnectionBD.getInstance().getCnx();
             
         try {
            String requete = "UPDATE abonne SET IdEnf=?,NomParent=?,Date=?,TypePay=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
                        Enfant dm =cbenf.getSelectionModel().getSelectedItem();

            pst.setInt(5, getid());
            pst.setInt(1, dm.getIdEnfant());
            pst.setString(2, nomptxt.getText());
            Date datecons = new Date(dpdate.getValue().getYear()-1900, dpdate.getValue().getMonthValue()-1, dpdate.getValue().getDayOfMonth());

            pst.setDate(3,datecons);
            pst.setString(4,typetxt.getText());
            
            pst.executeUpdate();
           // System.out.println("Enfant modifiée !");
            viewAbonnnement();
            //clearFields();
                         Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information");
           // alert.setHeaderText("Look, an Information Dialog");
            alert.setContentText("Abonnement Modifieé !");
            alert.showAndWait();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } 
    }
}
