package io.github.pikachy357.workoutdiary.storage;

import io.github.pikachy357.workoutdiary.model.Exercise;
import io.github.pikachy357.workoutdiary.model.ExerciseSet;
import io.github.pikachy357.workoutdiary.model.Workout;

import java.sql.*;


public class JdbcWorkoutStorage {
    public JdbcWorkoutStorage(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }
    private final String url;
    private final String user;
    private final String password;



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


