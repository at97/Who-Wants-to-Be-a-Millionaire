package Millionaire;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MillionaireGameApp {
    public static void main(String[] args) {
        // Read user input
        Scanner read = new Scanner(System.in);

        // Present launch screen
        launchScreen();
        boolean valid = false;
        while (!valid) {
            try {
                // Ask for launch screen input
                int launchOption = read.nextInt();
                read.nextLine();
                if (launchOption == 1) {
                    // Create player
                    System.out.print("Please enter your name: ");
                    String name = read.nextLine();
                    Player newPlayer = new Player(name, 0);
                    // Create question bank
                    QuestionBank questionBank = new QuestionBank();
                    questionBank.readFile("questions.txt");
                    for (int i = 0; i < questionBank.getQuestions().size(); i++) {
                        System.out.println(questionBank.getQuestions().get(i));
                    }
                    //System.out.println(Arrays.toString(questionBank.getQuestions().toArray()));
                    //ArrayList<Question> questionBank = new ArrayList<>();
                    //readFile(questionBank, "questions.txt");
                    //System.out.println(Arrays.toString(questionBank.toArray()));
                    valid = true;
                }
                else if (launchOption == 2) {
                    option2();
                    valid = true;
                }
                else if (launchOption == 3) {
                    option3();
                    valid = true;
                }
                else {
                    System.out.println("Error: not a valid option");
                    System.out.print("Please select an option: ");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: not a valid input");
                System.out.print("Please select an option: ");
                read.next();
            }
        }
    }

    public static void launchScreen() {
        System.out.println("Welcome to Who Wants to be a Millionaire!");
        System.out.println("  1. Start game");
        System.out.println("  2. View rules");
        System.out.println("  3. Exit");
        System.out.print("Please select an option: ");
    }

    public static void option2() {
        System.out.println("Option 2 selected");
    }

    public static void option3() {
        System.out.println("Option 3 selected");
    }
}
