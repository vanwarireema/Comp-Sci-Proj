import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The DisplayWordle class represents 
 * a game where players try not to
 * guess a hidden word.
 * 
 * 
 * @author Reema Vanwari, Chang He
 * @author Period - 1
 * @version 5/27/24
 */


public class DisplayWordle
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
    private JPanel       feedPan;

    /**
     * Constructs a new DisplayWordle
     * initializing the game 
     * and setting up the GUI layout.
     */
    public DisplayWordle()
    {
        checkWordle = new Wordle(); // Initializes Wordle game logic
        checkWordle.makeList();
        checkWordle.chooseWord();

        // Sets up the window sizes
        setTitle("Wordle");
        setSize(500, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Show initial instructions to the user
        ImageIcon pic1 = new ImageIcon("turtle.png");
        JLabel picLab1 = new JLabel(pic1);
        JPanel picPan1 = new JPanel(new GridBagLayout());
        picPan1.add(picLab1);
        JPanel textPan = new JPanel(new GridLayout(9, 0));
        textPan.add(new JLabel("  "));
        textPan.add(new JLabel("     Welcome to Wordle!"));
        textPan.add(new JLabel("  "));
        textPan.add(new JLabel("     Guess the 5-letter word in 6 tries!"));
        textPan.add(new JLabel("         - Each guess must be a valid 5-letter word"));
        textPan.add(new JLabel("         - Revealed hints must be used in subsequent guesses"));
        textPan.add(new JLabel("              - Green letters are in the right spot"));
        textPan.add(new JLabel("              - Yellow letters are in the wrong spot"));
        textPan.add(new JLabel("         - Type \"give up\" to give up"));

        JPanel bigPan = new JPanel(new BorderLayout());
        bigPan.add(picPan1, BorderLayout.NORTH);
        bigPan.add(textPan);
        JOptionPane.showMessageDialog(null, bigPan, "Wordle", JOptionPane.DEFAULT_OPTION);

        // creates the main panel with border layout
        pan = new JPanel(new BorderLayout());
        topPan = new JPanel();
        midPan = new JPanel(new GridLayout(MAX_GUESSES, 1));
        botPan = new JPanel();

        // Set up the top panel with a title label
        lab = new JLabel("Wordle");
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
        guessFields[0].requestFocus();

        // Creates the submit button
        submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Serif", Font.PLAIN, 20));
        submitButton.setPreferredSize(new Dimension(200, 50));
        submitButton.setBackground(new Color(131, 182, 127, 255));
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
        // midPan.setBackground(Color.CYAN);

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
     * SubmitListener is an inner class
     * handles the action event when the
     * submit button is clicked. 
     * It processes the user's guess and provides feedback.
     */
    private class SubmitListener
        implements ActionListener
    {
        private int guessCount = 0;


        /**
         * Handles the action event for the submit button. Processes the user's
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
                    // JOptionPane.showMessageDialog(null, message);
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
                        // guessFields[guessCount].requestFocus();

                        if (checkWordle.correctGuess() || guessCount == MAX_GUESSES)
                        {
                            JPanel msgPan = new JPanel(new GridLayout(3, 1));
                            String message1;
                            String message2;
                            ImageIcon icon;
                            if (checkWordle.correctGuess())
                            {
                                message1 = "Congratulations!";
                                message2 = "You guessed the word in " + guessCount + " tries.";
                                icon = new ImageIcon("smiley.png");
                            }
                            else
                            {
                                message1 = "Sorry, you lost!";
                                message2 = "The word was " + checkWordle.getCorrectWord() + ".";
                                icon = new ImageIcon("sad.png");
                            }
                            JPanel iconPan = new JPanel(new GridBagLayout());
                            JLabel iconLab = new JLabel(icon);
                            iconPan.add(iconLab);
                            msgPan.add(new JLabel(""));
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
                        JOptionPane.showMessageDialog(
                            null,
                            "Green letters must be used \nagain in the same spot.");
                        guessFields[guessCount].setText("");
                        feedbackLabels[guessCount].setText("");
                    }
                    else if (!checkWordle.yellows())
                    {
                        JOptionPane.showMessageDialog(null, "Yellow letters must be used again.");
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
         * @param feedback
         * @return a string feedback msg
         */
        private String generateFeedbackMessage(int[] feedback)
        {
            StringBuilder feedbackMessage = new StringBuilder("<html>");
            for (int i = 0; i < WORD_LENGTH; i++)
            {
                char letter = checkWordle.getGuess().charAt(i);
                // checks feedback array (that holds numbers) then
                // gives colors to the corresponding numbers
                if (feedback[i] == 2)
                {
                    feedbackMessage.append("<span style='color:#83B67F'>").append(letter)
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
         * 
         * rests the entire game
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
        SwingUtilities.invokeLater(() -> new DisplayWordle());
    }
}
