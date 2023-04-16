package ru.nsu.mbogdanov.application.snakegame;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import ru.nsu.mbogdanov.application.configuration.Configuration;
import ru.nsu.mbogdanov.snakegame.sprite.board.Board;
import ru.nsu.mbogdanov.snakegame.sprite.fruit.Fruit;
import ru.nsu.mbogdanov.snakegame.sprite.snake.Snake;
import ru.nsu.mbogdanov.snakegame.sprite.wall.Wall;
import ru.nsu.mbogdanov.snakegamefx.game.GameController;
import ru.nsu.mbogdanov.snakegamefx.game.GameFX;
import ru.nsu.mbogdanov.snakegamefx.proccesingofimages.ImageProcessor;
import ru.nsu.mbogdanov.snakegamefx.sprite.BoardFX;
import ru.nsu.mbogdanov.snakegamefx.sprite.FruitFX;
import ru.nsu.mbogdanov.snakegamefx.sprite.SnakeFX;
import ru.nsu.mbogdanov.snakegamefx.sprite.WallFX;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * The SnakeGame class that manages the game logic and rendering.
 */
public class SnakeGame {

    /**
     * The Stage on which the game is displayed.
     */
    private Stage stage;

    /**
     * The Configuration object for the game.
     */
    private final Configuration configuration;

    /**
     * Map containing image resources for the game.
     */
    private Map<String, Image> images;

    /**
     * The Board object for the game.
     */
    private Board board;

    /**
     * The Snake object for the game.
     */
    private Snake snake;

    /**
     * List of Fruit objects for the game.
     */
    private List<Fruit> food;

    /**
     * GameFX object for the game.
     */
    private GameFX snakeGame;

    /**
     * The Group object for the game frame.
     */
    private Group frame;

    /**
     * The GameController object for the game.
     */
    private GameController gameController;

    /**
     * The Scene object for the game.
     */
    private Scene scene;

    /**
     * The Timeline object for the game.
     */
    private Timeline timeline;

    /**
     * Dangerous walls.
     */
    private List<Wall> walls;

    /**
     * Constructor of this class.
     *
     * @param configuration - number of walls and size of the current game.
     */
    public SnakeGame(Configuration configuration) {
        this.configuration = configuration;
        setImages();
        setSnakeGame();
    }

    /**
     * Loads images from resources and adds them to this game.
     */
    private void setImages() {
        this.images = new HashMap<>();
        images.put("board", new Image(String.valueOf(getClass()
                .getResource("/ru/nsu/mbogdanov/images/board/board2.png"))));
        images.put("head", new Image(String.valueOf(getClass()
                .getResource("/ru/nsu/mbogdanov/images/snake/head.png"))));
        images.put("rotated", new Image(String.valueOf(getClass()
                .getResource("/ru/nsu/mbogdanov/images/snake/rotated.png"))));
        images.put("straight", new Image(String.valueOf(getClass()
                .getResource("/ru/nsu/mbogdanov/images/snake/straight.png"))));
        images.put("tail", new Image(String.valueOf(getClass()
                .getResource("/ru/nsu/mbogdanov/images/snake/tail.png"))));
        images.put("apple", new Image(String.valueOf(getClass()
                .getResource("/ru/nsu/mbogdanov/images/fruit/apple.png"))));
    }

    /**
     * Sets the board size and board image.
     */
    private void setBoard() {
        double width = configuration.getSquareSize() * configuration.getRowsNumber();
        double height = configuration.getSquareSize() * configuration.getColumnsNumber();
        ImageProcessor imageProcessor = new ImageProcessor(width, height, images.get("board"));
        BoardFX board = new BoardFX(width, height);
        board.setSkin(imageProcessor);
        this.board = board;
    }

    /**
     * Create the snake with all parts of its body.
     */
    private void setSnake() {
        ImageProcessor head = new ImageProcessor(configuration.getSquareSize(),
                configuration.getSquareSize(), images.get("head"));
        ImageProcessor rotated = new ImageProcessor(configuration.getSquareSize(),
                configuration.getSquareSize(), images.get("rotated"));
        ImageProcessor straight = new ImageProcessor(configuration.getSquareSize(),
                configuration.getSquareSize(), images.get("straight"));
        ImageProcessor tail = new ImageProcessor(configuration.getSquareSize(),
                configuration.getSquareSize(), images.get("tail"));
        SnakeFX snake = new SnakeFX(configuration.getSquareSize(),
                configuration.getSquareSize());
        snake.setSkins(head, rotated, straight, tail);
        this.snake = snake;
    }

    /**
     * Gets the apple image and sets them on the board.
     */
    private void setFood() {
        ImageProcessor imageProcessor = new ImageProcessor(configuration.getSquareSize(),
                configuration.getSquareSize(), images.get("apple"));
        List<Fruit> food = new ArrayList<>();
        for (int i = 0; i < configuration.getFruitsNumber(); ++i) {
            FruitFX fruit = new FruitFX(configuration.getSquareSize(),
                    configuration.getSquareSize());
            fruit.setSkin(imageProcessor);
            food.add(fruit);
        }
        this.food = food;
    }

    /**
     * Creates the walls and sets them on the board.
     */
    private void setWalls() {
        List<Wall> walls = new ArrayList<>();
        for (int i = 0; i < configuration.getWallsNumber(); ++i) {
            WallFX wall = new WallFX(configuration.getSquareSize(),
                    configuration.getSquareSize());
            wall.setColor(Color.web("#bcae76"));
            walls.add(wall);
        }
        this.walls = walls;
    }

    /**
     * Sets the whole snake game with walls, board, food and snake.
     */
    private void setSnakeGame() {
        setBoard();
        setSnake();
        setFood();
        setWalls();
        snakeGame = new GameFX(configuration, board, snake, food, walls);
        snakeGame.start();
    }

    /**
     *
     */
    private void setScene() {
        frame = new Group();
        snakeGame.render(frame);
        FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("/ru/nsu/mbogdanov/fxml/snakeGame.fxml"));
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

    /**
     * We set the timeline with the speed of the snake from the configuration.
     */
    private void setTimeline() {
        this.timeline = new Timeline(new KeyFrame(Duration.millis(configuration
                .getSnakeSpeed()),
                event -> gameController.run(frame)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    /**
     * Finally we set the snake game the special window.
     *
     * @param stage - another special window for the game.
     */
    public void setStage(Stage stage) {
        this.stage = stage;
        setTimeline();
        setScene();
        if (scene != null) {
            stage.setScene(scene);
        }
    }
}
