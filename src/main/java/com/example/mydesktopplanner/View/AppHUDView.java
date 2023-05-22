package com.example.mydesktopplanner.View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.time.LocalDate;

public class AppHUDView {

    AnchorPane view;
    CalendarView calendarView;
    Button nextWeek ;
    Button previousWeek;

    public AppHUDView(CalendarView calendarView) throws IOException {
        view = FXMLLoader.load(com.example.mydesktopplanner.Main.class.getResource("AppHUD.fxml"));
        
        this.calendarView = calendarView;

        nextWeek = (Button) view.lookup("#next");
        previousWeek = (Button) view.lookup("#previous");

        updateView();
        setControllers();
    }

    public void updateView() throws IOException {
        BorderPane borderPane = (BorderPane) view.lookup("#date_container");
        Text text = (Text) borderPane.getCenter().lookup("#date");
;

        text.setText(
                calendarView.getStartOfWeek().getDayOfMonth() + " " +
                MonthsFrench.values()[calendarView.getStartOfWeek().getMonth().getValue() - 1].getName() + " " +
                        calendarView.getStartOfWeek().getYear() + " ~ " +
                        calendarView.getStartOfWeek().plusDays(6).getDayOfMonth() + " " +
                        MonthsFrench.values()[calendarView.getStartOfWeek().plusDays(6).getMonth().getValue() - 1].getName() + " " +
                        calendarView.getStartOfWeek().plusDays(6).getYear()
        );
    }

    private void setControllers() {

        nextWeek.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("next week");
                try {
                    calendarView.changeWeek(calendarView.getStartOfWeek().plusDays(7));
                    updateView();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        });

        previousWeek.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                try {
                    calendarView.changeWeek(calendarView.getStartOfWeek().minusDays(7));
                    updateView();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        });


        ((ImageView) view.lookup("#profile")).setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    (new ProfilePopUpView()).show();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });


    }

    public Node getView() {
        return this.view;
    }
}
