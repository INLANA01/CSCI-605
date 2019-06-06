/*
 * ArtWork.java
 *
 * Version: 1.0
 *
 * Revisions: 0
 *
 * @author      Aditya Landge
 * @author      Rupa Karumanchi
 *
 */

import java.util.Scanner;
import java.io.File;
import java.util.*;
import java.io.FileNotFoundException;

/**
 *
 * Artwork class defined below contains the hardcoded artwork
 *
 */

public class ArtWork {

    // Assigning the artwork to a new string "Artwork"

    static String[] Artwork = { "XXX\nXXX\nXXX\nXXX\nXXX\nXXX\nXXX\nXXX\nXXX\n",
            " __________\nXXX\nXXX\nXXX\nXXX\nXXX\nXXX\nXXX\nXXX\n",  //1
            " __________\n|         |\nXXX\nXXX\nXXX\nXXX\nXXX\nXXX\nXXX\n", //2
            " __________\n|         |\n|         |\nXXX\nXXX\nXXX\nXXX\nXXX\nXXX\n", //3
            " __________\n|         |\n|         |\n|         0  \nXXX\nXXX\nXXX\nXXX\nXXX\n", //4
            " __________\n|         |\n|         |\n|         0  \n|        /|\\\nXXX\nXXX\nXXX\nXXX\n", //5
            " __________\n|         |\n|         |\n|         0  \n|        /|\\\n|        / \\\nXXX\nXXX\nXXX\n", //6
            " __________\n|         |\n|         |\n|         0  \n|        /|\\\n|        / \\\n|          \nXXX\nXXX\n", //7
            " __________\n|         |\n|         |\n|         0  \n|        /|\\\n|        / \\\n|          \n|          \nXXX\n", //8
            " __________\n|         |\n|         |\n|         0  \n|        /|\\\n|        / \\\n|          \n|          \n|__________\n"}; //9

    /**
     * In this method we call the GetWord function to get a random word from
     * the inputted text file through scanner. This method also defines the
     * actual working of the game by comparing the inputted character to the
     * winning answer, revealing a correct letter if guessed by the player,
     * ends the game if player does not guess the word after 9 guesses and
     * prints a congratulatory message if player guesses the correct word.
     */

    public static void main( String Args[] ) {

        // Calling GetWord function to get a random word from input file

        String aWord = GetWord();

        // Declaring a new Scanner called scanner

        Scanner scanner = new Scanner( System.in );
        int countIncorrectGuess = 1; // Count of no. of wrong guesses
        int wordLength = aWord.length(); // length of random word
        String dummyWord = " "; // word formed as user guesses the letters

        for ( int index = dummyWord.length(); index < wordLength; index++ )

            // dummyWord will be in the form of "...." if there are zero
            // correct guesses. Length is decided by wordLength.

            dummyWord += ".";

        String guessLetter = ""; // To store letters guessed by the player
        boolean flag = false;

        // The following loop checks for 3 conditions of the game viz.
        // (1) Player guesses a correct letter
        // (2) Player guesses all the correct letters
        // (3) Player guesses a wrong letter
        // (4) Player gets all incorrect guesses and loses

        while ( scanner.hasNextLine() ) {
            flag = false;
            guessLetter = scanner.nextLine(); // User input

            for ( int index = 0; index < wordLength; index++ ) {
                if ( aWord.charAt(index) == guessLetter.charAt(0) ) {

                    // revealing correct letter and its position

                    dummyWord = dummyWord.substring(0,index) + guessLetter
                                + dummyWord.substring(index + 1);
                    System.out.println("Correct letter!");

                    // to discount the turn used to make a correct guess

                    countIncorrectGuess--;
                } else {
                    flag = true;
                }
            }

            // Print the dummyWord

            System.out.println( dummyWord );

            // If player gets the correct word, exit the game.

            if ( dummyWord.equals(aWord) ) {
                System.out.println( "The Guess is correct!" );
                System.exit(0 );
            }

            // Display artwork in stages for each incorrect guess

            else if ( countIncorrectGuess < 9 ) {
                if (! flag ) {
                    System.out.print(dummyWord);
                } else {
                    System.out.println(countIncorrectGuess);
                    System.out.println(Artwork[countIncorrectGuess]);
                    countIncorrectGuess++; // increment the guesses used up
                }
            } else {

                // if dummyWord is not guessed and all the chances are done,
                // print losing message and reveal correct word

                System.out.println(Artwork[9]);
                System.out.println(" You have lost the game :)");
                System.out.println(" The word was: " + aWord);
                System.out.println(" Do you want to continue (yes/no)?");
            }
        }
    }

    /**
     * In this method we are selecting a random word out of the input file
     *
     * @return aWord the random word from the input file is returned
     */

    public static String GetWord() {
        String aWord = "";
        String rand = "";
        int count = 0;

        try {
            Scanner scanner = new Scanner( new File
            ("/Users/karumanchi/IdeaProjects/untitled3/src/entry_points.txt" ) );
            Random random = new Random();
            while ( scanner.hasNext() ) {
                count++;
                rand = scanner.next();
                if( random.nextInt(count) == 0 )
                    aWord = rand;
            }

            // Print initial artwork display which consists of only X's

            System.out.println(Artwork[0] + aWord); //DELETE aWord
            System.out.println("Enter a letter >> "); // Prompt user for input
            scanner.close();
        }
        catch (FileNotFoundException e){
            System.out.println("File Not Found");
        }
        return aWord;
    }
}
