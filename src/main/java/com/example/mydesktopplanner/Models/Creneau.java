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

    public void ajouterTache(Tache tache){
        this.tache = tache;
        this.libre = false;

        // On compare la durée de la tache avec la durée du creneau

    }

    public Duration getDuree(){
        return Duration.between(this.debut,this.fin);
    }


    @Override
    public Creneau decomposer(Void args) {
        // Si la durée de la tache contenue dans le créneau est inférieure a la durée du créneau , ce créneau est décomposé
        // en deux créneaux , un créneau contenant la tache et un créneau libre
        if (this.tache == null){return null;}
        // On teste si la durée de la tache est inférieure a celle du créneau
        if (this.tache.getDuree().compareTo(this.getDuree()) < 0){

            // Cette méthode change le temps de fin , et retourne le nouveau créneau (si decomplosable) sinon retourne null
            // Elle se base sur la tache contenue dans le creneau pour déterminer la durée du nouveau creneau
            // Le nouveau creneau est libre , et a la meme heure de fin que l'ancien


            // On teste si la durée du nouveau créneau est supérieure a la durée minimale d'un créneau
            if (Duration.between(this.getDebut().plus(this.tache.getDuree()),this.getFin()).compareTo(MyDesktopPlanner.getInstance().getTempsMinCreneau()) < 0){
                System.out.println(" Ce creneau ne peux pas etre décomposé , il est laissé ");
                return null;
            }
            LocalDateTime fin = this.getFin();
            this.setFin(this.getDebut().plus(this.tache.getDuree()));
            Creneau creneauLibre = null;
            try {
                creneauLibre = new Creneau(this.getFin().plusMinutes(1),fin);
            } catch (ExceptionDureeInvalide e) {
                // Cette exception est irréalisable théoriqiement car on a testé que la durée est bien correcte au début
                throw new RuntimeException(e);
            }
            this.libre = false;
            return creneauLibre;
        } else {
            return null;
        }


    }

    public void afficher(){

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        System.out.println("\n\n\n ------- crenau affiche ------- ");
        System.out.println("Debut : " + dtf.format(this.debut) + " Fin : " + dtf.format(this.fin) + "Libre: " + isLibre() );
        if (tache != null) tache.afficher();
    }

    public void supprimerTache(){
        this.tache = null;
        this.libre = true;
    }



}

