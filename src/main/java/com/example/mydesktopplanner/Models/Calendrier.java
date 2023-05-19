package com.example.mydesktopplanner.Models;


import com.example.mydesktopplanner.Models.ExceptionsPackage.*;

import java.io.Serializable;
import java.time.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.util.*;

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


    public void ajouterPeriode(Periode periode) throws ExceptionCollisionPeriode{
        // On vérifie si la période est en collision avec une période deja existante
        for (Periode periode_existante : periodes.values()) {
            if (periode.isColliding(periode_existante)) {
                throw new ExceptionCollisionPeriode("ERREUR : Collision avec periode existante");
            }
        }
        // On ajoute la période
        periodes.put(periode.getDebut(),periode);
    }

    public void ajouterCreneau(Creneau creneau) throws ExceptionDateInvalide, ExceptionCollisionHorairesCreneau {
        Jour jour = jours.get(creneau.getDebut().toLocalDate());
        if (jour == null) {
            System.out.println("day doesn't exist");
            //on va ajouter un nouveau jour, une exception est levée si la date est invalide
            //ie si la date est null ou si elle est avant la date actuelle
            jour = new Jour(creneau.getDebut().toLocalDate());
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

    public void plannifierTachePeriodique(CreneauPeriodique tache, int nJours, int nbFois) throws ExceptionDateInvalide, ExceptionCollisionHorairesCreneau {
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

    public void supprimerCreneau(Creneau tache) {
        jours.get(tache.getDebut().toLocalDate()).suprimerCreneau(tache);
    }

    public ArrayList<Jour> getJoursIntervalle(LocalDate debut , LocalDate fin){
        // Cette fonction renvoies une liste contenant tout les jours du calendrier [EXISTANTS] dans l'intervalle indiqué
        return new ArrayList<>(jours.subMap(debut,fin.plusDays(1)).values());
    }

    public LinkedList<Creneau> getCreneauxJour(LocalDate date) throws ExceptionDateInvalide{
    // Cette fonction renvoies la liste des creneaux d'un jour donné
        Jour jour =  jours.get(date);
        if (jour != null){
            return jour.getCreneaux();
        }
        else{
            throw new ExceptionDateInvalide("Le jour n'existe pas");
        }
    }

    public void supprimerPeriode(Periode periode) throws ExceptionPeriodeInexistante{
        if (periodes.containsKey(periode.getDebut())){
            periodes.remove(periode.getDebut());
        }
        else{
            throw new ExceptionPeriodeInexistante("La periode n'existe pas");
        }
    }

    public HashMap<String,Object> ajouterTacheCreneau(Creneau creneau, Tache tache) throws ExceptionDureeInvalide{

        return jours.get(creneau.getDebut().toLocalDate()).ajouterTacheCreneau(tache, creneau);
    }




    public void ajouterJour(LocalDate date) throws ExceptionDateInvalide {
        if (date.isBefore(LocalDate.now())) {
            throw new ExceptionDateInvalide("La date est invalide");
        }
        if (jours.containsKey(date)) {
            throw new ExceptionDateInvalide("Le jour existe déja");
        }
        Jour jour = new Jour(date);
        jours.put(date, jour);
    }

    public void afficher(){
        for (Jour jour : jours.values()) {
            jour.afficher();
        }
    }
    public void supprimerTachesPeriodique(CreneauPeriodique c) {
        CreneauPeriodique tmp = c.getPrecedent();
        while (tmp.getPrecedent() != null) {
            supprimerCreneau(tmp);
            tmp = tmp.getPrecedent();
        }

        while (c.getSuivant() != null) {
            supprimerCreneau(c);
            c = c.getSuivant();
        }


    }

    /**
     * this function takes a list of an unscheduled tasks and a period and schedule the tasks in the period
     * and returns the unscheduled tasks (since in some cases there will be no space to schedule all the tasks)
     * @param taches
     * @param periode
     * @return
     * @throws ExceptionDureeInvalide
     * @throws ExceptionCollisionHorairesCreneau
     */
    public ArrayList<Tache> plannifierTachesPeriode(LinkedList<Tache> taches, Periode periode) throws ExceptionDureeInvalide, ExceptionCollisionHorairesCreneau {


        //these are used to iterate through the days of the period
        LocalDate date;
        Jour jour;

        //this is the task we'll be scheduling, it iterates through the tasks in the parameters
        Tache tache;

        ArrayList<Tache> unscheduledTaches = new ArrayList<>();
        for (Tache t : taches) {
            unscheduledTaches.add(t);
        }



        //we'll loop through the tasks and schedule them in the days of the period
        //at each iteration we'll check if there is still tasks to schedule
        while ( unscheduledTaches.size() != 0 ) {
            tache = unscheduledTaches.get(0);
            date = periode.getDebut();

            while (date.isBefore(periode.getFin()) || date.isEqual(periode.getFin())){
                jour = jours.get(date);

                //we'll remove the task from unscheduledTasks and schedule it in the day
                unscheduledTaches.remove(0);
                if ((tache = jour.plannifierTache(tache)) != null) {
                    //if scheduling the task returns another task, we'll add it to the unscheduledTasks
                    //in place of the task we just removed, otherwise we'll keep the next one at the beginning of the list
                    unscheduledTaches.add(0, tache);
                } else {
                    break;
                }

                date = date.plusDays(1);
            }
        }
        return unscheduledTaches;
    }
}