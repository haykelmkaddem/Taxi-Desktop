/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktop.Service;

import desktop.Entite.Rating;
import desktop.Entite.Reclammations;
import desktop.Entite.Taxi;
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
public class ServiceRate {
    Connection cnx= DataBase.getInstance().getConnection();
    int rat =0;
    int total=0;
    public int FindGeneralRate(int part) throws SQLException{
        String req = "SELECT * FROM rating Where part = ?";
        PreparedStatement pst;
        pst = cnx.prepareStatement(req);
        pst.setInt(1, part);
        ResultSet rs = pst.executeQuery();
        
        while (rs.next()) {
            rat= rat+rs.getInt("rate");
            total=total+1;
            System.out.println(rs.getInt("rate")+" : "+total);
        }
        
        if(rat==0){
            return 0;
        }
        return rat/total;
        
    }
    public Rating FindMyRate(int part,int user) throws SQLException{
        Rating r = new Rating(0, 1, part, 0, new Date());
        String req = "SELECT * FROM rating Where part = ? AND user= ?";
        PreparedStatement pst;
        pst = cnx.prepareStatement(req);
        pst.setInt(1, part);
        pst.setInt(2, user);
        ResultSet rs = pst.executeQuery();
        
        if (!rs.next()) {
            
        } else {
            return new Rating(rs.getInt("id"),rs.getInt("user"), rs.getInt("part"),rs.getInt("rate"),rs.getDate("date"));
        }
        System.out.println(rat);
        return r;
        
    }
    
    public void AddRting(Rating r){
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        try {
            String requete = "INSERT INTO rating (user,part,rate,date) VALUES (?,?,?,?)";
            PreparedStatement pst;
            pst = cnx.prepareStatement(requete);
            pst.setInt(1, r.getUser());
            pst.setInt(2, r.getPart());
            pst.setInt(3, r.getRate());
            pst.setDate(4, sqlDate);
            pst.executeUpdate();
            System.out.println("Rating ajoutée !");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    
    public void UpdateRating(Rating r){
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        try {
            String requete = "UPDATE rating SET rate=?,date=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(3, r.getId());
            pst.setInt(1, r.getRate());
            pst.setDate(2, sqlDate);
            pst.executeUpdate();
            System.out.println("Rating modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    public ArrayList<Rating> FindAll(){
        ArrayList<Rating> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM rating";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Rating(rs.getInt("id"), rs.getInt("user"), rs.getInt("part"), rs.getInt("rate"), rs.getDate("date")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }
}
