package ru.nsu.mbogdanov.model.ingame.graphics;

import javafx.scene.Group;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import ru.nsu.mbogdanov.model.environment.Cell;
import ru.nsu.mbogdanov.model.ingame.objects.Wall;

/**
 * Implements the abstract class Wall.
 * Provides with an interface for rendering wall on the specified frame.
 */
public class WallGraphics extends Wall {
    private final Rectangle rectangle;

    /**
     * Class constructor. Creates a wall with specified width and height.
     *
     * @param width  - wall width.
     * @param height - wall height.
     */
    public WallGraphics(double width, double height) {
        super(width, height);
        rectangle = new Rectangle(width, height);
    }

    /**
     * Changes wall color.
     *
     * @param color - new wall color.
     */
    public void setColor(Paint color) {
        rectangle.setFill(color);
    }

    /**
     * Renders a cell on the specified rectangle, using the cell's coordinates as the Rectangle's x and y position.
     *
     * @param cell      - the cell to be rendered.
     * @param rectangle - the rectangle used to render the cell.
     * @return the modified rectangle.
     */
    public Rectangle renderCell(Cell cell, Rectangle rectangle) {
        rectangle.setX(cell.getRowCoordinate());
        rectangle.setY(cell.getColumnCoordinate());
        return rectangle;
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
        fruit.getChildren().add(renderCell(getBoundary(), rectangle));
        frame.getChildren().add(fruit);
    }
}
