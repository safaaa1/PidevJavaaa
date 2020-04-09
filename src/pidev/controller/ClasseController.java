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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import pidev.entites.Classe;
import pidev.entites.Grade;
import pidev.services.ClasseServices;
import pidev.utils.ConnectionBD;

/**
 *
 * @author Lenovo
 */


public class ClasseController implements Initializable {
    
        @FXML
    private TextField nomtxt;

    @FXML
    private TextField nbtxt;

    @FXML
    private ComboBox<Grade> cbcl;

    @FXML
    private Button save;

    @FXML
    private Button saveedit;

    @FXML
    private Button reset;

    @FXML
    private TextField recherchetxt;
        @FXML
    private TableView<Classe> table;

    @FXML
    private TableColumn<Classe, Integer> colid;

    @FXML
    private TableColumn<Classe, String> colnom;

    @FXML
    private TableColumn<Classe, Integer> colnb;

    @FXML
    private TableColumn<Classe, Integer> colgr;
    public ObservableList<Classe> data=FXCollections.observableArrayList();
    public ObservableList<Grade> data2=FXCollections.observableArrayList();


    
        @Override
    public void initialize(URL location, ResourceBundle resources) {
        viewClasse();
        viewGrade();  
   }
   
    public void viewClasse(){
    ClasseServices se = new ClasseServices();
    table.setItems((ObservableList<Classe>) se.afficher());
    colid.setCellValueFactory(new PropertyValueFactory<Classe,Integer>("idcl"));
    colnb.setCellValueFactory(new PropertyValueFactory<Classe,Integer>("nbrenfcl"));
    colgr.setCellValueFactory(new PropertyValueFactory<Classe,Integer>("idgr"));
    colnom.setCellValueFactory(new PropertyValueFactory<Classe,String>("nomclasse"));

    }
    public void viewGrade(){
    try{
        Connection cnx = ConnectionBD.getInstance().getCnx();
        String sql="SELECT * from grade";
        PreparedStatement stat = cnx.prepareStatement(sql);
        ResultSet rs = stat.executeQuery();
        while(rs.next()){
        data2.add(new Grade(rs.getInt(1),rs.getInt(2), rs.getInt(3),rs.getString(4)));
        
        }
    }
    catch(Exception e){
        e.printStackTrace();
    }
    cbcl.setItems(data2);
    
    }
    
    
    public void insertClasse(ActionEvent event){
        if(!nomtxt.getText().equals("")&&!nbtxt.getText().equals("")){
            ClasseServices se = new ClasseServices();
            Grade dm =cbcl.getSelectionModel().getSelectedItem();
        se.ajouter(new Classe(Integer.parseInt(nbtxt.getText()),nomtxt.getText(),dm.getIdgr()));
           
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information");
           // alert.setHeaderText("Look, an Information Dialog");
            alert.setContentText("Classe ajouté !");
            alert.showAndWait();
               nomtxt.setText("");
               nbtxt.setText("");
               viewClasse();

        }else{
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Warning");
        //alert.setHeaderText("Look, a Warning Dialog");
        alert.setContentText("verifier vos parametres ");
        alert.showAndWait();
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
           id=data.get(0).getNomclasse();
            System.out.println(id);
            nbtxt.setText(String.valueOf(data.get(0).getNbrenfcl()));
            nomtxt.setText(data.get(0).getNomclasse());
            return id;
    }
    
    @FXML
    void saveedit(ActionEvent event) {
             Connection cnx = ConnectionBD.getInstance().getCnx();
             
         try {
            String requete = "UPDATE Classe SET Nbrenfcl=?,idgr=?,Nomclasse=? WHERE Idcl=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
                        Grade dm =cbcl.getSelectionModel().getSelectedItem();

            pst.setInt(4, getid()); 
            
            pst.setInt(1, Integer.parseInt(nbtxt.getText()));
            pst.setInt(2,dm.getIdgr());
            pst.setString(3, nomtxt.getText());
            pst.executeUpdate();
            System.out.println("Classe modifiée !");
            viewClasse();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } 
    }

 int getid()
    {
        data=table.getSelectionModel().getSelectedItems();
            int id;
          return  id=data.get(0).getIdcl();
    }
    @FXML
    void supprimer(ActionEvent event) {
    Classe m = table.getSelectionModel().getSelectedItem();

    if (m == null) {
    Alert alert = new Alert(AlertType.WARNING);

    alert.setAlertType(Alert.AlertType.WARNING);

     // set content text
     alert.setContentText(" Choix invalide ");

     // show the dialog
     alert.show();
  }
     else{
           ClasseServices se =new ClasseServices();
       Classe e =new Classe(m.getIdcl());
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("Confirmation ");
      alert.setHeaderText(null);
      alert.setContentText("Vous voulez vraiment supprimer ce Classe ");
      Optional<ButtonType> action = alert.showAndWait();
      if (action.get() == ButtonType.OK) {
         se.supprimer(e);
         viewClasse();

      }
    }
} 
    
}
