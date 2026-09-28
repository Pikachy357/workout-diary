import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Идёт ввод упражнения: ");
        Exercise exercise = new Exercise(readString(scanner, "Введите название упражения: "), readString(scanner, "Введите описание упражения: "), new ArrayList<>());
        int count = 1;
        do {
            System.out.println("Ввод подхода №" + count++);
            exercise.addSet(readExerciseSet(scanner));
        }while (readYesNo(scanner,"Подход введён, ввести ещё подход ?"));

        System.out.println(exercise);
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
            System.out.println("Идёт ввод данных подхода.");
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
            String enter = readString(scanner,"Введите 'да' или 'нет' :");
            boolean yes = enter.trim().equalsIgnoreCase("да");
            boolean no = enter.trim().equalsIgnoreCase("нет");
            if (yes) {
                return yes;
            }
            if (no) {
                return !no;
            }
            System.out.println("Вы ввели не верно.");
        }
    }
       /* Map<String, Exercise> catalog = new HashMap<>();

        List<ExerciseSet> sets = new ArrayList<>();
        sets.add(new ExerciseSet(80, 10, 5, "norm"));
        sets.add(new ExerciseSet(80, 9, 7, "hard"));
        catalog.put("Приседания", new Exercise("Приседания", "Приседания со штангой", sets));

        List<ExerciseSet> sets2 = new ArrayList<>();
        sets2.add(new ExerciseSet(100, 6, 7, "norm"));
        sets2.add(new ExerciseSet(110, 4, 9, "hard"));
        catalog.put("Жим", new Exercise("Жим", "Жим лёжа со штангой", sets2));

        List<Exercise> exercises = new ArrayList<>();
        exercises.add(catalog.get("Приседания"));
        exercises.add(catalog.get("Жим"));

        List<Workout> workouts = new ArrayList<>();
        workouts.add(new Workout(LocalDate.of(2026, 6, 1), "Жим и присед", exercises));


        System.out.println("Упраженений в тренировке: " + workouts.get(0).getExercises().size());
        System.out.println("Первое упражнение " + workouts.get(0).getExercises().get(0).getName());

        // проверка методов: getTotalVolume, getAverageDifficulty, getMaxWeight
        System.out.print("Тоннаж первого упражнения: " + workouts.get(0).getExercises().get(0).getTotalVolume());
        System.out.println("средняя сложность за Жим: " + catalog.get("Жим").getAverageDifficulty());
        System.out.println("максимальный вес за Приседания: " + catalog.get("Приседания").getMaxWeight());

        // запрос 1 по каталогу
        String query = "прыжок";
        if (catalog.containsKey(query)) {
            Exercise found = catalog.get(query);
            System.out.println("Пользователь запросил " + query + "Название :" + found.getName() + "Описание: " + found.getDescription());
        } else {
            System.out.println("Пользователь запросил *" + query + "* \nУпражнение *" + query + "* не найдено в справочнике");
        }
        // запрос 2 по каталогу
        query = "Жим";
        if (catalog.containsKey(query)) {
            Exercise found = catalog.get(query);
            System.out.println("Пользователь запросил *" + query + "*\nНазвание: " + found.getName() + "\nОписание: " + found.getDescription());
        } else {
            System.out.println("Пользователь запросил *" + query + "* \nУпражнение *" + query + "* не найдено в справочнике");
        }

        // краткое описание всех первых
        System.out.println("\n" + sets.get(0).describe());
        System.out.println("\n" + exercises.get(0).describe());
        System.out.println("\n" + workouts.get(0).describe()); */
}


