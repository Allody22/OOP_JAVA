package ru.nsu.mbogdanov.snakegame.game;

import lombok.Getter;
import ru.nsu.mbogdanov.application.configuration.Configuration;
import ru.nsu.mbogdanov.snakegame.sprite.Sprite;
import ru.nsu.mbogdanov.snakegame.sprite.board.Board;
import ru.nsu.mbogdanov.snakegame.sprite.fruit.Fruit;
import ru.nsu.mbogdanov.snakegame.sprite.snake.Direction;
import ru.nsu.mbogdanov.snakegame.sprite.snake.Snake;

import java.util.List;

import static ru.nsu.mbogdanov.snakegame.game.GameState.DEFEAT;
import static ru.nsu.mbogdanov.snakegame.game.GameState.PLAY;
import static ru.nsu.mbogdanov.snakegame.game.GameState.VICTORY;


public class Game {
    private final Configuration configuration;
    private final Board board;
    private final Snake snake;
    private final List<Fruit> food;
    @Getter
    private GameState gameState;

    public Game(Configuration configuration, Board board, Snake snake, List<Fruit> food) {
        this.configuration = configuration;
        this.board = board;
        this.snake = snake;
        this.food = food;
        this.gameState = PLAY;
    }

    public int getScore() {
        return snake.getLength() - 3;
    }

    public void setSnakeDirection(Direction direction) {
        snake.setDirection(direction);
    }

    private void updateGameState() {
        if (!snake.intersects(board) || snake.intersects(snake)) {
            gameState = DEFEAT;
        } else if (getScore() == configuration.getMaximumScore()) {
            gameState = VICTORY;
        } else {
            gameState = PLAY;
        }
    }

    private void updateSprite(Sprite sprite) {
        do {
            sprite.update(configuration.getRowsNumber(), configuration.getColumnsNumber());
        } while (snake.intersects(sprite) ||
                food.stream().anyMatch(other -> other != sprite && other.intersects(sprite)));
    }

    private void eatFood() {
        for (Fruit fruit : food) {
            if (snake.intersects(fruit)) {
                snake.grow();
                updateSprite(fruit);
            }
        }
    }


    public void start() {
        snake.start(snake.getLength() * configuration.getSquareSize(),
                (configuration.getColumnsNumber() >> 1) * configuration.getSquareSize());
        food.forEach(this::updateSprite);
    }

    public void update() {
        if (gameState == PLAY) {
            eatFood();
            snake.update(configuration.getSquareSize(), configuration.getSquareSize());
            updateGameState();
        }
    }
}
