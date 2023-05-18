package com.example.mydesktopplanner.Models;


import com.example.mydesktopplanner.Models.ExceptionsPackage.*;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


// Cette classe contient les informations d'un creaneau
// Cette classe n'est pas encore finie (il manque les méthodes)
public class Creneau implements Decomposable<Void>, Collidable<Creneau>, Serializable, Comparable<Creneau>{



    private LocalDateTime debut,fin;
    private boolean libre, blocked;
    private Tache tache;




    // Tache simple car un creneau ne peux contenir qu'une seule tache , pas une liste de taches
    // Les taches decomposables sont de base des taches simples fragmentées.

    public Creneau(LocalDateTime debut, LocalDateTime fin) throws ExceptionDureeInvalide{

        // Si la durée d'un creneau est inferieure a user.getTempsMinCreneau() , on lance une exception
         if (debut.isBefore(LocalDateTime.now())){
            throw new ExceptionDureeInvalide("La date de debut est deja passee et invalide");
        }  else if (debut.isAfter(fin)){
            throw new ExceptionDureeInvalide("La date de debut ou de fin du creneau sont invalides");
        } else if (Duration.between(debut.toLocalTime(),fin.toLocalTime()).compareTo(MyDesktopPlanner.getInstance().getTempsMinCreneau()) < 0){
            throw new ExceptionDureeInvalide("La durée du creneau est invalide");
        } else if (debut.toLocalDate().equals(fin.toLocalDate()) == false){
             throw new ExceptionDureeInvalide("La date de debut et de fin ne sont pas dans le meme jour");
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

    public void afficherCreneau() {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        System.out.println("\n\n\n ------- crenau affiche ------- ");
        System.out.println("Debut : " + dtf.format(this.debut) + " Fin : " + dtf.format(this.fin) );
    }


    @Override
    public boolean isColliding(Creneau creneau) {

          return timeCollision(getDebut(), getFin(), creneau.getDebut()) ||
                  timeCollision(getDebut(), getFin(), creneau.getFin()) ||
                  timeCollision(creneau.getDebut(), creneau.getFin(), getDebut()) ||
                  timeCollision(creneau.getDebut(), creneau.getFin(), getFin()) ||
                  getDebut().isEqual(creneau.getDebut()) ||
                  getFin().isEqual(creneau.getFin());

    }

    @Override
    public int compareTo(Creneau o) {
        if (this == o) return  0;
        if (getFin().isBefore(o.getDebut())) {
            return -1;
        } else if (getDebut().isAfter(o.getFin())) {
            return 1;
        } else {
            return 0;
        }
    }
}

