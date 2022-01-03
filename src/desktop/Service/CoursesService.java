/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktop.Service;

import desktop.Entite.Commission;
import desktop.Entite.Courses;
import desktop.Entite.Inventaire;
import desktop.Entite.User;
import desktop.Utils.DataBase;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Arbi
 */
public class CoursesService {
    private Connection con;
    private Statement ste;
    
      public CoursesService() {
        con = DataBase.getInstance().getConnection();

    }
      public void Ajouter(Courses u) throws SQLException
    {
        List<Integer> idinv = new ArrayList<>();
        List<Integer> idcour = new ArrayList<>();
        List<Float> montantinv = new ArrayList<>();
        java.sql.Date sqlDate = java.sql.Date.valueOf(u.getDate_course().toLocalDate());
         PreparedStatement pre=con.prepareStatement("INSERT INTO `courses` ( `id`, `client_id`,`partenaire_id`, `depart`,`destination`, `date_course`,`prix`) VALUES ( null,2,?,?,?,?,?);");
    //pre.setInt(1, null);
    pre.setInt(1, u.getPartenaire().getId());
    pre.setString(2, u.getDepart());
    pre.setString(3, u.getDestination());
    pre.setFloat(5, u.getPrix());
    pre.setDate(4, sqlDate);
    pre.executeUpdate();
    //creation de la commission
    Commission com=new Commission();
    com.setCourse(u);
    com.setPourcentage((float) 0.15);
    com.setDate_comission(u.getDate_course());
    
    
    
    //creation de l'inventaire
    int x=0;
    List<Inventaire> inventaires=new ArrayList<>();
    Inventaire i=new Inventaire();
    PreparedStatement pre_inv=con.prepareStatement("select * from inventaire_c where partenaire_id=? and paye=0 ");
               pre_inv.setInt(1,u.getPartenaire().getId());
               ResultSet rs_inv=pre_inv.executeQuery();
               while(rs_inv.next())
               {
                   x++;
               }
    if(x==0)
    {
        i.setPartenaire(u.getPartenaire());
        i.setMontant((float) (u.getPrix()*0.15));
        i.setDate_i(u.getDate_course());
        i.setPaye(0);
        PreparedStatement preinsInv=con.prepareStatement("INSERT INTO `inventaire_c` ( `id`, `partenaire_id`,`montant`, `date_i`,`paye`) VALUES ( null,?,?,?,0);");
        preinsInv.setInt(1, u.getPartenaire().getId());
        preinsInv.setFloat(2, (float) (u.getPrix()*0.15));
        preinsInv.setDate(3, sqlDate);
        preinsInv.executeUpdate();
        
        PreparedStatement pre_inv2=con.prepareStatement("select id from inventaire_c where partenaire_id=? and paye=0");
               pre_inv2.setInt(1,u.getPartenaire().getId());
               ResultSet rs_inv2=pre_inv2.executeQuery();
               while(rs_inv2.next())
               {
                   int id=rs_inv2.getInt("id");
                   idinv.add(id);
                   
               }
        
        
    }
    else
    {

            PreparedStatement re_inv = con.prepareStatement("select * from inventaire_c where partenaire_id=? and paye=0 ");
            
               pre_inv.setInt(1,u.getPartenaire().getId());
                rs_inv=pre_inv.executeQuery();
                
               while(rs_inv.next())
               {
                   int id=rs_inv.getInt("id");
                   float montant=rs_inv.getFloat("montant");
                   idinv.add(id);
                   montantinv.add(montant);
                   /*Date date_inv_notdone=rs_inv.getDate("date_i");
                   Timestamp timestamp = new Timestamp(date_inv_notdone.getTime());
                   timestamp.toLocalDateTime();
                   
                   Inventaire inv=new Inventaire(id,u.getPartenaire(),montant,timestamp.toLocalDateTime(),0);
                   inventaires.add(inv);*/
               }
              // i=inventaires.get(0);
              PreparedStatement preupdate=con.prepareStatement("UPDATE  `inventaire_c` SET montant=? WHERE id=? ;");
                 preupdate.setFloat(1,montantinv.get(0)+200);
                 preupdate.setInt(2,idinv.get(0));
                 preupdate.executeUpdate();   
             
               
     }
    //recuperation du courses ajouté pour l id
    PreparedStatement cour= con.prepareStatement("select * from courses ORDER BY id DESC LIMIT 1 ");
            
               
               ResultSet rs_cour=cour.executeQuery();
               while(rs_cour.next())
               {
                    int id=rs_cour.getInt("id");
                    idcour.add(id);
                    
               }
               System.out.println(idcour.get(0));
    //Ajout de commission
    PreparedStatement preComm=con.prepareStatement("INSERT INTO `commission` ( `id`, `partenaire_id`,`course_id`, `pourcentage`,`date_commission`,`inventairec_id`) VALUES ( null,?,?,?,?,?);");
        preComm.setInt(1, u.getPartenaire().getId());
        preComm.setInt(2, idcour.get(0));
        preComm.setFloat(3,com.getPourcentage());
        preComm.setDate(4, sqlDate);
        preComm.setInt(5,idinv.get(0));
        preComm.executeUpdate();
  
   
    
    
    }
      
      
      
       
    public List<Courses> readAll() throws SQLException {
  List<Courses> arr=new ArrayList<>();
    List<User>clients= new ArrayList<User>();
    List<User>partenaires= new ArrayList<User>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from courses");
     while (rs.next()) {                
               int id=rs.getInt("id");
               String depart=rs.getString("depart");
               String destination=rs.getString("destination");
               int client_id=rs.getInt("client_id");
               int partenaire_id=rs.getInt("partenaire_id");
               float prix=rs.getFloat("prix");
               //recuperer client
                   PreparedStatement pre_client=con.prepareStatement("select * from utilisateurs where id=? ");
               pre_client.setInt(1,client_id);
               ResultSet rsclient=pre_client.executeQuery();
               while(rsclient.next())
               {
                   int idc=rsclient.getInt("id");
                   String usernamec=rsclient.getString("username");
                   String emailc=rsclient.getString("email");
                   String rolec="yikes";
                   String nomc=rsclient.getString("nom");
                   String prenomc=rsclient.getString("prenom");
                   User client= new User(idc,usernamec,emailc,rolec,nomc,prenomc);
                   clients.add(client);
               }
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
                
               Courses c=new Courses(id,clients.get(clients.size()-1),partenaires.get(partenaires.size()-1),depart,destination,null,prix);
   
               //Courses c=new Courses(id,clients.get(0),partenaires.get(0),depart,destination,null,prix);
     arr.add(c);
     }
    return arr;
    }
    
      public void Delete(int id) throws SQLException
    {
        ste=con.createStatement();
        float pourcentage=0;
        int id_inv=0;
        float montant=0;
        float prix=0;
        int id_com=0;
        ResultSet r=ste.executeQuery("SELECT id,pourcentage,inventairec_id from `commission` where course_id="+id);
          while(r.next())
          {
              id_com=r.getInt("id");
              pourcentage=r.getFloat("pourcentage");
              id_inv=r.getInt("inventairec_id");
          }
         ResultSet r2=ste.executeQuery("SELECT montant from `inventaire_c` where id="+id_inv);
          while(r2.next())
          {
              montant=r2.getFloat("montant");
          }
          ResultSet r3=ste.executeQuery("SELECT prix from `courses` where id="+id);
          while(r3.next())
          {
              prix=r3.getFloat("prix");
          }
          montant-=prix;
          
          ste.executeUpdate("Update inventaire_c set montant="+montant+" where id="+id_inv);
          ste.executeUpdate("DELETE FROM commission where id="+id_com);
          
          
          
          
          
   
        
        
        
    PreparedStatement pre=con.prepareStatement("DELETE FROM `courses` WHERE id=? ;");
    pre.setInt(1,id);
    pre.executeUpdate();
    }
       public void Update(int id, String destination) throws SQLException
    {
    PreparedStatement pre=con.prepareStatement("UPDATE  `courses` SET destination=? WHERE id=? ;");
    pre.setString(1,destination);
     pre.setInt(2,id);
    pre.executeUpdate();   
    }
       
       
       public Courses findCourse(int idcourse) throws SQLException {
  List<Courses> arr=new ArrayList<>();
    List<User>clients= new ArrayList<User>();
    List<User>partenaires= new ArrayList<User>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from courses where id="+idcourse);
     while (rs.next()) {                
               int id=rs.getInt("id");
               String depart=rs.getString("depart");
               String destination=rs.getString("destination");
               int client_id=rs.getInt("client_id");
               int partenaire_id=rs.getInt("partenaire_id");
               float prix=rs.getFloat("prix");
               //recuperer client
                   PreparedStatement pre_client=con.prepareStatement("select * from utilisateurs where id=? ");
               pre_client.setInt(1,client_id);
               ResultSet rsclient=pre_client.executeQuery();
               while(rsclient.next())
               {
                   int idc=rsclient.getInt("id");
                   String usernamec=rsclient.getString("username");
                   String emailc=rsclient.getString("email");
                   String rolec="yikes";
                   String nomc=rsclient.getString("nom");
                   String prenomc=rsclient.getString("prenom");
                   User client= new User(idc,usernamec,emailc,rolec,nomc,prenomc);
                   clients.add(client);
               }
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
                
               Courses c=new Courses(id,clients.get(clients.size()-1),partenaires.get(partenaires.size()-1),depart,destination,null,prix);
   
               //Courses c=new Courses(id,clients.get(0),partenaires.get(0),depart,destination,null,prix);
     arr.add(c);
     }
    return arr.get(0);
    }
       
       
       
    public List<Courses> ChercherCourses(String rech) throws SQLException {
  List<Courses> arr=new ArrayList<>();
    List<User>clients= new ArrayList<User>();
    List<User>partenaires= new ArrayList<User>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from courses where depart like '%"+rech+"%' or destination like '%"+rech+"%' or prix like '%"+rech+"%'  ");
     while (rs.next()) {                
               int id=rs.getInt("id");
               String depart=rs.getString("depart");
               String destination=rs.getString("destination");
               int client_id=rs.getInt("client_id");
               int partenaire_id=rs.getInt("partenaire_id");
               float prix=rs.getFloat("prix");
               //recuperer client
                   PreparedStatement pre_client=con.prepareStatement("select * from utilisateurs where id=? ");
               pre_client.setInt(1,client_id);
               ResultSet rsclient=pre_client.executeQuery();
               while(rsclient.next())
               {
                   int idc=rsclient.getInt("id");
                   String usernamec=rsclient.getString("username");
                   String emailc=rsclient.getString("email");
                   String rolec="yikes";
                   String nomc=rsclient.getString("nom");
                   String prenomc=rsclient.getString("prenom");
                   User client= new User(idc,usernamec,emailc,rolec,nomc,prenomc);
                   clients.add(client);
               }
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
                
               Courses c=new Courses(id,clients.get(clients.size()-1),partenaires.get(partenaires.size()-1),depart,destination,null,prix);
   
               //Courses c=new Courses(id,clients.get(0),partenaires.get(0),depart,destination,null,prix);
     arr.add(c);
     }
    return arr;
    }
    
}
