package com.example.mydesktopplanner.View;

import com.example.mydesktopplanner.Models.ExceptionsPackage.ExceptionCollisionHorairesCreneau;
import com.example.mydesktopplanner.Models.ExceptionsPackage.ExceptionDureeInvalide;
import com.example.mydesktopplanner.Models.MyDesktopPlanner;
import com.example.mydesktopplanner.Models.Periode;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class RightSidePannelPeriode {
    AnchorPane view;
    Periode periode;


    public RightSidePannelPeriode(Periode periode) throws IOException {
        this.view = FXMLLoader.load(com.example.mydesktopplanner.Main.class.getResource("RightSidePanelPeriode.fxml"));
        this.periode = periode;

        setViewValues();

        setControllers();


    }

    private void setControllers() {
        ((Button) view.lookup("#save")).setOnAction(event -> {
            periode.setNom(((TextField) view.lookup("#name")).getText());
            try {
                MainView.getInstance().update();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        //the delete button
        ((Button) view.lookup("#delete")).setOnAction(event -> {
            //deleting the periode from the list
            MyDesktopPlanner.getInstance().getPeriodes().remove(periode);
            //deleting the periode from the view
            ((AnchorPane) view.getParent()).getChildren().remove(view);
        });

        //replannifier button
        ((Button) view.lookup("#replannifier")).setOnAction(event -> {
            try {
                MyDesktopPlanner.getInstance().replannifierPeriode(periode, false);
                MainView.getInstance().update();
            } catch (ExceptionDureeInvalide e) {
                throw new RuntimeException(e);
            } catch (ExceptionCollisionHorairesCreneau e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });


    }

    private void setViewValues() {
        ((TextField) view.lookup("#name")).setText(periode.getNom());
        ((DatePicker) view.lookup("#debut")).setValue(periode.getDebut());
        ((DatePicker) view.lookup("#fin")).setValue(periode.getFin());
    }


    public AnchorPane getView() {
        return view;
    }
}
