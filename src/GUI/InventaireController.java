/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import desktop.Entite.Courses;
import desktop.Entite.Inventaire;
import desktop.Service.CoursesService;
import desktop.Service.InventaireService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


//STAT
import javafx.collections.ObservableList;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.chart.*;
import javafx.collections.FXCollections;
import javafx.scene.paint.Color;
/**
 * FXML Controller class
 *
 * @author anest
 */
public class InventaireController implements Initializable {

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
    private TableView<Inventaire> afficher_inventaires;
    @FXML
    private TableColumn<?, ?> afficher_inventaires_id;
    @FXML
    private TableColumn<?, ?> afficher_inventaires_partenaire;
    @FXML
    private TableColumn<?, ?> afficher_inventaires_date;
    @FXML
    private TableColumn<?, ?> afficher_inventaires_montant;
    private JFXHamburger hamburger;
    @FXML
    private Button voirCourses;
    @FXML
    private Button archive_inventaire;
    @FXML
    private Button retour;
    @FXML
    private Button payer;
    @FXML
    private Button details;
    private JFXDrawer draw;
    @FXML
    private Label titre;
    @FXML
    private AnchorPane containerAdmin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   
        
   payer.setVisible(true);
       archive_inventaire.setVisible(true);
       retour.setVisible(false);
       titre.setText("Inventaire");
         afficher_inventaires_id.setCellValueFactory(new PropertyValueFactory<>("id"));
         afficher_inventaires_partenaire.setCellValueFactory(new PropertyValueFactory<>("nompartenaire"));
         afficher_inventaires_date.setCellValueFactory(new PropertyValueFactory<>("date"));
         afficher_inventaires_montant.setCellValueFactory(new PropertyValueFactory<>("montant"));
         InventaireService is= new InventaireService();
         List<Inventaire> arrc=new ArrayList<>();
       
      
        try {
            arrc=is.readNonPaye();
        } catch (SQLException ex) {
            Logger.getLogger(InventaireController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       ObservableList<Inventaire> ovbservableList= FXCollections.observableArrayList(arrc);
       afficher_inventaires.setItems(ovbservableList);
       
       //stat
       System.out.println("espace admin");
        List<PieChart.Data> l = new ArrayList<>();
        InventaireService ps = new InventaireService();
        try {
            ps.readNonPaye().stream().forEach(p->{
                l.add(new PieChart.Data(p.getNompartenaire(),p.getMontant()));
            });
        } catch (SQLException ex) {
            Logger.getLogger(InventaireController.class.getName()).log(Level.SEVERE, null, ex);
        }

        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        l
                );
        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Montant à payer par chaque Partenaire");

        chart.setLabelLineLength(10);
        chart.setLegendSide(Side.LEFT);
        final Label caption = new Label("");
        caption.setTextFill(Color.BLACK);
        caption.setStyle("-fx-text-fill: black;");
        caption.setStyle("-fx-font: 24 arial;");

        for (final PieChart.Data data : chart.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED,
                    new EventHandler<MouseEvent>() {
                        @Override public void handle(MouseEvent e) {
                            caption.setTranslateX(e.getSceneX());
                            caption.setTranslateY(e.getSceneY()-110);
                            caption.setText(String.valueOf(data.getPieValue()) + "%");
                        }
                    });
        }
        containerAdmin.getChildren().add(chart);
        containerAdmin.getChildren().add(caption);

    }    


@FXML
    private void voirCourses(ActionEvent event) throws IOException {
        Parent page2;
        page2 = FXMLLoader.load(getClass().getResource("/GUI/CoursesAdmin.fxml"));
        Scene scene2= new Scene(page2);
        Stage window= (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene2);
        window.show();
    }

    @FXML
    private void payer(ActionEvent event) throws SQLException {
            InventaireService iv= new InventaireService();
        Inventaire u=afficher_inventaires.getSelectionModel().getSelectedItem();
        iv.payer(u.getId());
        List<Inventaire> arrc=new ArrayList<>();
         try {
            arrc=iv.readNonPaye();
        } catch (SQLException ex) {
            Logger.getLogger(InventaireController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       ObservableList<Inventaire> ovbservableList= FXCollections.observableArrayList(arrc);
       afficher_inventaires.setItems(ovbservableList);
         //afficher_courses.getItems().removeAll(afficher_courses.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void archive_inventaire(ActionEvent event) throws IOException {
     
        InventaireService iv= new InventaireService();
       List<Inventaire> arrc=new ArrayList<>();
         try {
            arrc=iv.readPaye();
        } catch (SQLException ex) {
            Logger.getLogger(InventaireController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       ObservableList<Inventaire> ovbservableList= FXCollections.observableArrayList(arrc);
       afficher_inventaires.setItems(ovbservableList);
       
       payer.setVisible(false);
       archive_inventaire.setVisible(false);
       retour.setVisible(true);
       titre.setText("Archive Inventaire");
       
       
    }

    @FXML
    private void retour(ActionEvent event) {
         InventaireService iv= new InventaireService();
       List<Inventaire> arrc=new ArrayList<>();
         try {
            arrc=iv.readNonPaye();
        } catch (SQLException ex) {
            Logger.getLogger(InventaireController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       ObservableList<Inventaire> ovbservableList= FXCollections.observableArrayList(arrc);
       afficher_inventaires.setItems(ovbservableList);
       
       payer.setVisible(true);
       retour.setVisible(false);
       archive_inventaire.setVisible(true);
       titre.setText("Inventaire");
        
    }

    @FXML
    private void details(ActionEvent event) throws IOException {
        Parent page2;
        page2 = FXMLLoader.load(getClass().getResource("/GUI/Commission.fxml"));
        Scene scene2= new Scene(page2);
        Stage window= (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene2);
        window.show();
    }
         @FXML
    private void goToAcceuil(ActionEvent event) {
        
    }

          @FXML
    private void goToclients(ActionEvent event) throws IOException {
       
    }
               @FXML
    private void goToPartenaire(ActionEvent event) throws IOException {
       
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
    private void goToReservation(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("ReservationFXML.fxml")));
                    stage.setScene(scene);
                    stage.show();
    }
      @FXML
    private void goToAbonne(ActionEvent event) {
        
    }

    
        

               @FXML
    private void goToFeedBack(ActionEvent event) throws IOException {
 
    }
    
    private void goToFront(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Login.fxml")));
                    stage.setScene(scene);
                    stage.show();
    }
    
}
