public class ExerciseSet implements Describable{
    private double weight;
    private int reps;
    private int difficulty;
    private String comment;

    public ExerciseSet(double weight, int reps, int difficulty, String comment) {
        if (weight < 0){
            throw new IllegalArgumentException("Отрицательный Вес " + weight);
        }

        if (reps <= 0){
            throw new IllegalArgumentException("повторений нет или они отрицательные " + reps);
        }

        if (difficulty < 1 || difficulty > 10){
            throw new IllegalArgumentException("Сложность вне диапазона от 1 до 10 " + difficulty);
        }

        this.weight = weight;
        this.reps = reps;
        this.difficulty = difficulty;
        this.comment = comment;
    }

    public double getWeight() {
        return weight;
    }

    public int getReps() {
        return reps;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public String getComment() {
        return comment;
    }

    @Override
    public String toString(){
        return "Вес - " + weight + ", Повторения - " + reps + ", Сложность - " + difficulty + ", Коментарий - " + comment;
    }

    @Override
    public String describe() {
        return "Подход: " + weight + "кг x" + reps;
    }
}

