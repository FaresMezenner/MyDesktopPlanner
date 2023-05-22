module com.example.mydesktopplanner {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    opens com.example.mydesktopplanner to javafx.fxml;
    opens com.example.mydesktopplanner.Authentification to javafx.fxml;
    opens com.example.mydesktopplanner.View to javafx.fxml;
    exports com.example.mydesktopplanner;
    exports com.example.mydesktopplanner.Models;
    exports com.example.mydesktopplanner.Control;
    exports com.example.mydesktopplanner.View;

}