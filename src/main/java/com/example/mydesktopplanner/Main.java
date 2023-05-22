package com.example.mydesktopplanner;

import com.example.mydesktopplanner.Models.*;
import com.example.mydesktopplanner.Models.ExceptionsPackage.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;


public class Main extends Application{

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Authentification/AuthentificationScreen.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) throws ExceptionDureeInvalide, ExceptionDateInvalide, ExceptionCollisionHorairesCreneau, IOException, ClassNotFoundException {

        //launch();

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
                LocalDateTime.of(2023, 7, 6, 12, 10 ),
                LocalDateTime.of(2023, 7, 6, 13, 10)
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
        Tache tacheSimple7 = new TacheSimple("Revision 1",Duration.ofMinutes(130),Priorite.HIGH,LocalDateTime.of(2024, 07, 20, 02, 10),Categorie.HEALTH,false);


        myDesktopPlanner.ajouterTache(tacheSimple1);
        myDesktopPlanner.ajouterTache(tacheSimple2);
        myDesktopPlanner.ajouterTache(tacheSimple3);
        myDesktopPlanner.ajouterTache(tacheSimple4);
        myDesktopPlanner.ajouterTache(tacheSimple5);
        myDesktopPlanner.ajouterTache(tacheSimple6);
        myDesktopPlanner.ajouterTache(tacheSimple7);



        try {
        myDesktopPlanner.affecterTacheCreneau(a, tacheSimple1);
        myDesktopPlanner.affecterTacheCreneau(b, tacheSimple2);
        myDesktopPlanner.affecterTacheCreneau(e, tacheSimple5);
        myDesktopPlanner.affecterTacheCreneau(f, tacheSimple6);
        } catch (ExceptionCreneauNonLibre ex) {
            throw new RuntimeException(ex);
        }

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




        // Affichage des creneaux et de leur contenu

        ArrayList<Creneau> creneaux= myDesktopPlanner.getCreneauxIntervalle(LocalDate.of(2023, 07, 1 ),LocalDate.of(2023, 07, 6));

        for (Creneau creneau : creneaux) {
            if (creneau.getTache() != null) {
                System.out.println(creneau.getTache().getNom());
            }
        }




//        TacheSimple test = new TacheSimple("test", Duration.ofMinutes(30), Priorite.HIGH, LocalDateTime.of(2021, 7, 1, 12, 00), Categorie.HEALTH, false);
//        myDesktopPlanner.ajouterTache(test);
//
//        Projet projet = new Projet("projet", "test projet", new ArrayList<Tache>());
//        myDesktopPlanner.ajouterProjet(projet);
//        myDesktopPlanner.ajouterTacheProjet(projet, test);
//
//
//        System.out.println(test);
//        System.out.println(myDesktopPlanner.getUnscheduledTaches().get(0));
//        System.out.println(myDesktopPlanner.getUtilisateur().getProjets().get(0).getTaches().get(0));
//
//        System.out.println("now testing the save");
//        //now testing the save
//
//        FileOutputStream fOut = new FileOutputStream("test");
//        ObjectOutputStream out = new ObjectOutputStream(fOut);
//        out.writeObject(myDesktopPlanner.getUtilisateur());
//
//        out.flush();
//        out.close();
//
//
//        FileInputStream fIn = new FileInputStream("test");
//        ObjectInputStream in = new ObjectInputStream(fIn);
//
//        Utilisateur utilisateurTest = (Utilisateur) in.readObject();
//
//        System.out.println(utilisateurTest.getUnscheduledTaches().get(0));
//        System.out.println(utilisateurTest.getProjets().get(0).getTaches().get(0));

/**
        Object a = new Object();
        Object b = new Object();
        Object c = new Object();

        ArrayList<Object> testList = new ArrayList<>();
        testList.add(a);
        testList.add(b);
        testList.add(c);

        for (Object o : testList) {
            System.out.println(o);
        }

        System.out.println("now testing the linked list");

        LinkedList<Object> testLinkedList = new LinkedList<>(testList);
        Collections.shuffle(testLinkedList);
        for (Object o : testLinkedList) {
            System.out.println(o);
        }
**/


        for (Creneau creneau : creneaux) {
            System.out.println(creneau.getDebut());
            if (creneau.getTache() != null) {
                System.out.println(creneau.getTache().getNom());
            }
        }


    }
}
