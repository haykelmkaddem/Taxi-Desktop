/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktop.Entite;
import java.util.Date;
import java.util.Objects;
/**
 *
 * @author anest
 */
public class commissionR {
      private int id;
    private int partenaire_id;
    private reservation reservation;
    private int inventaire_id;
    private float pourcentage;
    private Date date_commission;
    private float prix;
   private String destination;
    private String depart;
  public commissionR() {
      
    }
    public commissionR(int id, int partenaire_id, reservation reservation, int inventaire_id, float pourcentage, Date date_commission) {
        this.id = id;
        this.partenaire_id = partenaire_id;
        this.reservation = reservation;
        this.inventaire_id = inventaire_id;
        this.pourcentage = pourcentage;
        this.date_commission = date_commission;
    }

      public commissionR(int id,reservation R, float pourcentage) {
        this.reservation = R;
        this.pourcentage = pourcentage;
        this.date_commission = date_commission;
        this.id=id;
        destination=reservation.getDestination();
        depart=reservation.getPointAchat();
        prix=reservation.getPrix()*100;
       
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPartenaire_id() {
        return partenaire_id;
    }

    public void setPartenaire_id(int partenaire_id) {
        this.partenaire_id = partenaire_id;
    }

    public reservation getReservation() {
        return reservation;
    }

    public void setReservation(reservation reservation) {
        this.reservation = reservation;
    }


    public int getInventaire_id() {
        return inventaire_id;
    }

    public void setInventaire_id(int inventaire_id) {
        this.inventaire_id = inventaire_id;
    }

    public float getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(float pourcentage) {
        this.pourcentage = pourcentage;
    }

    public Date getDate_commission() {
        return date_commission;
    }

    public void setDate_commission(Date date_commission) {
        this.date_commission = date_commission;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.id;
        hash = 79 * hash + this.partenaire_id;
        hash = 79 * hash + Objects.hashCode(this.reservation);
        hash = 79 * hash + this.inventaire_id;
        hash = 79 * hash + Float.floatToIntBits(this.pourcentage);
        hash = 79 * hash + Objects.hashCode(this.date_commission);
        hash = 79 * hash + Float.floatToIntBits(this.prix);
        hash = 79 * hash + Objects.hashCode(this.destination);
        hash = 79 * hash + Objects.hashCode(this.depart);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final commissionR other = (commissionR) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.partenaire_id != other.partenaire_id) {
            return false;
        }
        if (this.inventaire_id != other.inventaire_id) {
            return false;
        }
        if (Float.floatToIntBits(this.pourcentage) != Float.floatToIntBits(other.pourcentage)) {
            return false;
        }
        if (Float.floatToIntBits(this.prix) != Float.floatToIntBits(other.prix)) {
            return false;
        }
        if (!Objects.equals(this.destination, other.destination)) {
            return false;
        }
        if (!Objects.equals(this.depart, other.depart)) {
            return false;
        }
        if (!Objects.equals(this.reservation, other.reservation)) {
            return false;
        }
        if (!Objects.equals(this.date_commission, other.date_commission)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "commissionR{" + "id=" + id + ", partenaire_id=" + partenaire_id + ", reservation=" + reservation + ", inventaire_id=" + inventaire_id + ", pourcentage=" + pourcentage + ", date_commission=" + date_commission + ", prix=" + prix + ", destination=" + destination + ", depart=" + depart + '}';
    }

   

   
    
    
}
