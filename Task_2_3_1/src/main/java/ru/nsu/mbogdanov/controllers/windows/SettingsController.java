package ru.nsu.mbogdanov.controllers.windows;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ru.nsu.mbogdanov.model.environment.Configuration;
import ru.nsu.mbogdanov.windows.Menu;

import java.util.Objects;

/**
 * Controller for the setting window with all configuration fields.
 */
public class SettingsController {
    private Stage stage;
    private Configuration configuration;

    @FXML
    private Slider squareSize;
    @FXML
    private TextField rowsNumber;
    @FXML
    private TextField columnsNumber;
    @FXML
    private TextField maximumScore;
    @FXML
    private Slider wallsNumber;
    @FXML
    private Slider fruitsNumber;
    @FXML
    private MenuButton snakeSpeed;

    /**
     * Init of the settings with configuration.
     *
     * @param mainStage     - main stage of the game itself.
     * @param configuration - configuration for the initialization.
     */
    public void initialize(Stage mainStage, Configuration configuration) {
        this.stage = mainStage;
        this.configuration = configuration;
        squareSize.setValue(configuration.getSquareSize());
        rowsNumber.setText(String.valueOf(configuration.getRowsNumber()));
        columnsNumber.setText(String.valueOf(configuration.getColumnsNumber()));
        maximumScore.setText(String.valueOf(configuration.getMaximumScore()));
        fruitsNumber.setValue(configuration.getFruitsNumber());
        wallsNumber.setValue(configuration.getWallsNumber());
    }

    /**
     * Button to change the speed of the snake in the settings.
     *
     * @param event - current event from the fxml.
     */
    @FXML
    private void changeSpeed(ActionEvent event) {
        MenuItem menuItem = (MenuItem) event.getSource();
        snakeSpeed.setText(menuItem.getText());
    }

    /**
     * Saving the new configuration of the game.
     */
    @FXML
    private void saveConfiguration() {
        double squareSize = this.squareSize.getValue();
        int rowsNumber = Objects.equals(this.rowsNumber.getText(), "")
                ? configuration.getRowsNumber() : Integer.parseInt(this.rowsNumber.getText());
        int columnsNumber = Objects.equals(this.rowsNumber.getText(), "")
                ? configuration.getColumnsNumber() : Integer.parseInt(this.columnsNumber.getText());
        int maximumScore = Objects.equals(this.rowsNumber.getText(), "")
                ? configuration.getMaximumScore() : Integer.parseInt(this.maximumScore.getText());
        int wallsNumber = (int) this.wallsNumber.getValue();
        int fruitsNumber = (int) this.fruitsNumber.getValue();
        int snakeSpeed = switch (this.snakeSpeed.getText()) {
            case "Slowly" -> 220;
            case "Normal" -> 100;
            case "Fast" -> 70;
            case "Extra speed" -> 60;
            default -> 150;
        };
        this.configuration = new Configuration(squareSize, rowsNumber,
                columnsNumber, maximumScore, wallsNumber, fruitsNumber, snakeSpeed);
        openMenu();
    }

    /**
     * Just button to open the menu window back.
     */
    @FXML
    private void openMenu() {
        ru.nsu.mbogdanov.windows.Menu menu = new Menu(configuration);
        menu.setStage(stage);
    }
}
