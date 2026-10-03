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
    void maxWeightIsSets(){
        Exercise press = new Exercise("Жим", "Со штангой", new ArrayList<>());
        press.addSet(new ExerciseSet(80, 10, 5, "разминка"));
        press.addSet(new ExerciseSet(100, 8, 7, "рабочий"));
        press.addSet(new ExerciseSet(110, 9, 7, "рабочий"));
        press.addSet(new ExerciseSet(105, 10, 7, "рабочий"));
        press.addSet(new ExerciseSet(90, 7, 7, "заминка"));
        double maxWeight = press.getMaxWeight();
        assertEquals(110, maxWeight);
    }

    @Test
    void negativeWeightIsRejected() {
        assertThrows(IllegalArgumentException.class,
                () -> new ExerciseSet(-5, 10, 5, "ой"));
    }
    @Test
    void outOfRangeDifficulty(){
        assertThrows(IllegalArgumentException.class, () -> new ExerciseSet(100, 5, 11,"Бац Бац"));
    }
}