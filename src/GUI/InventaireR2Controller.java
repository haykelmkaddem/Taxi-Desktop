/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import desktop.Entite.InventaireR;
import desktop.Entite.User;
import desktop.Entite.reservation;
import desktop.Service.ReservationSession;
import desktop.Service.ServiceInventaire;
import desktop.Service.ServiceReservation;
import desktop.Service.ServiceUtilisateur;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author anest
 */
public class InventaireR2Controller implements Initializable {

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
    private VBox pnItems;
    @FXML
    private TableView<InventaireR> tableview;
    @FXML
    private TableColumn<InventaireR, String> Montant;
    private JFXHamburger hamburger;
    private JFXDrawer draw;
    @FXML
    private TableColumn<?, ?> PartenaireID;
    @FXML
    private TableColumn<?, ?> Date;
    @FXML
    private Hyperlink InvPaid;
    @FXML
    private Hyperlink linkTotraited;
    @FXML
    private Hyperlink linkTonottraited;

    /**
     * Initializes the controller class.
     */
      @Override
    public void initialize(URL url, ResourceBundle rb) {


        try {
            displayAll();
        
            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(InventaireRController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
        public void displayAll() throws SQLException {

            ServiceInventaire sr = new ServiceInventaire();
        List listcs = sr.readpaid();
   

        ObservableList listInv = FXCollections.observableArrayList(listcs);

        tableview.setItems(listInv);
  
        PartenaireID.setCellValueFactory(new PropertyValueFactory<>("nompartenaire"));
        Montant.setCellValueFactory(new PropertyValueFactory<>("Montant"));
        Date.setCellValueFactory(new PropertyValueFactory<>("date_inventaire"));

                linkTonottraited.setOnAction((ActionEvent event) -> {
            Parent page2;
            try {
    
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
    private void InventairenotPaid(ActionEvent event) throws IOException {
                Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("InventaireR.fxml")));
                    stage.setScene(scene);
                    stage.show();
    }

    @FXML
    private void goToFeedBack(ActionEvent event) {
    }
}
