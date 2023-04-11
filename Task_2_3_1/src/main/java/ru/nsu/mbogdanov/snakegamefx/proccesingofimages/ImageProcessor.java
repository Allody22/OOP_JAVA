package ru.nsu.mbogdanov.snakegamefx.proccesingofimages;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

//Обработчик изображений
public class ImageProcessor {
    private final double width;
    private final double height;
    private final Image image;


    public ImageProcessor(double width, double height, Image image) {
        this.width = width;
        this.height = height;
        this.image = image;
    }

    public ImageView getImage() {
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        imageView.preserveRatioProperty();
        return imageView;
    }

    public ImageView getRotatedImage(double degrees) {
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        imageView.setRotate(degrees);
        imageView.preserveRatioProperty();
        return imageView;
    }
}
