package com.example.mydesktopplanner.View;

import com.example.mydesktopplanner.Models.Creneau;
import com.example.mydesktopplanner.Models.MyDesktopPlanner;
import com.example.mydesktopplanner.Models.Projet;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.util.TreeMap;

public class AjouterProjet implements Initializable {


    @FXML
    TextField nom;
    @FXML
    TextArea desc;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    private boolean checkFields(){
        return !(nom.getText().isBlank() || desc.getText().isBlank());
    }

    public void ajouter() throws IOException {
        if (checkFields()) {
            MyDesktopPlanner.getInstance().ajouterProjet(
                    new Projet(
                            nom.getText(),
                            desc.getText(),
                            new TreeMap<LocalDateTime, Creneau>()
                    )
            );
            MainView.getInstance().update();

        } else {
            ErrorPopUpView.show("Erreur", "Veuillez remplir tous les champs");
        }

    }
}
