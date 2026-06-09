import java.time.LocalDate;

public class Workout {
    private LocalDate workoutDate;
    private String name;
    private Exercise [] exercises;

    public LocalDate getWorkoutDate() {
        return workoutDate;
    }

    public String getName() {
        return name;
    }

    public Exercise[] getExercises() {
        return exercises;
    }

    public Workout(LocalDate data, String name, Exercise [] exercise){
        this.workoutDate = data;
        this.name = name;
        this.exercises = exercise;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Дата: ").append(workoutDate).append("\n");
        sb.append("Название: ").append(name).append("\n");
        sb.append("Упражнения:").append("\n");
        for (int i = 0; i < exercises.length; i++){
            sb.append(" ").append(i+1).append(") ").append(exercises[i]).append("\n\n");
        }
        return sb.toString();
    }
}
