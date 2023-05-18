package com.example.mydesktopplanner.Models;


import com.example.mydesktopplanner.Models.ExceptionsPackage.*;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.TreeMap;

// Cette classe contiens les informations du calendrier
// Cette classe n'est pas finie , il manque les methodes.
// All the Exceptions are handled in this class

// Don't forget that we need to store the days in a file. So , we have to do some file IO.
public class Calendrier implements Serializable {
    private TreeMap<LocalDate, Jour> jours;
    private TreeMap<LocalDate, Periode> periodes;
    // Les jours des periodes doivent etre ajoutés a la array Jour[].

    private ArrayList<Tache> tachesUnscheduled;
    private final Utilisateur utilisateur;

public Calendrier(Utilisateur utilisateur) {
this.utilisateur = utilisateur;
this.jours = new TreeMap<>();
this.tachesUnscheduled = new ArrayList<>();
this.periodes = new TreeMap<>();

}
    // -------------------------------------- Delimitation Setters/Getters --------------------------------------
    public Utilisateur getUtilisateur() {
        return utilisateur;
    }
    public TreeMap<LocalDate, Jour> getJours() {
        return jours;
    }

    public void setJours(TreeMap<LocalDate, Jour> jours) {
        this.jours = jours;
    }

    public TreeMap<LocalDate, Periode> getPeriode() {
        return periodes;
    }

    public void setPeriode(TreeMap<LocalDate, Periode> periode) {
        this.periodes = periode;
    }

    public void ajouterPeriode(Periode periode) {
        this.periodes.put(periode.getJours().get(0).getDate(),periode);
    }



    public void supprimerPeriode(Periode periode) {
        this.periodes.remove(periode);
    }

    public ArrayList<Tache> getTachesUnscheduled() {
        return tachesUnscheduled;
    }

    public void setTachesUnscheduled(ArrayList<Tache> tachesUnscheduled) {
        this.tachesUnscheduled = tachesUnscheduled;
    }

    // -------------------------------------- Delimitation Setters/Getters --------------------------------------

    public void ajouterJour(LocalDate date) throws ExceptionDateInvalide{
     Jour jour;
     jour = new Jour(date, utilisateur);
     jours.put(date,jour);
    }

    public void ajouterCreneau(LocalDate jourCnreneau , LocalTime tempsDebut, LocalTime tempsFin) throws ExceptionCollisionHorairesCreneau, ExceptionDureeInvalide, ExceptionDateInvalide {

        // On crée un creneau en récupérant les heures de variables debut et fin.
        // La date du jour est contenu dans la variable debut.
        // Si le jour existe deja, on ne fait que de l'inserer dans la liste des creneaux de ce jour.
        // Si le jour n'existe pas, on le crée , et on l'ajoute a la liste des jours du calendrier.

        boolean jourExiste = false;
        Jour jour = null;

        // On parcours le tree et on verifie si le jour existe deja
        for (Jour jourCal : jours.values()){
            if (jourCal.getDate().equals(jourCnreneau)) {
                // Si le jour existe , on l'enregistre
                jourExiste = true;
                jour = jourCal;

            }
        }
        // Sinon , on en crée un nouveau.
        if (!jourExiste) {
            jour = new Jour(jourCnreneau, utilisateur);
            System.out.println("Le jour n'existe pas , il a été créé");
            jours.put(jour.getDate(),jour);
        }

        // Finalement , on ajoute le créneau au jour.
        jour.ajouterCreneau(tempsDebut,tempsFin);
    }

    public void supprimerCreneau(Creneau creneau) throws ExceptionDateInvalide{
        creneau.getJour().supprimerCreneau(creneau);
    }

    public  void afficher(){
        System.out.println("-------- Affichage du calendrier --------");
        if (jours == null){
            System.out.println("Le calendrier est vide");
        }
        else{
        for (Jour jour : jours.values()){jour.afficher();}
        }
}




    // Ajouter une nouvelle tache unscheduled
    public void ajouterTache(String nom, Duration duree , Priorite priorite , LocalDate dateLimite , Categorie categorie , boolean isPeriodique, boolean isDecomposable) {
        Tache nouvelleTache;
        if (isDecomposable){
            nouvelleTache = new TacheDecomposable(nom, duree, priorite, dateLimite, categorie, Etat.UNSCHEDULED,0);
        }
        else{
            nouvelleTache = new TacheSimple(nom, duree, priorite, dateLimite, categorie, isPeriodique, Etat.UNSCHEDULED);
        }
        tachesUnscheduled.add(nouvelleTache);
    }

    public void supprimerJour(LocalDate date) throws ExceptionDateInvalide{
        Jour jour = jours.remove(date);
        if (jour == null){
            throw new ExceptionDateInvalide("SUPPRESSION IMPOSSIBLE : Le jour n'existe pas");
        }
    }
    public void supprimerTache(Tache tache){
        tachesUnscheduled.remove(tache);
    }

    public void afficherTachesUnscheduled(){
        System.out.println("-------- Affichage des taches non planifiées --------");
        if (tachesUnscheduled == null){
            System.out.println("Il n'y a pas de taches non planifiées");
        }
        else{
            for (Tache tache : tachesUnscheduled){tache.afficher();}
        }
    }


    public ArrayList<Jour> getJoursIntervalle(LocalDate debut , LocalDate fin){
    // Cette fonction renvoies une liste contenant tout les jours du calendrier [EXISTANTS] dans l'intervalle indiqué
        return new ArrayList<>(jours.subMap(debut,fin.plusDays(1)).values());
    }

    public ArrayList<Creneau> getCreneauxJour(LocalDate date) throws ExceptionDateInvalide{
    Jour jour =  jours.get(date);
    if (jour != null){
        return jour.getCreneaux();
    }
    else{
        throw new ExceptionDateInvalide("Le jour n'existe pas");
    }
    }

    public void ajouterTache(Tache tache){
    tachesUnscheduled.add(tache);
    }


    public void PlannifierTacheManuellement(Tache tache , Creneau creneau) throws ExceptionCreneauOccupe, ExceptionDureeTacheIncompatible {
    // Cette fonction permets de plannifier une tache (c'est a dire l'associer a un créneau au choix)
        // Si cette tache est simple , on l'ajoute simplement au créneau
        // Si cette tache est décomposable , et que la durée du créneau
        // Cette fonction renvoies le créneau dans lequel la tache a été plannifiée.
        Tache nouvelle_tache = creneau.ajouterTache(tache);
        if (nouvelle_tache != null){ajouterTache(nouvelle_tache);} // Si la tache est décomposable , on ajoute les taches restantes dans la liste des taches non planifiées


    }

    public void plannifierTachePeriode(){
        // Si l'utilisateur ne rentre pas de periode , les taches sont plannifiées dans le jour , puis celui qui suit.
        // Tu dois respecter les dates limites et la priorité , sinon tu informe l'utilisateur que c'est impossible
        // L'etat de la tache doit aussi changer en NOTREALIZED
        //TODO



    }

    public void dissocierTache(Creneau creneau){
    // Cette fonction dissocie la tache d'un créneau , le créneau deviens libre , et la tache deviens unscheduled
    ajouterTache(creneau.getTache());
    creneau.supprimerTache();
    }

    public void ajouterPeriode(LocalDate debut , LocalDate fin){
    // Ajoute une periode qui contiens les jours de debut a fin , si les jours n'existent pas , il les crée
    // Il ne peux pas y avoir de collisions entre les periodes , sinon on renvoies une erreur

        for (LocalDate date = debut ; date.isBefore(fin.plusDays(1)) ; date = date.plusDays(1)){
            if (!jours.containsKey(date)){
                try {
                    ajouterJour(date);
                } catch (ExceptionDateInvalide exceptionDateInvalide) {
                    exceptionDateInvalide.printStackTrace();
                }
            }
        }
    }

    public boolean collisionPeriode(LocalDate debut , LocalDate fin){
    // TODO
    // Pour voir si il y'a collision , on parcourt les periodes jusqu'a trouver la premiére periode dont la date de fin est plus petite que la date de début
        // Ensuite , on va a la suivante et on verifie si la date de début est comprise entre le début et la fin , si oui on renvoies vrai
        // Sinon , on verifie si la date de fin est comprise entre le début et la fin , si oui on renvoies vrai
        // Sinon , on renvoies faux.
       // 1- On parcourt les periodes jusqu'a trouver la premiére periode dont la date de fin est plus petite que la date de début
        // LocalDate debut_periode = periodes.lowerKey(fin);
         //Periode fin_periode = periodes.lowerEntry(debut);

         //if (debut_periode != null){
         //   if (collisionDate(debut_periode , debut , fin)){return true;}
         //}
         //if (fin_periode != null){
         //       LocalDate date_fin = fin_periode.get().getFin();
         //       if (collisionDate(fin_periode.getFi, debut , fin)){return true;}
         //       if ()
         //}
         return false;
    }

    public boolean collisionDate(LocalDate date,LocalDate debut,LocalDate fin) {
    return (date.isAfter(debut) && date.isBefore(fin)) || date.isEqual(debut) || date.isEqual(fin);
    }

}