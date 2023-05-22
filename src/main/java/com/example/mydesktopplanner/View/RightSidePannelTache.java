package com.example.mydesktopplanner.View;

import com.example.mydesktopplanner.Models.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;

public class RightSidePannelTache {
    AnchorPane view;

    Tache tache;

    public RightSidePannelTache(Tache tache) throws IOException {
        this.view = FXMLLoader.load(com.example.mydesktopplanner.Main.class.getResource("RightSidePanelTaches.fxml"));
        this.tache = tache;

        setViewValues();
        setControllers();

    }

    private void setControllers() {
        ((Button) view.lookup("#save")).setOnAction(new SaveButtonControllerTache(tache, view));

        ((Button) view.lookup("#delete")).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                MyDesktopPlanner.getInstance().getTachesUnscheduled().remove(tache);
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
        });
    }

    private void setViewValues() {

        //setting the name
        ((TextField) view.lookup("#name")).setText(tache.getNom());


        //setting the categorie
        ChoiceBox categorie = (ChoiceBox) view.lookup("#CategorieChoiceBox");
        categorie.setValue(categorie.getItems().get(tache.getCategorie().ordinal()));

        //setting priority
        ChoiceBox priority = (ChoiceBox) view.lookup("#PrioriteChoiceBox");
        priority.setValue(priority.getItems().get(tache.getPriorite().ordinal()));

        //setting the duration
        ((TextField) view.lookup("#duration")).setText(String.valueOf(tache.getDuree().toMinutes()));

        //setting the date limite
        ((DatePicker) view.lookup("#deadline")).setValue(tache.getDateLimite());

        //setting the state
        ChoiceBox state = (ChoiceBox) view.lookup("#EtatChoiceBox");
        if (tache.getEtat() == Etat.UNSCHEDULED) {

            state.setValue(state.getItems().get(1));
        } else {
            state.setValue(state.getItems().get(0));
        }

        //setting Decomposable
        ((CheckBox) view.lookup("#deco")).setSelected(tache.isDecomposable());

        //setting the subtasks
        AnchorPane subtasksContainer = (AnchorPane) view.lookup("#nb_sous_container");
        if (!tache.isDecomposable()) {
            subtasksContainer.setVisible(false);
        } else {
            ((Text) view.lookup("#nb_sous")).setText(String.valueOf(((TacheDecomposable) tache).getNbSousTaches()));
        }


    }

    public AnchorPane getView() {
        return view;
    }
}
