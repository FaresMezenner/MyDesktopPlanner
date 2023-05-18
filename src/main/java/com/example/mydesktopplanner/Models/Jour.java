package com.example.mydesktopplanner.Models;


import com.almasb.fxgl.time.LocalTimer;
import com.example.mydesktopplanner.Models.ExceptionsPackage.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;


// Cette classe contient les informations des jours
// Cette classe n'est pas finie , il manque les methodes

public class Jour implements Serializable {
    private LinkedList<Creneau> creneaux = new LinkedList<Creneau>();
    private LocalDateTime date;


    private int nbTachesAccomplies = 0;
    private boolean felicitations = false;


    public Jour(LocalDateTime date) throws ExceptionDateInvalide{
        if (date == null || date.toLocalDate().isBefore(LocalDateTime.now().toLocalDate())){
            // Si la date est null , ou elle est avant la date actuelle , on lance une exception
            throw new ExceptionDateInvalide("La date est invalide");
        }
        this.date = date;
    }

    public void ajouterCreneau(Creneau creneau) throws ExceptionCollisionHorairesCreneau {
        Iterator it = creneaux.iterator();

        int i = 0;
        if (!creneaux.isEmpty()) {
            while (it.hasNext()) {
                Creneau creneauIt = (Creneau) it.next();
                if (creneauIt.getFin().isBefore(creneau.getDebut())) {
                    i++;
                    if (it.hasNext() && testCrenauCollision((Creneau) it.next(), creneau)) {
                        throw new ExceptionCollisionHorairesCreneau("Le creneau est en collision avec un autre creneau");
                    } else {
                        creneaux.add(i, creneau);
                        break;
                    }
                }
                i++;
            }
        } else {

            creneaux.add(creneau);
        }
    }



    private boolean testCrenauCollision(Creneau creneauA, Creneau creneauB) {

        if (
                timeCollision(creneauA.getDebut(), creneauA.getFin(), creneauB.getDebut()) ||
                        timeCollision(creneauA.getDebut(), creneauA.getFin(), creneauB.getFin()) ||
                        timeCollision(creneauB.getDebut(), creneauB.getFin(), creneauA.getDebut()) ||
                        timeCollision(creneauB.getDebut(), creneauB.getFin(), creneauA.getFin())
        ) {
            return true;
        } else {
            return false;
        }
    }
    private boolean timeCollision(LocalDateTime startTime, LocalDateTime endTime, LocalDateTime currentTime) {
        // Cette fonction retourne vrai si currentTime est compris entre startTime et endTime
        return currentTime.isAfter(startTime) && currentTime.isBefore(endTime);
    }


    public void afficherCrenaux() {
        Iterator it = creneaux.iterator();
        while (it.hasNext()) {
            Creneau creneau = (Creneau) it.next();
            creneau.afficherCreneau();
        }
    }
}
