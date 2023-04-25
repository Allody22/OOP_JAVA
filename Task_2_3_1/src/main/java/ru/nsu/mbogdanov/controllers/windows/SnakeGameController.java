package ru.nsu.mbogdanov.controllers.windows;

import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import ru.nsu.mbogdanov.model.environment.Configuration;
import ru.nsu.mbogdanov.model.environment.Game;
import ru.nsu.mbogdanov.windows.EndGameWindow;
import ru.nsu.mbogdanov.windows.PauseWindow;

import static ru.nsu.mbogdanov.model.constants.GameState.DEFEAT;
import static ru.nsu.mbogdanov.model.constants.GameState.VICTORY;

/**
 * Controller for the game itself.
 * We need this for the connection with the user and his movements.
 */
public class SnakeGameController {

    /**
     * The pause modal window.
     */
    private PauseWindow pauseWindow;

    /**
     * Menu after the win or loose.
     */
    private EndGameWindow endGameWindow;

    /**
     * The game timeline that tracks each game frame.
     */
    private Timeline timeline;

    /**
     * The instance of the game.
     */
    private Game game;

    /**
     * The UI element to display the game score.
     */
    @FXML
    private Label score;

    /**
     * Initializes the controller.
     *
     * @param mainStage     - the main application window
     * @param configuration - the game configuration
     * @param timeline      - the game timeline that tracks each game frame
     * @param game          - the instance of the game
     */
    public void initialize(Stage mainStage, Configuration configuration,
                           Timeline timeline, Game game) {
        this.pauseWindow = new PauseWindow(mainStage, configuration, timeline);
        this.endGameWindow = new EndGameWindow(mainStage, configuration, timeline);
        this.game = game;
        this.timeline = timeline;
    }

    /**
     * Updates the game score based on the current game state.
     */
    public void updateScore() {
        score.setText("Score: " + game.getScore());
    }

    /**
     * The event handler for opening the pause modal window.
     * When called, the game will be paused, and the pause modal window will open.
     * If the current game state is failure or victory,
     * the pause modal window with the corresponding state will open.
     * Else the pause modal window with the label "PAUSE" will open.
     */
    @FXML
    public void openPauseWindow() {
        timeline.pause();
        if (game.getGameState() != DEFEAT && game.getGameState() != VICTORY) {
            pauseWindow.open();
            return;
        }
        endGameWindow.open();
    }
}
