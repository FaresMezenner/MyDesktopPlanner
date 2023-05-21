package com.example.mydesktopplanner.View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Stage {


    public Main() throws IOException {
        Scene mainScene = new Scene((new FXMLLoader(com.example.mydesktopplanner.Main.class.getResource("Main.fxml"))).load());
        setScene(mainScene);
        setTitle("My desktop planner");

        iniViews();

    }

    private void iniViews() throws IOException {
        //getting the calendar
        AnchorPane calendar = (AnchorPane) getScene().lookup("#calendar");
        calendar.getChildren().add((new Calendar()).getView());
    }


}
