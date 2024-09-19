import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * The Wordle class represents 
 * a game where players try to
 * guess a hidden word.
 * 
 * 
 * @author Reema Vanwari, Chang He
 * @author Period - 1
 * @version 5/27/24
 */

public class Wordle
{
    private String                 correctWord;
    private String                 guess;
    private static HashSet<String> words = new HashSet<String>(8000);
    private int[]                  arr;
    private String                 yellowLetters;
    private String                 grayLetters;
/**
 * constructor for Wordle
 * 
 */
    public Wordle()
    {
        arr = new int[5];
        yellowLetters = "";
        grayLetters = "";
    }
    /**
     * void method
     * resets the game
     */

    public void resetEverything()
    {
        arr = new int[5];
        yellowLetters = "";
        grayLetters = "";
    }

    /**
     * void method
     * 
     * creates the list of words
     * by scanning the txt file
     * adds words to hashSet words
     */

    public void makeList()
    {
        File text = new File("WordList.txt");
        try
        {
            Scanner scan = new Scanner(text);
            String str = "";
            while (scan.hasNextLine())
            {
                str = scan.nextLine();
                words.add(str);

            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("file not found");
        }
    }

/**
 * void method
 * 
 * chooses a random word from hashSet
 * by create a random int value
 * and selecting the word from the arrayList
 */
    public void chooseWord()
    {
        int rand = 0;
        rand = (int)(Math.random() * 5757) + 1;
        List<String> list = new ArrayList<String>(words);
        correctWord = list.get(rand);
    }

    /**
     * 
     * void method used to read the guess
     * @param g guess inputed by user
     */

    public void readGuess(String g)
    {
        /*Scanner scan = new Scanner(System.in);
        System.out.println("enter guess");
        guess = scan.nextLine();*/
        guess = g;
    }


    // not sure if we need
    /**
     * 
     * getter method
     * @return the correct word
     */
    public String getCorrectWord()
    {
        return correctWord;
    }


    // not sure if we need
    /**
     * 
     * getter method to get user guess
     * 
     * @return the users guess
     */
    public String getGuess()
    {
        return guess;
    }

    /**
     * 
     * arr that holds int values (0-2)
     * @return arr of int values
     */

    public int[] getArray()
    {
        return arr;
    }

    /**
     * 
     * grayLetters holds list of letters
     * that are not in the word 
     * 
     * @return list of grayLetters
     */

    public String getGrayLetters()
    {
        return grayLetters;
    }

    /**
     * 
     * yellow letters holds list of letters
     * that are in the word 
     * 
     * @return string of yellowLetters
     */
    public String getYellowLetters()
    {
        return yellowLetters;
    }
/**
 * 
 * @return true if the guess was
 * the correct word
 */

    public boolean correctGuess()
    {
        return guess.equals(correctWord);
    }

/**
 * 
 * @return true if its a valid guess
 */
    public boolean validGuess()
    {
        return actualWord() && greens() && yellows() && grays();
    }

    /**
     * 
     * @return true if an actual word
     */

    public boolean actualWord()
    {
        return words.contains(guess);
    }

    /**
     * 
     * @return true if guess contains
     * green letters in same spot
     */

    public boolean greens()
    {
        int[] tempA = new int[5];
        for (int i = 0; i < 5; i++)
        {
            tempA[i] = arr[i];
        }
        for (int i = 0; i < 5; i++)
        {
            if (tempA[i] == 2)
            {
                if (guess.charAt(i) != correctWord.charAt(i))
                {
                    return false;
                }
            }
        }
        return true;
    }
/**
 * 
 * @return true whether the guess contains 
 * yellow letters from prev guess
 */

    public boolean yellows()
    {
        for (int i = 0; i < yellowLetters.length(); i++)
        {
            if (!guess.contains(yellowLetters.charAt(i) + ""))
            {
                return false;
            }

        }
        return true;

    }

    /**
     * 
     * whether a value of gray letter
     * is a valid guess
     * @return true
     */

    public boolean grays()
    {
        return true;
    }

    /**
     * assigns numbers to each index
     * indicating whether the letter should
     * be yellow/green/yellow
     */

    public void setColors()
    {
        String temp = correctWord;
        for (int i = 0; i < 5; i++)
        {
            if (guess.charAt(i) == correctWord.charAt(i))
            {
                arr[i] = 2;
                temp = temp.replaceFirst(guess.charAt(i) + "", "*");
            }
        }
        for (int i = 0; i < 5; i++)
        {
            if (arr[i] != 2 && !temp.contains(guess.charAt(i) + ""))
            {
                arr[i] = 0;
                grayLetters += guess.charAt(i);
            }
        }
        for (int i = 0; i < 5; i++)
        {
            if (arr[i] != 2 && temp.contains(guess.charAt(i) + ""))
            {
                arr[i] = 1;
                yellowLetters += guess.charAt(i);
                temp = temp.replaceFirst(guess.charAt(i) + "", "*");
            }
        }
    }

    //testing
    /**
     * void method used for testing
     */
    public void print()
    {
        for (int i = 0; i < 5; i++)
        {
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }
}