package ru.nsu.mbogdanov.application.modalwindow;

import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.nsu.mbogdanov.application.configuration.Configuration;

import java.io.IOException;


public class PauseMenu {
    private Stage pauseMenuStage;
    private PauseMenuController controller;
    public PauseMenu(Stage stage, Configuration configuration, Timeline timeline) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/nsu/mbogdanov/fxml/modalWindow.fxml"));
        try {
            Parent root = loader.load();
            controller = loader.getController();
            controller.initialize(stage, configuration, timeline);
            pauseMenuStage = new Stage();
            pauseMenuStage.setScene(new Scene(root));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void open(String header) {
        if (controller != null && pauseMenuStage != null && !pauseMenuStage.isShowing()) {
            controller.setHeader(header);
            pauseMenuStage.show();
        }
    }
}

