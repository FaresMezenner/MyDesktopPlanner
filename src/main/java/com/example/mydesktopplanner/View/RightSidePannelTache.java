package com.example.mydesktopplanner.View;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class RightSidePannelTache {
    AnchorPane view;

    public RightSidePannelTache() throws IOException {
        this.view = FXMLLoader.load(com.example.mydesktopplanner.Main.class.getResource("RightSidePanelTaches.fxml"));
    }

    public AnchorPane getView() {
        return view;
    }
}
