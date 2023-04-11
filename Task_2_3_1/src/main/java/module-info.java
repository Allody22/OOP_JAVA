module ru.nsu.mbogdanov {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;

    exports ru.nsu.mbogdanov;
    opens ru.nsu.mbogdanov to javafx.fxml;
    exports ru.nsu.mbogdanov.snakegame.sprite;
    opens ru.nsu.mbogdanov.snakegame.sprite to javafx.fxml;
    exports ru.nsu.mbogdanov.snakegame.sprite.board;
    opens ru.nsu.mbogdanov.snakegame.sprite.board to javafx.fxml;
    exports ru.nsu.mbogdanov.snakegame.sprite.snake;
    opens ru.nsu.mbogdanov.snakegame.sprite.snake to javafx.fxml;
    exports ru.nsu.mbogdanov.snakegame.sprite.fruit;
    opens ru.nsu.mbogdanov.snakegame.sprite.fruit to javafx.fxml;
    exports ru.nsu.mbogdanov.snakegame.game;
    opens ru.nsu.mbogdanov.snakegame.game to javafx.fxml;
    exports ru.nsu.mbogdanov.snakegame.cell;
    opens ru.nsu.mbogdanov.snakegame.cell to javafx.fxml;
    exports ru.nsu.mbogdanov.snakegamefx.proccesingofimages;
    opens ru.nsu.mbogdanov.snakegamefx.proccesingofimages to javafx.fxml;
    exports ru.nsu.mbogdanov.snakegamefx.sprite;
    opens ru.nsu.mbogdanov.snakegamefx.sprite to javafx.fxml;
    exports ru.nsu.mbogdanov.snakegamefx.game;
    opens ru.nsu.mbogdanov.snakegamefx.game to javafx.fxml;
    exports ru.nsu.mbogdanov.application.modalwindow;
    opens ru.nsu.mbogdanov.application.modalwindow to javafx.fxml;
    exports ru.nsu.mbogdanov.application.menu;
    opens ru.nsu.mbogdanov.application.menu to javafx.fxml;
    exports ru.nsu.mbogdanov.application.configuration;
    opens ru.nsu.mbogdanov.application.configuration to javafx.fxml;
    exports ru.nsu.mbogdanov.application.snakegame;
    opens ru.nsu.mbogdanov.application.snakegame to javafx.fxml;
}