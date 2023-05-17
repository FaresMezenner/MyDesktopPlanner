package com.example.mydesktopplanner.Models;

import ExceptionsPackage.*;

import java.util.HashMap;

// Cette classe est la classe globale du DesktopPlanner
// Cette classe n'est pas finie , il manque les methodes.
public class MyDesktopPlanner {
    private HashMap<String, Utilisateur> utilisateurs;

    public MyDesktopPlanner() {
        utilisateurs = new HashMap<>();
        // This constructor is umpty.
    }
    public void ajouterUtilisateur(String nom) throws ExceptionSaisieInvalide
    {
        controleDeSaisie(nom);
        Utilisateur utilisateur = new Utilisateur(nom);
        utilisateurs.put(nom, utilisateur);
    }

    public void supprimerUtilisateur(String nom) throws ExceptionSaisieInvalide , ExceptionUserDoesNotExist{
        controleDeSaisie(nom);
        if (utilisateurs.containsKey(nom)){utilisateurs.remove(nom);}
        else {
        throw new ExceptionUserDoesNotExist("ERREUR : Cet utilisateur n'existe pas");
        }
    }

    public Utilisateur authentifierUtilisateur(String nom) throws ExceptionUserDoesNotExist, ExceptionSaisieInvalide{
        controleDeSaisie(nom);
        Utilisateur u = utilisateurs.get(nom);
        if (u == null) {throw new ExceptionUserDoesNotExist("ERREUR : Cet utilisateur n'existe pas");}
        return u;
    }

    public void afficherUtilisateurs() {
        for (String nom : utilisateurs.keySet()) {
            System.out.println(nom);
        }
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
    }

