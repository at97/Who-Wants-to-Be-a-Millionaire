package Millionaire;

import java.util.ArrayList;
import java.util.Random;

public class FiftyFifty extends Lifeline{
    public FiftyFifty() {
    }

    @Override
    public Question useLifeline(Question question) {
        Lifeline.setUsed5050Lifeline(true);
        boolean validIndex1 = false;
        boolean validIndex2 = false;
        int index1 = 0;
        int index2 = 0;
        while (!validIndex1) {
            // Select a non-answer index to remove from question's answers array
            index1 = new Random().nextInt(question.getAllAnswers().size());
            if (index1 != (question.getAnswer() - 1)) {
                // Ensure that the index does not correspond to the answer
                validIndex1 = true;
            }
        }
        // Hide the incorrect answer
        question.getAllAnswers().set(index1, " ");
        while (!validIndex2) {
            // Select another non-answer index to remove from question's answers array
            index2 = new Random().nextInt(question.getAllAnswers().size());
            if ((index2 != (question.getAnswer() - 1)) && (index1 != index2)) {
                // Ensure that the index does not correspond to the answer and that it's not the same as the previous index
                validIndex2 = true;
            }
        }
        // Hide the incorrect answer
        question.getAllAnswers().set(index2, " ");
        return question;
    }
}
