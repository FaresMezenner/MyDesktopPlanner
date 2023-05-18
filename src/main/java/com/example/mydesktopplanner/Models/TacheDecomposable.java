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
    public TacheDecomposable decomposer(Duration duree) {
        // Cette methode change juste le nom de la tache pour y ajouter le numéro de sous tache
        this.setNom(this.getNom() + " "+  Integer.toString(getNumeroSousTache()));
        return null;
    }



}
