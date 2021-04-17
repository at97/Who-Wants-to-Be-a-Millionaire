package Millionaire;

import java.util.ArrayList;
import java.util.Random;

public class Game {
    // Constants
    private static final int EASY_MODE = 1;
    private static final int HARD_MODE = 2;
    private static final int ROUND_1_WINNINGS = 1000;
    private static final int ROUND_2_WINNINGS = 32000;
    private static final int ROUND_3_WINNINGS = 1000000;

    // Instance variables
    private final int difficulty;
    private final Player player;
    private static ArrayList<Question> questions;

    public Game(Player player, int difficulty, ArrayList<Question> questions) {
        this.player = player;
        this.difficulty = difficulty;
        Game.questions = questions;
    }

    public ArrayList<Question> generateQuestions(ArrayList<Question> questionBank) {
        // Store questions for present round
        ArrayList<Question> roundQuestions = new ArrayList<>();
        if (difficulty == EASY_MODE) {
            // Easy mode = 3 questions
            for (int i = 0; i < EasyRound.getNumQuestions(); i++) {
                // Choose a random index from the QuestionBank
                int index = new Random().nextInt(questionBank.size());
                // Create the question object
                Question newQuestion = questionBank.get(index);
                // Add the question to the current round
                roundQuestions.add(newQuestion);
                // Remove the question from the QuestionBank to ensure it's not repeated in the game
                questionBank.remove(index);
            }
        } else if (difficulty == HARD_MODE) {
            // Hard mode = 5 questions
            for (int i = 0; i < HardRound.getNumQuestions(); i++) {
                int index = new Random().nextInt(questionBank.size());
                Question newQuestion = questionBank.get(index);
                roundQuestions.add(newQuestion);
                questionBank.remove(index);
            }
        } else {
            System.out.println("Error: invalid difficulty");
        }
        // Return questions for current round
        return roundQuestions;
    }

    public Round newRound() {
        if (difficulty == EASY_MODE) {
            // Create an EasyRound
            return new EasyRound(generateQuestions(questions));
        } else {
            // Create a HardRound
            return new HardRound(generateQuestions(questions));
        }
    }

    public boolean playRound(Round round) {
        // Store limit to number of questions
        int limit;
        int finalAnswer = 0;
        if (round instanceof EasyRound) {
            // EasyRound has 3 questions
            limit = EasyRound.getNumQuestions();
        } else {
            // HardRound has 5 questions
            limit = HardRound.getNumQuestions();
        }
        // Begin the round
        System.out.println("----------Beginning Round " + round.getRoundNumber() + "----------");
        for (int i = 0; i < limit; i++) {
            // Show question
            round.showQuestion(i);
            // Check if lifelines are eligible for this round
            if (round.checkLifelineEligibility()) {
                System.out.println("You have lifelines available. Please select one of the options above or enter 'l' to choose a lifeline.");
            }
            // Obtain answer from user
            char answer = round.getAnswer();
            // User has requested a lifeline
            if (answer == 'l') {
                // Present available lifelines
                round.offerLifeline(round.getQuestions().get(i));
                // Select the lifeline
                round.selectLifeline(round.getQuestions().get(i), i);
                // Show the question with the lifeline applied
                round.showQuestion(i);
                // Get the answer
                answer = round.getAnswer();
                // Ask user if it's their final answer and convert the answer to an integer to check against the question answer
                finalAnswer = round.finalAnswer(answer);
            } else {
                // Ask user if it's their final answer
                finalAnswer = round.finalAnswer(answer);
            }
            // Question is not longer using a lifeline
            round.setUsingLifeline(false);
            // Check if the user's answer is correct
            boolean result = round.checkAnswer(round.getQuestions().get(i), finalAnswer);
            if (result) {
                System.out.println("Correct!");
            } else {
                System.out.println("Sorry, that is incorrect.");
                // User fails game
                return false;
            }
        }
        // Apply winnings to player based on current round
        if (round.getRoundNumber() == 1) {
            player.setTotalWinnings(ROUND_1_WINNINGS);
        } else if (round.getRoundNumber() == 2) {
            player.setTotalWinnings(ROUND_2_WINNINGS);
        } else if (round.getRoundNumber() == 3) {
            player.setTotalWinnings(ROUND_3_WINNINGS);
        }
        // Player has completed the round
        return true;
    }

}
