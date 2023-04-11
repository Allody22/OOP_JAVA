package ru.nsu.mbogdanov.snakegamefx.sprite;

import javafx.scene.Group;
import javafx.scene.image.ImageView;
import ru.nsu.mbogdanov.snakegame.cell.Cell;
import ru.nsu.mbogdanov.snakegame.sprite.snake.Direction;
import ru.nsu.mbogdanov.snakegame.sprite.snake.Snake;
import ru.nsu.mbogdanov.snakegamefx.proccesingofimages.ImageProcessor;

import java.util.ArrayList;
import java.util.List;


public class SnakeFX extends Snake {
    private ImageProcessor headImageProcessor;
    private ImageProcessor rotatedImageProcessor;
    private ImageProcessor straightImageProcessor;
    private ImageProcessor tailImageProcessor;

    public SnakeFX(double width, double height) {
        super(width, height);
    }

    public void setSkins(ImageProcessor headImageProcessor, ImageProcessor rotatedImageProcessor, ImageProcessor straightImageProcessor, ImageProcessor tailImageProcessor) {
        this.headImageProcessor = headImageProcessor;
        this.rotatedImageProcessor = rotatedImageProcessor;
        this.straightImageProcessor = straightImageProcessor;
        this.tailImageProcessor = tailImageProcessor;
    }

    private ImageView renderCell(Cell cell, ImageView imageView) {
        imageView.setX(cell.getX());
        imageView.setY(cell.getY());
        return imageView;
    }

    private ImageView renderHead(Cell head, Direction direction) {
        ImageView imageView = switch (direction) {
            case LEFT -> headImageProcessor.getRotatedImage(180);
            case UP -> headImageProcessor.getRotatedImage(270);
            case DOWN -> headImageProcessor.getRotatedImage(90);
            case RIGHT -> headImageProcessor.getImage();
        };
        return renderCell(head, imageView);
    }

    private ImageView renderTail(Cell tail, Cell previous) {
        if (tail.getY() == previous.getY()) {
            if (tail.getX() < previous.getX()) {
                return renderCell(tail, tailImageProcessor.getImage());
            }
            if (tail.getX() > previous.getX()) {
                return renderCell(tail, tailImageProcessor.getRotatedImage(180));
            }
        }
        if (tail.getY() > previous.getY()) {
            return renderCell(tail, tailImageProcessor.getRotatedImage(270));
        }
        return renderCell(tail, tailImageProcessor.getRotatedImage(90));
    }

    private ImageView renderFlake(Cell flake, Cell previous, Cell next) {
        if (next.getY() == previous.getY()) {
            return renderCell(flake, straightImageProcessor.getImage());
        }
        if (flake.getX() == previous.getX()) {
            if (next.getY() < previous.getY()) {
                if (next.getX() < previous.getX()) {
                    return renderCell(flake, rotatedImageProcessor.getImage());
                }
                if (next.getX() > previous.getX()) {
                    return renderCell(flake, rotatedImageProcessor.getRotatedImage(270));
                }
            }
            if (next.getY() > previous.getY()) {
                if (next.getX() < previous.getX()) {
                    return renderCell(flake, rotatedImageProcessor.getRotatedImage(90));
                }
                if (next.getX() > previous.getX()) {
                    return renderCell(flake, rotatedImageProcessor.getRotatedImage(180));
                }
            }
        }
        if (flake.getX() == next.getX()) {
            if (next.getY() < previous.getY()) {
                if (next.getX() < previous.getX()) {
                    return renderCell(flake, rotatedImageProcessor.getRotatedImage(180));
                }
                if (next.getX() > previous.getX()) {
                    return renderCell(flake, rotatedImageProcessor.getRotatedImage(90));
                }
            }
            if (next.getY() > previous.getY()) {
                if (next.getX() < previous.getX()) {
                    return renderCell(flake, rotatedImageProcessor.getRotatedImage(270));
                }
                if (next.getX() > previous.getX()) {
                    return renderCell(flake, rotatedImageProcessor.getImage());
                }
            }
        }
        return renderCell(flake, straightImageProcessor.getRotatedImage(90));
    }

    private List<ImageView> renderBody(List<Cell> boundary) {
        List<ImageView> body = new ArrayList<>();
        body.add(renderHead(boundary.get(0), getDirection()));
        for (int i = 1; i < boundary.size() - 1; ++i) {
            body.add(renderFlake(boundary.get(i), boundary.get(i + 1), boundary.get(i - 1)));
        }
        body.add(renderTail(boundary.get(boundary.size() - 1), boundary.get(boundary.size() - 2)));
        return body;
    }

    @Override
    public void render(Object object) {
        Group frame = ((Group) object);
        Group snake = new Group();
        snake.getChildren().addAll(renderBody(getBoundary()));
        frame.getChildren().add(snake);
    }
}
