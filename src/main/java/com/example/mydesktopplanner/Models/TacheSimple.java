package com.example.mydesktopplanner.Models;

import javax.swing.plaf.TableHeaderUI;
import java.time.Duration;
import java.time.LocalDateTime;

public class TacheSimple extends Tache{

    boolean isPeriodique;


    public TacheSimple(String nom, Duration durée, Priorite priorite, LocalDateTime dateLimite, Categorie categorie, boolean isPeriodique) {
        super(nom, durée, priorite, dateLimite, categorie);
        this.isPeriodique = isPeriodique;
    }


    public boolean isPeriodique() {
        return isPeriodique;
    }

    public void setPeriodique(boolean periodique) {
        isPeriodique = periodique;
    }

    @Override
    boolean isDecomposable() {
        return false;
    }

    public void afficher(){
        super.afficher();
        System.out.println("Is periodique : " + isPeriodique);
    }

}
