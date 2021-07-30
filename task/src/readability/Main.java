package readability;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        String inputText = scanner.nextLine();

        System.out.print(inputText.length() > 100 ? "HARD" : "EASY");
    }
}
