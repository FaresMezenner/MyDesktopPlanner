package com.example.mydesktopplanner.View;

import com.example.mydesktopplanner.Models.Categorie;
import com.example.mydesktopplanner.Models.Etat;
import com.example.mydesktopplanner.Models.Priorite;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AjouterTache implements Initializable {

    @FXML
    ChoiceBox<String> CategorieChoiceBox;
    @FXML
    ChoiceBox<String> PrioriteChoiceBox;

    @FXML
    TextField PeriodiciteTextField;

    @FXML
    TextField NbPlannificationsTextField;

    @FXML
    TextField DureeTextField;
    @FXML
    TextField DebutHeures, DebutMinutes, FinHeures, FinMinutes;



    private String[] categories = new String[Categorie.values().length];
    private String[] priorites = {
            Priorite.LOW.getName(),
            Priorite.MEDIUM.getName(),
            Priorite.HIGH.getName()
    };
    public void setNumeric(TextField tf){
        tf.textProperty().addListener((observable, oldValue, newValue) -> {
            // Vérifie chaque caractère entré
            if (!newValue.matches("\\d*")) {
                tf.setText(newValue.replaceAll("[^\\d]", ""));
            }
    });}

    public void setMinutesField(TextField tf){
        tf.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                // Remplace tout caractère qui n'est pas un chiffre par une chaîne vide
                tf.setText(newValue.replaceAll("[^\\d]", ""));
            } else if (!newValue.isEmpty()) {
                // Vérifie si la valeur est supérieure à 59 et la corrige si nécessaire
                int heures = Integer.parseInt(newValue);
                if (heures > 59) {
                    tf.setText("59");
                }else {
                    // Supprime les zéros non significatifs
                    tf.setText(String.valueOf(heures));
                }
            }
        });

    }

    public void setHoursField(TextField tf){
        tf.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                // Remplace tout caractère qui n'est pas un chiffre par une chaîne vide
                tf.setText(newValue.replaceAll("[^\\d]", ""));
            } else if (!newValue.isEmpty()) {
                // Vérifie si la valeur est supérieure à 23 et la corrige si nécessaire
                int heures = Integer.parseInt(newValue);
                if (heures > 23) {
                    tf.setText("23");
                } else {
                    // Supprime les zéros non significatifs
                    tf.setText(String.valueOf(heures));
                }
            }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        for (Categorie categorie : Categorie.values()) {
            categories[categorie.ordinal()] = categorie.getName();
        }

        CategorieChoiceBox.getItems().addAll(categories);
        PrioriteChoiceBox.getItems().addAll(priorites);

        // Ajoute un écouteur d'événements pour le TextField
        setNumeric(NbPlannificationsTextField);
        setNumeric(DureeTextField);
        setNumeric(PeriodiciteTextField);

        setHoursField(DebutHeures);
        setMinutesField(DebutMinutes);
        setHoursField(FinHeures);
        setMinutesField(FinMinutes);


    }

}
