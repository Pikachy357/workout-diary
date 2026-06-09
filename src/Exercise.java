public class Exercise {
    private String name;
    private String description;
    private ExerciseSet [] sets;

    public Exercise(String name, String description, ExerciseSet[] sets){
        if (name == null || name.isBlank()){
            throw new IllegalArgumentException("имя null или пустое");
        }
        if (description == null || description.isBlank()){
            throw new IllegalArgumentException(" описание не может быть null");
        }
        if (sets == null) {
            throw new IllegalArgumentException(" походы не могут быть null");
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

    public ExerciseSet [] getSets(){
        return sets;
    }
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Упражнение: ").append(name).append("\n");
        sb.append("Описание: ").append(description).append("\n");
        sb.append("Подходы:").append("\n");
        for (int i = 0; i < sets.length; i++){
            sb.append(" ").append(i+1).append(") ").append(sets[i]).append("\n");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}