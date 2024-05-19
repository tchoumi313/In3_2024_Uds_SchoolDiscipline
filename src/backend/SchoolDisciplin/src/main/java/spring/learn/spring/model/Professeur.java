package spring.learn.spring.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "professeurs")
public class Professeur extends User {
    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    // private long idProfesseur;
    private String statut;

    public Professeur() {
    }



    // public long getIdProfesseur() {
    //     return idProfesseur;
    // }

    // public void setIdProfesseur(long idProfesseur) {
    //     this.idProfesseur = idProfesseur;
    // }

    public Professeur(String statut) {
        this.statut = statut;
    }



    public Professeur(Integer userId, String statut) {
        super(userId);
        this.statut = statut;
    }



    public Professeur(String password, String username, String userEmail, Date createdOn, String statut) {
        super(password, username, userEmail, createdOn);
        this.statut = statut;
    }



    public Professeur(String password, String username, String userEmail, String firstName, String lastName,
            Date createdOn, String statut) {
        super(password, username, userEmail, firstName, lastName, createdOn);
        this.statut = statut;
    }



    public Professeur(String password, String username, String userEmail, String firstName, String lastName, Integer id,
            String emailList, String statut) {
        super(password, username, userEmail, firstName, lastName, id, emailList);
        this.statut = statut;
    }



    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

}
