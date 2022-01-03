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
public class ViewCommentaire {
    private int id;
    private String user;
    private String part;
    private String commentaire;
    private String date;

    public ViewCommentaire(int id, String user, String part, String commentaire, String date) {
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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    
}
