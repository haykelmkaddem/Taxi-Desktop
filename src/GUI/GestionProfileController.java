/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import desktop.Service.Session;
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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author nefou
 */
public class GestionProfileController implements Initializable {

    @FXML
    private TextField nvEmail;
    @FXML
    private TextField nvUsername;
    @FXML
    private TextField nvTel;
    @FXML
    private PasswordField oldpw;
    @FXML
    private PasswordField nvpw;
    @FXML
    private Label checkpw;
    
       Connection con = DataBase.getInstance().getConnection();
    @FXML
    private Label checkemail;
    @FXML
    private Label checkuser;
    @FXML
    private Label checktel;
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

    @FXML
    private void changeEmail(MouseEvent event) throws SQLException {
                String req2 ="UPDATE `utilisateurs` SET `email` = '"+nvEmail.getText()+"' WHERE `utilisateurs`.`id` = "+Session.getIdSession()+";";
        java.sql.PreparedStatement ps2 = con.prepareStatement(req2);
        ps2.executeUpdate();
       checkemail.setTextFill(Color.GREEN);
            checkemail.setText("Email changed successfully !");
    }

    @FXML
    private void changeUsername(MouseEvent event) throws SQLException {
        
                String req2 ="UPDATE `utilisateurs` SET `username` = '"+nvUsername.getText()+"' WHERE `utilisateurs`.`id` = "+Session.getIdSession()
                        +";";
        java.sql.PreparedStatement ps3 = con.prepareStatement(req2);
        ps3.executeUpdate();
        checkuser.setTextFill(Color.GREEN);
            checkuser.setText("Username changed successfully !");
        
    }

    @FXML
    private void changeTel(MouseEvent event) throws SQLException {
                String req2 ="UPDATE `utilisateurs` SET `telephone` = '"+nvTel.getText()+"' WHERE `utilisateurs`.`id` = "+Session.getIdSession()
                        +";";
        java.sql.PreparedStatement ps3 = con.prepareStatement(req2);
        ps3.executeUpdate();
        checktel.setTextFill(Color.GREEN);
             checktel.setText("Cellphone number changed successfully !");
    }

    @FXML
    private void changePW(MouseEvent event) throws SQLException {
        
        
               String oldpassword="";
       
        String request0 ="SELECT * from `utilisateurs` WHERE `utilisateurs`.`id` = "+Session.getIdSession()+";";
        java.sql.PreparedStatement ps0 = con.prepareStatement(request0);
        ResultSet rs0 = ps0.executeQuery();

        if (rs0.next()) {
            oldpassword = rs0.getString("password");
        }
        
        if(oldpw.getText().equals(oldpassword)){
        
        String req ="UPDATE `utilisateurs` SET `password` = '"+nvpw.getText()+"' WHERE `utilisateurs`.`id` = "+Session.getIdSession()+";";
        
        
        
     PreparedStatement ps = con.prepareStatement(req);
      ps.executeUpdate();
      
      checkpw.setTextFill(Color.GREEN);
            checkpw.setText("Password changed successfully !");
      
        //    System.out.println("PW CHNGED");
        }
        else {
           checkpw.setTextFill(Color.TOMATO);
            checkpw.setText("Wrong password !");
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
