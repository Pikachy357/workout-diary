import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
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

        try {
            List<String> lines = Files.readAllLines(path);
            Workout currentWorkout = null;
            Exercise currentExercise = null;

            for (String line : lines) {
                String[] parts = line.split(SEPARATOR);
                switch (parts[0]) {
                    case "WORKOUT" -> {
                        currentWorkout = new Workout(LocalDate.parse(parts[1]), parts[2], new ArrayList<>());
                        workouts.add(currentWorkout);
                    }
                    case "EXERCISE" -> {
                        currentExercise = new Exercise(parts[1], parts[2], new ArrayList<>());
                        currentWorkout.addExercise(currentExercise);
                    }

                    case "SET" -> {
                        currentExercise.addSet(new ExerciseSet(Double.parseDouble(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3]), parts[4]));
                    }
                }
            }
        } catch (IOException e) {
            throw new IOException("Загрузка из файла не удалась.  Ошибка : " + e.getMessage());
        }
        return workouts;
    }
}
