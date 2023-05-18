package com.example.mydesktopplanner.Models;

import com.example.mydesktopplanner.Models.ExceptionsPackage.ExceptionDureeInvalide;

import java.time.LocalDateTime;

public class CreneauPeriodique extends Creneau  {

    private CreneauPeriodique precedent,suivant;

    public CreneauPeriodique(LocalDateTime debut, LocalDateTime fin, TacheSimple tache) throws ExceptionDureeInvalide {
        super(debut, fin);
        setTache(tache);
        setLibre(false);
    }

    public void suprimerTachePeriodique(){

        precedent.setSuivant(suivant);
        suivant.setPrecedent(precedent);
    }

    public CreneauPeriodique getSuivant() {
        return suivant;
    }

    public void setSuivant(CreneauPeriodique suivant) {
        this.suivant = suivant;
    }

    public CreneauPeriodique getPrecedent() {
        return precedent;
    }

    public void setPrecedent(CreneauPeriodique precedent) {
        this.precedent = precedent;
    }

}
