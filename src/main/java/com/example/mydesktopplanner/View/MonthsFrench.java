package com.example.mydesktopplanner.View;

public enum MonthsFrench {
    JANVIER("Janv"),
    FEVRIER("Févr"),
    MARS("Mars"),
    AVRIL("Avril"),
    MAI("Mai"),
    JUIN("Juin"),
    JUILLET("juil"),
    AOUT("Août"),
    SEPTEMBRE("Sept"),
    OCTOBRE("Oct"),
    NOVEMBRE("Nov"),
    DECEMBRE("Déc");

    private String name;

    MonthsFrench(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
