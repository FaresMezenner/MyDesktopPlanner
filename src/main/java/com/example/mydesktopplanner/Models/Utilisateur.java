package com.example.mydesktopplanner.Models;

import com.example.mydesktopplanner.Models.ExceptionsPackage.*;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class Utilisateur implements Serializable {
    private String pseudo;
    private int[] rendementJournalierArray = {0,0};

    private int nbEncouragements = 0;
    private Jour jourRentable = null;
    private LocalDateTime[] tempsCategories = new LocalDateTime[6];
    private Duration tempsMinCreneau = Duration.ofMinutes(30);

    private LinkedList<Tache> unscheduledTaches = new LinkedList<>();
    private Calendrier calendrier = new Calendrier();
    private int[] badges = {0,0,0};
    private ArrayList<Projet> projets = new ArrayList<>();
    private int nbMinimalTachesParJourBadgeGood = 5;         // Pour l'attribution des badges (lire l'ennoncé)

    private  LocalDateTime lastUpdateTachesTime = LocalDateTime.of(2000,1,1,0,0);    // Cette variable sert a modifier l'etat des taches , (In progress -> Not realized)

    private LocalDateTime lastUpdateStatisticsTime = LocalDateTime.of(2000,1,1,0,0);    // Cette variable sert a modifier statistiques
    public Utilisateur(String pseudo) {
        this.pseudo = pseudo;
    }


    // ----------------------------- Delimitation Gettes / Setters ----------------------------------


    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public float getRendementJournalier() {
       if (rendementJournalierArray[1] == 0) {return 0;}
         return rendementJournalierArray[0] / rendementJournalierArray[1];
    }

    public int[] getRendementJournalierArray() {
        return rendementJournalierArray;
    }

    public void rendementJournalierArray(int[] rendementJournalier) {
        this.rendementJournalierArray = rendementJournalier;
    }



    public int getNbEncouragements() {
        return nbEncouragements;
    }

    public void setNbEncouragements(int nbEncouragements) {
        this.nbEncouragements = nbEncouragements;
    }

    public Jour getJourRentable() {
        return jourRentable;
    }

    public void setJourRentable(Jour jourRentable) {
        this.jourRentable = jourRentable;
    }

    public LocalDateTime[] getTempsCategories() {
        return tempsCategories;
    }

    public void setTempsCategories(LocalDateTime[] tempsCategories) {
        this.tempsCategories = tempsCategories;
    }

    public Duration getTempsMinCreneau() {
        return tempsMinCreneau;
    }

    public void setTempsMinCreneau(Duration tempsMinCreneau) {
        this.tempsMinCreneau = tempsMinCreneau;
    }

    public LinkedList<Tache> getUnscheduledTaches() {
        return unscheduledTaches;
    }

    public void setUnscheduledTaches(LinkedList<Tache> unscheduledTaches) {
        this.unscheduledTaches = unscheduledTaches;
    }

    public Calendrier getCalendrier() {
        return calendrier;
    }

    public void setCalendrier(Calendrier calendrier) {
        this.calendrier = calendrier;
    }

    public int[] getBadges() {
        return badges;
    }

    public void setBadges(int[] badges) {
        this.badges = badges;
    }

    public ArrayList<Projet> getProjets() {
        return projets;
    }

    public void setProjets(ArrayList<Projet> projets) {
        this.projets = projets;
    }

    public int getNbMinimalTachesParJour() {
        return nbMinimalTachesParJourBadgeGood;
    }

    public void setNbMinimalTachesParJour(int nbMinimalTachesParJour) {
        this.nbMinimalTachesParJourBadgeGood = nbMinimalTachesParJour;
    }

    public int getNbMinimalTachesParJourBadgeGood() {
        return nbMinimalTachesParJourBadgeGood;
    }

    public void setNbMinimalTachesParJourBadgeGood(int nbMinimalTachesParJourBadgeGood) {
        this.nbMinimalTachesParJourBadgeGood = nbMinimalTachesParJourBadgeGood;
    }

    public LocalDateTime getLastUpdateTachesTime() {
        return lastUpdateTachesTime;
    }

    public void setLastUpdateTachesTime(LocalDateTime lastUpdateTachesTime) {
        this.lastUpdateTachesTime = lastUpdateTachesTime;
    }

    public LocalDateTime getLastUpdateStatisticsTime() {
        return lastUpdateStatisticsTime;
    }

    public void setLastUpdateStatisticsTime(LocalDateTime lastUpdateStatisticsTime) {
        this.lastUpdateStatisticsTime = lastUpdateStatisticsTime;
    }

    // ----------------------------- Delimitation Gettes / Setters ----------------------------------

    public float getRendementPeriode(Periode periode) throws ExceptionPeriodeInexistante{
        return calendrier.getRendementPeriode(periode);
    }

    public void ajouterTache(Tache tache) {
        int i = 0;
        for (Tache t : unscheduledTaches) {
            if (tache.compareTo(t) > 0) {
                unscheduledTaches.add(i, tache);
                break;
            }
            i++;
        }
        if (i == unscheduledTaches.size()) {
            unscheduledTaches.add(tache);
        }
    }

    public void supprimerTacheUnscheduled(Tache tache) {
        unscheduledTaches.remove(tache);
    }

    public void ajouterPeriode(Periode periode) throws ExceptionDateInvalide , ExceptionCollisionPeriode {
        calendrier.ajouterPeriode(periode);
    }

    public void ajouterCreneau(Creneau creneau) throws ExceptionDateInvalide, ExceptionCollisionHorairesCreneau {
        calendrier.ajouterCreneau(creneau);

    }


    public void ajouterProjet(Projet projet) {
        projets.add(projet);
    }

    public void supprimerProjet(Projet projet) {
        projets.remove(projet);
    }

    public void dissocierTacheCreneau(Creneau creneau) {
        // Dissocie la tache d'un créneau et la rend UNSCHEDULED
        if (creneau == null || creneau.getTache() == null) {
            return;
        }
        ajouterTache(creneau.dissocierTache());
    }

    public void afficher(){
        System.out.println("Pseudo : " + pseudo);
        System.out.println("Rendement journalier : " + getRendementJournalier());
        System.out.println("Nombre d'encouragements : " + nbEncouragements);
        System.out.println("Jour rentable : " + jourRentable);
        System.out.println("Temps par catégories : " + tempsCategories);
        System.out.println("Temps minimal par créneau : " + tempsMinCreneau);
        System.out.println("Tâches non planifiées : " + unscheduledTaches);
        System.out.println("Calendrier : " + calendrier);
        System.out.println("Badges : " + badges);
        System.out.println("Projets : " );
        for (Projet p : projets){
            p.afficher();
        }
        System.out.println("Nombre minimal de tâches par jour pour le badge good : " + nbMinimalTachesParJourBadgeGood);
    }

    public void afficherCrenaux() {
        calendrier.afficherCrenaux();
    }

    public void plannifierTachePeriodique(CreneauPeriodique tache, int nJours, int nbFois) throws ExceptionDateInvalide, ExceptionCollisionHorairesCreneau {
        calendrier.plannifierTachePeriodique(tache, nJours, nbFois);
    }

    public void suprimerCreneau(Creneau tache) {
        calendrier.supprimerCreneau(tache);
    }

    public void supprimerTachesPeriodique(CreneauPeriodique b) {
        calendrier.supprimerTachesPeriodique(b);
    }

    public void supprimerPeriode(Periode periode) throws ExceptionPeriodeInexistante {
        calendrier.supprimerPeriode(periode);
    }


    /**
     * WARNING: THIS METHOD TAKES THE REAL INSTANCES OF THE tache, NOT COPIES
     * @param tache
     * @param creneau
     */
    public void affecterTacheCreneau( Creneau creneau,Tache tache ) throws ExceptionDureeInvalide, ExceptionCreneauNonLibre {
        // Affecte une tâche à un créneau et la retire des taches non planifiées
        // Affecte le créneau restant (si il existe) a la liste des créneaux et affecte la tache décomposée (si elle existe) dans la liste des taches
        if (tache == null || creneau == null) {
            return;
        }
        if (!creneau.isLibre()){
            throw new ExceptionCreneauNonLibre("Le créneau n'est pas libre , Dissociez la tache avant");
        }

        unscheduledTaches.remove(tache);
        HashMap<String,Object> map = calendrier.ajouterTacheCreneau(creneau,tache);

        Tache nouvelle_tache = (Tache) map.get("tache");
        Creneau nouveau_creneau = (Creneau) map.get("creneau");

        if (nouvelle_tache != null) {
            unscheduledTaches.add(nouvelle_tache);
            return;
        }
        if (nouveau_creneau != null) {
            try {
                calendrier.ajouterCreneau(nouveau_creneau);
                // Les exceptions suivantes sont théoriquement impossibles.
            } catch (ExceptionDateInvalide e) {
                throw new RuntimeException(e);
            } catch (ExceptionCollisionHorairesCreneau e) {
                throw new RuntimeException(e);
            }
        }
    }


    public void plannifierTachesPeriode(LinkedList<Tache> taches, Periode periode) throws ExceptionDureeInvalide, ExceptionCollisionHorairesCreneau {

        ArrayList<Tache> unscheduledTaches = calendrier.plannifierTachesPeriode(taches, periode);


        for (Tache unscheduledTache : unscheduledTaches) {


            // On ajoute les taches non planifiées à la liste des taches non planifiées de l'utilisateur
            if (!this.unscheduledTaches.contains(unscheduledTache)) {
                ajouterTache(unscheduledTache);
            }

        }


        //on supprime les taches planifiées de la liste des taches non planifiées de l'utilisateur
        for (Tache unscheduledTache : taches.stream().toList()) {
            if (!unscheduledTaches.contains(unscheduledTache)) {
                this.unscheduledTaches.remove(unscheduledTache);
            }
        }


    }

    public void ajouterTacheProjet(Projet projet , Creneau tache){
        projet.ajouterTache(tache);
    }

    public void supprimerTacheProjet(Projet projet , Creneau creneau , Tache tache){
        // Supprimer tache Projet
        projet.supprimerTache(tache);
        unscheduledTaches.remove(tache);
        if (creneau != null){
            creneau.setTache(null);
            creneau.setLibre(true);
        }
    }


    public void changerEtatTache(Creneau creneau, Etat etat){
        if (creneau == null || etat == null){return;}
        if (!creneau.isLibre()) {
            Tache tache = creneau.getTache();
            tache.setEtat(etat);
            // Always remember to check for greetings when calling this function}
        }
        }


    public void ajouterBadge(Badge badge){
        // Cette méthode ajoute un badge à l'utilisateur
        switch(badge){
            case GOOD:
                this.badges[0]++;
            case VERYGOOD:
                this.badges[1]++;
                break;
            case EXCELLENT:
                this.badges[2]++;
                break;
                default:
                System.out.println("Choix incorrect");
                break;
        }
    }

    public void incNbEncouragements(){
        this.nbEncouragements++;
    }

    // This method only gets the creneau of the task as a parameter
    public int attribuerFelicitationsBadges(Creneau creneau){
        // Cette fonction renvoies 0 si l'utilisateur n'a rien eu
        // Elle renvoies 1 si l'utilisateur recoit des félicitations
        // Elle renvoies 2 si l'utilisateur recoit un badge GOOD
        // Elle renvoie 3 si l'utilisateur recoit un badge VERYGOOD
        // Elle renvoie 4 si l'utilisateur recoit un badge EXCELLENT
        Jour jour = calendrier.getJourDate(creneau.getDate());
        Tache tache = creneau.getTache();
        Etat etat = tache.getEtat();
        // On vérifie si l'utilisateur a deja ete félicité
        if (jour.getFelicitations() || !(etat.equals(Etat.COMPLETED))){return 0;}
        try {
            int nbTachesAccomplies= 0;
            LinkedList<Creneau> creneaux = calendrier.getCreneauxJour(creneau.getDate());


            for (Creneau c : creneaux){
                Tache tacheCreneau = c.getTache();
                if (tacheCreneau != null){
                    if (tache.getEtat().equals(Etat.COMPLETED)){
                        nbTachesAccomplies++;
                    }
                }
            }

            if (nbTachesAccomplies >= getNbMinimalTachesParJour()){
                // Si on arrive a cette condition , on félicite l'utilisteur
                jour.setFelicitations(true);
                incNbEncouragements();

                ArrayList<Jour> jours = calendrier.getJoursIntervalle(creneau.getDate().minusDays(4),creneau.getDate());
                int felicitation_consecutives = 0;
                for (Jour jour_felicite : jours){
                    if ((jour_felicite.getFelicitations() == true) && !(jour_felicite.getBadgeObtenu())){
                        felicitation_consecutives++;}
                }

                if (felicitation_consecutives == 5 ){
                    for (Jour jour_felicite : jours){
                        jour_felicite.setBadgeObtenu(true);
                    }
                    badges[0] = badges[0] + 1;

                // On teste pour les badges VERYGOOD et EXCELLENT
                int verygood = badges[0] / 3;
                int excellent = verygood / 3;


                // On teste pour le badge VERYGOOD

                if (badges[1] != verygood){
                    badges[1] = verygood;
                    if (badges[2] != excellent){
                        badges[2] = excellent;
                        System.out.println("Excellent !");
                        return 4;
                    }System.out.println("Very GOOD !");
                    return 3;
                }System.out.println("GOOD !");
                return 2;
            }System.out.println("Felicitations !");
                return 1;
        }System.out.println("Rien");
            return 0;
        } catch (ExceptionDateInvalide e) {
            throw new RuntimeException(e);
        }
    }

    public void updateEtatTaches(){

        // Update l'etat des taches planifiées
        calendrier.updateEtatTaches(this.lastUpdateTachesTime);
        // Update l'etat des taches UNSCHEDULED
        for (Tache tache : unscheduledTaches){
            if (tache.getDateLimite().isBefore(LocalDate.now().atStartOfDay())){
                tache.setEtat(Etat.NOTREALIZED);
            }
        }
        setLastUpdateTachesTime(LocalDateTime.now());
    }


    public void updateStatistiques(){

    }

    public void plannifierTacheAutomatiquement(Tache tache,LocalDate date) throws ExceptionPlannificationImpossible {
        ArrayList<Creneau> creneaux = calendrier.getCreneauxIntervalle(date, calendrier.getDernierJour().getDate());

        // On filtre les creneaux pour obtenir que ceux qui sont libres , pour ne pas tout parcourir
        List<Creneau> creneauxLibres = creneaux.stream().filter(Creneau::isLibre).collect(Collectors.toList());
        if (!creneauxLibres.isEmpty() && !(creneauxLibres == null)){
            for (Creneau creneau : creneauxLibres) {
                    try {
                        try {
                            affecterTacheCreneau(creneau, tache);
                            return;
                        } catch (ExceptionCreneauNonLibre e) {
                        // On essaye de plannifier une atche dans un creneau deja occupe , on ne fait rien
                        }
                        } catch (ExceptionDureeInvalide e) {
                        // On essaye de plannifier une tache simple dans un créneau plus petit , on ne fait rien
                        }

        }
    }
        throw new ExceptionPlannificationImpossible("Aucun creneau disponible");
    }




}