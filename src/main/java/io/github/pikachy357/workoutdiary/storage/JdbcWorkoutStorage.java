package io.github.pikachy357.workoutdiary.storage;

import io.github.pikachy357.workoutdiary.model.Exercise;
import io.github.pikachy357.workoutdiary.model.ExerciseSet;
import io.github.pikachy357.workoutdiary.model.Workout;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class JdbcWorkoutStorage {
    public JdbcWorkoutStorage(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }
    private final String url;
    private final String user;
    private final String password;

    public List<Workout> loadAll() throws SQLException{
        String sql = "SELECT * FROM workouts";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rows = statement.executeQuery()) {
            List<Workout> workouts = new ArrayList<>();
            while (rows.next()) {
                int workouts_id = rows.getInt("id");
                LocalDate date = rows.getObject("workout_date", LocalDate.class);
                String name = rows.getString("name");
                workouts.add(new Workout(date,name, loadExercise(workouts_id)));
            }
            return workouts;
        } catch (SQLException e) {
            System.out.println("Ошибка базы данных: " + e.getMessage());
        }
        return null;
    }

    public List<Exercise> loadExercise(int id) throws SQLException{
        String sql = "SELECT * FROM exercises WHERE workout_id = ?";
        List<Workout> workouts = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet rows = statement.executeQuery()){
                List <Exercise> exercises = new ArrayList<>();
                while (rows.next()) {
                int exercise_id = rows.getInt("id");
                String name = rows.getString("name");
                String description = rows.getString("description");
                    exercises.add( new Exercise(name, description, loadSet(exercise_id)));
                }
                return exercises;
            }
        } catch (SQLException e) {
            System.out.println("Ошибка базы данных: " + e.getMessage());
        }
        return null;
    }

    public List<ExerciseSet> loadSet(int id) throws SQLException{
        String sql = "SELECT * FROM exercise_sets WHERE exercise_id = ?";
        List<Workout> workouts = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet rows = statement.executeQuery()){
                List<ExerciseSet> sets = new ArrayList<>();
                while (rows.next()) {
                double weight = rows.getDouble("weight");
                int reps = rows.getInt("reps");
                int difficulty = rows.getInt("difficulty");
                String comment = rows.getString("comment");
                    sets.add(new ExerciseSet(weight,reps,difficulty,comment));
                }
                return sets;
            }
        } catch (SQLException e) {
            System.out.println("Ошибка базы данных: " + e.getMessage());
        }
        return null;
    }

    public void save(Workout workout) throws SQLException {
        try(Connection connection = DriverManager.getConnection(url, user, password);){
            connection.setAutoCommit(false);
            try {
                insertWorkoutWithExercises(workout, connection);
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw e;
            }
        }
    }

    private void insertWorkoutWithExercises(Workout workout, Connection connection) throws SQLException {
        int workoutId = insertWorkout(connection, workout);
        for (Exercise exercise : workout.getExercises()){
            int exerciseId = insertExercise(connection, exercise, workoutId);
            for (ExerciseSet set: exercise.getSets()) {
                insertExerciseSet(connection, set, exerciseId);
            }
        }
    }

    private int insertWorkout(Connection connection, Workout workout) throws SQLException {
        String sql = "INSERT INTO workouts (workout_date, name) VALUES (?, ?) RETURNING id";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, workout.getWorkoutDate());
            statement.setString(2, workout.getName());
            try (ResultSet rows = statement.executeQuery()) {
                rows.next();
                return rows.getInt("id");
            }
        }
    }
    private int insertExercise(Connection connection, Exercise exercise, int workoutId) throws SQLException {
        String sql = "INSERT INTO exercises (workout_id, name, description) VALUES (?, ?, ?) RETURNING id";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, workoutId);
            statement.setString(2, exercise.getName());
            statement.setString(3, exercise.getDescription());
            try (ResultSet rows = statement.executeQuery()) {
                rows.next();
                return rows.getInt("id");
            }
        }
    }
    private void insertExerciseSet(Connection connection, ExerciseSet set, int exerciseId) throws SQLException {
        String sql = "INSERT INTO exercise_sets (exercise_id, weight, reps, difficulty, comment) VALUES (?, ?, ?, ?, ?);";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, exerciseId);
            statement.setDouble(2, set.getWeight());
            statement.setInt(3, set.getReps());
            statement.setInt(4, set.getDifficulty());
            statement.setString(5,set.getComment());
            statement.executeUpdate();
        }

    }

}


