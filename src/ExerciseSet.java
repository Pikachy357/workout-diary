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

    public void showExerciseSet(){
        System.out.println("Вес - " + weight + ", Повторения - " + reps + ", Сложность - " + difficulty + ", Коментарий - " + comment);
    }

    public static void main(String[] args){

        ExerciseSet [] sets = new ExerciseSet[2];
        sets[0] = new ExerciseSet(80, 10,  5, "norm");
        sets[1] = new ExerciseSet(80, 9,  7, "hard");
        Exercise exercise = new Exercise("Приседания", "Приседания со штангой", sets);
        exercise.showExercise();
        System.out.println("Подходы распечатаны");
    }
}

