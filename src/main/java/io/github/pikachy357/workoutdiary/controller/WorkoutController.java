package io.github.pikachy357.workoutdiary.controller;

import io.github.pikachy357.workoutdiary.model.Workout;
import io.github.pikachy357.workoutdiary.storage.JdbcWorkoutStorage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
public class WorkoutController {
    private final JdbcWorkoutStorage storage;

    public WorkoutController (JdbcWorkoutStorage storage){
        this.storage = storage;
    }

    @GetMapping("/workouts")
    public List<Workout> getWorkouts() throws SQLException {
        return storage.loadAll();
    }
}