package Millionaire;

import java.util.ArrayList;

public class EasyRound extends Round {
    // Constant
    private static final int NUM_QUESTIONS = 3;
    //private static final int QUESTION_1_VALUE =

    public EasyRound(ArrayList<Question> questions) {
        super(questions);
    }

    public static int getNumQuestions() {
        return NUM_QUESTIONS;
    }
}
