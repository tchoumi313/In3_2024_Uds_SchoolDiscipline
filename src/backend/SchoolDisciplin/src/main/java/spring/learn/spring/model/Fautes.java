package spring.learn.spring.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "fautes")
public class Fautes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idFaute;
    private String libelle;
    private String gravite;

    public Fautes() {
    }

    public Fautes(long idFaute, String libelle, String gravite) {
        this.idFaute = idFaute;
        this.libelle = libelle;
        this.gravite = gravite;
    }

    public long getIdFaute() {
        return idFaute;
    }

    public void setIdFaute(long idFaute) {
        this.idFaute = idFaute;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getGravite() {
        return gravite;
    }

    public void setGravite(String gravite) {
        this.gravite = gravite;
    }

}
