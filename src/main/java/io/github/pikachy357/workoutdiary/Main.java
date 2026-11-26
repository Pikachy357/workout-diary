package io.github.pikachy357.workoutdiary;

import io.github.pikachy357.workoutdiary.model.Exercise;
import io.github.pikachy357.workoutdiary.model.ExerciseSet;
import io.github.pikachy357.workoutdiary.model.Workout;
import io.github.pikachy357.workoutdiary.service.StatisticsService;
import io.github.pikachy357.workoutdiary.storage.JdbcWorkoutStorage;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

public class Main {
    private static final Predicate<String> TEXT_RULE = v -> !v.contains(";") && !v.isBlank();
    private static final String TEXT_ERROR_MESSAGE = "Ошибка ввода, вы ввели пустую строку или использовали запрещённый символ ';' .";
    private static final String URL = "jdbc:postgresql://localhost:5432/workout_diary";
    private static final String USER = "postgres";

    public static void main(String[] args) {
        String password = System.getenv("DB_PASSWORD");
        if (password == null) {
            System.out.println("Не задана переменная окружения DB_PASSWORD");
            return;
        }

        JdbcWorkoutStorage storage = new JdbcWorkoutStorage(URL, USER, password);
        List<Workout> history;

        try {
            history = storage.loadAll();
            System.out.println("Загружено тренировок: " + history.size());
        } catch (SQLException e) {
            System.out.println("Ошибка базы данных: " + e.getMessage());
            return;
        }

        if (!history.isEmpty()) {
            System.out.println("Вывод истории: ");
            history.forEach(w -> System.out.println(w));
            StatisticsService statistics = new StatisticsService();
            System.out.println("Личные рекорды:");
            statistics.bestWeightByExercise(history)
                    .forEach((name, weight) -> System.out.println(name + ": " + weight + " кг"));
        }

        Scanner scanner = new Scanner(System.in);
        Workout workout = readWorkout(scanner);
        System.out.println(workout);
        System.out.println(workout.getStatistics());

        try {
            storage.save(workout);
            System.out.println("Сохранено в базе данных: URL = "+ URL + "USER = " + USER);
        } catch (SQLException e) {
            System.out.println("Ошибка базы данных: " + e.getMessage());
        }

    }

    public static int readInt(Scanner scanner, String prompt, Predicate<Integer> rule, String errorMessage) {
        while (true) {
            try {
                System.out.println(prompt);
                int value = scanner.nextInt();
                scanner.nextLine();
                if (rule.test(value)) {
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
                if (rule.test(value)) {
                    return value;
                }
                System.out.println(errorMessage);
            } catch (InputMismatchException e) {
                System.out.println("Вы ввели не число, попробуйте ещё раз.");
                scanner.nextLine();
            }
        }
    }

    public static String readString(Scanner scanner, String prompt, Predicate<String> rule, String errorMessage) {
        while (true) {
            System.out.println(prompt);
            String value = scanner.nextLine();
            if (rule.test(value)) {
                return value;
            }
            System.out.println(errorMessage);
        }
    }

    public static ExerciseSet readExerciseSet(Scanner scanner) {
        while (true) {
            double weight = readDouble(scanner, "Введите Вес:", v -> v >= 0, "Ошибка, вес должен быть не отрицательным.");
            int reps = readInt(scanner, "Введите кол-во раз:", v -> v >= 1 && v <= 1000, "Ошибка, диапазон может быть от 1 до 1000.");
            int difficulty = readInt(scanner, "Введите сложность (1-10):", v -> v >= 1 && v <= 10, "Ошибка, диапазон может быть от 1 до 10.");
            String comment = readString(scanner, "Введите коментарий (запрещенный символ ';' ): ", TEXT_RULE, TEXT_ERROR_MESSAGE);
            try {
                return new ExerciseSet(weight, reps, difficulty, comment);
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: " + e.getMessage() + " введите заново подход");
            }
        }
    }

    public static boolean readYesNo(Scanner scanner, String prompt) {
        System.out.println(prompt);
        while (true) {
            String enter = readString(scanner, "Введите 'да' или 'нет' :", v -> !v.isBlank(), "Ошибка ввода, вы ввели пустую строку.").trim();
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

    public static Exercise readExercise(Scanner scanner) {
        String name = readString(scanner, "Введите название упражнения (запрещенный символ ';' ): ", TEXT_RULE, TEXT_ERROR_MESSAGE);
        String description = readString(scanner, "Введите описание упражнения (запрещенный символ ';' ): ", TEXT_RULE, TEXT_ERROR_MESSAGE);
        Exercise exercise = new Exercise(name, description, new ArrayList<>());
        int count = 1;
        do {
            System.out.println("Ввод подхода №" + count++);
            exercise.addSet(readExerciseSet(scanner));
        } while (readYesNo(scanner, "Подход введён, ввести ещё подход ?"));
        return exercise;
    }

    public static Workout readWorkout(Scanner scanner) {
        System.out.println("Идёт ввод тренировки:");
        String name = readString(scanner, "Введите название тренировки (запрещенный символ ';' ): ", TEXT_RULE, TEXT_ERROR_MESSAGE);
        Workout workout = new Workout(LocalDate.now(), name, new ArrayList<>());
        int count = 1;
        do {
            System.out.println("Идёт ввод упражнения №" + count++);
            workout.addExercise(readExercise(scanner));
        } while (readYesNo(scanner, "Упражнение введено, хотите ввести ещё упражнение ?"));
        return workout;
    }

}


