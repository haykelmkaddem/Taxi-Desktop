/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import desktop.Entite.Courses;
import desktop.Entite.User;
import desktop.Service.CoursesService;
import desktop.Service.UserService;
import com.nexmo.client.NexmoClient;
import com.nexmo.client.sms.MessageStatus;
import com.nexmo.client.sms.SmsSubmissionResponse;
import com.nexmo.client.sms.messages.TextMessage;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.Callback;
//NOTIF
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;




/**
 * FXML Controller class
 *
 * @author anest
 */
public class CoursesController implements Initializable {

    @FXML
    private JFXTextField depart_ajout;
    @FXML
    private JFXComboBox<User> partenaire_ajout;
    @FXML
    private JFXButton ajout;
    @FXML
    private JFXTextField destination_ajout;
    @FXML
    private DatePicker date_ajout_courses;
    @FXML
    private Label success;
    @FXML
    private Label error;
    @FXML
    private Hyperlink admin;
    @FXML
    private Hyperlink Accueil;
    @FXML
    private Hyperlink profil;
    @FXML
    private Hyperlink reservation;
    @FXML
    private Hyperlink course;
    @FXML
    private Hyperlink abonnement;
    @FXML
    private Hyperlink login;
    @FXML
    private Hyperlink feedback;

    /**
     * Initializes the controller class.
     */
  @Override
    public void initialize(URL url, ResourceBundle rb) {
      
                ajout.setDisable(true);
        date_ajout_courses.setValue(LocalDate.now());
date_ajout_courses.setDayCellFactory(picker -> new DateCell() {
        public void updateItem(LocalDate date, boolean empty) {
            super.updateItem(date, empty);
            LocalDate today = LocalDate.now();

            setDisable(empty || date.compareTo(today) < 0 );
        }
    });



              destination_ajout.textProperty().addListener((observable, oldValue, newValue) -> {
        if (!newValue.matches("\\sa-zA-Z*")) {
            destination_ajout.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
            
        }
             if(destination_ajout.getText().length()==0 || depart_ajout.getText().length()==0 ||partenaire_ajout.getValue()==null || date_ajout_courses.getValue()==null )
                      {
           ajout.setDisable(true);
                      }
              else {  ajout.setDisable(false);}
    });
              
                         depart_ajout.textProperty().addListener((observable, oldValue, newValue) -> {
        if (!newValue.matches("\\sa-zA-Z*")) {
            depart_ajout.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
            
        }
             if(depart_ajout.getText().length()==0 ||destination_ajout.getText().length()==0 || partenaire_ajout.getValue()==null || date_ajout_courses.getValue()==null )
                      {
           ajout.setDisable(true);
                      }
              else {  ajout.setDisable(false);}
    });
    partenaire_ajout.valueProperty().addListener((ov, oldValue, newValue) -> {
                 if(newValue==null ||depart_ajout.getText().length()==0  || destination_ajout.getText().length()==0 ||date_ajout_courses.getValue()==null)
                 {
                     ajout.setDisable(true);
                }
                 
                
                  
                 else
                 ajout.setDisable(false);
                
            
        });
            date_ajout_courses.valueProperty().addListener((ov, oldValue, newValue) -> {
                 if(date_ajout_courses.getValue()==null|| depart_ajout.getText().length()==0 ||newValue==null || destination_ajout.getText().length()==0)
                 {
                     ajout.setDisable(true);
                }
                 
                
             
                 else
                 ajout.setDisable(false);
                
            
        });
        
        
     Callback<ListView<User>, ListCell<User>> cellFactory = new Callback<ListView<User>, ListCell<User>>() {

    @Override
    public ListCell<User> call(ListView<User> l) {
        return new ListCell<User>() {

            @Override
            protected void updateItem(User item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setGraphic(null);
                } else {
                    setText(item.getId() + "    " + item.getNom());
                }
            }
        } ;
    }
};

// Just set the button cell here:
partenaire_ajout.setButtonCell(cellFactory.call(null));
partenaire_ajout.setCellFactory(cellFactory);
 //remplissage du combobox
        UserService us= new UserService();
         List<User> arr=new ArrayList<>();
        try {
            arr=us.findPartenaires();
        } catch (SQLException ex) {
            Logger.getLogger(CoursesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        for(User u: arr)
        {
            partenaire_ajout.getItems().add(u);
        }
         //partenaire_ajout.setOnAction((ActionEvent e)->System.out.println(partenaire_ajout.getValue().getNom()));
        
         //affichage
       /*  afficher_courses_id.setCellValueFactory(new PropertyValueFactory<>("id"));
         afficher_courses_client.setCellValueFactory(new PropertyValueFactory<>("nomclient"));
         afficher_courses_partenaire.setCellValueFactory(new PropertyValueFactory<>("nompartenaire"));
         afficher_courses_depart.setCellValueFactory(new PropertyValueFactory<>("depart"));
         afficher_courses_destination.setCellValueFactory(new PropertyValueFactory<>("destination"));
         afficher_courses_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
         CoursesService cs= new CoursesService();
         List<Courses> arrc=new ArrayList<>();
       
          try {
            arrc=cs.readAll();
        } catch (SQLException ex) {
            Logger.getLogger(CoursesController.class.getName()).log(Level.SEVERE, null, ex);
        }
       ObservableList<Courses> ovbservableList= FXCollections.observableArrayList(arrc);
       afficher_courses.setItems(ovbservableList);
       
       //Updatable
        afficher_courses.setEditable(true);
         
        //afficher_id.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        afficher_courses_destination.setCellFactory(TextFieldTableCell.forTableColumn());
         
         */
        
    }    
 @FXML
    private void AjouterCourse(ActionEvent event) throws SQLException {
        User Partenaire= partenaire_ajout.getValue();
        System.out.println(Partenaire.getEmail());
        String depart= depart_ajout.getText();
        String destination= destination_ajout.getText();
        LocalDate date_c= date_ajout_courses.getValue();
       // Date d = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
       LocalDateTime date_cf=date_c.atTime(11,10);
       Courses a=new Courses(1,Partenaire,Partenaire,depart,depart,date_cf,0);
        System.out.println(date_cf);
        //Courses c=new Courses(1,null,Partenaire,depart,destination,date_cf,0);
        CoursesService cs= new CoursesService();
       cs.Ajouter(a);
        depart_ajout.setText("");
        destination_ajout.setText("");
        
        
        //update afficher

    /*     List<Courses> arrc=new ArrayList<>();
       
          try {
            arrc=cs.readAll();
        } catch (SQLException ex) {
            Logger.getLogger(CoursesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<Courses> ovbservableList= FXCollections.observableArrayList(arrc);
       afficher_courses.setItems(ovbservableList);*/
       
       /*NexmoClient client = NexmoClient.builder().apiKey("a7c8d346").apiSecret("06RtyiF7aVUXE90L").build();
  TextMessage message = new TextMessage("Reservation de taxi",
                   "+21698604435",
                    "Votre reservation de taxi a été effectué avec succes"
            );

SmsSubmissionResponse response = client.getSmsClient().submitMessage(message);
  if (response.getMessages().get(0).getStatus() == MessageStatus.OK) {
    System.out.println("Message sent successfully.");
} else {
    System.out.println("Message failed with error: " + response.getMessages().get(0).getErrorText());
}*/
       
       
       Image img = new Image("/Img/todo.jpg");
                              Notifications n = Notifications.create()
                              .title("SUCCESS")
                              .text("Reservation de taxi avec succes")
                              .graphic(new ImageView(img))
                              .position(Pos.TOP_CENTER)
                             .hideAfter(Duration.seconds(2));
               n.darkStyle();
               n.show();
        
  
        
        
    }

  /*  @FXML
    private void deleteCourse(ActionEvent event) throws SQLException {
        CoursesService cs= new CoursesService();
        Courses c=afficher_courses.getSelectionModel().getSelectedItem();
        cs.Delete(c.getId());
         afficher_courses.getItems().removeAll(afficher_courses.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void updateDestination(TableColumn.CellEditEvent<Courses, String> event) throws SQLException {
         Courses c=afficher_courses.getSelectionModel().getSelectedItem();
         c.setDestination(event.getNewValue());
        CoursesService cl= new CoursesService();
        cl.Update(c.getId(), c.getDestination());
         
        
    }*/
    
    
    private void voirInventaire(ActionEvent event) throws IOException
    {
        Parent page2;
        page2 = FXMLLoader.load(getClass().getResource("/GUI/CoursesAdmin.fxml"));
        Scene scene2= new Scene(page2);
        Stage window= (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene2);
        window.show();
        
        
        
     
           
        
    }
    
 
    
    @FXML
    private void goToAcceuil(ActionEvent event) throws IOException {
          Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Acceuil.fxml")));
                    stage.setScene(scene);
                    stage.show();
    }

       @FXML
    private void goToCourse(ActionEvent event) throws IOException {
           Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Courses.fxml")));
                    stage.setScene(scene);
                    stage.show();
    }
       
      @FXML
    private void goToAbonne(ActionEvent event) {
        
    }
          @FXML
    private void goToProfil(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("GestionProfile.fxml")));
                    stage.setScene(scene);
                    stage.show();
    }
    
        
          @FXML
    private void goToReservation(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("AjoutReservation.fxml")));
                    stage.setScene(scene);
                    stage.show();
    }
  
        
          @FXML
    private void goToLogin(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Login.fxml")));
                    stage.setScene(scene);
                    stage.show();
    }

    
            @FXML
    private void goToFeedBack(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("MainUi.fxml")));
                    stage.setScene(scene);
                    stage.show();
    }
    
            @FXML
    private void goToAdmin(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("ReservationFXML.fxml")));
                    stage.setScene(scene);
                    stage.show();
    }

    @FXML
    private void FeedMenu(javafx.scene.input.MouseEvent event) {
    }
    
    @FXML
    private void Reset()
{
    partenaire_ajout.setValue(null);
    depart_ajout.setText(null);
 
    destination_ajout.setText(null);
    date_ajout_courses.setValue(LocalDate.now());
}

    


    
}
