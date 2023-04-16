package ru.nsu.mbogdanov.application.modalwindow;

import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import ru.nsu.mbogdanov.application.configuration.Configuration;
import ru.nsu.mbogdanov.application.menu.Menu;
import ru.nsu.mbogdanov.application.snakegame.SnakeGame;


/**
 * Controller class for the pause menu.
 */
public class PauseMenuController {

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
    StackPane modalWindow;

    /**
     * The label for the header of the pause menu.
     */
    @FXML
    private Label header;

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
     * Method that sets the header text for the pause menu.
     *
     * @param text the header text to be set
     */
    public void setHeader(String text) {
        header.setText(text);
    }

    /**
     * Method that closes the modal window for the pause menu.
     */
    private void closeModalWindow() {
        Stage stage = (Stage) modalWindow.getScene().getWindow();
        stage.close();
    }

    /**
     * Method that restarts the game when the restart button is clicked on the pause menu.
     */
    @FXML
    private void restartGame() {
        SnakeGame snakeGame = new SnakeGame(configuration);
        snakeGame.setStage(stage);
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