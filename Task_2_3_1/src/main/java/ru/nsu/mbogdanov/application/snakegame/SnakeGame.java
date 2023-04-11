package ru.nsu.mbogdanov.application.snakegame;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import ru.nsu.mbogdanov.application.configuration.Configuration;
import ru.nsu.mbogdanov.snakegame.sprite.board.Board;
import ru.nsu.mbogdanov.snakegame.sprite.fruit.Fruit;
import ru.nsu.mbogdanov.snakegame.sprite.snake.Snake;
import ru.nsu.mbogdanov.snakegamefx.game.GameController;
import ru.nsu.mbogdanov.snakegamefx.game.GameFX;
import ru.nsu.mbogdanov.snakegamefx.proccesingofimages.ImageProcessor;
import ru.nsu.mbogdanov.snakegamefx.sprite.BoardFX;
import ru.nsu.mbogdanov.snakegamefx.sprite.FruitFX;
import ru.nsu.mbogdanov.snakegamefx.sprite.SnakeFX;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SnakeGame {
    private Stage stage;
    private final Configuration configuration;
    private Map<String, Image> images;
    private Board board;
    private Snake snake;
    private List<Fruit> food;
    private GameFX snakeGame;
    private Group frame;
    private GameController gameController;
    private Scene scene;
    private Timeline timeline;

    public SnakeGame(Configuration configuration) {
        this.configuration = configuration;
        setImages();
        setSnakeGame();
    }

    private void setImages() {
        this.images = new HashMap<>();
        images.put("board", new Image(String.valueOf(getClass().getResource("/ru/nsu/mbogdanov/images/board/board2.png"))));
        images.put("head", new Image(String.valueOf(getClass().getResource("/ru/nsu/mbogdanov/images/snake/head.png"))));
        images.put("rotated", new Image(String.valueOf(getClass().getResource("/ru/nsu/mbogdanov/images/snake/rotated.png"))));
        images.put("straight", new Image(String.valueOf(getClass().getResource("/ru/nsu/mbogdanov/images/snake/straight.png"))));
        images.put("tail", new Image(String.valueOf(getClass().getResource("/ru/nsu/mbogdanov/images/snake/tail.png"))));
        images.put("apple", new Image(String.valueOf(getClass().getResource("/ru/nsu/mbogdanov/images/fruit/apple.png"))));
    }

    private void setBoard() {
        double width = configuration.getSquareSize() * configuration.getRowsNumber();
        double height = configuration.getSquareSize() * configuration.getColumnsNumber();
        ImageProcessor imageProcessor = new ImageProcessor(width, height, images.get("board"));
        BoardFX board = new BoardFX(width, height);
        board.setSkin(imageProcessor);
        this.board = board;
    }

    private void setSnake() {
        ImageProcessor head = new ImageProcessor(configuration.getSquareSize(), configuration.getSquareSize(), images.get("head"));
        ImageProcessor rotated = new ImageProcessor(configuration.getSquareSize(), configuration.getSquareSize(), images.get("rotated"));
        ImageProcessor straight = new ImageProcessor(configuration.getSquareSize(), configuration.getSquareSize(), images.get("straight"));
        ImageProcessor tail = new ImageProcessor(configuration.getSquareSize(), configuration.getSquareSize(), images.get("tail"));
        SnakeFX snake = new SnakeFX(configuration.getSquareSize(), configuration.getSquareSize());
        snake.setSkins(head, rotated, straight, tail);
        this.snake = snake;
    }

    private void setFood() {
        ImageProcessor imageProcessor = new ImageProcessor(configuration.getSquareSize(), configuration.getSquareSize(), images.get("apple"));
        List<Fruit> food = new ArrayList<>();
        for (int i = 0; i < configuration.getFruitsNumber(); ++i) {
            FruitFX fruit = new FruitFX(configuration.getSquareSize(), configuration.getSquareSize());
            fruit.setSkin(imageProcessor);
            food.add(fruit);
        }
        this.food = food;
    }

    private void setSnakeGame() {
        setBoard();
        setSnake();
        setFood();
        snakeGame = new GameFX(configuration, board, snake, food);
        snakeGame.start();
    }

    private void setScene() {
        frame = new Group();
        snakeGame.render(frame);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/nsu/mbogdanov/fxml/snakeGame.fxml"));
        try {
            BorderPane root = loader.load();
            SnakeGameController snakeGameController = loader.getController();
            snakeGameController.initialize(stage, configuration, timeline, snakeGame);
            root.setCenter(frame);
            scene = new Scene(root);
            gameController = new GameController(snakeGameController, snakeGame, timeline);
            scene.setOnKeyPressed(gameController::handle);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private void setTimeline() {
        this.timeline = new Timeline(new KeyFrame(Duration.millis(configuration.getSnakeSpeed()), event -> gameController.run(frame)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
        setTimeline();
        setScene();
        if (scene != null) {
            stage.setScene(scene);
        }
    }
}
