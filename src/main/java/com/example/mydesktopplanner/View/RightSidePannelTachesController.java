package com.example.mydesktopplanner.View;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

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

    private String[] categories = {"Studies", "Work", "Hobby","Sport","Health","Other"};
    private String[] priorites = {"Low", "Medium", "High"};
    private String[] Etat = {"Not realized", "Completed", "In progress","Canceled","Delayed","Unscheduled"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CategorieChoiceBox.getItems().addAll(categories);
        PrioriteChoiceBox.getItems().addAll(priorites);
        EtatChoiceBox.getItems().addAll(Etat);
    }
}
