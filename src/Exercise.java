public class Exercise {
    private String name;
    private String description;
    private ExerciseSet [] sets;

    public Exercise(String name, String description, ExerciseSet[] sets){
        this.name = name;
        this.description = description;
        this.sets = sets;
    }
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Упражнение: ").append(name).append("\n");
        sb.append("Описание: ").append(description).append("\n");
        sb.append("Подходы").append("\n");
        for (int i = 0; i < sets.length; i++){
            sb.append(" ").append(i+1).append(") ").append(sets[i]).append("\n");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}