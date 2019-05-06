import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.io.IOException;
import java.util.Scanner;

public class HangManApplication {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to the Hangman game! I will pick a word and you will try to guess it!" +
                "\nIf you guess it wrong 6 times I will win, but if you guess it before then you'll win" +
                "\nAre you ready ? I hope so...");
        System.out.println();
        System.out.println("I have picked a word. Below is the picture, and below that is your current guess, which " +
                "starts as nothing. \nEvery time you guess incorrectly, I will add a body part and when there is a full man, " +
                "you'll loose the game.");

        // Allows to play multiple games
        boolean doYouWantToPlay = true;
        while(doYouWantToPlay) {

            // Settiing up the game
            System.out.print("\nLet's play");
            HangMan game = new HangMan();

            do {
                // Draw the things...
                System.out.println();
                System.out.println();
                System.out.println(game.drawPicture());
                System.out.println();
                System.out.println(game.getFormalCurrentGuess());

                // Play the game
                System.out.print("Enter the character that you think is in the word: ");
                char guess = (sc.next().toLowerCase()).charAt(0);
                System.out.println();

                // Check if the character is guessed already
                while (game.isGuessedAlready(guess)) {
                    System.out.println("Try again! You have already guessed that character!");
                    guess = (sc.next().toLowerCase()).charAt(0);
                    System.out.println();
                }


                if(game.playGuess(guess)) {
                    System.out.println("Great guess! that character was in the word...");
                } else {
                    System.out.println("Unfortunately! that character is not in the word... ");
                }

            } while (!game.gameOver()); // Keep playing until the game is over

            // Play again or no ?
            System.out.println();
            System.out.println("Do you want to play another game");
            char response = sc.next().toUpperCase().charAt(0);
            doYouWantToPlay = (response == 'Y');

        }

        System.out.println("Bye now! See you another time!");
    }
}
