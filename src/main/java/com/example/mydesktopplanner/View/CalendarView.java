package com.example.mydesktopplanner.View;

import com.example.mydesktopplanner.Models.*;
import com.example.mydesktopplanner.Models.ExceptionsPackage.ExceptionCollisionHorairesCreneau;
import com.example.mydesktopplanner.Models.ExceptionsPackage.ExceptionDateInvalide;
import com.example.mydesktopplanner.Models.ExceptionsPackage.ExceptionDureeInvalide;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

public class CalendarView {

    private AnchorPane calendar;
    private Label[] daysLabel = new Label[7], videLabel = new Label[7], periodeLabel = new Label[7];
    private VBox[] daysAgenda = new VBox[7];
    private LocalDate startOfWeek;

    public CalendarView() throws IOException {

        calendar= FXMLLoader.load(com.example.mydesktopplanner.Main.class.getResource("Calender.fxml"));
        LocalDate dayOfWeek = LocalDate.now();

        while (dayOfWeek.getDayOfWeek() != DayOfWeek.SUNDAY){
            dayOfWeek = dayOfWeek.minusDays(1);
        }
        startOfWeek = LocalDate.of(dayOfWeek.getYear(), dayOfWeek.getMonth(), dayOfWeek.getDayOfMonth());

        for (int i = 0; i < 7; i++) {
            daysLabel[i] = (Label) calendar.lookup("#d1" + i);

            daysLabel[i].setText(dayOfWeek.getDayOfMonth() + ". " + MonthsFrench.values()[dayOfWeek.getMonth().getValue() -1 ].getName());

            ScrollPane scrollPane = (ScrollPane) calendar.lookup("#c" + i);
            daysAgenda[i] = (VBox) scrollPane.getContent().lookup("#c1" + i);

            videLabel[i] = (Label) calendar.lookup("#v1" + i);

            BorderPane borderPane = (BorderPane) calendar.lookup("#p1" + i);
            periodeLabel[i] = (Label) borderPane.getCenter().lookup("#p0" + i);


            dayOfWeek = dayOfWeek.plusDays(1);
        }

        setCrenaux();

    }

    private void setCrenaux()  {

        LocalDate date = LocalDate.of(startOfWeek.getYear(), startOfWeek.getMonth(), startOfWeek.getDayOfMonth());
        for (int i = 0; i < 7; i++) {

            LinkedList<Creneau> creneaux;
            try {
                creneaux = MyDesktopPlanner.getInstance().getUtilisateur().getCalendrier().getCreneauxJour(date);
            } catch (ExceptionDateInvalide e) {

                date = date.plusDays(1);
                continue;
            }

            if (!creneaux.isEmpty()) {
                videLabel[i].setVisible(false);
            }



            for (Creneau creneau : creneaux) {
                try {
                    daysAgenda[i].getChildren().add(new CreneauView(creneau).getView());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


            date = date.plusDays(1);
        }

    }


    public void updateCalendar() {
        LocalDate date = LocalDate.of(startOfWeek.getYear(), startOfWeek.getMonth(), startOfWeek.getDayOfMonth());
        for (int i = 0; i < 7; i++) {
            daysLabel[i].setText(date.getDayOfMonth() + ". " + MonthsFrench.values()[date.getMonth().getValue() -1 ].getName());
            daysAgenda[i].getChildren().clear();
            videLabel[i].setVisible(true);
            periodeLabel[i].setText("");
            date = date.plusDays(1);
        }
        setCrenaux();
    }


    public void changeWeek(LocalDate date) {
        LocalDate dayOfWeek = LocalDate.of(date.getYear(), date.getMonth(), date.getDayOfMonth());
        if (!(dayOfWeek.isEqual(startOfWeek)
                || (dayOfWeek.isAfter(startOfWeek) && dayOfWeek.isBefore(startOfWeek.plusDays(7)))
                || dayOfWeek.isEqual(startOfWeek.plusDays(7)))) {
            while (dayOfWeek.getDayOfWeek() != DayOfWeek.SUNDAY){
                dayOfWeek = dayOfWeek.minusDays(1);
            }
            startOfWeek = dayOfWeek;
            updateCalendar();
        }


    }

    public AnchorPane getView(){
        return calendar;
    }

    private class CreneauView {

        AnchorPane view;
        String titre, begin, end, state;
        Creneau creneau;

        public CreneauView(Creneau creneau) throws IOException {
            this.view = (new FXMLLoader(com.example.mydesktopplanner.Main.class.getResource("Creneau.fxml"))).load();
            this.creneau = creneau;

            view.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    //TODO: open creneau Info view
                    changeWeek(LocalDate.of(2023, 5,31));
                }
            });


            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            this.begin = "De: " + creneau.getDebut().format(formatter);
            this.end = "à: "+creneau.getFin().format(formatter);
            this.state = "Etat: Libre";


            this.titre = "Creneau libre";

            if (!creneau.isLibre()) {
                this.titre = creneau.getTache().getNom();
                this.state = "Etat: " + creneau.getTache().getEtat().getName();
            }

            setView();
        }


        public void setView() {
            ((Label) view.lookup("#titre")).setText(titre);
            ((Label) view.lookup("#debut")).setText(begin);
            ((Label) view.lookup("#fin")).setText(end);
            ((Label) view.lookup("#etat")).setText(state);
        }

        public AnchorPane getView(){
            return this.view;
        }

    }
}
