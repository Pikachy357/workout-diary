import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ExerciseTest {

    @Test
    void totalVolumeIsSumOfWeightTimesReps() {

        Exercise squat = new Exercise("Приседания", "Со штангой", new ArrayList<>());
        squat.addSet(new ExerciseSet(80, 10, 5, "разминка"));
        squat.addSet(new ExerciseSet(100, 8, 7, "рабочий"));
        double volume = squat.getTotalVolume();
        assertEquals(1600.0, volume);
    }

    @Test
    void negativeWeightIsRejected() {
        assertThrows(IllegalArgumentException.class,
                () -> new ExerciseSet(-5, 10, 5, "ой"));
    }
}