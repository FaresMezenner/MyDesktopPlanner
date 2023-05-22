package com.example.mydesktopplanner.View;

import com.example.mydesktopplanner.Main;
import com.example.mydesktopplanner.Models.*;
import com.example.mydesktopplanner.Models.ExceptionsPackage.ExceptionPlannificationImpossible;
import com.example.mydesktopplanner.Models.ExceptionsPackage.ExceptionUserDoesNotExist;
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
import java.time.Duration;
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

        update();
        setControllers();
    }

    public void update() throws IOException {
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
                    update();
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
                    update();
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

        ((Button) view.lookup("#logout")).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    UserManager.getInstance().setUser(MyDesktopPlanner.getInstance().getUtilisateur());
                    MyDesktopPlanner.getInstance().logout();

                    MainView.getInstance().close();

                } catch (ExceptionUserDoesNotExist e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        });

        ((Button) view.lookup("#projet")).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                TacheSimple tacheSimple = new TacheSimple("tache simple", Duration.ofMinutes(30), Priorite.HIGH, LocalDate.of(2023, 5, 27), Categorie.HEALTH, false);
                try {
                    MyDesktopPlanner.getInstance().plannifierTacheAutomatiquement(tacheSimple);
                    MainView.getInstance().update();
                } catch (ExceptionPlannificationImpossible e) {
                    throw new RuntimeException(e);
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
