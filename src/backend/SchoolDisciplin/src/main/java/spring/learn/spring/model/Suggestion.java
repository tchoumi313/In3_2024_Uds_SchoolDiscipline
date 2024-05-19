package spring.learn.spring.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "suggestion")
public class Suggestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_personne_suggestion;

    private String libelle;
    private Boolean view;
    private String nom_emetteur;

    public Suggestion() {
    }

    public Suggestion(long id_personne_suggestion, String libelle, Boolean view, String nom_emetteur) {
        this.id_personne_suggestion = id_personne_suggestion;
        this.libelle = libelle;
        this.view = view;
        this.nom_emetteur = nom_emetteur;
    }

    public long getId_personne_suggestion() {
        return id_personne_suggestion;
    }

    public void setId_personne_suggestion(long id_personne_suggestion) {
        this.id_personne_suggestion = id_personne_suggestion;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Boolean getView() {
        return view;
    }

    public void setView(Boolean view) {
        this.view = view;
    }

    public String getNom_emetteur() {
        return nom_emetteur;
    }

    public void setNom_emetteur(String nom_emetteur) {
        this.nom_emetteur = nom_emetteur;
    }

}
