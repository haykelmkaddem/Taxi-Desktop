/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import desktop.Entite.Commentaires;
import desktop.Entite.Partenaire;
import desktop.Entite.Rating;
import desktop.Entite.Taxi;
import desktop.Entite.User;
import desktop.Service.CommentaireService;
import desktop.Service.ServiceFeedBack;
import desktop.Service.ServiceRate;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class DetailTaxiController implements Initializable {

    @FXML
    private Rectangle taxi;
    @FXML
    private Circle part;
    @FXML
    private Label date;
    @FXML
    private Label lieu;
    @FXML
    private Label contact;

    public ServiceFeedBack servF = new ServiceFeedBack();
    @FXML
    private Label matricule;
    @FXML
    private Label marque;
    @FXML
    private Label dispo;
    @FXML
    private Label nom;
    @FXML
    private Label prenom;
    @FXML
    private Label email;
    @FXML
    private Label tel;
    @FXML
    private JFXButton comment;
    @FXML
    private Pane CSection;
    @FXML
    private VBox liste_com;
    @FXML
    private FontAwesomeIcon SendBtn;

    ServiceFeedBack ServF = new ServiceFeedBack();
    CommentaireService ComS = new CommentaireService();
    @FXML
    private JFXTextArea Text;
    @FXML
    private FontAwesomeIcon etoile1;
    @FXML
    private FontAwesomeIcon etoile2;
    @FXML
    private FontAwesomeIcon etoile3;
    @FXML
    private FontAwesomeIcon etoile4;
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
    @FXML
    private FontAwesomeIcon etoile5;

    int nbre=3;
    int general=4;
    public ServiceRate SevRa=new ServiceRate();
    
    User client = new User(24, "username", "email", "role", "nom", "prenom");
    Taxi t = new Taxi(126, "etat", "marque", "image");
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lieu.setText(" Nouuur JAAFER");
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy  h:m:s");
        date.setText("" + formatter.format(new Date()));
        Image im1= new Image("/photos/client-pic-1.jpg", false);
        Image im= new Image("/photos/car-2.jpg", false);
        part.setFill(new ImagePattern(im1));
        taxi.setFill(new ImagePattern(im));
        CSection.setVisible(false);
        try {
            InitAll(t);
        } catch (SQLException ex) {
            Logger.getLogger(DetailTaxiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Time(MouseEvent event) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy  h:m:s");
        date.setText("" + formatter.format(new Date()));
    }

    public void InitAll(Taxi t) throws SQLException {
        
        int id_part = servF.FindPart(t.getMatricule());
        Partenaire P = servF.FindPartenaire(id_part);
        nom.setText(P.getNom());
        prenom.setText(P.getPrenom());
        email.setText(P.getEmail());
        tel.setText("" + P.getTel());
        System.out.println(id_part);
        matricule.setText("" + t.getMatricule());
        dispo.setText(t.getEtat());
        marque.setText(t.getMarque());
        
        nbre=SevRa.FindMyRate(id_part, client.getId()).getRate();
        Rate();
        GeneralRat();
    }

    @FXML
    private void SectionCommentaire(MouseEvent event) throws SQLException, IOException {
        liste_com.getChildren().clear();
        DisplayOne();
        CSection.setVisible(true);
    }

    @FXML
    private void EndComment(KeyEvent event) {
        if (event.getCode() == KeyCode.ESCAPE) {
            CSection.setVisible(false);
        }
    }

    public void DisplayOne() throws SQLException, IOException {

        ArrayList<Commentaires> TabC = ComS.FindCommentaire(1);
        ArrayList<Taxi> TabT = ServF.DisplayAll();
        for (Commentaires co : TabC) {
            FXMLLoader loader = new FXMLLoader();
            if (co.getUser() == client.getId()) {
                loader.setLocation(getClass().getResource("SingleComment.fxml"));
                Parent n = (Parent) loader.load();
                GUI.SingleCommentController com = loader.getController();
                com.Init(co);
                liste_com.getChildren().add(n);
            } else {
                loader.setLocation(getClass().getResource("MyComments.fxml"));
                Parent n = (Parent) loader.load();
                GUI.SingleCommentController com = loader.getController();
                com.Init(co);
                liste_com.getChildren().add(n);
            }

        }
    }

    @FXML
    private void SendComment(MouseEvent event) throws SQLException, IOException {
        System.out.println(client.getId());
        Commentaires c = new Commentaires(1,client.getId(),servF.FindPart(t.getMatricule()), Text.getText(), new Date());
        ComS.AddCommentaire(c);
        Text.setText("");
        liste_com.getChildren().clear();
        DisplayOne();
    }

    public void ActiveStar(FontAwesomeIcon f){
        f.getStyleClass().clear();
        f.getStyleClass().add("StarSelected");
    }
    public void DesactiveStar(FontAwesomeIcon f){
        f.getStyleClass().clear();
        f.getStyleClass().add("Star");
    }
    public void Rate(){
        switch (nbre) {
            case 0:
                DesactiveStar(star1);
                DesactiveStar(star2);
                DesactiveStar(star3);
                DesactiveStar(star4);
                DesactiveStar(star5);
                break;
            case 1:
                ActiveStar(star1);
                DesactiveStar(star2);
                DesactiveStar(star3);
                DesactiveStar(star4);
                DesactiveStar(star5);
                break;
            case 2:
                ActiveStar(star1);
                ActiveStar(star2);
                DesactiveStar(star3);
                DesactiveStar(star4);
                DesactiveStar(star5);
                break;
            case 3:
                ActiveStar(star1);
                ActiveStar(star2);
                ActiveStar(star3);
                DesactiveStar(star4);
                DesactiveStar(star5);
                break;
            case 4:
                ActiveStar(star1);
                ActiveStar(star2);
                ActiveStar(star3);
                ActiveStar(star4);
                DesactiveStar(star5);
                break;
            case 5:
                ActiveStar(star1);
                ActiveStar(star2);
                ActiveStar(star3);
                ActiveStar(star4);
                ActiveStar(star5);
                break;
            default:
                break;
        }
    }
    
    public void Rate2(){
        switch (general) {
            case 0:
                DesactiveStar(etoile1);
                DesactiveStar(etoile2);
                DesactiveStar(etoile3);
                DesactiveStar(etoile4);
                DesactiveStar(etoile5);
                break;
            case 1:
                ActiveStar(etoile1);
                DesactiveStar(etoile2);
                DesactiveStar(etoile3);
                DesactiveStar(etoile4);
                DesactiveStar(etoile5);
                break;
            case 2:
                ActiveStar(etoile1);
                ActiveStar(etoile2);
                DesactiveStar(etoile3);
                DesactiveStar(etoile4);
                DesactiveStar(etoile5);
                break;
            case 3:
                ActiveStar(etoile1);
                ActiveStar(etoile2);
                ActiveStar(etoile3);
                DesactiveStar(etoile4);
                DesactiveStar(etoile5);
                break;
            case 4:
                ActiveStar(etoile1);
                ActiveStar(etoile2);
                ActiveStar(etoile3);
                ActiveStar(etoile4);
                DesactiveStar(etoile5);
                break;
            case 5:
                ActiveStar(etoile1);
                ActiveStar(etoile2);
                ActiveStar(etoile3);
                ActiveStar(etoile4);
                ActiveStar(etoile5);
                break;
            default:
                break;
        }
    }
    
    public void GeneralRat() throws SQLException{
        general=SevRa.FindGeneralRate(servF.FindPart(t.getMatricule()));
        System.out.println(general);
        Rate2();
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

    @FXML
    private void ValidRate1(MouseEvent event) throws SQLException {
         Rating   R = SevRa.FindMyRate(ServF.FindPart(t.getMatricule()), client.getId());
         
        System.out.println(R.getRate());
        if(R.getRate() == 0){
            R.setUser(client.getId());
            R.setPart(ServF.FindPart(t.getMatricule()));
            R.setRate(1);
            SevRa.AddRting(R);
            nbre=R.getRate();
        }
        else{
            R.setRate(1);
            SevRa.UpdateRating(R);
            nbre=R.getRate();
        }
        Rate();
        GeneralRat();
    }

    @FXML
    private void ValidRate4(MouseEvent event) throws SQLException {
        Rating   R = SevRa.FindMyRate(ServF.FindPart(t.getMatricule()), client.getId());
         
        System.out.println(R.getRate());
        if(R.getRate() == 0){
            R.setUser(client.getId());
            R.setPart(ServF.FindPart(t.getMatricule()));
            R.setRate(4);
            SevRa.AddRting(R);
            nbre=R.getRate();
        }
        else{
            R.setRate(4);
            SevRa.UpdateRating(R);
            nbre=R.getRate();
        }
        Rate();
        GeneralRat();
    }

    @FXML
    private void ValidRate3(MouseEvent event) throws SQLException {
        Rating   R = SevRa.FindMyRate(ServF.FindPart(t.getMatricule()), client.getId());
         
        System.out.println(R.getRate());
        if(R.getRate() == 0){
            R.setUser(client.getId());
            R.setPart(ServF.FindPart(t.getMatricule()));
            R.setRate(3);
            SevRa.AddRting(R);
            nbre=R.getRate();
        }
        else{
            R.setRate(3);
            SevRa.UpdateRating(R);
            nbre=R.getRate();
        }
        Rate();
        GeneralRat();
    }

    @FXML
    private void ValidRate2(MouseEvent event) throws SQLException {
        Rating   R = SevRa.FindMyRate(ServF.FindPart(t.getMatricule()), client.getId());
         
        System.out.println(R.getRate());
        if(R.getRate() == 0){
            R.setUser(client.getId());
            R.setPart(ServF.FindPart(t.getMatricule()));
            R.setRate(2);
            SevRa.AddRting(R);
            nbre=R.getRate();
        }
        else{
            R.setRate(2);
            SevRa.UpdateRating(R);
            nbre=R.getRate();
        }
        Rate();
        GeneralRat();
    }

    @FXML
    private void ValidRate5(MouseEvent event) throws SQLException {
        
        Rating   R = SevRa.FindMyRate(ServF.FindPart(t.getMatricule()), client.getId());
         
        System.out.println(R.getRate());
        if(R.getRate() == 0){
            R.setUser(client.getId());
            R.setPart(ServF.FindPart(t.getMatricule()));
            R.setRate(5);
            SevRa.AddRting(R);
            nbre=R.getRate();
        }
        else{
            R.setRate(5);
            SevRa.UpdateRating(R);
            nbre=R.getRate();
        }
        Rate();
        GeneralRat();
    }

}
