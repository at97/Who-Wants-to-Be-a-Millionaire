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
        ArrayList<Question> roundQuestions = new ArrayList<>();
        if (difficulty == EASY_MODE) {
            for (int i = 0; i < EasyRound.getNumQuestions(); i++) {
                int index = new Random().nextInt(questionBank.size());
                Question newQuestion = questionBank.get(index);
                roundQuestions.add(newQuestion);
                questionBank.remove(index);
            }
        } else if (difficulty == HARD_MODE) {
            for (int i = 0; i < HardRound.getNumQuestions(); i++) {
                int index = new Random().nextInt(questionBank.size());
                Question newQuestion = questionBank.get(index);
                roundQuestions.add(newQuestion);
                questionBank.remove(index);
            }
        } else {
            System.out.println("Error: invalid difficulty");
        }
        return roundQuestions;
    }

    public Round newRound() {
        if (difficulty == EASY_MODE) {
            return new EasyRound(generateQuestions(questions));
        } else {
            return new HardRound(generateQuestions(questions));
        }
    }

    public boolean playRound(Round round) {
        int limit;
        if (round instanceof EasyRound) {
            limit = EasyRound.getNumQuestions();
        } else {
            limit = HardRound.getNumQuestions();
        }
        System.out.println("----------Beginning Round " + round.getRoundNumber() + "----------");
        for (int i = 0; i < limit; i++) {
            round.showQuestion(i);
            // Obtain answer from user
            char answer = round.getAnswer();
            // Ask user if it's their final answer
            int finalAnswer = round.finalAnswer(answer);
            boolean result = round.checkAnswer(round.getQuestions().get(i), finalAnswer);
            if (result) {
                System.out.println("Correct!");
            } else {
                System.out.println("Sorry, that is incorrect.");
                return false;
            }
        }
        if (round.getRoundNumber() == 1) {
            player.setTotalWinnings(ROUND_1_WINNINGS);
        } else if (round.getRoundNumber() == 2) {
            player.setTotalWinnings(ROUND_2_WINNINGS);
        } else if (round.getRoundNumber() == 3) {
            player.setTotalWinnings(ROUND_3_WINNINGS);
        }
        return true;
    }

}
