package ru.nsu.mbogdanov2.model.constant;

/**
 * Different order statuses.
 */
public enum State {
    IN_QUEUE("in queue"), COOKING("cooking"), IN_STOCK("in stock"),
    DELIVERING("delivering"), DELIVERED("delivered");
    private final String state;

    State(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}