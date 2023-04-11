package ru.nsu.mbogdanov.snakegamefx.sprite;

import javafx.scene.Group;
import javafx.scene.image.ImageView;
import ru.nsu.mbogdanov.snakegame.cell.Cell;
import ru.nsu.mbogdanov.snakegame.sprite.board.Board;
import ru.nsu.mbogdanov.snakegamefx.proccesingofimages.ImageProcessor;


public class BoardFX extends Board {
    private ImageProcessor imageProcessor;

    public BoardFX(double width, double height) {
        super(width, height);
    }

    public void setSkin(ImageProcessor imageProcessor) {
        this.imageProcessor = imageProcessor;
    }

    private ImageView renderBoard(Cell board, ImageView imageView) {
        imageView.setX(board.getX());
        imageView.setY(board.getY());
        return imageView;
    }

    @Override
    public void render(Object object) {
        Group frame = ((Group) object);
        Group board = new Group();
        board.getChildren().add(renderBoard(getBoundary(), imageProcessor.getImage()));
        frame.getChildren().add(board);
    }
}
