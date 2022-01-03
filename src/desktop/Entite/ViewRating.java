/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktop.Entite;

/**
 *
 * @author USER
 */
public class ViewRating {
    private int id;
    private String user;
    private String Partenaire;
    private int rate;
    private String date;

    public ViewRating(int id, String user, String Partenaire, int rate, String date) {
        this.id = id;
        this.user = user;
        this.Partenaire = Partenaire;
        this.rate = rate;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPartenaire() {
        return Partenaire;
    }

    public void setPartenaire(String Partenaire) {
        this.Partenaire = Partenaire;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    
}
