package com.example.mydesktopplanner.Models;


import com.almasb.fxgl.time.LocalTimer;
import com.example.mydesktopplanner.Models.ExceptionsPackage.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;


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
                    if (creneauIt.compareTo(creneau) > 0) {
                        creneaux.add(i, creneau);
                        return;

                    }
                } else {
                    throw new ExceptionCollisionHorairesCreneau("Le creneau est en collision avec un autre creneau");
                }


                i++;
            }
            creneaux.add(creneau);
        } else {

            creneaux.add(creneau);
        }
    }






    public void afficherCrenaux() {
        Iterator it = creneaux.iterator();
        while (it.hasNext()) {
            Creneau creneau = (Creneau) it.next();
            creneau.afficher();
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

    public HashMap<String,Object> ajouterTacheCreneau(Tache tache, Creneau creneau) throws ExceptionDureeInvalide {


        HashMap<String,Object> map = new HashMap<>();
        if (tache.isDecomposable()){

            TacheDecomposable tacheDec = (TacheDecomposable) tache;
            // Si la durée de la tache est plus grande que la durée du creneau
            if (tache.getDuree().compareTo(creneau.getDuree()) > 0){
                // On renvoie un HashMap contenant la tache restante
                TacheDecomposable nouvelle_tache = tacheDec.decomposer(creneau.getDuree());
                creneau.setTache(tacheDec);
                map.put("tache",nouvelle_tache);
            } else{
                creneau.setTache(tache);
                Creneau nouveau_creneau = creneau.decomposer(null);
                map.put("creneau",nouveau_creneau);
            }
        }
        else{
            if (tache.getDuree().compareTo(creneau.getDuree()) > 0){
                throw new ExceptionDureeInvalide("La durée de la tache est plus grande que la durée du creneau");
            }
            creneau.setTache(tache);
            Creneau nouveau_creneau = creneau.decomposer(null);
            map.put("creneau",nouveau_creneau);
        }

        tache.setEtat(Etat.INPROGRESS);
        creneau.setLibre(false);
        return map;
    }


    /**
     * this function schedule a task in a day, if the task is decomposable
     * it'll try to schedule the new tasks, if it couldn't
     * the latest new decomposed task is returned
     * @param tache
     * @return
     * @throws ExceptionDureeInvalide
     */
    public Tache plannifierTache(Tache tache) throws ExceptionDureeInvalide, ExceptionCollisionHorairesCreneau {
        Tache t = tache;
        HashMap<String,Object> map;
        for (Creneau creneau : creneaux) {
            if (creneau.isLibre()) {
                try {
                    map = ajouterTacheCreneau(t, creneau);
                } catch (ExceptionDureeInvalide e) {
                    continue;
                }
                if (map.get("creneau") != null) {
                    Creneau newCreneau = (Creneau) map.get("creneau");
                    ajouterCreneau(newCreneau);
                    return null;
                } else if ( (t = (Tache) map.get("tache")) == null){
                    return null;
                }
            }
        }
        return t;
    }
}
