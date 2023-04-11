package ru.nsu.mbogdanov.application.menu;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import ru.nsu.mbogdanov.application.configuration.Configuration;
import ru.nsu.mbogdanov.application.snakegame.SnakeGame;


public class MenuController {
    private Stage stage;
    private Configuration configuration;

    public void initialize(Stage stage, Configuration configuration) {
        this.stage = stage;
        this.configuration = configuration;
    }
    @FXML
    private void startGame() {
        SnakeGame snakeGame = new SnakeGame(configuration);
        snakeGame.setStage(stage);
    }
    @FXML
    private void exit() {
        stage.close();
    }
}
