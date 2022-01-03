/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktop.Entite;

import java.sql.Date;
import java.time.LocalDateTime;

/**
 *
 * @author Arbi
 */
public class Commission {
    private int id;
    private Courses course;
    private float pourcentage;
    private LocalDateTime date_comission;
    private Inventaire inventaire;
    private String destination;
    private String depart;
    private float prix;
    
    public Commission()
    {
        
    }
    public Commission(int id,Courses course, float pourcentage) {
        this.course = course;
        this.pourcentage = pourcentage;
        this.date_comission = date_comission;
        this.id=id;
        destination=course.getDestination();
        depart=course.getDepart();
        prix=course.getPrix()*100;
       
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

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public Commission(Courses course, float pourcentage, LocalDateTime date_comission, Inventaire inventaire) {
        this.course = course;
        this.pourcentage = pourcentage;
        this.date_comission = date_comission;
        this.inventaire = inventaire;
    }
    
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Courses getCourse() {
        return course;
    }

    public void setCourse(Courses course) {
        this.course = course;
    }

    public float getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(float pourcentage) {
        this.pourcentage = pourcentage;
    }

    public LocalDateTime getDate_comission() {
        return date_comission;
    }

    public void setDate_comission(LocalDateTime date_comission) {
        this.date_comission = date_comission;
    }

    public Inventaire getInventaire() {
        return inventaire;
    }

    public void setInventaire(Inventaire inventaire) {
        this.inventaire = inventaire;
    }
    
    
}
