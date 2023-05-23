package com.example.mydesktopplanner.View;

import com.example.mydesktopplanner.Main;
import com.example.mydesktopplanner.Models.*;
import com.example.mydesktopplanner.Models.ExceptionsPackage.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
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

            LinkedList<Tache> tachesSelected = new LinkedList<>();
            for (Tache tache : taches.getSelectionModel().getSelectedItems()){
                tachesSelected.add(tache);
            }

            try {

                ArrayList<Tache> unscheduledTaches = MyDesktopPlanner.getInstance().plannifierTachesPeriode(
                        tachesSelected,
                        periode
                );

                Stage stage = new Stage();
                stage.setScene(new Scene(FXMLLoader.load(Main.class.getResource("ListPopUp.fxml"))));
                ((Text) stage.getScene().lookup("#title")).setText("Taches non planifiées");
                ((Text) stage.getScene().lookup("#message")).setText("Les taches suivantes n'ont pas pu être planifiées \n voullez vous les planifier apres la periode ou annulers l'operation ?");
                ListView<Tache> taches = (ListView<Tache>) stage.getScene().lookup("#list");
                taches.getItems().addAll(unscheduledTaches);
                taches.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
                Button ok = (Button) stage.getScene().lookup("#ok");
                ok.setText("planifier");
                Button annuler = (Button) stage.getScene().lookup("#annuler");
                ok.setOnAction(actionEvent1 -> {
                    try {
                        for (Tache tache : taches.getSelectionModel().getSelectedItems()){

                            MyDesktopPlanner.getInstance().getUtilisateur().plannifierTacheAutomatiquement(tache, periode.getFin().plusDays(1));
                        }

                        stage.close();
                        close();
                        MainView.getInstance().update();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } catch (ExceptionPlannificationImpossible e) {
                        try {

                            System.out.println("plannification impossible");
                            ErrorPopUpView.show("Error","pas des creneaux disponible pour tous les taches, les restes sont planifiées");
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                });

                annuler.setOnAction(actionEvent1 -> {
                    stage.close();
                    close();
                    try {

                        FileInputStream fIn = new FileInputStream("utilisateurTmp");
                        ObjectInputStream in = new ObjectInputStream(fIn);
                        MyDesktopPlanner.getInstance().setUtilisateur((Utilisateur) in.readObject());
                        fIn.close();
                        in.close();
                        MainView.getInstance().update();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                });

                stage.show();




                MainView.getInstance().update();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ExceptionDureeInvalide e) {
                throw new RuntimeException(e);
            } catch (ExceptionCollisionHorairesCreneau e) {
                throw new RuntimeException(e);
            }


        });
        annuler.setOnAction(actionEvent -> {

            try {
                FileInputStream fIn = new FileInputStream("utilisateurTmp");
                ObjectInputStream in = new ObjectInputStream(fIn);
                MyDesktopPlanner.getInstance().setUtilisateur((Utilisateur) in.readObject());
                fIn.close();
                in.close();

            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            close();
            try {
                MainView.getInstance().update();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
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
