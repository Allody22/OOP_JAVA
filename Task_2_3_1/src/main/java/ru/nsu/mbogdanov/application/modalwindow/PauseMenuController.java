package ru.nsu.mbogdanov.application.modalwindow;

import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import ru.nsu.mbogdanov.application.configuration.Configuration;
import ru.nsu.mbogdanov.application.menu.Menu;
import ru.nsu.mbogdanov.application.snakegame.SnakeGame;


public class PauseMenuController {
    private Stage stage;
    private Configuration configuration;
    private Timeline timeline;

    @FXML
    StackPane modalWindow;
    @FXML
    private Label header;

    public void initialize(Stage mainStage, Configuration configuration, Timeline timeline) {
        this.stage = mainStage;
        this.configuration = configuration;
        this.timeline = timeline;
    }

    public void setHeader(String text) {
        header.setText(text);
    }

    private void closeModalWindow() {
        Stage stage = (Stage) modalWindow.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void restartGame() {
        SnakeGame snakeGame = new SnakeGame(configuration);
        snakeGame.setStage(stage);
        closeModalWindow();
    }

    @FXML
    private void continueGame() {
        timeline.play();
        closeModalWindow();
    }

    @FXML
    private void openMenu() {
        Menu menu = new Menu(configuration);
        menu.setStage(stage);
        timeline.stop();
        closeModalWindow();
    }
}
