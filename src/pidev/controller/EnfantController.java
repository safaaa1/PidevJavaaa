/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.controller;

import com.itextpdf.text.DocumentException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;
import javax.swing.JOptionPane;
import pidev.entites.DossierMedical;
import pidev.entites.Enfant;
import pidev.services.ServiceEnfant;
import pidev.utils.ConnectionBD;
import pidev.utils.pdf;
import static pidev.utils.pdf.pdf2;

/**
 * FXML Controller class
 *
 * @author yanisinfo
 */
public class EnfantController implements Initializable {
    @FXML
    private TextField nomtxt;
    @FXML
    private TextField agetxt;
    @FXML
    private ComboBox<DossierMedical> cbdm;
    @FXML
    private TableView<Enfant> table;

    @FXML
    private TableColumn<Enfant,String> colnom;
    @FXML
    private TableColumn<Enfant,Integer> colage;
    @FXML
    private TableColumn<Enfant,String> coldm;
    @FXML
    private TableColumn imprimerFacteur;
    @FXML
    private MenuItem supprimer;
    
    @FXML
    private TextField filterField;
    @FXML
    private Button saveedit;

    public ObservableList<Enfant> data=FXCollections.observableArrayList();
        public ObservableList<DossierMedical> data2=FXCollections.observableArrayList();
    @FXML
    private BorderPane sakhta;
    @FXML
    private Button save;
    @FXML
    private Button reset;
    @FXML
    private Button logout;
    @FXML
    private MenuItem modifier;
    @FXML
    private TableColumn<?, ?> colmodifier;

    
        @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        viewEnfant();
        viewDossier();
        filter();
    }
    @FXML
        public void filter(){
     ServiceEnfant se = new ServiceEnfant();
         data = FXCollections.observableArrayList(se.afficher());
            FilteredList<Enfant> filterdata = new FilteredList<>(data, t -> true);
            filterField.setOnKeyReleased(t -> {
                filterField.textProperty().addListener((observableValue, oldValue, newValue) -> {
                    filterdata.setPredicate((Predicate<? super Enfant>) type -> {
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }
                        if((type.getNom().contains(newValue)) || (type.getNom().toLowerCase().contains(newValue) )||
                                ((type.getAge()+"").contains(newValue)))
                                 {
                            return true;
                        }
                        return false;
                    });
                });
                SortedList<Enfant> sorteddata = new SortedList<>(filterdata);
                data = sorteddata;  
                table.setItems(data);
       
  
            });
    }
    
       @FXML
    private void logout(ActionEvent event) throws IOException {
        System.out.println(" Retour ");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/gui/seConnecter.fxml"));
        Parent root = loader.load();
        sakhta.getChildren().setAll(root);
    }  
        
        
        
        
    public void viewEnfant(){
    ServiceEnfant se = new ServiceEnfant();
    table.setItems((ObservableList<Enfant>) se.afficher());
    //colid.setCellValueFactory(new PropertyValueFactory<Enfant,Integer>("idEnfant"));
    colnom.setCellValueFactory(new PropertyValueFactory<Enfant,String>("nom"));
    colage.setCellValueFactory(new PropertyValueFactory<Enfant,Integer>("age"));
    coldm.setCellValueFactory(new PropertyValueFactory<Enfant,String>("titre"));
    
    
    
    
    Callback<TableColumn<Enfant, String>, TableCell<Enfant, String>> cellFactoryImprimer = (param) -> {

    final TableCell<Enfant, String> cell = new TableCell<Enfant, String>() {

        @Override
        public void updateItem(String item, boolean empty) {

           // super.updateItem(item, empty);
          //  if (empty) {
               // setGraphic(null);
               // setText(null);
          //  } else {//if(4>9){
                final Button test = new Button("Imprimer");
                test.setOnAction(event -> {
                    Enfant p = getTableView().getItems().get(getIndex());
                    try {
                        pdf.pdf(p);
                    } catch (FileNotFoundException ex) {
                        System.out.print(ex);                   
                    } catch (DocumentException ex) {
                       System.out.print(ex); 
                    }
                    //refresh();

                });

                setGraphic(test);
                setText(null);
                //}

            }
    
        //return null;

    };
            return cell;
        };
        imprimerFacteur.setCellFactory(cellFactoryImprimer);
    
    
    
    
    
    

    }
    public void viewDossier(){
    try{
      Connection cnx = ConnectionBD.getInstance().getCnx();
      String sql="SELECT * from dossier_medical";
      PreparedStatement stat = cnx.prepareStatement(sql);
      ResultSet rs = stat.executeQuery();
      while (rs.next()){
      data2.add(new DossierMedical(rs.getInt(1), rs.getString(2), rs.getString("contenu")));

      }
      

    }
    catch(Exception e){
        e.printStackTrace();
    }
    cbdm.setItems(data2);

    }
    
    
    @FXML
    public void insertEnfant(ActionEvent event) throws FileNotFoundException, DocumentException{
        if(!nomtxt.getText().equals("")&&!agetxt.getText().equals("")){
            ServiceEnfant se = new ServiceEnfant();
            DossierMedical dm =cbdm.getSelectionModel().getSelectedItem();
        se.ajouter(new Enfant(nomtxt.getText(),Integer.parseInt(agetxt.getText()),dm.getIdDM()));
         Enfant m = table.getSelectionModel().getSelectedItem();  
          Enfant e =new Enfant(m.getIdEnfant());
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information");
           // alert.setHeaderText("Look, an Information Dialog");
            alert.setContentText("Enfant ajouté !");
            alert.showAndWait();
               nomtxt.setText("");
               agetxt.setText("");
               viewEnfant();
               clearFields();
               pdf.pdf(e);
               

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
    Enfant m = table.getSelectionModel().getSelectedItem();

    if (m == null) {
    Alert alert = new Alert(AlertType.WARNING);

    alert.setAlertType(Alert.AlertType.WARNING);

     // set content text
     alert.setContentText(" Choix invalide ");

     // show the dialog
     alert.show();
  }
     else{
           ServiceEnfant se =new ServiceEnfant();
       Enfant e =new Enfant(m.getIdEnfant());
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("Confirmation ");
      alert.setHeaderText(null);
      alert.setContentText("Vous voulez vraiment supprimer ce Enfant ");
      Optional<ButtonType> action = alert.showAndWait();
      if (action.get() == ButtonType.OK) {
         se.supprimer(e);
         viewEnfant();
         

      }
    }
}       @FXML
         private void modifier(ActionEvent event){
           selectionner();
   
    }

 String selectionner()
    {
          data=table.getSelectionModel().getSelectedItems();
            String id;
           id=data.get(0).getNom();
            System.out.println(id);
            agetxt.setText(String.valueOf(data.get(0).getAge()));
            nomtxt.setText(data.get(0).getNom());
            return id;
    }

       @FXML
    void saveedit(ActionEvent event) {
             Connection cnx = ConnectionBD.getInstance().getCnx();
             
         try {
            String requete = "UPDATE enfant SET nom=?,age=?,id_dossier=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
                        DossierMedical dm =cbdm.getSelectionModel().getSelectedItem();

            pst.setInt(4, getid());
            pst.setString(1, nomtxt.getText());
            pst.setInt(2, Integer.parseInt(agetxt.getText()));
            pst.setInt(3,dm.getIdDM());
            pst.executeUpdate();
           // System.out.println("Enfant modifiée !");
            viewEnfant();
            clearFields();
                         Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information");
           // alert.setHeaderText("Look, an Information Dialog");
            alert.setContentText("Enfant Modifieé !");
            alert.showAndWait();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } 
    }

 int getid()
    {
        data=table.getSelectionModel().getSelectedItems();
            int id;
          return  id=data.get(0).getIdEnfant();
    }
 
 private void clearFields(){
     nomtxt.clear();
     agetxt.clear();
     cbdm.getSelectionModel().clearSelection();
 }
     @FXML
    void reset(ActionEvent event) {
    	clearFields();
    }
    
    
    
}