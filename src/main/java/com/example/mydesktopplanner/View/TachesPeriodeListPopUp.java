package com.example.mydesktopplanner.View;

import com.example.mydesktopplanner.Main;
import com.example.mydesktopplanner.Models.Creneau;
import com.example.mydesktopplanner.Models.ExceptionsPackage.ExceptionCollisionHorairesCreneau;
import com.example.mydesktopplanner.Models.ExceptionsPackage.ExceptionCollisionPeriode;
import com.example.mydesktopplanner.Models.ExceptionsPackage.ExceptionDateInvalide;
import com.example.mydesktopplanner.Models.ExceptionsPackage.ExceptionDureeInvalide;
import com.example.mydesktopplanner.Models.MyDesktopPlanner;
import com.example.mydesktopplanner.Models.Periode;
import com.example.mydesktopplanner.Models.Tache;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.LinkedList;

public class TachesPeriodeListPopUp extends Stage {



    String title, message;
    ListView<Tache> taches;
    Button ok, annuler;
    Periode periode;

    public TachesPeriodeListPopUp(String title, String message, Periode periode) throws IOException {
        setScene(new Scene(FXMLLoader.load(Main.class.getResource("ListPopUp.fxml"))));
        this.title = title;
        this.message = message;
        this.periode = periode;

        iniFields();
        setValues();
        setControllers();
    }

    private void setControllers() {
        ok.setOnAction(actionEvent -> {

//            try {
//                MyDesktopPlanner.getInstance().ajouterPeriode(
//                        periode
//                );
//
//                MainView.getInstance().update();
//            } catch (ExceptionDateInvalide e) {
//                throw new RuntimeException(e);
//            } catch (ExceptionCollisionPeriode e) {
//                try {
//                    ErrorPopUpView.show("Erreur", e.getMessage());
//                } catch (IOException ex) {
//                    throw new RuntimeException(ex);
//                }
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            } catch (ExceptionDureeInvalide e) {
//                throw new RuntimeException(e);
//            } catch (ExceptionCollisionHorairesCreneau e) {
//                throw new RuntimeException(e);
//            }


        });
        annuler.setOnAction(actionEvent -> {
        });

    }

    private void setValues() {
        taches.getItems().addAll(MyDesktopPlanner.getInstance().getTachesUnscheduled());
        taches.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        ((Text) getScene().lookup("#title")).setText(title);
        ((Text) getScene().lookup("#message")).setText(message);

    }

    private void iniFields() {
        taches = (ListView<Tache>) getScene().lookup("#list");
        ok = (Button) getScene().lookup("#ok");
        annuler = (Button) getScene().lookup("#annuler");
    }
}
