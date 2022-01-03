/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import desktop.Entite.Taxi;
import desktop.Service.ServiceFeedBack;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class TaxiController implements Initializable {

    @FXML
    private VBox liste_taxi;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            DisplayOne();
        } catch (SQLException ex) {
            Logger.getLogger(TaxiController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TaxiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    public void DisplayOne() throws SQLException, IOException{
        ServiceFeedBack ServF =new ServiceFeedBack();
        ArrayList<Taxi> TabT = ServF.DisplayAll();
        
        for(Taxi t : TabT){
            System.out.println("Ok");
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("TaxiOne.fxml"));
            Parent n = (Parent) loader.load();
            GUI.TaxiOneController tax = loader.getController();
            tax.InitTaxi(t);
            liste_taxi.getChildren().add(n);
        }
    }
}
