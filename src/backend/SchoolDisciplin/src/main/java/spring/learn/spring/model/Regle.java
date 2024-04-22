package spring.learn.spring.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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
