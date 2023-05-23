package com.example.mydesktopplanner.View;

import com.example.mydesktopplanner.Main;
import com.example.mydesktopplanner.Models.Creneau;
import com.example.mydesktopplanner.Models.ExceptionsPackage.ExceptionCreneauNonLibre;
import com.example.mydesktopplanner.Models.ExceptionsPackage.ExceptionDureeInvalide;
import com.example.mydesktopplanner.Models.MyDesktopPlanner;
import com.example.mydesktopplanner.Models.Tache;
import com.example.mydesktopplanner.Models.TacheDecomposable;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Collection;

public class TachesListPopUp extends Stage {



    String title, message;
    ListView<Tache> taches;
    Button ok, annuler;
    Creneau creneau;

    public TachesListPopUp(String title, String message, Creneau creneau) throws IOException {
        setScene(new Scene(FXMLLoader.load(Main.class.getResource("ListPopUp.fxml"))));
        this.title = title;
        this.message = message;
        this.creneau = creneau;

        iniFields();
        setValues();
        setControllers();
    }

    private void setControllers() {
        ok.setOnAction(actionEvent -> {

            try {
                Tache newTache = MyDesktopPlanner.getInstance().getUtilisateur().getCalendrier().getJours().get(creneau.getDebut().toLocalDate()).plannifierTacheCreneau(taches.getSelectionModel().getSelectedItem(), creneau);
                if (newTache != null){
                    MyDesktopPlanner.getInstance().ajouterTache(newTache);
                }
                MyDesktopPlanner.getInstance().getUtilisateur().getUnscheduledTaches().remove(taches.getSelectionModel().getSelectedItem());
            } catch (ExceptionDureeInvalide e) {
                try {
                    ErrorPopUpView.show("Erreur", "La durée de la tâche est supérieure à celle du créneau");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }

            try {
                MainView.getInstance().update();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            close();
        });
        annuler.setOnAction(actionEvent -> {
            try {
                MainView.getInstance().update();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            close();
        });

    }

    private void setValues() {
        taches.getItems().addAll(MyDesktopPlanner.getInstance().getTachesUnscheduled());
        ((Text) getScene().lookup("#title")).setText(title);
        ((Text) getScene().lookup("#message")).setText(message);

    }

    private void iniFields() {
        taches = (ListView<Tache>) getScene().lookup("#list");
        ok = (Button) getScene().lookup("#ok");
        annuler = (Button) getScene().lookup("#annuler");
    }
}
