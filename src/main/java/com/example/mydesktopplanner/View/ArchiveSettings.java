package com.example.mydesktopplanner.View;

import com.example.mydesktopplanner.Main;
import com.example.mydesktopplanner.Models.*;
import com.example.mydesktopplanner.Models.ExceptionsPackage.ExceptionCollisionHorairesCreneau;
import com.example.mydesktopplanner.Models.ExceptionsPackage.ExceptionDateInvalide;
import com.example.mydesktopplanner.Models.ExceptionsPackage.ExceptionDureeInvalide;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.TreeMap;

public class ArchiveSettings extends Stage {


    @FXML
    private ListView<String> archivesList;

    @FXML
    private Button afficherArchiveBtn;

    @FXML
    private Button retour;

    @FXML
    private Button archiver;

    MyDesktopPlanner myDesktopPlanner = MyDesktopPlanner.getInstance();

    Archive archive1 = new Archive(new int[]{0, 1, 0},0,1);
    Archive archive2 = new Archive(new int[]{0, 0, 0},0,0);

    public static ArrayList<Archive> archives = new ArrayList<Archive>();

    public void retour_func(Stage stage){
        ProfilePopUpView profilePopUpView = null;
        try {
            profilePopUpView = new ProfilePopUpView();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = profilePopUpView.getScene();
        stage.setTitle("My desktop planner");
        stage.setScene(scene);
    }

    public void initialize() {
        MyDesktopPlanner myDesktopPlanner = MyDesktopPlanner.getInstance();
        TreeMap<LocalDateTime, Archive> archives_map = myDesktopPlanner.getUtilisateur().getArchives();

        // --------------------------------------------------------------------------------------------------
        retour.setOnAction(event -> {
            retour_func((Stage) retour.getScene().getWindow());
        });

        archiver.setOnAction(event -> {
            Utilisateur utilisateur = myDesktopPlanner.getUtilisateur();
            myDesktopPlanner.archiver();
            retour_func((Stage) retour.getScene().getWindow());
        });


        // --------------------------------------------------------------------------------------------------

        // turn archives_map into an ArrayList
        archives = new ArrayList<Archive>();
        for (LocalDateTime key : archives_map.keySet()) {
            archives.add(archives_map.get(key));
        }
        System.out.println(archives);

        afficherArchives(archives);
        // Ajouter un gestionnaire d'événements au bouton
        afficherArchiveBtn.setOnAction(event -> {
            // Obtenir l'élément sélectionné dans la ListView
            String selectedValue = archivesList.getSelectionModel().getSelectedItem();

            if (selectedValue != null) {

                int retrievedIndex = Integer.parseInt(selectedValue.split(" - ")[0]);
                System.out.println("Index récupéré : " + retrievedIndex);

// ------------------------------------------ Here , we got back our value from the list -------------------------------
                Archive archiveAffichage = archives.get(retrievedIndex);
                ArchiveDisplay.archive = archiveAffichage;
                System.out.println(archiveAffichage);

                try {
                    // Charger le fichier FXML de la nouvelle scène
                    FXMLLoader loader = new FXMLLoader(Main.class.getResource("ArchiveDisplay.fxml"));

                    Parent root = loader.load();

                    ArchiveDisplay archiveDisplay = loader.getController();



                    // Créer une nouvelle scène avec la racine chargée
                    Scene nouvelleScene = new Scene(root);

                    // Obtenir la fenêtre principale (stage) actuelle
                    Stage stageActuel = (Stage) afficherArchiveBtn.getScene().getWindow();

                    // Définir la nouvelle scène sur la fenêtre principale

                    stageActuel.setScene(nouvelleScene);
                } catch (IOException e) {
                    e.printStackTrace();
                }



// ------------------------------------------WORK UP  ------------------------------------------------------------------
            } else {
                // Aucun élément sélectionné, afficher un message d'erreur
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Aucun élément sélectionné !");
                alert.showAndWait();
            }
        });


    }



    // Autres méthodes et logique de votre contrôleur

    public void afficherArchives(ArrayList<Archive> archives){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        ArrayList<String> listeArchivesArray = new ArrayList<>();
        for (int i = 0; i < archives.size(); i++) {
            LocalDateTime date = archives.get(i).getDateArchivage();
            String formattedArchive = String.format("%d - Archive du %s", i,date.format(formatter));
            listeArchivesArray.add(formattedArchive);
        }
        // Ajouter les éléments de l'ArrayList à la ListView
        archivesList.getItems().addAll(listeArchivesArray);
    }


    public void changeButton(Button button,String file){
        button.setOnAction(event -> {
            try {
                // Charger le fichier FXML de la nouvelle scène
                FXMLLoader loader = new FXMLLoader(Main.class.getResource(file));

                Parent root = loader.load();


                // Créer une nouvelle scène avec la racine chargée
                Scene nouvelleScene = new Scene(root);

                // Obtenir la fenêtre principale (stage) actuelle
                Stage stageActuel = (Stage) button.getScene().getWindow();

                // Définir la nouvelle scène sur la fenêtre principale

                stageActuel.setScene(nouvelleScene);
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
    }


}
