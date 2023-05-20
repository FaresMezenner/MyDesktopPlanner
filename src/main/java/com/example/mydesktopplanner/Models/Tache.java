package com.example.mydesktopplanner.Models;// Cette classe contiens les informations d'une tache
// Cette classe n'est pas encore finie (il manque les méthodes)


import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;


public abstract class Tache implements Serializable, Comparable<Tache> {
    private String nom;
    private Duration duree;
    private Priorite priorite;
    private LocalDateTime dateLimite;   // Dans ce projet , la limite est une date et non une heure
    private Categorie categorie;

    private Etat etat = Etat.UNSCHEDULED;

    public Tache(String nom, Duration duree, Priorite priorite, LocalDateTime dateLimite, Categorie categorie) {
        this.nom = nom;
        this.duree = duree;
        this.priorite = priorite;
        this.dateLimite = dateLimite;
        this.categorie = categorie;
    }

    public String getNom() {
        return nom;
    }

    // -------------------------------------- Delimitation Setters/Getters --------------------------------------



    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Duration getDuree() {
        return duree;
    }

    public void setDuree(Duration duree) {
        this.duree = duree;
    }

    public Priorite getPriorite() {
        return priorite;
    }

    public void setPriorite(Priorite priorite) {
        this.priorite = priorite;
    }

    public LocalDateTime getDateLimite() {
        return dateLimite;
    }

    public void setDateLimite(LocalDateTime dateLimite) {
        this.dateLimite = dateLimite;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }
    // -------------------------------------- Delimitation Setters/Getters --------------------------------------

    abstract boolean isDecomposable();  // Retourne vrai si la tache est décomposable , faux sinon


    // Fonction qui permet de comparer deux taches
    // la comparaison se fait en fonction de la priorité et de la date limite
    // Si la priorité est la même , on compare les dates limites
    @Override
    public int compareTo(Tache o) {
        if (this.priorite.ordinal() > o.priorite.ordinal()) {
            return 1;
        } else if (this.priorite.ordinal() < o.priorite.ordinal()) {
            return -1;
        } else {
            if (dateLimite.isAfter(o.getDateLimite())) {
                return -1;
            } else if (dateLimite.isBefore(o.getDateLimite())) {
                return 1;
            } else {
                return 0;
            }

        }
    }


    // Cette méthode affiche les informations d'une tache
    public void afficher() {

            System.out.println("\n\n\n -------------- tache ---------------- \n\n");
            System.out.println("Nom : " + getNom());
            System.out.println("Duree : " + getDuree());
            System.out.println("Priorite : " + getPriorite());
            System.out.println("Etat : " + getEtat());
            System.out.println("Categorie: "+getCategorie());
            System.out.println("Date Limite : " + getDateLimite());
        
    }
}
