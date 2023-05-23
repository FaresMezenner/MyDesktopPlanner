package com.example.mydesktopplanner.View;

import com.example.mydesktopplanner.Main;
import com.example.mydesktopplanner.Models.ExceptionsPackage.ExceptionCollisionHorairesCreneau;
import com.example.mydesktopplanner.Models.ExceptionsPackage.ExceptionDureeInvalide;
import com.example.mydesktopplanner.Models.MyDesktopPlanner;
import com.example.mydesktopplanner.Models.Periode;
import com.example.mydesktopplanner.Models.Tache;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

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
            MyDesktopPlanner.getInstance().getPeriodes().remove(periode.getDebut());
            //deleting the periode from the view
            try {
                MainView.getInstance().emptyRightSidePannel();
                MainView.getInstance().update();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        //replannifier button
        ((Button) view.lookup("#replannifier")).setOnAction(event -> {
            try {

                FileOutputStream fOut = new FileOutputStream("utilisateurTmp");
                ObjectOutputStream out = new ObjectOutputStream(fOut);
                out.writeObject(MyDesktopPlanner.getInstance().getUtilisateur());
                fOut.flush();
                out.flush();
                fOut.close();
                out.close();

                ArrayList<Tache> unscheduledTaches =  MyDesktopPlanner.getInstance().replannifierPeriode(periode, false);
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



    private class UnschduledTachesPopUp extends Stage {

        public UnschduledTachesPopUp() throws IOException {
            setScene(new Scene(FXMLLoader.load(Main.class.getResource("ListPopUp.fxml"))));
            ((Text) view.lookup("#text")).setText("Les taches suivantes n'ont pas pu être replannifiées");

        }
    }

}
