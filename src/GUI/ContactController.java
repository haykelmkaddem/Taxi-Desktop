/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import desktop.Entite.Reclammations;
import desktop.Service.ServiceFeedBack;
import desktop.Service.ServiceReclammation;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class ContactController implements Initializable {

    @FXML
    private TextField email;
    @FXML
    private TextField sujet;
    @FXML
    private TextArea contenu;
    @FXML
    private Button send;
    @FXML
    private Button CancelBtn;

    private boolean Isemail = false;

    ServiceReclammation SevR = new ServiceReclammation();
    ServiceFeedBack  ServF =new ServiceFeedBack();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    @FXML
    private void ControleEmail(KeyEvent event) {
        String email = this.email.getText().toString();
        String masque = "^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+"
                + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$";
        Pattern pattern = Pattern.compile(masque);
        Matcher controler = pattern.matcher(email);
        if (controler.matches()) {
            this.email.getStyleClass().clear();
            this.email.getStyleClass().add("FormValid");
            Isemail = true;
        } else {
            this.email.getStyleClass().clear();
            this.email.getStyleClass().add("FormError");
            Isemail = false;
        }
    }

    private boolean SubjetIsValid() {
        boolean test = false;
        String sujet = this.sujet.getText().toString();
        String masque = "^[a-zA-Z]+[a-zA-Z0-9\\._-]*$";
        Pattern pattern = Pattern.compile(masque);
        Matcher controler = pattern.matcher(sujet);
        if (controler.matches()) {
            this.sujet.getStyleClass().clear();
            this.sujet.getStyleClass().add("FormValid");
            test = true;
        } else {
            this.sujet.getStyleClass().clear();
            this.sujet.getStyleClass().add("FormError");
        }
        return test;
    }

    private boolean ContainIsValid() {
        boolean test = false;
        String contenu = this.contenu.getText().toString();
        String masque = "^[a-zA-Z]+[a-zA-Z0-9\\._-]*$";
        Pattern pattern = Pattern.compile(masque);
        Matcher controler = pattern.matcher(contenu);
        if (controler.matches()) {
            this.contenu.getStyleClass().clear();
            this.contenu.getStyleClass().add("FormValid");
            test = true;
        } else {
            this.contenu.getStyleClass().clear();
            this.contenu.getStyleClass().add("FormError");
        }
        return test;
    }

    @FXML
    private void Reset(MouseEvent event) {
        this.email.setText("");
        this.email.getStyleClass().clear();
        this.email.getStyleClass().add("TextContainer");
        this.sujet.setText("");
        this.sujet.getStyleClass().clear();
        this.sujet.getStyleClass().add("TextContainer");
        this.contenu.setText("");
        this.contenu.getStyleClass().clear();
        this.contenu.getStyleClass().add("TextContainer");
    }

    @FXML
    private void SendForm(MouseEvent event) throws SQLException {
        boolean TestSujet = SubjetIsValid();
        boolean TestContenu = ContainIsValid();
        if (TestContenu && TestSujet && Isemail) {
            int user= ServF.FindUserByEmail(email.getText());
            if(user != -1){
                Reclammations rec = new Reclammations(1, user, sujet.getText(), contenu.getText(),"En Attente",new Date());
                SevR.AddReclammation(rec);
            }
        } else {
            System.out.println("No");
        }
    }

}
