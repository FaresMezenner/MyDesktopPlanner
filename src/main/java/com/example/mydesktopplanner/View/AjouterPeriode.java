package com.example.mydesktopplanner.View;

import com.example.mydesktopplanner.Models.ExceptionsPackage.ExceptionCollisionPeriode;
import com.example.mydesktopplanner.Models.ExceptionsPackage.ExceptionDateInvalide;
import com.example.mydesktopplanner.Models.MyDesktopPlanner;
import com.example.mydesktopplanner.Models.Periode;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AjouterPeriode implements Initializable {

    @FXML
    TextField nom;
    @FXML
    DatePicker debut, fin;


    public void setNumeric(TextField tf){
        tf.textProperty().addListener((observable, oldValue, newValue) -> {
            // Vérifie chaque caractère entré
            if (!newValue.matches("\\d*")) {
                tf.setText(newValue.replaceAll("[^\\d]", ""));
            }
    });}


    private boolean checkFields(){
        return !(nom.getText().isBlank() || debut.getValue() == null || fin.getValue() == null);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }



    public void ajouter() throws IOException {
        if (checkFields()){
            try {
                Periode periode = new Periode(
                                debut.getValue(),
                                fin.getValue(),
                                nom.getText()
                        );


                Stage stage = new TachesPeriodeListPopUp("Choisir les taches", "Choisissez les taches a plannifier dans cette periode",periode);
                stage.show();


            } catch (ExceptionDateInvalide e) {
                ErrorPopUpView.show("Erreur", e.getMessage());
            }

        } else {

            ErrorPopUpView.show("Erreur","Veuillez remplir tous tous les champs");
        }
    }

}
