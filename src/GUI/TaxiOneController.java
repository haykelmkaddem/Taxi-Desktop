/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import desktop.Entite.Taxi;
import desktop.Service.ServiceFeedBack;
import desktop.Service.ServiceRate;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class TaxiOneController implements Initializable {

    @FXML
    private Circle part;
    @FXML
    private Rectangle image;
    @FXML
    private FontAwesomeIcon star1;
    @FXML
    private FontAwesomeIcon star2;
    @FXML
    private FontAwesomeIcon star3;
    @FXML
    private FontAwesomeIcon star4;
    @FXML
    private FontAwesomeIcon star5;
    
    int nbre=3;
    @FXML
    private HBox TaxiInfo;
    @FXML
    private HBox Separator;
    @FXML
    private Label matricule;
    @FXML
    private Label dispo;
    @FXML
    private Label marque;
    
    public ServiceRate SevRa=new ServiceRate();
    public ServiceFeedBack servF = new ServiceFeedBack();
    
    public void ActiveStar(FontAwesomeIcon f){
        f.getStyleClass().clear();
        f.getStyleClass().add("StarSelected");
    }
    public void DesactiveStar(FontAwesomeIcon f){
        f.getStyleClass().clear();
        f.getStyleClass().add("Star");
    }
    public void Rate(){
        if(nbre==0){
            DesactiveStar(star1);
            DesactiveStar(star2);
            DesactiveStar(star3);
            DesactiveStar(star4);
            DesactiveStar(star5);
        }
        else if(nbre==1){
            ActiveStar(star1);
            DesactiveStar(star2);
            DesactiveStar(star3);
            DesactiveStar(star4);
            DesactiveStar(star5);
        }
        else if(nbre==2){
            ActiveStar(star1);
            ActiveStar(star2);
            DesactiveStar(star3);
            DesactiveStar(star4);
            DesactiveStar(star5);
        }
        else if(nbre==3){
            ActiveStar(star1);
            ActiveStar(star2);
            ActiveStar(star3);
            DesactiveStar(star4);
            DesactiveStar(star5);
        }
        else if(nbre==4){
            ActiveStar(star1);
            ActiveStar(star2);
            ActiveStar(star3);
            ActiveStar(star4);
            DesactiveStar(star5);
        }
        else if(nbre==5){
            ActiveStar(star1);
            ActiveStar(star2);
            ActiveStar(star3);
            ActiveStar(star4);
            ActiveStar(star5);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        String chaine="star";
        Image im1= new Image("/photos/client-pic-1.jpg", false);
        Image im= new Image("/photos/car-2.jpg", false);
        part.setFill(new ImagePattern(im1));
        image.setFill(new ImagePattern(im));
        
    }    

    public void InitTaxi(Taxi t) throws SQLException{
        int taxi=servF.FindPart(t.getMatricule());
        nbre=SevRa.FindGeneralRate(taxi);
        System.out.println(taxi);
        matricule.setText(""+t.getMatricule());
        dispo.setText(t.getEtat());
        marque.setText(t.getMarque());
        Rate();
    }
    @FXML
    private void SeeMore(MouseEvent event) {
        Image im= new Image("/photos/car-4.jpg", false);
        part.setFill(new ImagePattern(im));
    }

    @FXML
    private void RateSelector1(MouseEvent event) {
        ActiveStar(star1);
        DesactiveStar(star2);
        DesactiveStar(star3);
        DesactiveStar(star4);
        DesactiveStar(star5);
    }

    @FXML
    private void RateExit1(MouseEvent event) {
        Rate();
    }

    @FXML
    private void RateExit2(MouseEvent event) {
        Rate();
    }

    @FXML
    private void RateSelector2(MouseEvent event) {
        ActiveStar(star1);
        ActiveStar(star2);
        DesactiveStar(star3);
        DesactiveStar(star4);
        DesactiveStar(star5);
    }

    @FXML
    private void RateExit3(MouseEvent event) {
        Rate();
    }

    @FXML
    private void RateSelector3(MouseEvent event) {
        ActiveStar(star1);
        ActiveStar(star2);
        ActiveStar(star3);
        DesactiveStar(star4);
        DesactiveStar(star5);
    }

    @FXML
    private void RateExit4(MouseEvent event) {
        Rate();
    }

    @FXML
    private void RateSelector4(MouseEvent event) {
        ActiveStar(star1);
        ActiveStar(star2);
        ActiveStar(star3);
        ActiveStar(star4);
        DesactiveStar(star5);
    }

    @FXML
    private void RateExit5(MouseEvent event) {
        Rate();
    }

    @FXML
    private void RateSelector5(MouseEvent event) {
        ActiveStar(star1);
        ActiveStar(star2);
        ActiveStar(star3);
        ActiveStar(star4);
        ActiveStar(star5);
    }

    
}
