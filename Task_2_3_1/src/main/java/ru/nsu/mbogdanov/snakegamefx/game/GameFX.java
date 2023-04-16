package ru.nsu.mbogdanov.snakegamefx.game;

import javafx.scene.Group;
import ru.nsu.mbogdanov.application.configuration.Configuration;
import ru.nsu.mbogdanov.snakegame.game.Game;
import ru.nsu.mbogdanov.snakegame.sprite.board.Board;
import ru.nsu.mbogdanov.snakegame.sprite.fruit.Fruit;
import ru.nsu.mbogdanov.snakegame.sprite.snake.Snake;
import ru.nsu.mbogdanov.snakegame.sprite.wall.Wall;

import java.util.List;

import static ru.nsu.mbogdanov.snakegame.game.GameState.PLAY;


/**
 * The GameFX class extends the base Game class and is responsible for rendering the
 * game board, Snake, fruit, and walls using JavaFX graphical components.
 */
public class GameFX extends Game {

    private final Board board;
    private final Snake snake;
    private final List<Fruit> food;
    private final List<Wall> walls;

    /**
     * Creates a GameFX object with the specified configuration, board, snake, fruit, and walls.
     *
     * @param configuration - configuration for the game
     * @param board         - board to render
     * @param snake         - snake to render
     * @param food          - fruits to render
     * @param walls         - walls to render
     */
    public GameFX(Configuration configuration, Board board,
                  Snake snake, List<Fruit> food, List<Wall> walls) {
        super(configuration, board, snake, food, walls);
        this.board = board;
        this.snake = snake;
        this.food = food;
        this.walls = walls;
    }

    /**
     * Renders all game objects using JavaFX graphical components.
     *
     * @param frame the group object that represents the frame to render
     */
    public void render(Group frame) {
        if (getGameState() == PLAY) {
            frame.getChildren().clear();
            board.render(frame);
            walls.forEach(wall -> wall.render(frame));
            food.forEach(fruit -> fruit.render(frame));
            snake.render(frame);
        }
    }
}