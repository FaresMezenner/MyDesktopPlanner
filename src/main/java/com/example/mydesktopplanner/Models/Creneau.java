package com.example.mydesktopplanner.Models;


import com.example.mydesktopplanner.Models.ExceptionsPackage.*;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;


// Cette classe contient les informations d'un creaneau
// Cette classe n'est pas encore finie (il manque les méthodes)
// TODO: Ajouter l'attribut IsBloque
public class Creneau implements Decomposable<Void>, Serializable {



    private LocalDateTime debut,fin;
    private boolean libre;
    private Tache tache;


    // Tache simple car un creneau ne peux contenir qu'une seule tache , pas une liste de taches
    // Les taches decomposables sont de base des taches simples fragmentées.

    public Creneau(LocalDateTime debut, LocalDateTime fin) throws ExceptionDureeInvalide{

        // Si la durée d'un creneau est inferieure a user.getTempsMinCreneau() , on lance une exception
        if (Duration.between(debut,fin).compareTo(MyDesktopPlanner.getInstance().getTempsMinCreneau()) < 0){
            throw new ExceptionDureeInvalide("La durée du creneau est invalide");
        }
        this.debut = debut;
        this.fin = fin;
        this.libre = true;
        this.tache = null;


    }

    @Override
    public Creneau decomposer(Void args) {
        return null;
    }
}

