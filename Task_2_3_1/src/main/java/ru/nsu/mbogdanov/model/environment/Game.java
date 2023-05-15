package ru.nsu.mbogdanov.model.environment;

import lombok.Getter;
import ru.nsu.mbogdanov.model.constants.GameState;
import ru.nsu.mbogdanov.model.ingame.objects.Board;
import ru.nsu.mbogdanov.model.constants.Direction;
import ru.nsu.mbogdanov.model.ingame.objects.Fruit;
import ru.nsu.mbogdanov.model.ingame.objects.Snake;
import ru.nsu.mbogdanov.model.ingame.objects.Sprite;
import ru.nsu.mbogdanov.model.ingame.objects.Wall;

import java.util.List;

import static ru.nsu.mbogdanov.model.constants.GameState.DEFEAT;
import static ru.nsu.mbogdanov.model.constants.GameState.PLAY;
import static ru.nsu.mbogdanov.model.constants.GameState.VICTORY;


/**
 * This class represents the Snake game objects.
 * That is a board, a snake, food, and walls.
 * It manages the game state, scoring, snake direction, and game update.
 */
public class Game {
    private final Configuration configuration;
    private final Board board;
    private final Snake snake;
    private final List<Fruit> food;
    private final List<Wall> walls;
    @Getter
    private GameState gameState;

    /**
     * Constructs a new Game object with a specified configuration, board, snake, food, and walls.
     *
     * @param configuration - the game configuration
     * @param board         - the game board
     * @param snake         - the snake object
     * @param food          - the list of food objects
     * @param walls         - the list of wall objects
     */
    public Game(Configuration configuration, Board board, Snake snake,
                List<Fruit> food, List<Wall> walls) {
        this.configuration = configuration;
        this.board = board;
        this.snake = snake;
        this.food = food;
        this.walls = walls;
        this.gameState = PLAY;
    }

    /**
     * Returns the current score of the game, based on the length of the snake.
     *
     * @return the current score of the game
     */
    public int getScore() {
        return snake.getLength() - 3;
    }

    /**
     * Sets the direction of the snake to the specified direction.
     *
     * @param direction - the direction to set the snake
     */
    public void setSnakeDirection(Direction direction) {
        snake.setDirection(direction);
    }

    /**
     * Updates the game state.
     * It is based on the current state of the game and the snake and wall intersection.
     */
    private void updateGameState() {
        if (!snake.intersects(board)
                || snake.intersects(snake)
                || walls.stream().anyMatch(snake::intersects)) {
            gameState = DEFEAT;
        } else if (getScore() == configuration.getMaximumScore()) {
            gameState = VICTORY;
        } else {
            gameState = PLAY;
        }
    }

    /**
     * Updates the position of the specified sprite, such as food or walls,
     * until it no longer intersects with the snake or other sprites.
     *
     * @param sprite - the sprite to update
     */
    private void updateSprite(Sprite sprite) {
        do {
            sprite.update(configuration.getRowsNumber(), configuration.getColumnsNumber());

        } while (snake.intersects(sprite)
                || food.stream().anyMatch(other -> other != sprite && other.intersects(sprite))
                || walls.stream().anyMatch(other -> other != sprite && other.intersects(sprite)));
    }

    /**
     * Checks if the snake intersects with any food object,
     * and grows the snake and updates the food object if it does.
     */
    private void eatFood() {
        for (Fruit fruit : food) {
            if (snake.intersects(fruit)) {
                snake.grow();
                updateSprite(fruit);
            }
        }
    }


    /**
     * Starts the game by placing the snake and updating the wall and food objects.
     */
    public void start() {
        snake.start(snake.getLength() * configuration.getSquareSize(),
                (configuration.getColumnsNumber() >> 1) * configuration.getSquareSize());
        walls.forEach(this::updateSprite);
        food.forEach(this::updateSprite);
    }

    /**
     * Updates the game by checking for the intersection of the snake and the food,
     * updating the snake, and updating the game state.
     */
    public void update() {
        if (gameState == PLAY) {
            eatFood();
            snake.update(configuration.getSquareSize(), configuration.getSquareSize());
            updateGameState();
        }
    }
}
