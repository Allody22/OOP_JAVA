package ru.nsu.mbogdanov.model.ingame.objects;


/**
 * This interface represents a game sprite in the Snake game.
 */
public interface Sprite {

    /**
     * Returns the boundaries of the sprite.
     *
     * @return the boundaries of the sprite
     */
    Object getBoundary();

    /**
     * Updates the position of the sprite to the specified x, y position.
     *
     * @param rowCoordinate - x coordinate of the sprite position
     * @param columnCoordinate - y coordinate of the sprite position
     */
    void update(double rowCoordinate, double columnCoordinate);

    /**
     * Checks if this sprite intersects with the specified sprite.
     *
     * @param sprite - sprite to check for intersection
     * @return true if this sprite intersects with the specified sprite
     */
    boolean intersects(Sprite sprite);

    /**
     * Renders this sprite in the specified object.
     *
     * @param object - the object to render the sprite in
     */
    void render(Object object);
}
