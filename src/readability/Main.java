/*
 * Copyright (c) 2021. Jeremy Grecu
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package readability;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        String inText = getText(args[0]);

        int numOfWords = inText.split("\\s").length;
        //String[] words = inText.split("\\s");
        String[] words = inText.replaceAll("[.!,]", "").split("\\s");
        int numOfSentences = inText.split("[.!?]").length;
        int numOfCharacters = inText.replaceAll("\\s+", "").length();
        int numOfSyllables = countSyllables(words);
        int numOfPolySyllables = countPolySyllables(words);

        System.out.println("Words: " + numOfWords);
        System.out.println("Sentences: " + numOfSentences);
        System.out.println("Characters: " + numOfCharacters);
        System.out.println("Syllables: " + numOfSyllables);
        System.out.println("Polysyllables: " + numOfPolySyllables);
        System.out.println("Enter the score you want to calculate (ARI, FK, SMOG, CL, all): ");
        String choice = scanner.nextLine().toUpperCase();
        scanner.close();
        switch (choice) {
            case "ARI":
                double ariScore = getARIScore(numOfWords, numOfSentences, numOfCharacters);
                System.out.println("The score is: " + String.format("%.2f", ariScore));
                System.out.println("This text should be understood by " + calculateAge(ariScore) + "-year-olds.");
                break;
            case "FK":
                double fkScore = getFKScore(numOfWords, numOfSentences, numOfSyllables);
                System.out.println("The score is: " + String.format("%.2f", fkScore));
                System.out.println("This text should be understood by " + calculateAge(fkScore) + "-year-olds.");
                break;
            case "SMOG":
                double smogScore = getSMOGScore(numOfSentences, numOfPolySyllables);
                System.out.println("The score is: " + String.format("%.2f", smogScore));
                System.out.println("This text should be understood by " + calculateAge(smogScore) + "-year-olds.");
                break;
            case "CL":
                double clScore = getCLScore(numOfWords, numOfSentences, numOfCharacters);
                System.out.println("The score is: " + String.format("%.2f", clScore));
                System.out.println("This text should be understood by " + calculateAge(clScore) + "-year-olds.");
                break;
            case "ALL":
                ariScore = getARIScore(numOfWords, numOfSentences, numOfCharacters);
                fkScore = getFKScore(numOfWords, numOfSentences, numOfSyllables);
                smogScore = getSMOGScore(numOfSentences, numOfPolySyllables);
                clScore = getCLScore(numOfWords, numOfSentences, numOfCharacters);
                double average = (calculateAge(ariScore) + calculateAge(fkScore) + calculateAge(smogScore) +
                        calculateAge(clScore + 1)) / 4.0;
                System.out.println("Automated Readability Index: " + String.format("%.2f", ariScore) +
                        " (about " + calculateAge(ariScore) + "-year-olds).");
                System.out.println("Flesch–Kincaid readability tests: " + String.format("%.2f", fkScore) +
                        " (about " + calculateAge(fkScore) + "-year-olds).");
                System.out.println("Simple Measure of Gobbledygook: " + String.format("%.2f", smogScore) +
                        " (about " + calculateAge(smogScore) + "-year-olds).");
                System.out.println("Coleman–Liau index: " + String.format("%.2f", clScore) +
                        " (about " + calculateAge(clScore + 1) + "-year-olds).");
                System.out.println();
                System.out.println("This text should be understood in average by  " + String.format("%.2f", average) +
                        "-year-olds.");
                break;
            default:
                break;
        }
    }

    public static String getText(String path) {
        StringBuilder sb = new StringBuilder();
        try {
            FileReader inputFile = new FileReader(path);
            BufferedReader br = new BufferedReader(inputFile);
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return sb.toString();
    }

    public static int countSyllablesRegex(String word) {
        return Math.max(1, word.toLowerCase()
                .replaceAll("e$", "")
                .replaceAll("[aeiouy]{2,}", "a")
                .replaceAll("[^aeiouy]", "")
                .length());
    }

    public static int countSyllables(String[] words) {
        int count = 0;

        for (String word : words) {
            count += countSyllablesRegex(word);
        }
        return count;
    }

    public static int countPolySyllables(String[] words) {
        int count = 0;

        for (String word : words) {
            int syllables = countSyllablesRegex(word);
            if (syllables > 2) {
                count++;
            }
        }
        return count;
    }

    public static double getARIScore(int numWords, int numSentences, int numChars) {
        return 4.71 * numChars / numWords + 0.5 * numWords / numSentences - 21.43;
    }

    public static double getFKScore(int numWords, int numSentences, int numSyllables) {
        return 0.39 * numWords / numSentences + 11.8 * numSyllables / numWords - 15.59;
    }

    public static double getSMOGScore(int numSentences, int numPolySyllables) {
        return 1.043 * Math.sqrt(numPolySyllables * 30.0 / numSentences) + 3.1291;
    }

    public static double getCLScore(int numWords, int numSentences, int numChars) {
        double S = (double) numSentences / (double) numWords * 100;
        double L = (double) numChars / (double) numWords * 100;

        return 0.0588 * L - 0.296 * S - 15.8;
    }

    public static int calculateAge(double score) {
        int level = Math.min(14, Math.max(1, (int) Math.ceil(score))) - 2;
        return new int[]{6, 7, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 24, 25}[level];
    }
}
