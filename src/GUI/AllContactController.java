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
import desktop.Entite.Reclammations;
import desktop.Entite.ViewReclammation;
import desktop.Service.ServiceFeedBack;
import desktop.Service.ServiceReclammation;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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
public class AllContactController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    ServiceReclammation ServR = new ServiceReclammation();
    
    ServiceFeedBack ServF = new ServiceFeedBack();
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
    private VBox pnItems;
    @FXML
    private TableView<?> tableview;
    @FXML
    private TableColumn<ViewReclammation, String> user;
    @FXML
    private TableColumn<ViewReclammation, String> sujet;
    @FXML
    private TableColumn<ViewReclammation, String> contenu;
    @FXML
    private TableColumn<ViewReclammation, Date> date;
    private JFXHamburger hamburger;
    private JFXDrawer draw;
    @FXML
    private TableColumn<ViewReclammation, String> etat;
    @FXML
    private TableColumn<?, ?> actions;
    @FXML
    private Label total;
    @FXML
    private TextField search;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            DisplayAll();
        } catch (SQLException ex) {
            Logger.getLogger(AllContactController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    } 
    
    public ArrayList<ViewReclammation> CreateTab() throws SQLException{
        ArrayList<Reclammations> TabR = ServR.FindAll();
        
        ArrayList<ViewReclammation> TabA = new ArrayList<>();
        for(Reclammations r : TabR){
            Partenaire P = ServF.FindUser(r.getUser());
            TabA.add(new ViewReclammation(r.getId(), P.getNom()+" "+P.getPrenom(), r.getSujet(),r.getContenu(),""+r.getDate(),r.getEtat()));    
        }
        return TabA;
    }
    
    
    public void DisplayAll() throws SQLException{
        
        ArrayList<ViewReclammation> TabA = CreateTab();
        ObservableList ViewRec = FXCollections.observableArrayList(TabA);
        tableview.setItems(ViewRec);
        user.setCellValueFactory(new PropertyValueFactory<>("user"));
        sujet.setCellValueFactory(new PropertyValueFactory<>("sujet"));
        contenu.setCellValueFactory(new PropertyValueFactory<>("contenu"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        this.total.setText(""+TabA.size());
    }

    

    @FXML
    private void SearchAll(KeyEvent event) throws SQLException {
        ArrayList<ViewReclammation> TabA = CreateTab();
        ArrayList<ViewReclammation> TabB = new ArrayList<>();
        for(ViewReclammation R : TabA){
            if(R.getUser().toLowerCase().indexOf(search.getText().toLowerCase()) != -1
                    || R.getContenu().toLowerCase().indexOf(search.getText().toLowerCase()) != -1
                    || R.getSujet().toLowerCase().indexOf(search.getText().toLowerCase()) != -1
                    ||R.getDate().toLowerCase().indexOf(search.getText().toLowerCase()) != -1
                    ||R.getEtat().toLowerCase().indexOf(search.getText().toLowerCase()) != -1){
                System.out.println(R.getUser());
                TabB.add(new ViewReclammation(R.getId(), R.getUser(), R.getSujet(),R.getContenu(),""+R.getDate(),R.getEtat()));    
        
            }
        }
        ObservableList ViewRec = FXCollections.observableArrayList(TabB);
        tableview.setItems(ViewRec);
        user.setCellValueFactory(new PropertyValueFactory<>("user"));
        sujet.setCellValueFactory(new PropertyValueFactory<>("sujet"));
        contenu.setCellValueFactory(new PropertyValueFactory<>("contenu"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
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
    private void GoToAllComment(MouseEvent event) throws IOException {
        Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("AllComment.fxml")));
                    stage.setScene(scene);
                    stage.show();
    }

    @FXML
    private void goToAcceuil(ActionEvent event) {
    }

    @FXML
    private void goToclients(ActionEvent event) {
    }

    @FXML
    private void goToPartenaire(ActionEvent event) {
    }

    @FXML
    private void goToReservation(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("ReservationFXML.fxml")));
                    stage.setScene(scene);
                    stage.show();
    }

    @FXML
    private void goToCourse(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("CoursesAdmin.fxml")));
                    stage.setScene(scene);
                    stage.show();
    }

    @FXML
    private void goToAbonne(ActionEvent event) {
    }

    @FXML
    private void goToFeedBack(ActionEvent event) {
    }
    
    
}
