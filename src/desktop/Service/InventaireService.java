/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktop.Service;


import desktop.Entite.Inventaire;
import desktop.Entite.Commission;
import desktop.Entite.Courses;
import desktop.Entite.User;
import desktop.Utils.DataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Arbi
 */
public class InventaireService {
private Connection con;
    private Statement ste;
    
    public InventaireService() {
        con = DataBase.getInstance().getConnection();

    }
    public List<Inventaire> readNonPaye() throws SQLException {
        List<Inventaire> arr=new ArrayList<>();
    //List<User>clients= new ArrayList<User>();
    List<User>partenaires= new ArrayList<User>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from inventaire_c where paye=0");
     while (rs.next()) {                
               int id=rs.getInt("id");
               java.sql.Date date_i=rs.getDate("date_i");
               int partenaire_id=rs.getInt("partenaire_id");
               float montant=rs.getFloat("montant");
               
                   
               //recuperer partenaire
               PreparedStatement pre_partenaire=con.prepareStatement("select * from utilisateurs where id=? ");
               pre_partenaire.setInt(1,partenaire_id);
               ResultSet rspartenaire=pre_partenaire.executeQuery();
               while(rspartenaire.next())
               {
                   int idp=rspartenaire.getInt("id");
                   String usernamep=rspartenaire.getString("username");
                   String emailp=rspartenaire.getString("email");
                   String rolep="yikes";
                   String nomp=rspartenaire.getString("nom");
                   String prenomp=rspartenaire.getString("prenom");
                   User partenaire= new User(idp,usernamep,emailp,rolep,nomp,prenomp);
                   partenaires.add(partenaire);
               }
                Timestamp timestamp = new Timestamp(date_i.getTime());
                   
               
                
               Inventaire i=new Inventaire(id,partenaires.get(0),montant,timestamp.toLocalDateTime(),0);
   
               //Courses c=new Courses(id,clients.get(0),partenaires.get(0),depart,destination,null,prix);
     arr.add(i);
     }
    return arr;
        
    }
    
    public void payer(int id) throws SQLException
    {
        PreparedStatement preupdate=con.prepareStatement("UPDATE  `inventaire_c` SET paye=1 WHERE id=? ;");
        preupdate.setInt(1,id);
                 preupdate.executeUpdate();  
    }
    
    
    public List<Inventaire> readPaye() throws SQLException {
        List<Inventaire> arr=new ArrayList<>();
    //List<User>clients= new ArrayList<User>();
    List<User>partenaires= new ArrayList<User>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from inventaire_c where paye=1");
     while (rs.next()) {                
               int id=rs.getInt("id");
               java.sql.Date date_i=rs.getDate("date_i");
               int partenaire_id=rs.getInt("partenaire_id");
               float montant=rs.getFloat("montant");
               
                   
               //recuperer partenaire
               PreparedStatement pre_partenaire=con.prepareStatement("select * from utilisateurs where id=? ");
               pre_partenaire.setInt(1,partenaire_id);
               ResultSet rspartenaire=pre_partenaire.executeQuery();
               while(rspartenaire.next())
               {
                   int idp=rspartenaire.getInt("id");
                   String usernamep=rspartenaire.getString("username");
                   String emailp=rspartenaire.getString("email");
                   String rolep="yikes";
                   String nomp=rspartenaire.getString("nom");
                   String prenomp=rspartenaire.getString("prenom");
                   User partenaire= new User(idp,usernamep,emailp,rolep,nomp,prenomp);
                   partenaires.add(partenaire);
               }
                Timestamp timestamp = new Timestamp(date_i.getTime());
                   
               
                
               Inventaire i=new Inventaire(id,partenaires.get(0),montant,timestamp.toLocalDateTime(),0);
   
               //Courses c=new Courses(id,clients.get(0),partenaires.get(0),depart,destination,null,prix);
     arr.add(i);
     }
    return arr;
        
    }
    
}
