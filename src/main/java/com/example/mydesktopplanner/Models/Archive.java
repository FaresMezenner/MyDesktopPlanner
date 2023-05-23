package com.example.mydesktopplanner.Models;

import java.time.LocalDateTime;

public class Archive {
    // Cette classe sert a stocker les données archivés de l'utilisateur , afin de gerer l'historique.

    private int badges[];
    private int nbTachesCompletees,nbProjetsCompletes;

    private LocalDateTime dateArchivage;

    public Archive(int badges[],int nbTachesCompletees,int nbProjetsCompletes){
        this.badges = badges;
        this.nbTachesCompletees = nbTachesCompletees;
        this.nbProjetsCompletes = nbProjetsCompletes;
        dateArchivage = LocalDateTime.now();
    }

    public int[] getBadges() {
        return badges;
    }

    public void setBadges(int[] badges) {
        this.badges = badges;
    }

    public int getNbTachesCompletees() {
        return nbTachesCompletees;
    }

    public void setNbTachesCompletees(int nbTachesCompletees) {
        this.nbTachesCompletees = nbTachesCompletees;
    }

    public int getNbProjetsCompletes() {
        return nbProjetsCompletes;
    }

    public void setNbProjetsCompletes(int nbProjetsCompletes) {
        this.nbProjetsCompletes = nbProjetsCompletes;
    }

    public LocalDateTime getDateArchivage() {
        return dateArchivage;
    }

    public void setDateArchivage(LocalDateTime dateArchivage) {
        this.dateArchivage = dateArchivage;
    }

    public void afficher(){
        System.out.println("Date d'archivage : "+dateArchivage);
        System.out.println("Nombre de taches completes : "+nbTachesCompletees);
        System.out.println("Nombre de projets completes : "+nbProjetsCompletes);
        System.out.println("Badges : ");
        for(int i = 0 ; i < badges.length ; i++){
            System.out.println("Badge "+i+" : "+badges[i]);
        }
    }
}
