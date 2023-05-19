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
                LocalDateTime.of(2023, 07, 19, 22, 00 ),
                LocalDateTime.of(2023, 07, 19, 23, 00)
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

        CreneauPeriodique creneauPeriodique = null;

        TacheSimple tacheSimple1 = new TacheSimple("Walk",Duration.ofMinutes(20),Priorite.HIGH,LocalDateTime.of(2023, 07, 19, 22, 00 ),Categorie.SPORT,false);
        TacheSimple tacheSimple2 = new TacheSimple("Revision OPTOE",Duration.ofMinutes(60),Priorite.HIGH,LocalDateTime.of(2023, 07, 19, 22, 00 ),Categorie.SPORT,false);


        TacheDecomposable tacheDecomposable = new TacheDecomposable("Walk",Duration.ofMinutes(20),Priorite.HIGH,LocalDateTime.of(2024, 07, 19, 22, 00 ),Categorie.SPORT,null,1);
        TacheDecomposable tacheDecomposable2 = new TacheDecomposable("Révision ANAL",Duration.ofMinutes(120),Priorite.HIGH,LocalDateTime.of(2024, 07, 19, 18, 13 ),Categorie.SPORT,null,1);
        myDesktopPlanner.ajouterTache(tacheDecomposable);
        myDesktopPlanner.ajouterTache(tacheDecomposable2);
        myDesktopPlanner.ajouterTache(tacheSimple1);
        myDesktopPlanner.ajouterTache(tacheSimple2);


        myDesktopPlanner.afficherHashmap(myDesktopPlanner.affecterTacheCreneau(tacheDecomposable2,a));
    }
}
