package Millionaire;

import java.util.ArrayList;
import java.util.Random;

public class Game {
    // Constants
    private static final int EASY_MODE = 1;
    private static final int HARD_MODE = 2;

    // Instance variables
    private final int difficulty;
    private final Player player;
    private static QuestionBank questions;

    public Game(Player player, int difficulty, QuestionBank questions) {
        this.player = player;
        this.difficulty = difficulty;
        Game.questions = questions;
    }

    public ArrayList<Question> generateQuestions(QuestionBank questionBank) {
        ArrayList<Question> roundQuestions = new ArrayList<>();
        if (difficulty == EASY_MODE) {
            for (int i = 0; i < EasyRound.getNumQuestions(); i++) {
                int index = new Random().nextInt(questionBank.getQuestions().size());
                Question newQuestion = questionBank.getQuestions().get(index);
                System.out.println(newQuestion.toString());
                questionBank.getQuestions().remove(index);
            }
        } else if (difficulty == HARD_MODE) {
            for (int i = 0; i < HardRound.getNumQuestions(); i++) {
                int index = new Random().nextInt(questionBank.getQuestions().size());
                Question newQuestion = questionBank.getQuestions().get(index);
                System.out.println(newQuestion.toString());
                questionBank.getQuestions().remove(index);
            }
        } else {
            System.out.println("Error: invalid difficulty");
        }
        return roundQuestions;
    }

    public void newRound() {
        //ArrayList<Question> roundQuestions = generateQuestions(questions);
        if (difficulty == EASY_MODE) {
            EasyRound easyRound = new EasyRound(generateQuestions(questions));
        } else if (difficulty == HARD_MODE) {
            HardRound hardRound = new HardRound(generateQuestions(questions));
        }
    }
}
