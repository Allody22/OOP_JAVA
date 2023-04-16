package ru.nsu.mbogdanov.application.menu;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.nsu.mbogdanov.application.configuration.Configuration;

import java.io.IOException;


/**
 * Class for the game menu.
 */
public class Menu {

    /**
     * Configuration object for the game.
     */
    private final Configuration configuration;

    /**
     * The scene for the menu.
     */
    private Scene scene;

    /**
     * The controller for the menu.
     */
    private MenuController controller;

    /**
     * Constructor for the menu that creates an object and loads the FXML file.
     *
     * @param configuration configuration object for the game
     */
    public Menu(Configuration configuration) {
        this.configuration = configuration;
        FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("/ru/nsu/mbogdanov/fxml/menu.fxml"));
        try {
            Parent root = loader.load();
            scene = new Scene(root);
            controller = loader.getController();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Method that sets the menu scene on a given Stage object.
     *
     * @param stage the Stage object on which the menu will be displayed
     */
    public void setStage(Stage stage) {
        if (scene != null && controller != null) {
            controller.initialize(stage, configuration);
            stage.setScene(scene);
        }
    }
}