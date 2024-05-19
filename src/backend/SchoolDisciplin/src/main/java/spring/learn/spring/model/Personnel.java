package spring.learn.spring.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "personnels")
public class Personnel extends Personne {

    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    // private long id;
    private String fonction;

    public Personnel() {
    }

    public Personnel(long id_personne, String firstName, String lastName, Date dateNaissance, String password,
            String lieuDeNaissance, String photo, String email, String sexe, long telephone, String role,
            String username, String fonction) {
        super(id_personne, firstName, lastName, dateNaissance, password, lieuDeNaissance, photo, email, sexe, telephone,
                role, username);
        this.fonction = fonction;
    }

    public String getFonction() {
        return fonction;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }

}
