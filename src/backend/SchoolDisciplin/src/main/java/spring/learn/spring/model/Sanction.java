package spring.learn.spring.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sanction")
public class Sanction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idSanction;
    private String libelle;
    private String niveauGravite;
    private String motif;
    private Date dureeValidite;

    public Sanction() {
    }

    public Sanction(long idSanction, String libelle, String niveauGravite, String motif, Date dureeValidite) {
        this.idSanction = idSanction;
        this.libelle = libelle;
        this.niveauGravite = niveauGravite;
        this.motif = motif;
        this.dureeValidite = dureeValidite;
    }

    public long getIdSanction() {
        return idSanction;
    }

    public void setIdSanction(long idSanction) {
        this.idSanction = idSanction;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getNiveauGravite() {
        return niveauGravite;
    }

    public void setNiveauGravite(String niveauGravite) {
        this.niveauGravite = niveauGravite;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public Date getDureeValidite() {
        return dureeValidite;
    }

    public void setDureeValidite(Date dureeValidite) {
        this.dureeValidite = dureeValidite;
    }

}
