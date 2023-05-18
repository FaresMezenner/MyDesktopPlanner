package com.example.mydesktopplanner.Models;


import com.almasb.fxgl.time.LocalTimer;
import com.example.mydesktopplanner.Models.ExceptionsPackage.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;


// Cette classe contient les informations des jours
// Cette classe n'est pas finie , il manque les methodes

public class Jour implements Serializable {
    private LinkedList<Creneau> creneaux = new LinkedList<Creneau>();
    private final LocalDate date;


    private int nbTachesAccomplies = 0;
    private boolean felicitations = false;


    public Jour(LocalDate date) throws ExceptionDateInvalide{
        if (date == null || date.isBefore(LocalDateTime.now().toLocalDate())){
            // Si la date est null , ou elle est avant la date actuelle , on lance une exception
            throw new ExceptionDateInvalide("La date est invalide");
        }
        this.date = date;
    }


    public LinkedList<Creneau> getCreneaux() {
        return creneaux;
    }

    public void setCreneaux(LinkedList<Creneau> creneaux) {
        this.creneaux = creneaux;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getNbTachesAccomplies() {
        return nbTachesAccomplies;
    }

    public void setNbTachesAccomplies(int nbTachesAccomplies) {
        this.nbTachesAccomplies = nbTachesAccomplies;
    }

    public boolean isFelicitations() {
        return felicitations;
    }

    public void setFelicitations(boolean felicitations) {
        this.felicitations = felicitations;
    }

    public void ajouterCreneau(Creneau creneau) throws ExceptionCollisionHorairesCreneau {
        Iterator it = creneaux.iterator();

        int i = 0;
        if (!creneaux.isEmpty()) {
            while (it.hasNext()) {
                Creneau creneauIt = (Creneau) it.next();


                if (!creneauIt.isColliding(creneau)) {
                    if (creneauIt.compareTo(creneau) < 0) {
                        i++;
                        if (it.hasNext() && creneau.isColliding((Creneau) it.next())) {
                            throw new ExceptionCollisionHorairesCreneau("Le creneau est en collision avec un autre creneau");
                        } else {
                            creneaux.add(i, creneau);
                            break;
                        }
                    } else if (creneauIt.compareTo(creneau) > 0) {

                        creneaux.add(i, creneau);
                        break;
                    }
                } else {
                    throw new ExceptionCollisionHorairesCreneau("Le creneau est en collision avec un autre creneau");
                }


                i++;
            }
        } else {

            creneaux.add(creneau);
        }
    }






    public void afficherCrenaux() {
        Iterator it = creneaux.iterator();
        while (it.hasNext()) {
            Creneau creneau = (Creneau) it.next();
            creneau.afficherCreneau();
        }
    }

    public void afficher(){
        System.out.println("Date : " + date);
        afficherCrenaux();

    }

    public void suprimerCreneau(Creneau tache) {
        Iterator it = creneaux.iterator();
        while (it.hasNext()) {
            Creneau creneau = (Creneau) it.next();
            if (creneau.compareTo(tache) == 0) {
                creneaux.remove(creneau);
                if (tache.getClass().equals(CreneauPeriodique.class)) ((CreneauPeriodique) creneau).suprimerTachePeriodique();
                break;
            }
        }
    }
}
