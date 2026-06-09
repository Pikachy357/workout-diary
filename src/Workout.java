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

    public Workout(LocalDate workoutDate, String name, Exercise [] exercise){
        if (workoutDate == null){
            throw new IllegalArgumentException(" дата не может быть null" );
        }
        if (name == null || name.isBlank()){
            throw new IllegalArgumentException("имя null или пустое");
        }
        if (exercise == null) {
            throw new IllegalArgumentException(" Упражнения не может быть null");
        }
        this.workoutDate = workoutDate;
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
