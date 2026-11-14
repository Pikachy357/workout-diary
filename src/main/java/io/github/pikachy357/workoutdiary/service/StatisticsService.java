package io.github.pikachy357.workoutdiary.service;

import io.github.pikachy357.workoutdiary.model.Exercise;
import io.github.pikachy357.workoutdiary.model.Workout;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class StatisticsService {

    public Map<String, Double> bestWeightByExercise(List<Workout> workouts) {
        Map<String, Double> best = new TreeMap<>();
        for (Workout w: workouts){
            for (Exercise e: w.getExercises()){
                if (best.containsKey(e.getName())) {
                    if (best.get(e.getName()) > e.getMaxWeight()) {
                        continue;
                    }
                }
                best.put(e.getName(), e.getMaxWeight());
            }
        }
        return best;
    }
}