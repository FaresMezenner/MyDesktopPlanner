package com.example.mydesktopplanner.View;

import com.example.mydesktopplanner.Models.Categorie;
import com.example.mydesktopplanner.Models.Etat;
import com.example.mydesktopplanner.Models.Priorite;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import com.example.mydesktopplanner.Models.Etat;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class RightSidePannelTachesController implements Initializable {

    @FXML
    ChoiceBox<String> CategorieChoiceBox;
    @FXML
    ChoiceBox<String> PrioriteChoiceBox;
    @FXML
    ChoiceBox<String> EtatChoiceBox;

    private String[] categories = new String[Categorie.values().length];
    private String[] priorites = {
            Priorite.LOW.getName(),
            Priorite.MEDIUM.getName(),
            Priorite.HIGH.getName()
    };
    private String[] Etats = new String[2];

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        for (Categorie categorie : Categorie.values()) {
            categories[categorie.ordinal()] = categorie.getName();
        }

        int i = 0;
        for (Etat etat : Etat.values()) {
            if (etat == Etat.UNSCHEDULED || etat == Etat.INPROGRESS) {
                Etats[i] = etat.getName();
                i++;
            }

        }

        CategorieChoiceBox.getItems().addAll(categories);
        PrioriteChoiceBox.getItems().addAll(priorites);
        EtatChoiceBox.getItems().addAll(Etats);
    }
}
