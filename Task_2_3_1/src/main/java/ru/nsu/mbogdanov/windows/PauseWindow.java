package ru.nsu.mbogdanov.windows;

import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.nsu.mbogdanov.controllers.windows.PauseController;
import ru.nsu.mbogdanov.model.environment.Configuration;

import java.io.IOException;


/**
 * Pause menu class.
 */
public class PauseWindow {

    /**
     * The Stage object for the pause menu.
     */
    private Stage pauseMenuStage;

    /**
     * The controller for the pause menu.
     */
    private PauseController controller;

    /**
     * Constructor for the pause menu that creates an object and loads the FXML file.
     *
     * @param stage         - the Stage on which the pause menu will be displayed
     * @param configuration - Configuration object for the game
     * @param timeline      - Timeline object for the game
     */
    public PauseWindow(Stage stage, Configuration configuration, Timeline timeline) {
        FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("/ru/nsu/mbogdanov/fxml/pauseWindow.fxml"));
        try {
            Parent root = loader.load();
            pauseMenuStage = new Stage();
            pauseMenuStage.setScene(new Scene(root));
            controller = loader.getController();
            controller.initialize(stage, configuration, timeline);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Method that opens the pause menu.
     */
    public void open() {
        if (controller != null && pauseMenuStage != null && !pauseMenuStage.isShowing()) {
            pauseMenuStage.show();
        }
    }
}

