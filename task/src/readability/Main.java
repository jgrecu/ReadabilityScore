package readability;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        int numOfWords = 0;
        int numOfSentences = 0;
        int numOfCharacters = 0;

        try {
            FileReader inputFile = new FileReader(args[0]);
            BufferedReader br = new BufferedReader(inputFile);
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String inText = sb.toString();
        numOfWords = inText.split("\\s").length;
        numOfSentences = inText.split("[.!?]").length;
        numOfCharacters = inText.replaceAll("\\s+", "").length();

        double score = 4.71 * numOfCharacters / numOfWords + 0.5 * numOfWords / numOfSentences - 21.43;
        int sc = (int) Math.ceil(score);

        String age = "";

        switch (sc) {
            case 1:
                age = "5-6-year-olds.";
                break;
            case 2:
                age = "6-7-year-olds.";
                break;
            case 3:
                age = "7-9-year-olds.";
                break;
            case 4:
                age = "9-10-year-olds.";
                break;
            case 5:
                age = "10-11-year-olds.";
                break;
            case 6:
                age = "11-12-year-olds.";
                break;
            case 7:
                age = "12-13-year-olds.";
                break;
            case 8:
                age = "13-14-year-olds.";
                break;
            case 9:
                age = "14-15-year-olds.";
                break;
            case 10:
                age = "15-16-year-olds.";
                break;
            case 11:
                age = "16-17-year-olds.";
                break;
            case 12:
                age = "17-18-year-olds.";
                break;
            case 13:
                age = "18-24-year-olds.";
                break;
            case 14:
                age = "24-year-olds.";
                break;
            default:
                break;
        }

        System.out.println("Words: " + numOfWords);
        System.out.println("Sentences: " + numOfSentences);
        System.out.println("Characters: " + numOfCharacters);
        System.out.println("The score is: " + String.format("%.2f", score));
        System.out.println("This text should be understood by " + age);

        //final Scanner scanner = new Scanner(inputFile);
        //String inputText = scanner.nextLine();
        //String[] phrases = inputText.split("\\.|!|\\?");

        //int textSize = phrases.length;
        //double numOfWords = 0;

        //for (int i = 0; i < textSize; i++) {
            //numOfWords += phrases[i].trim().split(" ").length;
        //}

        //System.out.print(numOfWords / textSize > 10 ? "HARD" : "EASY");
    }
}
