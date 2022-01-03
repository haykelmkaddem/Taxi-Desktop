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
 * @author USER
 */
public class Reclammations {
    int id;
    int user;
    String sujet;
    String contenu;
    String etat;
    Date date;

    public Reclammations(int id, int user, String sujet, String contenu, String etat, Date date) {
        this.id = id;
        this.user = user;
        this.sujet = sujet;
        this.contenu = contenu;
        this.etat = etat;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + this.id;
        hash = 43 * hash + this.user;
        hash = 43 * hash + Objects.hashCode(this.sujet);
        hash = 43 * hash + Objects.hashCode(this.contenu);
        hash = 43 * hash + Objects.hashCode(this.etat);
        hash = 43 * hash + Objects.hashCode(this.date);
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
        final Reclammations other = (Reclammations) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.user != other.user) {
            return false;
        }
        if (!Objects.equals(this.sujet, other.sujet)) {
            return false;
        }
        if (!Objects.equals(this.contenu, other.contenu)) {
            return false;
        }
        if (!Objects.equals(this.etat, other.etat)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        return true;
    }
    
    
}
