import java.time.LocalDate;

public class Main {
    public static void main(String[] args){

        ExerciseSet [] sets = new ExerciseSet[2];
        sets[0] = new ExerciseSet(80, 10,  5, "norm");
        sets[1] = new ExerciseSet(80, 9,  7, "hard");
        Exercise[] exercises = new Exercise[2];
        exercises[0] = new Exercise("Приседания", "Приседания со штангой", sets);
        ExerciseSet [] sets2 = new ExerciseSet[2];
        sets2[0] = new ExerciseSet(100, 6,  7, "norm");
        sets2[1] = new ExerciseSet(110, 4,  9, "hard");
        exercises[1] = new Exercise("Жим", "Жим лёжа со штангой", sets2);
        Workout workout = new Workout(LocalDate.of(2026, 6, 1), "Жим и присед", exercises);
        System.out.println("Упраженений в тренировке: " + workout.getExercises().length);
        System.out.println("Первое упражнение " + workout.getExercises()[0].getName());
        System.out.print("сумарный вес за первое упражнение: ");
        ExerciseSet [] firstExerciseSets = workout.getExercises()[0].getSets();
        double total = 0;
        for (ExerciseSet set:firstExerciseSets){
            total += set.getReps() * set.getWeight();
        }
        System.out.println(total);

    }
}
