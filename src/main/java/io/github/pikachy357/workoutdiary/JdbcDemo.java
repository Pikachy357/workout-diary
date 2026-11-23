package io.github.pikachy357.workoutdiary;

import io.github.pikachy357.workoutdiary.model.Workout;
import io.github.pikachy357.workoutdiary.storage.JdbcWorkoutStorage;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import static io.github.pikachy357.workoutdiary.Main.readWorkout;

public class JdbcDemo {

    private static final String URL = "jdbc:postgresql://localhost:5432/workout_diary";
    private static final String USER = "postgres";

    public static void main(String[] args) {
        String password = System.getenv("DB_PASSWORD");
        if (password == null) {
            System.out.println("Не задана переменная окружения DB_PASSWORD");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        Workout workout = readWorkout(scanner);
        JdbcWorkoutStorage jdbcWorkoutStorage = new JdbcWorkoutStorage(URL,USER,password);

        try {
            jdbcWorkoutStorage.save(workout);
        }
        catch (SQLException e) {
            System.out.println("Ошибка базы данных: " + e.getMessage());
        }


        String sql = "SELECT * FROM workouts";
        try (Connection connection = DriverManager.getConnection(URL, USER, password);
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rows = statement.executeQuery()) {
            while (rows.next()) {
                int id = rows.getInt("id");
                LocalDate date = rows.getObject("workout_date", LocalDate.class);
                String name = rows.getString("name");
                System.out.println(id + " | " + date + " | " + name);
            }
        } catch (SQLException e) {
            System.out.println("Ошибка базы данных: " + e.getMessage());
        }

        sql = "SELECT * FROM exercises";
        try (Connection connection = DriverManager.getConnection(URL, USER, password);
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rows = statement.executeQuery()) {
            while (rows.next()) {
                int id = rows.getInt("id");
                int workout_id = rows.getInt("workout_id");
                String name = rows.getString("name");
                String description = rows.getString("description");
                System.out.println(id + " | " + workout_id + " | " + name+ " | " + description);
            }
        } catch (SQLException e) {
            System.out.println("Ошибка базы данных: " + e.getMessage());
        }

        sql = "SELECT * FROM exercise_sets";
        try (Connection connection = DriverManager.getConnection(URL, USER, password);
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rows = statement.executeQuery()) {
            while (rows.next()) {
                int id = rows.getInt("id");
                int exercise_id = rows.getInt("exercise_id");
                double weight = rows.getDouble("weight");
                int reps = rows.getInt("reps");
                int difficulty = rows.getInt("difficulty");
                String comment = rows.getString("comment");
                System.out.println(id + " | " + exercise_id + " | " + weight+ " | " + reps + " | " + difficulty + " | " + comment);
            }
        } catch (SQLException e) {
            System.out.println("Ошибка базы данных: " + e.getMessage());
        }


        sql = "SELECT id, workout_date, name FROM workouts ORDER BY workout_date";

        try (Connection connection = DriverManager.getConnection(URL, USER, password);
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rows = statement.executeQuery()) {
            while (rows.next()) {
                int id = rows.getInt("id");
                LocalDate date = rows.getObject("workout_date", LocalDate.class);
                String name = rows.getString("name");
                System.out.println(id + " | " + date + " | " + name);
            }
        } catch (SQLException e) {
            System.out.println("Ошибка базы данных: " + e.getMessage());
        }
        sql = "SELECT e.name, MAX(s.weight) AS best_weight FROM exercises e JOIN exercise_sets s ON e.id = s.exercise_id GROUP BY e.name ORDER BY e.name";

        try (Connection connection = DriverManager.getConnection(URL, USER, password);
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rows = statement.executeQuery()) {
            while (rows.next()) {
                String name = rows.getString("name");
                double bestWeight = rows.getDouble("best_weight");
                System.out.println(name + " | " + bestWeight);
            }
        } catch (SQLException e) {
            System.out.println("Ошибка базы данных: " + e.getMessage());
        }
    }
}