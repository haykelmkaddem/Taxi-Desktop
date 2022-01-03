/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import desktop.Entite.reservation;
import desktop.Service.ReservationSession;
import desktop.Service.ServiceReservation;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author anest
 */
public class ReservationDetailTraitedController implements Initializable {

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
    private JFXHamburger hamburger;
    @FXML
    private Hyperlink linkTotraited;
    @FXML
    private Label Numero;
    @FXML
    private Hyperlink linkTonottraited;
    @FXML
    private TableView<?> tableview;
    @FXML
    private TableColumn<?, ?> produit;
    @FXML
    private TableColumn<?, ?> pointA;
    @FXML
    private TableColumn<?, ?> Destination;
    @FXML
    private TableColumn<?, ?> Remarques;
    @FXML
    private Label prix;
    @FXML
    private Label prix1;
    @FXML
    private Label date;
    @FXML
    private Label nom;
    @FXML
    private Label prenom;
    @FXML
    private Label mobile;
    private JFXDrawer draw;

    private int id_resrvation = 0;
    ReservationSession RS = ReservationSession.getInstance();
    @FXML
    private Hyperlink linkTonottraited1;
    @FXML
    private Hyperlink linkTonottraited11;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
  

        id_resrvation = RS.getId_reservation();

 
 
        try {
            displayAll();
            addButtonToTable();
            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(ReservationFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public void displayAll() throws SQLException {

        ServiceReservation sr = new ServiceReservation();
        List listcs = sr.details(id_resrvation);
        reservation R = sr.details2(id_resrvation);

        int number = sr.counttraited();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String strDate = dateFormat.format(R.getDate_reservation());
          System.out.println("non"+R.getClient());
nom.setText(R.getClient().getNom());
prenom.setText(R.getClient().getPrenom());
mobile.setText(R.getClient().getTelephone());
 
        date.setText(strDate);
        prix1.setText(Float.toString(R.getPrix()));
        prix.setText(Float.toString(R.getPrix()));
        //listcs.forEach(System.out::println);
        Numero.setText(Integer.toString(R.getId()));
        ObservableList listReserv = FXCollections.observableArrayList(listcs);

        tableview.setItems(listReserv);

        pointA.setCellValueFactory(new PropertyValueFactory<>("pointAchat"));
        Destination.setCellValueFactory(new PropertyValueFactory<>("destination"));
        produit.setCellValueFactory(new PropertyValueFactory<>("listAchats"));
        Remarques.setCellValueFactory(new PropertyValueFactory<>("remarques"));

    }

    private void addButtonToTable() throws SQLException {

        linkTonottraited.setOnAction((ActionEvent event) -> {
            Parent page2;
            try {
                RS.cleanUserSession();
                page2 = FXMLLoader.load(getClass().getResource("/GUI/ReservationFXML.fxml"));
                Scene scene2 = new Scene(page2);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(scene2);
                window.show();

            } catch (IOException ex) {
                Logger.getLogger(ReservationFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        linkTotraited.setOnAction((ActionEvent event) -> {
            Parent page2;
            try {
                RS.cleanUserSession();
                page2 = FXMLLoader.load(getClass().getResource("/GUI/ReservationTraited.fxml"));
                Scene scene2 = new Scene(page2);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(scene2);
                window.show();

            } catch (IOException ex) {
                Logger.getLogger(ReservationFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
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
 
    }
    
    private void goToFront(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Login.fxml")));
                    stage.setScene(scene);
                    stage.show();
    }
    
                 @FXML
    private void InventairenotPaid(ActionEvent event) throws IOException {
                Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("InventaireR.fxml")));
                    stage.setScene(scene);
                    stage.show();
    }
            @FXML
    private void InventairPaid(ActionEvent event) throws IOException {
                Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("InventaireR2.fxml")));
                    stage.setScene(scene);
                    stage.show();
    }
}
