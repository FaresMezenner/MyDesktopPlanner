package com.example.mydesktopplanner.View;

import com.example.mydesktopplanner.Main;
import com.example.mydesktopplanner.Models.ExceptionsPackage.ExceptionCollisionHorairesCreneau;
import com.example.mydesktopplanner.Models.ExceptionsPackage.ExceptionDureeInvalide;
import com.example.mydesktopplanner.Models.MyDesktopPlanner;
import com.example.mydesktopplanner.Models.Periode;
import com.example.mydesktopplanner.Models.Tache;
import com.example.mydesktopplanner.Models.Utilisateur;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
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
                Stage stage = new UnschduledTachesPopUp(unscheduledTaches);
                stage.show();
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

        public UnschduledTachesPopUp(ArrayList<Tache> taches) throws IOException {
            setScene(new Scene(FXMLLoader.load(Main.class.getResource("ListPopUp.fxml"))));
            ((Text) getScene().lookup("#title")).setText("Replanification");
            ((Text) getScene().lookup("#message")).setText("Les tâches suivantes n'ont pas pu être replanifiées :");
            ((ListView<Tache>)  getScene().lookup("#list")).getItems().addAll(taches);

            ((Button) getScene().lookup("#ok")).setText("replanifier");
            ((Button) getScene().lookup("#ok")).setOnAction(event -> {
                close();
                ArrayList<Tache> unscheduledTaches = null;
                try {
                    FileInputStream fIn = new FileInputStream("utilisateurTmp");
                    ObjectInputStream in = new ObjectInputStream(fIn);
                    MyDesktopPlanner.getInstance().setUtilisateur((Utilisateur) in.readObject());
                    fIn.close();
                    in.close();

                    unscheduledTaches = MyDesktopPlanner.getInstance().replannifierPeriode(periode, true);
                    MainView.getInstance().update();
                    Stage stage = new UnschduledTachesPopUp(unscheduledTaches);
                    stage.show();
                } catch (ExceptionDureeInvalide e) {
                    throw new RuntimeException(e);
                } catch (ExceptionCollisionHorairesCreneau e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            });

                ((Button) getScene().lookup("#annuler")).setOnAction(event -> {
                    close();

                    FileInputStream fIn = null;
                    try {
                        fIn = new FileInputStream("utilisateurTmp");
                        ObjectInputStream in = new ObjectInputStream(fIn);
                        MyDesktopPlanner.getInstance().setUtilisateur((Utilisateur) in.readObject());
                        fIn.close();
                        in.close();
                        MainView.getInstance().update();
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                });

        }
    }

}
