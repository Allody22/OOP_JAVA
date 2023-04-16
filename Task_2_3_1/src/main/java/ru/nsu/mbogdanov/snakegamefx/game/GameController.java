package ru.nsu.mbogdanov.snakegamefx.game;

import javafx.animation.Timeline;

import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import ru.nsu.mbogdanov.application.snakegame.SnakeGameController;

import static javafx.animation.Animation.Status.PAUSED;
import static javafx.animation.Animation.Status.RUNNING;
import static ru.nsu.mbogdanov.snakegame.game.GameState.DEFEAT;
import static ru.nsu.mbogdanov.snakegame.game.GameState.VICTORY;
import static ru.nsu.mbogdanov.snakegame.sprite.snake.Direction.DOWN;
import static ru.nsu.mbogdanov.snakegame.sprite.snake.Direction.LEFT;
import static ru.nsu.mbogdanov.snakegame.sprite.snake.Direction.RIGHT;
import static ru.nsu.mbogdanov.snakegame.sprite.snake.Direction.UP;


/**
 * The GameController class is responsible for managing user input and updating the Snake game.
 */
public class GameController {

    private final SnakeGameController controller;
    private final GameFX snakeGame;
    private final Timeline timeline;

    /**
     * Creates a GameController object with the specified SnakeGameController,
     * GameFX and Timeline objects.
     *
     * @param controller - SnakeGameController that manages the Snake game
     * @param snakeGame  - GameFX object that contains the Snake game
     * @param timeline   - Timeline object that manages the game loop
     */
    public GameController(SnakeGameController controller, GameFX snakeGame,
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
            case ESCAPE -> controller.openModalWindow();
        }
    }

    /**
     * Runs the main game loop by updating current game state,
     * score, and rendering the updated frame.
     *
     * @param frame the Group object that represents the frame to render
     */
    public void run(Group frame) {
        if (timeline.getStatus() == RUNNING && (snakeGame.getGameState() == DEFEAT
                || snakeGame.getGameState() == VICTORY)) {
            timeline.stop();
            controller.openModalWindow();
        }
        snakeGame.update();
        controller.updateScore();
        snakeGame.render(frame);
    }
}