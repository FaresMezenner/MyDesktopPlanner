package com.example.mydesktopplanner.Models;


import com.example.mydesktopplanner.Models.ExceptionsPackage.*;

import java.io.Serializable;
import java.time.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.TreeMap;

// Cette classe contiens les informations du calendrier
// Cette classe n'est pas finie , il manque les methodes.
// All the Exceptions are handled in this class

// Don't forget that we need to store the days in a file. So , we have to do some file IO.
public class Calendrier implements Serializable {
    private TreeMap<LocalDate, Jour> jours = new TreeMap<>();
    private TreeMap<LocalDate, Periode> periodes= new TreeMap<>();
    // Les jours des periodes doivent etre ajoutés a ArrayList jours.


public Calendrier() {

}


    public void ajouterPeriode() {
    }

    public void ajouterCreneau(Creneau creneau) throws ExceptionDateInvalide, ExceptionCollisionHorairesCreneau {
        Jour jour = jours.get(creneau.getDebut().toLocalDate());
        if (jour == null) {
            System.out.println("day doesn't exist");
            //on va ajouter un nouveau jour, une exception est levée si la date est invalide
            //ie si la date est null ou si elle est avant la date actuelle
            jour = new Jour(creneau.getDebut());
            //on ajoute le creneau au jour, une exception est levée si la date du creneau est invalide
            //ie s'il exist deja un creneau dans la meme interval du temp
            jour.ajouterCreneau(creneau);
            jours.put(creneau.getDebut().toLocalDate(), jour);
        } else {
            jour.ajouterCreneau(creneau);
        }
    }

    public void afficherCrenaux() {
        for (Jour jour : jours.values()) {
            jour.afficherCrenaux();
        }
    }

    public void ajouterTachePeriodique(CreneauPeriodique tache, int nJours, int nbFois) throws ExceptionDateInvalide, ExceptionCollisionHorairesCreneau {
    //we will save the list of days in case we face an exception and we need the abort the operation
    TreeMap<LocalDate, Jour> jours = this.jours;


    CreneauPeriodique tachePeriodique = tache;

    try {
        for (int i = 0; i < nbFois; i++) {

            CreneauPeriodique tmp;

            ajouterCreneau(tachePeriodique);


            tmp = new CreneauPeriodique(
                    tachePeriodique.getDebut().plusDays(nJours),
                    tachePeriodique.getFin().plusDays(nJours),
                    (TacheSimple) tachePeriodique.getTache()
            );

            tachePeriodique.setSuivant(tmp);
            tmp.setPrecedent(tachePeriodique);

            tachePeriodique = tmp;

        }
    } catch (ExceptionDateInvalide e) {
        this.jours = jours;
        throw new ExceptionDateInvalide();
    } catch (ExceptionCollisionHorairesCreneau e) {
        this.jours = jours;
        throw e;
    } catch (ExceptionDureeInvalide e) {
        this.jours = jours;
        throw new RuntimeException(e);
    }



    }
}