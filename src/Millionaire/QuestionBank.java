package Millionaire;

import java.io.File;
//import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class QuestionBank {
    private ArrayList<Question> questionBank;

    public QuestionBank() {
    }

    public ArrayList<Question> readFile(String inputFile) {
        File questionFile = new File(inputFile);
        ArrayList<String> allAnswers = new ArrayList<>();
        try {
            Scanner read = new Scanner(questionFile);
            read.useDelimiter("\t|\n");

            // Read data from file line-by-line
            while(read.hasNext()) {
                // Store data from file
                int questionID = read.nextInt();
                int difficulty = read.nextInt();
                String question = read.next();
                String optionA = read.next();
                String optionB = read.next();
                String optionC = read.next();
                String optionD = read.next();
                int answer = read.nextInt();

                // Add answers to ArrayList
                allAnswers.add(optionA);
                allAnswers.add(optionB);
                allAnswers.add(optionC);
                allAnswers.add(optionD);

                // Create question
                Question newQuestion = new Question(questionID, difficulty, question, allAnswers, answer);

                // Add newQuestion to questionBank
                questionBank.add(newQuestion);

                // Clear allAnswers ArrayList
                allAnswers.clear();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return questionBank;
    }
}
