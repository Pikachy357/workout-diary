public class Exercise {
    private String name;
    private String description;
    private ExerciseSet [] sets;

    public Exercise(String name, String description, ExerciseSet[] sets){
        this.name = name;
        this.description = description;
        this.sets = sets;
    }

    public void showExercise(){
        for (int i = 0; i < sets.length; i++){
            sets[i].showExerciseSet();
        }
    }
}