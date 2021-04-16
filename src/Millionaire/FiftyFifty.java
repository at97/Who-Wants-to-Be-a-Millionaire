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
            index1 = new Random().nextInt(question.getAllAnswers().size());
            if (index1 != (question.getAnswer() - 1)) {
                validIndex1 = true;
            }
        }
        question.getAllAnswers().set(index1, " ");
        while (!validIndex2) {
            index2 = new Random().nextInt(question.getAllAnswers().size());
            if ((index2 != (question.getAnswer() - 1)) && (index1 != index2)) {
                validIndex2 = true;
            }
        }
        question.getAllAnswers().set(index2, " ");
        return question;
    }
}
