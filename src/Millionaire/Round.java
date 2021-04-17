package Millionaire;

import java.util.ArrayList;
import java.util.InputMismatchException;

public abstract class Round {
    // Constants
    private static final int EASY_MODE = 1;
    private static final int HARD_MODE = 2;

    // Instance variables
    private static int roundNumber;
    private static int difficulty;
    private ArrayList<Question> questions;
    private boolean usingLifeline;

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

    public boolean isUsingLifeline() {
        return usingLifeline;
    }

    public void setUsingLifeline(boolean usingLifeline) {
        this.usingLifeline = usingLifeline;
    }

    public void showQuestion(int index) {
        // Show question and it's associated answers
        System.out.println(questions.get(index).getQuestion());
        System.out.println("  A. " + questions.get(index).getAllAnswers().get(0));
        System.out.println("  B. " + questions.get(index).getAllAnswers().get(1));
        System.out.println("  C. " + questions.get(index).getAllAnswers().get(2));
        System.out.println("  D. " + questions.get(index).getAllAnswers().get(3));
    }

    public boolean checkLifelineEligibility() {
        // Check if present round is eligible for lifelines
        if ((difficulty == EASY_MODE) || (difficulty == HARD_MODE && (roundNumber == 2 || roundNumber == 3))) {
            // Check for unused lifelines
            if (!Lifeline.isUsed5050Lifeline() || !Lifeline.isUsedAudienceLifeline() || !Lifeline.isUsedFriendLifeline()) {
                // There are still unused lifelines
                return true;
            }
        }
        // There are no lifelines left or lifelines cannot be used for the present round
        return false;
    }

    public void offerLifeline(Question question) {
        System.out.println("The following lifelines are available: ");
        if (!Lifeline.isUsed5050Lifeline()) {
            System.out.println("  1. 50/50 - Eliminate two incorrect answers");
        }
        if (!Lifeline.isUsedAudienceLifeline()) {
            System.out.println("  2. Ask the audience - Audience votes on the correct answer");
        }
        if (!Lifeline.isUsedFriendLifeline()) {
            System.out.println("  3. Phone a friend - Your friend suggests the correct answer");
        }

    }

    public void selectLifeline(Question question, int index) {
        boolean valid = false;
        while (!valid) {
            try{
                // Select lifeline
                System.out.print("Please select an option: ");
                int lifeline = MillionaireGameApp.read.nextInt();
                if (lifeline == 1 && !Lifeline.isUsed5050Lifeline()) {
                    // Select 50/50 lifeline
                    usingLifeline = true;
                    FiftyFifty fiftyFiftyObj = new FiftyFifty();
                    Lifeline.setUsed5050Lifeline(true);
                    Question newQuestion = fiftyFiftyObj.useLifeline(question);
                    // Hide incorrect answers
                    questions.get(index).setAllAnswers(newQuestion.getAllAnswers());
                    valid = true;
                }
                if (lifeline == 2 && !Lifeline.isUsedAudienceLifeline()) {
                    // Ask the audience
                    usingLifeline = true;
                    Audience audienceObj = new Audience();
                    Lifeline.setUsedAudienceLifeline(true);
                    Question newQuestion = audienceObj.useLifeline(question);
                    valid = true;
                }
                if (lifeline == 3 && !Lifeline.isUsedFriendLifeline()) {
                    // Phone a friend
                    usingLifeline = true;
                    System.out.print("Please enter the name of the friend you would like to call: ");
                    String friendName = MillionaireGameApp.read.next();
                    Friend friendObj = new Friend(friendName);
                    Lifeline.setUsedFriendLifeline(true);
                    Question newQuestion = friendObj.useLifeline(question);
                    valid = true;
                }
            } catch (InputMismatchException e) {
                System.out.print("Error: not a valid input. ");
                MillionaireGameApp.read.next();
            }
        }
    }

    public char getAnswer() {
        boolean validInput = false;
        char answer = 0;
        while (!validInput) {
            // Get user's answer
            System.out.print("Please select an answer: ");
            answer = MillionaireGameApp.read.next().charAt(0);
            // Convert answer to lowercase
            answer = Character.toLowerCase(answer);
            // Check if lifeline can be used in case 'l' is a valid input
            boolean lifelineEligible = checkLifelineEligibility();

            if (!(answer == 'a' || answer == 'b' || answer == 'c' || answer == 'd' || (answer == 'l' && !usingLifeline && lifelineEligible))) {
                // User enters an input other than 'a', 'b', 'c', 'd', or 'l' while lifelines are available and not being used
                System.out.print("Error: not a valid option. ");
            } else {
                // User enters valid input
                validInput = true;
            }
        }
        // Return user's answer
        return answer;
    }

    public int finalAnswer(char answer) {
        boolean finalAnswer = false;
        char response;
        int integerAnswer = 0;
        while (!finalAnswer) {
            // Verify if the user's reponse if what they want
            System.out.print("Is '" + answer + "' your final answer? Enter 'y' or 'n': ");
            response = MillionaireGameApp.read.next().charAt(0);
            // Prompt for yes or no
            response = Character.toLowerCase(response);
            if (response == 'y') {
                // It is their final answer
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
                // Ask user for another response
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
            // Compare user's answer to answer from question
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
            // Prompt user if they want to walk away with their earnings
            response = Character.toLowerCase(response);
            if (response == 'y') {
                // User walks away with winnings
                valid = true;
            } else if (response == 'n') {
                // User continues with game
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
