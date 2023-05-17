package com.example.mydesktopplanner.Models;

import java.time.Duration;
import java.time.LocalDate;

public class TacheDecomposable extends Tache implements Decomposable {

    private int numeroSousTache;
    private TacheDecomposable precedent,suivant;
    public TacheDecomposable(String nom, Duration durée, Priorite priorite, LocalDate dateLimite, Categorie categorie, Etat etat, int numeroSousTache) {
        super(nom, durée, priorite, dateLimite, categorie, etat);
        this.numeroSousTache = numeroSousTache;
        precedent = null;
        suivant = null;
    }

    // Constructeur qui permets de générer une tache décomposable avec un numéro de sous tache par défaut à 1 et UNSCHEDULED
    public TacheDecomposable(String nom, Duration durée, Priorite priorite, LocalDate dateLimite, Categorie categorie) {
        super(nom, durée, priorite, dateLimite, categorie, Etat.UNSCHEDULED);
        this.numeroSousTache = 1;
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
    public void decomposer() {
        // Cette methode change juste le nom de la tache pour y ajouter le numéro de sous tache
        this.setNom(this.getNom() + " "+  Integer.toString(getNumeroSousTache()));
    }



}
