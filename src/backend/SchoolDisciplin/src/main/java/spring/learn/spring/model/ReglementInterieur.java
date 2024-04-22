package spring.learn.spring.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "reglementInterieur")
public class ReglementInterieur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idReglementI;
    private String libelle;

    public ReglementInterieur() {
    }

    public ReglementInterieur(long idReglementI, String libelle) {
        this.idReglementI = idReglementI;
        this.libelle = libelle;
    }

    public long getIdReglementI() {
        return idReglementI;
    }

    public void setIdReglementI(long idReglementI) {
        this.idReglementI = idReglementI;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

}
