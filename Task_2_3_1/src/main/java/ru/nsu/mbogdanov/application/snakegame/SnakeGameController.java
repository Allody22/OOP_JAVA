package ru.nsu.mbogdanov.application.snakegame;

import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import ru.nsu.mbogdanov.application.configuration.Configuration;
import ru.nsu.mbogdanov.application.modalwindow.PauseMenu;
import ru.nsu.mbogdanov.snakegame.game.Game;

import static ru.nsu.mbogdanov.snakegame.game.GameState.DEFEAT;
import static ru.nsu.mbogdanov.snakegame.game.GameState.VICTORY;


public class SnakeGameController {
    private PauseMenu pauseMenu;
    private Timeline timeline;
    private Game game;

    @FXML
    private Label score;

    public void initialize(Stage mainStage, Configuration configuration, Timeline timeline, Game game) {
        this.pauseMenu = new PauseMenu(mainStage, configuration, timeline);
        this.game = game;
        this.timeline = timeline;
    }

    public void updateScore() {
        score.setText("Score: " + game.getScore());
    }

    @FXML
    public void openModalWindow() {
        timeline.pause();
        if (game.getGameState() != DEFEAT && game.getGameState() != VICTORY) {
            pauseMenu.open("PAUSE");
            return;
        }
        pauseMenu.open(game.getGameState().toString());
    }
}
