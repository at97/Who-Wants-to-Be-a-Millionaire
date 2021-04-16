package Millionaire;

import java.io.File;
//import java.io.FileNotFoundException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class QuestionBank {
    private ArrayList<Question> questions;

    public QuestionBank() {
        questions = new ArrayList<>();
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public void readFile(String inputFile) {
        File questionFile = new File(inputFile);
        ArrayList<String> allAnswers = new ArrayList<>();
        try {
            Scanner read = new Scanner(questionFile);
            read.useDelimiter("[\t\n]|\r\n");

            // Read data from file line-by-line
            while(read.hasNext()) {
                // Store data from file
                int questionID = read.nextInt();
                //System.out.println("Question ID: " + questionID);
                int difficulty = read.nextInt();
                //System.out.println("Difficulty: " + difficulty);
                String question = read.next();
                //System.out.println("Question: " + question);
                String optionA = read.next();
                //System.out.println("Option A: " + optionA);
                String optionB = read.next();
                //System.out.println("Option B: " + optionB);
                String optionC = read.next();
                //System.out.println("Option C: " + optionC);
                String optionD = read.next();
                //System.out.println("Option D: " + optionD);
                int answer = read.nextInt();
                //System.out.println("Answer: " + answer);

                // Add answers to ArrayList
                allAnswers.add(optionA);
                allAnswers.add(optionB);
                allAnswers.add(optionC);
                allAnswers.add(optionD);

                // Create question
                Question newQuestion = new Question(questionID, difficulty, question, allAnswers, answer);

                // Add newQuestion to questionBank
                questions.add(newQuestion);

                // Clear allAnswers ArrayList
                allAnswers.clear();
            }
        } catch (NullPointerException e) {
            System.out.println("Error: null pointer");
        } catch (FileNotFoundException e) {
            System.out.println("Error: file not found" );
        }
        //return questions;
    }
}
