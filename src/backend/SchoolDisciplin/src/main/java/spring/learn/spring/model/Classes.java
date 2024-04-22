package spring.learn.spring.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "classes")
public class Classes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idClasse;
    private String name;
    private String shortName;
    private String speciality;
    private String no;
    private long effectif;

    public Classes() {
    }

    public Classes(Long idClasse, String name, String shortName, String speciality, String no, long effectif) {
        this.idClasse = idClasse;
        this.name = name;
        this.shortName = shortName;
        this.speciality = speciality;
        this.no = no;
        this.effectif = effectif;
    }

    public Long getIdClasse() {
        return idClasse;
    }

    public void setIdClasse(Long idClasse) {
        this.idClasse = idClasse;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public long getEffectif() {
        return effectif;
    }

    public void setEffectif(long effectif) {
        this.effectif = effectif;
    }

}
