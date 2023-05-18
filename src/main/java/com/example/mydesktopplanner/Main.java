package com.example.mydesktopplanner;

import com.example.mydesktopplanner.Models.*;
import com.example.mydesktopplanner.Models.ExceptionsPackage.ExceptionCollisionHorairesCreneau;
import com.example.mydesktopplanner.Models.ExceptionsPackage.ExceptionDateInvalide;
import com.example.mydesktopplanner.Models.ExceptionsPackage.ExceptionDureeInvalide;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;


public class Main {

//    @Override
//    public void start(Stage stage) throws Exception {
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Authentification/AuthentificationScreen.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
//        stage.setTitle("Hello!");
//        stage.setScene(scene);
//        stage.show();
//
//    }

    public static void main(String[] args) throws ExceptionDureeInvalide, ExceptionDateInvalide, ExceptionCollisionHorairesCreneau {


        Utilisateur utilisateur = new Utilisateur("user");

        MyDesktopPlanner myDesktopPlanner = MyDesktopPlanner.initiateInstance(utilisateur);

        //creer 2 hours
        Creneau a = new Creneau(
                LocalDateTime.of(2023, 07, 19, 22, 13 ),
                LocalDateTime.of(2023, 07, 19, 23, 10)
        );

        //after crenau a with 2 hours another one with 2 hours
        Creneau b = new Creneau(
                LocalDateTime.of(2023, 07, 19, 23, 11 ),
                LocalDateTime.of(2023, 07, 19, 23, 51)
        );

        //after crenau a with 1 minute another one with 40 minutes
        Creneau c = new Creneau(
                LocalDateTime.of(2023, 07, 20, 00, 10 ),
                LocalDateTime.of(2023, 07, 20, 02, 10)
        );

        myDesktopPlanner.ajouterCreneau(a);
        myDesktopPlanner.ajouterCreneau(b);
        myDesktopPlanner.ajouterCreneau(c);

        try {
            myDesktopPlanner.ajouterTachePeriodique(
                    new CreneauPeriodique(
                            LocalDateTime.of(2023, 07, 19, 03, 10),
                            LocalDateTime.of(2023, 07, 19, 05, 10),
                            new TacheSimple("tache", Duration.ofHours(2), Priorite.HIGH, LocalDateTime.now().plusHours(5), Categorie.HEALTH, false)
                    ),
                    1,
                    4
            );
        } catch (ExceptionCollisionHorairesCreneau e){
            System.out.println("ERROR: " + e.getMessage());
        }


        System.out.println("BEFORE DELETION:   ------------------");
        myDesktopPlanner.afficherCreneaux();

//        myDesktopPlanner.supprimerTachesPeriodique(b);



        System.out.println("AFTER DELETION:   ------------------");
        myDesktopPlanner.afficherCreneaux();





//        Tache tache2 = new TacheSimple("tache2", Duration.ofHours(2), Priorite.HIGH, LocalDateTime.now().plusHours(3), Categorie.HEALTH, false);
//        Tache tache = new TacheSimple("tache", Duration.ofHours(2), Priorite.HIGH, LocalDateTime.now().plusHours(5), Categorie.HEALTH, false);
//
//        myDesktopPlanner.ajouterTache(tache);
//        myDesktopPlanner.ajouterTache(tache2);
//
//        myDesktopPlanner.afficherTaches();


    }
}
