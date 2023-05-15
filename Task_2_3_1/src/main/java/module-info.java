module ru.nsu.mbogdanov {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;

    exports ru.nsu.mbogdanov;
    opens ru.nsu.mbogdanov to javafx.fxml;
    exports ru.nsu.mbogdanov.windows;
    opens ru.nsu.mbogdanov.windows to javafx.fxml;
    exports ru.nsu.mbogdanov.controllers.windows;
    opens ru.nsu.mbogdanov.controllers.windows to javafx.fxml;
    exports ru.nsu.mbogdanov.model.constants;
    opens ru.nsu.mbogdanov.model.constants to javafx.fxml;
    exports ru.nsu.mbogdanov.model.ingame.graphics;
    opens ru.nsu.mbogdanov.model.ingame.graphics to javafx.fxml;
    exports ru.nsu.mbogdanov.model.ingame.objects;
    opens ru.nsu.mbogdanov.model.ingame.objects to javafx.fxml;
    exports ru.nsu.mbogdanov.model.environment;
    opens ru.nsu.mbogdanov.model.environment to javafx.fxml;
    exports ru.nsu.mbogdanov.controllers.other;
    opens ru.nsu.mbogdanov.controllers.other to javafx.fxml;
}