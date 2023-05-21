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
public class Periode implements Serializable , Collidable<Periode> , Comparable<Periode>{

    private LocalDate debut,fin;





    public Periode(LocalDate debut, LocalDate fin) throws ExceptionDateInvalide {

        if (debut.isBefore(LocalDate.now())) {
            throw new ExceptionDateInvalide("La date de debut est deja passee et invalide");
        } else if (fin.isBefore(debut)) {
            throw new ExceptionDateInvalide("La date de debut doit etre avant la date de fin");
        }

        this.debut = debut;
        this.fin = fin;

    }

    // -------------------------------------- Delimitation Setters/Getters --------------------------------------

    public LocalDate getDebut() {
        return debut;
    }

    public void setDebut(LocalDate debut) {
        this.debut = debut;
    }

    public LocalDate getFin() {
        return fin;
    }

    public void setFin(LocalDate fin) {
        this.fin = fin;
    }


    // -------------------------------------- Delimitation Setters/Getters --------------------------------------



    @Override
    public boolean isColliding(Periode periode) {
        LocalDateTime debut_periode = getDebut().atStartOfDay();
        LocalDateTime fin_periode = getFin().atStartOfDay();

        LocalDateTime debut_periode_argument = periode.getDebut().atStartOfDay();
        LocalDateTime fin_periode_argument = periode.getFin().atStartOfDay();


        return timeCollision(debut_periode, fin_periode, debut_periode_argument) ||
                timeCollision(debut_periode, fin_periode, fin_periode_argument) ||
                timeCollision(debut_periode_argument, fin_periode_argument, debut_periode) ||
                timeCollision(debut_periode_argument, fin_periode_argument, fin_periode) ||
                debut_periode.isEqual(debut_periode_argument) ||
                fin_periode.isEqual(fin_periode_argument);
    }

    public void afficher(){
        System.out.println("Debut : " + debut + " Fin : " + fin);
    }

    @Override
    public int compareTo(Periode periode) {
        // Teste si les deux periodes sont identiques
        if (((this.getDebut().compareTo(periode.getDebut()) == 0) && (this.getFin().compareTo(periode.getFin())) == 0) || this == periode){
            return 0;
        }
        else{
            // Teste si la periode actuelle est avant la periode argument
            if (this.getDebut().compareTo(periode.getDebut()) < 0){
                return -1;
            }
            else if (this.getDebut().compareTo(periode.getDebut()) > 0){
                return 1;
            }
            if (this.getFin().compareTo(periode.getFin()) < 0){
                return -1;
            }
            else {
                return 1;
            }
        }
    }

}
