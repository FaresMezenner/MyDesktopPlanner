package com.example.mydesktopplanner.View;

import com.example.mydesktopplanner.Models.Creneau;
import com.example.mydesktopplanner.Models.MyDesktopPlanner;
import com.example.mydesktopplanner.Models.TacheDecomposable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

public class RightSidePannelCreneau {

    AnchorPane view;
    Creneau creneau;


    public RightSidePannelCreneau(Creneau creneau) throws IOException {
        view = FXMLLoader.load(com.example.mydesktopplanner.Main.class.getResource("RightSidePanelCreneau.fxml"));
        this.creneau = creneau;
        setViewValues();
        setControllers();
    }

    private void setControllers() {

        //setting the save button
        ((Button) view.lookup("#save")).setOnAction(new SaveButtonControllerTache(creneau.getTache(), view){
            @Override
            public void handle(ActionEvent actionEvent) {
                super.handle(actionEvent);
                creneau.setBlocked(((CheckBox) view.lookup("#blocked")).isSelected());
            }
        });

        //setting the delete button

        ((Button) view.lookup("#delete")).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (!creneau.isLibre()) MyDesktopPlanner.getInstance().getTachesUnscheduled().remove(creneau.getTache());
                MyDesktopPlanner.getInstance().suprimerCreneau(creneau);
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

        Node view = ((ScrollPane) this.view.lookup("#container")).getContent();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy - HH:mm");

        //setting the time of beginning
        ((TextField) view.lookup("#debut")).setText(creneau.getDebut().format(formatter));

        //setting the time of ending
        ((TextField) view.lookup("#fin")).setText(creneau.getFin().format(formatter));

        //set blocked
        ((CheckBox) view.lookup("#blocked")).setSelected(creneau.isBlocked());

        //setting if free or not
        ((CheckBox) view.lookup("#free")).setSelected(creneau.isLibre());
        if (!creneau.isLibre()) ((CheckBox) view.lookup("#free")).setDisable(false);

        //setting if periodique or not
        ((CheckBox) view.lookup("#perio")).setSelected(creneau.isPeriodique());

        //showing tache info only if free
        if (!creneau.isLibre()) {
            //setting the name
            ((TextField) view.lookup("#name")).setText(creneau.getTache().getNom());

            //setting the categorie
            ChoiceBox categorie = (ChoiceBox) view.lookup("#CategorieChoiceBox");
            categorie.setValue(categorie.getItems().get(creneau.getTache().getCategorie().ordinal()));

            //setting priority
            ChoiceBox priority = (ChoiceBox) view.lookup("#PrioriteChoiceBox");
            priority.setValue(priority.getItems().get(creneau.getTache().getPriorite().ordinal()));

            //setting the duration
            ((TextField) view.lookup("#duration")).setText(String.valueOf(creneau.getTache().getDuree().toMinutes()));

            //setting the date limite
            ((DatePicker) view.lookup("#deadline")).setValue(creneau.getTache().getDateLimite());

            //setting the state
            ChoiceBox state = (ChoiceBox) view.lookup("#EtatChoiceBox");
            state.setValue(state.getItems().get(creneau.getTache().getEtat().ordinal()));

            //setting Decomposable
            ((CheckBox) view.lookup("#deco")).setSelected(creneau.getTache().isDecomposable());

            //setting the subtasks
            AnchorPane subtasksContainer = (AnchorPane) view.lookup("#nb_sous_container");
            if (!creneau.getTache().isDecomposable()) {
                subtasksContainer.setVisible(false);
            } else {
                ((Text) view.lookup("#nb_sous")).setText(String.valueOf(((TacheDecomposable) creneau.getTache()).getNbSousTaches()));
            }
        } else {
            ((AnchorPane) view.lookup("#tache_info")).setVisible(false);
        }


    }

    public AnchorPane getView() {
        return view;
    }
}