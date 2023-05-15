package ru.nsu.mbogdanov.model.ingame.graphics;

import javafx.scene.Group;
import javafx.scene.image.ImageView;
import ru.nsu.mbogdanov.model.environment.Cell;
import ru.nsu.mbogdanov.model.ingame.objects.Fruit;
import ru.nsu.mbogdanov.controllers.other.ImageProcessor;


/**
 * The FruitFX that is responsible for rendering the fruit using JavaFX.
 */
public class FruitGraphics extends Fruit {

    private ImageProcessor imageProcessor;

    /**
     * Creates a FruitFX object with the specified width and height.
     *
     * @param width  - width of the fruit
     * @param height - height of the fruit
     */
    public FruitGraphics(double width, double height) {
        super(width, height);
    }

    /**
     * Sets the ImageProcessor object used to render the fruit.
     *
     * @param imageProcessor - ImageProcessor object to use
     */
    public void setSkin(ImageProcessor imageProcessor) {
        this.imageProcessor = imageProcessor;
    }

    /**
     * Renders the fruit using JavaFX graphical components.
     *
     * @param object - object to render the fruit on
     */
    @Override
    public void render(Object object) {
        Group frame = ((Group) object);
        Group fruit = new Group();
        fruit.getChildren().add(renderFruit(getBoundary(), imageProcessor.getImage()));
        frame.getChildren().add(fruit);
    }

    /**
     * Renders the specified cell using the specified ImageView.
     *
     * @param fruit     - cell to be rendered
     * @param imageView - ImageView to render the cell with
     * @return the rendered ImageView of the fruit
     */
    private ImageView renderFruit(Cell fruit, ImageView imageView) {
        imageView.setX(fruit.getRowCoordinate());
        imageView.setY(fruit.getColumnCoordinate());
        return imageView;
    }
}