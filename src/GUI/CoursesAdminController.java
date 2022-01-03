/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import desktop.Entite.Courses;
import desktop.Entite.User;
import desktop.Service.CoursesService;
import desktop.Service.UserService;
/*import com.nexmo.client.NexmoClient;
import com.nexmo.client.sms.MessageStatus;
import com.nexmo.client.sms.SmsSubmissionResponse;
import com.nexmo.client.sms.messages.TextMessage;*/

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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;


/**
 * FXML Controller class
 *
 * @author anest
 */
public class CoursesAdminController implements Initializable {

    @FXML
    private VBox vbox;
    @FXML
    private Button clients1;
    @FXML
    private Button clients;
    @FXML
    private Button partenaires;
    @FXML
    private Button reservations;
    @FXML
    private Button reservations1;
    @FXML
    private Button abonnements;
    @FXML
    private Button feedback;
    @FXML
    private Button logout;
    @FXML
    private Pane pnlCustomer;
    @FXML
    private Pane pnlOrders;
    @FXML
    private Pane pnlOverview;
    @FXML
    private TextField rech;
 
    @FXML
    private VBox pnItems;
    @FXML
    private TableView<Courses> afficher_courses;
    @FXML
    private TableColumn<?, ?> afficher_courses_id;
    @FXML
    private TableColumn<Courses, User> afficher_courses_client;
    @FXML
    private TableColumn<?, ?> afficher_courses_partenaire;
    @FXML
    private TableColumn<?, ?> afficher_courses_depart;
    @FXML
    private TableColumn<?, String> afficher_courses_destination;
    @FXML
    private TableColumn<?, ?> afficher_courses_prix;
    private JFXHamburger hamburger;
    @FXML
    private Button delete_course;
    private JFXDrawer draw;
    @FXML
    private Hyperlink voirInventaire;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

       //Creating custom cell forr combobox
    /* Callback<ListView<User>, ListCell<User>> cellFactory = new Callback<ListView<User>, ListCell<User>>() {

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
            Logger.getLogger(CoursesAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        for(User u: arr)
        {
            partenaire_ajout.getItems().add(u);
        }
         //partenaire_ajout.setOnAction((ActionEvent e)->System.out.println(partenaire_ajout.getValue().getNom()));
        */
         //affichage
         afficher_courses_id.setCellValueFactory(new PropertyValueFactory<>("id"));
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
            Logger.getLogger(CoursesAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
       ObservableList<Courses> ovbservableList= FXCollections.observableArrayList(arrc);
       afficher_courses.setItems(ovbservableList);
       
       //Updatable
        afficher_courses.setEditable(true);
         
        //afficher_id.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        afficher_courses_destination.setCellFactory(TextFieldTableCell.forTableColumn());
    }    

    /* @FXML
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

         List<Courses> arrc=new ArrayList<>();
       
          try {
            arrc=cs.readAll();
        } catch (SQLException ex) {
            Logger.getLogger(CoursesAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<Courses> ovbservableList= FXCollections.observableArrayList(arrc);
       afficher_courses.setItems(ovbservableList);
       
       NexmoClient client = NexmoClient.builder().apiKey("a7c8d346").apiSecret("06RtyiF7aVUXE90L").build();
  TextMessage message = new TextMessage("Reservation de taxi",
                   "+21698604435",
                    "Votre reservation de taxi a été effectué avec succes"
            );

SmsSubmissionResponse response = client.getSmsClient().submitMessage(message);
  if (response.getMessages().get(0).getStatus() == MessageStatus.OK) {
    System.out.println("Message sent successfully.");
} else {
    System.out.println("Message failed with error: " + response.getMessages().get(0).getErrorText());
}
        
  
        
        
    }*/

    @FXML
    private void deleteCourse(ActionEvent event) throws SQLException {
        CoursesService cs= new CoursesService();
        Courses c=afficher_courses.getSelectionModel().getSelectedItem();
        cs.Delete(c.getId());
         afficher_courses.getItems().removeAll(afficher_courses.getSelectionModel().getSelectedItem());
    }

    
    
    private void voirInventaire(ActionEvent event) throws IOException
    {
        Parent page2;
        page2 = FXMLLoader.load(getClass().getResource("/GUI/Inventaire.fxml"));
        Scene scene2= new Scene(page2);
        Stage window= (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene2);
        window.show();
        
        
        
     
           
        
    }

    
    private void ChercherCourses(KeyEvent event) {
        
       // System.out.println();
       CoursesService cs=new CoursesService();
       
               List<Courses> arrc=new ArrayList<>();
       
          try {
            arrc=cs.ChercherCourses(rech.getText());
        } catch (SQLException ex) {
            Logger.getLogger(CoursesAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<Courses> ovbservableList= FXCollections.observableArrayList(arrc);
       afficher_courses.setItems(ovbservableList);
    }



              @FXML
    private void goToInventaire(ActionEvent event) throws IOException {
            Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Inventaire.fxml")));
                    stage.setScene(scene);
                    stage.show();
    }  
 

  

    @FXML
    private void ChercherCourses(ActionEvent event) {
          CoursesService cs=new CoursesService();
       
               List<Courses> arrc=new ArrayList<>();
       
          try {
            arrc=cs.ChercherCourses(rech.getText());
        } catch (SQLException ex) {
            Logger.getLogger(CoursesAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<Courses> ovbservableList= FXCollections.observableArrayList(arrc);
       afficher_courses.setItems(ovbservableList);
    }


    @FXML
    private void ChercherCourses(InputMethodEvent event) {
        CoursesService cs=new CoursesService();
       
               List<Courses> arrc=new ArrayList<>();
       
          try {
            arrc=cs.ChercherCourses(rech.getText());
        } catch (SQLException ex) {
            Logger.getLogger(CoursesAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<Courses> ovbservableList= FXCollections.observableArrayList(arrc);
       afficher_courses.setItems(ovbservableList);
    }

  

    

    @FXML
    private void updateDestination(TableColumn.CellEditEvent<Courses, String> event) throws SQLException {
                      Courses c=afficher_courses.getSelectionModel().getSelectedItem();
         c.setDestination(event.getNewValue());
        CoursesService cl= new CoursesService();
        cl.Update(c.getId(), c.getDestination());
    }

       @FXML
    private void goToAcceuil(ActionEvent event) {
        
    }

          @FXML
    private void goToclients(ActionEvent event) throws IOException {
       
    }
               @FXML
    private void goToPartenaire(ActionEvent event) throws IOException {
       
    }  
    
             @FXML
    private void goToCourse(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("CoursesAdmin.fxml")));
                    stage.setScene(scene);
                    stage.show();
    }
    
                 @FXML
    private void goToReservation(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("ReservationFXML.fxml")));
                    stage.setScene(scene);
                    stage.show();
    }
      @FXML
    private void goToAbonne(ActionEvent event) {
        
    }

    
        

               @FXML
    private void goToFeedBack(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("AllContact.fxml")));
                    stage.setScene(scene);
                    stage.show();
 
    }
    
    private void goToFront(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Login.fxml")));
                    stage.setScene(scene);
                    stage.show();
    }



 
}
