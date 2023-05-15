package ru.nsu.mbogdanov.controllers.other;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 * The ImageProcessor that is responsible for processing images and creating
 * image views.
 */
public class ImageProcessor {

    private final double width;
    private final double height;
    private final Image image;

    /**
     * Creates an ImageProcessor with the specified width, height, and image.
     *
     * @param width  - width of the image
     * @param height - height of the image
     * @param image  - image to process
     */
    public ImageProcessor(double width, double height, Image image) {
        this.width = width;
        this.height = height;
        this.image = image;
    }

    /**
     * Function that returns ImageView object of the processed image.
     *
     * @return an ImageView object of the processed image
     */
    public ImageView getImage() {
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        imageView.preserveRatioProperty();
        return imageView;
    }

    /**
     * Returns an ImageView object of the processed image rotated by the specified degrees.
     *
     * @param degrees - degrees to rotate the image
     * @return an ImageView object of the processed image rotated by the specified degrees
     */
    public ImageView getRotatedImage(double degrees) {
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        imageView.setRotate(degrees);
        imageView.preserveRatioProperty();
        return imageView;
    }
}