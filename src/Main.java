import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){

        List<ExerciseSet> sets = new ArrayList<>();
        sets.add(new ExerciseSet(80, 10,  5, "norm"));
        sets.add(new ExerciseSet(80, 9,  7, "hard"));
        Exercise squat = new Exercise("Приседания", "Приседания со штангой", sets);

        List<ExerciseSet> sets2 = new ArrayList<>();
        sets2.add(new ExerciseSet(100, 6,  7, "norm"));
        sets2.add(new ExerciseSet(110, 4,  9, "hard"));
        Exercise press = new Exercise("Жим", "Жим лёжа со штангой", sets2);

        List<Exercise> exercises = new ArrayList<>();
        exercises.add(squat);
        exercises.add(press);

        List <Workout> workouts = new ArrayList<>();
        workout.add(new Workout(LocalDate.of(2026, 6, 1), "Жим и присед", exercises));

        try {
            ExerciseSet firts = new ExerciseSet(-50, 10, 5, "ой");
            System.out.println("Подход Создан: " + firts);
        } catch (IllegalArgumentException e){
            System.out.println("не создался подход из-за ошибки: " + e.getMessage());
        }
        System.out.println("Упраженений в тренировке: " + workouts.get(0).getExercises().size());
        System.out.println("Первое упражнение " + workouts.get(0).getExercises().get(0).getName());
        System.out.print("сумарный вес за первое упражнение: ");
        ExerciseSet [] firstExerciseSets = workouts.get(0).getExercises().get(0).getSets();
        double total = 0;
        for (ExerciseSet set:firstExerciseSets){
            total += set.getReps() * set.getWeight();
        }
        System.out.println(total);

        double [] rawWeights = {80, 100, -50, 90};
        ExerciseSet [] setfirts = new ExerciseSet[rawWeights.length];
        int i = 0;
        for (double weight: rawWeights){
            try {
                setfirts[i++]= new ExerciseSet(weight, 10, 5, "set");
                System.out.println("Подход с весом " + weight + " создан");
            } catch (IllegalArgumentException e) {
                System.out.println("Вес " + weight + " пропущен: " + e.getMessage());
            }
        }
    }
}

