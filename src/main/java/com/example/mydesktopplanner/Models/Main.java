package com.example.mydesktopplanner.Models;


import com.example.mydesktopplanner.Models.ExceptionsPackage.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        System.out.println(getClass().getResource("hello-view.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
        // Le but de ce programme est de créer un système de gestion de projets , qui permets de gerer son temps et de suivre son avancement
//        try {
//        MyDesktopPlanner myDesktopPlanner = new MyDesktopPlanner();
//        myDesktopPlanner.ajouterUtilisateur("Sofiane");
//        myDesktopPlanner.ajouterUtilisateur("Fares");
//
//        myDesktopPlanner.supprimerUtilisateur("Sofiane");
//
//        myDesktopPlanner.ajouterUtilisateur("Sofiane");
//
//
//        myDesktopPlanner.afficherUtilisateurs();
//
//        Utilisateur sofiane = myDesktopPlanner.authentifierUtilisateur("Sofiane");
//
//        Calendrier calendrier = sofiane.getCalendrier();
//
//        // Exemple d'ajout de creneau
//
//            calendrier.ajouterCreneau(LocalDate.of(2023, 12, 1), LocalTime.of(8, 30),LocalTime.of(10,25));
//            ArrayList<Creneau> creneaux =  calendrier.getCreneauxJour(LocalDate.of(2023,12,1));
//
//            TacheDecomposable tacheDec = new TacheDecomposable("Finir le TP POO", Duration.of(2, ChronoUnit.HOURS), Priorite.HIGH, LocalDate.of(2023, 12, 1), Categorie.STUDIES);
//            TacheSimple tacheSim = new TacheSimple("Finir le TP POO", Duration.of(1, ChronoUnit.HOURS), Priorite.HIGH, LocalDate.of(2023, 12, 1), Categorie.STUDIES);
//
//            Creneau monCreneau = creneaux.get(0);
//            Tache nouvelle_tache = monCreneau.ajouterTache(tacheSim);
//
//            System.out.println(calendrier.getCreneauxJour(LocalDate.of(2023,12,1)));
//            calendrier.getCreneauxJour(LocalDate.of(2023,12,1)).get(1).afficher();
//
//
//
//        } catch (ExceptionCollisionHorairesCreneau e) {
//            throw new RuntimeException(e);
//        } catch (ExceptionDureeTacheIncompatible e) {
//            throw new RuntimeException(e);
//        } catch (ExceptionCreneauOccupe e) {
//            throw new RuntimeException(e);
//        } catch (ExceptionDureeInvalide e) {
//            throw new RuntimeException(e);
//        } catch (ExceptionDateInvalide e) {
//            throw new RuntimeException(e);
//        }catch(ExceptionSaisieInvalide e){
//            throw new RuntimeException(e);
//        } catch(ExceptionUserDoesNotExist e){
//            throw new RuntimeException(e);
//        }


    }
}