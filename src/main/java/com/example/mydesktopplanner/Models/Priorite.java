package com.example.mydesktopplanner.Models;

// Cette classe contiens les priorités possibles d'une tache
public enum Priorite {
LOW("Low"),
    MEDIUM("Medium"),
    HIGH("High");

final String name;

    Priorite(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    static public Priorite getPriorite(String name){
        for (Priorite priorite : Priorite.values()) {
            if (priorite.getName().equals(name)) {
                return priorite;
            }
        }
        return null;
    }

}




