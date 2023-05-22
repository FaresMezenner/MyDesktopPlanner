package com.example.mydesktopplanner.Models;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.TreeMap;

// Cette classe contiens les informations d'un projet
// Cette classe n'est pas finie , il manque les methodes.
public class Projet implements Serializable {
    private String nom,description;
    private TreeMap<LocalDateTime,Creneau> taches;

    public Projet(String nom, String description, TreeMap<LocalDateTime,Creneau> taches) {
        this.nom = nom;
        this.description = description;
        this.taches = taches;
    }

    // -------------------------------------- Delimitation Setters/Getters --------------------------------------
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TreeMap<LocalDateTime,Creneau> getTaches() {
        return taches;
    }

    public void setTaches(TreeMap<LocalDateTime,Creneau> taches) {
        this.taches = taches;
    }

    // -------------------------------------- Delimitation Setters/Getters --------------------------------------

    public void ajouterTache(Creneau tache) {
        taches.put(tache.getDebut(),tache);
    }



    public void supprimerTache(Creneau tache) {
        taches.remove(tache.getDebut());
    }

    public void afficher(){
        System.out.println("Nom : "+nom);
        System.out.println("Description : "+description);
        System.out.println("Taches : ");
        for(Creneau tache : taches.values()){
            System.out.println(tache.getTache().getNom());
        }
    }

}


