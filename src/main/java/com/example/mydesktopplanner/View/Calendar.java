package com.example.mydesktopplanner.View;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class Calendar {

    private AnchorPane calendar;
    private Label[] daysLabel = new Label[7];
    private ScrollPane[] daysAgenda = new ScrollPane[7];
    private LocalDate dayOfWeek;

    public Calendar() throws IOException {

        calendar= FXMLLoader.load(com.example.mydesktopplanner.Main.class.getResource("Calender.fxml"));
        dayOfWeek = LocalDate.now();

        while (dayOfWeek.getDayOfWeek() != DayOfWeek.SUNDAY){
            dayOfWeek = dayOfWeek.minusDays(1);
        }

        for (int i = 0; i < 7; i++) {
            daysLabel[i] = (Label) calendar.lookup("#d1" + i);

            daysLabel[i].setText(dayOfWeek.getDayOfMonth() + ". " + dayOfWeek.getMonth());

            daysAgenda[i] = (ScrollPane) calendar.lookup("#c1" + i);

            dayOfWeek = dayOfWeek.plusDays(1);
        }

        setCrenaux();

    }

    private void setCrenaux() {
    }

    public AnchorPane getView(){
        return calendar;
    }
}
