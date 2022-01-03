/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktop.Service;

import desktop.Entite.Inventaire;
import desktop.Entite.InventaireR;
import desktop.Entite.User;
import desktop.Entite.reservation;
import desktop.Utils.DataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author anest
 */
public class ServiceInventaire {

    private Connection con;
    private Statement ste;
    private PreparedStatement pre;

    public ServiceInventaire() {
        con = DataBase.getInstance().getConnection();
    }

    public float Montant(int id) throws SQLException {
        String req = "select montant  from inventaire_r  where partenaire_id='" + id + "'";

        ste = con.createStatement();

        ResultSet rs = ste.executeQuery(req);
        rs.next();
        float montant = rs.getFloat(1);

        return montant;
    }

    public boolean compare(float prix, int id, int id_r) throws SQLException {
        ServiceCommission c = new ServiceCommission();
        float p = c.pourcentage(id_r);
        float v1 = Montant(id);
        float d = v1 -(prix * p);
        
        if (d == 0) {
            if (delete_inv(id)) {
                c.delete_comm(id_r);
                return true;
            }
        } else {
            if (update(d, id)) {
                c.delete_comm(id_r);
                return true;
            }
        }
        return false;
    }

    public boolean delete_inv(int id) throws SQLException {

        pre = con.prepareStatement("delete from inventaire_r where partenaire_id=(?)");
        pre.setInt(1, id);
        System.out.println(pre.execute());
        return true;
    }

    public boolean update(float d, int id) throws SQLException {
        pre = con.prepareStatement("update inventaire_r set montant='" + d + "' where partenaire_id='" + id + "'");
        pre.executeUpdate();
        System.out.println("ok");
        return true;
    }
    
    
    public List<InventaireR> readpaid() throws SQLException {
              
          String req = "select * from inventaire_r where done =1";
    
     
        List<InventaireR> list = new ArrayList<>();
 String usernamep = "";
        try {
            ste = con.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while (rs.next()) {
  PreparedStatement pre_partenaire=con.prepareStatement("select * from utilisateurs where id=? ");
               pre_partenaire.setInt(1,rs.getInt(2));
               ResultSet rspartenaire=pre_partenaire.executeQuery();
               while(rspartenaire.next())
               {
        
                   usernamep=rspartenaire.getString("username");
               }
                list.add(new InventaireR(usernamep, rs.getFloat(3),  rs.getDate(4)));

            }
           
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReservation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }
    
       public List<InventaireR> readNotpaid() throws SQLException {
              
          String req = "select * from inventaire_r where done =0";
    
     
        List<InventaireR> list = new ArrayList<>();
 String usernamep = "";
        try {
            ste = con.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while (rs.next()) {
   java.sql.Date sqlDate = java.sql.Date.valueOf(rs.getDate(4).toLocalDate());
            PreparedStatement pre_partenaire=con.prepareStatement("select * from utilisateurs where id=? ");
               pre_partenaire.setInt(1,rs.getInt(2));
               ResultSet rspartenaire=pre_partenaire.executeQuery();
               while(rspartenaire.next())
               {
        
                   usernamep=rspartenaire.getString("username");
               }
                list.add(new InventaireR(usernamep, rs.getFloat(3),  rs.getDate(4)));

            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReservation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

   public boolean payer( int id) throws SQLException {
        pre = con.prepareStatement("update inventaire_r set done=1 where partenaire_id='" + id + "'");
        pre.executeUpdate();
        System.out.println("ok");
        return true;
    }
    
    


}
