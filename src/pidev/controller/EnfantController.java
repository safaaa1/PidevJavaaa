/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.util.Duration;
import javax.swing.JOptionPane;
import pidev.entites.DossierMedical;
import pidev.entites.Enfant;
import pidev.services.ServiceEnfant;
import pidev.utils.ConnectionBD;
import pidev.utils.pdf;
import static pidev.utils.pdf.pdf2;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

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
    private Button logout;
    @FXML
    private MenuItem modifier;
    @FXML
    private Button pdf;
    @FXML
    private Button logout1;
    
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
    
       
      Enfant e=new Enfant();  

    public Enfant getE() {
        return e;
    }

    public void setE(Enfant e) {
        this.e = e;
    }
        
        
        
    public void viewEnfant(){
    ServiceEnfant se = new ServiceEnfant();
    table.setItems((ObservableList<Enfant>) se.afficher());
    //colid.setCellValueFactory(new PropertyValueFactory<Enfant,Integer>("idEnfant"));
    colnom.setCellValueFactory(new PropertyValueFactory<Enfant,String>("nom"));
    colage.setCellValueFactory(new PropertyValueFactory<Enfant,Integer>("age"));
    coldm.setCellValueFactory(new PropertyValueFactory<Enfant,String>("titre"));
    
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
       public static boolean isNumber(String num)
{
  
	if( num!=null && num.trim().length()>0 )
	return num.matches("^[0-9]{1,2}$");
	return false;
} 
    
    @FXML
    public void insertEnfant(ActionEvent event) throws FileNotFoundException, DocumentException{
        if(!nomtxt.getText().equals("")&&!agetxt.getText().equals("")){
            if(isNumber(agetxt.getText())){
            ServiceEnfant se = new ServiceEnfant();
            DossierMedical dm =cbdm.getSelectionModel().getSelectedItem();
        se.ajouter(new Enfant(nomtxt.getText(),Integer.parseInt(agetxt.getText()),dm.getIdDM()));
         Enfant m = table.getSelectionModel().getSelectedItem();  
         // Enfant e =new Enfant(m.getIdEnfant());
          /*  Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information");
           // alert.setHeaderText("Look, an Information Dialog");
            alert.setContentText("Enfant ajouté !");
            alert.showAndWait();*/
            TrayNotification tray = new TrayNotification("Done","l'enfant est ajouté ", NotificationType.INFORMATION);
          tray.setAnimationType(AnimationType.SLIDE);
          tray.showAndDismiss(Duration.seconds(5));
               nomtxt.setText("");
               agetxt.setText("");
               viewEnfant();
               clearFields();
            //   pdf.pdf(e);
            }else{
              Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Warning");
        //alert.setHeaderText("Look, a Warning Dialog");
        alert.setContentText("verifier l'age ");
        alert.showAndWait();
            }      

        }else{
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Warning");
        //alert.setHeaderText("Look, a Warning Dialog");
        alert.setContentText("verifier vos parametres ");
        alert.showAndWait();
        }

    }
   Alert alert = new Alert(Alert.AlertType.NONE); 
   
  @FXML
    void pdfAction(ActionEvent event) {
           Enfant ss = table.getSelectionModel().getSelectedItem();
        if (ss == null) {

              alert.setAlertType(Alert.AlertType.WARNING);
           
              alert.setContentText(" Choix invalide ");
           
              alert.show();
                       }
        else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
               alert.setTitle("Confirmation ");
               alert.setHeaderText(null);
               alert.setContentText("Voulez-vous vraiment fichier pdf pour cette enfant?");
               Optional<ButtonType> action = alert.showAndWait();
               if (action.get() == ButtonType.OK) {
                 pdf(ss);

               }
        }
           
    }
   
     void pdf(Enfant m )
     {

try {
           //Path 
         OutputStream file = new FileOutputStream(new File("C:/Users/yanisinfo/Desktop/junior/"+m.getIdEnfant()+" "+m.getNom()+".pdf"));

            Document document = new Document();

            PdfWriter.getInstance(document, file);
          
            document.open();
            ServiceEnfant sP =new ServiceEnfant();
             document.add(new Paragraph("------------------------------------------------------------BIENVENU-------------------------------------------------------"));
             document.add(new Paragraph("Nom d'enfant :"+ sP.getById(m.getIdEnfant())));
             System.out.println(sP.getById(m.getIdEnfant()));
             document.add(new Paragraph("**************************************************************************"));
             document.add(new Paragraph(" Nom:  "+m.getNom()));
             document.add(new Paragraph("**************************************************************************"));
             document.add(new Paragraph(" Age:  "+m.getAge()));
             document.add(new Paragraph(" Titre du Dossier Medical:  "+m.getTitre()));
             document.add(new Paragraph("**************************************************************************"));
            // document.add(new Paragraph(" Description:  "+m.getDescription()));
             

            /* PdfPTable my_first_table = new PdfPTable(3);
             PdfPCell table_cell;
             table_cell=new PdfPCell(new Phrase("aza"));
              my_first_table.addCell(table_cell);
              table_cell=new PdfPCell(new Phrase("zaazd"));
               my_first_table.addCell(table_cell);
               table_cell=new PdfPCell(new Phrase(5));
                my_first_table.addCell(table_cell);*/
        
             // my_first_table.addCell(table_cell);
              // document.add(new Paragraph(getquantite(4)));

         //  document.add( my_first_table);    

  
            document.close();
             
            file.close();


        } catch (Exception e) {


            e.printStackTrace();


     
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
     void reset(ActionEvent event) {
    	clearFields();
    }
    
    
     
     
     
     
     
     
      @FXML
          private void pdfs() 
             throws Exception{
           try {
              Class.forName("com.mysql.jdbc.Driver");
                  Connection cnx = ConnectionBD.getInstance().getCnx();
     Statement stmt = cnx.createStatement();
                       /* Define the SQL query */
                       ResultSet query_set = stmt.executeQuery("SELECT enfant.id, enfant.nom,enfant.age ,dossier_medical.titre FROM `enfant`,dossier_medical WHERE dossier_medical.id=enfant.id_dossier;");
                       /* Step-2: Initialize PDF documents - logical objects */
                       Document my_pdf_report = new Document();
                       PdfWriter.getInstance(my_pdf_report, new FileOutputStream("C:/Users/yanisinfo/Desktop/junior/enfants.pdf"));
                       my_pdf_report.open();            
                       //we have four columns in our table
                       PdfPTable my_report_table = new PdfPTable(3);
                       //create a cell object
                       PdfPCell table_cell;
                        my_pdf_report.add(new Paragraph("------------------------------------------------------Liste Des Enfants-----------------------------------------------------"));
                       my_pdf_report.add(new Paragraph(" "));
                       my_pdf_report.add(new Paragraph(" "));
                       
                        my_report_table.addCell("Nom");
                        my_report_table.addCell("Age");
                        my_report_table.addCell("Titre du Dossier Medical");
                       while (query_set.next()) {                
                                      /* int dept_id = query_set.getInt("id");
                                       table_cell=new PdfPCell(new Phrase(dept_id));
                                       my_report_table.addCell(table_cell */
                                       String dept_name=query_set.getString("nom");
                                       table_cell=new PdfPCell(new Phrase(dept_name));
                                       my_report_table.addCell(table_cell);
                                       String manager_id=query_set.getString("age");
                                       table_cell=new PdfPCell(new Phrase(manager_id));
                                       my_report_table.addCell(table_cell);
                                       String location_id=query_set.getString("titre");
                                       table_cell=new PdfPCell(new Phrase(location_id));
                                       my_report_table.addCell(table_cell);
                                       }
                       /* Attach report table to PDF */
                       my_pdf_report.add(my_report_table);                       
                       my_pdf_report.close();

                       /* Close all DB related objects */
                       query_set.close();
                       stmt.close(); 
                       cnx.close();  
          TrayNotification tray = new TrayNotification("Done","La liste des Enfants est imprimée ! ", NotificationType.SUCCESS);
          tray.setAnimationType(AnimationType.SLIDE);
          tray.showAndDismiss(Duration.seconds(5));



       } catch (FileNotFoundException e) {
       // TODO Auto-generated catch block
       e.printStackTrace();
       } catch (DocumentException e) {
       // TODO Auto-generated catch block
       e.printStackTrace();
       }
     
     
     
     
          }  

     @FXML
    private void Retour(ActionEvent event) throws IOException {
        System.out.println(" Retour ");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/gui/Anis.fxml"));
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