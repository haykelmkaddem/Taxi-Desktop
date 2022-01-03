/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktop.Service;

import desktop.Entite.Commentaires;
import desktop.Entite.Partenaire;
import desktop.Utils.DataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author USER
 */
public class CommentaireService {
    Connection cnx = DataBase.getInstance().getConnection();
    
    
    public ArrayList<Commentaires> FindCommentaire(int part) throws SQLException {
        
        ArrayList<Commentaires> ListCom = new ArrayList<>();
        String req = "SELECT * FROM commentaires Where part = ? ";
        PreparedStatement pst;
        pst = cnx.prepareStatement(req);
        pst.setInt(1, part);
        ResultSet rs = pst.executeQuery();
        while (rs.next()){
            ListCom.add(new Commentaires(rs.getInt("id"),rs.getInt("user"),rs.getInt("part"),rs.getString("commentaire"),rs.getDate("date")));
        }
        return ListCom;
    }
    
    public void AddCommentaire(Commentaires c){
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        try {
            String requete = "INSERT INTO commentaires (user,part,commentaire,date) VALUES (?,?,?,?)";
            PreparedStatement pst;
            pst = cnx.prepareStatement(requete);
            pst.setInt(1, c.getUser());
            pst.setInt(2, c.getPart());
            pst.setString(3, c.getCommentaire());
            pst.setDate(4, sqlDate);
            pst.executeUpdate();
            System.out.println("RCommmentaires ajoutée !");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    
    public ArrayList<Commentaires> FindAll(){
        ArrayList<Commentaires> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM commentaires";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Commentaires(rs.getInt("id"), rs.getInt("user"), rs.getInt("part"), rs.getString("commentaire"), rs.getDate("date")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }
}
