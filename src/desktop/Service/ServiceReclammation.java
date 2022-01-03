/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktop.Service;

import desktop.Entite.Reclammations;
import desktop.Utils.DataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author USER
 */
public class ServiceReclammation {
    Connection cnx= DataBase.getInstance().getConnection();
    
    public void AddReclammation(Reclammations rec){
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        try {
            String requete = "INSERT INTO reclammations (sujet,contenu,etat,date,user) VALUES (?,?,?,?,?)";
            PreparedStatement pst;
            pst = cnx.prepareStatement(requete);
            pst.setString(1, rec.getSujet());
            pst.setString(2, rec.getContenu());
            pst.setString(3, rec.getEtat());
            pst.setDate(4, sqlDate);
            pst.setInt(5,rec.getUser());
            pst.executeUpdate();
            System.out.println("Reclammation ajoutée !");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    
    public ArrayList<Reclammations> FindAll(){
        ArrayList<Reclammations> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM reclammations";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Reclammations(rs.getInt(1), rs.getInt("user"), rs.getString("sujet"), rs.getString("contenu"),rs.getString("etat"),rs.getDate("date")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }
}
