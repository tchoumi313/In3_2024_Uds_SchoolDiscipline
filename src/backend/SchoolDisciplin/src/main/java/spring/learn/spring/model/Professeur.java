package spring.learn.spring.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "professeurs")
public class Professeur extends Personne {
    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    // private long idProfesseur;
    private String statut;

    public Professeur() {
    }

    public Professeur(long id_personne, String firstName, String lastName, Date dateNaissance, String password,
            String lieuDeNaissance, String photo, String email, String sexe, long telephone, String role,
            String username, String statut) {
        super(id_personne, firstName, lastName, dateNaissance, password, lieuDeNaissance, photo, email, sexe, telephone,
                role, username);
        this.statut = statut;
    }

    // public long getIdProfesseur() {
    //     return idProfesseur;
    // }

    // public void setIdProfesseur(long idProfesseur) {
    //     this.idProfesseur = idProfesseur;
    // }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

}
