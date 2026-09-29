import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;


public class WorkoutStorage {
    private static final String SEPARATOR = ";";

    private final Path path;

    public WorkoutStorage(Path path) {
        this.path = path;
    }

    public Path getPath(){
        return path;
    }

    public void save(Workout workout) throws IOException{
        List<String> lines = new ArrayList<>();
        lines.add("WORKOUT" + SEPARATOR + workout.getWorkoutDate() + SEPARATOR + workout.getName());
        for (Exercise e: workout.getExercises()){
            lines.add("EXERCISE" + SEPARATOR + e.getName() + SEPARATOR + e.getDescription());
            e.getSets().forEach(s -> lines.add("SET" + SEPARATOR + s.getWeight() + SEPARATOR + s.getReps() + SEPARATOR + s.getDifficulty() + SEPARATOR + s.getComment()));
        }
        Files.write(path, lines, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }
}
