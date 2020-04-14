/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.controller;




import com.nexmo.client.NexmoClient;
import com.nexmo.client.sms.MessageStatus;
import com.nexmo.client.sms.SmsSubmissionResponse;
import com.nexmo.client.sms.messages.TextMessage;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import pidev.entites.Enseignant;
import pidev.entites.Salaire;
import pidev.services.EnseignantService;
import pidev.utils.ConnectionBD;




import javafx.collections.ObservableList;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.chart.*;
import javafx.collections.FXCollections;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author islem
 */
public class EnseignantController implements Initializable {

    @FXML
    private AnchorPane containerAdmin;
    
    @FXML
    private TextField nomtxt;

    @FXML
    private TextField prenomtxt;

    @FXML
    private TextField emailtxt;

    @FXML
    private TextField teltxt;

    @FXML
    private Button save;

    @FXML
    private Button saveedit;
   @FXML
    private TextField filterField;
       @FXML
    private Label numTelTest;

    @FXML
    private Button viewEnseignant;

    @FXML
    private TableView<Enseignant> table;

  

    @FXML
    private TableColumn<Enseignant, String> colnom;

    @FXML
    private TableColumn<Enseignant, String> colprenom;

    @FXML
    private TableColumn<Enseignant, String> colemail;

    @FXML
    private TableColumn<Enseignant, Integer> coltelephone;
        @FXML
    private ComboBox<Salaire> cbsalaire;
    
     public ObservableList<Enseignant> data=FXCollections.observableArrayList();
             public ObservableList<Salaire> data2=FXCollections.observableArrayList();
              //  ObservableList<PieChart> pieChartData = FXCollections.observableArrayList();

     
    @FXML
    private Button reset;
    @FXML
    private MenuItem supprimer;
    @FXML
    private MenuItem modifier;
    boolean verificationNumTel=false;
    @FXML
    private TableColumn<Enseignant, Integer> salaire;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //   float sum= 1;    
        viewEnseignant();
        viewSalaire();

       filter();
        // TODO
        System.out.println("espace admin");
        List<PieChart.Data> l = new ArrayList<>();
        EnseignantService ps = new EnseignantService();
        float sum=ps.sumsalaire();
        if(sum==0)sum=1;
       /* ps.getAllEnseignant().stream().forEach(p->{
            
            l.add(new PieChart.Data(p.getNom(),p.getSalaire_montant()*100/sum));
        });*/
        for(Enseignant p :  ps.getAllEnseignant()){
            System.out.println("");
            
            l.add(new PieChart.Data(p.getNom(),p.getSalaire_montant()*100/sum));
        }
        
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        l
                );
        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Statistique Enseignant");
        chart.setLabelLineLength(10);
        chart.setLegendSide(Side.LEFT);
        final Label caption = new Label("");
        caption.setTextFill(Color.BLACK);
        caption.setStyle("-fx-text-fill: black;");
        caption.setStyle("-fx-font: 24 arial;");
        for (final PieChart.Data data : chart.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED,
                    new EventHandler<MouseEvent>() {
                        @Override public void handle(MouseEvent e) {
                            caption.setTranslateX(e.getSceneX());
                            caption.setTranslateY(e.getSceneY()-110);
                            caption.setText(String.valueOf(data.getPieValue()) + "%");
                        }
                    });
        }
        containerAdmin.getChildren().add(chart);
        containerAdmin.getChildren().add(caption);
    
 
    }
        
    public void filter(){
     EnseignantService se = new EnseignantService();
         data = FXCollections.observableArrayList(se.read());
            FilteredList<Enseignant> filterdata = new FilteredList<>(data, e -> true);
            filterField.setOnKeyReleased(e -> {
                filterField.textProperty().addListener((observableValue, oldValue, newValue) -> {
                    filterdata.setPredicate((Predicate<? super Enseignant>) type -> {
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }
                        if((type.getNom().contains(newValue)) || (type.getNom().toLowerCase().contains(newValue) )|| 
                                (type.getPrenom().contains(newValue)) || (type.getPrenom().toLowerCase().contains(newValue)) ||
                                ((type.getSalaire_montant()+"").contains(newValue)))  {
                            return true;
                        }
                        return false;
                    });
                });
                SortedList<Enseignant> sorteddata = new SortedList<>(filterdata);
                data = sorteddata;  
                table.setItems(data);
       
  
            });}
    
         
    
        
    
    
        public void viewEnseignant(){
    EnseignantService se = new EnseignantService();
   // table.setItems((ObservableList<Enseignant>) se.read());
    
    colnom.setCellValueFactory(new PropertyValueFactory<Enseignant,String>("nom"));
    colprenom.setCellValueFactory(new PropertyValueFactory<Enseignant,String>("prenom"));
    colemail.setCellValueFactory(new PropertyValueFactory<Enseignant,String>("email"));
    coltelephone.setCellValueFactory(new PropertyValueFactory<Enseignant,Integer>("tel"));
    salaire.setCellValueFactory(new PropertyValueFactory<Enseignant,Integer>("salaire_montant"));
    
    
  table.setItems((ObservableList<Enseignant>) se.read());
  

    }
    public void viewSalaire(){
    try{
      Connection cnx = ConnectionBD.getInstance().getCnx();
      String sql="SELECT * from salaire";
      PreparedStatement stat = cnx.prepareStatement(sql);
      ResultSet rs = stat.executeQuery();
      while (rs.next()){
     data2.add(new Salaire(rs.getInt(1), rs.getInt(2)));

      }
      

    }
    catch(Exception e){
        e.printStackTrace();
    }
    cbsalaire.setItems(data2);

    }
    @FXML
    private void insertEnseignant(ActionEvent event)  {
           
        if(!nomtxt.getText().equals("")&&!prenomtxt.getText().equals("")&&!emailtxt.getText().equals("")&&!teltxt.getText().equals("")){
            EnseignantService se = new EnseignantService();
           Salaire s = cbsalaire.getSelectionModel().getSelectedItem();
        se.add(new Enseignant(nomtxt.getText(),prenomtxt.getText(),emailtxt.getText(),Integer.parseInt(teltxt.getText()),0));
           
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information");
           // alert.setHeaderText("Look, an Information Dialog");
            alert.setContentText("Enseignant ajouté !");
            alert.showAndWait();
               nomtxt.setText("");
               prenomtxt.setText("");
               emailtxt.setText("");
               teltxt.setText("");
               viewEnseignant();
   /*            
   NexmoClient client = NexmoClient.builder().apiKey("372d5729").apiSecret("n3FnzJypbJxuwj5G").build();
  TextMessage message = new TextMessage("JUNIOR",
                   "+21626899579",
                    "félicitation, vous ete ajouté avec succé, bienvenu  !"
            );
  try{
    SmsSubmissionResponse response = client.getSmsClient().submitMessage(message);
  if (response.getMessages().get(0).getStatus() == MessageStatus.OK) {
    System.out.println("Message sent successfully.");
} else {
    System.out.println("Message failed with error: " + response.getMessages().get(0).getErrorText());
}
        }catch (NoClassDefFoundError ex) {
            System.err.println(ex.getMessage());
        } */   }        

        else{
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Warning");
        //alert.setHeaderText("Look, a Warning Dialog");
        alert.setContentText("verifier vos parametres ");
        alert.showAndWait();
        }
        }

    


    

    
    
    



    @FXML
    private void supprimer(ActionEvent event) {
          Enseignant m = table.getSelectionModel().getSelectedItem();

    if (m == null) {
    Alert alert = new Alert(AlertType.WARNING);

    alert.setAlertType(Alert.AlertType.WARNING);

     // set content text
     alert.setContentText(" Choix invalide ");

     // show the dialog
     alert.show();
  }
     else{
           EnseignantService se =new EnseignantService();
       Enseignant e =new Enseignant(m.getId());
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("Confirmation ");
      alert.setHeaderText(null);
      alert.setContentText("Vous voulez vraiment supprimer ce Enseignant ");
      Optional<ButtonType> action = alert.showAndWait();
      if (action.get() == ButtonType.OK) {
         se.delete(e);
         viewEnseignant();

      }
    }
    }

    @FXML
    private void modifier(ActionEvent event) {
         selectionner();
   
    }

 String selectionner()
    {
           data=table.getSelectionModel().getSelectedItems();
            String id;
           id=data.get(0).getNom();
            System.out.println(id);
            teltxt.setText(String.valueOf(data.get(0).getTel()));
            nomtxt.setText(data.get(0).getNom());
            prenomtxt.setText(data.get(0).getPrenom());
            emailtxt.setText(data.get(0).getEmail());
            return id;
    }

       @FXML
    void saveedit(ActionEvent event) {
             Connection cnx = ConnectionBD.getInstance().getCnx();
             
         try {
            String requete = "UPDATE Enseignant SET nom=?,prenom=?,email=?,tel=?,salaire_id=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
                       

            pst.setInt(6, getid());
            pst.setString(1, nomtxt.getText());
            pst.setString(2, prenomtxt.getText());
            pst.setString(3, emailtxt.getText());
            pst.setInt(4, Integer.parseInt(teltxt.getText()));
            pst.setInt(5, Integer.parseInt(teltxt.getText()));

           //salaire
            
            pst.executeUpdate();
            System.out.println("enseignant modifiée !");
            viewEnseignant();

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
    private void controlNumero(KeyEvent event) {
        verificationNumTel = false;
        if (teltxt.getText().trim().length() == 8) {
            boolean test = true;
            for (int i = 1; i < teltxt.getText().trim().length() && test; i++) {
                char ch = teltxt.getText().charAt(i);
                if (Character.isLetter(ch)) {
                    test = false;
                }
            }
            if (test) {
                System.out.println("taille num est valide");
                numTelTest.setVisible(false);
                verificationNumTel = true;
            }
        } else {
            System.out.println("taille num non valide");
            numTelTest.setVisible(true);
            numTelTest.setText("Il faut 8 chiffres");
            verificationNumTel = false;
        }
    }
    
    
    
    
    
    
    
    
    
    
    
 
    
    
    
}

    

