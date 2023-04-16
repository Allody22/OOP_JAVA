package ru.nsu.mbogdanov.snakegame.game;


/**
 * This enum represents the different possible states of the Snake game.
 */
public enum GameState {
    DEFEAT("GAME OVER"),
    VICTORY("YOU WIN"),
    PLAY("SNAKE GAME");
    private final String state;

    /**
     * Constructs a new GameState with the specified state label.
     *
     * @param state - the label corresponding to the state
     */
    GameState(String state) {
        this.state = state;
    }

    /**
     * Returns the string this enum.
     *
     * @return the label corresponding to the state
     */
    @Override
    public String toString() {
        return state;
    }
}