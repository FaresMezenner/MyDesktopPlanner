package com.example.mydesktopplanner.View;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ParametresProfil implements Initializable {


    @FXML
    TextField nombreFelicitations;

    @FXML
    TextField duree;

    public void setNumeric(TextField tf){
        tf.textProperty().addListener((observable, oldValue, newValue) -> {
            // Vérifie chaque caractère entré
            if (!newValue.matches("\\d*")) {
                tf.setText(newValue.replaceAll("[^\\d]", ""));
            }
    });}




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Ajoute un écouteur d'événements pour le TextField
        setNumeric(duree);
        setNumeric(nombreFelicitations);
    }

}
