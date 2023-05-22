package com.example.mydesktopplanner.View;

import com.example.mydesktopplanner.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class ErrorPopUpView extends Stage {

    private String title, message;

    public ErrorPopUpView(String title, String message) throws IOException {
        setScene(new Scene(FXMLLoader.load(Main.class.getResource("ErrorsPopUp.fxml"))));
        this.title = title;
        this.message = message;

        if (title == null) {
            this.title = "Erreur";
        }

        ((Text) getScene().lookup("#title")).setText(title);
        ((Text) getScene().lookup("#message")).setText(message);

    }
}