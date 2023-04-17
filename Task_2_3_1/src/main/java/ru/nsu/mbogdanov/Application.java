package ru.nsu.mbogdanov;

import javafx.scene.image.Image;
import javafx.stage.Stage;
import ru.nsu.mbogdanov.model.environment.Configuration;
import ru.nsu.mbogdanov.windows.Menu;

/**
 * Main class of the application that can launch it.
 */
public class Application extends javafx.application.Application {
    private final Configuration defaultConfiguration = new Configuration(40, 15,
            15, 100, 5, 10, 100);
    private final Image gameIcon = new Image(String.valueOf(getClass()
            .getResource("/ru/nsu/mbogdanov/images/fruit/heart.png")));

    /**
     * Overriding of the start method.
     *
     * @param primaryStage - the primary stage for this application, onto which
     *                     the application scene can be set.
     *                     Applications may create other stages, if needed, but they will not be
     *                     primary stages.
     */
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Snake");
        primaryStage.centerOnScreen();
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(gameIcon);
        Menu menu = new Menu(defaultConfiguration);
        menu.setStage(primaryStage);
        primaryStage.show();
    }

    /**
     * Main method that starts the application.
     *
     * @param args - main string arguments
     */
    public static void main(String[] args) {
        launch();
    }
}
