import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Workout {

    private LocalDate workoutDate;
    private String name;
    private List <Exercise> exercises;

    public LocalDate getWorkoutDate() {
        return workoutDate;
    }

    public String getName() {
        return name;
    }

    public List <Exercise> getExercises() {
        return exercises;
    }

    public Workout(LocalDate workoutDate, String name, List<Exercise> exercise){
        if (workoutDate == null){
            throw new IllegalArgumentException("Дата не может быть null" );
        }
        if (name == null || name.isBlank()){
            throw new IllegalArgumentException("Имя null или пустое");
        }
        if (exercise == null) {
            throw new IllegalArgumentException("Список упражнения не может быть null");
        }
        this.workoutDate = workoutDate;
        this.name = name;
        this.exercises = exercise;
    }

    public void addExercise(Exercise exercise){
        if (exercise == null){
            throw new IllegalArgumentException("Нельзя добавить null упражение");
        }
        exercises.add(exercise);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Дата: ").append(workoutDate).append("\n");
        sb.append("Название: ").append(name).append("\n");
        sb.append("Упражнения:").append("\n");
        int number = 1;
        for (Exercise exercise: exercises) {
            sb.append(" ").append(number).append(") ").append(exercise).append("\n\n");
            number++;
        }
        return sb.toString();
    }
}
