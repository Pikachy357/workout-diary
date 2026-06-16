import java.util.ArrayList;
import java.util.List;

public class Exercise {
    private String name;
    private String description;
    private List<ExerciseSet> sets;

    public Exercise(String name, String description, List<ExerciseSet> sets){
        if (name == null || name.isBlank()){
            throw new IllegalArgumentException("Имя null или пустое");
        }
        if (description == null || description.isBlank()){
            throw new IllegalArgumentException("Описание не может быть null");
        }
        if (sets == null) {
            throw new IllegalArgumentException("Список походов не может быть null");
        }
        this.name = name;
        this.description = description;
        this.sets = sets;
    }
    public String getName(){
        return name;
    }
    public String getDescription(){
        return description;
    }

    public List <ExerciseSet> getSets(){
        return sets;
    }

    public void addSet(ExerciseSet set){
        if (set == null){
            throw new IllegalArgumentException("Нельзя добавть null подходов");
        }
        sets.add(set);
    }
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Упражнение: ").append(name).append("\n");
        sb.append("Описание: ").append(description).append("\n");
        sb.append("Подходы:").append("\n");
        int number = 1;
        for (ExerciseSet set: sets){
            sb.append(" ").append(number).append(") ").append(set).append("\n");
            number++;
        }
        if (!sets.isEmpty()) {sb.deleteCharAt(sb.length() - 1);}
        return sb.toString();
    }
}