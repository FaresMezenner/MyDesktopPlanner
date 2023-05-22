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
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.NoSuchElementException;

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

        setCrenauxEtPeriodes();

    }

    private void setCrenauxEtPeriodes()  {

        LocalDate date = LocalDate.of(startOfWeek.getYear(), startOfWeek.getMonth(), startOfWeek.getDayOfMonth());

        LinkedList<Periode> periodes = new LinkedList<>();
        for (int i = 0; i < 7; i++) {

            //setting creneaux
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


            //now getting the periodes that will be shown
            Periode periode = MyDesktopPlanner.getInstance().getUtilisateur().getCalendrier().getPeriode(date);
            if (periode != null) {
                periodes.add(periode);

            }


            date = date.plusDays(1);
        }

        //getting the periode that is before this week, since it could end this week;
        date = date.minusDays(7);
        //testing if there's any periode before this week
        try {
            LocalDate firstPeriodeDate = MyDesktopPlanner.getInstance().getUtilisateur().getCalendrier().getPeriodes().firstKey();
            if (firstPeriodeDate.isBefore(date)) {
                //if there's, we get all those periodes
                ArrayList<Periode> previousPeriodes = MyDesktopPlanner.getInstance().getUtilisateur().getCalendrier().getPeriodesIntervalle(
                        firstPeriodeDate, date.minusDays(1)
                );
                //we add the last one to the periodes list if it's before this week
                Periode lastPeriodeBeforeWeek = previousPeriodes.get(previousPeriodes.size() - 1);
                if (lastPeriodeBeforeWeek.getFin().isAfter(date) || lastPeriodeBeforeWeek.getFin().isEqual(date))
                    periodes.add(0, lastPeriodeBeforeWeek);

            }
        } catch (NoSuchElementException e){
            System.out.println("No periode before this week");
        }
        //now we show these periodes on the calendar

        for (int i = 0; i < 7; i++) {
            for (Periode periode : periodes) {
                if (periode.getDebut().isEqual(date) || periode.getFin().isEqual(date)) {
                    if (periode.getDebut().isEqual(periode.getFin())) {
                        periodeLabel[date.getDayOfWeek().getValue()].setText(periode.getNom());
                    } else if (periode.getDebut().isEqual(date)) {
                        periodeLabel[date.getDayOfWeek().getValue()].setText("Debut " + periode.getNom());
                    } else  {
                        periodeLabel[date.getDayOfWeek().getValue()].setText("Fin " + periode.getNom());
                    }
                }
            }

            date = date.plusDays(1);
        }



    }


    public void update() {
        LocalDate date = LocalDate.of(startOfWeek.getYear(), startOfWeek.getMonth(), startOfWeek.getDayOfMonth());
        for (int i = 0; i < 7; i++) {
            daysLabel[i].setText(date.getDayOfMonth() + ". " + MonthsFrench.values()[date.getMonth().getValue() -1 ].getName());
            daysAgenda[i].getChildren().clear();
            videLabel[i].setVisible(true);
            periodeLabel[i].setText("");
            date = date.plusDays(1);
        }
        setCrenauxEtPeriodes();
    }


    public void changeWeek(LocalDate date) {
        LocalDate dayOfWeek = LocalDate.of(date.getYear(), date.getMonth(), date.getDayOfMonth());
        if (!(dayOfWeek.isEqual(startOfWeek)
                || (dayOfWeek.isAfter(startOfWeek) && dayOfWeek.isBefore(startOfWeek.plusDays(7)))
        )) {
            while (dayOfWeek.getDayOfWeek() != DayOfWeek.SUNDAY){
                dayOfWeek = dayOfWeek.minusDays(1);
            }
            startOfWeek = dayOfWeek;
            update();
        }


    }

    public AnchorPane getView(){
        return calendar;
    }


    public LocalDate getStartOfWeek() {
        return startOfWeek;
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
                    try {
                        MainView.getInstance().setRightSidePannel((new RightSidePannelCreneau(creneau)).getView());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
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
