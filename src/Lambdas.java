import java.util.ArrayList;
import java.util.List;

interface IntOperation {
    int apply(int x);
}

public class Lambdas {
    public static void main(String[] args) {
        IntOperation x, y, z;
        x = e -> e * 2;
        y = e -> e * e;
        z = e -> e + 10;
        System.out.println(x.apply(2));
        System.out.println(y.apply(9));
        System.out.println(z.apply(6));
        Describable d1, d2;
        d1 = new Describable() {
            @Override
            public String describe() {
                return "это анонимный класс " + x.apply(2) + " " + y.apply(9) + " " + z.apply(6);
            }
        };
        System.out.println(d1.describe());
        d2 = () -> "это лябда" + x.apply(2) + " " + y.apply(9) + " " + z.apply(6);
        System.out.println(d2.describe());
        Describable d3 = new ExerciseSet(100, 6, 8, "pumpgood");
        System.out.println(d3.describe());
        List<ExerciseSet> sets = new ArrayList<>();
        sets.add(new ExerciseSet(105, 4, 9, "kek"));
        sets.add(new ExerciseSet(100, 5, 7, "a"));
        sets.add(new ExerciseSet(80, 10, 5, "b"));
        sets.add(new ExerciseSet(120, 3, 9, "c"));
        sets.add(new ExerciseSet(90, 8, 6, "d"));
        sets.sort((a, b) -> Double.compare(a.getWeight(), b.getWeight()));
        System.out.println("Вес по возрастанию: \n");
        sets.forEach(s -> System.out.println(s));
        System.out.println("Вес по убыванию: \n");
        sets.sort((a, b) -> Double.compare(b.getWeight(), a.getWeight()));
        sets.forEach(s -> System.out.println(s));
        System.out.println("сложность по убыванию: \n");
        sets.sort((a, b) -> Integer.compare(b.getDifficulty(), a.getDifficulty()));
        sets.forEach(s -> System.out.println(s));
        System.out.println("повторы по возрастанию: \n");
        sets.sort((a, b) -> Integer.compare(a.getReps(), b.getReps()));
        sets.forEach(s -> System.out.println(s));

        System.out.println("Суммарный поднятый вес по всем подходам: " + sets.stream().mapToDouble(s -> s.getWeight() * s.getReps()).sum());
        System.out.println("Сколько подходов тяжелее 95 кг: " + sets.stream().filter(s -> s.getWeight() > 95).count());
        System.out.println("Максимальный подход: " + sets.stream().mapToDouble(s -> s.getWeight()).max().orElse(0));
        System.out.println("Cредняя сложность: " + sets.stream().mapToInt(s -> s.getDifficulty()).average().orElse(0));
        System.out.println("Комментарии подхожов тяжелее 100 кг: ");
        sets.stream().filter(s -> s.getWeight() > 100).map(s -> s.getComment()).forEach(s -> System.out.println(s));
    }


}
