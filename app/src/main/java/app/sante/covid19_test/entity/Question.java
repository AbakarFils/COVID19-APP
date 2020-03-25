package app.sante.covid19_test.entity;

import java.io.Serializable;
import java.util.List;

public class Question implements Serializable {
    private String uid;
    private String libelle;
    private String audio;
    private Integer type;
    private List<Reponse> reponses;

    public Question() {
    }

    public Question(String uid, String libelle, String audio, Integer type) {
        this.uid = uid;
        this.libelle = libelle;
        this.audio = audio;
        this.type = type;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public List<Reponse> getReponses() {
        return reponses;
    }

    public void setReponses(List<Reponse> reponses) {
        this.reponses = reponses;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
