package ru.nsu.mbogdanov2;

import org.junit.jupiter.api.Test;
import ru.nsu.mbogdanov2.json.BakerJson;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Class with tests for Baker entity.
 */
public class BakerJsonTest {

    @Test
    public void testId() {
        int id = 123;
        BakerJson baker = new BakerJson();
        baker.id = id;
        assertEquals(id, baker.id());
    }

    @Test
    public void testWorkingExperience() {
        int workingExperience = 5;
        BakerJson baker = new BakerJson();
        baker.workingExperience = workingExperience;
        assertEquals(workingExperience, baker.workingExperience());
    }

}
