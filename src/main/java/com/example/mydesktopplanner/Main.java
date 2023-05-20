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

        utilisateur.setNbMinimalTachesParJour(1);



        Creneau a = new Creneau(
                LocalDateTime.of(2023, 07, 1, 12, 00 ),
                LocalDateTime.of(2023, 07, 1, 13, 10)
        );

        Creneau b = new Creneau(
                LocalDateTime.of(2023, 07, 2, 12, 00 ),
                LocalDateTime.of(2023, 07, 2, 13, 51)
        );

        Creneau c = new Creneau(
                LocalDateTime.of(2023, 07, 3, 12, 10 ),
                LocalDateTime.of(2023, 07, 3, 13, 10)
        );
        Creneau d = new Creneau(
                LocalDateTime.of(2023, 07, 4, 12, 11 ),
                LocalDateTime.of(2023, 07, 4, 13, 51)
        );

        Creneau e = new Creneau(
                LocalDateTime.of(2023, 07, 5, 12, 10 ),
                LocalDateTime.of(2023, 07, 5, 13, 10)
        );

        Creneau f = new Creneau(
                LocalDateTime.of(2023, 8, 5, 12, 10 ),
                LocalDateTime.of(2023, 8, 5, 13, 10)
        );


        myDesktopPlanner.ajouterCreneau(a);
        myDesktopPlanner.ajouterCreneau(b);
        myDesktopPlanner.ajouterCreneau(c);
        myDesktopPlanner.ajouterCreneau(d);
        myDesktopPlanner.ajouterCreneau(e);
        myDesktopPlanner.ajouterCreneau(f);






        Tache tacheSimple1 = new TacheSimple("Revision 1",Duration.ofMinutes(30),Priorite.HIGH,LocalDateTime.of(2024, 07, 20, 02, 10),Categorie.HEALTH,false);
        Tache tacheSimple2 = new TacheSimple("Revision 1",Duration.ofMinutes(30),Priorite.HIGH,LocalDateTime.of(2024, 07, 20, 02, 10),Categorie.HEALTH,false);
        Tache tacheSimple3 = new TacheSimple("Revision 1",Duration.ofMinutes(30),Priorite.HIGH,LocalDateTime.of(2024, 07, 20, 02, 10),Categorie.HEALTH,false);
        Tache tacheSimple4 = new TacheSimple("Revision 1",Duration.ofMinutes(30),Priorite.HIGH,LocalDateTime.of(2024, 07, 20, 02, 10),Categorie.HEALTH,false);
        Tache tacheSimple5 = new TacheSimple("Revision 1",Duration.ofMinutes(30),Priorite.HIGH,LocalDateTime.of(2024, 07, 20, 02, 10),Categorie.HEALTH,false);
        Tache tacheSimple6 = new TacheSimple("Revision 1",Duration.ofMinutes(30),Priorite.HIGH,LocalDateTime.of(2024, 07, 20, 02, 10),Categorie.HEALTH,false);


        myDesktopPlanner.ajouterTache(tacheSimple1);
        myDesktopPlanner.ajouterTache(tacheSimple2);
        myDesktopPlanner.ajouterTache(tacheSimple3);
        myDesktopPlanner.ajouterTache(tacheSimple4);
        myDesktopPlanner.ajouterTache(tacheSimple5);
        myDesktopPlanner.ajouterTache(tacheSimple6);


        myDesktopPlanner.affecterTacheCreneau(tacheSimple1,a);
        myDesktopPlanner.affecterTacheCreneau(tacheSimple2,b);
        myDesktopPlanner.affecterTacheCreneau(tacheSimple3,c);
        myDesktopPlanner.affecterTacheCreneau(tacheSimple4,d);
        myDesktopPlanner.affecterTacheCreneau(tacheSimple5,e);
        myDesktopPlanner.affecterTacheCreneau(tacheSimple6,f);

        myDesktopPlanner.changerEtatTache(a,Etat.COMPLETED);
        myDesktopPlanner.attribuerFelicitationsBadges(a);

        myDesktopPlanner.changerEtatTache(b,Etat.COMPLETED);
        myDesktopPlanner.attribuerFelicitationsBadges(b);

        myDesktopPlanner.changerEtatTache(c,Etat.COMPLETED);
        myDesktopPlanner.attribuerFelicitationsBadges(c);

        myDesktopPlanner.changerEtatTache(d,Etat.COMPLETED);
        myDesktopPlanner.attribuerFelicitationsBadges(d);

        myDesktopPlanner.changerEtatTache(e,Etat.COMPLETED);
        myDesktopPlanner.attribuerFelicitationsBadges(e);

        myDesktopPlanner.changerEtatTache(f,Etat.COMPLETED);
        myDesktopPlanner.attribuerFelicitationsBadges(f);




    }
}
