package ru.nsu.mbogdanov.model.ingame.graphics;

import javafx.scene.Group;
import javafx.scene.image.ImageView;
import ru.nsu.mbogdanov.controllers.other.ImageProcessor;
import ru.nsu.mbogdanov.model.environment.Cell;
import ru.nsu.mbogdanov.model.ingame.objects.Wall;

/**
 * Implements the abstract class Wall.
 * Provides with an interface for rendering wall on the specified frame.
 */
public class WallGraphics extends Wall {
    private ImageProcessor imageProcessor;

    /**
     * Class constructor. Creates a wall with specified width and height.
     *
     * @param width  - wall width.
     * @param height - wall height.
     */
    public WallGraphics(double width, double height) {
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

    private ImageView renderWall(Cell wall, ImageView imageView) {
        imageView.setX(wall.getRowCoordinate());
        imageView.setY(wall.getColumnCoordinate());
        return imageView;
    }

    /**
     * Renders wall on the specified frame depending on its coordinates and bounds.
     *
     * @param object - object on which wall should be rendered.
     */
    @Override
    public void render(Object object) {
        Group frame = ((Group) object);
        Group fruit = new Group();
        fruit.getChildren().add(renderWall(getBoundary(), imageProcessor.getImage()));
        frame.getChildren().add(fruit);
    }
}
