import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;


public class WorkoutStorage {
    private static final String SEPARATOR = ";";

    private final Path path;

    public WorkoutStorage(Path path) {
        this.path = path;
    }

    public Path getPath() {
        return path;
    }

    public void save(Workout workout) throws IOException {
        List<String> lines = new ArrayList<>();
        lines.add("WORKOUT" + SEPARATOR + workout.getWorkoutDate() + SEPARATOR + workout.getName());
        for (Exercise e : workout.getExercises()) {
            lines.add("EXERCISE" + SEPARATOR + e.getName() + SEPARATOR + e.getDescription());
            e.getSets().forEach(s -> lines.add("SET" + SEPARATOR + s.getWeight() + SEPARATOR + s.getReps() + SEPARATOR + s.getDifficulty() + SEPARATOR + s.getComment()));
        }
        Files.write(path, lines, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }

    public List<Workout> loadAll() throws IOException {
        List<Workout> workouts = new ArrayList<>();
        if (!Files.exists(path)) {
            return workouts;
        }
        List<String> lines = Files.readAllLines(path);
        Workout currentWorkout = null;
        Exercise currentExercise = null;
        int lineNumber = 0;
        for (String line : lines) {
            lineNumber++;
            if (line.isBlank()) {
                continue;
            }
            String[] parts = line.split(SEPARATOR);
            try {
                switch (parts[0]) {
                    case "WORKOUT" -> {
                        currentWorkout = new Workout(LocalDate.parse(parts[1]), parts[2], new ArrayList<>());
                        workouts.add(currentWorkout);
                        currentExercise = null;
                    }
                    case "EXERCISE" -> {
                        if (currentWorkout == null){
                            throw new IOException("Строка " + lineNumber + ": упражнение без тренировки");
                        }

                        currentExercise = new Exercise(parts[1], parts[2], new ArrayList<>());
                        currentWorkout.addExercise(currentExercise);
                    }
                    case "SET" -> {
                        if (currentExercise == null){
                            throw new IOException("Строка " + lineNumber + ": подход без упражнения");
                        }
                        currentExercise.addSet(new ExerciseSet(Double.parseDouble(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3]), parts[4]));
                    }
                    default -> {
                        throw new IOException("Строка " + lineNumber + ": неизветсная метка  " + parts[0]);
                    }
            }
            } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException | DateTimeParseException e) {
                throw new IOException("Файл повреждён, строка " + lineNumber + ": " + line, e);
            }
        }
        return workouts;
    }
}
