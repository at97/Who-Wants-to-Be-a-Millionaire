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
        //System.out.println(questions.get(0));
        //System.out.println(questions.get(0).getAllAnswers().size());
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public ArrayList<Question> readFile(String inputFile) {
        File questionFile = new File(inputFile);
        ArrayList<Question> questionBank = new ArrayList<>();
        try {
            Scanner read = new Scanner(questionFile);
            read.useDelimiter("[\t\n]|\r\n");

            // Read data from file line-by-line
            while(read.hasNext()) {
                ArrayList<String> allAnswers = new ArrayList<>();
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
                questionBank.add(newQuestion);
            }
            read.close();
        } catch (NullPointerException e) {
            System.out.println("Error: null pointer");
        } catch (FileNotFoundException e) {
            System.out.println("Error: file not found" );
        }

        return questionBank;
    }
}
