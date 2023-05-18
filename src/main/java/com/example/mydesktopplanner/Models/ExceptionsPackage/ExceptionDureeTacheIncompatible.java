package com.example.mydesktopplanner.Models.ExceptionsPackage;

public class ExceptionDureeTacheIncompatible extends Exception{
    // Cette exception est lancée quand on essaye d'affecter une tache a un creneau qui ne peut pas la contenir. [ Exemple : creneau de 1h , tache de 2h ]
    public ExceptionDureeTacheIncompatible(String message){
        super(message);
    }
}
