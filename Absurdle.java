import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;


/**
 * The Absurdle class represents 
 * a game where players try not to
 * guess a hidden word.
 * 
 * 
 * @author Reema Vanwari, Chang He
 * @author Period - 1
 * @version 5/27/24
 */

public class Absurdle extends Wordle
{
    /**
     * method that tracks whether a gray
     * letter is repeated in a guess
     * 
     * @return true if a gray letter is repeated
     */
    public boolean grays()
    {
        for (int i = 0; i < 5; i++)
        {
            if (getArray()[i] == 0 && getGrayLetters().contains(getGuess().charAt(i)+""))
            {
                return false;
            }
        }
        return true;
    }

}