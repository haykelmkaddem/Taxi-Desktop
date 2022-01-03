/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import static desktop.Service.Session.setIdSession;
import desktop.Utils.DataBase;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author nefou
 */
public class InscriptionController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private TextField tel;
    @FXML
    private Label login;
    @FXML
    private RadioButton roleClient;
    @FXML
    private RadioButton roleChauffeur;
    @FXML
    private Button register;

    /**
     * Initializes the controller class.
     */
    
    Connection con = DataBase.getInstance().getConnection();
    @FXML
    private ToggleGroup Roles;
    @FXML
    private TextField userName;
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
    private Hyperlink login1;
    @FXML
    private Hyperlink feedback;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void goToLogin(MouseEvent event) throws IOException {
                     
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Login.fxml")));
                    stage.setScene(scene);
                    stage.show();

          
    }

    @FXML
    private void register(MouseEvent event) throws SQLException, IOException {
        String leRole="";
         if(roleChauffeur.isSelected()){
            leRole="Chauffeur";
        }
        if(roleClient.isSelected()){
            leRole="Client";
        }
        
        
        String req ="INSERT INTO `utilisateurs` (`id`, `username`, `username_canonical`, `email`, `email_canonical`, `enabled`, `salt`, `password`, `last_login`, `confirmation_token`, `password_requested_at`, `roles`, `nom`, `prenom`, `telephone`) VALUES (NULL, '"+userName.getText()+"', NULL, '"+email.getText()+"', NULL, NULL, NULL, '"+password.getText()+"', NULL, NULL, NULL, '"+leRole+"', '"+nom.getText()+"', '"+prenom.getText()+"', '"+tel.getText()+"');";
      PreparedStatement  ps = con.prepareStatement(req);
        ps.executeUpdate();
        
       
       
       
       
                    if (leRole.equals("Client")){
                   Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("ProfileClient.fxml")));
                    stage.setScene(scene);
                    stage.show();
                
            }
                    if (leRole.equals("Chauffeur")){
                   Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("ProfileChauffeur.fxml")));
                    stage.setScene(scene);
                    stage.show();
                
            }
        
        
        
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
    private void FeedMenu(MouseEvent event) {
    }
    

    
}
