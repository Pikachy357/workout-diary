package io.github.pikachy357.workoutdiary.service;

import io.github.pikachy357.workoutdiary.model.Exercise;
import io.github.pikachy357.workoutdiary.model.ExerciseSet;
import io.github.pikachy357.workoutdiary.model.Workout;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StatisticsServiceTest {

    private final StatisticsService statistics = new StatisticsService();

    @Test
    void bestWeightIsTakenAcrossAllWorkouts() {
        // Понедельник: жим (лучший 100) и приседания (120)
        Exercise pressMonday = new Exercise("Жим", "Лёжа", new ArrayList<>());
        pressMonday.addSet(new ExerciseSet(80, 10, 5, "разминка"));
        pressMonday.addSet(new ExerciseSet(100, 5, 8, "рабочий"));
        Exercise squat = new Exercise("Приседания", "Со штангой", new ArrayList<>());
        squat.addSet(new ExerciseSet(120, 5, 8, "рабочий"));
        Workout monday = new Workout(LocalDate.of(2026, 11, 9), "Понедельник", List.of(pressMonday, squat));

        // Пятница: жим 105 — новый рекорд
        Exercise pressFriday = new Exercise("Жим", "Лёжа", new ArrayList<>());
        pressFriday.addSet(new ExerciseSet(105, 3, 9, "рекорд"));
        Workout friday = new Workout(LocalDate.of(2026, 11, 13), "Пятница", List.of(pressFriday));

        Map<String, Double> best = statistics.bestWeightByExercise(List.of(monday, friday));

        assertEquals(2, best.size());              // два разных упражнения
        assertEquals(105.0, best.get("Жим"));       // рекорд из пятницы, а не 100
        assertEquals(120.0, best.get("Приседания"));
    }

    @Test
    void emptyHistoryGivesNoRecords() {
        assertTrue(statistics.bestWeightByExercise(List.of()).isEmpty());
    }
}
