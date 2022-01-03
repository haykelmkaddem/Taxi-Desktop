/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import desktop.Entite.Commentaires;
import desktop.Entite.Partenaire;
import desktop.Service.ServiceFeedBack;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class SingleCommentController implements Initializable {

    @FXML
    private Label username;
    @FXML
    private Label date;
    @FXML
    private Label contain;
    @FXML
    private Circle user;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Image im1= new Image("/photos/client-pic-1.jpg", false);
        user.setFill(new ImagePattern(im1));
    }    
    
    public  void Init(Commentaires c) throws SQLException{
        
        ServiceFeedBack S = new ServiceFeedBack();
        Partenaire P= S.FindUser(c.getUser());
       
        String Us = P.getPrenom()+" "+P.getNom();
        username.setText(Us.toUpperCase());
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        date.setText(""+formatter.format(c.getDate()));
        contain.setText(c.getCommentaire().toLowerCase());
    }
}
