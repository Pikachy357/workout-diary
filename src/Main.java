import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Workout workout = readWorkout(scanner);
        System.out.println(workout);

    }
    public static int readInt(Scanner scanner, String prompt, Predicate<Integer> rule, String errorMessage) {
        while (true) {
            try {
                System.out.println(prompt);
                int value = scanner.nextInt();
                scanner.nextLine();
                if (rule.test(value))
                {
                    return value;
                }
                System.out.println(errorMessage);
            } catch (InputMismatchException e) {
                System.out.println("Вы ввели не целое число, попробуйте ещё раз.");
                scanner.nextLine();
            }

        }
    }

    public static double readDouble(Scanner scanner, String prompt, Predicate<Double> rule, String errorMessage) {
        while (true) {
            try {
                System.out.println(prompt);
                double value = scanner.nextDouble();
                scanner.nextLine();
                if (rule.test(value))
                {
                    return value;
                }
                System.out.println(errorMessage);
            } catch (InputMismatchException e) {
                System.out.println("Вы ввели не число, попробуйте ещё раз.");
                scanner.nextLine();
            }
        }
    }
    public static String readString(Scanner scanner, String prompt) {
        while (true) {
            System.out.println(prompt);
            String value = scanner.nextLine();
            if (!value.isBlank()){
                return value;
            }
            System.out.println("Ошибка, пустая строка, введите ещё раз.");
        }
    }

    public static ExerciseSet readExerciseSet(Scanner scanner){
        while(true) {
            double weight = readDouble(scanner, "Введите Вес:", v -> v>=0, "Ошибка, вес должен быть не отрицательным.");
            int reps = readInt(scanner, "Введите кол-во раз:", v -> v >= 1 && v <= 1000, "Ошибка, диапазон может быть от 1 до 1000.");
            int difficulty = readInt(scanner, "Введите сложность (1-10):", v -> v>=1 && v<=10, "Ошибка, диапазон может быть от 1 до 10.");
            String comment = readString(scanner, "Введите коментарий: ");
            try{
                return new ExerciseSet(weight, reps, difficulty, comment);
            }
            catch (IllegalArgumentException e){
                System.out.println("Ошибка: " + e.getMessage() + " введите заново подход");
            }
        }
    }

    public static boolean readYesNo(Scanner scanner, String prompt){
        System.out.println(prompt);
        while (true) {
            String enter = readString(scanner,"Введите 'да' или 'нет' :").trim();
            boolean yes = enter.equalsIgnoreCase("да");
            boolean no = enter.equalsIgnoreCase("нет");
            if (yes) {
                return yes;
            }
            if (no) {
                return !no;
            }
            System.out.println("Вы ввели не верно.");
        }
    }

    public static Exercise readExercise(Scanner scanner){
        String name = readString(scanner, "Введите название упражнения: ");
        String description = readString(scanner, "Введите описание упражнения: ");
        Exercise exercise = new Exercise(name, description, new ArrayList<>());
        int count = 1;
        do {
            System.out.println("Ввод подхода №" + count++);
            exercise.addSet(readExerciseSet(scanner));
        }while (readYesNo(scanner,"Подход введён, ввести ещё подход ?"));
        return exercise;
    }

    public static Workout readWorkout(Scanner scanner){
        System.out.println("Идёт ввод тренировки:");
        String name = readString(scanner, "Введите название тренировки");
        Workout workout = new Workout(LocalDate.now(), name, new ArrayList<>());
        int count = 1;
        do {
            System.out.println("Идёт ввод упражнения №" + count++);
            workout.addExercise(readExercise(scanner));
        }while (readYesNo(scanner, "Упражнение введено, хотите ввести ещё упражнение ?"));
        return workout;
    }

    public static void getStatisticWorkout(Workout workout){
        System.out.println("Общий тонаж за тренировку: " + workout.getTotalVolume());

    }

    public static void getStatisticExercise(Exercise exercise){
        StringBuilder sb = new StringBuilder();
        sb.append("Упражнение : ").append(exercise.getName());
        sb.append("Тонаж за упражнение : ").append(exercise.getTotalVolume()).append("\n");
        sb.append("Max вес упр")
    }

}


