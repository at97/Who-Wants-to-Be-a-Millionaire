package Millionaire;

import java.util.ArrayList;
import java.util.Random;

public class Friend extends Lifeline {
    public String name;

    public Friend(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Question useLifeline(Question question) {
        int answer = question.getAnswer();
        ArrayList<String> answerOptions = new ArrayList<>();
        answerOptions.add("A: " + question.getAllAnswers().get(0));
        answerOptions.add("B: " + question.getAllAnswers().get(1));
        answerOptions.add("C: " + question.getAllAnswers().get(2));
        answerOptions.add("D: " + question.getAllAnswers().get(3));

        // Generate random chance of friend knowing the answer
        int chanceOfKnowing = new Random().nextInt(100);
        // Friend knows the answer when chance >= 50
        if (chanceOfKnowing >= 50) {
            System.out.print(getName() + " says: That's easy! The answer is ");
            switch (answer) {
                // Present answer
                case 1:
                    System.out.println("A: " + question.getAllAnswers().get(0));
                    break;
                case 2:
                    System.out.println("B: " + question.getAllAnswers().get(1));
                    break;
                case 3:
                    System.out.println("C: " + question.getAllAnswers().get(2));
                    break;
                case 4:
                    System.out.println("D: " + question.getAllAnswers().get(3));
                    break;
            }
        } else if (chanceOfKnowing >= 12) {
            // Friend guesses the answer when chance is between 50 and 12
            System.out.print(getName() + " says: I'm not sure about this...I think it might be ");
            int index = new Random().nextInt(answerOptions.size());
            System.out.println(answerOptions.get(index));
            answerOptions.remove(index);
        } else {
            // Friend doesn't know the answer
            System.out.println(getName() + " says: I don't know anything about that! Sorry!");
        }
        return question;
    }
}
