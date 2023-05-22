package com.example.mydesktopplanner.View;

import com.example.mydesktopplanner.Main;
import com.example.mydesktopplanner.Models.*;
import com.example.mydesktopplanner.Models.ExceptionsPackage.ExceptionCollisionHorairesCreneau;
import com.example.mydesktopplanner.Models.ExceptionsPackage.ExceptionDateInvalide;
import com.example.mydesktopplanner.Models.ExceptionsPackage.ExceptionDureeInvalide;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class AjouterTacheView extends Stage {

    private TextField nom, duration, perioTextField, nbTextField, debutHours, debutMinutes, finHours, finMinutes;
    private Text perioText, nbText;
    private ChoiceBox category, priority;
    private DatePicker deadlinePicker;
    private CheckBox perioCheck, decoCheck;
    private Button ok;
    private AnchorPane container;

    public AjouterTacheView() throws IOException {
        setScene(new Scene(FXMLLoader.load(Main.class.getResource("AjouterTache.fxml"))));

        setFields();
        setControllers();
        
        
    }

    private void setFields() {

        nom = (TextField) getScene().lookup("#name");
        category = (ChoiceBox) getScene().lookup("#CategorieChoiceBox");
        priority = (ChoiceBox) getScene().lookup("#PrioriteChoiceBox");
        duration = (TextField) getScene().lookup("#DureeTextField");
        deadlinePicker = (DatePicker) getScene().lookup("#DeadlineDatePicker");
        decoCheck = (CheckBox) getScene().lookup("#deco");
        perioCheck = (CheckBox) getScene().lookup("#perio");
        perioText = (Text) getScene().lookup("#perioText");
        nbText = (Text) getScene().lookup("#NbText");
        perioTextField = (TextField) getScene().lookup("#PeriodiciteTextField");
        nbTextField = (TextField) getScene().lookup("#NbPlannificationsTextField");
        container = (AnchorPane) getScene().lookup("#debutFinContainer");
        debutHours = (TextField) getScene().lookup("#DebutHeures");
        debutMinutes = (TextField) getScene().lookup("#DebutMinutes");
        finHours = (TextField) getScene().lookup("#FinHeures");
        finMinutes = (TextField) getScene().lookup("#FinMinutes");
        ok = (Button) getScene().lookup("#ok");


    }

    private boolean checkInput() {
         return !(nom.getText().isBlank() || duration.getText().isBlank() || deadlinePicker.getValue() == null || category.getValue() == null || priority.getValue() == null);
    }
    private void setControllers() {
        decoCheck.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                if (t1) {
                    perioCheck.setSelected(false);
                }
            }
        });

        perioCheck.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                if (t1) {
                    decoCheck.setSelected(false);

                    perioText.setVisible(true);
                    perioTextField.setVisible(true);
                    nbText.setVisible(true);
                    nbTextField.setVisible(true);
                    container.setVisible(true);
                } else{
                    perioText.setVisible(false);
                    perioTextField.setVisible(false);
                    nbText.setVisible(false);
                    nbTextField.setVisible(false);
                    container.setVisible(false);
                }

            }
        });


        ok.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {


                if (checkInput()) {
                    if (decoCheck.isSelected()) {
                        TacheDecomposable tache = new TacheDecomposable(
                                nom.getText(),
                                Duration.ofMinutes(Integer.valueOf(duration.getText())),
                                Priorite.getPriorite((String) priority.getValue()),
                                deadlinePicker.getValue(),
                                Categorie.getCategorie((String) category.getValue())
                        );
                        MyDesktopPlanner.getInstance().ajouterTache(tache);
                        close();
                        try {
                            MainView.getInstance().update();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    } else {

                        TacheSimple tache = new TacheSimple(
                                nom.getText(),
                                Duration.ofMinutes(Integer.valueOf(duration.getText())),
                                Priorite.getPriorite((String) priority.getValue()),
                                deadlinePicker.getValue(),
                                Categorie.getCategorie((String) category.getValue()),
                                false
                        );
                        if (perioCheck.isSelected()) {
                            tache.setPeriodique(true);

                            CreneauPeriodique creneauPeriodique;
                            try {
                                creneauPeriodique = new CreneauPeriodique(
                                        LocalDateTime.of(LocalDate.now(), LocalTime.of(Integer.valueOf(debutHours.getText()), Integer.valueOf(debutMinutes.getText()))),
                                        LocalDateTime.of(LocalDate.now(), LocalTime.of(Integer.valueOf(finHours.getText()), Integer.valueOf(finMinutes.getText()))),
                                        tache
                                );
                            } catch (ExceptionDureeInvalide e) {
                                try {
                                    (new ErrorPopUpView(null, e.getMessage())).show();
                                } catch (IOException ex) {
                                    throw new RuntimeException(ex);
                                }
                                return;
                            }


                            try {
                                MyDesktopPlanner.getInstance().plannifierTachePeriodique(
                                        creneauPeriodique,
                                        Integer.valueOf(perioTextField.getText()),
                                        Integer.valueOf(nbTextField.getText())
                                );

                                close();
                                try {
                                    MainView.getInstance().update();
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }

                            } catch (ExceptionDateInvalide e) {
                                throw new RuntimeException(e);
                            } catch (ExceptionCollisionHorairesCreneau e) {
                                try {
                                    (new ErrorPopUpView(null, e.getMessage())).show();
                                    return;
                                } catch (IOException ex) {
                                    throw new RuntimeException(ex);
                                }
                            }

                        } else {

                            MyDesktopPlanner.getInstance().ajouterTache(tache);
                            close();
                            try {
                                MainView.getInstance().update();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }

                        }

                    }
                }
                else {
                    try {
                        (new ErrorPopUpView(null, "Veuillez remplir tous tous les champs")).show();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });






    }
}
