public class ExerciseSet {
    private double weight;
    private int reps;
    private int difficulty;
    private String comment;

    public ExerciseSet(double weight, int reps, int difficulty, String comment) {
        this.weight = weight;
        this.reps = reps;
        this.difficulty = difficulty;
        this.comment = comment;
    }
    @Override
    public String toString(){
        return "Вес - " + weight + ", Повторения - " + reps + ", Сложность - " + difficulty + ", Коментарий - " + comment;
    }


}

