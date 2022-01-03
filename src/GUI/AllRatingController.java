/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import desktop.Entite.Partenaire;
import desktop.Entite.Rating;
import desktop.Entite.Reclammations;
import desktop.Entite.ViewRating;
import desktop.Entite.ViewReclammation;
import desktop.Service.ServiceFeedBack;
import desktop.Service.ServiceRate;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class AllRatingController implements Initializable {

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
    private TableColumn<ViewRating, String> user;
    @FXML
    private TableColumn<ViewRating, String> part;
    @FXML
    private TableColumn<ViewRating, Integer> rate;
    @FXML
    private TableColumn<ViewRating, String> date;
    @FXML
    private TableColumn<?, ?> actions;
    private JFXHamburger hamburger;
    @FXML
    private Label total;
    private JFXDrawer draw;

    ServiceFeedBack ServF = new ServiceFeedBack();
    ServiceRate ServRa = new ServiceRate();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        
        try {
            DisplayAll();
        } catch (SQLException ex) {
            Logger.getLogger(AllContactController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    

    public ArrayList<ViewRating> CreateTab() throws SQLException{
        ArrayList<Rating> TabR = ServRa.FindAll();
        
        ArrayList<ViewRating> TabA = new ArrayList<>();
        for(Rating r : TabR){
            Partenaire P = ServF.FindUser(r.getUser());
            Partenaire P1 = ServF.FindPartenaire(r.getPart());
            System.out.println(P1.getNom());
            TabA.add(new ViewRating(r.getId(),P.getNom()+" "+P.getPrenom(), P1.getNom()+" "+P1.getPrenom(),r.getRate(),""+r.getDate()));
        }
        return TabA;
    }
    public void DisplayAll() throws SQLException{
        
        ArrayList<ViewRating> TabA = CreateTab();
        ObservableList ViewRec = FXCollections.observableArrayList(TabA);
        tableview.setItems(ViewRec);
        user.setCellValueFactory(new PropertyValueFactory<>("user"));
        part.setCellValueFactory(new PropertyValueFactory<>("partenaire"));
        rate.setCellValueFactory(new PropertyValueFactory<>("rate"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        this.total.setText(""+TabA.size());
    }
    @FXML
    private void handleClicks(ActionEvent event) {
    }

    @FXML
    private void SearchAll(KeyEvent event) throws SQLException {
        ArrayList<ViewRating> TabA = CreateTab();
        ArrayList<ViewRating> TabB = new ArrayList<>();
        for(ViewRating R : TabA){
            String star="star"+R.getRate();
            if(R.getUser().toLowerCase().indexOf(search.getText().toLowerCase()) != -1
                    ||R.getPartenaire().toLowerCase().indexOf(search.getText().toLowerCase()) != -1
                    ||star.toLowerCase().indexOf(search.getText().toLowerCase()) != -1
                    || R.getDate().toLowerCase().indexOf(search.getText().toLowerCase()) != -1){
                
                TabB.add(new ViewRating(R.getId(),R.getUser(),R.getPartenaire(),R.getRate(),""+R.getDate()));
        
            }
        }
        ObservableList ViewRec = FXCollections.observableArrayList(TabB);
        tableview.setItems(ViewRec);
        user.setCellValueFactory(new PropertyValueFactory<>("user"));
        part.setCellValueFactory(new PropertyValueFactory<>("partenaire"));
        rate.setCellValueFactory(new PropertyValueFactory<>("rate"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        this.total.setText(""+TabB.size());
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

    @FXML
    private void GoToAllComment(MouseEvent event) throws IOException {
        Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("AllComment.fxml")));
                    stage.setScene(scene);
                    stage.show();
    }
    
}
