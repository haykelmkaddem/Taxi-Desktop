/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import desktop.Entite.User;
import desktop.Entite.reservation;
import desktop.Service.ServiceReservation;
import desktop.Service.ServiceUtilisateur;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author anest
 */
public class AjoutReservationController implements Initializable {

    @FXML
    private JFXComboBox<User> partenaire_ajout;
    @FXML
    private JFXComboBox<String> Region;
    @FXML
    private JFXComboBox<String> Produit;
    @FXML
    private JFXButton ajout;
    @FXML
    private JFXComboBox<String> PointA;
    @FXML
    private JFXTextField Destination;
    @FXML
    private JFXTextField Remarques;
    @FXML
    private DatePicker date_reservation;
    @FXML
    private Label success;
    @FXML
    private Label error;
    @FXML
    private Hyperlink admin;
    @FXML
    private Hyperlink Accueil;
    @FXML
    private Hyperlink profil;
    @FXML
    private Hyperlink reservation;
    @FXML
    private Hyperlink course;
    @FXML
    private Hyperlink abonnement;
    @FXML
    private Hyperlink login;
    @FXML
    private Hyperlink feedback;
    ObservableList<String> Regions
            = FXCollections.observableArrayList(
                    "petit Ariana",
                    "Bardo",
                    "Ennaser",
                    "Manar",
                    "Manzah"
            );

    final ObservableList<String> PointsAchats_petit_Ariana
            = FXCollections.observableArrayList(
                    "Aziza",
                    "Haafood",
                    "Chanabo",
                    "Comme chez toi");

    final ObservableList<String> PointsAchats_Bardo
            = FXCollections.observableArrayList(
                    "Baguette et Baguette",
                    "Monoprix",
                    "Storia D'Italia");

    final ObservableList<String> PointsAchats_Ennaser
            = FXCollections.observableArrayList(
                    "Ritrovo Degli Artisti",
                    "Momenti Italiani",
                    "La Tavolata");
    final ObservableList<String> PointsAchats_Manar
            = FXCollections.observableArrayList(
                    "Sultan Ahmet",
                    "Chili's American's Grill"
            );
    final ObservableList<String> PointsAchats_Manzah
            = FXCollections.observableArrayList(
                    "Plan B El Menzah 5",
                    "Pizza Town"
            );
    final ObservableList<String> Produit_azzia
            = FXCollections.observableArrayList(
                    "eau",
                    "Gel douche");
    final ObservableList<String> Produit_Hafood
            = FXCollections.observableArrayList(
                    "Baguette farcie",
                    "pizza 4 fromages");
    final ObservableList<String> Produit_Chanabo
            = FXCollections.observableArrayList(
                    "lablebi"
            );
    final ObservableList<String> Produit_CCT
            = FXCollections.observableArrayList(
                    "Makloub",
                    "Baguette farcie"
            );
    final ObservableList<String> Produit_BG_BG
            = FXCollections.observableArrayList(
                    "pizza 4 fromages",
                    "Makloub");
    final ObservableList<String> Produit_Monoprix
            = FXCollections.observableArrayList(
                    "eau",
                    "LAIT");

    final ObservableList<String> Produit_STORIA
            = FXCollections.observableArrayList("Pas de produit disponible");
    final ObservableList<String> Produit_RITROVA
            = FXCollections.observableArrayList("Pas de produit disponible");
    final ObservableList<String> Produit_Momenti
            = FXCollections.observableArrayList("Pas de produit disponible");
    final ObservableList<String> Produit_TAVA
            = FXCollections.observableArrayList("Pas de produit disponible");
    final ObservableList<String> Produit_SULTAN
            = FXCollections.observableArrayList("Pas de produit disponible");
    final ObservableList<String> Produit_CHILS
            = FXCollections.observableArrayList("Pas de produit disponible");
    final ObservableList<String> Produit_PlanB
            = FXCollections.observableArrayList("Pas de produit disponible");
    final ObservableList<String> Produit_Pizza_Town
            = FXCollections.observableArrayList("Pas de produit disponible");

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        boolean RG = Region.getSelectionModel().isEmpty();
        boolean PROD = Produit.getValue() == null;
        boolean PA = PointA.getValue() == null;

        if (RG) {
            ajout.setDisable(true);
            Region.setValue("choisir une region");
        }

        if (PA) {
            ajout.setDisable(true);
            Region.setValue("choisir une region d'abord");
        }
        if (PROD) {
            ajout.setDisable(true);
            Region.setValue("choisir un point d'achat d'abord");

        }
        Region.setItems(Regions);
        ajout.setDisable(true);
        date_reservation.setValue(LocalDate.now());
        date_reservation.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();

                setDisable(empty || date.compareTo(today) < 0);
            }
        });

        Destination.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (Destination.getText().length() == 0) {
                    Destination.setPromptText("remplir le champ destination");

                }

            }

        });
        Destination.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (Destination.getText().length() == 0) {
                    Destination.setPromptText("destination");

                }

            }

        });

        Destination.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\sa-zA-Z*")) {
                Destination.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));

            }
            if (Destination.getText().length() == 0 || Produit.getValue() == "Pas de produit disponible" || Region.getValue() == null || Produit.getValue() == null || PointA.getValue() == null || partenaire_ajout.getValue() == null || date_reservation.getValue() == null) {
                ajout.setDisable(true);
            } else {
                ajout.setDisable(false);
            }
        });
        partenaire_ajout.valueProperty().addListener((ov, oldValue, newValue) -> {
            if (newValue == null || Produit.getValue() == "Pas de produit disponible" || Region.getValue() == null || Produit.getValue() == null || PointA.getValue() == null || Destination.getText().length() == 0 || date_reservation.getValue() == null) {
                ajout.setDisable(true);
            } else {
                ajout.setDisable(false);
            }

        });

        Region.valueProperty().addListener((ov, oldValue, newValue) -> {
            if (newValue == null || Produit.getValue() == "Pas de produit disponible" || partenaire_ajout.getValue() == null || Produit.getValue() == null || PointA.getValue() == null || Destination.getText().length() == 0 || date_reservation.getValue() == null) {
                ajout.setDisable(true);
            } else {
                ajout.setDisable(false);
            }

        });
        Produit.valueProperty().addListener((ov, oldValue, newValue) -> {
            if (newValue == null ||newValue == "Pas de produit disponible" || Region.getValue() == null || partenaire_ajout.getValue() == null || PointA.getValue() == null || Destination.getText().length() == 0 || date_reservation.getValue() == null) {
                ajout.setDisable(true);
            } else {
                ajout.setDisable(false);
            }

        });

        PointA.valueProperty().addListener((ov, oldValue, newValue) -> {
            if (newValue == null || Region.getValue() == null || Produit.getValue() == null || Produit.getValue() == "Pas de produit disponible" || partenaire_ajout.getValue() == null || Destination.getText().length() == 0 || date_reservation.getValue() == null) {
                ajout.setDisable(true);
            } else {
                ajout.setDisable(false);
            }

        });
        date_reservation.valueProperty().addListener((ov, oldValue, newValue) -> {
            if (date_reservation.getValue() == null || newValue == null || Destination.getText().length() == 0) {
                ajout.setDisable(true);
            } else {
                ajout.setDisable(false);
            }

        });

        Callback<ListView<User>, ListCell<User>> cellFactory = new Callback<ListView<User>, ListCell<User>>() {

            @Override
            public ListCell<User> call(ListView<User> l) {
                return new ListCell<User>() {

                    @Override
                    protected void updateItem(User item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setGraphic(null);
                        } else {
                            setText(item.getNom());
                        }
                    }
                };
            }
        };

        partenaire_ajout.setButtonCell(cellFactory.call(null));
        partenaire_ajout.setCellFactory(cellFactory);
        //remplissage du combobox
        ServiceUtilisateur us = new ServiceUtilisateur();
        List<User> arr = new ArrayList<>();
        try {
            arr = us.findPartenaires();
        } catch (SQLException ex) {
            Logger.getLogger(AjoutReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (User u : arr) {
            partenaire_ajout.getItems().add(u);
        }

        admin.setOnAction((ActionEvent event) -> {
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

        Region.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue ov, Object t, Object t1) {
Produit.setValue(null);
                switch (t1.toString()) {
                    case "petit Ariana":
                        PointA.setItems(PointsAchats_petit_Ariana);
                        break;
                    case "Bardo":
                        PointA.setItems(PointsAchats_Bardo);
                        break;
                    case "Ennaser":
                        PointA.setItems(PointsAchats_Ennaser);
                        break;
                    case "Manar":
                        PointA.setItems(PointsAchats_Manar);
                        break;
                    case "Manzah":
                        PointA.setItems(PointsAchats_Manzah);
                        break;
                 
                }
            }

        });
    Region.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue ov, Object t, Object t1) {

                switch (t1.toString()) {
                    case "Pas de produit disponible":
                ajout.setDisable(false);
                        break;
           
                 
                }
            }

        });
        PointA.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue ov, Object t, Object t1) {

                switch (t1.toString()) {
                    case "Aziza":
                        Produit.setItems(Produit_azzia);
                        break;
                    case "Haafood":
                        Produit.setItems(Produit_Hafood);
                        break;
                    case "Chanabo":
                        Produit.setItems(Produit_Chanabo);
                        break;

                    case "Comme chez toi":
                        Produit.setItems(Produit_CCT);
                        break;
                    case "Baguette et Baguette":
                        Produit.setItems(Produit_BG_BG);
                        break;
                    case "Monoprix":
                        Produit.setItems(Produit_Monoprix);
                        break;
                    case "Storia D'Italia":
                        Produit.setItems(Produit_STORIA);
                        break;
                    case "Ritrovo Degli Artisti":
                        Produit.setItems(Produit_RITROVA);
                        break;
                    case "Momenti Italiani":
                        Produit.setItems(Produit_Momenti);
                        break;
                    case "La Tavolata":
                        Produit.setItems(Produit_TAVA);
                        break;
                    case "Sultan Ahmet":
                        Produit.setItems(Produit_SULTAN);
                        break;
                    case "Chili's American's Grill":
                        Produit.setItems(Produit_CHILS);
                        break;
                    case "Plan B El Menzah 5":
                        Produit.setItems(Produit_PlanB);
                        break;
                    case "Pizza Town":
                        Produit.setItems(Produit_Pizza_Town);
                        break;

                }
            }

        });
    }

    @FXML
    private void Reset() {
        partenaire_ajout.setValue(null);
        Region.setValue(null);
        PointA.setValue(null);
        Destination.setText(null);
        Produit.setValue(null);
        Remarques.setText(null);
        date_reservation.setValue(LocalDate.now());
    }

    @FXML
    private void AjouterReservation(ActionEvent event) throws SQLException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Reservation");
        alert.setHeaderText("Voulez-vous vraiment passer cette reservation ?");
        Optional<ButtonType> option = alert.showAndWait();

        if (option.get() == ButtonType.OK) {
            User Partenaire = partenaire_ajout.getValue();

            Integer id = Partenaire.getId();
            String mail = Partenaire.getEmail();
            String depart = (String) PointA.getValue();
            String destination = Destination.getText();
            String produits = (String) Produit.getValue();
            String remarques = Remarques.getText();
            LocalDate date_R = date_reservation.getValue();

            LocalDateTime date_Re = date_R.atTime(11, 10);
            java.sql.Date sqlDate = java.sql.Date.valueOf(date_Re.toLocalDate());
            reservation r = new reservation(1, 2, id, depart, destination, sqlDate, (float) 200, produits, remarques, "ok");

            ServiceReservation RV = new ServiceReservation();

            RV.ajouter(r);
            {
                success.setText("Reservation ajoutée avec succes");
                Reset();
            }

        }
    }

    @FXML
    private void goToAcceuil(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Acceuil.fxml")));
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void goToCourse(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Courses.fxml")));
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void goToAbonne(ActionEvent event) {

    }

    @FXML
    private void goToProfil(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("GestionProfile.fxml")));
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void goToReservation(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("AjoutReservation.fxml")));
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void goToLogin(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Login.fxml")));
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void goToFeedBack(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("MainUi.fxml")));
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void goToAdmin(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("ReservationFXML.fxml")));
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void FeedMenu(MouseEvent event) {
    }

}
