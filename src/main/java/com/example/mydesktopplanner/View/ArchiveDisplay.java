package com.example.mydesktopplanner.View;

import com.example.mydesktopplanner.Main;
import com.example.mydesktopplanner.Models.Archive;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ArchiveDisplay extends Stage {


    @FXML
    private Text good;

    @FXML
    private Text verygood;

    @FXML
    private Text excellent;

    @FXML
    private Text tasks;
    @FXML
    private Text projects;

    @FXML
    private  Button back;

    public static Archive archive;


    public void changeButton(Button button,String file){
        button.setOnAction(event -> {
            try {
                // Charger le fichier FXML de la nouvelle scène
                FXMLLoader loader = new FXMLLoader(Main.class.getResource(file));

                Parent root = loader.load();


                // Créer une nouvelle scène avec la racine chargée
                Scene nouvelleScene = new Scene(root);

                // Obtenir la fenêtre principale (stage) actuelle
                Stage stageActuel = (Stage) button.getScene().getWindow();

                // Définir la nouvelle scène sur la fenêtre principale

                stageActuel.setScene(nouvelleScene);
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
    }

    public void initialize() {
        changeButton(back,"ArchiveSettings.fxml");
    int[] badges = archive.getBadges();
    good.setText(String.valueOf(badges[0]));
    verygood.setText(String.valueOf(badges[1]));
    excellent.setText(String.valueOf(badges[2]));
    tasks.setText(String.valueOf(archive.getNbTachesCompletees()));
    projects.setText(String.valueOf(archive.getNbProjetsCompletes()));


        back.setOnAction(event -> {
            try {
                // Charger le fichier FXML de la nouvelle scène
                FXMLLoader loader = new FXMLLoader(Main.class.getResource("ArchiveSettings.fxml"));
                Parent root = loader.load();

                // Créer une nouvelle fenêtre
                Stage nouvelleFenetre = new Stage();

                // Définir la scène avec la racine chargée
                Scene nouvelleScene = new Scene(root);
                nouvelleFenetre.setScene(nouvelleScene);

                // Afficher la nouvelle fenêtre
                nouvelleFenetre.show();

                // Fermer la fenêtre actuelle si nécessaire
                Stage stageActuel = (Stage) back.getScene().getWindow();
                stageActuel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    }



