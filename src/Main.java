import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Main {
    public static void main(String[] args){
        Map<String, Exercise> catalog = new HashMap<>();

        List<ExerciseSet> sets = new ArrayList<>();
        sets.add(new ExerciseSet(80, 10,  5, "norm"));
        sets.add(new ExerciseSet(80, 9,  7, "hard"));
        catalog.put("Приседания", new Exercise("Приседания", "Приседания со штангой", sets));

        List<ExerciseSet> sets2 = new ArrayList<>();
        sets2.add(new ExerciseSet(100, 6,  7, "norm"));
        sets2.add(new ExerciseSet(110, 4,  9, "hard"));
        catalog.put("Жим", new Exercise("Жим", "Жим лёжа со штангой", sets2));

        List<Exercise> exercises = new ArrayList<>();
        exercises.add(catalog.get("Приседания"));
        exercises.add(catalog.get("Жим"));

        List <Workout> workouts = new ArrayList<>();
        workouts.add(new Workout(LocalDate.of(2026, 6, 1), "Жим и присед", exercises));

        try {
            ExerciseSet firts = new ExerciseSet(-50, 10, 5, "ой");
            System.out.println("Подход Создан: " + firts);
        } catch (IllegalArgumentException e){
            System.out.println("не создался подход из-за ошибки: " + e.getMessage());
        }
        System.out.println("Упраженений в тренировке: " + workouts.get(0).getExercises().size());
        System.out.println("Первое упражнение " + workouts.get(0).getExercises().get(0).getName());
        System.out.print("сумарный вес за первое упражнение: ");
        List<ExerciseSet> firstExerciseSets = workouts.get(0).getExercises().get(0).getSets();
        double total = 0;
        for (ExerciseSet set:firstExerciseSets){
            total += set.getReps() * set.getWeight();
        }
        System.out.println(total);

        double [] rawWeights = {80, 100, -50, 90};
        List <ExerciseSet> setfirts = new ArrayList<>();
        for (double weight: rawWeights){
            try {
                setfirts.add(new ExerciseSet(weight, 10, 5, "set"));
                System.out.println("Подход с весом " + weight + " создан");
            } catch (IllegalArgumentException e) {
                System.out.println("Вес " + weight + " пропущен: " + e.getMessage());
            }
        }
        System.out.println("Успешно создано подходов: " + setfirts.size());

        System.out.println("выводим по каталогу через get (ключ Приседания) -> " + catalog.get("Приседания") );
        System.out.println("выводим по каталогу через get (ключ Жим) -> " + catalog.get("Жим") );
        System.out.println("выводим по каталогу через get (ключ Становая) -> " + catalog.get("Становая") );
        String query = "прыжок";
        if (catalog.containsKey(query)){
            Exercise found = catalog.get(query);
            System.out.println( "Пользователь запросил " + query + "Название :" + found.getName() + "Описание: " + found.getDescription());
        } else{
            System.out.println("Пользователь запросил *" + query + "* \nУпражнение *" + query + "* не найдено в справочнике");
        }

        query = "Жим";
        if (catalog.containsKey(query)){
            Exercise found = catalog.get(query);
            System.out.println( "Пользователь запросил *" + query + "*\nНазвание: " + found.getName() + "\nОписание: " + found.getDescription());
        } else{
            System.out.println("Пользователь запросил *" + query + "* \nУпражнение *" + query + "* не найдено в справочнике");
        }

    }
}

