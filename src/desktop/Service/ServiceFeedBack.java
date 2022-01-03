/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktop.Service;

import desktop.Entite.Partenaire;
import desktop.Entite.Taxi;
import desktop.Utils.DataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.scene.paint.Color;

/**
 *
 * @author USER
 */
public class ServiceFeedBack {

    Connection cnx = DataBase.getInstance().getConnection();

    public int FindUserByEmail(String email) throws SQLException {
        String req = "SELECT * FROM utilisateurs Where email = ?";
        PreparedStatement pst;
        pst = cnx.prepareStatement(req);
        pst.setString(1, email);
        ResultSet rs = pst.executeQuery();
        if (!rs.next()) {
            System.out.println("Email Non Valide ");
        } else {
            return rs.getInt(1);
        }
        return -1;
    }

    public int FindPart(int taxi) throws SQLException {
        String req = "SELECT * FROM partenaire Where taxi_id = ?";
        PreparedStatement pst;
        pst = cnx.prepareStatement(req);
        pst.setInt(1, taxi);
        ResultSet rs = pst.executeQuery();
        if (!rs.next()) {
            System.out.println("Dont exist");
        } else {
            return rs.getInt(1);
        }
        return -1;
    }
    
    public Partenaire FindPartenaire(int id) throws SQLException {
        String req = "SELECT * FROM partenaire Where id = ?";
        PreparedStatement pst;
        pst = cnx.prepareStatement(req);
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();
        if (!rs.next()) {
            System.out.println("Dont exist");
        } else {
            return new Partenaire(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("mail"), rs.getInt("tel"));
        }
        return null;
    }
    
    public Partenaire FindUser(int id) throws SQLException {
        String req = "SELECT * FROM utilisateurs Where id = ?";
        PreparedStatement pst;
        pst = cnx.prepareStatement(req);
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();
        if (!rs.next()) {
            System.out.println("Dont exist");
        } else {
            return new Partenaire(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), 0);
        }
        return null;
    }
    
    
    public ArrayList<Taxi> DisplayAll() throws SQLException {
        ArrayList<Taxi> TabT = new ArrayList<>();
        String req = "SELECT * FROM taxi";
        PreparedStatement pst;
        pst = cnx.prepareStatement(req);
        ResultSet rs = pst.executeQuery();
        while (rs.next()){
            System.out.println("Coool");
            TabT.add(new Taxi(rs.getInt("matricule"),rs.getString("etat"), rs.getString("marque"), rs.getString("image")));
        }
        return TabT;
    }
}
