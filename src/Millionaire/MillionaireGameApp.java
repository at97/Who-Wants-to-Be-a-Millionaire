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
                    QuestionBank questionBankObj = new QuestionBank();
                    ArrayList<Question> questionBank = questionBankObj.readFile("questions.txt");

                    // Create game + round 1
                    Game newGame = new Game(newPlayer, gameDifficulty, questionBank);

                    // Play round 1
                    Round round1 = newGame.newRound();
                    boolean roundOneResult = roundOneTwo(newGame, round1, newPlayer);
                    if (roundOneResult) {
                        valid = true;
                    }
//                    boolean round1Result = newGame.playRound(round1);
//                    if (round1Result) {
//                        System.out.println("Congratulations! You passed round " + round1.getRoundNumber() + "! Your current winnings are at $" + newPlayer.getTotalWinnings());
//                    } else {
//                        System.out.println("Thanks for playing!");
//                        valid = true;
//                    }
//
//                    // Ask player if they want to walk away
//                    boolean walkAway1 = round1.walkAway();
//                    if (walkAway1) {
//                        // Player agrees to walk away
//                        System.out.println("Thanks for playing!");
//                        valid = true;
//                    }

                    // Player does not walk away. Play round 2
                    Round round2 = newGame.newRound();
                    boolean roundTwoResult = roundOneTwo(newGame, round2, newPlayer);
                    if (roundTwoResult) {
                        valid = true;
                    }

                    // Player does not walk away. Play round 3
                    Round round3 = newGame.newRound();
                    boolean roundThreeResult = roundThree(newGame, round3, newPlayer);
                    if (roundThreeResult) {
                        valid = true;
                    }

                    valid = true;
                } else if (launchOption == RULES) {
                    displayRules();
                    valid = true;
                } else if (launchOption == EXIT) {
                    valid = exit();
                } else {
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
        System.out.print("Thank you for playing Who Wants to Be a Millionaire!");
        return true;
    }

    public static boolean roundOneTwo(Game game, Round round, Player player) {
        boolean roundResult = game.playRound(round);
        if (roundResult) {
            System.out.println("Congratulations! You passed round " + round.getRoundNumber() + "! Your current winnings are at $" + player.getTotalWinnings());
        } else {
            System.out.println("Thank you for playing Who Wants to Be a Millionaire!");
            return true;
        }
        // Ask player if they want to walk away
        boolean walkAway = round.walkAway();
        if (walkAway) {
            // Player agrees to walk away
            System.out.println("Thank you for playing Who Wants to Be a Millionaire!");
            return true;
        }
        return false;
    }

    public static boolean roundThree(Game game, Round round, Player player) {
        boolean roundResult = game.playRound(round);
        if (roundResult) {
            System.out.println("Congratulations! You passed round " + round.getRoundNumber() + "! You won $" + player.getTotalWinnings() + "!");
            System.out.println("Thank you for playing Who Wants to Be a Millionaire!");
        } else {
            System.out.println("Thank you for playing Who Wants to Be a Millionaire!");
            return true;
        }
        return true;
    }
}
