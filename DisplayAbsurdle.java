import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The DisplayAbsurdle class represents 
 * a game where players try not to
 * guess a hidden word.
 * 
 * 
 * @author Reema Vanwari, Chang He
 * @author Period - 1
 * @version 5/27/24
 */


public class DisplayAbsurdle
    extends JFrame
{
    private JTextField[] guessFields;
    private JLabel[]     feedbackLabels;
    private final int    WORD_LENGTH = 5;
    private final int    MAX_GUESSES = 6;
    private Wordle       checkWordle;

    private JPanel       pan;
    private JPanel       topPan;
    private JPanel       midPan;
    private JPanel       botPan;
    private JLabel       lab;
    private JButton      submitButton;

    /**
     * constructor for DisplayAbsurdle
     */

    public DisplayAbsurdle()
    {
        checkWordle = new Absurdle(); // Initialize your Wordle game logic
        checkWordle.makeList();
        checkWordle.chooseWord();

        // Sets up the window sizes
        setTitle("Absurdle");
        setSize(500, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        ImageIcon pic1 = new ImageIcon("redturtle.png");
        JLabel picLab1 = new JLabel(pic1);
        JPanel picPan1 = new JPanel(new GridBagLayout());
        picPan1.add(picLab1);
        JPanel textPan = new JPanel(new GridLayout(10, 0));
        textPan.add(new JLabel("  "));
        textPan.add(new JLabel("     Welcome to Absurdle!"));
        textPan.add(new JLabel("  "));
        textPan.add(new JLabel("     Try NOT to guess the 5-letter word 6 times!"));
        textPan.add(new JLabel("         - Each guess must be a valid 5-letter word"));
        textPan.add(new JLabel("         - Revealed hints must be used in subsequent guesses"));
        textPan.add(new JLabel("              - Red letters are in the right spot"));
        textPan.add(new JLabel("              - Yellow letters are in the wrong spot"));
        textPan.add(new JLabel("              - Gray letters cannot be used again"));
        textPan.add(new JLabel("         - Type \"give up\" to give up"));

        JPanel bigPan = new JPanel(new BorderLayout());
        bigPan.add(picPan1, BorderLayout.NORTH);
        bigPan.add(textPan);
        JOptionPane.showMessageDialog(null, bigPan, "Wordle", JOptionPane.DEFAULT_OPTION);

        // creates the main panel with border layout
        pan = new JPanel(new BorderLayout());
        topPan = new JPanel();
        midPan = new JPanel(new GridLayout(MAX_GUESSES, 2));
        botPan = new JPanel();

        // Set up the top panel with a title label
        lab = new JLabel("Absurdle");
        lab.setFont(new Font("Serif", Font.BOLD, 40));
        topPan.add(lab);

        // Initialize the arrays for text fields and feedback labels
        guessFields = new JTextField[MAX_GUESSES];
        feedbackLabels = new JLabel[MAX_GUESSES];

        // Loop to create and add text fields and feedback labels to the middle
        // panel
        for (int i = 0; i < MAX_GUESSES; i++)
        {
            guessFields[i] = new JTextField();
            feedbackLabels[i] = new JLabel();
            guessFields[i].setPreferredSize(new Dimension(100, 10));
            feedbackLabels[i].setPreferredSize(new Dimension(100, 10));
            guessFields[i].setHorizontalAlignment(JTextField.CENTER);
            feedbackLabels[i].setHorizontalAlignment(JLabel.CENTER);
            guessFields[i].setFont(new Font("Serif", Font.BOLD, 30));
            feedbackLabels[i].setFont(new Font("Serif", Font.BOLD, 30));
            midPan.add(guessFields[i], CENTER_ALIGNMENT);
            midPan.add(feedbackLabels[i], CENTER_ALIGNMENT);
        }

        // Creates the submit button
        submitButton = new JButton("Submit");
        submitButton.setFont(new Font ("Serif", Font.PLAIN, 20));
        submitButton.setPreferredSize(new Dimension(200, 50));
        submitButton.setBackground(new Color(254, 127, 137, 255));
        submitButton.setOpaque(true);
        submitButton.setBorderPainted(false);
        submitButton.addActionListener(new SubmitListener());
        botPan.add(submitButton);

        // Set preferred sizes and borders for panels
        midPan.setPreferredSize(new Dimension(400, 500));
        botPan.setPreferredSize(new Dimension(400, 120));
        pan.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        topPan.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        midPan.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        botPan.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        // Add sub-panels to the main panel with BorderLayout
        pan.add(topPan, BorderLayout.NORTH);
        pan.add(midPan, BorderLayout.CENTER);
        pan.add(botPan, BorderLayout.SOUTH);

        // Add the main panel to the frame and make it visible
        add(pan);
        setVisible(true);
    }

    // Class to handle submit button clicks

    /**
     * private class 
     */
    private class SubmitListener
        implements ActionListener
    {
        private int guessCount = 0;


        /** Handles the action event for the submit button. Processes the user's
         * guess, updates feedback, and checks for game end conditions.
         *
         * @param e the action event
         */

        @Override

        public void actionPerformed(ActionEvent e)
        {
            if (guessCount < MAX_GUESSES)
            {
                // Get the text from the current guess field
                String guess = guessFields[guessCount].getText().toLowerCase();

                // Pass the guess to the game logic
                checkWordle.readGuess(guess);

                if (checkWordle.getGuess().equals("give up"))
                {
                    String message = "The word was " + checkWordle.getCorrectWord() + ".";
                    JPanel msgPan = new JPanel();
                    ImageIcon icon = new ImageIcon("sad.png");
                    JPanel iconPan = new JPanel(new GridBagLayout());
                    JLabel iconLab = new JLabel(icon);
                    iconPan.add(iconLab);
                    msgPan.add(new JLabel(" "));
                    msgPan.add(new JLabel(message));
                    JPanel bigPan = new JPanel(new BorderLayout());
                    bigPan.add(iconPan, BorderLayout.NORTH);
                    bigPan.add(msgPan);
                    JOptionPane.showMessageDialog(null, bigPan, "", JOptionPane.DEFAULT_OPTION);
                    //JOptionPane.showMessageDialog(null, message);
                    resetGame();
                    return;
                }

                if (guess.length() == WORD_LENGTH)
                {

                    if (checkWordle.actualWord() && checkWordle.validGuess())
                    {
                        checkWordle.setColors(); // Set colors based on the
                                                 // guess
                        int[] feedback = checkWordle.getArray(); // Get feedback
                                                                 // array

                        // Construct feedback message
                        String feedbackMessage = generateFeedbackMessage(feedback);

                        // Update feedback label
                        feedbackLabels[guessCount].setText(feedbackMessage);
                        guessCount++;

                        if (checkWordle.correctGuess() || guessCount == MAX_GUESSES)
                        {
                            JPanel msgPan = new JPanel(new GridLayout(3, 1));
                            String message1;
                            String message2;
                            ImageIcon icon;
                            if (checkWordle.correctGuess())
                            {
                                message1 = "Sorry, you lost!";
                                message2 = "You guessed the word in " + guessCount + " tries.";
                                icon = new ImageIcon("sad.png");
                            }
                            else
                            {
                                message1 = "Congrats! You avoided the word!";
                                message2 = "The word was " + checkWordle.getCorrectWord() + ".";
                                icon = new ImageIcon("smiley.png");
                            }
                            JPanel iconPan = new JPanel(new GridBagLayout());
                            JLabel iconLab = new JLabel(icon);
                            iconPan.add(iconLab);
                            msgPan.add(new JLabel(" "));
                            msgPan.add(new JLabel(message1));
                            msgPan.add(new JLabel(message2));
                            JPanel bigPan = new JPanel(new BorderLayout());
                            bigPan.add(iconPan, BorderLayout.NORTH);
                            bigPan.add(msgPan);
                            JOptionPane
                                .showMessageDialog(null, bigPan, "", JOptionPane.DEFAULT_OPTION);

                            resetGame();
                        }
                    }
                    else if (!checkWordle.actualWord())
                    {
                        JOptionPane.showMessageDialog(null, "Not an actual word.");
                        guessFields[guessCount].setText("");
                        feedbackLabels[guessCount].setText("");
                    }
                    else if (!checkWordle.greens())
                    {
                        JOptionPane.showMessageDialog(null, "Red letters must be used \nagain in the same spot.");
                        guessFields[guessCount].setText("");
                        feedbackLabels[guessCount].setText("");
                    }
                    else if (!checkWordle.yellows())
                    {
                        JOptionPane.showMessageDialog(null, "Yellow letters must be used again.");
                        guessFields[guessCount].setText("");
                        feedbackLabels[guessCount].setText("");
                    }
                    else if (!checkWordle.grays())
                    {
                        JOptionPane.showMessageDialog(null, "Gray letters cannot be used again.");
                        guessFields[guessCount].setText("");
                        feedbackLabels[guessCount].setText("");
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Not a 5-letter word.");
                    guessFields[guessCount].setText("");
                    feedbackLabels[guessCount].setText("");
                }
                guessFields[guessCount].requestFocus();
            }
        }


        // Helper method to generate feedback message
        // adds colors using
        /**
         * 
         * @param feedback for msg
         * @return str msg
         */
        private String generateFeedbackMessage(int[] feedback)
        {
            StringBuilder feedbackMessage = new StringBuilder("<html>"); // StringBuilder
                                                                         // allows
                                                                         // for
                                                                         // modification
            for (int i = 0; i < WORD_LENGTH; i++)
            {
                char letter = checkWordle.getGuess().charAt(i);
                if (feedback[i] == 2)
                {
                    feedbackMessage.append("<span style='color:#FE7F89'>").append(letter)
                        .append("</span> ");
                }
                else if (feedback[i] == 1)
                {
                    feedbackMessage.append("<span style='color:#F4D03F'>").append(letter)
                        .append("</span> ");
                }
                else
                {
                    feedbackMessage.append("<span style='color:#B2B2B2'>").append(letter)
                        .append("</span> ");
                }
            }
            feedbackMessage.append("</html>"); // use html for better user
                                               // friendly ??
            return feedbackMessage.toString();
        }


        // Game reset
        /**
         * resets game
         */
        private void resetGame()
        {
            for (int i = 0; i < MAX_GUESSES; i++)
            {
                guessFields[i].setText("");
                feedbackLabels[i].setText("");
            }
            guessFields[0].requestFocus();
            guessCount = 0;
            checkWordle.chooseWord(); // Reset the game logic by choosing a new
                                      // word
            checkWordle.resetEverything();
        }
    }
/**
 * 
 * @param args main method
 */
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() -> new DisplayAbsurdle());
    }
}