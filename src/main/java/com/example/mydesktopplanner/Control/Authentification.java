package com.example.mydesktopplanner.Control;
import com.example.mydesktopplanner.Main;
import com.example.mydesktopplanner.Models.ExceptionsPackage.ExceptionUserDoesNotExist;
import com.example.mydesktopplanner.Models.UserManager;
import com.example.mydesktopplanner.Models.Utilisateur;
import com.example.mydesktopplanner.View.MainView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.IOException;

public class Authentification implements EventHandler<ActionEvent> {

    TextField pseudoField;
    Stage window;

    public Authentification(TextField pseudoField, Stage window) {
        this.pseudoField = pseudoField;
        this.window = window;
    }

    // this function authentifies the user
    public void handle(ActionEvent event) {
        String pseudo = pseudoField.getText();
        Utilisateur utilisateur;
        try {
            //we'll try to authentic the user
            utilisateur = UserManager.getInstance().Authentify(pseudoField.getText());
            window.close();

            //once authenticated, we'll show the main view
            MainView.getInstance().show();
        } catch (ExceptionUserDoesNotExist e) {
            //id the user is not found, we'll show a pop up asking him to create a user or not
            System.out.println("USER DOESN't EXIST");
            (new CreateUserPopUp(pseudo)).show();
            return;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private class CreateUserPopUp extends Stage {
        String pseudo;

        public CreateUserPopUp(String pseudo) {
            this.pseudo = pseudo;

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Authentification/CreateUserPopUp.fxml"));

            Scene scene;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException e) {
                System.out.println(e.getMessage());
                return;
            }
            Button oui = (Button) scene.lookup("#oui");
            Button no = (Button) scene.lookup("#no");

            no.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    close();
                }
            });


            oui.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    Utilisateur utilisateur = new Utilisateur(pseudo);
                    try {
                        UserManager.getInstance().setUser(utilisateur);
                        window.close();

                        //once uer created, we'll show the main view
                        MainView.getInstance().show();

                    } catch (ExceptionUserDoesNotExist e) {
                        System.out.println("THIS ERROR SHOULD NEVER HAPPEN");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } finally {
                        close();



                    }
                }
            });

            setScene(scene);

        }

    }

}


