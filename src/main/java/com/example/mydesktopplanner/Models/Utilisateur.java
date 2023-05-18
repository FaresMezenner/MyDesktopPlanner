package com.example.mydesktopplanner.Models;

import com.example.mydesktopplanner.Models.ExceptionsPackage.ExceptionCollisionHorairesCreneau;
import com.example.mydesktopplanner.Models.ExceptionsPackage.ExceptionDateInvalide;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;

public class Utilisateur {
    private String pseudo;
    private float rendementJournalier , rendementPeriode;
    private int nbEncouragements;
    private Jour jourRentable;
    private LocalDateTime[] tempsCategories;
    private Duration tempsMinCreneau = Duration.ofMinutes(30);

    //TODO: tache periodique
    private LinkedList<Tache> unscheduledTaches = new LinkedList<>();
    private Calendrier calendrier = new Calendrier();
    private int[] badges;
    private ArrayList<Projet> projets = new ArrayList<>();
    private int nbMinimalTachesParJourBadgeGood = 5;         // Pour l'attribution des badges (lire l'ennoncé)

    public Utilisateur(String pseudo) {
        this.pseudo = pseudo;
    }


    // ----------------------------- Delimitation Gettes / Setters ----------------------------------


    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public float getRendementJournalier() {
        return rendementJournalier;
    }

    public void setRendementJournalier(float rendementJournalier) {
        this.rendementJournalier = rendementJournalier;
    }

    public float getRendementPeriode() {
        return rendementPeriode;
    }

    public void setRendementPeriode(float rendementPeriode) {
        this.rendementPeriode = rendementPeriode;
    }

    public int getNbEncouragements() {
        return nbEncouragements;
    }

    public void setNbEncouragements(int nbEncouragements) {
        this.nbEncouragements = nbEncouragements;
    }

    public Jour getJourRentable() {
        return jourRentable;
    }

    public void setJourRentable(Jour jourRentable) {
        this.jourRentable = jourRentable;
    }

    public LocalDateTime[] getTempsCategories() {
        return tempsCategories;
    }

    public void setTempsCategories(LocalDateTime[] tempsCategories) {
        this.tempsCategories = tempsCategories;
    }

    public Duration getTempsMinCreneau() {
        return tempsMinCreneau;
    }

    public void setTempsMinCreneau(Duration tempsMinCreneau) {
        this.tempsMinCreneau = tempsMinCreneau;
    }

    public LinkedList<Tache> getUnscheduledTaches() {
        return unscheduledTaches;
    }

    public void setUnscheduledTaches(LinkedList<Tache> unscheduledTaches) {
        this.unscheduledTaches = unscheduledTaches;
    }

    public Calendrier getCalendrier() {
        return calendrier;
    }

    public void setCalendrier(Calendrier calendrier) {
        this.calendrier = calendrier;
    }

    public int[] getBadges() {
        return badges;
    }

    public void setBadges(int[] badges) {
        this.badges = badges;
    }

    public ArrayList<Projet> getProjets() {
        return projets;
    }

    public void setProjets(ArrayList<Projet> projets) {
        this.projets = projets;
    }

    public int getNbMinimalTachesParJour() {
        return nbMinimalTachesParJourBadgeGood;
    }

    public void setNbMinimalTachesParJour(int nbMinimalTachesParJour) {
        this.nbMinimalTachesParJourBadgeGood = nbMinimalTachesParJour;
    }


    public void ajouterTache(Tache tache) {
        int i = 0;
        for (Tache t : unscheduledTaches) {
            if (t.compareTo(tache) <= 0) {
                unscheduledTaches.add(i, tache);
                 break;
            }
            i++;
        }
        if (i == unscheduledTaches.size()) {
            unscheduledTaches.add(tache);
        }
    }

    public void ajouterPeriode() {
        calendrier.ajouterPeriode();
    }

    public void ajouterCreneau(Creneau creneau) throws ExceptionDateInvalide, ExceptionCollisionHorairesCreneau {
        calendrier.ajouterCreneau(creneau);

    }
}





