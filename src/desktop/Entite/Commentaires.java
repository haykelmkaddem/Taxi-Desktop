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
public class Commentaires {
    private int id;
    private int user;
    private int part;
    private String commentaire;
    private Date date;

    public Commentaires(int id, int user, int part, String commentaire, Date date) {
        this.id = id;
        this.user = user;
        this.part = part;
        this.commentaire = commentaire;
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

    public int getPart() {
        return part;
    }

    public void setPart(int part) {
        this.part = part;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Commentaires other = (Commentaires) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.user != other.user) {
            return false;
        }
        if (this.part != other.part) {
            return false;
        }
        if (!Objects.equals(this.commentaire, other.commentaire)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        return true;
    }
    
    
}
