/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import desktop.Entite.User;
import desktop.Entite.reservation;
import desktop.Service.ReservationSession;
import desktop.Service.ServiceReservation;
import desktop.Service.ServiceUtilisateur;
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
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author anest
 */
public class ReservationFXMLController implements Initializable {

    @FXML
    private Pane pnlCustomer;
    @FXML
    private Pane pnlOrders;
    @FXML
    private Pane pnlOverview;
    @FXML
    private VBox pnItems;
    @FXML
    private Button clients;
    @FXML
    private Button partenaires;
    @FXML
    private Button reservations;
    @FXML
    private Button abonnements;
    @FXML
    private Button feedback;
    @FXML
    private Button logout;
    @FXML
    private Button reservations1;
    @FXML
    private TableView<reservation> tableview;

    @FXML
    private TableColumn<reservation, String> Destination;
    @FXML
    private TableColumn<reservation, String> Date;
    @FXML
    private TableColumn<reservation, String> pointA;
    private JFXHamburger hamburger;
    private JFXDrawer draw;
    @FXML
    private VBox vbox;
    @FXML
    private Button clients1;
    @FXML
    private Label total_not_traited;
    @FXML
    private Hyperlink linkTotraited;
    @FXML
    private Hyperlink stat;
ObservableList <reservation> data =FXCollections.observableArrayList();
    @FXML
    private TextField rechercher;
    @FXML
    private Hyperlink linkTonottraited1;
    @FXML
    private Hyperlink linkTonottraited11;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

      
        try {
            displayAll();
            addButtonToTable();
            rechercher();
            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(ReservationFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void displayAll() throws SQLException {

        ServiceReservation sr = new ServiceReservation();
        List listcs = sr.readNottraited();
        int number = sr.countNottraited();
        ObservableList listReserv = FXCollections.observableArrayList(listcs);
       
        tableview.setItems(listReserv);
         listReserv.forEach(System.out::println);
        total_not_traited.setText(Integer.toString(number));

        pointA.setCellValueFactory(new PropertyValueFactory<>("pointAchat"));
        Destination.setCellValueFactory(new PropertyValueFactory<>("destination"));
        Date.setCellValueFactory(new PropertyValueFactory<>("date_reservation"));

        linkTotraited.setOnAction((ActionEvent event) -> {
            Parent page2;
            try {
                page2 = FXMLLoader.load(getClass().getResource("/GUI/ReservationTraited.fxml"));
                Scene scene2 = new Scene(page2);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(scene2);
                window.show();

            } catch (IOException ex) {
                Logger.getLogger(ReservationFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        
      

             stat.setOnAction((ActionEvent event) -> {
            Parent page2;
            try {
                page2 = FXMLLoader.load(getClass().getResource("/GUI/Statistique.fxml"));
                Scene scene2 = new Scene(page2);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(scene2);
                window.show();

            } catch (IOException ex) {
                Logger.getLogger(ReservationFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
    }

    @FXML
    void rechercher() throws SQLException {
        ServiceReservation cs = new ServiceReservation();
        ArrayList listcs = (ArrayList) cs.readNottraited();
        ObservableList OReservation = FXCollections.observableArrayList(listcs);
        FilteredList<reservation> filteredData = new FilteredList<>(OReservation, p -> true);
        rechercher.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(myObject -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(myObject.getDestination()).toLowerCase().contains(lowerCaseFilter)|| String.valueOf(myObject.getPointAchat()).toLowerCase().contains(lowerCaseFilter)||String.valueOf(myObject.getPrix()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;

                }
                return false;
            });
        });
        SortedList<reservation> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableview.comparatorProperty());
        tableview.setItems(sortedData);
    }


    private void addButtonToTable() throws SQLException {
        TableColumn actionCol = new TableColumn("Action");
        actionCol.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));

        Callback<TableColumn<reservation, Void>, TableCell<reservation, Void>> cellFactory;
        cellFactory = new Callback<TableColumn<reservation, Void>, TableCell<reservation, Void>>() {
            @Override
            public TableCell<reservation, Void> call(final TableColumn<reservation, Void> param) {
                final TableCell<reservation, Void> cell = new TableCell<reservation, Void>() {

                    private final Button refuse = new Button();
                    private final Button accept = new Button("");
                    private final Button details = new Button("");
                    Label lb = new Label("  ");
                    Label lb2 = new Label("  ");
                    private final HBox pane = new HBox(details, lb2, accept, lb, refuse);

                    {
                        Image ref = new Image(getClass().getResourceAsStream("icons/not.png"));
                        Image acc = new Image(getClass().getResourceAsStream("icons/ok.png"));
                        Image det = new Image(getClass().getResourceAsStream("icons/details.png"));
                        refuse.setGraphic(new ImageView(ref));
                        accept.setGraphic(new ImageView(acc));
                        details.setGraphic(new ImageView(det));
                        refuse.setMaxSize(10, 10);
                        accept.setMaxSize(10, 10);
                        details.setMaxSize(10, 10);
                        refuse.setOnAction((ActionEvent event) -> {
                            ServiceReservation sr = new ServiceReservation();
                            reservation reserv = getTableView().getItems().get(getIndex());
                            System.out.println(reserv);
                            try {

                                sr.refuse(reserv.getId());
                                displayAll();
                            } catch (SQLException ex) {
                                Logger.getLogger(ReservationFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        });

                        accept.setOnAction((ActionEvent event) -> {
                            ServiceReservation sr = new ServiceReservation();
                             ServiceUtilisateur su = new ServiceUtilisateur();
                            reservation reserv = getTableView().getItems().get(getIndex());
                            System.out.println(reserv);
                            try {
                              Integer id_client=reserv.getClient_id();
                              User u =su.finduser(id_client);
                              String mail= u.getEmail();
     if(sr.envoyerMail("twasalni?", mail))
        {
                                sr.Accept(reserv.getId());
                                displayAll(); }
     
                            } catch (SQLException ex) {
                                Logger.getLogger(ReservationFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        });

                        details.setOnAction((ActionEvent event) -> {
                            Parent page2;
                            try {
                                reservation reserv = getTableView().getItems().get(getIndex());
                                ReservationSession.getInstace(reserv.getId());
                                page2 = FXMLLoader.load(getClass().getResource("/GUI/ReservationDetail.fxml"));
                                Scene scene2 = new Scene(page2);
                                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                window.setScene(scene2);
                                window.show();

                            } catch (IOException ex) {
                                Logger.getLogger(ReservationFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);

                        setGraphic(empty ? null : pane);
                    }
                };
                return cell;
            }
        };

        actionCol.setCellFactory(cellFactory);

        tableview.getColumns().add(actionCol);

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
        Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("AllContact.fxml")));
                    stage.setScene(scene);
                    stage.show();
 
    }
    
    private void goToFront(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Login.fxml")));
                    stage.setScene(scene);
                    stage.show();
    }
    

               @FXML
    private void InventairenotPaid(ActionEvent event) throws IOException {
                Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("InventaireR.fxml")));
                    stage.setScene(scene);
                    stage.show();
    }
            @FXML
    private void InventairPaid(ActionEvent event) throws IOException {
                Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("InventaireR2.fxml")));
                    stage.setScene(scene);
                    stage.show();
    }

}
