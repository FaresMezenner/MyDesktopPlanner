package com.example.mydesktopplanner.Models;


import com.example.mydesktopplanner.Models.ExceptionsPackage.*;

import java.time.Duration;
import java.util.HashMap;
import java.util.LinkedList;

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

    public void suprimerTache(Creneau tache){
        utilisateur.suprimerCreneau(tache);
    }

    public void plannifierTachePeriodique(CreneauPeriodique tache, int nJours, int nbFois) throws ExceptionDateInvalide, ExceptionCollisionHorairesCreneau {
        utilisateur.plannifierTachePeriodique(tache, nJours, nbFois);
    }

    public void ajouterPeriode(Periode periode) throws ExceptionDateInvalide , ExceptionCollisionPeriode {
        utilisateur.ajouterPeriode(periode);
    }

    public void ajouterCreneau(Creneau creneau) throws ExceptionDateInvalide, ExceptionCollisionHorairesCreneau {
        utilisateur.ajouterCreneau(creneau);
    }

    public void afficherTaches(){
        for (Tache t : utilisateur.getUnscheduledTaches()) {
            t.afficher();
        }
    }

    public void affecterTacheCreneau(Tache tache, Creneau creneau) throws ExceptionDureeInvalide {
        utilisateur.affecterTacheCreneau(tache, creneau);
    }

    public void plannifierTachesPeriode(LinkedList<Tache> taches, Periode periode) throws ExceptionDureeInvalide, ExceptionCollisionHorairesCreneau {
        utilisateur.plannifierTachesPeriode(taches, periode);
    }


    public void afficherHashmap(HashMap<String, Object> map) {
        System.out.println(map);
        if (map == null || map.isEmpty()) {
            System.out.println("MAP vide");
            return;
        }
        if (map.get("creneau") != null){
            Creneau creneau = (Creneau) map.get("creneau");
            creneau.afficher();
        }

        if (map.get("tache") != null){
            Tache tache = (Tache) map.get("tache");
            tache.afficher();
        }

    }

    public LinkedList<Tache> getUnscheduledTaches(){
        return utilisateur.getUnscheduledTaches();
    }

    public void afficherCreneaux(){
        utilisateur.afficherCrenaux();
    }

    /**
     * WARNING: THIS METHOD TAKES THE REAL INSTANCES OF THE OBJECTS, NOT COPIES
     * @param b
     */
    public void supprimerTachesPeriodique(CreneauPeriodique b) {
        utilisateur.supprimerTachesPeriodique(b);
    }


    public void supprimerCreneau(Creneau creneau) {
        utilisateur.suprimerCreneau(creneau);
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void ajouterTacheProjet(Projet projet , Tache tache){
        utilisateur.ajouterTacheProjet(projet,tache);
    }

    public void changerEtatTache(Creneau creneau , Etat etat){
        utilisateur.changerEtatTache(creneau,etat);
    }

    public int attribuerFelicitationsBadges(Creneau creneau){
        return utilisateur.attribuerFelicitationsBadges(creneau);
    }

    public void supprimerTacheProjet(Projet projet , Creneau creneau , Tache tache){
        utilisateur.supprimerTacheProjet(projet,creneau,tache);
    }

    public void ajouterProjet(Projet projet){
        utilisateur.ajouterProjet(projet);
    }

    public void supprimerProjet(Projet projet){
        utilisateur.supprimerProjet(projet);
    }

    public void dissocierTacheCreneau(Creneau creneau){
        utilisateur.dissocierTacheCreneau(creneau);
    }

    public void supprimerPeriode(Periode periode) throws ExceptionPeriodeInexistante{
        utilisateur.supprimerPeriode(periode);
    }

    public void supprimerTacheUnscheduled(Tache tache){
        utilisateur.supprimerTacheUnscheduled(tache);
    }








}






