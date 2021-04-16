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

    public static int getDifficulty() {
        return difficulty;
    }

    public static void setDifficulty(int difficulty) {
        Round.difficulty = difficulty;
    }

    public void showQuestion(int index) {
        System.out.println(questions.get(index).getQuestion());
        System.out.println("A. " + questions.get(index).getAllAnswers().get(0));
        System.out.println("B. " + questions.get(index).getAllAnswers().get(1));
        System.out.println("C. " + questions.get(index).getAllAnswers().get(2));
        System.out.println("D. " + questions.get(index).getAllAnswers().get(3));

    }

    public char getAnswer() {
        boolean validInput = false;
        char answer = 0;
        while (!validInput) {
            System.out.print("Please select an answer: ");
            answer = MillionaireGameApp.read.next().charAt(0);
            answer = Character.toLowerCase(answer);
            if (!(answer == 'a' || answer == 'b' || answer == 'c' || answer == 'd')) {
                System.out.print("Error: not a valid option. ");
            } else {
                validInput = true;
            }
        }
        return answer;
    }

    public int finalAnswer(char answer) {
        boolean finalAnswer = false;
        char response;
        int integerAnswer = 0;
        while (!finalAnswer) {
            System.out.print("Is '" + answer + "' your final answer? Enter 'y' or 'n': ");
            response = MillionaireGameApp.read.next().charAt(0);
            response = Character.toLowerCase(response);
            if (response == 'y') {
                finalAnswer = true;
                // Match their answer to an integer to check if it's correct
                switch (answer) {
                    case 'a':
                        integerAnswer = 1;
                        break;
                    case 'b':
                        integerAnswer = 2;
                        break;
                    case 'c':
                        integerAnswer = 3;
                        break;
                    case 'd':
                        integerAnswer = 4;
                        break;
                }
            } else if (response == 'n') {
                answer = getAnswer();
            } else {
                System.out.println("Error: not a valid option.");
            }
        }
        return integerAnswer;
    }

    public boolean checkAnswer(Question question, int answer) {
        boolean correctAnswer = false;
        try {
            if (answer == question.getAnswer()) {
                correctAnswer = true;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return correctAnswer;
    }

    public boolean walkAway() {
        boolean valid = false;
        char response = 0;
        while (!valid) {
            System.out.print("Would you like to walk away? Enter 'y' or 'n': ");
            response = MillionaireGameApp.read.next().charAt(0);
            response = Character.toLowerCase(response);
            if (response == 'y') {
                valid = true;
            } else if (response == 'n') {
                return false;
            } else {
                System.out.print("Error: not a valid option. ");
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "Round{" +
                "questions=" + questions +
                '}';
    }
}
