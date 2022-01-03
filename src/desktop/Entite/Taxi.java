/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktop.Entite;

/**
 *
 * @author nefou
 */
public class Taxi {
    private int matricule;
    private String etat;
    private String marque;
    private String image;

    public Taxi(int matricule, String etat, String marque, String image) {
        this.matricule = matricule;
        this.etat = etat;
        this.marque = marque;
        this.image = image;
    }

    public int getMatricule() {
        return matricule;
    }

    public void setMatricule(int matricule) {
        this.matricule = matricule;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Taxi{" + "matricule=" + matricule + ", etat=" + etat + ", marque=" + marque + ", image=" + image + '}';
    }
    
    
}


