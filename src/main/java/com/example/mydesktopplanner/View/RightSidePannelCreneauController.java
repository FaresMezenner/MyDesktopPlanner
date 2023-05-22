package com.example.mydesktopplanner.View;

import com.example.mydesktopplanner.Models.Categorie;
import com.example.mydesktopplanner.Models.Priorite;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.ResourceBundle;

public class RightSidePannelCreneauController implements Initializable {

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
    private String[] Etat = new String[com.example.mydesktopplanner.Models.Etat.values().length];

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        for (Categorie categorie : Categorie.values()) {
            categories[categorie.ordinal()] = categorie.getName();
        }

        for (com.example.mydesktopplanner.Models.Etat etat : com.example.mydesktopplanner.Models.Etat.values()) {
            Etat[etat.ordinal()] = etat.getName();

        }

        CategorieChoiceBox.getItems().addAll(categories);
        PrioriteChoiceBox.getItems().addAll(priorites);
        EtatChoiceBox.getItems().addAll(Etat);
    }
}
