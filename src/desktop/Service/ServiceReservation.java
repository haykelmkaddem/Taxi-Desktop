/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktop.Service;

import desktop.Entite.InventaireR;
import desktop.Entite.User;
import desktop.Entite.commissionR;
import desktop.Entite.reservation;
import desktop.Utils.DataBase;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import static javax.swing.UIManager.getInt;


/**
 *
 * @author anest
 */
public class ServiceReservation {

    private Connection con;
    private Statement ste;
    private PreparedStatement pre;

    public ServiceReservation() {
        con = DataBase.getInstance().getConnection();
    }


    public void Accept(int id) throws SQLException {
        pre = con.prepareStatement("update `pidev`.`reservation` set etat='accepté' where id =(?)");
        pre.setInt(1, id);
        pre.executeUpdate();
        System.out.println("ok");

    }

    public void refuse(int id) throws SQLException {
        pre = con.prepareStatement("update `pidev`.`reservation` set etat='refusé' where id =(?)");
        pre.setInt(1, id);
        pre.executeUpdate();
        System.out.println("ok");

    }


    
    
     public void ajouter(reservation R) throws SQLException
    {
        List<Integer> idinv = new ArrayList<>();
        List<Integer> idRes = new ArrayList<>();
        List<Float> montantinv = new ArrayList<>();
        java.sql.Date sqlDate = java.sql.Date.valueOf(R.getDate_reservation().toLocalDate());
        pre = con.prepareStatement("INSERT INTO `pidev`.`reservation` ( `client_id`,`partenaire_id`,`pointAchat`,`destination`,`date`,`prix`,`listAchats`,`remarques`) VALUES ( ?, ?,?,?,?,?,?,?);");
    pre.setInt(1, R.getClient_id());
    pre.setInt(2, R.getPartenaire_id());
    pre.setString(3, R.getPointAchat());
    pre.setString(4, R.getDestination());
    pre.setDate(5, sqlDate);
    pre.setFloat(6, R.getPrix()); 
       pre.setString(7, R.getListAchats());
    pre.setString(8, R.getRemarques());
        
    pre.executeUpdate();
    //creation de la commission
    commissionR com=new commissionR();
    com.setReservation(R);
    com.setPourcentage((float) 0.15);
    com.setDate_commission(R.getDate_reservation());
    
    
    
    //creation de l'inventaire
    int x=0;
    List<InventaireR> inventaires=new ArrayList<>();
    InventaireR i=new InventaireR();
    PreparedStatement pre_inv=con.prepareStatement("select * from inventaire_r where partenaire_id=? and done=0 ");
               pre_inv.setInt(1,R.getPartenaire_id());
               ResultSet rs_inv=pre_inv.executeQuery();
               while(rs_inv.next())
               {
                   x++;
               }
    if(x==0)
    {
        i.setPartenaire_id(R.getPartenaire_id());
        i.setMontant((float) (R.getPrix()*0.15));
        i.setDate_inventaire(R.getDate_reservation());
        i.setDone(0);
        PreparedStatement preinsInv=con.prepareStatement("INSERT INTO `inventaire_r` ( `id`, `partenaire_id`,`montant`, `date_i`,`done`) VALUES ( null,?,?,?,0);");
        preinsInv.setInt(1, R.getPartenaire_id());
        preinsInv.setFloat(2, (float) (R.getPrix()*0.15));
        preinsInv.setDate(3, sqlDate);
        preinsInv.executeUpdate();
        
        PreparedStatement pre_inv2=con.prepareStatement("select id from inventaire_r where partenaire_id=? and done=0");
               pre_inv2.setInt(1,R.getPartenaire_id());
               ResultSet rs_inv2=pre_inv2.executeQuery();
               while(rs_inv2.next())
               {
                   int id=rs_inv2.getInt("id");
                   idinv.add(id);
                   
               }
        
        
    }
    else
    {

            PreparedStatement re_inv = con.prepareStatement("select * from inventaire_r where partenaire_id=? and done=0 ");
            
               pre_inv.setInt(1,R.getPartenaire_id());
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
              PreparedStatement preupdate=con.prepareStatement("UPDATE  `inventaire_r` SET montant=? WHERE id=? ;");
                 preupdate.setFloat(1,montantinv.get(0)+200);
                 preupdate.setInt(2,idinv.get(0));
                 preupdate.executeUpdate();   
             
               
     }
    //recuperation du courses ajouté pour l id
    PreparedStatement cour= con.prepareStatement("select * from reservation ORDER BY id DESC LIMIT 1 ");
            
               
               ResultSet rs_cour=cour.executeQuery();
               while(rs_cour.next())
               {
                    int id=rs_cour.getInt("id");
                     idRes.add(id);
                    
               }
               System.out.println( idRes.get(0));
    //Ajout de commission
    PreparedStatement preComm=con.prepareStatement("INSERT INTO `commission_r` ( `id`, `partenaire_id`,`reservation_id`, `pourcentage`,`date_commission`,`inventaireR_id`) VALUES ( null,?,?,?,?,?);");
        preComm.setInt(1, R.getPartenaire_id());
        preComm.setInt(2, idRes.get(0));
        preComm.setFloat(3,com.getPourcentage());
        preComm.setDate(4, sqlDate);
        preComm.setInt(5,idinv.get(0));
        preComm.executeUpdate();
  
   
    
    
    }
      
      
      
    
    


    public boolean delete(float montant, int id, int id_r) throws SQLException {

        ServiceInventaire I = new ServiceInventaire();
        if (I.compare(montant, id, id_r)) {
            pre = con.prepareStatement("delete from `pidev`.`reservation` where id = '" + id_r + "'");

            System.out.println(pre.execute());
            return true;

        } else {
            return false;
        }
    }

    public boolean chercher(int id) throws SQLException {
        String req = "select * from reservation where etat ='non traite'";
        List<Integer> list = new ArrayList<>();

        try {
            ste = con.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while (rs.next()) {
                list.add(rs.getInt(1));

            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReservation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list.contains(id);
    }
    public boolean chercher2(int id) throws SQLException {
        String req = "select * from reservation where etat!='non traite'";
        List<Integer> list = new ArrayList<>();

        try {
            ste = con.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while (rs.next()) {
                list.add(rs.getInt(1));

            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReservation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list.contains(id);
    }
    public List<reservation> readTraited(int from, int to) throws SQLException {
                System.out.println("The total pay is " + from + to);
        String req = "select * from reservation where etat!='non traite' limit " + from + ", " + to + " " ;
    
     
        List<reservation> list = new ArrayList<>();
 List<User>CLS= new ArrayList<User>();
        try {
            ste = con.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while (rs.next()) {
   java.sql.Date sqlDate = java.sql.Date.valueOf(rs.getDate(6).toLocalDate());
    PreparedStatement pre_partenaire=con.prepareStatement("select * from utilisateurs where id=? ");
               pre_partenaire.setInt(1,rs.getInt(2));
               ResultSet Clients=pre_partenaire.executeQuery();
  while(Clients.next())
               {
                   int idc=Clients.getInt("id");
                   String username=Clients.getString("username");
                   String email=Clients.getString("email");
                   String role=Clients.getString("roles");
                   String nom=Clients.getString("nom");
                   String prenom=Clients.getString("prenom");
                   User Client= new User(idc,username,email,role,nom,prenom);
                   CLS.add(Client);
               }
       // CLS.forEach(System.out::println);
               list.add(new reservation(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5),  sqlDate, rs.getFloat(7), rs.getString(8), rs.getString(9), rs.getString(10),CLS.get(0)));

            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReservation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }
    public List<reservation> readTraited2() throws SQLException {
              
        String req = "select * from reservation where etat!='non traite'  " ;
    
     
        List<reservation> list = new ArrayList<>();
 List<User>CLS= new ArrayList<User>();
        try {
            ste = con.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while (rs.next()) {
   java.sql.Date sqlDate = java.sql.Date.valueOf(rs.getDate(6).toLocalDate());
    PreparedStatement pre_partenaire=con.prepareStatement("select * from utilisateurs where id=? ");
               pre_partenaire.setInt(1,rs.getInt(2));
               ResultSet Clients=pre_partenaire.executeQuery();
  while(Clients.next())
               {
                   int idc=Clients.getInt("id");
                   String username=Clients.getString("username");
                   String email=Clients.getString("email");
                   String role=Clients.getString("roles");
                   String nom=Clients.getString("nom");
                   String prenom=Clients.getString("prenom");
                   User Client= new User(idc,username,email,role,nom,prenom);
                   CLS.add(Client);
               }
       // CLS.forEach(System.out::println);
             list.add(new reservation(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5),  sqlDate, rs.getFloat(7), rs.getString(8), rs.getString(9), rs.getString(10),CLS.get(0)));

            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReservation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }
    public List<reservation> readNottraited() throws SQLException {
        String req = "select * from reservation where etat ='non traite'";
        List<reservation> list = new ArrayList<>();
 List<User>CLS= new ArrayList<User>();
        try {
            ste = con.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while (rs.next()) {
   java.sql.Date sqlDate = java.sql.Date.valueOf(rs.getDate(6).toLocalDate());
       PreparedStatement pre_partenaire=con.prepareStatement("select * from utilisateurs where id=? ");
               pre_partenaire.setInt(1,rs.getInt(2));
               ResultSet Clients=pre_partenaire.executeQuery();
  while(Clients.next())
               {
                   int idc=Clients.getInt("id");
                   String username=Clients.getString("username");
                   String email=Clients.getString("email");
                   String role=Clients.getString("roles");
                   String nom=Clients.getString("nom");
                   String prenom=Clients.getString("prenom");
                   User Client= new User(idc,username,email,role,nom,prenom);
                   CLS.add(Client);
               }
              //  System.out.println("test");
       //CLS.forEach(System.out::println);
                list.add(new reservation(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5),sqlDate, rs.getFloat(7), rs.getString(8), rs.getString(9), rs.getString(10),CLS.get(0)));

            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReservation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    public List<reservation> details(int id) throws SQLException {

        String req = "select * from reservation where id ='" + id + "'";

        List<reservation> list = new ArrayList<>();
 List<User>CLS= new ArrayList<User>();
        try {
            ste = con.createStatement();

            ResultSet rs = ste.executeQuery(req);
            while (rs.next()) {
                    PreparedStatement pre_partenaire=con.prepareStatement("select * from utilisateurs where id=? ");
               pre_partenaire.setInt(1,rs.getInt(2));
               ResultSet Clients=pre_partenaire.executeQuery();
  while(Clients.next())
               {
                   int idc=Clients.getInt("id");
                   String username=Clients.getString("username");
                   String email=Clients.getString("email");
                   String role=Clients.getString("roles");
                   String nom=Clients.getString("nom");
                   String prenom=Clients.getString("prenom");
                       String mobile=Clients.getString("telephone");
                   User Client= new User(idc,username,email,role,nom,prenom,mobile);
                   CLS.add(Client);
               }
       // CLS.forEach(System.out::println);
                list.add(new reservation(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getDate(6), rs.getFloat(7), rs.getString(8), rs.getString(9), rs.getString(10),CLS.get(0)));

            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReservation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    public reservation details2(int id) throws SQLException {

        String req = "select * from reservation where id ='" + id + "'";

        reservation R = null;
         List<User>CLS= new ArrayList<User>();
        ste = con.createStatement();

        ResultSet rs = ste.executeQuery(req);

        while (rs.next()) {
                         PreparedStatement pre_partenaire=con.prepareStatement("select * from utilisateurs where id=? ");
               pre_partenaire.setInt(1,rs.getInt(2));
               ResultSet Clients=pre_partenaire.executeQuery();
            while(Clients.next())
               {
                   
                   int idc=Clients.getInt("id");
                   String username=Clients.getString("username");
                   String email=Clients.getString("email");
                   String role=Clients.getString("roles");
                   String nom=Clients.getString("nom");
                   String prenom=Clients.getString("prenom");
                           String mobile=Clients.getString("telephone");
                   User Client= new User(idc,username,email,role,nom,prenom,mobile);
                   CLS.add(Client);
               }
     // CLS.forEach(System.out::println);           
 R = new reservation(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getDate(6), rs.getFloat(7), rs.getString(8), rs.getString(9), rs.getString(10),CLS.get(0));

        }

        return R;
    }

    public void restaurer(int id) throws SQLException {
        pre = con.prepareStatement("update `pidev`.`reservation` set etat='non traité' where id =(?)");
        pre.setInt(1, id);
        pre.executeUpdate();
        System.out.println("ok");

    }
    
    public boolean envoyerMail(String fromMail ,String toMail)
    {
        
        try{
            String host ="smtp.gmail.com" ;
            String user = "twasalniapp@gmail.com";
            String pass = "twasalni123";
            String to = toMail;
            String from = "Twasalni?";
            String subject = "Reservation Taxi";
            String messageText ="Votre Reservation est confirmé , merci ";
            boolean sessionDebug = false;

            Properties props = System.getProperties();

            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.required", "true");

            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject); msg.setSentDate(new java.util.Date());
            msg.setText(messageText);

           Transport transport=mailSession.getTransport("smtp");
           transport.connect(host, user, pass);
           
           transport.sendMessage(msg, msg.getAllRecipients());
           System.out.println("message send successfully");
     
       //    transport.close();
         return   transport.isConnected();
       
        
        }catch(Exception ex)
        {
            System.out.println(ex);
        }
        
        return false;
 
    }
          public int TotalAccepted() {
       int nb=0;
       String req = "SELECT count(id) from reservation where etat='accepté'";
       PreparedStatement preparedStatement;
        try {
            preparedStatement = con.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            nb=resultSet.getInt(1);
        } catch (SQLException ex) {
                 Logger.getLogger(ServiceReservation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return  nb;
    }
           public int TotalRefused() {
       int nb=0;
       String req = "SELECT count(id) from reservation where etat='refusé'";
       PreparedStatement preparedStatement;
        try {
            preparedStatement = con.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            nb=resultSet.getInt(1);
        } catch (SQLException ex) {
                      Logger.getLogger(ServiceReservation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return  nb;
    }
           
               public int counttraited()  {
        try {
            String req = "select count(*) AS total from reservation where etat !='non traite'";
            ste = con.createStatement();
            ResultSet rs = ste.executeQuery(req);
            rs.next();
            int count = rs.getInt(1);
            System.out.println(count);
            return count;
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReservation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
               
               
    public int countNottraited() throws SQLException {
        String req = "select count(*) AS total from reservation where etat ='non traite'";
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery(req);
        rs.next();
        int count = rs.getInt(1);
        System.out.println(count);
        return count;
    }



}
