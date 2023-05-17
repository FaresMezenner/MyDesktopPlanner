package com.example.mydesktopplanner.Models.ExceptionsPackage;

public class ExceptionSaisieInvalide extends Exception{
    // Cette exception est retournée quand l'utiliséteur saisit des champs avec des caractéres spéciaux
    public ExceptionSaisieInvalide() {
        super("Exception : Saisie invalide , reverifiez le format de votre saisie");
    }

    public ExceptionSaisieInvalide(String message) {
        super(message);
    }
}
