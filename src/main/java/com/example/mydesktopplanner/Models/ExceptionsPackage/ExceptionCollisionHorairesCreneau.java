package com.example.mydesktopplanner.Models.ExceptionsPackage;

// Cette exception est lancée lorsque l'utilisateur tente de créer un creneau qui chevauche un autre creneau
public class ExceptionCollisionHorairesCreneau extends Exception{
    public ExceptionCollisionHorairesCreneau() {
        super("Collision horaires creneau");}
    public ExceptionCollisionHorairesCreneau(String message){
        super(message);
    }
}
