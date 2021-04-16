package Millionaire;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MillionaireGameApp {
    private static final int START = 1;
    private static final int RULES = 2;
    private static final int EXIT = 3;
    private static final int EASY_MODE = 1;
    private static final int HARD_MODE = 2;
    // Read user input
    public static final Scanner read = new Scanner(System.in);

    public static void main(String[] args) {
        // Present launch screen
        launchScreen();
        boolean valid = false;
        while (!valid) {
            try {
                // Ask for launch screen input
                int launchOption = read.nextInt();
                read.nextLine();

                if (launchOption == START) {
                    // Create player
                    System.out.print("Please enter your name: ");
                    String name = read.nextLine();
                    Player newPlayer = new Player(name, 0);

                    // Ask for game difficulty
                    int gameDifficulty = gameDifficulty();

                    // Create question bank
                    QuestionBank questionBank = new QuestionBank();
                    questionBank.readFile("questions.txt");

                    // Create game
                    Game newGame = new Game(newPlayer, gameDifficulty, questionBank);
                    newGame.newRound();


                    valid = true;
                }
                else if (launchOption == RULES) {
                    displayRules();
                    valid = true;
                }
                else if (launchOption == EXIT) {
                    valid = exit();
                }
                else {
                    System.out.print("Error: not a valid option. Please select an option: ");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: not a valid input. Please select an option: ");
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

    public static int gameDifficulty() {
        System.out.println("Select your difficulty");
        System.out.println("  1. Easy mode");
        System.out.println("  2. Hard mode");
        System.out.print("Please select an option: ");
        int difficulty = 0;
        while (true) {
            try {
                difficulty = read.nextInt();

                if (difficulty == EASY_MODE || difficulty == HARD_MODE) {
                    return difficulty;
                } else {
                    System.out.print("Error: not a valid option. Please select an option: ");
                }
            } catch (InputMismatchException e) {
                System.out.print("Error: not a valid input. Please select an option: ");
                read.next();
            }
        }
    }

    public static void displayRules() {
        System.out.println("Option 2 selected");
    }

    public static boolean exit() {
        System.out.print("Thanks for playing!");
        return true;
    }
}
