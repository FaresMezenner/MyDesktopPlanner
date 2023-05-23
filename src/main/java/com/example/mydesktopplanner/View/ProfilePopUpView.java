package com.example.mydesktopplanner.View;

import com.example.mydesktopplanner.Main;
import com.example.mydesktopplanner.Models.*;
import com.example.mydesktopplanner.Models.ExceptionsPackage.ExceptionPeriodeInexistante;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.util.TreeMap;

public class ProfilePopUpView extends Stage {


    Text rendementJournalier;
    Text rendementPeriode;
    Text encouragement;
    Text studies;
    Text work;
    Text hobbies;
    Text sport;
    Text other;
    Text day;

    public ProfilePopUpView() throws IOException {
        setScene(new Scene(FXMLLoader.load(com.example.mydesktopplanner.Main.class.getResource("ProfilePopUp.fxml"))));

        setValues();
        setControllers();
    }

    private void setControllers() {
        ((Button)getScene().lookup("#ok")).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                close();
            }
        });

        Button archiverBtn = (Button) getScene().lookup("#archiver");

        archiverBtn.setOnAction(event -> {
                try {
                    // Charger le fichier FXML de la nouvelle scène
                    FXMLLoader loader = new FXMLLoader(Main.class.getResource("ArchiveSettings.fxml"));

                    Parent root = loader.load();


                    // Créer une nouvelle scène avec la racine chargée
                    Scene nouvelleScene = new Scene(root);

                    // Obtenir la fenêtre principale (stage) actuelle
                    Stage stageActuel = (Stage) archiverBtn.getScene().getWindow();

                    // Définir la nouvelle scène sur la fenêtre principale

                    stageActuel.setScene(nouvelleScene);
                } catch (IOException e) {
                    e.printStackTrace();
                }

        });

        chageButton((Button) getScene().lookup("#settings"),"ParametresProfil.fxml");
    }

    public void chageButton(Button button,String file){
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

    private void setValues() {
        ((Text)getScene().lookup("#user")).setText(MyDesktopPlanner.getInstance().getUtilisateur().getPseudo());
        ((Text)getScene().lookup("#good")).setText(
                String.valueOf(MyDesktopPlanner.getInstance().getUtilisateur().getBadges()[Badge.GOOD.ordinal()])
        );
        ((Text)getScene().lookup("#verygood")).setText(
                String.valueOf(MyDesktopPlanner.getInstance().getUtilisateur().getBadges()[Badge.VERYGOOD.ordinal()])
        );
        ((Text)getScene().lookup("#excellent")).setText(
                String.valueOf(MyDesktopPlanner.getInstance().getUtilisateur().getBadges()[Badge.EXCELLENT.ordinal()])
        );

        MyDesktopPlanner myDesktopPlanner = MyDesktopPlanner.getInstance();
        int[]rendement = myDesktopPlanner.getUtilisateur().getRendementJournalierArray();

        if (rendement[1] == 0){
            ((Text)getScene().lookup("#rendementJournalier")).setText("0");}
        else {
            ((Text) getScene().lookup("#rendementJournalier")).setText(Float.toString((float) rendement[0] / rendement[1]));
        }
        Duration[] tempsCategorie = myDesktopPlanner.getUtilisateur().getTempsCategories();

        ((Text)getScene().lookup("#studies")).setText(String.valueOf(tempsCategorie[0].toMinutes()));
        ((Text)getScene().lookup("#work")).setText(String.valueOf(tempsCategorie[1].toMinutes()));
        ((Text)getScene().lookup("#hobbies")).setText(String.valueOf(tempsCategorie[2].toMinutes()));
        ((Text)getScene().lookup("#sport")).setText(String.valueOf(tempsCategorie[3].toMinutes()));
        ((Text)getScene().lookup("#others")).setText(String.valueOf(tempsCategorie[4].toMinutes()));
        ((Text)getScene().lookup("#health")).setText(String.valueOf(tempsCategorie[5].toMinutes()));
        ((Text)getScene().lookup("#encouragement")).setText(String.valueOf(myDesktopPlanner.getUtilisateur().getNbEncouragements()));

        Jour jourRent = myDesktopPlanner.getUtilisateur().getJourRentable();
        if (jourRent != null){
            ((Text)getScene().lookup("#day")).setText(jourRent.getDate().toString());
        }else{
            ((Text)getScene().lookup("#day")).setText("/");
        }

        // Rechercher la periode actuelle :

        TreeMap<LocalDate,Periode> periodes= myDesktopPlanner.getPeriodes();
        Periode periodeActuelle = null;

        for (Periode periode : periodes.values()) {
            if (periode.getDebut().isBefore(LocalDate.now()) && periode.getFin().isAfter(LocalDate.now())){
                periodeActuelle = periode;
            }
        }
        float rendement_periode = 0;

        try {
             rendement_periode = myDesktopPlanner.getRendementPeriode(periodeActuelle);
            ((Text)getScene().lookup("#rendementPeriode")).setText(String.valueOf(rendement_periode));
        } catch (ExceptionPeriodeInexistante e) {
            ((Text)getScene().lookup("#rendementPeriode")).setText("/");
        }


    }

    public void initialize() {


}
}
