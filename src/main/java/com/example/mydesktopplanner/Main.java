package com.example.mydesktopplanner;

import com.example.mydesktopplanner.Models.*;
import com.example.mydesktopplanner.Models.ExceptionsPackage.ExceptionDureeInvalide;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;


public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Authentification/AuthentificationScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) throws ExceptionDureeInvalide {


        Utilisateur utilisateur = new Utilisateur("user");

        MyDesktopPlanner myDesktopPlanner = MyDesktopPlanner.initiateInstance(utilisateur);

        Creneau c = new Creneau(
                LocalDateTime.now(), LocalDateTime.now().plusHours(2)
        );



        Tache tache2 = new TacheSimple("tache2", Duration.ofHours(2), Priorite.HIGH, LocalDateTime.now().plusHours(3), Categorie.HEALTH, false);
        Tache tache = new TacheSimple("tache", Duration.ofHours(2), Priorite.HIGH, LocalDateTime.now().plusHours(5), Categorie.HEALTH, false);

        myDesktopPlanner.ajouterTache(tache);
        myDesktopPlanner.ajouterTache(tache2);

        myDesktopPlanner.afficherTaches();


    }
}
