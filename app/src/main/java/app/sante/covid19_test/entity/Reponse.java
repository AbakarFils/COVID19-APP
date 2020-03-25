package app.sante.covid19_test.entity;

import java.io.Serializable;

public class Reponse implements Serializable {

    private int type;
    private int etat;
    private String uid;
    private String nom;
    private boolean chosed;
    private boolean selected;


    public Reponse(int type, int etat, String uid, String nom, boolean chosed, boolean selected) {
        this.type = type;
        this.etat = etat;
        this.uid = uid;
        this.nom = nom;
        this.chosed = chosed;
        this.selected = selected;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public boolean isChosed() {
        return chosed;
    }

    public void setChosed(boolean chosed) {
        this.chosed = chosed;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
