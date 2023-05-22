package com.example.mydesktopplanner.Models.ExceptionsPackage;

// C'est l'exception qui est lancée quand la durée d'une tâche est invalide.
public class ExceptionDureeInvalide extends Exception{
    public ExceptionDureeInvalide() {
        super("La durée entrée est invalide");
    }

    public ExceptionDureeInvalide(String message) {
        super(message);
    }
}
