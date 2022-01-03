/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktop.Entite;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Arbi
 */
public class Inventaire {
    private int id;
    private User partenaire;
    private float montant;
    private LocalDateTime date_i;
    private int paye;
    private String nompartenaire;
    private String date;
    
    public Inventaire(int id,User partenaire, float montant, LocalDateTime date_i, int paye) {
        this.id=id;
        this.partenaire = partenaire;
        this.montant = montant;
        this.date_i = date_i;
        this.paye = paye;
        this.nompartenaire=partenaire.getNom();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        this.date = date_i.format(formatter);
    }

    public String getNompartenaire() {
        return nompartenaire;
    }

    public void setNompartenaire(String nompartenaire) {
        this.nompartenaire = nompartenaire;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Inventaire() {
    }

    public int getId() {
        return id;
    }
    

    public void setId(int id) {
        this.id = id;
    }

    public User getPartenaire() {
        return partenaire;
    }

    public void setPartenaire(User partenaire) {
        this.partenaire = partenaire;
    }

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public LocalDateTime getDate_i() {
        return date_i;
    }

    public void setDate_i(LocalDateTime date_i) {
        this.date_i = date_i;
    }

    public int getPaye() {
        return paye;
    }

    public void setPaye(int paye) {
        this.paye = paye;
    }
}
