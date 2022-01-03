/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import desktop.Entite.Commentaires;
import desktop.Entite.Partenaire;
import desktop.Entite.Rating;
import desktop.Entite.ViewCommentaire;
import desktop.Entite.ViewRating;
import desktop.Service.CommentaireService;
import desktop.Service.ServiceFeedBack;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class AllCommentController implements Initializable {

    @FXML
    private VBox vbox;
    @FXML
    private Button clients1;
    @FXML
    private Button clients;
    @FXML
    private Button partenaires;
    @FXML
    private Button reservations;
    @FXML
    private Button reservations1;
    @FXML
    private Button abonnements;
    @FXML
    private Button feedback;
    @FXML
    private Button logout;
    @FXML
    private Pane pnlCustomer;
    @FXML
    private Pane pnlOrders;
    @FXML
    private Pane pnlOverview;
    @FXML
    private TextField search;
    @FXML
    private VBox pnItems;
    @FXML
    private TableView<?> tableview;
    @FXML
    private TableColumn<ViewCommentaire, String> user;
    @FXML
    private TableColumn<ViewCommentaire, String>  part;
    @FXML
    private TableColumn<ViewCommentaire, String>  comment;
    @FXML
    private TableColumn<ViewCommentaire, String>  date;
    @FXML
    private TableColumn<?, ?> actions;
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private Label total;
    FontAwesomeIcon f;
    @FXML
    private JFXDrawer draw;
    ServiceFeedBack ServF = new ServiceFeedBack();
    CommentaireService ServC = new CommentaireService();
    @FXML
    private FontAwesomeIcon delete;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        draw.setSidePane(vbox);

        HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);
        transition.setRate(-1);
        hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
            transition.setRate(transition.getRate() * -1);
            transition.play();

            if (draw.isOpened()) {

                draw.close();
            } else {
                draw.open();

            }
        });
        
        try {
            DisplayAll();
        } catch (SQLException ex) {
            Logger.getLogger(AllContactController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    } 

    public ArrayList<ViewCommentaire> CreateTab() throws SQLException{
        ArrayList<Commentaires> TabC = ServC.FindAll();
        
        ArrayList<ViewCommentaire> TabA = new ArrayList<>();
        for(Commentaires c : TabC){
            Partenaire P = ServF.FindUser(c.getUser());
            Partenaire P1 = ServF.FindPartenaire(c.getPart());
            System.out.println(P1.getNom());
            TabA.add(new ViewCommentaire(c.getId(),P.getNom()+" "+P.getPrenom(),P1.getNom()+" "+P1.getPrenom(), c.getCommentaire(),""+c.getDate()));
        }
        return TabA;
    }
    
    public void DisplayAll() throws SQLException{
        
        ArrayList<ViewCommentaire> TabA = CreateTab();
        ObservableList ViewRec = FXCollections.observableArrayList(TabA);
        tableview.setItems(ViewRec);
        user.setCellValueFactory(new PropertyValueFactory<>("user"));
        part.setCellValueFactory(new PropertyValueFactory<>("part"));
        comment.setCellValueFactory(new PropertyValueFactory<>("commentaire"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        
        this.total.setText(""+TabA.size());
    }
    @FXML
    private void handleClicks(ActionEvent event) {
    }

    @FXML
    private void SearchAll(KeyEvent event) throws SQLException {
        ArrayList<ViewCommentaire> TabA = CreateTab();
        ArrayList<ViewCommentaire> TabB = new ArrayList<>();
        for(ViewCommentaire r : TabA){
            if(r.getUser().toLowerCase().indexOf(search.getText().toLowerCase()) != -1
                    ||r.getPart().toLowerCase().indexOf(search.getText().toLowerCase()) != -1
                    ||r.getCommentaire().toLowerCase().indexOf(search.getText().toLowerCase()) != -1
                    || r.getDate().toLowerCase().indexOf(search.getText().toLowerCase()) != -1){
                
                TabB.add(new ViewCommentaire(r.getId(),r.getUser(),r.getPart(), r.getCommentaire(),r.getDate()));
        
        
            }
        }
        ObservableList ViewRec = FXCollections.observableArrayList(TabB);
        tableview.setItems(ViewRec);
        user.setCellValueFactory(new PropertyValueFactory<>("user"));
        part.setCellValueFactory(new PropertyValueFactory<>("part"));
        comment.setCellValueFactory(new PropertyValueFactory<>("commentaire"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        this.total.setText(""+TabB.size());
    }

    @FXML
    private void GoToAllRating(MouseEvent event) throws IOException {
        Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("AllRating.fxml")));
                    stage.setScene(scene);
                    stage.show();
    }

    @FXML
    private void GoToAllContact(MouseEvent event) throws IOException {
        Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("AllContact.fxml")));
                    stage.setScene(scene);
                    stage.show();
    }
    
}
