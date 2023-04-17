package ru.nsu.mbogdanov.windows;

import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.nsu.mbogdanov.controllers.windows.PauseMenuController;
import ru.nsu.mbogdanov.model.environment.Configuration;

import java.io.IOException;


/**
 * Pause menu class.
 */
public class PauseMenu {

    /**
     * The Stage object for the pause menu.
     */
    private Stage pauseMenuStage;

    /**
     * The controller for the pause menu.
     */
    private PauseMenuController controller;

    /**
     * Constructor for the pause menu that creates an object and loads the FXML file.
     *
     * @param stage         - the Stage on which the pause menu will be displayed
     * @param configuration - Configuration object for the game
     * @param timeline      - Timeline object for the game
     */
    public PauseMenu(Stage stage, Configuration configuration, Timeline timeline) {
        FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("/ru/nsu/mbogdanov/fxml/modalWindow.fxml"));
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
     *
     * @param header the header text for the pause menu
     */
    public void open(String header) {
        if (controller != null && pauseMenuStage != null && !pauseMenuStage.isShowing()) {
            controller.setHeader(header);
            pauseMenuStage.show();
        }
    }
}

