/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class MainUiController implements Initializable {

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
    @FXML
    private Pane FeedMain;
    @FXML
    private Pane Contain;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        FeedMain.setVisible(true);
    }    


    @FXML
    private void GoToContact(MouseEvent event) throws IOException {
        List<Node> node_Contact = new ArrayList<>();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Contact.fxml"));
        Parent n = (Parent) loader.load();
        ContactController cont= loader.getController();
        Contain.getChildren().clear();
        Contain.getChildren().add(n);
    }

    @FXML
    private void GoToTaxi(MouseEvent event) throws IOException {
        List<Node> node_Contact = new ArrayList<>();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Taxi.fxml"));
        Parent n = (Parent) loader.load();
        TaxiController cont= loader.getController();
        Contain.getChildren().clear();
        Contain.getChildren().add(n);
    }

    @FXML
    private void GoToDetailTaxi(MouseEvent event) throws IOException {
        List<Node> node_Contact = new ArrayList<>();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("DetailTaxi.fxml"));
        Parent n = (Parent) loader.load();
        DetailTaxiController cont= loader.getController();
        Contain.getChildren().clear();
        Contain.getChildren().add(n);
    }

    @FXML
    private void GoToAllContact(MouseEvent event) throws IOException {
       
        
    }

    @FXML
    private void GoToAllRating(MouseEvent event) {
    }

    @FXML
    private void FeedMenu(MouseEvent event) {
        FeedMain.setVisible(true);
    }
    
    
     @FXML
    private void goToAcceuil(ActionEvent event) {
        
    }

       @FXML
    private void goToCourse(ActionEvent event) {
        
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
    

    
    
    
}
