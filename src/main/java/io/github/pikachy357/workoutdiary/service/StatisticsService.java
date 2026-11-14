package io.github.pikachy357.workoutdiary.service;

import io.github.pikachy357.workoutdiary.model.Exercise;
import io.github.pikachy357.workoutdiary.model.Workout;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class StatisticsService {

    public Map<String, Double> bestWeightByExercise(List<Workout> workouts) {
        Map<String, Double> best = new TreeMap<>();
        for (Workout workout: workouts){
            for (Exercise exercise: workout.getExercises()){
                String name = exercise.getName();
                Double max =exercise.getMaxWeight();
                if (!best.containsKey(name) || max > best.get(name)) {
                        best.put(name,max);
                }
            }
        }
        return best;
    }
}