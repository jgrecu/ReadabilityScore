package readability;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        String inputText = scanner.nextLine();
        String[] phrases = inputText.split("\\.|!|\\?");
        int textSize = phrases.length;
        double numOfWords = 0;

        for (int i = 0; i < textSize; i++) {
            numOfWords += phrases[i].trim().split(" ").length;
        }

        System.out.print(numOfWords / textSize > 10 ? "HARD" : "EASY");
    }
}
