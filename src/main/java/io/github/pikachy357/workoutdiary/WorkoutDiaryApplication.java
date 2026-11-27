package io.github.pikachy357.workoutdiary;

import io.github.pikachy357.workoutdiary.storage.JdbcWorkoutStorage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class WorkoutDiaryApplication {
    private static final String URL = "jdbc:postgresql://localhost:5432/workout_diary";
    private static final String USER = "postgres";
    public static void main(String[] args) {
        SpringApplication.run(WorkoutDiaryApplication.class, args);
    }
    @Bean
    public JdbcWorkoutStorage workoutStorage() {
        String password = System.getenv("DB_PASSWORD");
        return  new JdbcWorkoutStorage(URL, USER, password);
    }
}