package com.example.mydesktopplanner.Models;

import java.time.Duration;
import java.time.LocalDateTime;

public class TacheDecomposable extends Tache implements Decomposable<Duration> {

    private int numeroSousTache = 1;
    private TacheDecomposable precedent,suivant;

    public TacheDecomposable(String nom, Duration duree, Priorite priorite, LocalDateTime dateLimite, Categorie categorie, TacheDecomposable precedent, int numeroSousTache) {
        super(nom, duree, priorite, dateLimite, categorie);
        this.numeroSousTache = numeroSousTache;
        this.precedent = precedent;
    }

    // Constructeur qui permets de générer une tache décomposable avec un numéro de sous tache par défaut à 1 et UNSCHEDULED
    public TacheDecomposable(String nom, Duration duree, Priorite priorite, LocalDateTime dateLimite, Categorie categorie) {
        super(nom, duree, priorite, dateLimite, categorie);
    }


    // --------------- Getters/Setters ---------------
    public int getNumeroSousTache() {
        return numeroSousTache;
    }

    public void setNumeroSousTache(int numeroSousTache) {
        this.numeroSousTache = numeroSousTache;
    }

    public TacheDecomposable getPrecedent() {
        return precedent;
    }

    public void setPrecedent(TacheDecomposable precedent) {
        this.precedent = precedent;
    }

    public TacheDecomposable getSuivant() {
        return suivant;
    }

    public void setSuivant(TacheDecomposable suivant) {
        this.suivant = suivant;
    }

    // --------------- Getters/Setters ---------------
    @Override
    boolean isDecomposable() {
        return true;
    }


    public int incNumeroSousTache(){
        return numeroSousTache+1;
    }

    @Override
    public TacheDecomposable decomposer(Duration duree) {
        // Cette methode change le nom pour y ajouter le numero de la sous-tache
        // Elle crée une nouvelle tache avec le reste de la durée
        // Elle retourne la nouvelle tache



        // On vérifie si la durée de la tache est supérieure a duree
        if (this.getDuree().compareTo(duree) <= 0) {
            // Si c'est le cas on retourne null
            return null;
        }
        // On crée une nouvelle tache avec le reste de la durée
        System.out.println(this.getNumeroSousTache());
        TacheDecomposable nouvelleTache = new TacheDecomposable(this.getNom() + " "+  Integer.toString(getNumeroSousTache() + 1), this.getDuree().minus(duree), this.getPriorite(), this.getDateLimite(), this.getCategorie(), this, this.incNumeroSousTache());
        this.suivant = nouvelleTache;
        System.out.println(nouvelleTache.getNumeroSousTache());
        // Si la tache est la première sous-tache , on change son nom
        if (this.getNumeroSousTache() == 1){this.setNom(this.getNom() + " 1");}
        // On change la durée de la tache actuelle
        this.setDuree(duree);
        // On retourne la nouvelle tache
        return nouvelleTache;
    }

    public void afficher(){
        super.afficher();
        System.out.println("Numero de sous-tache : " + this.getNumeroSousTache());

    }



}
