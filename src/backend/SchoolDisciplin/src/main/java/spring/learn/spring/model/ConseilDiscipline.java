package spring.learn.spring.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "conseilDiscipline")
public class ConseilDiscipline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idCd;
    private Date dateCd;
    private Date heureDebutCd;
    private Date heureFinCd;

    public ConseilDiscipline() {
    }

    public ConseilDiscipline(long idCd, Date dateCd, Date heureDebutCd, Date heureFinCd) {
        this.idCd = idCd;
        this.dateCd = dateCd;
        this.heureDebutCd = heureDebutCd;
        this.heureFinCd = heureFinCd;
    }

    public Date getDateCd() {
        return dateCd;
    }

    public void setDateCd(Date dateCd) {
        this.dateCd = dateCd;
    }

    public Date getHeureDebutCd() {
        return heureDebutCd;
    }

    public void setHeureDebutCd(Date heureDebutCd) {
        this.heureDebutCd = heureDebutCd;
    }

    public Date getHeureFinCd() {
        return heureFinCd;
    }

    public void setHeureFinCd(Date heureFinCd) {
        this.heureFinCd = heureFinCd;
    }

    public long getIdCd() {
        return idCd;
    }

    public void setIdCd(long idCd) {
        this.idCd = idCd;
    }
}
