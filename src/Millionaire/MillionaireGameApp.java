package Millionaire;

import java.util.ArrayList;
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

                    // Create game
                    Game newGame = new Game(newPlayer, gameDifficulty, questionBank);

                    // Create round 1
                    Round round1 = newGame.newRound();
                    // Set Round difficulty
                    if (gameDifficulty == EASY_MODE) {
                        Round.setDifficulty(EASY_MODE);
                    } else {
                        Round.setDifficulty(HARD_MODE);
                    }

                    // Get result from the round
                    boolean roundOneResult = roundOneTwo(newGame, round1, newPlayer);
                    if (roundOneResult) {
                        // User failed the round. Exit game
                        break;
                    }

                    // Player does not walk away. Play round 2
                    Round round2 = newGame.newRound();
                    boolean roundTwoResult = roundOneTwo(newGame, round2, newPlayer);
                    if (roundTwoResult) {
                        // Round 2 failed
                        break;
                    }

                    // Player does not walk away. Play round 3
                    Round round3 = newGame.newRound();
                    boolean roundThreeResult = roundThree(newGame, round3, newPlayer);
                    if (roundThreeResult) {
                        // Round 3 failed
                        break;
                    }

                    // End
                    valid = true;
                } else if (launchOption == RULES) {
                    displayRules();
                    boolean validMenu = false;
                    int userOption;
                    while (!validMenu) {
                        try {
                            // Ask for user input
                            System.out.print("Press 1 to go back to the launch screen: ");
                            userOption = read.nextInt();
                            if (userOption == START) {
                                // Display launch menu then go back
                                launchScreen();
                                validMenu = true;
                            } else {
                                System.out.print("Error: not a valid option. ");
                            }
                        } catch (InputMismatchException e) {
                            System.out.print("Error: not a valid input. ");
                            read.next();
                        }
                    }
                } else if (launchOption == EXIT) {
                    valid = exit();
                } else {
                    System.out.print("Error: not a valid option. Please select an option: ");
                }
            } catch (InputMismatchException e) {
                System.out.print("Error: not a valid input. Please select an option: ");
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
                // Check for valid input
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
        System.out.println("--------------------------------------------------------------------------------How to Play"
                + "--------------------------------------------------------------------------------");
        System.out.println("The rules of the game are simple. Once you start, you will be presented with an option to "
                + "play on easy mode or hard mode. You will face 3 rounds of questions.\nOn easy mode you'll "
                + "have 3 questions per round. On hard mode you'll have 5 questions per round. At the end of "
                + "each round, you'll have the chance to walk away with the money you've earned.\nIf you choose "
                + "to keep playing, you'll have the opportunity to win more money...up to one million dollars! "
                + "But beware, if you get even one question wrong, you will lose and go home empty-handed.\n");
        System.out.println("To help you win the coveted million-dollar prize, you have three lifelines. The 50/50 lifeline "
                + "eliminates two incorrect answers. You can also ask the audience,\nwho will vote on the answer, "
                + "or you can call a friend to help you out. In easy mode, you can use your lifelines from round "
                + "1. In hard mode, your lifelines are only available starting on round 2.\n");
        System.out.println("Good luck!");
    }

    public static boolean exit() {
        System.out.print("Thank you for playing Who Wants to Be a Millionaire!");
        return true;
    }

    public static boolean roundOneTwo(Game game, Round round, Player player) {
        // Play round then obtain result
        boolean roundResult = game.playRound(round);
        if (roundResult) {
            // Passed round
            System.out.println("Congratulations! You passed round " + round.getRoundNumber() + "! Your current winnings are at $" + player.getTotalWinnings());
        } else {
            // Failed round
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
        // Play round 3
        boolean roundResult = game.playRound(round);
        if (roundResult) {
            // User wins game
            System.out.println("Congratulations! You passed round " + round.getRoundNumber() + "! You won $" + player.getTotalWinnings() + "!");
            System.out.println("Thank you for playing Who Wants to Be a Millionaire!");
        } else {
            System.out.println("Thank you for playing Who Wants to Be a Millionaire!");
            return true;
        }
        // No option to walk away
        return true;
    }
}
