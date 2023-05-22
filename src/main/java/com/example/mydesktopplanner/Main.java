package com.example.mydesktopplanner;

import com.example.mydesktopplanner.Control.Authentification;
import com.example.mydesktopplanner.Models.*;
import com.example.mydesktopplanner.Models.ExceptionsPackage.*;
import com.example.mydesktopplanner.View.MainView;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.*;
import java.time.*;
import java.util.TreeMap;


public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        System.out.println(getClass().getResource("RightSidePanelTaches.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("RightSidePanelTaches.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

//        button.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent actionEvent) {
//
//                try {
//                    AnchorPane fxmlLoader =  FXMLLoader.load(Main.class.getResource("Authentification/CreateUserPopUp.fxml"));
//
//                    AnchorPane anchorPane = (AnchorPane) scene.lookup("#testA");
//                    anchorPane.getChildren().add(fxmlLoader);
//
//                    Button button1 = (Button) fxmlLoader.lookup("#oui");
//                    button1.setOnAction(new EventHandler<ActionEvent>() {
//                        @Override
//                        public void handle(ActionEvent actionEvent) {
//                            try {
//                                (new MainView()).show();
//                            } catch (IOException e) {
//                                throw new RuntimeException(e);
//                            }
//                        }
//                    });
//
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//                AnchorPane anchorPane = (AnchorPane) scene.lookup("#testA");
//
//            }
//        });
        stage.setTitle("My desktop planner");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) throws ExceptionDureeInvalide, ExceptionDateInvalide, ExceptionCollisionHorairesCreneau, IOException, ClassNotFoundException, ExceptionCollisionPeriode, ExceptionUserDoesNotExist {



//        Utilisateur utilisateur = UserManager.getInstance().Authentify("fares");
//
//       MyDesktopPlanner myDesktopPlanner = MyDesktopPlanner.initiateInstance(utilisateur);
//
//        utilisateur.setNbMinimalTachesParJour(1);
//
//
//
//        Creneau a = new Creneau(
//                LocalDateTime.of(2023, 05, 23, 12, 00 ),
//                LocalDateTime.of(2023, 05, 23, 13, 10)
//        );
//
//        Creneau b = new Creneau(
//                LocalDateTime.of(2023, 05, 24, 12, 00 ),
//                LocalDateTime.of(2023, 05, 24, 13, 51)
//        );
//
//        Creneau c = new Creneau(
//                LocalDateTime.of(2023, 05, 31, 12, 00 ),
//                LocalDateTime.of(2023, 05, 31, 13, 51)
//        );
//
//        myDesktopPlanner.ajouterCreneau(a);
//        myDesktopPlanner.ajouterCreneau(b);
//        myDesktopPlanner.ajouterCreneau(c);
//
//
//        Periode periode = new Periode(
//                LocalDate.of(2023, 05, 23),
//                LocalDate.of(2023, 05, 23),
//                "periode 1"
//        );
//        myDesktopPlanner.ajouterPeriode(periode);
//
//        TacheSimple tacha = new TacheSimple("tache 1", Duration.ofMinutes(30), Priorite.HIGH, LocalDate.of(2023, 05, 23), Categorie.HEALTH, false);
//
//
//        myDesktopPlanner.ajouterTache(tacha);
//
//        Projet projeta = new Projet("projet 1", "testing", new TreeMap<>());
//
//        myDesktopPlanner.ajouterProjet(projeta);

        launch();
//        myDesktopPlanner.afficherCreneaux();

//        Creneau d = new Creneau(
//                LocalDateTime.of(2023, 07, 4, 12, 11 ),
//                LocalDateTime.of(2023, 07, 4, 13, 51)
//        );
//
//        Creneau e = new Creneau(
//                LocalDateTime.of(2023, 07, 5, 12, 10 ),
//                LocalDateTime.of(2023, 07, 5, 13, 10)
//        );
//
//        Creneau f = new Creneau(
//                LocalDateTime.of(2023, 8, 5, 12, 10 ),
//                LocalDateTime.of(2023, 8, 5, 13, 10)
//        );

//
//        myDesktopPlanner.ajouterCreneau(a);
//        myDesktopPlanner.ajouterCreneau(b);
//        myDesktopPlanner.ajouterCreneau(c);
//        myDesktopPlanner.ajouterCreneau(d);
//        myDesktopPlanner.ajouterCreneau(e);
//        myDesktopPlanner.ajouterCreneau(f);
//
//
//
//
//
//
//        Tache tacheSimple1 = new TacheSimple("Revision 1",Duration.ofMinutes(30),Priorite.HIGH,LocalDateTime.of(2024, 07, 20, 02, 10),Categorie.HEALTH,false);
//        Tache tacheSimple2 = new TacheSimple("Revision 1",Duration.ofMinutes(30),Priorite.HIGH,LocalDateTime.of(2024, 07, 20, 02, 10),Categorie.HEALTH,false);
//        Tache tacheSimple3 = new TacheSimple("Revision 1",Duration.ofMinutes(30),Priorite.HIGH,LocalDateTime.of(2024, 07, 20, 02, 10),Categorie.HEALTH,false);
//        Tache tacheSimple4 = new TacheSimple("Revision 1",Duration.ofMinutes(30),Priorite.HIGH,LocalDateTime.of(2024, 07, 20, 02, 10),Categorie.HEALTH,false);
//        Tache tacheSimple5 = new TacheSimple("Revision 1",Duration.ofMinutes(30),Priorite.HIGH,LocalDateTime.of(2024, 07, 20, 02, 10),Categorie.HEALTH,false);
//        Tache tacheSimple6 = new TacheSimple("Revision 1",Duration.ofMinutes(30),Priorite.HIGH,LocalDateTime.of(2024, 07, 20, 02, 10),Categorie.HEALTH,false);
//
//
//        myDesktopPlanner.ajouterTache(tacheSimple1);
//        myDesktopPlanner.ajouterTache(tacheSimple2);
//        myDesktopPlanner.ajouterTache(tacheSimple3);
//        myDesktopPlanner.ajouterTache(tacheSimple4);
//        myDesktopPlanner.ajouterTache(tacheSimple5);
//        myDesktopPlanner.ajouterTache(tacheSimple6);
//
//
//
//        try {
//        myDesktopPlanner.affecterTacheCreneau(a, tacheSimple1);
//        myDesktopPlanner.affecterTacheCreneau(b, tacheSimple2);
//        myDesktopPlanner.affecterTacheCreneau(c, tacheSimple3);
//        myDesktopPlanner.affecterTacheCreneau(d, tacheSimple4);
//        myDesktopPlanner.affecterTacheCreneau(e, tacheSimple5);
//        myDesktopPlanner.affecterTacheCreneau(f, tacheSimple6);
//        } catch (ExceptionCreneauNonLibre ex) {
//            throw new RuntimeException(ex);
//        }
//        myDesktopPlanner.changerEtatTache(a,Etat.COMPLETED);
//        myDesktopPlanner.attribuerFelicitationsBadges(a);
//
//        myDesktopPlanner.changerEtatTache(b,Etat.COMPLETED);
//        myDesktopPlanner.attribuerFelicitationsBadges(b);
//
//        myDesktopPlanner.changerEtatTache(c,Etat.COMPLETED);
//        myDesktopPlanner.attribuerFelicitationsBadges(c);
//
//        myDesktopPlanner.changerEtatTache(d,Etat.COMPLETED);
//        myDesktopPlanner.attribuerFelicitationsBadges(d);
//
//        myDesktopPlanner.changerEtatTache(e,Etat.COMPLETED);
//        myDesktopPlanner.attribuerFelicitationsBadges(e);
//
//        myDesktopPlanner.changerEtatTache(f,Etat.COMPLETED);
//        myDesktopPlanner.attribuerFelicitationsBadges(f);
//
//
//
//       myDesktopPlanner.updateEtatTaches();
//
//        System.out.println(utilisateur.getLastUpdateTachesTime());


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
//        Object a = new Object();
//        Object b = new Object();
//        Object c = new Object();
//
//        ArrayList<Object> testList = new ArrayList<>();
//        testList.add(a);
//        testList.add(b);
//        testList.add(c);
//
//        for (Object o : testList) {
//            System.out.println(o);
//        }
//
//        System.out.println("now testing the linked list");
//
//        LinkedList<Object> testLinkedList = new LinkedList<>(testList);
//        Collections.shuffle(testLinkedList);
//        for (Object o : testLinkedList) {
//            System.out.println(o);
//        }

        launch();
**/

//        FileOutputStream fOut = new FileOutputStream("TEST AUTH");
//        ObjectOutputStream out = new ObjectOutputStream(fOut);
//        out.writeObject("TEST1");
//        fOut.flush();
//        out.flush();
//        fOut.close();
//        out.close();
//
//
//        fOut = new FileOutputStream("TEST AUTH");
//        out = new ObjectOutputStream(fOut);
//        out.writeObject("TEST2");
//        fOut.flush();
//        out.flush();
//        fOut.close();
//        out.close();
//
//        FileInputStream fIn = new FileInputStream("TEST AUTH");
//        ObjectInputStream in = new ObjectInputStream(fIn);
//        System.out.println((String) in.readObject());

//        launch();




    }
}
