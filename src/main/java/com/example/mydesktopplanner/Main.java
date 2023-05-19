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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;


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

        Creneau a = new Creneau(
                LocalDateTime.of(2023, 07, 19, 22, 00 ),
                LocalDateTime.of(2023, 07, 19, 23, 10)
        );

        Creneau b = new Creneau(
                LocalDateTime.of(2023, 07, 19, 23, 11 ),
                LocalDateTime.of(2023, 07, 19, 23, 51)
        );

        Creneau c = new Creneau(
                LocalDateTime.of(2023, 07, 20, 00, 10 ),
                LocalDateTime.of(2023, 07, 20, 02, 10)
        );

        myDesktopPlanner.ajouterCreneau(a);
        myDesktopPlanner.ajouterCreneau(b);
        myDesktopPlanner.ajouterCreneau(c);

//        myDesktopPlanner.afficherCreneaux();

        CreneauPeriodique creneauPeriodique = null;

        TacheSimple tacheSimple1 = new TacheSimple(
                "Walk",
                Duration.ofMinutes(21),
                Priorite.HIGH,LocalDateTime.of(2023, 07, 19, 22, 00 ),
                Categorie.SPORT,
                false
        );
        TacheSimple tacheSimple2 = new TacheSimple("Revision OPTOE",Duration.ofMinutes(60),Priorite.HIGH,LocalDateTime.of(2023, 07, 19, 22, 00 ),Categorie.SPORT,false);


        TacheSimple tacheSimple3 = new TacheSimple(
                "Dying",
                Duration.ofMinutes(21),
                Priorite.MEDIUM,LocalDateTime.of(2023, 07, 19, 22, 00 ),
                Categorie.SPORT,
                false
        );
        TacheSimple tacheSimple4 = new TacheSimple(
                "Dying2",
                Duration.ofMinutes(21),
                Priorite.MEDIUM,LocalDateTime.of(2023, 07, 19, 22, 00 ),
                Categorie.SPORT,
                false
        );
        TacheSimple tacheSimple5 = new TacheSimple(
                "Dying3",
                Duration.ofMinutes(21),
                Priorite.MEDIUM,LocalDateTime.of(2023, 07, 19, 22, 00 ),
                Categorie.SPORT,
                false
        );

        TacheDecomposable tacheDecomposable = new TacheDecomposable("Walk",Duration.ofMinutes(80),Priorite.HIGH,LocalDateTime.of(2023, 07, 19, 22, 00 ),Categorie.SPORT,null,1);
        TacheDecomposable tacheDecomposable2 = new TacheDecomposable("Révision ANAL",Duration.ofMinutes(30),Priorite.HIGH,LocalDateTime.of(2023, 07, 19, 18, 13 ),Categorie.SPORT,null,1);
        myDesktopPlanner.ajouterTache(tacheDecomposable);
        myDesktopPlanner.ajouterTache(tacheDecomposable2);
        myDesktopPlanner.ajouterTache(tacheSimple1);
        myDesktopPlanner.ajouterTache(tacheSimple2);
        myDesktopPlanner.ajouterTache(tacheSimple3);
        myDesktopPlanner.ajouterTache(tacheSimple4);
        myDesktopPlanner.ajouterTache(tacheSimple5);


//        System.out.println("\n\n -------- returned deco ------------");
//        myDesktopPlanner.afficherHashmap(myDesktopPlanner.affecterTacheCreneau(tacheDecomposable2,a));


//        myDesktopPlanner.afficherCreneaux();

        myDesktopPlanner.plannifierTachesPeriode(myDesktopPlanner.getUnscheduledTaches(),
                new Periode(LocalDate.of(2023, 07, 19 ),LocalDate.of(2023, 07, 20))
                );


//        myDesktopPlanner.plannifierTachesPeriode(new LinkedList<>(List.of(new Tache[]{tacheDecomposable})),
//                new Periode(LocalDate.of(2023, 07, 19 ),LocalDate.of(2023, 07, 20))
//        );
        myDesktopPlanner.afficherCreneaux();

//        myDesktopPlanner.afficherTaches();




    }
}
