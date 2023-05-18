"# MyDesktopPlanner" 
"# MyDesktopPlanner" 


Dans l'interface graphique n'oublie pas que dans la plannification des taches l'utilisateur
peut soit accepter la proposition soit la refuser.




// Fonctionnalités de l'application :
- SetFiles()                               DONE
- Authentification                               DONE
- Ajouter un utilisateur                                DONE
- Supprimer un utilisateur - Fares
- Ajouter un projet                               DONE
- Supprimer un projet                             DONE
- Ajouter tache a un projet                      DONE
- Supprimer tache d'un projet                    DONE
- Ajouter tache UNSCHEDULED                    DONE
- Supprimer une tache UNSCHEDULED                    DONE
- Ajouter une periode - Sofiane
- Supprimer une periode - Sofiane
- Plannifier une tache - Sofiane
- Plannifier une liste de taches sur une periode - Sofiane
- Ajouter un creneau - Fares
- Supprimer un creneau - Fares
- Decomposer() Creneau - Sofiane
- Decomposer() Tache - Sofiane
- AjouterTachePeriodique() - Fares
- SupprimerTachePeriodique() - Fares
- Attribution des badges
- GetStatistiques()



// Defining the interface of the business layer : (Didn't specify Getters and Setters , they obviously exist)

---------------------------------------------------- My Desktop Planner ----------------------------------------------------

ajouterUtilisateur(String nom) : crée un nouvel utilisateur

supprimerUtilisateur(String nom) : Supprime un utilisateur

afficherUtilisateurs() : For debugging reasons

authentifierUtilisateur(String nom) : Returns Utilisateur object if exists (Will be useful after)


---------------------------------------------------- Utilisateur ---------------------------------------------------- (With the object that you got from the authentification)

ajouterProjet(Projet projet) : Updates the Array of projects of the user , by adding the project

supprimerProjet(Projet projet) : Updates the Array of projects of the user , by removing the project

ajouterTache(Tache tache) : Updates the Array of unscheduled tasks of the user , by adding the task

supprimerTache(Tache tache) : Updates the Array of unscheduled tasks of the user , by removing the task

The most important thing in this class is the getCalendrier() method that returns the Calendrier object of the user

---------------------------------------------------- Calendrier -------------------------------------------- ( Main class that you will work with )

ajouterJour(LocalDate date) : Adds a new day to the calendar , the date is LocalDate object

supprimerJour(LocalDate date) : Removes a day from the calendar # When deleting a day , check for the tasks

getJoursIntervalle(LocalDate debut , LocalDate fin) : Returns an ArrayList of days (Jour objects) between the two dates (including the two dates)

ajouterCreneau(LocalDate jourCnreneau , LocalTime tempsDebut, LocalTime tempsFin) : Ajoute un creneau au calendrier de l'utilisateur, si le jour spécifié n'existe pas , il sera crée automatiquement

getCreneauxJour(LocalDate date) : Returns an ArrayList of creneaux (Creneau objects) of the specified day

supprimerCreneau(Creneau creneau) : Supprime un creneau du calendrier de l'utilisateur

dissocierTache(Creneau creneau) : Dissocie la tache d'un créneau (Le créneau devient libre , et la tache redeviens unsheduled)

plannifierTacheManuellement(Tache tache , Creneau creneau) : Plannifie une tache dans un creneau spécifié , si cette tache est décomposée , les autres sous-taches sont ajoutées a la liste des UNSCHEDULED tasks

plannifierTachePeriode() : Plannifie automoatiquement des taches selon leur priorité et leur date limite sur une periode

ajouterPeriode(LocalDate debut , LocalDate fin) : Ajoute une periode au calendrier de l'utilisateur 

supprimerPeriode(LocalDate debut , LocalDate fin) : Supprime une periode du calendrier de l'utilisateur





// TODO : Ajouter couleures aux taches de méme categorie