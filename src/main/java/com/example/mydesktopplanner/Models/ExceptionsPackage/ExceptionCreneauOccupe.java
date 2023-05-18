package com.example.mydesktopplanner.Models.ExceptionsPackage;

public class ExceptionCreneauOccupe extends Exception{
    // Cette exception est lancée quand on essaye d'affecter une tache a un creneau occupé.
    public ExceptionCreneauOccupe(String message){
        super(message);
    }
}
