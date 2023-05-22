package com.example.mydesktopplanner.Models;

import javafx.scene.paint.Color;

// Cette classe contiens la categorie a laquelle appartiens la tache
// L'integration des couleures n'est pas encore faite
public enum Categorie {
    // TODO : Affecter les couleurs aux categories

    STUDIES(Color.CYAN,0),WORK(Color.LIGHTGRAY,1),HOBBY(Color.LIGHTPINK,2),SPORT(Color.LIGHTYELLOW,3),HEALTH(Color.LIGHTGREEN,4),OTHERS(Color.LIGHTBLUE,5);

    final Color color;
    final int index;


    Categorie(Color color,int index){
        this.color = color;
        this.index = index;
    }

    public Color color() {
        return color;
    }

    public int index() {
        return index;
    }
}
