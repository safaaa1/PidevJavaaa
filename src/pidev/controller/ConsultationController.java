/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import pidev.entites.Consultation;
import pidev.entites.Enfant;
import pidev.entites.Medecin;
import pidev.services.ServiceConsultation;
import pidev.utils.ConnectionBD;

/**
 *
 * @author yanisinfo
 */
public class ConsultationController implements Initializable{
    @FXML
    private ComboBox<Medecin> cbmed;
    private ObservableList<Consultation> listt;

    @FXML
    private ComboBox<Enfant> cbenf;

    @FXML
    private DatePicker dpdate;
    @FXML
    private TextField recherchetxt;
    @FXML
    private TableView<Consultation> table;

    @FXML
    private TableColumn<Consultation, String> colmed;

    @FXML
    private TableColumn<Consultation, String> colenf;

    @FXML
    private TableColumn<Consultation, Date> coldate;
    @FXML
    private TableColumn<Consultation, Integer> colid;
    
        public ObservableList<Consultation> data=FXCollections.observableArrayList();
        public ObservableList<Medecin> data2=FXCollections.observableArrayList();
        public ObservableList<Enfant> data3=FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       
        viewMedecin();
        viewEnfant();
     viewConsultation();  
    }
     public void viewConsultation(){
    ServiceConsultation se = new ServiceConsultation();
    table.setItems((ObservableList<Consultation>) se.afficher());
    //colid.setCellValueFactory(new PropertyValueFactory<Consultation,Integer>("idCons"));
    colmed.setCellValueFactory(new PropertyValueFactory<Consultation,String>("nomMed"));
    colenf.setCellValueFactory(new PropertyValueFactory<Consultation,String>("nomEnf"));
    coldate.setCellValueFactory(new PropertyValueFactory<Consultation,Date>("dateCons"));

    }
     
    public void viewMedecin(){
    try{
      Connection cnx = ConnectionBD.getInstance().getCnx();
      String sql="SELECT * from medcin";
      PreparedStatement stat = cnx.prepareStatement(sql);
      ResultSet rs = stat.executeQuery();
      while (rs.next()){
        data2.add(new Medecin(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5)));

      }
      

    }
    catch(Exception e){
        e.printStackTrace();
    }
    cbmed.setItems(data2);

    }

    public void viewEnfant(){
    try{
      Connection cnx = ConnectionBD.getInstance().getCnx();
      String sql="SELECT * from enfant";
      PreparedStatement stat = cnx.prepareStatement(sql);
      ResultSet rs = stat.executeQuery();
      while (rs.next()){
        data3.add(new Enfant(rs.getInt(1), rs.getString(2), rs.getInt(3),rs.getInt(4)));

      }
      

    }
    catch(Exception e){
        e.printStackTrace();
    }
    cbenf.setItems(data3);

    }
    
    public void insertConsultation(ActionEvent event){
        if(!cbenf.getSelectionModel().isEmpty()&&!cbmed.getSelectionModel().isEmpty()&&!dpdate.getValue().equals(null)){
            ServiceConsultation se = new ServiceConsultation();
            Medecin m =cbmed.getSelectionModel().getSelectedItem();
            Enfant e =cbenf.getSelectionModel().getSelectedItem();
            Date datecons = new Date(dpdate.getValue().getYear()-1900, dpdate.getValue().getMonthValue()-1, dpdate.getValue().getDayOfMonth());

        se.ajouter(new Consultation(m.getIdMedecin(),e.getIdEnfant(),datecons));
           
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information");
           // alert.setHeaderText("Look, an Information Dialog");
            alert.setContentText("Consultation ajoutéz !");
            alert.showAndWait();
              /* nomtxt.setText("");
               agetxt.setText("");*/
               viewConsultation();
               //clearFields();

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
    Consultation m = table.getSelectionModel().getSelectedItem();

    if (m == null) {
    Alert alert = new Alert(AlertType.WARNING);

    alert.setAlertType(Alert.AlertType.WARNING);

     // set content text
     alert.setContentText(" Choix invalide ");

     // show the dialog
     alert.show();
  }
     else{
           ServiceConsultation se =new ServiceConsultation();
       Consultation e =new Consultation(m.getIdCons());
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("Confirmation ");
      alert.setHeaderText(null);
      alert.setContentText("Vous voulez vraiment supprimer cette consultation ");
      Optional<ButtonType> action = alert.showAndWait();
      if (action.get() == ButtonType.OK) {
         se.supprimer(e);
          
         viewConsultation();

      }
    }
}
        @FXML
         private void modifier(ActionEvent event){
           selectionner();
   
    }

 int selectionner()
    {
          data=table.getSelectionModel().getSelectedItems();
            int id;
           id=data.get(0).getIdEnf();
            System.out.println(id);
            
            //cbmed.setValue(data.get(0).getIdMed());
            //agetxt.setText(String.valueOf(data.get(0).getAge()));
            
            dpdate.setValue(data.get(0).getDateCons().toLocalDate());
            return id;
    }
  int getid()
    {
        data=table.getSelectionModel().getSelectedItems();
            int id;
          return  id=data.get(0).getIdCons();
    }
       @FXML
    void saveedit(ActionEvent event) {
             Connection cnx = ConnectionBD.getInstance().getCnx();
             
         try {
            String requete = "UPDATE consultation SET id_medecin=?,id_enfant=?,date_const=? WHERE id_const=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            Medecin m =cbmed.getSelectionModel().getSelectedItem();
            Enfant e =cbenf.getSelectionModel().getSelectedItem();

            pst.setInt(4, getid());
            pst.setInt(1, m.getIdMedecin());
            pst.setInt(2, e.getIdEnfant());
            Date datecons = new Date(dpdate.getValue().getYear()-1900, dpdate.getValue().getMonthValue()-1, dpdate.getValue().getDayOfMonth());

            pst.setDate(3,datecons);
            pst.executeUpdate();
             viewConsultation();
            // clearFields();
            System.out.println("consultation modifiée !");
              Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information");
           // alert.setHeaderText("Look, an Information Dialog");
            alert.setContentText("Consultation Modifieé !");
            alert.showAndWait();
           

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } 
    }
    
    private void clearFields(){
       cbmed.getSelectionModel().clearSelection(); 
       cbenf.getSelectionModel().clearSelection();
       dpdate.getEditor().clear();
    }
    @FXML
    void reset(ActionEvent event) {
    	clearFields();
    }
}
