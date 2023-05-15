package ru.nsu.mbogdanov.model.ingame.graphics;

import javafx.scene.Group;
import ru.nsu.mbogdanov.model.environment.Configuration;
import ru.nsu.mbogdanov.model.environment.Game;
import ru.nsu.mbogdanov.model.ingame.objects.Board;
import ru.nsu.mbogdanov.model.ingame.objects.Fruit;
import ru.nsu.mbogdanov.model.ingame.objects.Snake;
import ru.nsu.mbogdanov.model.ingame.objects.Wall;

import java.util.List;

import static ru.nsu.mbogdanov.model.constants.GameState.PLAY;


/**
 * The GameFX class. It
 * extends the base Game class and is responsible for rendering the
 * game board, Snake, fruit, and walls using JavaFX graphical components.
 */
public class GameGraphics extends Game {

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
    public GameGraphics(Configuration configuration, Board board,
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