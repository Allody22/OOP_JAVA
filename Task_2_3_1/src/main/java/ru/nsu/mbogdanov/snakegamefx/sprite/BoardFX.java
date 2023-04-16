package ru.nsu.mbogdanov.snakegamefx.sprite;

import javafx.scene.Group;
import javafx.scene.image.ImageView;
import ru.nsu.mbogdanov.snakegame.cell.Cell;
import ru.nsu.mbogdanov.snakegame.sprite.board.Board;
import ru.nsu.mbogdanov.snakegamefx.proccesingofimages.ImageProcessor;


/**
 * The BoardFX class that is responsible for rendering the game board by JavaFX.
 */
public class BoardFX extends Board {

    private ImageProcessor imageProcessor;

    /**
     * Creates a BoardFX object with the specified width and height.
     *
     * @param width  - width of the board
     * @param height - height of the board
     */
    public BoardFX(double width, double height) {
        super(width, height);
    }

    /**
     * Sets the ImageProcessor object used to render the board.
     *
     * @param imageProcessor - ImageProcessor object to use
     */
    public void setSkin(ImageProcessor imageProcessor) {
        this.imageProcessor = imageProcessor;
    }

    /**
     * Renders the game board using JavaFX.
     *
     * @param object the object to render on the board
     */
    @Override
    public void render(Object object) {
        Group frame = ((Group) object);
        Group board = new Group();
        board.getChildren().add(renderBoard(getBoundary(), imageProcessor.getImage()));
        frame.getChildren().add(board);
    }

    /**
     * Renders the specified cell using the specified ImageView.
     *
     * @param board     - cell to be rendered
     * @param imageView - ImageView to render the cell with
     * @return the rendered ImageView of the cell
     */
    private ImageView renderBoard(Cell board, ImageView imageView) {
        imageView.setX(board.getX());
        imageView.setY(board.getY());
        return imageView;
    }
}
