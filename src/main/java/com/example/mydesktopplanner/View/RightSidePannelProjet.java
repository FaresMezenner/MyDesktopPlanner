package com.example.mydesktopplanner.View;

import com.example.mydesktopplanner.Main;
import com.example.mydesktopplanner.Models.*;
import com.example.mydesktopplanner.Models.ExceptionsPackage.ExceptionDureeInvalide;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

public class RightSidePannelProjet {
    AnchorPane view;
    Projet projet;
    TextField name, desc;
    ListView<Creneau> listTaches;


    public RightSidePannelProjet(Projet projet) throws IOException {
        this.view = FXMLLoader.load(Main.class.getResource("RightSidePanelProjet.fxml"));
        this.projet = projet;

        iniFields();
        setValuesView();
        setControllers();

    }

    private void setControllers() {
        ((Button) view.lookup("#delete")).setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    try {
                        ChoiceView.showChoice(
                                new EventHandler<ActionEvent>() {
                                    @Override
                                    public void handle(ActionEvent actionEvent) {
                                        MyDesktopPlanner.getInstance().supprimerProjetAvecCreneau(projet);
                                        try {
                                            MainView.getInstance().emptyRightSidePannel();
                                        } catch (IOException e) {
                                            throw new RuntimeException(e);
                                        }
                                        try {
                                            MainView.getInstance().update();
                                        } catch (IOException e) {
                                            throw new RuntimeException(e);
                                        }

                                    }
                                },
                                new EventHandler<ActionEvent>() {
                                    @Override
                                    public void handle(ActionEvent actionEvent) {

                                        MyDesktopPlanner.getInstance().supprimerProjet(projet);
                                        try {
                                            MainView.getInstance().emptyRightSidePannel();
                                        } catch (IOException e) {
                                            throw new RuntimeException(e);
                                        }
                                        try {
                                            MainView.getInstance().update();
                                        } catch (IOException e) {
                                            throw new RuntimeException(e);
                                        }
                                    }
                                },
                                "Voulez vous supprimer tous les creneaux de ce projet aussi?"
                        );
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                }
            }
        );
    }

    private void iniFields() {
        name = (TextField) view.lookup("#name");
        desc = (TextField) view.lookup("#desc");
        listTaches = (ListView<Creneau>) view.lookup("#tasks");
    }

    private void setValuesView() {
        name.setText(projet.getNom());
        desc.setText(projet.getDescription());
        listTaches.getItems().addAll( projet.getTaches().values());
    }

    public AnchorPane getView() {
        return view;
    }
}
