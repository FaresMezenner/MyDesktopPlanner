package com.example.mydesktopplanner.Models;


import com.example.mydesktopplanner.Models.ExceptionsPackage.ExceptionCollisionHorairesCreneau;
import com.example.mydesktopplanner.Models.ExceptionsPackage.ExceptionDateInvalide;
import com.example.mydesktopplanner.Models.ExceptionsPackage.ExceptionSaisieInvalide;

import java.time.Duration;

// Cette classe est la classe globale du DesktopPlanner
// Cette classe n'est pas finie , il manque les methodes.
public class MyDesktopPlanner {



    public static MyDesktopPlanner instance = null;


    public static MyDesktopPlanner initiateInstance(Utilisateur utilisateur) {
        if (instance == null) {
            instance = new MyDesktopPlanner(utilisateur);
            return instance;
        }
        else return instance;
    }

    public static MyDesktopPlanner getInstance() {
        return instance;
    }




    private  Utilisateur utilisateur;



    private MyDesktopPlanner(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }



    public static boolean isAlphaNumeric(String string) {
        // Renvoies vrai si string est alphanumérique
        return string != null && string.matches("^[a-zA-Z0-9]*$");
    }

    public void controleDeSaisie(String saisie) throws ExceptionSaisieInvalide {
        // Cette methode permets de controler les inputes en verifiant si c'est blank , vide et que c'est bien alphanumérique
        if (saisie.isEmpty() || saisie.isBlank() ){throw new ExceptionSaisieInvalide("ERREUR : Champ vide");}
        else if ( !isAlphaNumeric(saisie)) {throw new ExceptionSaisieInvalide("ERREUR : Le nom d'utilisateur ne doit contenir que des caractéres alphanumériques.");}
    }

    public Duration getTempsMinCreneau(){
        return utilisateur.getTempsMinCreneau();
    }

    public void ajouterTache(Tache tache){
        utilisateur.ajouterTache(tache);
    }

    public void ajouterTachePeriodique(CreneauPeriodique tache, int nJours, int nbFois) throws ExceptionDateInvalide, ExceptionCollisionHorairesCreneau {
        utilisateur.ajouterTachePeriodique(tache, nJours, nbFois);
    }

    public void ajouterPeriode(){
        utilisateur.ajouterPeriode();
    }

    public void ajouterCreneau(Creneau creneau) throws ExceptionDateInvalide, ExceptionCollisionHorairesCreneau {
        utilisateur.ajouterCreneau(creneau);
    }

    public void afficherTaches(){
        for (Tache t : utilisateur.getUnscheduledTaches()) {
            t.afficher();
        }
    }

    public void afficherCreneaux(){
        utilisateur.afficherCrenaux();
    }

}






