package com.example.mydesktopplanner.View;

import com.example.mydesktopplanner.Models.Badge;
import com.example.mydesktopplanner.Models.MyDesktopPlanner;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class ProfilePopUpView extends Stage {


    public ProfilePopUpView() throws IOException {
        setScene(new Scene(FXMLLoader.load(com.example.mydesktopplanner.Main.class.getResource("ProfilePopUp.fxml"))));

        setValues();
        setControllers();
    }

    private void setControllers() {
        ((Button)getScene().lookup("#ok")).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                close();
            }
        });
    }

    private void setValues() {
        ((Text)getScene().lookup("#user")).setText(MyDesktopPlanner.getInstance().getUtilisateur().getPseudo());
        ((Text)getScene().lookup("#good")).setText(
                String.valueOf(MyDesktopPlanner.getInstance().getUtilisateur().getBadges()[Badge.GOOD.ordinal()])
        );
        ((Text)getScene().lookup("#verygood")).setText(
                String.valueOf(MyDesktopPlanner.getInstance().getUtilisateur().getBadges()[Badge.VERYGOOD.ordinal()])
        );
        ((Text)getScene().lookup("#excellent")).setText(
                String.valueOf(MyDesktopPlanner.getInstance().getUtilisateur().getBadges()[Badge.EXCELLENT.ordinal()])
        );

    }

}
