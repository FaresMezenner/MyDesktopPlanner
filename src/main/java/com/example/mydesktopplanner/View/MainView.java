package com.example.mydesktopplanner.View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainView extends Stage {




    private static MainView instance = null;

    public static MainView getInstance() throws IOException {
        if (instance == null) {
            instance = new MainView();
        }
        return instance;
    }

    private CalendarView calendarView;



    private MainView() throws IOException {
        Scene mainScene = new Scene((new FXMLLoader(com.example.mydesktopplanner.Main.class.getResource("Main.fxml"))).load());
        setScene(mainScene);
        setTitle("My desktop planner");

        iniViews();

    }

    private void iniViews() throws IOException {
        //getting the calendar placeholder
        AnchorPane calendar = (AnchorPane) getScene().lookup("#calendar");
        this.calendarView = new CalendarView();
        calendar.getChildren().add(calendarView.getView());
    }

    public CalendarView getCalendarView() {
        return calendarView;
    }
}
