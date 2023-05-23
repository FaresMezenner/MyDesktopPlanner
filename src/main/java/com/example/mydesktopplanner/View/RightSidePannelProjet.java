package com.example.mydesktopplanner.View;

import com.example.mydesktopplanner.Main;
import com.example.mydesktopplanner.Models.*;
import com.example.mydesktopplanner.Models.ExceptionsPackage.ExceptionDureeInvalide;
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
        ((Button) view.lookup("#save")).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                projet.setNom(name.getText());
                projet.setDescription(desc.getText());
                try {
                    MainView.getInstance().update();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        ((Button) view.lookup("#add")).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Stage creneauxPopUp = new Stage();
                try {
                    creneauxPopUp.setScene(new Scene(FXMLLoader.load(Main.class.getResource("ListPopUp.fxml"))));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                ((Text) creneauxPopUp.getScene().lookup("#title")).setText("Ajouter une tache");

                ((Text) creneauxPopUp.getScene().lookup("#message")).setText("Choisissez une tache planifiée pour l'ajouter \n a votre projet");
                ListView<Creneau> creneauListView = (ListView<Creneau>) creneauxPopUp.getScene().lookup("#list");

                //getting les creneaux non libre
                creneauListView.getItems().addAll(MyDesktopPlanner.getInstance().getCreneauxNonLibres());
                creneauListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

                ((Button) creneauxPopUp.getScene().lookup("#ok")).setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {

                        for (Creneau creneau : creneauListView.getSelectionModel().getSelectedItems()) {
                            projet.ajouterTache(creneau);
                        }

                        creneauxPopUp.close();
                        try {
                            MainView.getInstance().update();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                    }
                });

                creneauxPopUp.show();



            }
        });

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
