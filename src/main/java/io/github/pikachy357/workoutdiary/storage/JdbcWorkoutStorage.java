package io.github.pikachy357.workoutdiary.storage;

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

    String sql = "INSERT INTO workouts (workout_date, name) VALUES (?, ?) RETURNING id";

    public void save(Workout workout) throws SQLException {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setObject(1, workout.getWorkoutDate());
            statement.setString(2, workout.getName());
            try(ResultSet rows = statement.executeQuery()){
                rows.next();
                int workoutId = rows.getInt("id");
            }

        }

    }
}


