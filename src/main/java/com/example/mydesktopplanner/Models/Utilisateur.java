package com.example.mydesktopplanner.Models;

import com.example.mydesktopplanner.Models.ExceptionsPackage.*;

import java.awt.*;
import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class Utilisateur implements Serializable {
    private String pseudo;
    private int[] rendementJournalierArray = {0,0};

    private int nbEncouragements = 0;
    private Jour jourRentable = null;
    private Duration[] tempsCategories = {Duration.ofMinutes(0),Duration.ofMinutes(0),Duration.ofMinutes(0),Duration.ofMinutes(0),Duration.ofMinutes(0),Duration.ofMinutes(0)};
    private Duration tempsMinCreneau = Duration.ofMinutes(30);

    private LinkedList<Tache> unscheduledTaches = new LinkedList<>();

    private LinkedList<Tache> cancelledTaches = new LinkedList<>();
    private Calendrier calendrier = new Calendrier();

    private TreeMap<LocalDateTime,Archive> archives = new TreeMap<>();
    private int[] badges = {0,0,0};
    private ArrayList<Projet> projets = new ArrayList<>();
    private int nbMinimalTachesParJourBadgeGood = 5;         // Pour l'attribution des badges (lire l'ennoncé)

    private int nbTachesCompletees= 0;

    private Color couleursCategorie[] = {Color.CYAN,Color.lightGray,Color.pink,Color.yellow,Color.green,Color.blue};

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

    public Duration[] getTempsCategories() {
        return tempsCategories;
    }

    public void setTempsCategories(Duration[] tempsCategories) {
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

    public void setRendementJournalierArray(int[] rendementJournalierArray) {
        this.rendementJournalierArray = rendementJournalierArray;
    }

    public LinkedList<Tache> getCancelledTaches() {
        return cancelledTaches;
    }

    public void setCancelledTaches(LinkedList<Tache> cancelledTaches) {
        this.cancelledTaches = cancelledTaches;
    }

    public TreeMap<LocalDateTime, Archive> getArchives() {
        return archives;
    }

    public void setArchives(TreeMap<LocalDateTime, Archive> archives) {
        this.archives = archives;
    }

    public int getNbTachesCompletees() {
        return nbTachesCompletees;
    }

    public void setNbTachesCompletees(int nbTachesCompletees) {
        this.nbTachesCompletees = nbTachesCompletees;
    }

    public Color[] getCouleursCategorie() {
        return couleursCategorie;
    }

    public void setCouleursCategorie(Color[] couleursCategorie) {
        this.couleursCategorie = couleursCategorie;
    }

    // ----------------------------- Delimitation Gettes / Setters ----------------------------------


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
        if (creneau.getTache().getEtat().equals(Etat.COMPLETED)) {
            nbTachesCompletees--;
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

    public void supprimerTacheProjet(Projet projet , Creneau creneau){
        // Supprimer tache Projet
        if (projet == null || creneau == null){return;}

        if ((creneau.getTache() != null) && (creneau.getTache().getEtat().equals(Etat.COMPLETED))){
            nbTachesCompletees--;
        }
        projet.supprimerTache(creneau);
    }

    public void ajouterDureeCategorie(Categorie categorie , Duration duree){
        if (duree == null || categorie == null){return;}

        tempsCategories[categorie.index()] = tempsCategories[categorie.index()].plus(duree);

    }

    public TreeMap<LocalDate, Periode> getPeriodes() {
        return calendrier.getPeriodes();
    }
    public void changerEtatTache(Creneau creneau, Etat etat){
        if (creneau == null || etat == null ){return;}
        if (!creneau.isLibre()) {
            Tache tache = creneau.getTache();
            tache.setEtat(etat);

            if (etat.equals(Etat.COMPLETED)){
                Categorie categorie = tache.getCategorie();
                ajouterDureeCategorie(categorie,tache.getDuree());
                nbTachesCompletees++;
            }
            // Always remember to check for greetings when calling this function}
        }
        updateEtatTaches();
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
        Etat etat = Etat.CANCELLED;
        if (tache != null){etat = tache.getEtat();}
        // On vérifie si l'utilisateur a deja ete félicité
        if (jour.getFelicitations() || !(etat.equals(Etat.COMPLETED))){return 0;}
        try {
            int nbTachesAccomplies= 0;
            LinkedList<Creneau> creneaux = calendrier.getCreneauxJour(creneau.getDate());


            for (Creneau c : creneaux){
                Tache tacheCreneau = c.getTache();
                if (tacheCreneau != null){
                    if (tacheCreneau.getEtat().equals(Etat.COMPLETED)){
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
            if (tache.getDateLimite().isBefore(LocalDate.now())){
                tache.setEtat(Etat.NOTREALIZED);
            }
        }
        setLastUpdateTachesTime(LocalDateTime.now());
    }

    public void plannifierTacheAutomatiquement(Tache tache,LocalDate date) throws ExceptionPlannificationImpossible {
        ArrayList<Creneau> creneaux = calendrier.getCreneauxIntervalle(date, calendrier.getDernierJour().getDate());

        // On filtre les creneaux pour obtenir que ceux qui sont libres , pour ne pas tout parcourir
        List<Creneau> creneauxLibres = creneaux.stream().filter(Creneau::isLibre).collect(Collectors.toList());
        if (!creneauxLibres.isEmpty() && !(creneauxLibres == null)){
            for (Creneau creneau : creneauxLibres) {
                if (creneau.getDate().isAfter(tache.getDateLimite())) {
                    throw new ExceptionPlannificationImpossible("Creneau non libre");
                }
                try {
                    affecterTacheCreneau(creneau, tache);
                    return;
                } catch (ExceptionCreneauNonLibre e) {
                // On essaye de plannifier une atche dans un creneau deja occupe , on ne fait rien
                } catch (ExceptionDureeInvalide e) {
                // On essaye de plannifier une tache simple dans un créneau plus petit , on ne fait rien
                }
        }
    }
        throw new ExceptionPlannificationImpossible("Aucun creneau disponible");
    }


    public float getRendementJournalier(Jour jour){
        // Renvoies le nombre de taches réalisés / nombre de taches planifiées
        if (jour == null){return 0;}
        int nbTachesRealisees = 0;
        int nbTachesPlanifiees = 0;
        try {
            LinkedList<Creneau> creneaux = calendrier.getCreneauxJour(jour.getDate());
            if (creneaux != null && !creneaux.isEmpty()){
                for (Creneau creneau : creneaux){
                    if (creneau.getTache() != null){
                        nbTachesPlanifiees++;

                        if (creneau.getTache().getEtat().equals(Etat.COMPLETED)){
                            nbTachesRealisees++;

                        }
                    }
                }
            }
            if (nbTachesPlanifiees == 0){
                return 0;
            }
            return (float) nbTachesRealisees / nbTachesPlanifiees;
        } catch (ExceptionDateInvalide e) {
            throw new RuntimeException(e);
        }

    }

    public float getRendementJournalierToday(){
        return getRendementJournalier(calendrier.getJourDate(LocalDate.now()));
    }

    public Jour UpdateJourRentable(){
        // On calcule le jour le plus rentable
        float rendementMax = 0;
        LocalDate debut = lastUpdateStatisticsTime.toLocalDate();
        ArrayList<Jour> jours = calendrier.getJoursIntervalle(debut, LocalDate.now());
        if (jours.isEmpty() || jours == null){
            return null;
        }
        if (jourRentable != null){
        rendementMax = getRendementJournalier(jourRentable);}
        for (Jour jour : jours){
            float rendement = getRendementJournalier(jour);
            if (rendement > rendementMax){
                rendementMax = rendement;
                jourRentable = jour;
            }
        }
        // setLastUpdateStatisticsTime(LocalDateTime.now());
        return jourRentable;
    }

    public float updateRendementJournalier(){
    int[] rendement = getRendementJournalierArray();
    float rendementMoyen = 0;

    int tachesRealisees = rendement[0];
    int tachesTotales = rendement[1];

    LocalDateTime debut = getLastUpdateStatisticsTime();
    LocalDateTime fin = LocalDateTime.now();

    // On récupére tout les creneaux dont la date de fin est comprise entre debut et fin

    ArrayList<Creneau> creneaux = calendrier.getCreneauxIntervalle(debut.toLocalDate(),fin.toLocalDate());
    Tache tache;
    // On récupére le nombre de creneaux dont la date de fin est comprise entre debut et fin
    for (Creneau creneau : creneaux){
        if (creneau.getDebut().plus(creneau.getDuree()).isBefore(fin)){
            tache = creneau.getTache();
            if (tache != null){
                tachesTotales++;
                if (tache.getEtat().equals(Etat.COMPLETED)){
                    tachesRealisees++;
                }
            }
        }
    }
    if (tachesTotales == 0){return rendementJournalierArray[0]/rendementJournalierArray[1];}
    this.rendementJournalierArray[0] = rendement[0] + tachesRealisees;
    this.rendementJournalierArray[1] = rendement[1] + tachesTotales;
    return rendement[0]/rendement[1];

    }

    public ArrayList<Creneau> getCreneauxIntervalle(LocalDate debut, LocalDate fin){
        return calendrier.getCreneauxIntervalle(debut,fin);
    }

    public void cancelUnscheduledTache(Tache tache){
        unscheduledTaches.remove(tache);
        cancelledTaches.add(tache);
    }
    public void cancelScheduledTache(Creneau tache){
        Tache tache_supprimee = tache.dissocierTache();
        cancelUnscheduledTache(tache_supprimee);
    }

    public void updateStatistics(){
        updateRendementJournalier();
        jourRentable = UpdateJourRentable();
        setLastUpdateStatisticsTime(LocalDateTime.now());
    }

    public float getRendementPeriode(Periode periode) throws ExceptionPeriodeInexistante{
        if (periode == null){throw new ExceptionPeriodeInexistante("La periode est null");}

        ArrayList<Jour> jours = calendrier.getJoursIntervalle(periode.getDebut(),periode.getFin());
        TreeMap<LocalDate,Periode> periodes = calendrier.getPeriodes();
        if (periodes.containsValue(periode)){
        jours = calendrier.getJoursIntervalle(periode.getDebut(),periode.getFin());
        // nombre jours est egal au nombre de jours dans la periode
        int nombreJours = jours.size();
        float rendementJournalier = 0;
        for (Jour jour : jours){
            rendementJournalier = rendementJournalier + getRendementJournalier(jour);

        }
        if (nombreJours == 0){return 0;}
        return rendementJournalier/nombreJours;
        }
        return 0;
    }

    public boolean projetComplete(Projet projet){
        // Renvoies true si l'entiéreté des taches d'un projet sont a l'état completed
        // Renvoies false sinon
        TreeMap<LocalDateTime,Creneau> taches = projet.getTaches();
        if (taches == null || taches.isEmpty()){return true;}

        for (Creneau creneau : taches.values()){
            if (creneau.getTache() != null){
                if(!creneau.getTache().getEtat().equals(Etat.COMPLETED)){return false;}
            }
        }
        return true;
    }

    public void afficherArchive(){
        for (LocalDateTime date : archives.keySet()){
            System.out.println(date);
            System.out.println(archives.get(date));
        }
    }


    public void archiver(){
        // Cette méthode permets d'archiver les données d'un utilisateur;
        // On crée une nouvelle instance de Archive , et on supprime les données de l'utilisateur.

        int projetsCompletes = 0;
        for (Projet projet : projets){
            if (projetComplete(projet)){
                projetsCompletes++;
            }
        }

        Archive archive = new Archive(getBadges(),nbTachesCompletees,projetsCompletes);

        archives.put(LocalDateTime.now(),archive);


        // On efface les données de l'utilisateur
          rendementJournalierArray = new int[] {0,0};
          nbEncouragements = 0;
          jourRentable = null;
          tempsCategories = new Duration[] {Duration.ofMinutes(0),Duration.ofMinutes(0),Duration.ofMinutes(0),Duration.ofMinutes(0),Duration.ofMinutes(0),Duration.ofMinutes(0)};
          unscheduledTaches = new LinkedList<>();
          cancelledTaches = new LinkedList<>();
          calendrier = new Calendrier();
          badges = new int[]{0,0,0};
          projets = new ArrayList<>();
          nbTachesCompletees= 0;
          lastUpdateTachesTime = LocalDateTime.of(2000,1,1,0,0);    // Cette variable sert a modifier l'etat des taches , (In progress -> Not realized)
          lastUpdateStatisticsTime = LocalDateTime.of(2000,1,1,0,0);    // Cette variable sert a modifier statistiques




    }
}