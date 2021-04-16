package Millionaire;

import java.util.ArrayList;

public abstract class Round {
    private static int roundNumber;
    private static int difficulty;
    private ArrayList<Question> questions;

    public Round(ArrayList<Question> questions) {
        roundNumber++;
        this.questions = questions;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public void showQuestion(int index) {
        System.out.println(questions.get(index));
        for (int i = 0; i < questions.size(); i++) {
            System.out.println("A. " + questions.get(index).getAllAnswers().get(0));
            System.out.println("B. " + questions.get(index).getAllAnswers().get(1));
            System.out.println("C. " + questions.get(index).getAllAnswers().get(2));
            System.out.println("D. " + questions.get(index).getAllAnswers().get(3));
        }
    }
}
