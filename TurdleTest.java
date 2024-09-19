import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * test class for Turdle
 */

public class TurdleTest
{
    /**
     * 
     * @param args main method
     */
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("enter 1 for wordle of 2 for absurdle");
        String str = scan.nextLine();
        if (str.equals("1"))
        {
            Wordle hi = new Wordle();
            hi.makeList();
            hi.chooseWord();
            int i = 0;
            System.out.println(hi.getCorrectWord());
            while ((i == 0 || !hi.correctGuess()) && i < 6)
            {
                hi.readGuess("hi");
                if (hi.validGuess())
                {
                    hi.setColors();
                    hi.print();
                    i++;
                }
            }
            if (hi.correctGuess())
            {
                System.out.println("congrats! you guessed the word in " + i + " tries");
            }
            else
            {
                System.out.println("sorry try again");
                System.out.println("the word was " + hi.getCorrectWord());
            }
        }
        else if (str.equals("2")){
            Absurdle hi = new Absurdle();
            hi.makeList();
            hi.chooseWord();
            //System.out.println(hi.getCorrectWord());
            int i = 0;
            while ((i == 0 || !hi.correctGuess()) && i < 6)
            {
                hi.readGuess("hi");
                if (hi.getGuess().equals("give up"))
                {
                    System.out.println(hi.getCorrectWord());
                    return;
                }
                if (hi.validGuess())
                {
                    hi.setColors();
                    hi.print();
                    i++;
                }
            }
            if (hi.correctGuess())
            {
                System.out.println("oh no you guessed the word on try number " + i);
                //System.out.println("the word was " + hi.getCorrectWord());
            }
            else
            {
                System.out.println("congrats! you lasted 6 guesses");
                System.out.println("the word was "+ hi.getCorrectWord());
            }
        }
    }

}
