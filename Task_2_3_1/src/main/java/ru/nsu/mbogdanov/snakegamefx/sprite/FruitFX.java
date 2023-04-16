package ru.nsu.mbogdanov.snakegamefx.sprite;

import javafx.scene.Group;
import javafx.scene.image.ImageView;
import ru.nsu.mbogdanov.snakegame.cell.Cell;
import ru.nsu.mbogdanov.snakegame.sprite.fruit.Fruit;
import ru.nsu.mbogdanov.snakegamefx.proccesingofimages.ImageProcessor;


/**
 * The FruitFX that is responsible for rendering the fruit using JavaFX.
 */
public class FruitFX extends Fruit {

    private ImageProcessor imageProcessor;

    /**
     * Creates a FruitFX object with the specified width and height.
     *
     * @param width  - width of the fruit
     * @param height - height of the fruit
     */
    public FruitFX(double width, double height) {
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
        imageView.setX(fruit.getX());
        imageView.setY(fruit.getY());
        return imageView;
    }
}