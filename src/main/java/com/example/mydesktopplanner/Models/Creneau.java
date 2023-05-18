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
    private boolean libre, blocked;
    private Tache tache;


    // Tache simple car un creneau ne peux contenir qu'une seule tache , pas une liste de taches
    // Les taches decomposables sont de base des taches simples fragmentées.

    public Creneau(LocalDateTime debut, LocalDateTime fin) throws ExceptionDureeInvalide{

        // Si la durée d'un creneau est inferieure a user.getTempsMinCreneau() , on lance une exception
        if (Duration.between(debut.toLocalTime(),fin.toLocalTime()).compareTo(MyDesktopPlanner.getInstance().getTempsMinCreneau()) < 0){
            throw new ExceptionDureeInvalide("La durée du creneau est invalide");
        }
        this.debut = debut;
        this.fin = fin;
        this.libre = true;
        this.tache = null;


    }

    public LocalDateTime getDebut() {
        return debut;
    }

    public void setDebut(LocalDateTime debut) {
        this.debut = debut;
    }

    public LocalDateTime getFin() {
        return fin;
    }

    public void setFin(LocalDateTime fin) {
        this.fin = fin;
    }

    public boolean isLibre() {
        return libre;
    }

    public void setLibre(boolean libre) {
        this.libre = libre;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public Tache getTache() {
        return tache;
    }

    public void setTache(Tache tache) {
        this.tache = tache;
    }

    @Override
    public Creneau decomposer(Void args) {
        return null;
    }
}

