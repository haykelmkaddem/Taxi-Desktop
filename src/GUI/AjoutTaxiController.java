/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import desktop.Service.Session;
import static desktop.Service.Session.getIdSession;
import desktop.Utils.DataBase;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author nefou
 */
public class AjoutTaxiController implements Initializable {

    @FXML
    private TextField mat;
    @FXML
    private TextField mar;
    
    
    Connection con = DataBase.getInstance().getConnection();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void CreateTaxi(MouseEvent event) throws SQLException {
        
            String req ="INSERT INTO `taxi` (`matricule`, `etat`, `marque`, `image`) VALUES ('"+mat.getText()+"', 'hors service', '"+mar.getText()+"', NULL);";
      PreparedStatement  ps = con.prepareStatement(req);
        ps.executeUpdate();
        
        String req1="UPDATE `utilisateurs` SET `taxi` = '"+mat.getText()+"' WHERE `utilisateurs`.`id` = "+Session.getIdSession()+";";
    PreparedStatement  ps2 = con.prepareStatement(req1);
        ps2.executeUpdate();
    }

    @FXML
    private void ToProfile(MouseEvent event) throws IOException {
                Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("ProfileChauffeur.fxml")));
                    stage.setScene(scene);
                    stage.show();
    }
    
}
