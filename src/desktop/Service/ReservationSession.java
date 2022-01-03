
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktop.Service;

/**
 *
 * @author anest
 */
public class ReservationSession {
    private static ReservationSession instance;
    private int id_reservation;
    
    
    public ReservationSession(int id) {
        this.id_reservation = id;
    }
    public static ReservationSession getInstance() {
        return instance;
        
    }
    public static void setInstance(ReservationSession instance) {
        ReservationSession.instance = instance; 
    }
     public static ReservationSession getInstace(int id) {
        if(instance == null) {
         instance = new ReservationSession(id);
        }
        return instance;
    }

    @Override
    public String toString() {
        return "ReservationSession{" + "id_reservation=" + id_reservation + '}';
    }

    public int getId_reservation() {
        return id_reservation;
    }

    public void setId_reservation(int id_reservation) {
        this.id_reservation = id_reservation;
    }
      public void cleanUserSession() {
       
        id_reservation = 0;//7 or null
      instance = null;
        
       // or null
    } 
    
}
