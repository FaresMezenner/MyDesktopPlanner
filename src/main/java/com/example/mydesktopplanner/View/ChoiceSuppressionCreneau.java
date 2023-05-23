package com.example.mydesktopplanner.View;

import com.example.mydesktopplanner.Main;
import com.example.mydesktopplanner.Models.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.TreeMap;

public class ChoiceSuppressionCreneau extends Stage {


    @FXML
    private Button negative;

    @FXML
    private Button positive;

    public static Creneau creneau;


    MyDesktopPlanner myDesktopPlanner = MyDesktopPlanner.getInstance();




    public void initialize() {
        MyDesktopPlanner myDesktopPlanner = MyDesktopPlanner.getInstance();

        negative.setOnAction(event -> {
                MyDesktopPlanner.getInstance().dissocierTacheCreneau(creneau);
                MyDesktopPlanner.getInstance().suprimerCreneau(creneau);




    });

        positive.setOnAction(event -> {
                Tache tache;
                tache = creneau.getTache();

                MyDesktopPlanner.getInstance().dissocierTacheCreneau(creneau);
                MyDesktopPlanner.getInstance().supprimerTacheUnscheduled(tache);
                MyDesktopPlanner.getInstance().suprimerCreneau(creneau);



        });
    }






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


}
