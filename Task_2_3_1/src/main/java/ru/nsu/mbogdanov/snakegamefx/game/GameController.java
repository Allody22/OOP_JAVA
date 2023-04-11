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


public class GameController {
    private final SnakeGameController controller;
    private final GameFX snakeGame;
    private final Timeline timeline;

    public GameController(SnakeGameController controller, GameFX snakeGame, Timeline timeline) {
        this.controller = controller;
        this.snakeGame = snakeGame;
        this.timeline = timeline;
    }

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

    public void run(Group frame) {
        if (timeline.getStatus() == RUNNING && (snakeGame.getGameState() == DEFEAT || snakeGame.getGameState() == VICTORY)) {
            timeline.stop();
            controller.openModalWindow();
        }
        snakeGame.update();
        controller.updateScore();
        snakeGame.render(frame);
    }
}
