package com.example.mydesktopplanner.View;

import com.example.mydesktopplanner.Main;
import com.example.mydesktopplanner.Models.*;
import com.example.mydesktopplanner.Models.ExceptionsPackage.ExceptionCreneauNonLibre;
import com.example.mydesktopplanner.Models.ExceptionsPackage.ExceptionDureeInvalide;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class SaveButtonControllerTache implements EventHandler<ActionEvent> {

    Tache tache;
    AnchorPane view;

    public SaveButtonControllerTache(Tache tache, AnchorPane view) {
        this.tache = tache;
        this.view = view;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        if (tache != null) {
            tache.setNom(((TextField) view.lookup("#name")).getText());
            tache.setCategorie(
                    Categorie.getCategorie(((ChoiceBox) view.lookup("#CategorieChoiceBox")).getValue().toString())
            );
            tache.setPriorite(
                    Priorite.getPriorite(((ChoiceBox) view.lookup("#PrioriteChoiceBox")).getValue().toString())
            );
            tache.setDateLimite(((DatePicker) view.lookup("#deadline")).getValue());

            Button annuler = ((Button) view.lookup("#annuler"));


            String etatString = ((ChoiceBox) view.lookup("#EtatChoiceBox")).getValue().toString();

            Etat etatPrecedent = tache.getEtat();
            Etat etatActuel = Etat.getEtat(etatString);
            MyDesktopPlanner myDesktopPlanner = MyDesktopPlanner.getInstance();

            if (etatPrecedent.equals(Etat.UNSCHEDULED) && etatActuel.equals(Etat.INPROGRESS)) {
                // On fait apparaitre une fenetre de dialogue pour choisir le creneau
                try {
                    System.out.println(Main.class.getResource("ListPopUp.fxml"));


                    FXMLLoader loader = new FXMLLoader(Main.class.getResource("ListPopUp.fxml"));
                    Stage stage = new Stage();
                    stage.setScene(new Scene(loader.load()));
                    stage.show();


                    Text titreText = ((Text) stage.getScene().lookup("#title"));

                    titreText.setText("Selection de creneau ");

                    Text descriptionText = ((javafx.scene.text.Text) stage.getScene().lookup("#message"));

                    titreText.setText("Choisissez un créneau pour la tache ");

                    ListView<Creneau> listView = ((ListView<Creneau>) stage.getScene().lookup("#list"));

                    Button ok = ((Button) stage.getScene().lookup("#ok"));


                    ArrayList<Creneau> listeCreneauxArray = new ArrayList<Creneau>();

                    LocalDate dernierJour = myDesktopPlanner.getUtilisateur().getCalendrier().getDernierJour().getDate();

                    if (LocalDate.now().compareTo(dernierJour) < 0) {
                        listeCreneauxArray = myDesktopPlanner.getCreneauxIntervalle(LocalDate.now(), dernierJour);

                        // Filtrer pour garder que les creneaux libres :
                        ArrayList<Creneau> listeCreneauxLibres = new ArrayList<Creneau>();

                        for (Creneau creneau : listeCreneauxArray) {
                            if (creneau.getTache() == null) {
                                listeCreneauxLibres.add(creneau);
                            }
                        }

                        listView.getItems().addAll(listeCreneauxLibres);
                    }


                    ok.setOnAction(event -> {
                        // Obtenir l'élément sélectionné dans la ListView
                        Creneau selectedValue = listView.getSelectionModel().getSelectedItem();

                        try {

                            myDesktopPlanner.affecterTacheCreneau(selectedValue, tache);
                            myDesktopPlanner.changerEtatTache(selectedValue, Etat.INPROGRESS);
                            myDesktopPlanner.attribuerFelicitationsBadges(selectedValue);
                            stage.close();
                            try {
                                MainView.getInstance().update();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        } catch (ExceptionDureeInvalide e) {
                            try {
                                ErrorPopUpView.show("Duree invealide", "La duree de la tache est trop longue pour le creneau selectionne");
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        } catch (ExceptionCreneauNonLibre e) {
                            throw new RuntimeException(e);
                        }


                    });


                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else if (etatPrecedent.equals(Etat.INPROGRESS) && etatActuel.equals(Etat.COMPLETED)) {
                tache.setEtat(Etat.COMPLETED);

            }
        }
        try {
            MainView.getInstance().update();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


}
