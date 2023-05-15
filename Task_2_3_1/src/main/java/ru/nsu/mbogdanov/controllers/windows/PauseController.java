package ru.nsu.mbogdanov.controllers.windows;

import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import ru.nsu.mbogdanov.model.environment.Configuration;
import ru.nsu.mbogdanov.windows.Menu;
import ru.nsu.mbogdanov.windows.SnakeBoardWindow;

/**
 * Controller for pause window.
 * It contains main buttons and methods.
 */
public class PauseController {

    /**
     * The Stage on which the game is displayed.
     */
    private Stage stage;

    /**
     * Configuration object for the game.
     */
    private Configuration configuration;

    /**
     * Timeline object for the game.
     */
    private Timeline timeline;

    /**
     * The stack pane for the modal window.
     */
    @FXML
    StackPane pauseWindow;

    /**
     * Initialization method for the controller.
     *
     * @param mainStage     - the Stage on which the game is displayed
     * @param configuration - Configuration object for the game
     * @param timeline      - Timeline object for the game
     */
    public void initialize(Stage mainStage, Configuration configuration, Timeline timeline) {
        this.stage = mainStage;
        this.configuration = configuration;
        this.timeline = timeline;
    }

    /**
     * Method that closes the modal window for the pause menu.
     */
    void closeModalWindow() {
        Stage stage = (Stage) pauseWindow.getScene().getWindow();
        stage.close();
    }

    /**
     * Method that restarts the game when the restart button is clicked on the pause menu.
     */
    @FXML
    private void restartGame() {
        SnakeBoardWindow snakeBoardWindow = new SnakeBoardWindow(configuration);
        snakeBoardWindow.setStage(stage);
        closeModalWindow();
    }

    /**
     * Method that continues the game when the continue button is clicked on the pause menu.
     */
    @FXML
    private void continueGame() {
        timeline.play();
        closeModalWindow();
    }

    /**
     * Method that opens the game menu when the menu button is clicked on the pause menu.
     */
    @FXML
    private void openMenu() {
        Menu menu = new Menu(configuration);
        menu.setStage(stage);
        timeline.stop();
        closeModalWindow();
    }
}
