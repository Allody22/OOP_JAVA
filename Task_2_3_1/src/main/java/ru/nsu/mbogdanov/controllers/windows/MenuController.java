package ru.nsu.mbogdanov.controllers.windows;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import ru.nsu.mbogdanov.windows.SettingsWindow;
import ru.nsu.mbogdanov.model.environment.Configuration;
import ru.nsu.mbogdanov.windows.SnakeBoardWindow;


/**
 * Controller class for the game menu.
 */
public class MenuController {

    /**
     * The Stage object on which the menu will be displayed.
     */
    private Stage stage;

    /**
     * Configuration object for the game.
     */
    private Configuration configuration;

    /**
     * Initialization method for the controller.
     *
     * @param stage         - the Stage object on which the menu will be displayed
     * @param configuration - configuration object for the game
     */
    public void initialize(Stage stage, Configuration configuration) {
        this.stage = stage;
        this.configuration = configuration;
    }

    /**
     * Method called when the start game button is clicked on the menu.
     */
    @FXML
    private void startGame() {
        SnakeBoardWindow snakeBoardWindow = new SnakeBoardWindow(configuration);
        snakeBoardWindow.setStage(stage);
    }

    /**
     * When click on the button with the specified handler, the settings window opens.
     */
    @FXML
    private void openSettings() {
        SettingsWindow settingsWindow = new SettingsWindow(configuration);
        settingsWindow.setStage(stage);
    }

    /**
     * Method called when the exit button is clicked on the menu.
     */
    @FXML
    private void exit() {
        stage.close();
    }
}