package com.example.mydesktopplanner.View;

import com.example.mydesktopplanner.Models.MyDesktopPlanner;
import com.example.mydesktopplanner.Models.Periode;
import com.example.mydesktopplanner.Models.Projet;
import com.example.mydesktopplanner.Models.Tache;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class SidePanelView {
    AnchorPane view;
    VBox taches, projets, periodes;


    public SidePanelView() throws IOException {
        this.view = FXMLLoader.load(com.example.mydesktopplanner.Main.class.getResource("SidePanel.fxml"));

        ScrollPane scrollPane = (ScrollPane) view.lookup("#container");
        Accordion accordion = (Accordion) scrollPane.getContent().lookup("#accordion");

        TitledPane titledPane;
        //getting the taches Vbox
        titledPane = (TitledPane) accordion.getPanes().get(0);
        taches = (VBox) titledPane.getContent().lookup("#taches");

        //getting the projets Vbox
        titledPane = (TitledPane) accordion.getPanes().get(1);
        projets = (VBox) titledPane.getContent().lookup("#projets");

        //getting the periodes Vbox
        titledPane = (TitledPane) accordion.getPanes().get(2);
        periodes = (VBox) titledPane.getContent().lookup("#periodes");

        fillTitledPanes();


    }

    private void fillTitledPanes() throws IOException {

        fillTaches();
        fillProjets();
        fillPeriodes();

    }



    public AnchorPane getView() {
        return view;
    }

    private void fillTaches() throws IOException {

        Button button;
        for (Tache tache : MyDesktopPlanner.getInstance().getTachesUnscheduled()){
            button = new TacheListView(tache).getView();
            taches.getChildren().add(button);
        }


    }

    private class TacheListView {
        Button view;
        Tache tache;


        public TacheListView( Tache tache) throws IOException {
            this.view = FXMLLoader.load(com.example.mydesktopplanner.Main.class.getResource("ButtonListView.fxml"));
            this.tache = tache;
            view.setText(tache.getNom());
            view.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    System.out.println(tache);
                }
            });
        }

        public Button getView() {
            return view;
        }
    }

    private void fillProjets() throws IOException {
        Button button;
        for (Projet projet : MyDesktopPlanner.getInstance().getProjets()) {
            button = new ProjetListView(projet).getView();
            projets.getChildren().add(button);
        }
    }


    private class ProjetListView {
        Button view;
        Projet projet;


        public ProjetListView( Projet projet) throws IOException {
            this.view = FXMLLoader.load(com.example.mydesktopplanner.Main.class.getResource("ButtonListView.fxml"));
            this.projet = projet;
            view.setText(projet.getNom());
            view.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    System.out.println(projet);
                }
            });
        }

        public Button getView() {
            return view;
        }
    }


    private void fillPeriodes() throws IOException {

        Button button;
        for (Periode periode :  MyDesktopPlanner.getInstance().getPeriodes().values()) {
            System.out.println(periode);
            button = new PeriodeListView(periode).getView();
            periodes.getChildren().add(button);
        }
    }


    private class PeriodeListView {
        Button view;
        Periode periode;


        public PeriodeListView(Periode periode) throws IOException {
            this.view = FXMLLoader.load(com.example.mydesktopplanner.Main.class.getResource("ButtonListView.fxml"));
            this.periode = periode;
            view.setText(this.periode.getNom());
            view.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    System.out.println(periode);
                }
            });
        }

        public Button getView() {
            return view;
        }
    }



}
