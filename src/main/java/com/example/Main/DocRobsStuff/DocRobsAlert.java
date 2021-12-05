package com.example.Main.DocRobsStuff;

import javafx.scene.control.Alert;

public class DocRobsAlert {
    public void Alerts() {
    }

    public static void infoAlert(String title, String message, String... headerText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        if (headerText.length > 0) {
            alert.setHeaderText(headerText[0]);
        } else {
            alert.setHeaderText("");
        }

        alert.setContentText(message);
        alert.showAndWait();
    }
}
