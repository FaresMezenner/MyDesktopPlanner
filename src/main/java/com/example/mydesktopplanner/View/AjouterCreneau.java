package com.example.mydesktopplanner.View;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AjouterCreneau implements Initializable {

    @FXML
    TextField DebutMinutes;

    @FXML
    TextField DebutHeures;

    @FXML
    TextField FinMinutes;

    @FXML
    TextField FinHeures;



    public void setNumeric(TextField tf){
        tf.textProperty().addListener((observable, oldValue, newValue) -> {
            // Vérifie chaque caractère entré
            if (!newValue.matches("\\d*")) {
                tf.setText(newValue.replaceAll("[^\\d]", ""));
            }
    });
    }

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
                }
            }
        });
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    setHoursField(DebutHeures);
    setHoursField(FinHeures);
    setMinutesField(DebutMinutes);
    setMinutesField(FinMinutes);

        // Ajoute un écouteur d'événements pour le TextField

    }

}
