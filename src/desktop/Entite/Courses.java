/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktop.Entite;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author Arbi
 */
public class Courses {
    private int id;
    private User client;
    private User partenaire;
    private String depart;
    private String destination;
    private LocalDateTime date_course;
    private float prix;
    private String nompartenaire;
    private String nomclient;

    public Courses(int id,User client, User partenaire, String depart, String destination, LocalDateTime date_course, float prix) {
        this.id = id;
        this.client = client;
        this.partenaire = partenaire;
        this.depart = depart;
        this.destination = destination;
        this.date_course = date_course;
        this.prix = prix;
        this.nompartenaire= this.partenaire.getNom();
        this.nomclient= this.client.getNom();
    }

    public String getNomclient() {
        return nomclient;
    }

    public void setNomclient(String nomclient) {
        this.nomclient = nomclient;
    }

    public String getNompartenaire() {
        return nompartenaire;
    }

    public void setNompartenaire(String nompartenaire) {
        this.nompartenaire = nompartenaire;
    }

    public int getId() {
        return id;
    }

   /* public void setId(int id) {
        this.id = id;
    }
*/
    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public User getPartenaire() {
        return partenaire;
    }

    public void setPartenaire(User partenaire) {
        this.partenaire = partenaire;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDateTime getDate_course() {
        return date_course;
    }

    public void setDate_course(LocalDateTime date_course) {
        this.date_course = date_course;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }
    
    
}
