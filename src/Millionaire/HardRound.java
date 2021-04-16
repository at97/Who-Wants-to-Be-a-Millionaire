package Millionaire;

import java.util.ArrayList;

public class HardRound extends Round{
    // Constant
    private static final int NUM_QUESTIONS = 5;

    public HardRound(ArrayList<Question> questions) {
        super(questions);
    }

    public static int getNumQuestions() {
        return NUM_QUESTIONS;
    }
}
