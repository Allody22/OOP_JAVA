package ru.nsu.mbogdanov.application.snakegame;

import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import ru.nsu.mbogdanov.application.configuration.Configuration;
import ru.nsu.mbogdanov.application.modalwindow.PauseMenu;
import ru.nsu.mbogdanov.snakegame.game.Game;

import static ru.nsu.mbogdanov.snakegame.game.GameState.DEFEAT;
import static ru.nsu.mbogdanov.snakegame.game.GameState.VICTORY;

/**
 * Controller for the game itself.
 * We need this for the connection with the user and his movements.
 */
public class SnakeGameController {

    /**
     * The pause modal window.
     */
    private PauseMenu pauseMenu;

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
        this.pauseMenu = new PauseMenu(mainStage, configuration, timeline);
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
     * If the current game state is not failure or victory, the pause modal window with the label "PAUSE" will open.
     * If the current game state is failure or victory, the pause modal window with the corresponding state will open.
     */
    @FXML
    public void openModalWindow() {
        timeline.pause();
        if (game.getGameState() != DEFEAT && game.getGameState() != VICTORY) {
            pauseMenu.open("PAUSE");
            return;
        }
        pauseMenu.open(game.getGameState().toString());
    }
}
