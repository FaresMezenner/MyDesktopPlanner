package com.example.mydesktopplanner.Models;


import com.example.mydesktopplanner.Models.ExceptionsPackage.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedList;


// Cette classe contient les informations des jours
// Cette classe n'est pas finie , il manque les methodes

public class Jour implements Serializable {
    private LinkedList<Creneau> creneaux = new LinkedList<Creneau>();
    private LocalDateTime date;


    private int nbTachesAccomplies = 0;
    private boolean felicitations = false;


    public Jour(LocalDateTime date) throws ExceptionDateInvalide{
        if (date == null || date.toLocalDate().isBefore(LocalDateTime.now().toLocalDate())){
            // Si la date est null , ou elle est avant la date actuelle , on lance une exception
            throw new ExceptionDateInvalide("La date est invalide");
        }
        this.date = date;
    }

    public void ajouterCreneau(Creneau creneau) throws ExceptionCollisionHorairesCreneau {

    }


}
