"# MyDesktopPlanner" 
"# MyDesktopPlanner" 


Dans l'interface graphique n'oublie pas que dans la plannification des taches l'utilisateur
peut soit accepter la proposition soit la refuser.

// Defining the interface of the business layer : (Didn't specify Getters and Setters , they obviously exist)

---------------------------------------------------- My Desktop Planner ----------------------------------------------------

ajouterUtilisateur(String nom) : crée un nouvel utilisateur

supprimerUtilisateur(String nom) : Supprime un utilisateur

afficherUtilisateurs() : Affiche les utilisateurs (I guess it's useless for you)

authentifierUtilisateur(String nom) : Returns Utilisateur object if exists (Will be useful after)


---------------------------------------------------- Utilisateur ---------------------------------------------------- (With the object that you got from the authentification)

ajouterProjet(Projet projet) : Updates the Array of projects of the user , by adding the project

supprimerProjet(Projet projet) : Updates the Array of projects of the user , by removing the project

The most important thing in this class is the getCalendrier() method that returns the Calendrier object of the user

---------------------------------------------------- Calendrier -------------------------------------------- ( Main class that you will work with )

ajouterJour(LocalDate date) : Adds a new day to the calendar , the date is LocalDate object

supprimerJour(LocalDate date) : Removes a day from the calendar # When deleting a day , check for the tasks

getJoursIntervalle(LocalDate debut , LocalDate fin) : Returns an ArrayList of days (Jour objects) between the two dates (including the two dates)

ajouterCreneau(LocalDate jourCnreneau , LocalTime tempsDebut, LocalTime tempsFin) : Ajoute un creneau au calendrier de l'utilisateur, si le jour spécifié n'existe pas , il sera crée automatiquement

getCreneauxJour(LocalDate date) : Returns an ArrayList of creneaux (Creneau objects) of the specified day

supprimerCreneau(Creneau creneau) : Supprime un creneau du calendrier de l'utilisateur

dissocierTache(Creneau creneau) : Dissocie la tache d'un créneau (Le créneau devient libre , et la tache redeviens unsheduled)

ajouterTache(String nom, Duration duree , Priorite priorite , LocalDate dateLimite , Categorie categorie , boolean isPeriodique,boolean isDecomposable) : Ajoute une tache a la liste  des taches unscheduled (it's an array in the calendar , these tasks are not scheduled yet , they will be scheduled later , with different options)

supprimerTache(Tache tache) : Supprime une tache de la liste des taches unscheduled

plannifierTacheManuellement(Tache tache , Creneau creneau) : Plannifie une tache dans un creneau spécifié , si cette tache est décomposée , les autres sous-taches sont ajoutées a la liste des UNSCHEDULED tasks

plannifierTachePeriode() : Plannifie automoatiquement des taches selon leur priorité et leur date limite sur une periode

ajouterPeriode(LocalDate debut , LocalDate fin) : Ajoute une periode au calendrier de l'utilisateur 











