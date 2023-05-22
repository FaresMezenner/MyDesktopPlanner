package com.example.mydesktopplanner.View;

import com.example.mydesktopplanner.Models.Badge;
import com.example.mydesktopplanner.Models.MyDesktopPlanner;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ProfilePopUpView extends Stage {


    public ProfilePopUpView() throws IOException {
        setScene(FXMLLoader.load(com.example.mydesktopplanner.Main.class.getResource("ProfilePopUp.fxml")));

        setValues();
    }

    private void setValues() {
        getScene().lookup("#user").setAccessibleText(MyDesktopPlanner.getInstance().getUtilisateur().getPseudo());
        getScene().lookup("#good").setAccessibleText(
                String.valueOf(MyDesktopPlanner.getInstance().getUtilisateur().getBadges()[Badge.GOOD.ordinal()])
        );
        getScene().lookup("#verygood").setAccessibleText(
                String.valueOf(MyDesktopPlanner.getInstance().getUtilisateur().getBadges()[Badge.VERYGOOD.ordinal()])
        );
        getScene().lookup("#excellent").setAccessibleText(
                String.valueOf(MyDesktopPlanner.getInstance().getUtilisateur().getBadges()[Badge.EXCELLENT.ordinal()])
        );

    }

}
