package com.example.mydesktopplanner.View;

import com.example.mydesktopplanner.Main;
import com.example.mydesktopplanner.Models.MyDesktopPlanner;
import com.example.mydesktopplanner.Models.Utilisateur;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.ResourceBundle;

public class ParametresProfil implements Initializable {


    @FXML
    TextField nombreFelicitations;

    @FXML
    TextField duree;

    @FXML
    Button ok;

    public void setNumeric(TextField tf) {
        tf.textProperty().addListener((observable, oldValue, newValue) -> {
            // Vérifie chaque caractère entré
            if (!newValue.matches("\\d*")) {
                tf.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }


    MyDesktopPlanner myDesktopPlanner = MyDesktopPlanner.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Ajoute un écouteur d'événements pour le TextField
        setNumeric(duree);
        setNumeric(nombreFelicitations);

        duree.setText(String.valueOf(myDesktopPlanner.getUtilisateur().getTempsMinCreneau()));

        nombreFelicitations.setText(String.valueOf(myDesktopPlanner.getUtilisateur().getNbMinimalTachesParJourBadgeGood()));
        ok.setOnAction(event -> {
            Utilisateur utilisateur = myDesktopPlanner.getUtilisateur();
            utilisateur.setTempsMinCreneau(Duration.ofMinutes(Integer.parseInt(duree.getText())));
            utilisateur.setNbMinimalTachesParJourBadgeGood(Integer.parseInt(nombreFelicitations.getText()));
            // Obtenir la fenêtre principale (stage) actuelle
            Stage stageActuel = (Stage) ok.getScene().getWindow();

            // Définir la nouvelle scène sur la fenêtre principale

            retour_func(stageActuel);

        });

    }


    public void retour_func(Stage stage) {
        ProfilePopUpView profilePopUpView = null;
        try {
            profilePopUpView = new ProfilePopUpView();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = profilePopUpView.getScene();
        stage.setTitle("My desktop planner");
        stage.setScene(scene);
    }
}