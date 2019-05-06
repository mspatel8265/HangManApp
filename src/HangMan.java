import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class HangMan {
    String mysteryWord;
    StringBuilder currentGuess;
    ArrayList<Character> previousGuesses = new ArrayList<>();

    int maxTries = 6;
    int currentTry = 0;

    ArrayList<String> dictionary = new ArrayList<>();
    private static FileReader fileReader;
    private static BufferedReader bufferedFileReader;

    public HangMan() throws IOException {
        initializeStreams();
        mysteryWord = pickWord();
        currentGuess = initializeCurrentGuess();
    }

    public void initializeStreams() throws IOException {
        // Initialize dictionary Arraylist
        try {
           File inFile = new File("dictionary.txt");
           fileReader = new FileReader(inFile);
           bufferedFileReader = new BufferedReader(fileReader);
           String currentLine = bufferedFileReader.readLine();
           while(currentLine != null) {
               dictionary.add(currentLine);
               currentLine = bufferedFileReader.readLine();
           }
           bufferedFileReader.close();
           fileReader.close();
        } catch(IOException e) {
            System.out.println("Could not init Streams");
        }
    }

    public String pickWord() {
        Random rand = new Random();
        int wordIndex = Math.abs(rand.nextInt()) % dictionary.size();
        return dictionary.get(wordIndex);
    }

    public StringBuilder initializeCurrentGuess() {
        StringBuilder current = new StringBuilder();
        for(int i = 0; i < mysteryWord.length() * 2; i++) {
            if(i % 2 == 0) {
                current.append("_");
            } else {
                current.append(" ");
            }
        }
        return current;
    }


    public String getFormalCurrentGuess() {
        return "Current guess: " + currentGuess.toString();
    }

    public boolean gameOver() {

        if(didWeWin()) {
            System.out.println("\nCongrats! You won! You guessed the correct word");
            return true;
        } else if(didWeLose()) {
            System.out.println("\nYou spent all of your 6 tries. \nThe word was " + mysteryWord);
            return true;
        }

        return false;
    }

    public boolean didWeWin() {
        String guess = getCondensedCurrentGuess();
        return guess.equals(mysteryWord);
    }

    public boolean didWeLose() {
        return currentTry >= maxTries;
    }

    public String getCondensedCurrentGuess() {
        String guess = currentGuess.toString();
        return guess.replace(" ", "");
    }

    public boolean isGuessedAlready(char guess) {
        return previousGuesses.contains(guess);
    }

    public boolean playGuess(char guess) {
        boolean isItAGoodGuess = false;
        previousGuesses.add(guess);

        for (int i = 0; i < this.mysteryWord.length(); i++) {
            if(mysteryWord.charAt(i) == guess) {
                currentGuess.setCharAt(i * 2 , guess);
                isItAGoodGuess = true;
            }
        }

        if(!isItAGoodGuess) {
            currentTry++;
        }

        return isItAGoodGuess;
    }

    public String drawPicture() {
        switch(currentTry) {
            case 0: return noPersonDraw();
            case 1: return addHeadDraw();
            case 2: return addBodyDraw();
            case 3: return addOneArmDraw();
            case 4: return addSecondArmDraw();
            case 5: return addOneLegDraw();
            default: return fullPersonDraw();

        }
    }

    private String noPersonDraw() {
       return " - - - - -\n"+
                "|        |\n"+
                "|        \n" +
                "|         \n"+
                "|        \n" +
                "|         \n" +
                "|\n" +
                "|\n";
    }

    private String addHeadDraw() {
        return " - - - - -\n"+
                "|        |\n"+
                "|        O\n" +
                "|       \n"+
                "|        \n" +
                "|        \n" +
                "|\n" +
                "|\n";
    }

    private String addBodyDraw() {
        return " - - - - -\n"+
                "|        |\n"+
                "|        O\n" +
                "|        |  \n"+
                "|        |\n" +
                "|         \n" +
                "|\n" +
                "|\n";
    }

    private String addOneArmDraw() {
        return " - - - - -\n"+
                "|        |\n"+
                "|        O\n" +
                "|      / |  \n"+
                "|        |\n" +
                "|       \n" +
                "|\n" +
                "|\n";
    }

    private String addSecondArmDraw() {
        return " - - - - -\n"+
                "|        |\n"+
                "|        O\n" +
                "|      / | \\ \n"+
                "|        |\n" +
                "|        \n" +
                "|\n" +
                "|\n";
    }

    private String addOneLegDraw() {
        return " - - - - -\n"+
                "|        |\n"+
                "|        O\n" +
                "|      / | \\ \n"+
                "|        |\n" +
                "|       /  \n" +
                "|\n" +
                "|\n";
    }

    private String fullPersonDraw() {
        return " - - - - -\n"+
                "|        |\n"+
                "|        O\n" +
                "|      / | \\ \n"+
                "|        |\n" +
                "|       / \\ \n" +
                "|\n" +
                "|\n";
    }

}
