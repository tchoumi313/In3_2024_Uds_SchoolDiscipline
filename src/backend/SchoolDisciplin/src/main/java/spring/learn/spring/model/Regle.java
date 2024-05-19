package spring.learn.spring.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "regles")
public class Regle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idRegle;
    private String libelle;

    public Regle() {
    }

    public Regle(long idRegle, String libelle) {
        this.idRegle = idRegle;
        this.libelle = libelle;
    }

    public long getIdRegle() {
        return idRegle;
    }

    public void setIdRegle(long idRegle) {
        this.idRegle = idRegle;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

}
