package ru.nsu.mbogdanov.snakegamefx.sprite;

import javafx.scene.Group;
import javafx.scene.image.ImageView;
import ru.nsu.mbogdanov.snakegame.cell.Cell;
import ru.nsu.mbogdanov.snakegame.sprite.fruit.Fruit;
import ru.nsu.mbogdanov.snakegamefx.proccesingofimages.ImageProcessor;


public class FruitFX extends Fruit {
    private ImageProcessor imageProcessor;

    public FruitFX(double width, double height) {
        super(width, height);
    }

    public void setSkin(ImageProcessor imageProcessor) {
        this.imageProcessor = imageProcessor;
    }

    private ImageView renderFruit(Cell fruit, ImageView imageView) {
        imageView.setX(fruit.getX());
        imageView.setY(fruit.getY());
        return imageView;
    }

    @Override
    public void render(Object object) {
        Group frame = ((Group) object);
        Group fruit = new Group();
        fruit.getChildren().add(renderFruit(getBoundary(), imageProcessor.getImage()));
        frame.getChildren().add(fruit);
    }
}
