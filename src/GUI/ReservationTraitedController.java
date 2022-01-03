/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
 

 
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import desktop.Entite.reservation;
import desktop.Service.ReservationSession;
import desktop.Service.ServiceReservation;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author anest
 */
public class ReservationTraitedController implements Initializable {

    @FXML
    private Pane pnlCustomer;
    @FXML
    private Pane pnlOrders;
    @FXML
    private Pane pnlOverview;
    @FXML
    private VBox pnItems;
    @FXML
    private TableView<reservation> tableview;
    @FXML
    private TableColumn<reservation, String> pointA;
    @FXML
    private TableColumn<reservation, String> Destination;
    @FXML
     private TableColumn<reservation, String> Date;

    private JFXHamburger hamburger;
    private Label total_not_traited;
    private JFXDrawer draw;
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
    private Label total_traited;
    @FXML
    private Hyperlink linkTonottraited;

    
    ObservableList <reservation> data =FXCollections.observableArrayList();
    @FXML
    private Pagination pagination;
    
  int from =0, to=0;
  int itemPerPage=6;
    @FXML
    private Hyperlink stat;
    @FXML
    private Hyperlink linkTonottraited1;
    @FXML
    private Hyperlink linkTonottraited11;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
            
        pointA.setCellValueFactory(new PropertyValueFactory<>("pointAchat"));
        Destination.setCellValueFactory(new PropertyValueFactory<>("destination"));
        Date.setCellValueFactory(new PropertyValueFactory<>("date_reservation"));
       
        int count =0;
 ServiceReservation sr = new ServiceReservation();
         count = sr.counttraited();
         int pageCount=(count/itemPerPage)+1;
         pagination.setPageCount(pageCount);
       //  System.out.println( "page count"+ pageCount);
         pagination.setPageFactory(this::page);

        try {
           // displayAll();
            addButtonToTable();
            linkTonottraited.setOnAction((ActionEvent event) -> {
                Parent page2;
                try {
                    page2 = FXMLLoader.load(getClass().getResource("/GUI/ReservationFXML.fxml"));
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
            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(ReservationFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   private Label label;
 
   private void showConfirmation() {
 
      Alert alert = new Alert(AlertType.CONFIRMATION);
      alert.setTitle("Delete File");
      alert.setHeaderText("Are you sure want to move this file to the Recycle Bin?");
      alert.setContentText("C:/MyFile.txt");
 
      // option != null.
      Optional<ButtonType> option = alert.showAndWait();
 
      if (option.get() == null) {
         this.label.setText("No selection!");
      } else if (option.get() == ButtonType.OK) {
      
      } else if (option.get() == ButtonType.CANCEL) {
         this.label.setText("Cancelled!");
      } else {
         this.label.setText("-");
      }
   }
    public List displayAll()  {

        try {
            ServiceReservation sr = new ServiceReservation();
            List listcs = sr.readTraited(from,to);
            int number = sr.counttraited();
           // listcs.forEach(System.out::println);
    
              // listcs.forEach(System.out::println);
            total_traited.setText(Integer.toString(number));
            return listcs;
        } catch (SQLException ex) {
            Logger.getLogger(ReservationTraitedController.class.getName()).log(Level.SEVERE, null, ex);
               return null;
        }

    }
    
       public Node page( int pageIndex)   {
        
    from = pageIndex * itemPerPage;
    to = itemPerPage;
    //   System.out.println("test" + from +" "+ pageIndex);
        ObservableList listReserv = FXCollections.observableArrayList(displayAll() );
        tableview.setItems(listReserv);
  
return tableview;
    }
  

    private void addButtonToTable() throws SQLException {
        TableColumn actionCol = new TableColumn("Action");
        actionCol.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));

        Callback<TableColumn<reservation, Void>, TableCell<reservation, Void>> cellFactory;
        cellFactory = new Callback<TableColumn<reservation, Void>, TableCell<reservation, Void>>() {
            @Override
            public TableCell<reservation, Void> call(final TableColumn<reservation, Void> param) {
                final TableCell<reservation, Void> cell = new TableCell<reservation, Void>() {

                    private final Button delete = new Button();
                    private final Button restaurer = new Button("");
                    private final Button details = new Button("");
                    Label lb = new Label("  ");
                    Label lb2 = new Label("  ");
                    private final HBox pane = new HBox(details, lb2, restaurer, lb, delete);

                    {
                        final Tooltip tooltip = new Tooltip();
                        tooltip.setText("supprimer ");
                        delete.setTooltip(tooltip);

                        final Tooltip res = new Tooltip();
                        tooltip.setText("restaurer");
                        restaurer.setTooltip(res);

                        final Tooltip deta = new Tooltip();
                        tooltip.setText("afficher en details");
                        details.setTooltip(deta);
                        Image ref = new Image(getClass().getResourceAsStream("icons/delete.png"));
                        Image acc = new Image(getClass().getResourceAsStream("icons/restaurer.png"));
                        Image det = new Image(getClass().getResourceAsStream("icons/details.png"));
                        delete.setGraphic(new ImageView(ref));
                        restaurer.setGraphic(new ImageView(acc));
                        details.setGraphic(new ImageView(det));
                        delete.setMaxSize(10, 10);
                        restaurer.setMaxSize(10, 10);
                        details.setMaxSize(10, 10);
                       
                        
                        delete.setOnAction((ActionEvent event) -> {
                            
     
                            
                     

                        });

                        restaurer.setOnAction((ActionEvent event) -> {
                                                        
      Alert alert = new Alert(AlertType.CONFIRMATION);
      alert.setTitle("Restaurer réservation");
      alert.setHeaderText("Voulez-vous vraiment restaurer cette réservation?");
      Optional<ButtonType> option = alert.showAndWait();
 if (option.get() == ButtonType.OK) {
                            
                          
                            ServiceReservation sr = new ServiceReservation();
                            reservation reserv = getTableView().getItems().get(getIndex());
                          //  System.out.println(reserv);
                            try {

                                sr.restaurer(reserv.getId());
                                displayAll();
                            } catch (SQLException ex) {
                                Logger.getLogger(ReservationFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                            }
 }
                        });
                        delete.setOnAction((ActionEvent event) -> {
                            
                            
                             Alert alert = new Alert(AlertType.CONFIRMATION);
      alert.setTitle("suppression");
      alert.setHeaderText("Voulez-vous vraiment supprimer cette réservation?");
      Optional<ButtonType> option = alert.showAndWait();

 if (option.get() == ButtonType.OK) {
              
                            ServiceReservation sr = new ServiceReservation();
                            reservation reserv = getTableView().getItems().get(getIndex());
                         //   System.out.println(reserv);
                            try {

                                sr.refuse(reserv.getId());
                                displayAll();
                            } catch (SQLException ex) {
                                Logger.getLogger(ReservationFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                            }
      } 
                            
                            ServiceReservation sr = new ServiceReservation();
                            reservation reserv = getTableView().getItems().get(getIndex());
                          //  System.out.println(reserv);
                            try {

                                sr.delete(reserv.getPrix(), reserv.getPartenaire_id(), reserv.getId());
                                displayAll();
                            } catch (SQLException ex) {
                                Logger.getLogger(ReservationFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        });
                        details.setOnAction((ActionEvent event) -> {
                            Parent page2;
                            try {
                                reservation reserv = getTableView().getItems().get(getIndex());
                                ReservationSession.getInstace(reserv.getId());
                                page2 = FXMLLoader.load(getClass().getResource("/GUI/ReservationDetailTraited.fxml"));
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
