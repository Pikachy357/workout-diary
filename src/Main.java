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
        System.out.println(  workout + "\n Распечатана тренировка");
    }
}
