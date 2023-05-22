package com.example.mydesktopplanner.Models;

// Cette classe contiens les etats possibles d'une tache
public enum Etat{
    NOTREALIZED("Non réalisé"),
    COMPLETED("terminée"),
    INPROGRESS("en cours"),
    CANCELLED("annulée"),
    DELAYED("retardée"),
    UNSCHEDULED("non planifiée");

    final String name;


    Etat(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }


    public static Etat getEtat(String name) {
        for (Etat etat : Etat.values()) {
            if (name.equals(etat.getName())) {
                return etat;

            }
        }
        return null;
    }
}
