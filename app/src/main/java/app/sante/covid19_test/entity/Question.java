package app.sante.covid19_test.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Question implements Serializable {
    private String uid;
    private String libelle;
    private String audio;
    private Integer type;
    private List<ReponsesPossible> reponsesPossibles;

    public Question() {
    }

    public Question(String uid, String libelle, String audio, Integer type, List<ReponsesPossible> reponsesPossibles) {
        this.uid = uid;
        this.libelle = libelle;
        this.audio = audio;
        this.type = type;
        this.reponsesPossibles = reponsesPossibles;
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

    public List<ReponsesPossible> getReponsesPossibles() {
        return reponsesPossibles;
    }

    public void setReponsesPossibles(List<ReponsesPossible> reponsesPossibles) {
        this.reponsesPossibles = reponsesPossibles;
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
