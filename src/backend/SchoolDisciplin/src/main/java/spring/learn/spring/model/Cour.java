package spring.learn.spring.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cours")
public class Cour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idCours;
    private Date dateCours;
    private Date heureDebut;
    private Date heureFin;
    private String libelle;

    public Cour() {
    }

    public Cour(long idCours, Date dateCours, Date heureDebut, Date heureFin, String libelle) {
        this.idCours = idCours;
        this.dateCours = dateCours;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.libelle = libelle;
    }

    public long getIdCours() {
        return idCours;
    }

    public void setIdCours(long idCours) {
        this.idCours = idCours;
    }

    public Date getDateCours() {
        return dateCours;
    }

    public void setDateCours(Date dateCours) {
        this.dateCours = dateCours;
    }

    public Date getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(Date heureDebut) {
        this.heureDebut = heureDebut;
    }

    public Date getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(Date heureFin) {
        this.heureFin = heureFin;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

}
