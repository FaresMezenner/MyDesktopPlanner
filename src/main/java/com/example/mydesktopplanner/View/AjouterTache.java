package com.example.mydesktopplanner.View;

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



    private String[] categories = {"Studies", "Work", "Hobby","Sport","Health","Other"};
    private String[] priorites = {"Low", "Medium", "High"};
    private String[] Etat = {"Not realized", "Completed", "In progress","Canceled","Delayed","Unscheduled"};

    public void setNumeric(TextField tf){
        tf.textProperty().addListener((observable, oldValue, newValue) -> {
            // Vérifie chaque caractère entré
            if (!newValue.matches("\\d*")) {
                tf.setText(newValue.replaceAll("[^\\d]", ""));
            }
    });}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CategorieChoiceBox.getItems().addAll(categories);
        PrioriteChoiceBox.getItems().addAll(priorites);

        // Ajoute un écouteur d'événements pour le TextField
        setNumeric(NbPlannificationsTextField);
        setNumeric(DureeTextField);
        setNumeric(PeriodiciteTextField);
    }

}
