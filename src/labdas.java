import java.util.ArrayList;
import java.util.List;

interface IntOperation {
    int apply(int x);
}

public class labdas {
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
    }


}
