package spring.learn.spring.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "membreConseils")
public class MembreConseil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idMembreC;
    private long idChef;
    private long idSurveillantG;
    private long idRepresentantE;

    public MembreConseil() {
    }

    public MembreConseil(long idMembreC, long idChef, long idSurveillantG, long idRepresentantE) {
        this.idMembreC = idMembreC;
        this.idChef = idChef;
        this.idSurveillantG = idSurveillantG;
        this.idRepresentantE = idRepresentantE;
    }

    public long getIdMembreC() {
        return idMembreC;
    }

    public void setIdMembreC(long idMembreC) {
        this.idMembreC = idMembreC;
    }

    public long getIdChef() {
        return idChef;
    }

    public void setIdChef(long idChef) {
        this.idChef = idChef;
    }

    public long getIdSurveillantG() {
        return idSurveillantG;
    }

    public void setIdSurveillantG(long idSurveillantG) {
        this.idSurveillantG = idSurveillantG;
    }

    public long getIdRepresentantE() {
        return idRepresentantE;
    }

    public void setIdRepresentantE(long idRepresentantE) {
        this.idRepresentantE = idRepresentantE;
    }

}
