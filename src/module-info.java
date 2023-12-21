/**
 * Ce module définit les dépendances nécessaires et les exportations de packages.
 */

module ulb.infof307.g02 {
    requires javafx.controls;
    requires javafx.fxml;
    requires jlatexmath;
    requires java.desktop;
    requires javafx.graphics;
    requires javafx.base;
    requires javafx.swing;


    exports ulb.infof307.g02.views;
    opens ulb.infof307.g02.views to javafx.fxml;
    exports ulb.infof307.g02.controllers;
    opens ulb.infof307.g02.controllers to javafx.fxml;
    exports ulb.infof307.g02.models;
    opens ulb.infof307.g02.models to javafx.fxml;
}