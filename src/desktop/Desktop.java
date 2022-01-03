    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktop;
import desktop.Entite.InventaireR;
import desktop.Entite.commissionR;
import desktop.Entite.reservation;
import desktop.Utils.DataBase;
import desktop.Service.ServiceReservation;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.sql.SQLException;
import java.sql.*;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import desktop.Utils.DataBase;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.StageStyle;

/**
 *
 * @author anest
 */
public class Desktop extends Application {

    
    public static Stage stage = null;
    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/GUI/Acceuil.fxml"));
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
       
        stage.show();
       
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        launch(args);
        
         

     
       

        //pr.ajouter();
      //  pr.delete(12);
      //  pr.readAll().forEach(System.out::println);
    }
    
}
