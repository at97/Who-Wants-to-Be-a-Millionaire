package Millionaire;

import java.util.ArrayList;

public class Question {
    private int questionID;
    private int difficulty;
    private String question;
    private ArrayList<String> allAnswers;
    private int answer;

    public Question(int questionID, int difficulty, String question, ArrayList<String> allAnswers, int answer) {
        this.questionID = questionID;
        this.difficulty = difficulty;
        this.question = question;
        this.allAnswers = allAnswers;
        this.answer = answer;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public ArrayList<String> getAllAnswers() {
        return allAnswers;
    }

    public void setAllAnswers(ArrayList<String> allAnswers) {
        this.allAnswers = allAnswers;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "Question{" +
                "questionID=" + questionID +
                ", difficulty=" + difficulty +
                ", question='" + question + '\'' +
                ", allAnswers=" + allAnswers +
                ", answer=" + answer +
                '}';
    }
}
