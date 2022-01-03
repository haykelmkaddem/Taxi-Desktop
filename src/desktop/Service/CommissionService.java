/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktop.Service;

import desktop.Entite.Commission;
import desktop.Entite.reservation;
import desktop.Entite.Courses;
import desktop.Entite.User;
import desktop.Utils.DataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Arbi
 */
public class CommissionService {
    private Connection con;
    private Statement ste;
    
      public CommissionService() {
        con = DataBase.getInstance().getConnection();

    }
      
      
      
      public List<Commission> find(int idinventaire) throws SQLException {
  List<Commission> arr=new ArrayList<>();
    List<User>clients= new ArrayList<User>();
    List<User>partenaires= new ArrayList<User>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from commission where inventairec_id="+idinventaire);
     while (rs.next()) {                
              int id=rs.getInt("id");
              float pourcentage=rs.getFloat("pourcentage");
              CoursesService cs= new CoursesService();
             
              Courses c=cs.findCourse(rs.getInt("course_id"));
              Commission com= new Commission(id,c,pourcentage);
                
               //Courses c=new Courses(id,clients.get(clients.size()-1),partenaires.get(partenaires.size()-1),depart,destination,null,prix);
   
               //Courses c=new Courses(id,clients.get(0),partenaires.get(0),depart,destination,null,prix);
     arr.add(com);
     }
    return arr;
    }
      

    
}
