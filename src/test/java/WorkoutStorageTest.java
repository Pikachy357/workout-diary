import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WorkoutStorageTest {

    @TempDir
    Path tempDir;

    @Test
    void savedWorkoutIsLoadedBack() throws IOException {
        WorkoutStorage storage = new WorkoutStorage(tempDir.resolve("Workouts.txt"));
        Exercise squat = new Exercise("Приседания", "Со штангой", new ArrayList<>());
        squat.addSet(new ExerciseSet(80, 10, 5, "разминка"));
        squat.addSet(new ExerciseSet(100, 8, 7, "рабочий"));
        List<Exercise> exercises = new ArrayList<>();
        exercises.add(squat);
        Workout workout = new Workout(LocalDate.of(2026, 10, 3), "День ног", exercises);

        storage.save(workout);
        List<Workout> loaded = storage.loadAll();

        assertEquals(1,loaded.size());
        Workout result = loaded.get(0);
        assertEquals("День ног", result.getName());
        assertEquals(LocalDate.of(2026, 10, 3), result.getWorkoutDate());
        assertEquals(1600.0, result.getTotalVolume());
    }
}
