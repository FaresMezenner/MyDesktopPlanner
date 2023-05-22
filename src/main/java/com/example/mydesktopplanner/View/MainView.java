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
    private AppHUDView hudView;
    private SidePanelView sidePanelView;



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
        //setting the calendar inside its placeholder
        calendar.getChildren().add(this.calendarView.getView());

        //getting the hud placeholder
        AnchorPane hud = (AnchorPane) getScene().lookup("#hud");
        this.hudView = new AppHUDView(this.calendarView);
        //setting the hud inside its placeholder
        hud.getChildren().add(hudView.getView());

        //getting the side panel placeholder
        AnchorPane sidePanel = (AnchorPane) getScene().lookup("#side_panel");
        this.sidePanelView = new SidePanelView();
        //setting the side panel inside its placeholder
        sidePanel.getChildren().add(sidePanelView.getView());




    }

    public CalendarView getCalendarView() {
        return calendarView;
    }
}
