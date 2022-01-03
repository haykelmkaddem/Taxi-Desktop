/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import desktop.Entite.Commission;
import desktop.Service.CommissionService;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
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
public class CommissionController implements Initializable {

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
    private Hyperlink voirInventaire;
    @FXML
    private VBox pnItems;
    @FXML
    private TableView<Commission> commission;
    @FXML
    private TableColumn<?, ?> commission_id;
    @FXML
    private TableColumn<?, ?> commission_depart;
    @FXML
    private TableColumn<?, ?> commission_destination;
    @FXML
    private TableColumn<?, ?> commission_prix;
    @FXML
    private TableColumn<?, ?> commission_pourcentage;
    private JFXHamburger hamburger;
    private JFXDrawer draw;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        
        
         //affichage
         commission_id.setCellValueFactory(new PropertyValueFactory<>("id"));
         commission_depart.setCellValueFactory(new PropertyValueFactory<>("depart"));
         commission_destination.setCellValueFactory(new PropertyValueFactory<>("destination"));
         commission_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
         commission_pourcentage.setCellValueFactory(new PropertyValueFactory<>("pourcentage"));
        // CoursesService cs= new CoursesService();
        CommissionService cs=new CommissionService();
        
         List<Commission> arrc=new ArrayList<>();
       
          try {
            arrc=cs.find(14);
        } catch (SQLException ex) {
            Logger.getLogger(CoursesController.class.getName()).log(Level.SEVERE, null, ex);
        }
       ObservableList<Commission> ovbservableList= FXCollections.observableArrayList(arrc);
       commission.setItems(ovbservableList);
       
       //Updatable
       // afficher_courses.setEditable(true);
         
        //afficher_id.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
       // afficher_courses_destination.setCellFactory(TextFieldTableCell.forTableColumn());
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
    private void goToInventaire(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Inventaire.fxml")));
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
}
