package com.example.mydesktopplanner.Models;


import com.example.mydesktopplanner.Models.ExceptionsPackage.*;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.TreeMap;

// Cette classe contiens les informations du calendrier
// Cette classe n'est pas finie , il manque les methodes.
// All the Exceptions are handled in this class

// Don't forget that we need to store the days in a file. So , we have to do some file IO.
public class Calendrier implements Serializable {
    private TreeMap<LocalDateTime, Jour> jours = new TreeMap<>();
    private TreeMap<LocalDateTime, Periode> periodes= new TreeMap<>();
    // Les jours des periodes doivent etre ajoutés a ArrayList jours.


public Calendrier() {

}


    public void ajouterPeriode() {
    }

    public void ajouterCreneau(Creneau creneau) throws ExceptionDateInvalide, ExceptionCollisionHorairesCreneau {
        Jour jour = jours.get(creneau.getDebut());
        if (jour == null) {
            //on va ajouter un nouveau jour, une exception est levée si la date est invalide
            //ie si la date est null ou si elle est avant la date actuelle
            jour = new Jour(creneau.getDebut());
            //on ajoute le creneau au jour, une exception est levée si la date du creneau est invalide
            //ie s'il exist deja un creneau dans la meme interval du temp
            jour.ajouterCreneau(creneau);
            jours.put(creneau.getDebut(), jour);
        } else {
            jour.ajouterCreneau(creneau);
        }
    }
}