package ru.nsu.mbogdanov.model.ingame.graphics;

import javafx.scene.Group;
import javafx.scene.image.ImageView;
import ru.nsu.mbogdanov.model.environment.Cell;
import ru.nsu.mbogdanov.model.constants.Direction;
import ru.nsu.mbogdanov.model.ingame.objects.Snake;
import ru.nsu.mbogdanov.controllers.other.ImageProcessor;

import java.util.ArrayList;
import java.util.List;


/**
 * The SnakeFX class that is responsible for rendering the snake's body and head using JavaFX.
 */
public class SnakeGraphics extends Snake {

    private ImageProcessor headImageProcessor;
    private ImageProcessor rotatedImageProcessor;
    private ImageProcessor straightImageProcessor;
    private ImageProcessor tailImageProcessor;

    /**
     * Creates a SnakeFX object with the specified width and height.
     *
     * @param width  - width of the snake
     * @param height - height of the snake
     */
    public SnakeGraphics(double width, double height) {
        super(width, height);
    }

    /**
     * Sets the ImageProcessor objects used to render the snake's body and head.
     *
     * @param headImageProcessor     - ImageProcessor for the snake's head
     * @param rotatedImageProcessor  - ImageProcessor for rotated snake body segments
     * @param straightImageProcessor -ImageProcessor for straight snake body segments
     * @param tailImageProcessor     - ImageProcessor for the snake's tail
     */
    public void setSkins(ImageProcessor headImageProcessor, ImageProcessor rotatedImageProcessor,
                         ImageProcessor straightImageProcessor, ImageProcessor tailImageProcessor) {
        this.headImageProcessor = headImageProcessor;
        this.rotatedImageProcessor = rotatedImageProcessor;
        this.straightImageProcessor = straightImageProcessor;
        this.tailImageProcessor = tailImageProcessor;
    }

    /**
     * Rendering of the current cell method.
     *
     * @param cell      - cell to be rendered
     * @param imageView - ImageView to render the cell with
     * @return the rendered ImageView of this cell
     */
    private ImageView renderCell(Cell cell, ImageView imageView) {
        imageView.setX(cell.getXCoordinate());
        imageView.setY(cell.getYCoordinate());
        return imageView;
    }

    /**
     * Rendering of the head method.
     *
     * @param head      - head cell to be rendered
     * @param direction - direction of this snake that is head
     * @return the rendered ImageView of this cell
     */
    private ImageView renderHead(Cell head, Direction direction) {
        ImageView imageView = switch (direction) {
            case LEFT -> headImageProcessor.getRotatedImage(180);
            case UP -> headImageProcessor.getRotatedImage(270);
            case DOWN -> headImageProcessor.getRotatedImage(90);
            case RIGHT -> headImageProcessor.getImage();
        };
        return renderCell(head, imageView);
    }

    /**
     * Rendering of the tail method.
     *
     * @param tail     - tail cell to be rendered
     * @param previous - previous cell of the snake
     * @return the rendered ImageView of this cell
     */
    private ImageView renderTail(Cell tail, Cell previous) {
        if (tail.getYCoordinate() == previous.getYCoordinate()) {
            if (tail.getXCoordinate() < previous.getXCoordinate()) {
                return renderCell(tail, tailImageProcessor.getImage());
            }
            if (tail.getXCoordinate() > previous.getXCoordinate()) {
                return renderCell(tail, tailImageProcessor.getRotatedImage(180));
            }
        }
        if (tail.getYCoordinate() > previous.getYCoordinate()) {
            return renderCell(tail, tailImageProcessor.getRotatedImage(270));
        }
        return renderCell(tail, tailImageProcessor.getRotatedImage(90));
    }

    /**
     * Rendering of the flake method.
     *
     * @param flake-   flake cell to be rendered
     * @param previous - previous cell the body
     * @param next     - next cell the body
     * @return the rendered ImageView of this cell
     */
    private ImageView renderFlake(Cell flake, Cell previous, Cell next) {
        if (next.getYCoordinate() == previous.getYCoordinate()) {
            return renderCell(flake, straightImageProcessor.getImage());
        }
        if (flake.getXCoordinate() == previous.getXCoordinate()) {
            if (next.getYCoordinate() < previous.getYCoordinate()) {
                if (next.getXCoordinate() < previous.getXCoordinate()) {
                    return renderCell(flake, rotatedImageProcessor.getImage());
                }
                if (next.getXCoordinate() > previous.getXCoordinate()) {
                    return renderCell(flake, rotatedImageProcessor.getRotatedImage(270));
                }
            }
            if (next.getYCoordinate() > previous.getYCoordinate()) {
                if (next.getXCoordinate() < previous.getXCoordinate()) {
                    return renderCell(flake, rotatedImageProcessor.getRotatedImage(90));
                }
                if (next.getXCoordinate() > previous.getXCoordinate()) {
                    return renderCell(flake, rotatedImageProcessor.getRotatedImage(180));
                }
            }
        }
        if (flake.getXCoordinate() == next.getXCoordinate()) {
            if (next.getYCoordinate() < previous.getYCoordinate()) {
                if (next.getXCoordinate() < previous.getXCoordinate()) {
                    return renderCell(flake, rotatedImageProcessor.getRotatedImage(180));
                }
                if (next.getXCoordinate() > previous.getXCoordinate()) {
                    return renderCell(flake, rotatedImageProcessor.getRotatedImage(90));
                }
            }
            if (next.getYCoordinate() > previous.getYCoordinate()) {
                if (next.getXCoordinate() < previous.getXCoordinate()) {
                    return renderCell(flake, rotatedImageProcessor.getRotatedImage(270));
                }
                if (next.getXCoordinate() > previous.getXCoordinate()) {
                    return renderCell(flake, rotatedImageProcessor.getImage());
                }
            }
        }
        return renderCell(flake, straightImageProcessor.getRotatedImage(90));
    }

    /**
     * This private method is used to render the snake's body parts using JavaFX.
     *
     * @param boundary - a list of cells representing the boundary of the snake
     * @return a list of image views representing the snake's body parts
     */
    private List<ImageView> renderBody(List<Cell> boundary) {
        List<ImageView> body = new ArrayList<>();
        body.add(renderHead(boundary.get(0), getDirection()));
        for (int i = 1; i < boundary.size() - 1; ++i) {
            body.add(renderFlake(boundary.get(i), boundary.get(i + 1), boundary.get(i - 1)));
        }
        body.add(renderTail(boundary.get(boundary.size() - 1),
                boundary.get(boundary.size() - 2)));
        return body;
    }

    /**
     * Renders the snake using JavaFX.
     *
     * @param object the object to render the board
     */
    @Override
    public void render(Object object) {
        Group frame = ((Group) object);
        Group snake = new Group();
        snake.getChildren().addAll(renderBody(getBoundary()));
        frame.getChildren().add(snake);
    }
}
