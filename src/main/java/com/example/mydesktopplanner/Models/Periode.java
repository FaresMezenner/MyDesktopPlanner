package com.example.mydesktopplanner.Models;

import com.example.mydesktopplanner.Models.ExceptionsPackage.ExceptionDateInvalide;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;

// Cette classe concerne les periodes que l'utilisateur pourra spécifier sur son calendrier
// Cette classe peut etre supprimée
// LocalDateTime debut LocalDateTime fin
public class Periode implements Serializable {

    private LocalDate debut,fin;
    private LinkedList<Jour> jours; // On stocke les jours pour avoir un acces simple et dynamique aux creneaux (au lieu de les stocker dans une ArrayList)




    public Periode(LocalDate debut, LocalDate fin, LinkedList<Jour> jours) throws ExceptionDateInvalide {

        if (debut.isBefore(LocalDate.now())) {
            throw new ExceptionDateInvalide("La date de debut est deja passee et invalide");
        } else if (debut.isBefore(fin)) {
            throw new ExceptionDateInvalide("La date de debut doit etre avant la date de fin");
        }

        this.debut = debut;
        this.fin = fin;
        this.jours = jours;
    }

    // -------------------------------------- Delimitation Setters/Getters --------------------------------------

    public LinkedList<Jour> getJours() {
        return jours;
    }

    public void setJours(LinkedList<Jour> jours) {
        this.jours = jours;
    }

    // -------------------------------------- Delimitation Setters/Getters --------------------------------------

    public void ajouterJour(Jour jour) {
        this.jours.add(jour);
    }

    public void suppJour(Jour jour) {
        this.jours.remove(jour);
    }

    public LocalDate getDebut() {
        return this.debut;
    }

    public LocalDate getFin() {
        return this.fin;
    }


}
