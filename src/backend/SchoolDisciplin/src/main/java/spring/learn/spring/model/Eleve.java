package spring.learn.spring.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
// @Table(name = "eleves")
public class Eleve extends Personne {

    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    // private long id;
    private String solvable;
    private String redoublant;

    public Eleve(long id_personne, String firstName, String lastName, Date dateNaissance, String password,
            String lieuDeNaissance, String photo, String email, String sexe, long telephone, String role,
            String username, String solvable, String redoublant) {
        this.redoublant = redoublant;
    }

    public Eleve() {
    }

    public String getSolvable() {
        return solvable;
    }

    public void setSolvable(String solvable) {
        this.solvable = solvable;
    }

    public String getRedoublant() {
        return redoublant;
    }

    public void setRedoublant(String redoublant) {
        this.redoublant = redoublant;
    }

}
