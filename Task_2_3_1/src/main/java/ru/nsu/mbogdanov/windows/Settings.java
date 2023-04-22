package ru.nsu.mbogdanov.windows;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.nsu.mbogdanov.controllers.windows.SettingsController;
import ru.nsu.mbogdanov.model.environment.Configuration;

import java.io.IOException;

/**
 * This class represents the settings window for the snake game.
 */
public class Settings {
    private final Configuration configuration;
    private SettingsController controller;
    private Scene scene;

    /**
     * Constructor that initializes a settings scene and a controller.
     *
     * @param configuration - configuration of the current snake game.
     */
    public Settings(Configuration configuration) {
        this.configuration = configuration;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/nsu/mbogdanov/fxml/settings.fxml"));
        try {
            Parent root = loader.load();
            controller = loader.getController();
            scene = new Scene(root);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Allows to open settings in the specified window.
     *
     * @param stage - the window in which it is necessary to open settings.
     */
    public void setStage(Stage stage) {
        if (scene != null && controller != null) {
            controller.initialize(stage, configuration);
            stage.setScene(scene);
        }
    }
}
