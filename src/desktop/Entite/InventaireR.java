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
public class InventaireR {
    private int id;
    private int partenaire_id;
    private float montant;
    private Date date_inventaire;
    private int done;
    private String nompartenaire;

    public InventaireR() {
       
    }
    public InventaireR(int id, int partenaire_id, float montant, Date date_inventaire, int done) {
        this.id = id;
        this.partenaire_id = partenaire_id;
        this.montant = montant;
        this.date_inventaire = date_inventaire;
        this.done = done;
    }
    public InventaireR(int id, int partenaire_id, float montant, Date date_inventaire, int done,String nom) {
        this.id = id;
        this.partenaire_id = partenaire_id;
        this.montant = montant;
        this.date_inventaire = date_inventaire;
        this.done = done;
        this.nompartenaire=nom;
    }

    public InventaireR( String  partenaire_id, float montant, Date date_inventaire) {
         this.nompartenaire=partenaire_id;
        this.montant = montant;
        this.date_inventaire = date_inventaire; //To change body of generated methods, choose Tools | Templates.
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

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public Date getDate_inventaire() {
        return date_inventaire;
    }

    public void setDate_inventaire(Date date_inventaire) {
        this.date_inventaire = date_inventaire;
    }

    public int getDone() {
        return done;
    }

    public void setDone(int done) {
        this.done = done;
    }

    public String getNompartenaire() {
        return nompartenaire;
    }

    public void setNompartenaire(String nompartenaire) {
        this.nompartenaire = nompartenaire;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.id;
        hash = 59 * hash + this.partenaire_id;
        hash = 59 * hash + Float.floatToIntBits(this.montant);
        hash = 59 * hash + Objects.hashCode(this.date_inventaire);
        hash = 59 * hash + this.done;
        hash = 59 * hash + Objects.hashCode(this.nompartenaire);
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
        final InventaireR other = (InventaireR) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.partenaire_id != other.partenaire_id) {
            return false;
        }
        if (Float.floatToIntBits(this.montant) != Float.floatToIntBits(other.montant)) {
            return false;
        }
        if (this.done != other.done) {
            return false;
        }
        if (!Objects.equals(this.nompartenaire, other.nompartenaire)) {
            return false;
        }
        if (!Objects.equals(this.date_inventaire, other.date_inventaire)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "InventaireR{" + "id=" + id + ", partenaire_id=" + partenaire_id + ", montant=" + montant + ", date_inventaire=" + date_inventaire + ", done=" + done + ", nompartenaire=" + nompartenaire + '}';
    }



    
    
    

}
