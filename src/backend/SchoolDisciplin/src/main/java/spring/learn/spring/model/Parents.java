package spring.learn.spring.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "parents")
public class Parents extends Personne {

    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    // private long id;
    private String profession;

    public Parents(long id_personne, String firstName, String lastName, Date dateNaissance, String password,
            String lieuDeNaissance, String photo, String email, String sexe, long telephone, String role,
            String username, String profession) {
        super(id_personne, firstName, lastName, dateNaissance, password, lieuDeNaissance, photo, email, sexe, telephone,
                role, username);
        this.profession = profession;
    }

    public Parents() {
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

}
