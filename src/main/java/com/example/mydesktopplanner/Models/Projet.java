package com.example.mydesktopplanner.Models;

import java.io.Serializable;
import java.util.ArrayList;
// Cette classe contiens les informations d'un projet
// Cette classe n'est pas finie , il manque les methodes.
public class Projet implements Serializable {
    private String nom,description;
    private ArrayList<Tache> taches;

    public Projet(String nom, String description, ArrayList<Tache> taches) {
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

    public ArrayList<Tache> getTaches() {
        return taches;
    }

    public void setTaches(ArrayList<Tache> taches) {
        this.taches = taches;
    }

    // -------------------------------------- Delimitation Setters/Getters --------------------------------------

    public void ajouterTache(Tache tache) {
        taches.add(tache);
    }

    public void supprimerTache(Tache tache) {
        taches.remove(tache);
    }

    public void afficher(){
        System.out.println("Nom : "+nom);
        System.out.println("Description : "+description);
        System.out.println("Taches : ");
        for(Tache tache : taches){
            System.out.println(tache.getNom());
        }
    }

}


