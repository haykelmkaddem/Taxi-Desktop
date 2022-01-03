/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import desktop.Service.Session;
import static desktop.Service.Session.setIdSession;
import desktop.Utils.DataBase;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author nefou
 */
public class LoginController implements Initializable {

    @FXML
    private TextField user;
    @FXML
    private PasswordField pw;
    @FXML
    private Label check;
    @FXML
    private Button log;
    @FXML
    private Label register;
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
        // TODO
    } 
    
    Connection con = DataBase.getInstance().getConnection();
    


    @FXML
    private void Login(javafx.scene.input.MouseEvent event) throws SQLException, IOException {
        String role = "";
         String req = "SELECT * FROM utilisateurs Where email = ? and password ='"+pw.getText()+"'";
       
       PreparedStatement ps = con.prepareStatement(req);
        ps.setString(1, user.getText());
        
       ResultSet rs = ps.executeQuery();

        if (!rs.next()) {
            check.setTextFill(Color.TOMATO);
            check.setText("Wrong Email/password");
      
        } else { 
            check.setTextFill(Color.GREEN);
            check.setText("Logging Succesfull..Redirecting..");
            role=rs.getString("roles");
            int k = rs.getInt("id");
            setIdSession(k);
            
            
            if (role.equals("Client")){
                   Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("ProfileClient.fxml")));
                    stage.setScene(scene);
                    stage.show();
                
            }
                        if (role.equals("Chauffeur")){
                   Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("ProfileChauffeur.fxml")));
                    stage.setScene(scene);
                    stage.show();
                
            }
             if (role.equals("admin")){
                   Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("ProfileAdmin.fxml")));
                    stage.setScene(scene);
                    stage.show();
                
            }
            
            
            
            
            
            

        }
        
    }

    @FXML
    private void Register(javafx.scene.input.MouseEvent event) throws IOException {
        Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Inscription.fxml")));
                    stage.setScene(scene);
                    stage.show();
    }

    
     @FXML
    private void goToAcceuil(ActionEvent event) {
        
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
    

    
}
