/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktop.Service;

import desktop.Entite.User;
import desktop.Utils.DataBase;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Arbi
 */
public class UserService {
    private Connection con;
    private Statement ste;
    
      public UserService() {
        con = DataBase.getInstance().getConnection();

    }
      
      public List<User> findPartenaires() throws SQLException
      {
          List<User> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select id,username,email,roles,nom,prenom from utilisateurs where roles like '%ROLE_PARTENAIRE%' ");
     while (rs.next()) {                
               int id=rs.getInt("id");
               String nom=rs.getString("nom");
               String prenom=rs.getString("prenom");
               String email=rs.getString("email");
               String username=rs.getString("username");
               String role=rs.getString("roles");
               
               User u=new User(id,username,email,role,nom,prenom);
     arr.add(u);
     }
    return arr;
          
      }
    
    
    
}
