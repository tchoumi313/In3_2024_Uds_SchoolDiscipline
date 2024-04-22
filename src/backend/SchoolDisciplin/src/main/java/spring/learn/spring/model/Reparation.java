package spring.learn.spring.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "reparation")
public class Reparation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idReparation;
    private String demarcheMediation;

    public Reparation() {
    }

    public Reparation(long idReparation, String demarcheMediation) {
        this.idReparation = idReparation;
        this.demarcheMediation = demarcheMediation;
    }

    public long getIdReparation() {
        return idReparation;
    }

    public void setIdReparation(long idReparation) {
        this.idReparation = idReparation;
    }

    public String getDemarcheMediation() {
        return demarcheMediation;
    }

    public void setDemarcheMediation(String demarcheMediation) {
        this.demarcheMediation = demarcheMediation;
    }

}
