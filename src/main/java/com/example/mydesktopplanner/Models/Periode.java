package com.example.mydesktopplanner.Models;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

// Cette classe concerne les periodes que l'utilisateur pourra spécifier sur son calendrier
// Cette classe peut etre supprimée
// LocalDate debut LocalDate fin
public class Periode implements Serializable {
    private LocalDateTime debut,fin;

    private ArrayList<Jour> jours; // On stocke les jours pour avoir un acces simple et dynamique aux creneaux (au lieu de les stocker dans une ArrayList)

    private ArrayList<Creneau> creneaux;
    private final Utilisateur utilisateur;

    public Periode(LocalDateTime debut, LocalDateTime fin, Utilisateur utilisateur) {
        this.debut = debut;
        this.fin = fin;
        jours = utilisateur.getCalendrier().getJoursIntervalle(debut.toLocalDate(),fin.toLocalDate());
        this.utilisateur = utilisateur;
    }

    // -------------------------------------- Delimitation Setters/Getters --------------------------------------
    public Utilisateur getUtilisateur() {
        return utilisateur;
    }
    public ArrayList<Jour> getJours() {
        return jours;
    }

    public void setJours(ArrayList<Jour> jours) {
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
        return this.jours.get(0).getDate();
    }

    public LocalDate getFin() {
        return this.jours.get(this.jours.size()-1).getDate();
    }

}
