package Millionaire;

import java.util.ArrayList;
import java.util.Random;

public class Audience extends Lifeline {
    public Audience() {
    }

    @Override
    public Question useLifeline(Question question) {
        // Get random number
        int answer = question.getAnswer();
        // Generate a number between 0 and 100. Half of the time, this number will be greater than 50.
        // This bias is used to give greater weight to the correct answer, as the audience is almost always right.
        int option1 = new Random().nextInt(100);
        int difference1 = 100 - option1;
        // Calculate a second value between 0 and (100 - option1)
        int option2 = new Random().nextInt(difference1);
        int difference2 = difference1 - option2;
        // Calculate a third value between 0 and (100 - option1 - option2)
        int option3 = new Random().nextInt(difference2);
        // Use the remainder as the fourth, final value between 0 and (100 - option1 - option2 - option3)
        int option4 = difference2 - option3;

        // Store remaining values in an arraylist to random assign to variables
        ArrayList<Integer> optionsArray = new ArrayList<>();
        System.out.println("Audience results:");
        switch (answer) {
            case 1:
                optionsArray.add(option2);
                optionsArray.add(option3);
                optionsArray.add(option4);
                int index1 = new Random().nextInt(optionsArray.size());
                System.out.println("A: " + option1 + "%");
                System.out.println("B + " + optionsArray.get(index1) + "%");
                optionsArray.remove(index1);
                int index2 = new Random().nextInt(optionsArray.size());
                System.out.println("C + " + optionsArray.get(index2) + "%");
                optionsArray.remove(index2);
                int index3 = new Random().nextInt(optionsArray.size());
                System.out.println("D + " + optionsArray.get(index3) + "%");
                optionsArray.remove(index3);
                break;
            case 2:
                optionsArray.add(option2);
                optionsArray.add(option3);
                optionsArray.add(option4);
                int index4 = new Random().nextInt(optionsArray.size());
                System.out.println("A: " + optionsArray.get(index4) + "%");
                optionsArray.remove(index4);
                System.out.println("B: " + option1 + "%");
                int index5 = new Random().nextInt(optionsArray.size());
                System.out.println("C + " + optionsArray.get(index5) + "%");
                optionsArray.remove(index5);
                int index6 = new Random().nextInt(optionsArray.size());
                System.out.println("D + " + optionsArray.get(index6) + "%");
                optionsArray.remove(index6);
                break;
            case 3:
                optionsArray.add(option2);
                optionsArray.add(option3);
                optionsArray.add(option4);
                int index7 = new Random().nextInt(optionsArray.size());
                System.out.println("A: " + optionsArray.get(index7) + "%");
                optionsArray.remove(index7);
                int index8 = new Random().nextInt(optionsArray.size());
                System.out.println("B: " + optionsArray.get(index8) + "%");
                optionsArray.remove(index8);
                System.out.println("C: " + option1 + "%");
                int index9 = new Random().nextInt(optionsArray.size());
                System.out.println("D: " + optionsArray.get(index9) + "%");
                optionsArray.remove(index9);
                break;
            case 4:
                optionsArray.add(option2);
                optionsArray.add(option3);
                optionsArray.add(option4);
                int index10 = new Random().nextInt(optionsArray.size());
                System.out.println("A: " + optionsArray.get(index10) + "%");
                optionsArray.remove(index10);
                int index11 = new Random().nextInt(optionsArray.size());
                System.out.println("B + " + optionsArray.get(index11) + "%");
                optionsArray.remove(index11);
                int index12 = new Random().nextInt(optionsArray.size());
                System.out.println("C + " + optionsArray.get(index12) + "%");
                optionsArray.remove(index12);
                System.out.println("D: " + option1 + "%");
                break;
        }
        return question;
    }
}
