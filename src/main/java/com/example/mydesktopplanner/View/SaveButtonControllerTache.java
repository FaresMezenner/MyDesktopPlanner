package com.example.mydesktopplanner.View;

import com.example.mydesktopplanner.Models.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class SaveButtonControllerTache implements EventHandler<ActionEvent> {

    Tache tache;
    AnchorPane view;

    public SaveButtonControllerTache(Tache tache, AnchorPane view) {
        this.tache = tache;
        this.view = view;
    }

    @Override
    public void handle(ActionEvent actionEvent) {

        tache.setNom(((TextField) view.lookup("#name")).getText());
        tache.setCategorie(
                Categorie.getCategorie(((ChoiceBox) view.lookup("#CategorieChoiceBox")).getValue().toString())
        );
        tache.setPriorite(
                Priorite.getPriorite(((ChoiceBox) view.lookup("#PrioriteChoiceBox")).getValue().toString())
        );
        tache.setDateLimite(((DatePicker) view.lookup("#deadline")).getValue());

        String etatString = ((ChoiceBox) view.lookup("#EtatChoiceBox")).getValue().toString();

        try {
            MainView.getInstance().update();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
