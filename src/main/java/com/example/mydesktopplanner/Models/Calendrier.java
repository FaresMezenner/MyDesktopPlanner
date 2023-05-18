package com.example.mydesktopplanner.Models;


import com.example.mydesktopplanner.Models.ExceptionsPackage.*;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.TreeMap;

// Cette classe contiens les informations du calendrier
// Cette classe n'est pas finie , il manque les methodes.
// All the Exceptions are handled in this class

// Don't forget that we need to store the days in a file. So , we have to do some file IO.
public class Calendrier implements Serializable {
    private TreeMap<LocalDateTime, Jour> jours = new TreeMap<>();;
    private TreeMap<LocalDateTime, Periode> periodes= new TreeMap<>();
    // Les jours des periodes doivent etre ajoutés a la array Jour[].


public Calendrier() {

}


}