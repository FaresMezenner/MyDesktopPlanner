package com.example.mydesktopplanner.View;

import com.example.mydesktopplanner.Models.Creneau;
import com.example.mydesktopplanner.Models.ExceptionsPackage.ExceptionCollisionHorairesCreneau;
import com.example.mydesktopplanner.Models.ExceptionsPackage.ExceptionDateInvalide;
import com.example.mydesktopplanner.Models.ExceptionsPackage.ExceptionDureeInvalide;
import com.example.mydesktopplanner.Models.MyDesktopPlanner;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.LocalTime;
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

    @FXML
    DatePicker date;



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
    setHoursField(DebutHeures);
    setHoursField(FinHeures);
    setMinutesField(DebutMinutes);
    setMinutesField(FinMinutes);

        // Ajoute un écouteur d'événements pour le TextField

    }

    private boolean checkInput() {
        return !(date.getValue() == null || DebutHeures.getText().isEmpty() || DebutMinutes.getText().isEmpty() || FinHeures.getText().isEmpty() || FinMinutes.getText().isEmpty());
    }

    public void ajouter() throws IOException {
        if (checkInput()) {

            Creneau creneau;
            try {
                creneau = new Creneau(
                        LocalDateTime.of(date.getValue(), LocalTime.of(Integer.parseInt(DebutHeures.getText()), Integer.parseInt(DebutMinutes.getText()))),
                        LocalDateTime.of(date.getValue(), LocalTime.of(Integer.parseInt(FinHeures.getText()), Integer.parseInt(FinMinutes.getText())))
                );
            } catch (ExceptionDureeInvalide e) {
                try {
                    (new ErrorPopUpView(null, e.getMessage())).show();
                    return;
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }

            try {
                MyDesktopPlanner.getInstance().ajouterCreneau(creneau);
            } catch (ExceptionDateInvalide e) {
                ErrorPopUpView.show(null, e.getMessage());
            } catch (ExceptionCollisionHorairesCreneau e) {
                ErrorPopUpView.show(null, e.getMessage());
            }

            MainView.getInstance().update();


        } else {

            try {
                (new ErrorPopUpView(null, "Veuillez remplir tous tous les champs")).show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }



}
