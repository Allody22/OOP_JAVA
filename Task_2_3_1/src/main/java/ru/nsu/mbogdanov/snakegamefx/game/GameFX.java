package ru.nsu.mbogdanov.snakegamefx.game;

import javafx.scene.Group;
import ru.nsu.mbogdanov.application.configuration.Configuration;
import ru.nsu.mbogdanov.snakegame.game.Game;
import ru.nsu.mbogdanov.snakegame.sprite.board.Board;
import ru.nsu.mbogdanov.snakegame.sprite.fruit.Fruit;
import ru.nsu.mbogdanov.snakegame.sprite.snake.Snake;

import java.util.List;

import static ru.nsu.mbogdanov.snakegame.game.GameState.PLAY;


public class GameFX extends Game {
    private final Board board;
    private final Snake snake;
    private final List<Fruit> food;

    public GameFX(Configuration configuration, Board board, Snake snake, List<Fruit> food) {
        super(configuration, board, snake, food);
        this.board = board;
        this.snake = snake;
        this.food = food;
    }


    public void render(Group frame) {
        if (getGameState() == PLAY) {
            frame.getChildren().clear();
            board.render(frame);
            food.forEach(fruit -> fruit.render(frame));
            snake.render(frame);
        }
    }
}
