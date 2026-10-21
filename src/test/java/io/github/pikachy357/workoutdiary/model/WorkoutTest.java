package io.github.pikachy357.workoutdiary.model;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

 class WorkoutTest {
     @Test
     void exercisesCannotBeModifiedFromOutside(){
         Workout press = new Workout(LocalDate.now(), "Тренировка в воскресенье", new ArrayList<>());

         assertThrows(UnsupportedOperationException.class,
                 () -> press.getExercises().add(new Exercise("Жим", "с прыжком",  new ArrayList<>())));
     }
     @Test
     void workoutKeepsOwnOfExerciseList() {
         List<Exercise> exercises = new ArrayList<>();
         Workout press = new Workout(LocalDate.now(), "Тренировка в воскресенье", exercises);
         exercises.add(new Exercise("Жим", "с прыжком",new ArrayList<>()));
         assertEquals(0,press.getExercises().size());
     }
}
