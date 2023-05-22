package com.example.mydesktopplanner.Models;

import javafx.scene.paint.Color;

// Cette classe contiens la categorie a laquelle appartiens la tache
// L'integration des couleures n'est pas encore faite
public enum Categorie {
    // TODO : Affecter les couleurs aux categories

    STUDIES(Color.CYAN,0, "Studies"),
    WORK(Color.LIGHTGRAY,1, "Work"),
    HOBBY(Color.LIGHTPINK,2, "Hobby"),
    SPORT(Color.LIGHTYELLOW,3, "Sport"),
    HEALTH(Color.LIGHTGREEN,4, "Health"),
    OTHERS(Color.LIGHTBLUE,5, "Other");

    final Color color;
    final int index;
    final String name;


    Categorie(Color color,int index, String name){
        this.color = color;
        this.index = index;
        this.name = name;
    }

    public Color color() {
        return color;
    }

    public int index() {
        return index;
    }

    public String getName() {
        return name;
    }

    static public Categorie getCategorie(String name){
        for (Categorie categorie : Categorie.values()) {
            if (categorie.getName().equals(name)) {
                return categorie;
            }
        }
        return null;
    }
}
