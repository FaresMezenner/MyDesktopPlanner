package com.example.mydesktopplanner.View;

import com.example.mydesktopplanner.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class ChoiceView extends Stage {

    String message;

    public ChoiceView(EventHandler<ActionEvent> positive, EventHandler<ActionEvent> negative, String message, String positiveBtn, String negativeBtn) throws IOException {
        setScene(new Scene(FXMLLoader.load(Main.class.getResource("ChoicePopUp.fxml"))));
        this.message = message;


        ((Button) getScene().lookup("#positive")).setText(positiveBtn);
        ((Button) getScene().lookup("#negative")).setText(negativeBtn);
        ((Button) getScene().lookup("#positive")).setOnAction(positive);
        ((Button) getScene().lookup("#negative")).setOnAction(negative);

        ((Text) getScene().lookup("#message")).setText(message);



    }

    static public void showChoice(EventHandler<ActionEvent> positive, EventHandler<ActionEvent> negative, String message) throws IOException {
        Stage choice = new Stage();
        Stage finalChoice = choice;
        choice = (new ChoiceView(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                positive.handle(actionEvent);
                finalChoice.close();

            }
        }, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                negative.handle(actionEvent);
                finalChoice.close();
            }
        }, message, "Oui", "Non"));
        choice.show();

    }

    static public void showChoice(EventHandler<ActionEvent> positive, EventHandler<ActionEvent> negative, String message, String positiveBtn, String negativeBtn) throws IOException {
        Stage choice = new Stage();
        Stage finalChoice = choice;
        choice = (new ChoiceView(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                positive.handle(actionEvent);
                finalChoice.close();

            }
        }, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                negative.handle(actionEvent);
                finalChoice.close();
            }
        }, message, positiveBtn, negativeBtn));
        choice.show();
    }

}
