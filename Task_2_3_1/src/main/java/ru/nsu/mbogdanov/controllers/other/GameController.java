package ru.nsu.mbogdanov.controllers.other;

import javafx.animation.Timeline;

import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import ru.nsu.mbogdanov.controllers.windows.SnakeGameController;
import ru.nsu.mbogdanov.model.ingame.graphics.GameGraphics;

import static javafx.animation.Animation.Status.PAUSED;
import static javafx.animation.Animation.Status.RUNNING;
import static ru.nsu.mbogdanov.model.constants.GameState.DEFEAT;
import static ru.nsu.mbogdanov.model.constants.GameState.VICTORY;
import static ru.nsu.mbogdanov.model.constants.Direction.DOWN;
import static ru.nsu.mbogdanov.model.constants.Direction.LEFT;
import static ru.nsu.mbogdanov.model.constants.Direction.RIGHT;
import static ru.nsu.mbogdanov.model.constants.Direction.UP;


/**
 * The GameController class is responsible for managing user input and updating the Snake game.
 */
public class GameController {

    private final SnakeGameController controller;
    private final GameGraphics snakeGame;
    private final Timeline timeline;

    /**
     * Creates a GameController object with the specified SnakeGameController,
     * GameFX and Timeline objects.
     *
     * @param controller - SnakeGameController that manages the Snake game
     * @param snakeGame  - GameFX object that contains the Snake game
     * @param timeline   - Timeline object that manages the game loop
     */
    public GameController(SnakeGameController controller, GameGraphics snakeGame,
                          Timeline timeline) {
        this.controller = controller;
        this.snakeGame = snakeGame;
        this.timeline = timeline;
    }

    /**
     * Handles the given KeyEvent by updating snakes direction and triggering the
     * appropriate action depending on which key is pressed.
     *
     * @param event the KeyEvent to handle
     */
    public void handle(KeyEvent event) {
        KeyCode code = event.getCode();
        if (timeline.getStatus() == PAUSED) {
            timeline.play();
            return;
        }
        switch (code) {
            case RIGHT -> snakeGame.setSnakeDirection(RIGHT);
            case LEFT -> snakeGame.setSnakeDirection(LEFT);
            case UP -> snakeGame.setSnakeDirection(UP);
            case DOWN -> snakeGame.setSnakeDirection(DOWN);
            case ESCAPE -> controller.openPauseWindow();
            default -> {
            }
        }
    }

    /**
     * Runs the main game loop by updating current game state,
     * score, and rendering the updated frame.
     *
     * @param frame - the Group object that represents the frame to render
     */
    public void run(Group frame) {
        if (timeline.getStatus() == RUNNING && (snakeGame.getGameState() == DEFEAT
                || snakeGame.getGameState() == VICTORY)) {
            timeline.stop();
            controller.openPauseWindow();
        }
        snakeGame.update();
        controller.updateScore();
        snakeGame.render(frame);
    }
}