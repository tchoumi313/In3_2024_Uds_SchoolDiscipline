package spring.learn.spring.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "convocations")
public class Convocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_convocation;
    private String libelle;
    private Date dateConvocation;
    private String statut;
    private Date dateRdv;

    public Convocation() {
    }

    public Convocation(long id_convocation, String libelle, Date dateConvocation, String statut, Date dateRdv) {
        this.id_convocation = id_convocation;
        this.libelle = libelle;
        this.dateConvocation = dateConvocation;
        this.statut = statut;
        this.dateRdv = dateRdv;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Date getDateConvocation() {
        return dateConvocation;
    }

    public void setDateConvocation(Date dateConvocation) {
        this.dateConvocation = dateConvocation;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Date getDateRdv() {
        return dateRdv;
    }

    public void setDateRdv(Date dateRdv) {
        this.dateRdv = dateRdv;
    }

    public long getId_convocation() {
        return id_convocation;
    }

    public void setId_convocation(long id_convocation) {
        this.id_convocation = id_convocation;
    }
}
