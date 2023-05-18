package com.example.mydesktopplanner;

import com.example.mydesktopplanner.Models.*;
import com.example.mydesktopplanner.Models.ExceptionsPackage.ExceptionCollisionHorairesCreneau;
import com.example.mydesktopplanner.Models.ExceptionsPackage.ExceptionCollisionPeriode;
import com.example.mydesktopplanner.Models.ExceptionsPackage.ExceptionDateInvalide;
import com.example.mydesktopplanner.Models.ExceptionsPackage.ExceptionDureeInvalide;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.time.Duration;
import java.time.LocalDate;
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

    public static void main(String[] args) throws ExceptionDureeInvalide, ExceptionDateInvalide, ExceptionCollisionHorairesCreneau {


        Utilisateur utilisateur = new Utilisateur("user");

        MyDesktopPlanner myDesktopPlanner = MyDesktopPlanner.initiateInstance(utilisateur);

        Periode periode1 = new Periode(LocalDate.of(2024, 1, 1), LocalDate.of(2024, 1, 31));
        Periode periode2 = new Periode(LocalDate.of(2024, 2, 16), LocalDate.of(2024, 2, 29));
        try {
            myDesktopPlanner.ajouterPeriode(periode1);
            myDesktopPlanner.ajouterPeriode(periode2);
        } catch (ExceptionCollisionPeriode e) {
            throw new RuntimeException(e);
        }
        System.out.println("Fin de programme .");


        //creer 2 hours





//        Tache tache2 = new TacheSimple("tache2", Duration.ofHours(2), Priorite.HIGH, LocalDateTime.now().plusHours(3), Categorie.HEALTH, false);
//        Tache tache = new TacheSimple("tache", Duration.ofHours(2), Priorite.HIGH, LocalDateTime.now().plusHours(5), Categorie.HEALTH, false);
//
//        myDesktopPlanner.ajouterTache(tache);
//        myDesktopPlanner.ajouterTache(tache2);
//
//        myDesktopPlanner.afficherTaches();


    }
}
