package com.example.mydesktopplanner;

import com.example.mydesktopplanner.Models.ExceptionsPackage.ExceptionUserDoesNotExist;
import com.example.mydesktopplanner.Models.UserManager;
import com.example.mydesktopplanner.Models.Utilisateur;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Authentification/AuthentificationScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args)  {

        Utilisateur user = new Utilisateur("fares");

        System.out.println("Before storing user \n\n");

        user.afficher();

        try {
            UserManager.getInstance().setUser(user);
        } catch (ExceptionUserDoesNotExist e) {
            throw new RuntimeException(e);
        }


        System.out.println("\n\n After storing user \n\n");
        Utilisateur inUser;
        try {
            inUser = UserManager.getInstance().Authentify("fares");
        } catch (ExceptionUserDoesNotExist e) {
            throw new RuntimeException(e);
        }

        inUser.afficher();


    }
}
